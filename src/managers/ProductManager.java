package managers;

import models.Product;
import utils.GetInput;

import java.util.*;

public class ProductManager {
    private List<Product> products = new ArrayList<>();
    private HashMap<Product, Integer> cart = new HashMap<>();

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
        int productAmount = Integer.parseInt(GetInput.getInput("Kaç farklı ürün gireceksiniz ? ", InputType.INT, 2, null));

        for (int i = 0; i < productAmount; i++) {
            System.out.println("Ürün bilgilerini giriniz: ");
            String name = GetInput.getInput("Ürün adı: ", InputType.STRING, 20, null);
            float price = Float.parseFloat(GetInput.getInput("Ürün fiyatı: ", InputType.FLOAT, 1, 100));
            int stock = Integer.parseInt(GetInput.getInput("Ürün stok adedi: ", InputType.INT, 1, null));
            float rating = Float.parseFloat(GetInput.getInput("Ürün puanı: ", InputType.FLOAT, 0, 5));

            products.add(new Product(name, price, stock, rating));
        }
    }

    public void sortProducts() {
        SortBy sortBy = getSortByFromUser();
        SortType sortType = getSortTypeFromUser();

        if (sortBy == null || sortType == null) {
            System.out.println("Geçersiz sıralama kriteri veya türü girdiniz.");
            return;
        }

        Comparator<Product> comparator = null;

        switch (sortBy) {
            case NAME:
                comparator = Comparator.comparing(Product::getName);
                break;
            case STOCK:
                comparator = Comparator.comparing(Product::getStock);
                break;
            case RATING:
                comparator = Comparator.comparing(Product::getRating);
                break;
            default:
                comparator = Comparator.comparing(Product::getPrice);
                break;
        }

        switch (sortType) {
            case ASCENDING:
                assert comparator != null;
                products.sort(comparator);
                break;
            case DESCENDING:
                assert comparator != null;
                products.sort(comparator.reversed());
                break;
        }

        displayProducts();
    }

    private void displayProducts() {
        System.out.println("Sıralanmış Ürünler: ");
        for (Product product : products) {
            System.out.println("Ürün adı: " + product.getName() + " Fiyat: " + product.getPrice() + " Stok: " + product.getStock() + " Puan: " + product.getRating());
        }
    }

    public void wantToAddToCart() {
        String answer = GetInput.getInput("Ürün eklemek istiyor musunuz? (Evet/Hayır)", InputType.STRING, null, null).toUpperCase();
        if (answer.equalsIgnoreCase("evet")) {
            addToCart();
            wantToAddToCart();
        }
    }

    public void addToCart() {
        String productName = GetInput.getInput("Sepete eklemek istediğiniz ürünün adını giriniz: ", InputType.STRING, 20, null);
        Product product = findProductByName(productName);

        if (product == null) {
            System.out.println("Ürün bulunamadı.");
            return;
        }

        int quantity = Integer.parseInt(GetInput.getInput("Üründen kaç adet eklemek istersiniz? ", InputType.INT, 1, null));

        if (quantity > product.getStock()) {
            System.out.println("Stokta yeterli ürün bulunmamaktadı.");
            return;
        }

        cart.put(product, cart.getOrDefault(product, 0) + quantity);
        product.setStock(product.getStock() - quantity);
        calculateCartTotal();
    }

    private void calculateCartTotal() {
        double total = 0;

        System.out.println("Sepetiniz:");
        for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            double price = product.getPrice();
            double discountedPrice = price;

            if (total > 0 && price < total / quantity) {
                double discount = (total / quantity) - price;
                discountedPrice = price - discount;
            }

            total += discountedPrice * quantity;

            System.out.printf("%s - Adet: %d, Toplam Fiyat: %.2f\n", product.getName(), quantity, discountedPrice * quantity);
        }

        System.out.printf("Sepet Toplamı: %.2f\n", total);
    }


    private Product findProductByName(String name) {
        return products.stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    private SortBy getSortByFromUser() {
        String input = GetInput.getInput("Ürünleri hangi kritere göre sıralamak istersiniz ? (name/stock/rating)", InputType.STRING, null, null).toUpperCase();
        try {
            return SortBy.valueOf(input);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    private SortType getSortTypeFromUser() {
        String input = GetInput.getInput("Sıralama türünü belirleyin (ascending/descending)", InputType.STRING, null, null).toUpperCase();
        try {
            return SortType.valueOf(input);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
