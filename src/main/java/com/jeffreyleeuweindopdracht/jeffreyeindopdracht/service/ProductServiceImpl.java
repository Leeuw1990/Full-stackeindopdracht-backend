package com.jeffreyleeuweindopdracht.jeffreyeindopdracht.service;

import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.Repository.ProductListRepository;
import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.Repository.ProductRepository;
import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.Repository.UserRepository;
import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.exception.RecordNotFoundException;
import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.model.Product;
import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.model.ProductList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    ProductListRepository productListRepository;


    @Override
    public String createProduct(Product product, long id) {
        product.setProductList(productListRepository.getById(id));
        Product newProduct = productRepository.save(product);
        return newProduct.getId();
    }

//    @Override
//    public long createProductList(ProductList productList) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String currentPrincipalName = authentication.getName();
//        productList.setUser(userRepository.findByUsername(currentPrincipalName).orElse(null));
//        ProductList newProductList = productListRepository.save(productList);
//        return newProductList.getId();
//    }




    @Override
    public void updateProduct(long id, Product newProduct) {
        if (!productRepository.existsById(id)) throw new RecordNotFoundException();
        Product product = productRepository.findById(id).get();
        product.setPrice(newProduct.getPrice());
        product.setShopName(newProduct.getShopName());
        product.setComment(newProduct.getComment());
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(long id) {
        if (!productRepository.existsById(id)) throw new RecordNotFoundException();
        productRepository.deleteById(id);
    }

    @Override
    public Optional<Product> getProductById(long id) {
        if (!productRepository.existsById(id)) throw new RecordNotFoundException();
        return productRepository.findById(id);
    }

    @Override
    public Collection<Product> getProduct() {
        return productRepository.findAll();
    }

    @Override
    public Collection<Product> getProductByShopName(String shopName) {
        return productRepository.findAllByShopName(shopName);
    }

}
