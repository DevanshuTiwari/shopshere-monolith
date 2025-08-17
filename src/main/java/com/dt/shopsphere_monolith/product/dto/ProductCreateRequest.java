package com.dt.shopsphere_monolith.product.dto;

import java.math.BigDecimal;

public record ProductCreateRequest(
        String name,
        String description,
        BigDecimal price,
        String category,
        Integer initialStockCount
) {}
