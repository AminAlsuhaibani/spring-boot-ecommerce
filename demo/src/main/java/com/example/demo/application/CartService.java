package com.example.demo.application;

import com.example.demo.domain.CartItem;
import com.example.demo.domain.Product;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CartService {

    // In-memory cart
    private final Map<Integer, CartItem> cart;

    public CartService(Map<Integer, CartItem> cart) {
        this.cart = cart;
    }

    // Add product to the cart
    public void addToCart(Product product) {
        CartItem cartItem = cart.get(product.getId());
        if (cartItem != null) {
            cartItem.incrementQuantity();
        } else {
            cart.put(product.getId(), new CartItem(product));
        }
    }

    // Remove product from cart
    public void removeFromCart(int productId) {
        CartItem cartItem = cart.get(productId);
        if (cartItem != null) {
            if (cartItem.getQuantity() > 1) {
                cartItem.decrementQuantity();
            } else {
                cart.remove(productId);
            }
        }
    }

    // View all items in the cart
    public Map<Integer, CartItem> viewCart() {
        return cart;
    }

    // Empty the entire cart
    public void emptyCart() {
        cart.clear();
    }

    // Checkout and calculate totals (total price, total tax)
    public double calculateTotal() {
        return cart.values().stream().mapToDouble(CartItem::getTotalPrice).sum();
    }

    public double calculateTotalTax() {
        return cart.values().stream().mapToDouble(CartItem::getTotalTax).sum();
    }
}
