package com.kodilla.ecommercee.service.mapper;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.service.dto.ProductDto;

public class ProductMapper {

    public static ProductDto mapToProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setProductName(product.getProductName());
        productDto.setPrice(product.getPrice() != null ? product.getPrice().doubleValue() : null);
        productDto.setGroupId(product.getGroup() != null ? product.getGroup().getId() : null);

        return productDto;
    }

    public static Product mapToProduct(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setProductName(productDto.getProductName());
        product.setPrice(productDto.getPrice() != null ? Double.valueOf(productDto.getPrice()) : null);

        return product;
    }
}
