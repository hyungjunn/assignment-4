package warmingupclub.initialversion.assignment4.dto.request;

import java.time.LocalDate;

public class FruitInformationRequest {
    private String name;
    private LocalDate date;
    private long price;

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
