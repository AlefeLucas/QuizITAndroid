package com.example.ti01n.quizit;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Alefe Lucas on 11/11/2015.
 */
public class QuestionWrapper implements Serializable{

    private ArrayList<Question> questions;

    private static final long serialVersionUID = 1L;

    public QuestionWrapper(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }
}
