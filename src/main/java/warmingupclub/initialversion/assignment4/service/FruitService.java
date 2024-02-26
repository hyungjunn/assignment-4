package warmingupclub.initialversion.assignment4.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import warmingupclub.initialversion.assignment4.dto.request.FruitCreateRequest;
import warmingupclub.initialversion.assignment4.dto.respond.FruitReadSalesAmountRespond;
import warmingupclub.initialversion.assignment4.repository.FruitRepository;

@Service
public class FruitService {

    private final FruitRepository fruitRepository;

    public FruitService(FruitRepository fruitRepository) {
        this.fruitRepository = fruitRepository;
    }

    public void saveFruit(FruitCreateRequest request) {
        fruitRepository.saveFruit(request);
    }

    public FruitReadSalesAmountRespond readSalesFruitAmount(String name) {
        return fruitRepository.getSalesFruitAmount(name);
    }

    public void updateSoldFruitInformation(long id) {
        if (fruitRepository.isNotExistFruit(id)) {
            throw new IllegalArgumentException();
        }

        fruitRepository.updateFruit(id);
    }

}
