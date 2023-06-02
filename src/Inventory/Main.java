package Inventory;
import java.util.Scanner;


public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static InventoryList inventoryList = new InventoryList();

    public static void main(String[] args) {
        boolean quit = false;
        int choice = 0;
        printInstructions();
        inventoryList.loadFromFile();

        while (!quit) {
            System.out.println("What do you want to do next? Enter your choice below: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 0:
                    printInstructions();
                    break;
                case 1:
                    inventoryList.printInventoryList();
                    break;
                case 2:
                    addItem();
                    break;
                case 3:
                    modifyItem();
                    break;
                case 4:
                    removeItem();
                    break;
                case 5:
                    searchForItem();
                    break;
                case 6:
                    quit = true;
                    break;
            }
        }
        inventoryList.saveToFile();
    }

    public static void printInstructions() {
        System.out.println("\nPress");
        System.out.println("0 - to print your choices");
        System.out.println("1 - to print the list of all added items");
        System.out.println("2 - add an item to the list");
        System.out.println("3 - to modify an item in the list");
        System.out.println("4 - to remove an item from the list");
        System.out.println("5 - to search for an item from the list");
        System.out.println("6 - Quit Application");
    }

    public static void addItem() {
        System.out.println("Please enter the item name: ");
        String itemName = scanner.nextLine();

        System.out.println("Please enter the item name: ");
        String name = scanner.nextLine();

        System.out.println("Please enter the item quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Please enter the item price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Please enter the item category: ");
        String category = scanner.nextLine();

        inventoryList.addInventoryItem(name, quantity, price, category);


        System.out.println("Item added successfully with barcode: " + inventoryList.getBarcodeForItem(itemName));
    }

    public static void modifyItem() {
        System.out.println("Enter the item number: ");
        int itemNo = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter the replacement item: ");
        String newItem = scanner.nextLine();
        inventoryList.modifyInventoryItem(scanner, itemNo - 1, newItem);
    }

    public static void removeItem() {
        System.out.println("Please enter the item number: ");
        int itemNo = scanner.nextInt();
        scanner.nextLine();
        inventoryList.removeInventoryItem(itemNo - 1);
    }

    public static void searchForItem() {
        System.out.println("What are you searching for? ");
        String searchItem = scanner.nextLine();
        String foundItem = inventoryList.findItem(searchItem);
        if (foundItem != null) {
            System.out.println("Found " + searchItem + " in the Inventory List");
        } else {
            System.out.println("The item you are looking for doesn't exist.");
        }
    }
}
