package com.dt.shopsphere_monolith.order.dto;


import java.util.List;

public record OrderRequest(
        List<OrderItemRequest> items
){}
