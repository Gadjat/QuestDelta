package com.javarush.quest.bulimov.questdelta.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "exceptionServlet", value = "/exception")
public class ExceptionServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Ошибка!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + request.getSession().getAttribute("login") + message + "</h1>");
        out.println("</body></html>");

    }

    public void destroy() {
    }
}