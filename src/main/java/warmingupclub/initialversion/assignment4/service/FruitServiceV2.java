package warmingupclub.initialversion.assignment4.service;

import org.springframework.stereotype.Service;
import warmingupclub.initialversion.assignment4.domain.Fruit;
import warmingupclub.initialversion.assignment4.dto.request.FruitCreateRequest;
import warmingupclub.initialversion.assignment4.dto.respond.FruitReadSalesAmountRespond;
import warmingupclub.initialversion.assignment4.repository.FruitJpaRepository;

import java.util.List;

@Service
public class FruitServiceV2 {

    private final FruitJpaRepository fruitRepository;

    public FruitServiceV2(FruitJpaRepository fruitRepository) {
        this.fruitRepository = fruitRepository;
    }

    public void saveFruit(FruitCreateRequest request) {
        fruitRepository.save(new Fruit(request.getName(), request.getDate(), request.getPrice()));
    }

    public FruitReadSalesAmountRespond getSalesFruitAmount(String name) {
        List<Fruit> fruits = fruitRepository.findByName(name);

        Long salesAmount = calculateAmount(fruits, true);
        Long notSalesAmount = calculateAmount(fruits, false);

        return new FruitReadSalesAmountRespond(salesAmount, notSalesAmount);
    }

    private Long calculateAmount(List<Fruit> fruits, boolean isSold) {
        return fruits.stream()
                .filter(fruit -> fruit.isSold() == isSold)
                .mapToLong(Fruit::getPrice)
                .sum();
    }

}
