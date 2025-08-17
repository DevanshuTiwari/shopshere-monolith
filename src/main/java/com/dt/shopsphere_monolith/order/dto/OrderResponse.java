package com.dt.shopsphere_monolith.order.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderResponse(
        Long orderId,
        Long userId,
        LocalDateTime orderDate,
        String status,
        BigDecimal totalAmount,
        List<OrderItemResponse> items
) {}
