public class Order {
    private CoffeeMenu menu;
    private int count;

    public Order(CoffeeMenu menu, int count) {
        this.menu = menu;
        this.count = count;
    }

    public boolean hasCount() {
        return count != 0;
    }

    public CoffeeMenu dequeueMenu() {
        count--;
        return menu;
    }
}
