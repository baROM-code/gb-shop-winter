package ru.gb.service;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.gb.dto.ProductDto;

import java.util.List;

public interface ProductApi {

    List<ProductDto> getProductList();

    List<ProductManufacturerDto> getInfoProductList();

    ResponseEntity<?> getProduct(@PathVariable("productId") Long id);

    ResponseEntity<?> handlePost(@Validated @RequestBody ProductDto productDto);

    ResponseEntity<?> handleUpdate(@PathVariable("productId") Long id, @Validated @RequestBody ProductDto productDto);

    void deleteById(@PathVariable("productId") Long id);
}
