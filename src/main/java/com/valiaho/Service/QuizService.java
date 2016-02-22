package com.valiaho.Service;

import com.valiaho.Domain.CurrentQuiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Stack;

/**
 * Created by akivv on 18.2.2016.
 */
@Service
public class QuizService {
    @Autowired
    RandomNumberService randomNumberService;
    private Stack<CurrentQuiz> stackOfQuizes = new Stack<>();

    public void generateNewQuestion() {
        stackOfQuizes.add(new CurrentQuiz(randomNumberService.generateRandomNumber(10),
                randomNumberService.generateRandomNumber(20)));
    }

    public CurrentQuiz getLastQuiz() {
        return stackOfQuizes.pop();
    }

    public boolean noQuestions() {
        return stackOfQuizes.empty();
    }

    public boolean checkAnnswer(Integer i) {
        if (getLastQuiz().getTulo() == i) {
            stackOfQuizes.pop();
            return true;
        } else {
            return false;
        }
    }


}
