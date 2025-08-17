package com.dt.shopsphere_monolith.order.model;

import com.dt.shopsphere_monolith.product.model.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private Integer quantity;

    // Store the price at the time of purchase for historical accuracy
    @Column(name = "price_per_unit", nullable = false)
    private BigDecimal pricePerUnit;
}
