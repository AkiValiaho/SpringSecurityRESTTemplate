package com.valiaho.Domain;

public class CurrentQuiz {
        private int number1, number2, tulo;

    public int getNumber1() {
        return number1;
    }

    public void setNumber1(int number1) {
        this.number1 = number1;
    }

    public int getNumber2() {
        return number2;
    }

    public void setNumber2(int number2) {
        this.number2 = number2;
    }

    public int getTulo() {
        return tulo;
    }

    public void setTulo(int tulo) {
        this.tulo = tulo;
    }

    public CurrentQuiz(int number1, int number2) {
            this.number1 = number1;
            this.number2 = number2;
            this.tulo = number1 * number2;
        }
    }