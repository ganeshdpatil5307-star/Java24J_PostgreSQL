package com.ecommerce.service;

import com.ecommerce.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final List<Product> products = new ArrayList<>();

    public ProductService() {
        seedProducts();
    }

    private void seedProducts() {
        products.add(new Product(1L, "Wireless Headphones",
                "Noise-cancelling over-ear headphones with 30h battery.",
                99.99, "https://picsum.photos/seed/headphones/400/300"));
        products.add(new Product(2L, "Smart Watch",
                "Fitness tracking smartwatch with heart-rate monitor.",
                149.99, "https://picsum.photos/seed/watch/400/300"));
        products.add(new Product(3L, "Mechanical Keyboard",
                "RGB backlit mechanical keyboard with blue switches.",
                79.99, "https://picsum.photos/seed/keyboard/400/300"));
        products.add(new Product(4L, "Wireless Mouse",
                "Ergonomic wireless mouse with silent clicks.",
                29.99, "https://picsum.photos/seed/mouse/400/300"));
        products.add(new Product(5L, "USB-C Hub",
                "7-in-1 USB-C hub with HDMI and card reader.",
                49.99, "https://picsum.photos/seed/hub/400/300"));
        products.add(new Product(6L, "Portable Speaker",
                "Waterproof Bluetooth speaker with deep bass.",
                59.99, "https://picsum.photos/seed/speaker/400/300"));
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public Optional<Product> getProductById(Long id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst();
    }
}
