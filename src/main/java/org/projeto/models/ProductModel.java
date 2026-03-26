package org.projeto.models;

import lombok.Builder;

@Builder
public class ProductModel {
    private int id;
    private String productName;
    private double price;
    private ProductType type;

    @Override
    public String toString() {
        return "ProductModel{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", type=" + type +
                '}';
    }
}
