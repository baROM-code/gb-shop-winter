package ru.gb.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.cloud.openfeign.FeignClient;
import ru.gb.dto.*;

import java.util.List;

@FeignClient(url = "localhost:8080/api/v1/manufacturer", value = "ManufacturerService")
public interface ManufacturerService extends ManufacturerApi {

    @GetMapping(produces = "application/json;charset=UTF-8",
            consumes = "application/json;charset=UTF-8")
    List<ManufacturerDto> getManufacturerList();

    @GetMapping(value = "/{manufacturerId}", produces = "application/json;charset=UTF-8",
            consumes = "application/json;charset=UTF-8")
    ResponseEntity<?> getManufacturer(@PathVariable("manufacturerId") Long id);

    @PostMapping(produces = "application/json;charset=UTF-8",
            consumes = "application/json;charset=UTF-8")
    ResponseEntity<?> handlePost(@Validated @RequestBody ManufacturerDto manufacturerDto);

    @PutMapping(value = "/{manufacturerId}", produces = "application/json;charset=UTF-8",
            consumes = "application/json;charset=UTF-8")
    ResponseEntity<?> handleUpdate(@PathVariable("manufacturerId") Long id,
                                   @Validated @RequestBody ManufacturerDto manufacturerDto);

    @DeleteMapping(value = "/{manufacturerId}", produces = "application/json;charset=UTF-8",
            consumes = "application/json;charset=UTF-8")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteById(@PathVariable("manufacturerId") Long id);
}