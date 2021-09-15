package com.jeffreyleeuweindopdracht.jeffreyeindopdracht.controller;

import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.message.ProductResponseFile;
import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.message.ProductResponseMessage;
import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.model.Product;
import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.service.ProductListService;
import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/api/productlist")
public class ProductController {

    private ProductService productService;

    private ProductListService productListService;

    @Autowired
    public void setProductService (ProductService productService) {
        this.productService = productService;
    }

    @DeleteMapping(value = "/files/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable("id") String id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/files/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable("id") String id, @RequestBody Product product) {
        productService.updateProduct(id, product);
        return ResponseEntity.status(HttpStatus.OK).body(new ProductResponseMessage("Updated"));
    }

    @PostMapping("{product_list_id}/upload")
    public ResponseEntity<ProductResponseMessage> uploadFile(@PathVariable (value = "product_list_id") @RequestParam("file") MultipartFile file, long product_list_id) {

        String message = "";
        try {
            productService.store(file, product_list_id );
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ProductResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ProductResponseMessage(message));
        }
    }

    @GetMapping("{product_list_id}/files")
    public ResponseEntity<List<ProductResponseFile>> getAllFiles(@PathVariable (value = "product_list_id") Long product_list_id, Pageable pageable) {
        List<ProductResponseFile> files = productService.getAllFiles(product_list_id, pageable).map(product -> {
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

