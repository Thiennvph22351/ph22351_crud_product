package com.thiennvph2351.ph22351_crud_product.DTO;

public class CatDTO {
    int id;
    String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CatDTO() {
    }

    public CatDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public CatDTO(String name) {
        this.name = name;
    }
}
