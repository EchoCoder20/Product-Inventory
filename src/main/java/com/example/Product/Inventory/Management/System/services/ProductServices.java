package com.example.Product.Inventory.Management.System.services;

import com.example.Product.Inventory.Management.System.entities.Category;
import com.example.Product.Inventory.Management.System.entities.Product;
import com.example.Product.Inventory.Management.System.entities.RequestProductDTO;
import com.example.Product.Inventory.Management.System.entities.UpdateProductDTO;
import com.example.Product.Inventory.Management.System.repositories.CategoryRepositories;
import com.example.Product.Inventory.Management.System.repositories.ProductRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.PublicKey;
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
    public Product findById(int id) throws Exception{
        Product products=productRepositories.findById(id).get();
        if(products !=null){
            return products;
        }else{
            throw new Exception("Bad Request");
        }
    }
    public UpdateProductDTO  convertToUpdateDTO(Product product){
        UpdateProductDTO dto = new UpdateProductDTO();

        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setQuantity(product.getQuantity());
        dto.setCategoryId(product.getCategory().getId());

        return dto;
    }

    public Product updateProduct(UpdateProductDTO productDTO){
        Product product=productRepositories.findById(productDTO.getId()).get();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        Category category=categoryRepositories.findById(productDTO.getCategoryId()).get();
        product.setCategory(category);
        product.setQuantity(productDTO.getQuantity());
        product.setUpdatedData(new Date());
        product.setActive(product.isActive());
        return productRepositories.save(product);
    }
    public void deleteProduct(int id) throws Exception{
        Product product=productRepositories.findById(id).get();
        if(product!=null){
            product.setUpdatedData(new Date());
            product.setActive(false);
            productRepositories.save(product);
        }else{
            throw new Exception("Bad Request");
        }
    }

}
