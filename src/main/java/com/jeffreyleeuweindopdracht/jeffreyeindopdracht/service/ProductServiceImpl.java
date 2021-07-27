package com.jeffreyleeuweindopdracht.jeffreyeindopdracht.service;

import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.Repository.ProductListRepository;
import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.Repository.ProductRepository;
import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.exception.RecordNotFoundException;
import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.model.Product;
import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.model.ProductList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    ProductList productList;

    @Override
    public String createProduct(Product product, String id) {
        Product newProduct = productRepository.save(product);
        productList.addProduct(newProduct);

        return newProduct.getId();
    }

    @Override
    public void updateProduct(String id, Product newProduct) {
        if (!productRepository.existsById(id)) throw new RecordNotFoundException();
        Product product = productRepository.findById(id).get();
        product.setPrice(newProduct.getPrice());
        product.setShopName(newProduct.getShopName());
        product.setComment(newProduct.getComment());
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(String id) {
        if (!productRepository.existsById(id)) throw new RecordNotFoundException();
        productRepository.deleteById(id);
    }


    @Override
    public Collection<Product> getProduct() {
        return productRepository.findAll();
    }

    @Override
    public Collection<Product> getProductById(String id) {
        return productRepository.findAllById(id);
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
