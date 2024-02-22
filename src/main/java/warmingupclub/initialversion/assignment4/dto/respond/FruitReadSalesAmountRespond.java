package warmingupclub.initialversion.assignment4.dto.respond;

public class FruitReadSalesAmountRespond {

    private long salesAmount;
    private long notSalesAmount;

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
