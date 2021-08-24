package com.jeffreyleeuweindopdracht.jeffreyeindopdracht.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;



@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column
    private int rating;

    @Column(nullable = true, unique = false, length = 30)
    private String shopName;

    @Column(nullable = true, unique = false, length = 10)
    private float price;

    @Column(nullable = true, unique = false, length = 255)
    private String comment;

    @Column
    private String nameDB;

    @Column
    private String type;

    @Lob
    @Column
    private byte[] data;

    //Many-to-one bi-directional relation between product to productList
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "product_list_id")
    private ProductList productList;


    public Product(String nameDB, String type, byte[] data) {
        this.nameDB = nameDB;
        this.type = type;
        this.data = data;
    }

    public Product(String id) {
        this.id = id;
    }

    public Product() {
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

}
