package warmingupclub.initialversion.assignment4.service;

import org.springframework.jdbc.core.JdbcTemplate;
import warmingupclub.initialversion.assignment4.dto.request.FruitCreateRequest;
import warmingupclub.initialversion.assignment4.dto.respond.FruitReadSalesAmountRespond;
import warmingupclub.initialversion.assignment4.repository.FruitRepository;

public class FruitService {

    private final FruitRepository fruitRepository;

    public FruitService(JdbcTemplate jdbcTemplate) {
        this.fruitRepository = new FruitRepository(jdbcTemplate);
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
