package com.works.restcontrollers;

import com.works.models.Product;
import com.works.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BasketRestController {

    final ProductService productService;

    @PostMapping("/sendBasket")
    public ResponseEntity sendBasket(@RequestBody Product product) {
        return productService.sendBasket(product);
    }

}
