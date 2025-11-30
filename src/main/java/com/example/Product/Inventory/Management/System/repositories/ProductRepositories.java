package com.example.Product.Inventory.Management.System.repositories;

import com.example.Product.Inventory.Management.System.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepositories extends JpaRepository<Product,Integer> {
}
