package com.jeffreyleeuweindopdracht.jeffreyeindopdracht.service;

import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.Repository.ProductRepository;
import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.exception.RecordNotFoundException;
import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;


@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository (ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void updateProduct(String id, Product newProduct) {
        if (!productRepository.existsById(id)) throw new RecordNotFoundException();
        Product product = productRepository.findById(id).get();
        product.setRating(newProduct.getRating());
        product.setPrice(newProduct.getPrice());
        product.setShopName(newProduct.getShopName());
        product.setComment(newProduct.getComment());
        productRepository.save(product);
    }

    @Override
    public Product store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Product Product = new Product(fileName, file.getContentType(), file.getBytes());

        return productRepository.save(Product);
    }

    @Override
    public Product getFile(String id) {
        return productRepository.findById(id).get();
    }

    @Override
    public Stream<Product> getAllFiles() {
        return productRepository.findAll().stream();
    }

}
