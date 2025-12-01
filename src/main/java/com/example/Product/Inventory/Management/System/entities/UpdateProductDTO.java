package com.example.Product.Inventory.Management.System.entities;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class UpdateProductDTO {
    private Integer id;
    @NotEmpty(message = "Product Name Must Not Be Empty!")
    private String name;
    @Min(value = 1,message = "Price must be greater than zero !")
    private double price;
    @Positive(message = "Quantity must not be negative!")
    private Integer quantity;
    private Integer categoryId;
}
