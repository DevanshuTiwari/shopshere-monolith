package com.dt.shopsphere_monolith.order.dto;

import java.math.BigDecimal;

public record OrderItemResponse(
        Long productId,
        String productName,
        Integer quantity,
        BigDecimal pricePerUnit
) {}
