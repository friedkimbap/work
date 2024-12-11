package org.example;

import java.util.ArrayList;
import java.util.List;

public abstract class Pizza {
    String name;
    String dough;
    String sauce;
    List<String> toppings = new ArrayList<>();
    public void prepare() {
        System.out.println("Preparing pizza");
        for (String topping : toppings){
            System.out.println(" "+topping);
        }
    }

    public void bake() {
        System.out.println("Baking pizza");
    }

    public void cut() {
        System.out.println("Cutting pizza");
    }

    public void box() {
        System.out.println("Boxing pizza");
    }

    public String getName(){
        return name;
    }


}
