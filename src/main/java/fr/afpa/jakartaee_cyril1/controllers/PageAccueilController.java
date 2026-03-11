package fr.afpa.jakartaee_cyril1.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PageAccueilController implements ICommand {

    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "index.jsp";
    }
}