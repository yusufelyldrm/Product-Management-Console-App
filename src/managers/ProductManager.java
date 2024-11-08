package managers;

import models.Product;
import utils.GetInput;

import java.util.ArrayList;
import java.util.List;

public class ProductManager {
    private List<Product> products = new ArrayList<>();
    private List<Product> cart = new ArrayList<>();

    public enum SortType {
        ASCENDING,
        DESCENDING
    }

    public enum InputType {
        INT,
        STRING,
        LETTER,
        FLOAT
    }

    public enum SortBy {
        RATING,
        STOCK,
        NAME,
        PRICE,
    }

    public void addProducts() {
        int productAmount = Integer.parseInt(GetInput.getInput("Kaç farklı ürün gireceksiniz ? ", InputType.INT,2,null));

        for(int i = 0; i < productAmount; i++) {
            System.out.println("Ürün bilgilerini giriniz: ");
            String name = GetInput.getInput("Ürün adı: ", InputType.STRING,20,null);
            float price = Float.parseFloat(GetInput.getInput("Ürün fiyatı: ", InputType.FLOAT,1,100));
            int stock = Integer.parseInt(GetInput.getInput("Ürün stok adedi: ", InputType.INT,1,null));
            float rating = Float.parseFloat(GetInput.getInput("Ürün puanı: ", InputType.FLOAT,0,5));

            products.add(new Product(name, price, stock, rating));
        }
    }

    public void sortProducts() {}

    private void displayProducts() {}

    public void addToCart() {}

    private void calculateCartTotal() {}

    private void findProductByName() {}
}
