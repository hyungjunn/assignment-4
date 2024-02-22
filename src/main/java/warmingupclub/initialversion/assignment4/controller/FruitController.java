package warmingupclub.initialversion.assignment4.controller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import warmingupclub.initialversion.assignment4.dto.request.FruitCreateRequest;
import warmingupclub.initialversion.assignment4.dto.request.FruitUpdateRequest;

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
    public void updateFruitInformation(@RequestBody FruitUpdateRequest request) {
        String sql = "UPDATE fruit SET is_sold = ? WHERE id = ?";
        jdbcTemplate.update(sql, request.isSold(), request.getId());
    }
}
