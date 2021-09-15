package com.jeffreyleeuweindopdracht.jeffreyeindopdracht.service;

import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.model.Product;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.data.domain.Pageable;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

public interface ProductService {
    public abstract void deleteProduct(String id);
    public abstract void updateProduct(String id, Product product);
    public abstract Optional<Product> store (MultipartFile file, Long product_list_id) throws IOException;
    public abstract Product getFile(String id);
    public abstract Stream<Product> getAllFiles(long product_list_id, Pageable pageable);
}
