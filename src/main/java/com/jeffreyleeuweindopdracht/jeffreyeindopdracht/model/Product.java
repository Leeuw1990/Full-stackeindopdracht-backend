package com.jeffreyleeuweindopdracht.jeffreyeindopdracht.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Optional;

@Entity
public class Product {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(nullable = true, unique = false, length = 30)
    private String shopName;

    @Column(nullable = true, unique = false, length = 10)
    private int price;

    @Column(nullable = true, unique = false, length = 30)
    private String comment;

    @Column
    private String nameDB;

    @Column
    private String type;

    @Lob
    @Column
    private byte[] data;

    public Product() {
    }

//    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinColumn(name = "productListId")
//    @JsonIgnoreProperties("product")
//    private ProductList productList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productList_id")
    @JsonIgnoreProperties("product")
    private ProductList productList;


    public Product(String nameDB, String type, byte[] data) {
        this.nameDB = nameDB;
        this.type = type;
        this.data = data;
    }

    public ProductList getProductList() {
        return productList;
    }

    public void setProductList(ProductList productList) {
        this.productList = productList;
    }


    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getNameDB() {
        return nameDB;
    }

    public void setNameDB(String nameDB) {
        this.nameDB = nameDB;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Product))
            return false;
        return
                id != null &&
                        id.equals(((Product) o).getId());
    }
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
