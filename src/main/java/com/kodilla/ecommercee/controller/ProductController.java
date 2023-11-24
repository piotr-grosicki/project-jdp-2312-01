package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.model.repository.ProductRepository;
import com.kodilla.ecommercee.service.ProductServiceImpl;
import com.kodilla.ecommercee.service.dto.ProductDto;
import com.kodilla.ecommercee.service.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductServiceImpl productService;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;


//    @GetMapping()
//    public ResponseEntity<List<ProductDto>> getAllProducts() {
//        List<Product> products = productService.getAllProducts();
//        return ResponseEntity.ok(productMapper.mapToProductDtoList(products));
//    }

    @GetMapping()
    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(productMapper::mapToProductDto)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "{productId}")
    public ProductDto getProduct(@PathVariable Long productId) {
        return new ProductDto(1L, "in this method getProduct returns always the same value no matter the @PathVariable param");
    }

//    @DeleteMapping(value = "{productId}")
//    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
//        return ResponseEntity.ok().build();
//    }

    @DeleteMapping(value = "{productId}")
    public void deleteProduct(@PathVariable Long productId) {
        productRepository.deleteById(productId);
    }

//    @PutMapping()
//    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto) {
//        return ResponseEntity.ok(new ProductDto(1L, "Edited test_product"));
//    }

    @PutMapping()
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        productDto = new ProductDto(2L, "updated product no matter the json body");
        productRepository.save(productMapper.mapToProduct(productDto));
        return productDto;
    }

//    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Void> createProduct(@RequestBody ProductDto productDto) {
//        productService.saveProduct(productMapper.mapToProduct(productDto));
//        return ResponseEntity.ok().build();
//    }

    @PostMapping
    public ProductDto createProduct(ProductDto productDto) {
        return new ProductDto();
    }
}
