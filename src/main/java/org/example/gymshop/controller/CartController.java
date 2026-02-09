package org.example.gymshop.controller;

import org.example.gymshop.model.CartItem;
import org.example.gymshop.model.User;
import org.example.gymshop.service.CartService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public String viewCart(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        if(userDetails == null) return "redirect:/login";

        List<CartItem> cartItems = cartService.getCartItems(userDetails.getUsername());

        double totalPrice = 0;
        for (CartItem item : cartItems) {
            double price = item.getProduct().getPrice();
            int quantity = item.getQuantity();
            totalPrice = totalPrice + (price * quantity);
        }
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalPrice", totalPrice);
        return "cart";
    }

    @PostMapping("/add/{productId}")
    public String addCartItem(@PathVariable Long productId, @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) return "redirect:/login";

        cartService.addToCart(userDetails.getUsername(), productId);
        return "redirect:/products";
    }

    @GetMapping("/remove/{id}")
    public String removeCartItem(@PathVariable Long id) {
        cartService.removeFromCart(id);
        return "redirect:/cart";
    }

    @PostMapping("/checkout")
    public String checkout(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails != null){
            cartService.clearCart(userDetails.getUsername());
        }
        return "redirect:/products";
    }

}
