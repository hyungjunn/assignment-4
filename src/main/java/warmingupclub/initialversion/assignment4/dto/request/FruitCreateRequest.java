package warmingupclub.initialversion.assignment4.dto.request;

import java.time.LocalDate;

public class FruitCreateRequest {
    private long id;
    private String name;
    private LocalDate date;
    private long price;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public long getPrice() {
        return price;
    }
}
