package com.jeffreyleeuweindopdracht.jeffreyeindopdracht.controller;

import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.model.ProductList;
import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.service.ProductListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/api/productlist")
public class ProductListController {

    @Autowired
    private ProductListService productListService;



    @PostMapping(value = "")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> createProductList(@RequestBody ProductList productList) {
        long newId = productListService.createProductList(productList);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}")
                .buildAndExpand(newId).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> deleteProductList(@PathVariable("id") long id) {
        productListService.deleteProductList(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping(value = "")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> getProductList(@RequestParam(required = false) String listName) {
        if (listName != null && !listName.isEmpty()) {
            return ResponseEntity.ok().body(productListService.getProductListByListName(listName));
        }
        else {
            return ResponseEntity.ok().body(productListService.getProductList());
        }
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Optional<ProductList>> getProductListById(@PathVariable("id") long id) {
        return ResponseEntity.ok().body(productListService.getProductListById(id));
    }
}
