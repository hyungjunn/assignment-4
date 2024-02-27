package warmingupclub.initialversion.assignment4.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Fruit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @Column(nullable = false)
    private String name;
    private LocalDate warehousing_date;
    private Long price;
    public Boolean isSold;

    protected Fruit() {}

    public Fruit(String name, LocalDate warehousing_date, Long price) {
        this.name = name;
        this.warehousing_date = warehousing_date;
        this.price = price;
        this.isSold = false;
    }

    public Fruit(Long id, String name, LocalDate warehousing_date, Long price) {
        this.id = id;
        this.name = name;
        this.warehousing_date = warehousing_date;
        this.price = price;
        this.isSold = false;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getWarehousing_date() {
        return warehousing_date;
    }

    public Long getPrice() {
        return price;
    }

    public Boolean isSold() {
        return isSold;
    }

    public void updateSoldInformation() {
        this.isSold = true;
    }

}
