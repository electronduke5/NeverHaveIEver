package com.example.neverhaveiever_2.CustomQuestionModel;

import com.example.neverhaveiever_2.data.QuestionContract;

public class CustomQModel {
    int ID;
    String Text;

    public CustomQModel(int id, String text){
        this.ID = id;
        this.Text = text;
    }

    public String getText() {
        return Text;
    }

    public int getID() {
        return ID;
    }
}
