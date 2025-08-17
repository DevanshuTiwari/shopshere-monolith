package com.dt.shopsphere_monolith.inventory.repository;

import com.dt.shopsphere_monolith.inventory.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}
