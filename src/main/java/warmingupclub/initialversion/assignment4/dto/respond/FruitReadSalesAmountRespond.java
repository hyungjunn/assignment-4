package warmingupclub.initialversion.assignment4.dto.respond;

public class FruitReadSalesAmountRespond {

    private final long salesAmount;
    private final long notSalesAmount;

    public FruitReadSalesAmountRespond(long salesAmount, long notSalesAmount) {
        this.salesAmount = salesAmount;
        this.notSalesAmount = notSalesAmount;
    }

    public long getSalesAmount() {
        return salesAmount;
    }

    public long getNotSalesAmount() {
        return notSalesAmount;
    }
}
