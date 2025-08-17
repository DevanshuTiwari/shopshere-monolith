package com.dt.shopsphere_monolith.order.service;

import com.dt.shopsphere_monolith.order.dto.OrderRequest;
import com.dt.shopsphere_monolith.order.dto.OrderResponse;

public interface OrderService {
    OrderResponse placeOrder(String userEmail, OrderRequest orderRequest);
}
