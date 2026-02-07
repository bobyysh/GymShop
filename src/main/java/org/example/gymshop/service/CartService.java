package org.example.gymshop.service;

import jakarta.transaction.Transactional;
import org.example.gymshop.model.CartItem;
import org.example.gymshop.model.Product;
import org.example.gymshop.model.User;
import org.example.gymshop.repository.CartItemRepository;
import org.example.gymshop.repository.ProductRepository;
import org.example.gymshop.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public CartService(CartItemRepository cartItemRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.cartItemRepository = cartItemRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public void addToCart(String username, Long productId) {
        User user = userRepository.findByUsername(username).orElseThrow();
        Product product = productRepository.findById(productId).orElseThrow();

        CartItem cartItem = cartItemRepository.findByUserAndProduct(user, product)
                .orElse(new CartItem(user, product,0));

        cartItem.setQuantity(cartItem.getQuantity() + 1);
        cartItemRepository.save(cartItem);
    }

    public List<CartItem> getCartItems(String username){
        User user = userRepository.findByUsername(username).orElseThrow();
        return cartItemRepository.findByUser(user);
    }

    public void removeFromCart(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }

    @Transactional
    public void clearCart(String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        cartItemRepository.deleteByUser(user);
    }

}
