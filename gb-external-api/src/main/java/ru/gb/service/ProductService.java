package ru.gb.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.gb.dto.ProductDto;

import java.util.List;

@FeignClient(url = "localhost:8080/api/v1/product", value = "ProductService")
public interface ProductService extends ProductApi {

    @GetMapping(produces = "application/json;charset=UTF-8",
            consumes = "application/json;charset=UTF-8")
    List<ProductDto> getProductList();

    @GetMapping(value = "/info", produces = "application/json;charset=UTF-8",
            consumes = "application/json;charset=UTF-8")
    List<ProductManufacturerDto> getInfoProductList();

    @GetMapping(value = "/{productId}", produces = "application/json;charset=UTF-8",
            consumes = "application/json;charset=UTF-8")
    ResponseEntity<?> getProduct(@PathVariable("productId") Long id);

    @PostMapping(produces = "application/json;charset=UTF-8",
            consumes = "application/json;charset=UTF-8")
    ResponseEntity<?> handlePost(@Validated @RequestBody ProductDto productDto);

    @PutMapping(value = "/{productId}", produces = "application/json;charset=UTF-8",
            consumes = "application/json;charset=UTF-8")
    ResponseEntity<?> handleUpdate(@PathVariable("productId") Long id, @Validated @RequestBody ProductDto productDto);

    @DeleteMapping(value = "/{productId}", produces = "application/json;charset=UTF-8",
            consumes = "application/json;charset=UTF-8")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteById(@PathVariable("productId") Long id);
}
