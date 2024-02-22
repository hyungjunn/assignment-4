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

import java.util.HashSet;
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

    @GetMapping("/fruit/stat")
    public FruitReadSalesAmountRespond readSalesFruitAmount(@RequestParam String name) {

        long salesAmount = 0;
        long notSalesAmount = 0;

        String salesAmountSql = "SELECT * FROM fruit WHERE name = ? AND is_sold = 1";
        List<Long> salesPrices = jdbcTemplate.query(salesAmountSql, (rs, rowNum) -> rs.getLong("price"), name);

        String notSalesAmountSql = "SELECT * FROM fruit WHERE name = ? AND is_sold = 0";
        List<Long> notSalesPrices = jdbcTemplate.query(notSalesAmountSql, (rs, rowNum) -> rs.getLong("price"), name);

        for (Long salesPrice : salesPrices) {
            salesAmount += salesPrice;
        }

        for (Long notSalesPrice : notSalesPrices) {
            notSalesAmount += notSalesPrice;
        }

        return new FruitReadSalesAmountRespond(salesAmount, notSalesAmount);
    }
}
