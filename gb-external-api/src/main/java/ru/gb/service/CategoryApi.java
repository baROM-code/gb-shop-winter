package ru.gb.service;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.gb.dto.CategoryDto;

import java.util.List;

public interface CategoryApi {

    List<CategoryDto> getCategoryList();

    ResponseEntity<?> getCategory(@PathVariable("categoryId") Long id);

    ResponseEntity<?> handlePost(@Validated @RequestBody CategoryDto categoryDto);

    ResponseEntity<?> handleUpdate(@PathVariable("categoryId") Long id,
                                   @Validated @RequestBody CategoryDto categoryDto);

    void deleteById(@PathVariable("categoryId") Long id);
}
