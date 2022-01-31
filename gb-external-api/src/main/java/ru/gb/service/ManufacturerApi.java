package ru.gb.service;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface ManufacturerApi {

    List<ManufacturerDto> getManufacturerList();

    ResponseEntity<?> getManufacturer(@PathVariable("manufacturerId") Long id);

    ResponseEntity<?> handlePost(@Validated @RequestBody ManufacturerDto manufacturerDto);

    ResponseEntity<?> handleUpdate(@PathVariable("manufacturerId") Long id,
                                   @Validated @RequestBody ManufacturerDto manufacturerDto);

    void deleteById(@PathVariable("manufacturerId") Long id);
}
