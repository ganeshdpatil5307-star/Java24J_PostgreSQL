package com.ecommerce.service;

import com.ecommerce.model.CartItem;
import com.ecommerce.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class CartService {

    // In-memory cart keyed by productId
    private final Map<Long, CartItem> cart = new LinkedHashMap<>();

    private final ProductService productService;

    public CartService(ProductService productService) {
        this.productService = productService;
    }

    public List<CartItem> getCart() {
        return new ArrayList<>(cart.values());
    }

    public List<CartItem> addToCart(Long productId) {
        Product product = productService.getProductById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found: " + productId));

        CartItem existing = cart.get(productId);
        if (existing != null) {
            existing.setQuantity(existing.getQuantity() + 1);
        } else {
            cart.put(productId, new CartItem(product, 1));
        }
        return getCart();
    }

    public List<CartItem> removeFromCart(Long productId) {
        CartItem existing = cart.get(productId);
        if (existing != null) {
            if (existing.getQuantity() > 1) {
                existing.setQuantity(existing.getQuantity() - 1);
            } else {
                cart.remove(productId);
            }
        }
        return getCart();
    }
}
