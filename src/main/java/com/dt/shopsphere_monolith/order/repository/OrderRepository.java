package com.dt.shopsphere_monolith.order.repository;

import com.dt.shopsphere_monolith.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
