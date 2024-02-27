package warmingupclub.initialversion.assignment4.repository;

import org.springframework.stereotype.Repository;
import warmingupclub.initialversion.assignment4.domain.Fruit;
import warmingupclub.initialversion.assignment4.dto.request.FruitCreateRequest;
import warmingupclub.initialversion.assignment4.dto.respond.FruitReadSalesAmountRespond;

import java.util.ArrayList;
import java.util.List;

//@Primary
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
        long salesAmount = calculateSalesAmount(name);
        long notSalesAmount = calculateNotSalesAmount(name);
        return new FruitReadSalesAmountRespond(salesAmount, notSalesAmount);
    }

    @Override
    public boolean isNotExistFruit(Long id) {
        return fruits.stream().noneMatch(fruit -> fruit.getId().equals(id));
    }

    @Override
    public void updateFruit(Long d) {
        fruits.stream()
                .filter(fruit -> fruit.getId().equals(id))
                .forEach(fruit -> fruit.isSold = true);
    }

    private long calculateSalesAmount(String name) {
        return fruits.stream()
                .filter(fruit -> fruit.getName().equals(name) && fruit.isSold())
                .mapToLong(Fruit::getPrice)
                .sum();
    }

    private long calculateNotSalesAmount(String name) {
        return fruits.stream()
                .filter(fruit -> fruit.getName().equals(name) && !fruit.isSold())
                .mapToLong(Fruit::getPrice)
                .sum();
    }

}
