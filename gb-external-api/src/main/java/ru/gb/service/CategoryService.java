package ru.gb.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.gb.dto.CategoryDto;

import java.util.List;

@FeignClient(url = "localhost:8080/api/v1/category", value = "CategoryService")
public interface CategoryService extends CategoryApi {

    @GetMapping(produces = "application/json;charset=UTF-8",
            consumes = "application/json;charset=UTF-8")
    List<CategoryDto> getCategoryList();

    @GetMapping(value = "/{categoryId}", produces = "application/json;charset=UTF-8",
            consumes = "application/json;charset=UTF-8")
    ResponseEntity<?> getCategory(@PathVariable("categoryId") Long id);

    @PostMapping(produces = "application/json;charset=UTF-8",
            consumes = "application/json;charset=UTF-8")
    ResponseEntity<?> handlePost(@Validated @RequestBody CategoryDto categoryDto);

    @PutMapping(value = "/{categoryId}", produces = "application/json;charset=UTF-8",
            consumes = "application/json;charset=UTF-8")
    ResponseEntity<?> handleUpdate(@PathVariable("categoryId") Long id,
                                   @Validated @RequestBody CategoryDto categoryDto);

    @DeleteMapping(value = "/{categoryId}", produces = "application/json;charset=UTF-8",
            consumes = "application/json;charset=UTF-8")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteById(@PathVariable("categoryId") Long id);
}
