package warmingupclub.initialversion.assignment4.repository;

import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import warmingupclub.initialversion.assignment4.dto.request.FruitCreateRequest;
import warmingupclub.initialversion.assignment4.dto.respond.FruitReadSalesAmountRespond;

import java.util.List;

@Repository
public class FruitMySqlRepository implements FruitRepository {

    private final JdbcTemplate jdbcTemplate;

    public FruitMySqlRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveFruit(FruitCreateRequest request) {
        String sql = "INSERT INTO fruit(name, warehousing_date, price) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, request.getName(), request.getDate(), request.getPrice());
    }

    @Override
    public FruitReadSalesAmountRespond getSalesFruitAmount(String name) {
        String salesAmountSql = "SELECT SUM(price) FROM fruit WHERE name = ? GROUP BY is_sold";
        List<Long> salesAmounts = jdbcTemplate.query(salesAmountSql, (rs, rowNum) -> rs.getLong(1), name);

        Long salesAmount = salesAmounts.get(0);
        Long notSalesAmount = salesAmounts.get(1);

        return new FruitReadSalesAmountRespond(salesAmount, notSalesAmount);
    }

    @Override
    public boolean isNotExistFruit(long id) {
        String readSql = "SELECT * FROM fruit WHERE id = ?";
        return jdbcTemplate.query(readSql, (rs, rowNum) -> 0, id).isEmpty();
    }

    @Override
    public void updateFruit(long id) {
        String sql = "UPDATE fruit SET is_sold = 1 WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
