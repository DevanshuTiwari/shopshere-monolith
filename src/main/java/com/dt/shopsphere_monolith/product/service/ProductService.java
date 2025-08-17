package com.dt.shopsphere_monolith.product.service;

import com.dt.shopsphere_monolith.product.dto.ProductCreateRequest;
import com.dt.shopsphere_monolith.product.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductCreateRequest productCreateRequest);
    List<ProductResponse> getAllProducts();
}
