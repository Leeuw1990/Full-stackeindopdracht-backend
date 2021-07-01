package com.jeffreyleeuweindopdracht.jeffreyeindopdracht.service;

import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.model.Product;
import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.model.ProductList;

import java.util.Collection;
import java.util.Optional;

public interface ProductService {
    public abstract String createProduct(Product product, long id);
    public abstract void updateProduct(long id, Product product);
    public abstract void deleteProduct(long id);
    public abstract Collection<Product> getProduct();
    public abstract Optional<Product> getProductById(long id);
    public abstract Collection<Product> getProductByShopName(String shopName);
}
