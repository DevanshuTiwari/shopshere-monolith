package com.dt.shopsphere_monolith.order.service;

import com.dt.shopsphere_monolith.exception.InsufficientStockException;
import com.dt.shopsphere_monolith.exception.ResourceNotFoundException;
import com.dt.shopsphere_monolith.inventory.model.Inventory;
import com.dt.shopsphere_monolith.inventory.repository.InventoryRepository;
import com.dt.shopsphere_monolith.order.dto.OrderItemRequest;
import com.dt.shopsphere_monolith.order.dto.OrderItemResponse;
import com.dt.shopsphere_monolith.order.dto.OrderRequest;
import com.dt.shopsphere_monolith.order.dto.OrderResponse;
import com.dt.shopsphere_monolith.order.model.Order;
import com.dt.shopsphere_monolith.order.model.OrderItem;
import com.dt.shopsphere_monolith.order.repository.OrderRepository;
import com.dt.shopsphere_monolith.product.model.Product;
import com.dt.shopsphere_monolith.product.repository.ProductRepository;
import com.dt.shopsphere_monolith.user.model.User;
import com.dt.shopsphere_monolith.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final InventoryRepository inventoryRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository, ProductRepository productRepository, InventoryRepository inventoryRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    @Transactional
    public OrderResponse placeOrder(String userEmail, OrderRequest orderRequest) {

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + userEmail));

        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("PENDING");

        BigDecimal totalAmount = BigDecimal.ZERO;

        // Process each item in the order
        for (OrderItemRequest itemRequest : orderRequest.items()) {
            Product product = productRepository.findById(itemRequest.productId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + itemRequest.productId()));

            // Check and update inventory
            Inventory inventory = product.getInventory();
            if (inventory.getProductStockCount() < itemRequest.quantity()) {
                throw new InsufficientStockException("Insufficient stock for product: " + product.getProductName());
            }
            inventory.setProductStockCount(inventory.getProductStockCount() - itemRequest.quantity());
            inventoryRepository.save(inventory);

            // Create OrderItem and add to Order
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setQuantity(itemRequest.quantity());
            orderItem.setPricePerUnit(product.getProductPrice());
            order.addOrderItem(orderItem);

            // Calculate total amount
            totalAmount = totalAmount.add(product.getProductPrice().multiply(BigDecimal.valueOf(itemRequest.quantity())));
        }

        order.setTotalAmount(totalAmount);

        // Save the complete order
        Order savedOrder = orderRepository.save(order);

        // Map to response DTO
        return mapToOrderResponse(savedOrder);
    }

    private OrderResponse mapToOrderResponse(Order order) {
        List<OrderItemResponse> itemResponses = order.getItems().stream()
                .map(item -> new OrderItemResponse(
                        item.getProduct().getId(),
                        item.getProduct().getProductName(),
                        item.getQuantity(),
                        item.getPricePerUnit()))
                .collect(Collectors.toList());

        return new OrderResponse(
                order.getId(),
                order.getUser().getId(),
                order.getOrderDate(),
                order.getStatus(),
                order.getTotalAmount(),
                itemResponses
        );
    }
}
