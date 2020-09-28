package by.gil.receiver;

import java.io.Serializable;

public class ReceiverDto implements Serializable {

    private int id;
    private String receiverName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }


    @Override
    public String toString() {
        return "ReceiverDto{" + "id = " + id + ", receiver name = " + receiverName;
    }
}
