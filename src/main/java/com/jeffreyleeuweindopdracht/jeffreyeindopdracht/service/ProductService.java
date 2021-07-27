package com.jeffreyleeuweindopdracht.jeffreyeindopdracht.service;

import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.model.Product;
import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.model.ProductList;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Stream;

public interface ProductService {
    public abstract String createProduct(Product product, String id);
    public abstract void updateProduct(String id, Product product);
    public abstract void deleteProduct(String id);
    public abstract Collection<Product> getProduct();
//    public abstract Optional<Product> getProductById(String id);
    public abstract Collection<Product> getProductById(String id);
    public abstract Product store (MultipartFile file) throws IOException;
    public abstract Product getFile(String id);
    public abstract Stream<Product> getAllFiles();
}
