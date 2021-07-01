package com.jeffreyleeuweindopdracht.jeffreyeindopdracht.service;

import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.Repository.ProductListRepository;
import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.Repository.UserRepository;
import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.exception.NotAuthorizedException;
import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.exception.RecordNotFoundException;
import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.model.ProductList;
import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.model.Users;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ProductListServiceImpl implements ProductListService {

    @Autowired
    private ProductListRepository productListRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public long createProductList(ProductList productList) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        productList.setUser(userRepository.findByUsername(currentPrincipalName).orElse(null));
        ProductList newProductList = productListRepository.save(productList);
        return newProductList.getId();
    }

    @Override
    public void deleteProductList(long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Users users = (userRepository.findByUsername(currentPrincipalName).orElse(null));
        Optional <ProductList> productList = productListRepository.findById(id);
        if (!productListRepository.existsById(id)) throw new RecordNotFoundException();
        if (productList.get().getUsers().getId() != users.getId()) throw new NotAuthorizedException();
        productListRepository.deleteById(id);
    }

    @Override
    public Optional<ProductList> getProductListById(long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Users users = (userRepository.findByUsername(currentPrincipalName).orElse(null));
        Optional <ProductList> productList = productListRepository.findById(id);
        if (!productListRepository.existsById(id)) throw new RecordNotFoundException();
        if (productList.get().getUsers().getId() != users.getId()) throw new NotAuthorizedException();
        return productList;
    }



    @Override
    public Collection<ProductList> getProductList() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Users users = (userRepository.findByUsername(currentPrincipalName).orElse(null));
        Collection<ProductList> productLists = productListRepository.findAllByUsers(users);
        return productLists;
    }


    @Override
    public Collection<ProductList> getProductListByListName(String listName) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Users users = (userRepository.findByUsername(currentPrincipalName).orElse(null));
        Collection<ProductList> productLists = productListRepository.findAllByUsersAndListName(users, listName);
        return productLists;
    }
}
