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
        long salesAmout = 0L;
        long notSalesAmout = 0L;
        for (Fruit fruit : fruits) {
            if (fruit.getName().equals(name)) {
                if (fruit.is_sold()) {
                    salesAmout += fruit.getPrice();
                } else {
                    notSalesAmout += fruit.getPrice();
                }
            }
        }

        return new FruitReadSalesAmountRespond(salesAmout, notSalesAmout);
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
