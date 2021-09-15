package com.jeffreyleeuweindopdracht.jeffreyeindopdracht.service;

import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.Repository.ProductListRepository;
import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.Repository.ProductRepository;
import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.Repository.UserRepository;
import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.exception.RecordNotFoundException;
import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.data.domain.Pageable;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;


@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    private ProductListRepository productListRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    public void setProductRepository (ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void deleteProduct(String id) {
        Optional <Product> product = productRepository.findById(id);
        if(!productRepository.existsById(id)) throw new RecordNotFoundException();
        product.get().getId();
        productRepository.deleteById(id);
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
    public Optional<Product> store(MultipartFile file, Long product_list_id) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Product product = new Product(fileName, file.getContentType(), file.getBytes());

        return productListRepository.findById(product_list_id).map(productList -> {
           product.setProductList(productList);
            return productRepository.save(product);
        });



    }

    @Override
    public Product getFile(String id) {
        return productRepository.findById(id).get();
    }

    @Override
    public Stream<Product> getAllFiles(long product_list_id, Pageable pageable) {
        return productRepository.findByProductListId(product_list_id, pageable).stream();
    }

}
