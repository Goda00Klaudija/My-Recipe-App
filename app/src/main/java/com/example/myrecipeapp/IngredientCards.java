package com.example.myrecipeapp;

public class IngredientCards {

    //Model Class
    private long ing_id;
    private String name;

    //Constructor
    public IngredientCards(long ing_id, String name) {
        this.ing_id = ing_id;
        this.name = name;
    }

    public long getIng_Id() {
        return ing_id;
    }

    public String getName() {
        return name;
    }
}
