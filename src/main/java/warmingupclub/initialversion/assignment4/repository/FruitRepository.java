package warmingupclub.initialversion.assignment4.repository;

import org.springframework.stereotype.Repository;
import warmingupclub.initialversion.assignment4.dto.request.FruitCreateRequest;
import warmingupclub.initialversion.assignment4.dto.respond.FruitReadSalesAmountRespond;

@Repository
public interface FruitRepository {

    void saveFruit(FruitCreateRequest request);

    FruitReadSalesAmountRespond getSalesFruitAmount(String name);

    boolean isNotExistFruit(Long id);

    void updateFruit(Long id);

}
