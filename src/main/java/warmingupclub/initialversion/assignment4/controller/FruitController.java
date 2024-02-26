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
import warmingupclub.initialversion.assignment4.service.FruitService;

@RequestMapping("/api/v1")
@RestController
public class FruitController {

    private final FruitService fruitService;

    public FruitController(FruitService fruitService) {
        this.fruitService = fruitService;
    }

    @PostMapping("/fruit")
    public void saveFruit(@RequestBody FruitCreateRequest request) {
        fruitService.saveFruit(request);
    }

    @GetMapping("/fruit/stat")
    public FruitReadSalesAmountRespond readSalesFruitAmount(@RequestParam String name) {
        return fruitService.readSalesFruitAmount(name);
    }

    @PutMapping("/fruit")
    public void updateSoldFruitInformation(@RequestBody FruitUpdateRequest request) {
        fruitService.updateSoldFruitInformation(request.getId());
    }

}