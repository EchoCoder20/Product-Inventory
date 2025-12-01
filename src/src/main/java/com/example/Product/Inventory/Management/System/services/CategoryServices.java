package com.example.Product.Inventory.Management.System.services;

import com.example.Product.Inventory.Management.System.entities.Category;
import com.example.Product.Inventory.Management.System.entities.Product;
import com.example.Product.Inventory.Management.System.repositories.CategoryRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServices {
    @Autowired
    CategoryRepositories categoryRepositories;
    public List<Category> getAllCategory(){
        return categoryRepositories.findAll();
    }
}
