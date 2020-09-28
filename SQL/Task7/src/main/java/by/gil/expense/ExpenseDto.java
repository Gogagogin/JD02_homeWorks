package by.gil.expense;

import java.io.Serializable;
import java.sql.Date;

public class ExpenseDto implements Serializable {

    private int id;
    private Date paydate;
    private int receiver;
    private float total;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getPaydate() {
        return paydate;
    }

    public void setPaydate(Date paydate) {
        this.paydate = paydate;
    }

    public int getReceiver() {
        return receiver;
    }

    public void setReceiver(int receiver) {
        this.receiver = receiver;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }


    @Override
    public String toString() {
        return "ClientDto{" +
                "id=" + id +
                ", pay date='" + paydate + '\'' +
                ", receiver='" + receiver + '\'' +
                ", total='" + total +
                '}';
    }
}
