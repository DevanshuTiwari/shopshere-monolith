package com.dt.shopsphere_monolith.product.service;

import com.dt.shopsphere_monolith.inventory.model.Inventory;
import com.dt.shopsphere_monolith.product.dto.ProductCreateRequest;
import com.dt.shopsphere_monolith.product.dto.ProductResponse;
import com.dt.shopsphere_monolith.product.model.Product;
import com.dt.shopsphere_monolith.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponse createProduct(ProductCreateRequest productCreateRequest) {

        // DTO to Entity
        Product product = new Product();
        product.setProductName(productCreateRequest.name());
        product.setProductDescription(productCreateRequest.description());
        product.setProductPrice(productCreateRequest.price());
        product.setProductCategory(productCreateRequest.category());

        Inventory inventory = new Inventory();
        inventory.setProductStockCount(productCreateRequest.initialStockCount());

        product.setInventory(inventory);
        inventory.setProduct(product);

        Product savedProduct = productRepository.save(product);

        // Entity to DTO Response
        return new ProductResponse(
                savedProduct.getId(),
                savedProduct.getProductName(),
                savedProduct.getProductDescription(),
                savedProduct.getProductCategory(),
                savedProduct.getProductPrice(),
                savedProduct.getInventory().getProductStockCount()
        );
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(product -> new ProductResponse(
                        product.getId(),
                        product.getProductName(),
                        product.getProductDescription(),
                        product.getProductCategory(),
                        product.getProductPrice(),
                        product.getInventory().getProductStockCount()
                )).collect(Collectors.toList());
    }
}
