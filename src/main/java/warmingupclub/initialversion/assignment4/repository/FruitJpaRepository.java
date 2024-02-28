package warmingupclub.initialversion.assignment4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import warmingupclub.initialversion.assignment4.domain.Fruit;

import java.util.List;

public interface FruitJpaRepository extends JpaRepository<Fruit, Long> {

    List<Fruit> findByName(String name);

    List<Fruit> findByPriceGreaterThanEqualAndIsSold(Long price, Boolean isSold);
    List<Fruit> findByPriceLessThanEqualAndIsSold(Long price, Boolean isSold);

}
