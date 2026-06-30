package com.ecommerce.service;

import com.ecommerce.model.CartEntry;
import com.ecommerce.model.CartItem;
import com.ecommerce.model.Product;
import com.ecommerce.repository.CartEntryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CartService {

    private final CartEntryRepository cartEntryRepository;
    private final ProductService productService;

    public CartService(CartEntryRepository cartEntryRepository, ProductService productService) {
        this.cartEntryRepository = cartEntryRepository;
        this.productService = productService;
    }

    public List<CartItem> getCart() {
        return cartEntryRepository.findAll().stream()
                .map(this::toCartItem)
                .toList();
    }

    @Transactional
    public List<CartItem> addToCart(Long productId) {
        productService.getProductById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found: " + productId));

        cartEntryRepository.findByProductId(productId).ifPresentOrElse(
                entry -> entry.setQuantity(entry.getQuantity() + 1),
                () -> cartEntryRepository.save(new CartEntry(productId, 1))
        );
        return getCart();
    }

    @Transactional
    public List<CartItem> removeFromCart(Long productId) {
        cartEntryRepository.findByProductId(productId).ifPresent(entry -> {
            if (entry.getQuantity() > 1) {
                entry.setQuantity(entry.getQuantity() - 1);
            } else {
                cartEntryRepository.deleteByProductId(productId);
            }
        });
        return getCart();
    }

    private CartItem toCartItem(CartEntry entry) {
        Product product = productService.getProductById(entry.getProductId())
                .orElse(null);
        return new CartItem(product, entry.getQuantity());
    }
}
