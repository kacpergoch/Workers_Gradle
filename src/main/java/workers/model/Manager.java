package workers.model;

import java.io.Serializable;

public class Manager extends Employee  implements Serializable {
    private final int bonus;
    private final String card;
    private final int cardLimit;

    public Manager(
            String pesel, String name, String surname, String salary, String phone,
            String bonus, String card, String cardLimit
    ){
        super(pesel, name, surname, salary, phone);
        this.bonus = Integer.parseInt(bonus);
        this.card = card;
        this.cardLimit = Integer.parseInt(cardLimit);
    }

    public int getBonus() {
        return bonus;
    }

    public String getCard() {
        return card;
    }

    public int getCardLimit() {
        return cardLimit;
    }
}
