package com.ecommerce.controller;

import com.ecommerce.dto.CartRequest;
import com.ecommerce.model.CartItem;
import com.ecommerce.service.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public List<CartItem> getCart() {
        return cartService.getCart();
    }

    @PostMapping("/add")
    public List<CartItem> addToCart(@RequestBody CartRequest request) {
        return cartService.addToCart(request.getProductId());
    }

    @PostMapping("/remove")
    public List<CartItem> removeFromCart(@RequestBody CartRequest request) {
        return cartService.removeFromCart(request.getProductId());
    }
}
