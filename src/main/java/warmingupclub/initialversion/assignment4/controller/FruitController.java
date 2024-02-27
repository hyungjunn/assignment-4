package warmingupclub.initialversion.assignment4.controller;

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
import warmingupclub.initialversion.assignment4.service.FruitServiceV2;

@RequestMapping("/api/v1")
@RestController
public class FruitController {

    private final FruitServiceV2 fruitServiceV2;

    public FruitController(FruitServiceV2 fruitServiceV2) {
        this.fruitServiceV2 = fruitServiceV2;
    }

    @PostMapping("/fruit")
    public void saveFruit(@RequestBody FruitCreateRequest request) {
        fruitServiceV2.saveFruit(request);
    }

    @GetMapping("/fruit/stat")
    public FruitReadSalesAmountRespond readSalesFruitAmount(@RequestParam String name) {
        return fruitServiceV2.getSalesFruitAmount(name);
    }

    @PutMapping("/fruit")
    public void updateSoldFruitInformation(@RequestBody FruitUpdateRequest request) {
        fruitServiceV2.updateSoldFruitInformation(request.getId());
    }

}