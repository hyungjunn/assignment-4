package warmingupclub.initialversion.assignment4.controller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import warmingupclub.initialversion.assignment4.dto.request.FruitCreateRequest;
import warmingupclub.initialversion.assignment4.dto.request.FruitUpdateRequest;
import warmingupclub.initialversion.assignment4.dto.respond.FruitReadSalesAmountRespond;

import java.util.List;

@RequestMapping("/api/v1")
@RestController
public class FruitController {

    private final JdbcTemplate jdbcTemplate;

    public FruitController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping("/fruit")
    public void createFruit(@RequestBody FruitCreateRequest request) {
        String sql = "INSERT INTO fruit(name, warehousing_date, price) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, request.getName(), request.getDate(), request.getPrice());
    }

    @PutMapping("/fruit")
    public void updateSoldFruitInformation(@RequestBody FruitUpdateRequest request) {
        String readSql = "SELECT * FROM fruit WHERE id = ?";
        boolean isEmpty = jdbcTemplate.query(readSql, (rs, rowNum) -> 0, request.getId()).isEmpty();
        if (isEmpty) {
            throw new IllegalArgumentException();
        }
        String sql = "UPDATE fruit SET is_sold = 1 WHERE id = ?";
        jdbcTemplate.update(sql, request.getId());
    }


    private long salesAmount;
    private long notSalesAmount;
    @GetMapping("/fruit/stat")
    public FruitReadSalesAmountRespond readSalesFruitAmount(@RequestParam String name) {
        String sql = "SELECT * FROM fruit WHERE name = ?";
        List<FruitReadSalesAmountRespond> query = jdbcTemplate.query(sql, (rs, rowNum) -> {
            long price = rs.getLong("price");
            boolean isSold = rs.getBoolean("is_sold");
            if (isSold) {
                salesAmount += price;
            }

            if (!isSold) {
                notSalesAmount += price;
            }

            return new FruitReadSalesAmountRespond(salesAmount, notSalesAmount);
        }, name);
        return new FruitReadSalesAmountRespond(query.get(query.size() - 1).getSalesAmount(), query.get(query.size() - 1).getNotSalesAmount());
    }
}
