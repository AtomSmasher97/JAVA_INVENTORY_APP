package Inventory;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class InventoryItem {
    private String name;
    private int quantity;
    private double price;
    private String category;

    public InventoryItem(String name, int quantity, double price, String category) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }
}