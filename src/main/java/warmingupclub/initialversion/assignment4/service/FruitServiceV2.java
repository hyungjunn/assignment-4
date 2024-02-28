package warmingupclub.initialversion.assignment4.service;

import org.springframework.stereotype.Service;
import warmingupclub.initialversion.assignment4.domain.Fruit;
import warmingupclub.initialversion.assignment4.dto.request.FruitCreateRequest;
import warmingupclub.initialversion.assignment4.dto.respond.FruitReadSalesAmountRespond;
import warmingupclub.initialversion.assignment4.dto.respond.FruitSoldCountRespond;
import warmingupclub.initialversion.assignment4.dto.respond.FruitsSpecificOptionPriceRespond;
import warmingupclub.initialversion.assignment4.repository.FruitJpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public void updateSoldFruitInformation(Long id) {
        Fruit fruit = fruitRepository.findById(id)
                .orElseThrow(IllegalAccessError::new);

        fruit.updateSoldInformation();
        fruitRepository.save(fruit);
    }

    public FruitSoldCountRespond getSoldFruitCount(String name) {
        // name(요청한)과 repo에 있는 name이 일치하는 애들중에
        // 팔린(isSold가 true인) 것들의 갯수를 반환
        Long count = fruitRepository.findByName(name).stream()
                .filter(fruit -> fruit.isSold == true)
                .count();
        return new FruitSoldCountRespond(count);
    }

    public List<FruitsSpecificOptionPriceRespond> getSpecificOptionPriceFruits(String option, Long price) {
        if (isNotSpecificPriceOption(option)) {
            throw new IllegalArgumentException();
        }

        List<Fruit> fruits = new ArrayList<>();

        if (isGTE(option)) {
            fruits =  fruitRepository.findByPriceGreaterThanEqualAndIsSold(price, false);
        }

        if (isLTE(option)) {
            fruits =  fruitRepository.findByPriceLessThanEqualAndIsSold(price, false);
        }

        return convertToFruitsSpecificOptionPriceRespond(fruits);
    }

    private List<FruitsSpecificOptionPriceRespond> convertToFruitsSpecificOptionPriceRespond(List<Fruit> fruits) {
        return fruits.stream()
                .map(FruitsSpecificOptionPriceRespond::new)
                .collect(Collectors.toList());
    }

    private boolean isNotSpecificPriceOption(String option) {
        return !("GTE".equals(option) || "LTE".equals(option));
    }

    private boolean isGTE(String option) {
        return "GTE".equals(option);
    }

    private boolean isLTE(String option) {
        return "LTE".equals(option);
    }

}
