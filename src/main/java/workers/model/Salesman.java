package workers.model;

import java.io.Serializable;

public class Salesman extends Employee implements Serializable {
    private final int commission;
    private final int commissionLimit;

    public Salesman(
            String pesel, String name, String surname, String salary, String phone,
            String commission, String commissionLimit
    ){
        super(pesel, name, surname, salary, phone);
        this.commission = Integer.parseInt(commission);
        this.commissionLimit  = Integer.parseInt(commissionLimit);
    }

    public int getCommission() {
        return commission;
    }

    public int getCommissionLimit() {
        return commissionLimit;
    }
}
