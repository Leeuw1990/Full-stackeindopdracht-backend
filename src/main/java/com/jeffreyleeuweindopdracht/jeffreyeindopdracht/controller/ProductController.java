package com.jeffreyleeuweindopdracht.jeffreyeindopdracht.controller;

import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.Repository.ProductRepository;
import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.message.ProductResponseFile;
import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.message.ProductResponseMessage;
import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.model.Product;
import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.service.ProductFileStorageService;
import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductFileStorageService storageService;

//    @ResponseBody
//    @RequestMapping(value = "")
//    public List<Product> getProductDetails() {
//        List<Product> productResponse = (List<Product>) productRepository.findAll();
//
//        return productResponse;
//    }

    @PostMapping(value = "")
    public ResponseEntity<Object> createProduct(long id, @RequestBody Product product) {
        String newId = productService.createProduct(product, id);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}")
                .buildAndExpand(newId).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateProductList(@PathVariable("id") long id, @RequestBody Product product) {
        productService.updateProduct(id, product);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable("id") long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Product>> getProduct(@PathVariable("id") long id) {
        return ResponseEntity.ok().body(productService.getProductById(id));
    }

    @GetMapping(value = "")
    public ResponseEntity<Object> getProduct(@RequestParam(required = false) String shopName) {
        if (shopName != null && !shopName.isEmpty()) {
            return ResponseEntity.ok().body(productService.getProductByShopName(shopName));
        }
        else {
            return ResponseEntity.ok().body(productService.getProduct());
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<ProductResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            storageService.store(file);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ProductResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ProductResponseMessage(message));
        }
    }

    @GetMapping("/files")
    public ResponseEntity<List<ProductResponseFile>> getListFiles() {
        List<ProductResponseFile> files = storageService.getAllFiles().map(product -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/api/product/files/")
                    .path(product.getId())
                    .toUriString();

            return new ProductResponseFile(
                    product.getNameDB(),
                    fileDownloadUri,
                    product.getType(),
                    product.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
        Product product = storageService.getFile(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + product.getNameDB() + "\"")
                .body(product.getData());
    }
}

