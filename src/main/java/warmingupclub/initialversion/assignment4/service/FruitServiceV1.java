package warmingupclub.initialversion.assignment4.service;

import org.springframework.stereotype.Service;
import warmingupclub.initialversion.assignment4.dto.request.FruitCreateRequest;
import warmingupclub.initialversion.assignment4.dto.respond.FruitReadSalesAmountRespond;
import warmingupclub.initialversion.assignment4.repository.FruitRepository;

@Service
public class FruitServiceV1 {

    private final FruitRepository fruitRepository;

    public FruitServiceV1(FruitRepository fruitRepository) {
        this.fruitRepository = fruitRepository;
    }

    public void saveFruit(FruitCreateRequest request) {
        fruitRepository.saveFruit(request);
    }

    public FruitReadSalesAmountRespond readSalesFruitAmount(String name) {
        return fruitRepository.getSalesFruitAmount(name);
    }

    public void updateSoldFruitInformation(Long id) {
        if (fruitRepository.isNotExistFruit(id)) {
            throw new IllegalArgumentException();
        }

        fruitRepository.updateFruit(id);
    }

}
