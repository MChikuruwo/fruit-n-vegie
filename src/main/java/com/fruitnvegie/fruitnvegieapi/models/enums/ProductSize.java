package com.fruitnvegie.fruitnvegieapi.models.enums;

public enum ProductSize {
    SMALL("SMALL"),
    MEDIUM("MEDIUM"),
    LARGE("LARGE");


    public final String label;

    private ProductSize(String label){
        this.label = label;
    }
}