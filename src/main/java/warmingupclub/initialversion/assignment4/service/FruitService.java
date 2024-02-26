package warmingupclub.initialversion.assignment4.service;

import org.springframework.jdbc.core.JdbcTemplate;
import warmingupclub.initialversion.assignment4.dto.request.FruitCreateRequest;
import warmingupclub.initialversion.assignment4.dto.respond.FruitReadSalesAmountRespond;
import warmingupclub.initialversion.assignment4.repository.FruitRepository;

public class FruitService {

    private final FruitRepository fruitRepository = new FruitRepository();

    public void saveFruit(JdbcTemplate jdbcTemplate, FruitCreateRequest request) {
        fruitRepository.saveFruit(jdbcTemplate, request);
    }

    public FruitReadSalesAmountRespond readSalesFruitAmount(JdbcTemplate jdbcTemplate, String name) {
        return fruitRepository.getSalesFruitAmount(jdbcTemplate, name);
    }

    public void updateSoldFruitInformation(JdbcTemplate jdbcTemplate, long id) {
        if (fruitRepository.isNotExistFruit(jdbcTemplate, id)) {
            throw new IllegalArgumentException();
        }

        fruitRepository.updateFruit(jdbcTemplate, id);
    }

}
