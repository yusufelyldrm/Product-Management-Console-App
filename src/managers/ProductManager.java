package managers;

public class ProductManager {
    public enum SortType {
        ASCENDING,
        DESCENDING
    }

    public enum SortBy {
        RATING,
        STOCK,
        NAME,
        PRICE,
    }

    public void addProducts() {}

    public void sortProducts(SortType sortType, SortBy sortBy) {}

    private void dispplayProducts() {}

    public void addToCart() {}

    private void calculateCartTotal() {}

    private void findProductByName() {}
}
