package com.example.Product.Inventory.Management.System.controller;


import com.example.Product.Inventory.Management.System.entities.Category;
import com.example.Product.Inventory.Management.System.entities.Product;
import com.example.Product.Inventory.Management.System.entities.RequestProductDTO;
import com.example.Product.Inventory.Management.System.services.CategoryServices;
import com.example.Product.Inventory.Management.System.services.ProductServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    ProductServices productServices;
    @Autowired
    CategoryServices categoryServices;
    @GetMapping("/")
    public String homePage(Model model){
        List<Product> products=productServices.getAllProduct();
        model.addAttribute("products",products);
        return "index";
    }
    @GetMapping("/addPage")
    public String addingPage(Model model){
        model.addAttribute("products", new RequestProductDTO()); // for form binding
        List<Category> categoryList=categoryServices.getAllCategory();
        model.addAttribute("categories",categoryList);
        if (!model.containsAttribute("errors")) {
            model.addAttribute("errors", new ArrayList<String>());
        }
        return "add";
    }
    @PostMapping("/createProduct")
    public String createProduct(@Valid @ModelAttribute RequestProductDTO product, BindingResult result, RedirectAttributes redirectAttribute){
        if(result.hasErrors()){
            List<String> errors=result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            redirectAttribute.addFlashAttribute("errors",errors);
            return "redirect:/addPage";
        }
        productServices.saveProject(product);
        redirectAttribute.addFlashAttribute("success","Product Added Successfully!");
        return "redirect:/";

    }
}
