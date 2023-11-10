package com.thiennvph2351.ph22351_crud_product.DTO;

public class ProductDTO {
    int id;
    String name2;
    int price;
    int id_cat;
    String name;

    public ProductDTO() {
    }

    public ProductDTO(String name2, int price, int id_cat) {
        this.name2 = name2;
        this.price = price;
        this.id_cat = id_cat;
    }
    public ProductDTO(int id, String name2, int price, int id_cat, String name) {
        this.id = id;
        this.name2 = name2;
        this.price = price;
        this.id_cat = id_cat;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId_cat() {
        return id_cat;
    }

    public void setId_cat(int id_cat) {
        this.id_cat = id_cat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
