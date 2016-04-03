package com.valiaho.Controller;

import com.valiaho.Domain.CurrentQuiz;
import com.valiaho.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by akivv on 18.2.2016.
 */
@RequestMapping(value = "/quizapp")
@ComponentScan(basePackages = "com.valiaho.Service")
@RestController
public class QuizController {

    @Autowired
    private QuizService quizService;

    @RequestMapping
    public CurrentQuiz questionToAsk() {
        if (quizService.noQuestions()) {
            quizService.generateNewQuestion();
        }
        return quizService.getLastQuiz();
    }
    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public boolean postAnswer(Integer i) {
        if (quizService.checkAnnswer(i)) {
            return true;
        } else {
            return false;
        }
    }
}
