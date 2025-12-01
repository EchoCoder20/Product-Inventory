package com.example.Product.Inventory.Management.System.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private double price;
    private Integer quantity;
    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;
    Date createdData;
    Date updatedData;
    boolean isActive;


}
