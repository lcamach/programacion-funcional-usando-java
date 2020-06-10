package co.s4n.immutableobjects;

public final class Book {

    private final String title;
    private final Double price;

    protected Book(String title, Double price) {
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public Double getPrice() {
        return price;
    }

    public Book changePrice(Double price) {
        return new Book(title, price);
    }
}
