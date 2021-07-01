package com.jeffreyleeuweindopdracht.jeffreyeindopdracht.service;

import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.Repository.ProductFileDBRepository;
import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;


@Service
public class ProductFileStorageService {

    @Autowired
    private ProductFileDBRepository productFileDBRepository;

    public Product store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Product Product = new Product(fileName, file.getContentType(), file.getBytes());

        return productFileDBRepository.save(Product);
    }

    public Product getFile(String id) {
        return productFileDBRepository.findById(id).get();
    }

    public Stream<Product> getAllFiles() {
        return productFileDBRepository.findAll().stream();
    }
}
