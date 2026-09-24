package com.rohit.springboot_learning.dto;

public class ProductResponse {
    private Long id;

    private String name;
    private Double price;
    private Long categoryId;
    private String categoryName;

    public ProductResponse(Long id, String name, Double price, Long categoryId, String categoryName) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.categoryId = categoryId;
        this.categoryName = categoryName;

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }
    public Long getCategoryId(){
        return categoryId;
    }

    public String getCategoryName(){
        return categoryName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setCategoryId(Long categoryId){
        this.categoryId = categoryId;
    }
    public void setCategoryName(String categoryName){
        this.categoryName = categoryName;
    }
}