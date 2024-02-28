package warmingupclub.initialversion.assignment4.dto.respond;

import warmingupclub.initialversion.assignment4.domain.Fruit;

import java.time.LocalDate;

public class FruitsSpecificOptionPriceRespond {

    private String name;
    private Long price;
    private LocalDate wareHousingDate;

    public FruitsSpecificOptionPriceRespond(Fruit fruit) {
        this.name = fruit.getName();
        this.price = fruit.getPrice();
        this.wareHousingDate = fruit.getWarehousing_date();
    }

    public String getName() {
        return name;
    }

    public Long getPrice() {
        return price;
    }

    public LocalDate getWareHousingDate() {
        return wareHousingDate;
    }
}
