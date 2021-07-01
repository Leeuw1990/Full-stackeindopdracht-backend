package com.jeffreyleeuweindopdracht.jeffreyeindopdracht.message;

public class ProductResponseFile {
    private String nameDB;
    private String url;
    private String type;
    private long size;

    public ProductResponseFile(String nameDB, String url, String type, long size) {
        this.nameDB = nameDB;
        this.url = url;
        this.type = type;
        this.size = size;
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
