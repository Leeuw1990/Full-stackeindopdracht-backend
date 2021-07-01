package com.jeffreyleeuweindopdracht.jeffreyeindopdracht.message;

public class ProductResponseMessage {
    private String message;

    public ProductResponseMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
