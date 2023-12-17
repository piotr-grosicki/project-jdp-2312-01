package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.exceptions.CartNotFoundException;
import com.kodilla.ecommercee.exceptions.ProductNotFoundException;
import com.kodilla.ecommercee.exceptions.UserNotFoundException;
import com.kodilla.ecommercee.model.repository.CartRepository;
import com.kodilla.ecommercee.model.repository.ProductRepository;
import com.kodilla.ecommercee.model.repository.UserRepository;
import com.kodilla.ecommercee.service.dto.CartDto;
import com.kodilla.ecommercee.service.mapper.CartMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CartService {

    private final CartMapper cartMapper;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public List<CartDto> getAllCarts() {
        return cartRepository.findAll().stream()
                .map(cartMapper::mapToCartDto)
                .collect(Collectors.toList());
    }

    public CartDto getCartById(Long id) {
        Cart existingCart = cartRepository.findById(id)
                .orElseThrow(() -> new CartNotFoundException("There is no Cart for id: " + id));

        return cartMapper.mapToCartDto(existingCart);
    }

    public CartDto createCart(CartDto cartDto) {
        return cartMapper.mapToCartDto(cartRepository.save(cartMapper.mapToCart(cartDto)));
    }

    public CartDto updateCart(Long id, CartDto cartDto) {
        Cart existingCart = cartRepository.findById(id)
                .orElseThrow(() -> new CartNotFoundException("There is no Cart for id: " + cartDto.getId()));

        User user = userRepository.findById(cartDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found for id: " + cartDto.getUserId()));
        existingCart.setUser(user);

        List<Product> products = cartDto.getProductsId().stream()
                .map(productId -> productRepository.findById(productId)
                        .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + productId)))
                .collect(Collectors.toList());

        existingCart.setProducts(products);

        Cart updatedCart = cartRepository.save(existingCart);
        return cartMapper.mapToCartDto(updatedCart);
    }

    public void deleteCart(Long id) {
        try {
            cartRepository.deleteById(id);
        } catch (Exception e) {
            throw new CartNotFoundException("There is no Cart for id: " + id);
        }
    }

}
