package com.jeffreyleeuweindopdracht.jeffreyeindopdracht.service;

import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.model.ProductList;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

public interface ProductListService {
    public abstract long createProductList(ProductList productList);
    public abstract void deleteProductList(long id);
    public abstract Collection<ProductList> getProductList();
    public abstract Collection<ProductList> getProductListByListName(String listName);
    public abstract Optional<ProductList> getProductListById(long id);
}
