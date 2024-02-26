package warmingupclub.initialversion.assignment4.repository;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import warmingupclub.initialversion.assignment4.domain.Fruit;
import warmingupclub.initialversion.assignment4.dto.request.FruitCreateRequest;
import warmingupclub.initialversion.assignment4.dto.respond.FruitReadSalesAmountRespond;

import java.util.ArrayList;
import java.util.List;

@Primary
@Repository
public class FruitMemoryRepository implements FruitRepository {

    private final List<Fruit> fruits = new ArrayList<>();
    private static Long id = 1L;

    @Override
    public void saveFruit(FruitCreateRequest request) {
        fruits.add(new Fruit(id++, request.getName(), request.getDate(), request.getPrice()));
    }

    @Override
    public FruitReadSalesAmountRespond getSalesFruitAmount(String name) {
        long salesAmount = fruits.stream()
                .filter(fruit -> fruit.getName().equals(name) && fruit.is_sold())
                .mapToLong(Fruit::getPrice)
                .sum();

        long notSalesAmount = fruits.stream()
                .filter(fruit -> fruit.getName().equals(name) && !fruit.is_sold())
                .mapToLong(Fruit::getPrice)
                .sum();

        return new FruitReadSalesAmountRespond(salesAmount, notSalesAmount);
    }

    @Override
    public boolean isNotExistFruit(long id) {
        return fruits.stream().noneMatch(fruit -> fruit.getId() == id);
    }

    @Override
    public void updateFruit(long id) {
        fruits.stream()
                .filter(fruit -> fruit.getId() == id)
                .forEach(fruit -> fruit.is_sold = true);
    }
}
