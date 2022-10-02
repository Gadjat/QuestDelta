package com.javarush.quest.bulimov.questdelta.controller;

import com.javarush.quest.bulimov.questdelta.dto.FormData;
import com.javarush.quest.bulimov.questdelta.services.AnswerService;
import com.javarush.quest.bulimov.questdelta.services.GameService;
import com.javarush.quest.bulimov.questdelta.services.QuestionService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;

@WebServlet(name = "gameServlet", value = "/game")
public class GameServlet extends HttpServlet {
    private final GameService gameService = GameService.INSTANCE;
    private final QuestionService questionService = QuestionService.INSTANCE;

    private final AnswerService answerService = AnswerService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getAttribute("id") == null){
            doPost(req, resp);
        }
        else{

            Long questionId = gameService.find(FormData.of(req)).get().getCurrentQuestionId();
            req.setAttribute("questionText", questionService.get(questionId).get().getText());



            req.setAttribute("ans1", answerService.get(questionId).get());
            req.setAttribute("ans2", "test");

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("game.jsp");
            requestDispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        gameService.create(FormData.of(req));
        req.setAttribute("name", gameService.find(FormData.of(req)).get().getUserName());
        req.setAttribute("id", gameService.find(FormData.of(req)).get().getId());
        doGet(req, resp);
    }
}
