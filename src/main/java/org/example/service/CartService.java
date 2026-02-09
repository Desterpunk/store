package org.example.service;

import org.example.model.CartItem;
import org.example.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CartService {
    private List<CartItem> cartItems;

    public CartService() {
        cartItems = new ArrayList<>();
    }

    public void addProduct(Product product) {
        Optional<CartItem> existingItem = cartItems.stream()
                .filter(item -> item.getProduct().getId().equals(product.getId()))
                .findFirst();

        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + 1);
        } else {
            cartItems.add(new CartItem(product, 1));
        }
    }

    public void removeProduct(CartItem item) {
        cartItems.remove(item);
    }

    public List<CartItem> getCartItems() {
        return new ArrayList<>(cartItems);
    }

    public double getTotal() {
        return cartItems.stream()
                .mapToDouble(CartItem::getSubtotal)
                .sum();
    }

    public void clearCart() {
        cartItems.clear();
    }

    public String getOrderSummary() {
        StringBuilder summary = new StringBuilder();
        summary.append("🛒 *Orden de Compra*\n\n");

        for (CartItem item : cartItems) {
            summary.append(String.format("• %s x%d - $%.2f\n",
                item.getProduct().getName(),
                item.getQuantity(),
                item.getSubtotal()));
        }

        summary.append(String.format("\n*Total: $%.2f*", getTotal()));
        return summary.toString();
    }
}
