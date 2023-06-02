package Inventory;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class InventoryList {
    private ArrayList<InventoryItem> inventoryList = new ArrayList<>();
    private Map<InventoryItem, String> itemBarcodes = new HashMap<>();
    private static final String FILE_NAME = "inventory.txt";

    public void addInventoryItem(String name, int quantity, double price, String category) {
        InventoryItem item = new InventoryItem(name, quantity, price, category);
        String barcode = generateBarcode();
        inventoryList.add(item);
        itemBarcodes.put(item, barcode);
    }

    private String generateBarcode() {
        String barcode = UUID.randomUUID().toString();
        return barcode.substring(0, 8); // Use a substring of the generated UUID as the barcode
    }

    public String getBarcodeForItem(InventoryItem item) {
        return itemBarcodes.get(item);
    }

    public void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (InventoryItem item : inventoryList) {
                writer.println(item.getName() + "," + item.getQuantity() + "," + item.getPrice() + "," + item.getCategory());
            }
            System.out.println("Inventory list has been saved to file.");
        } catch (IOException e) {
            System.out.println("Error occurred while saving the inventory list to file: " + e.getMessage());
        }
    }

    public void loadFromFile() {
        inventoryList.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] itemData = line.split(","); // Assuming the file format is CSV (comma-separated values)
                if (itemData.length == 4) {
                    String name = itemData[0].trim();
                    int quantity = Integer.parseInt(itemData[1].trim());
                    double price = Double.parseDouble(itemData[2].trim());
                    String category = itemData[3].trim();

                    InventoryItem item = new InventoryItem(name, quantity, price, category);
                    inventoryList.add(item);
                }
            }
            System.out.println("Inventory list has been loaded from file.");
        } catch (FileNotFoundException e) {
            System.out.println("No saved inventory list found.");
        } catch (IOException e) {
            System.out.println("Error occurred while loading the inventory list from file: " + e.getMessage());
        }
    }


    public void printInventoryList() {
        System.out.println("You have " + inventoryList.size() + " items in your list");
        for (int i = 0; i < inventoryList.size(); i++) {
            InventoryItem item = inventoryList.get(i);
            String barcode = itemBarcodes.get(item);
            System.out.println((i + 1) + ". " + item.getName());
            System.out.println("   Barcode: " + barcode);
            System.out.println("Item name: " + item.getName());
            System.out.println("Item qty: " + item.getQuantity());
            System.out.println("Item price: " + item.getPrice());
            System.out.println("Item category: " + item.getCategory());
            System.out.println();
        }
    }

    public void modifyInventoryItem(int position, InventoryItem newItem) {
        if (position >= 0 && position < inventoryList.size()) {
            inventoryList.set(position, newItem);
            System.out.println("Inventory item " + (position + 1) + " has been modified");
        } else {
            System.out.println("Invalid item position. The item you are trying to modify doesn't exist.");
        }
    }

    public void removeInventoryItem(int position) {
        if (position >= 0 && position < inventoryList.size()) {
            InventoryItem theItem = inventoryList.get(position);
            inventoryList.remove(position);
            System.out.println("Inventory item " + (position + 1) + " has been removed");
        } else {
            System.out.println("Invalid item position. The item you are trying to remove doesn't exist.");
        }
    }

    public InventoryItem findItem(String searchItem) {
        for (InventoryItem item : inventoryList) {
            if (item.getName().equals(searchItem)) {
                return item;
            }
        }
        return null;
    }
}