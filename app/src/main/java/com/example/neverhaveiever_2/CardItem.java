package com.example.neverhaveiever_2;

public class CardItem {
    private String Action;
    private String CountAction;

    public CardItem(String Action, String CountAction){
        this.Action = Action;
        this.CountAction = CountAction;
    }
    public CardItem(String Action){
        this.Action = Action;
    }

    public String getCountAction(){
        return CountAction;
    }

    public void setCountAction(String countAction) {
        this.CountAction = countAction;
    }

    public String getAction(){
        return Action;
    }

    public void setAction(String Action){
        this.Action = Action;
    }
}