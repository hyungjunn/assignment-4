package warmingupclub.initialversion.assignment4.dto.request;

public class FruitUpdateRequest {
    Long id;
    Boolean isSold;

    public FruitUpdateRequest(Long id, Boolean isSold) {
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
