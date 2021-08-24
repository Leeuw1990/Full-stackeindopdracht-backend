package com.jeffreyleeuweindopdracht.jeffreyeindopdracht.message;

public class ProductResponseFile {
    private int rating;
    private String shopName;
    private float price;
    private String comment;
    private String nameDB;
    private String url;
    private String type;
    private long size;


    public ProductResponseFile(int rating, String shopName, float price, String comment,String nameDB, String url, String type, long size) {
        this.rating = rating;
        this.shopName = shopName;
        this.price = price;
        this.comment = comment;
        this.nameDB = nameDB;
        this.url = url;
        this.type = type;
        this.size = size;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
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

    public String getNameDB() {
        return nameDB;
    }

    public void setNameDB(String nameDB) {
        this.nameDB = nameDB;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

}
