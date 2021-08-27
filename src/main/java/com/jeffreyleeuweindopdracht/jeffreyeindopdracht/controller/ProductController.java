package com.jeffreyleeuweindopdracht.jeffreyeindopdracht.controller;

import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.message.ProductResponseFile;
import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.message.ProductResponseMessage;
import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.model.Product;
import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/api/product")
public class ProductController {

    private ProductService productService;

    @Autowired
    public void setProductService (ProductService productService) {
        this.productService = productService;
    }

    @PatchMapping(value = "files/{id}")
    public ResponseEntity<Object> updateProductList(@PathVariable("id") String id, @RequestBody Product product) {
        productService.updateProduct(id, product);
        return ResponseEntity.status(HttpStatus.OK).body(new ProductResponseMessage("Updated"));
    }

    @PostMapping("/upload")
    public ResponseEntity<ProductResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {

        String message = "";
        try {
            productService.store(file);
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ProductResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ProductResponseMessage(message));
        }
    }

    @GetMapping("/files")
    public ResponseEntity<List<ProductResponseFile>> getListFiles() {
        List<ProductResponseFile> files = productService.getAllFiles().map(product -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/api/product/files/")
                    .path(product.getId())
                    .toUriString();

            return new ProductResponseFile(
                    product.getRating(),
                    product.getShopName(),
                    product.getPrice(),
                    product.getComment(),
                    product.getNameDB(),
                    fileDownloadUri,
                    product.getType(),
                    product.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
        Product product = productService.getFile(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + product.getNameDB() + "\"")
                .body(product.getData());
    }
}

