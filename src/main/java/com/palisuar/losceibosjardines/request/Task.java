package com.palisuar.losceibosjardines.request;

public class Task {
    private String quantity;
    private String description;
    private double price;
    private String typeCurrency;
    private String pricePerUnity;

    // Getters y setters

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPricePerUnity() {
        return pricePerUnity;
    }

    public void setPricePerUnity(String pricePerUnity) {
        this.pricePerUnity = pricePerUnity;
    }

    public String getTypeCurrency() {
        return typeCurrency;
    }

    public void setTypeCurrency(String typeCurrency) {
        this.typeCurrency = typeCurrency;
    }
}