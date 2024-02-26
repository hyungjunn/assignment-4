package warmingupclub.initialversion.assignment4.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import warmingupclub.initialversion.assignment4.dto.request.FruitCreateRequest;
import warmingupclub.initialversion.assignment4.dto.respond.FruitReadSalesAmountRespond;

import java.util.List;

public class FruitRepository {

    public void saveFruit(JdbcTemplate jdbcTemplate, FruitCreateRequest request) {
        String sql = "INSERT INTO fruit(name, warehousing_date, price) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, request.getName(), request.getDate(), request.getPrice());
    }

    public FruitReadSalesAmountRespond getSalesFruitAmount(JdbcTemplate jdbcTemplate, String name) {
        String salesAmountSql = "SELECT SUM(price) FROM fruit WHERE name = ? GROUP BY is_sold";
        List<Long> salesAmounts = jdbcTemplate.query(salesAmountSql, (rs, rowNum) -> rs.getLong(1), name);

        Long salesAmount = salesAmounts.get(0);
        Long notSalesAmount = salesAmounts.get(1);

        return new FruitReadSalesAmountRespond(salesAmount, notSalesAmount);
    }

    public boolean isNotExistFruit(JdbcTemplate jdbcTemplate, long id) {
        String readSql = "SELECT * FROM fruit WHERE id = ?";
        return jdbcTemplate.query(readSql, (rs, rowNum) -> 0, id).isEmpty();
    }

    public void updateFruit(JdbcTemplate jdbcTemplate, long id) {
        String sql = "UPDATE fruit SET is_sold = 1 WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

}
