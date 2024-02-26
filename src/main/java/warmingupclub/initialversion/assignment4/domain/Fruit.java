package warmingupclub.initialversion.assignment4.domain;

import java.time.LocalDate;

public class Fruit {

    private long id;
    private final String name;
    private final LocalDate warehousing_date;
    private final long price;
    public boolean is_sold;

    public Fruit(long id, String name, LocalDate warehousing_date, long price) {
        this.id = id;
        this.name = name;
        this.warehousing_date = warehousing_date;
        this.price = price;
        this.is_sold = false;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getWarehousing_date() {
        return warehousing_date;
    }

    public long getPrice() {
        return price;
    }

    public boolean is_sold() {
        return is_sold;
    }
}
