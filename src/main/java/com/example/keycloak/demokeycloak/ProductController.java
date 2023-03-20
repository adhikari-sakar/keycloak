package com.example.keycloak.demokeycloak;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(path = "/")
    public String index() {
        return "index";
    }

    @GetMapping("/products")
    public String getMyProducts(Model model, Principal principal) {
        model.addAttribute("products", productService.getProducts());
        model.addAttribute("userName", principal.getName());
        return "products";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) throws ServletException {
        request.logout();
        return "index";
    }
}

@Service
class ProductService {

    public List<Product> getProducts() {
        return Arrays.asList(new Product("P1"), new Product("P2"), new Product("P3"));
    }
}

class Product {
    private String name;

    public Product(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}