package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.service.dto.ProductDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/v1/products")
public class ProductController {

    @GetMapping()
    public List<ProductDto> getProducts() {
        return new ArrayList<>(Arrays.asList(
                new ProductDto(1L, "Dummy Product One"),
                new ProductDto(2L, "Dummy Product Two"),
                new ProductDto(3L, "Dummy Product Three")
        ));
    }

    @GetMapping("/{productId}")
    public ProductDto getProduct(@PathVariable("productId") Long productId) {
        return new ProductDto(1L, "Dummy Product");
    }

    @PostMapping("/create")
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        return new ProductDto(1L, "Newly Created Dummy Product");
    }

    @PutMapping("/update")
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        return new ProductDto(1L, "Updated Dummy Product");
    }

    @DeleteMapping("/delete/{productId}")
    public void deleteProduct(@PathVariable("productId") Long productId) {

    }
}
