package workers.controller;

import java.util.List;

public class Constants {
    public static final List<String> attributesManager = List.of(
            "PESEL",
            "Imię",
            "Nazwisko",
            "Wynagrodzenie (zł)",
            "Telefon służbowy",
            "Dodatek służbowy",
            "Kartę służbową",
            "Limit kosztów/miesiąc"
    );

    public static final List<String> attributesSalesman = List.of(
            "PESEL",
            "Imię",
            "Nazwisko",
            "Wynagrodzenie (zł)",
            "Telefon służbowy",
            "Prowizję (%)",
            "Limit prowizji/miesiąc (zł)"
    );
}
