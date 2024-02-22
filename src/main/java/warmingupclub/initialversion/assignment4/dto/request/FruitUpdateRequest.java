package warmingupclub.initialversion.assignment4.dto.request;

public class FruitUpdateRequest {
    long id;
    boolean isSold;

    public FruitUpdateRequest(long id, boolean isSold) {
        this.id = id;
        this.isSold = isSold;
    }

    public long getId() {
        return id;
    }

    public boolean isSold() {
        return isSold;
    }
}
