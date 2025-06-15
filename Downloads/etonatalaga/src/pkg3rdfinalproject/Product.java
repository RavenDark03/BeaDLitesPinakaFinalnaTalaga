/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg3rdfinalproject;

/**
 *
 * @author Lynch
 */
public class Product {
    private String name;
    private String[] sizes;
    private String[] toppings;

    public Product(String name, String[] sizes, String[] toppings) {
        this.name = name;
        this.sizes = sizes;
        this.toppings = toppings;
    }

    public String getName() { 
        return name; }
    public String[] getSizes() { 
        return sizes; }
    public String[] getToppings() { 
        return toppings; }
}
