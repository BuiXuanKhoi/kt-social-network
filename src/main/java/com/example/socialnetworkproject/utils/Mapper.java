package com.example.socialnetworkproject.utils;

public class Mapper {

    private Object source;
    private Class<?> target;


    public void from(Object source){
        this.source = source;
    }

    public void to(Class<?> target){
        this.target = target;
    }


}
