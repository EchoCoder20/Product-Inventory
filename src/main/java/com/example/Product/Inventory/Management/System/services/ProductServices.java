package com.example.Product.Inventory.Management.System.services;

import com.example.Product.Inventory.Management.System.entities.Category;
import com.example.Product.Inventory.Management.System.entities.Product;
import com.example.Product.Inventory.Management.System.entities.RequestProductDTO;
import com.example.Product.Inventory.Management.System.repositories.CategoryRepositories;
import com.example.Product.Inventory.Management.System.repositories.ProductRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductServices {
    @Autowired
    ProductRepositories productRepositories;
    @Autowired
    CategoryRepositories categoryRepositories;
    public List<Product> getAllProduct(){
        return productRepositories.findAll();
    }
    public Product saveProject(RequestProductDTO productDTO){
        Product product=new Product();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());
        Category category=categoryRepositories.findById(productDTO.getCategoryId()).get();
        System.out.println(category);
        product.setCategory(category);
        product.setCreatedData(new Date());
        product.setActive(true);

        return productRepositories.save(product);
    }
}
