import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.dto.ProductDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ShopController {

    private final ProductService productService;

    @GetMapping()
    public List<ProductDto> getProducts() {
        return productService.getProductList();
    }
}