package com.loka.service;

import com.loka.dto.CartDto;
import com.loka.model.CartItem;
import com.loka.model.Product;
import com.loka.model.User;
import com.loka.repository.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartItemRepository cartItemRepository;
    private final ProductService productService;
    private final UserService userService;

    public CartDto.CartResponse getCart(String email) {
        User user = userService.getUserByEmail(email);
        List<CartItem> items = cartItemRepository.findByUser(user);
        return buildCartResponse(items);
    }

    @Transactional
    public CartDto.CartResponse addToCart(String email, CartDto.AddToCartRequest request) {
        User user = userService.getUserByEmail(email);
        Product product = productService.getProductEntityById(request.getProductId());

        Optional<CartItem> existingItem = cartItemRepository.findByUserAndProduct(user, product);

        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + request.getQuantity());
            cartItemRepository.save(item);
        } else {
            CartItem newItem = CartItem.builder()
                    .user(user)
                    .product(product)
                    .quantity(request.getQuantity())
                    .build();
            cartItemRepository.save(newItem);
        }

        return getCart(email);
    }

    @Transactional
    public CartDto.CartResponse updateCartItem(String email, Long itemId, CartDto.UpdateCartRequest request) {
        User user = userService.getUserByEmail(email);
        CartItem item = cartItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        if (!item.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized");
        }

        item.setQuantity(request.getQuantity());
        cartItemRepository.save(item);
        return getCart(email);
    }

    @Transactional
    public CartDto.CartResponse removeFromCart(String email, Long itemId) {
        User user = userService.getUserByEmail(email);
        CartItem item = cartItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        if (!item.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized");
        }

        cartItemRepository.delete(item);
        return getCart(email);
    }

    @Transactional
    public void clearCart(User user) {
        cartItemRepository.deleteByUser(user);
    }

    public List<CartItem> getCartItems(User user) {
        return cartItemRepository.findByUser(user);
    }

    private CartDto.CartResponse buildCartResponse(List<CartItem> items) {
        CartDto.CartResponse response = new CartDto.CartResponse();

        List<CartDto.CartItemResponse> itemResponses = items.stream()
                .map(this::mapToItemResponse)
                .collect(Collectors.toList());

        BigDecimal total = itemResponses.stream()
                .map(CartDto.CartItemResponse::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        int totalItems = items.stream().mapToInt(CartItem::getQuantity).sum();

        response.setItems(itemResponses);
        response.setTotalAmount(total);
        response.setTotalItems(totalItems);
        return response;
    }

    private CartDto.CartItemResponse mapToItemResponse(CartItem item) {
        CartDto.CartItemResponse response = new CartDto.CartItemResponse();
        response.setId(item.getId());
        response.setProductId(item.getProduct().getId());
        response.setProductName(item.getProduct().getName());
        response.setProductImageUrl(item.getProduct().getImageUrl());
        response.setProductPrice(item.getProduct().getPrice());
        response.setQuantity(item.getQuantity());
        response.setSubtotal(item.getProduct().getPrice().multiply(new BigDecimal(item.getQuantity())));
        return response;
    }
}
