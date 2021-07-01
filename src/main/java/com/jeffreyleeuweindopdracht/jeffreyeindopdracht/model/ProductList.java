package com.jeffreyleeuweindopdracht.jeffreyeindopdracht.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class ProductList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true, length = 25)
    private String listName;

    @OneToMany(mappedBy = "productList",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products = new ArrayList<>();



//    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(name = "user_productList",
//        joinColumns = {@JoinColumn(name = "product_list_id")},
//        inverseJoinColumns = {@JoinColumn(name = "user_id")})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    @JsonIgnoreProperties("productLists")
    private Users users;

    public Users getUsers() {
        return users;
    }

    public void addProduct(Product product) {
        products.add(product);
        product.setProductList(this);
    }

    public void removeProduct(Product product) {
        products.remove(product);
        product.setProductList(null);
    }

    public void setUser(Users users) {
        this.users = users;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof ProductList))
            return false;
        return
                id > 0 &&
                id ==(((ProductList) o).getId());
    }
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
