package warmingupclub.initialversion.assignment4.dto.respond;

public class FruitReadSalesAmountRespond {

    private final Long salesAmount;
    private final Long notSalesAmount;

    public FruitReadSalesAmountRespond(Long salesAmount, Long notSalesAmount) {
        this.salesAmount = salesAmount;
        this.notSalesAmount = notSalesAmount;
    }

    public Long getSalesAmount() {
        return salesAmount;
    }

    public Long getNotSalesAmount() {
        return notSalesAmount;
    }
}
