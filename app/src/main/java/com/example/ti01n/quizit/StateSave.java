package com.example.ti01n.quizit;



import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Alefe Lucas on 11/11/2015.
 */
public class StateSave implements Serializable {

    private Question current;
    private ArrayList<Question> questions;
    private String selectedOp;

    private int total;
    private int acertos;

    public StateSave(Question current, ArrayList<Question> questions, String selectedOp, int acertos, int total) {
        this.current = current;
        this.questions = questions;
        this.selectedOp = selectedOp;
        this.acertos = acertos;
        this.total = total;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Question getCurrent() {
        return current;
    }

    public void setCurrent(Question current) {
        this.current = current;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public String getSelectedOp() {
        return selectedOp;
    }

    public void setSelectedOp(String selectedOp) {
        this.selectedOp = selectedOp;
    }

    public int getAcertos() {
        return acertos;
    }

    public void setAcertos(int acertos) {
        this.acertos = acertos;
    }
}
