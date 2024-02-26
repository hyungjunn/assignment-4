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
        for (Fruit fruit : fruits) {
            if (fruit.getId() == id) {
                return false;
            }
        }

        return true;
    }

    @Override
    public void updateFruit(long id) {
        // id - 1을 인덱스로 하고 request한 객체의 is_sold를 true로 바꾼다
        for (int i = 0; i < fruits.size(); i++) {
            if (fruits.get(i).getId() == id) {
                fruits.get(i).is_sold = true;
            }
        }
    }
}
