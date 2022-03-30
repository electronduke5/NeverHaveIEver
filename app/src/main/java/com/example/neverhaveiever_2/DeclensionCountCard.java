package com.example.neverhaveiever_2;

public class DeclensionCountCard {

    private final String FirstCase = "карта"; //Карта
    private final String SecondCase = "карты"; //Карты
    private final String ThirdCase = "карт";  //Карт

    public DeclensionCountCard() {}

    public String GetCase(int amount) {
        String wordCase;
        if (amount == 0) {
            wordCase = ThirdCase;
        } else if (amount >= 10 && amount <= 20) {
            wordCase = ThirdCase;
        } else {
            int remainder = amount % 10;
            if (remainder == 0)
                wordCase = ThirdCase;
            else if (remainder == 1)
                wordCase = FirstCase;
            else if (remainder > 1 && remainder < 5)
                wordCase = SecondCase;
            else wordCase = ThirdCase;
        }
        return String.format("%s %s", amount, wordCase);
    }
}