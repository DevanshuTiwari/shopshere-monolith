package com.dt.shopsphere_monolith.product.controller;

import com.dt.shopsphere_monolith.product.dto.ProductCreateRequest;
import com.dt.shopsphere_monolith.product.dto.ProductResponse;
import com.dt.shopsphere_monolith.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(@RequestBody ProductCreateRequest productCreateRequest) {
        return productService.createProduct(productCreateRequest);
    }

    @GetMapping
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }
}
