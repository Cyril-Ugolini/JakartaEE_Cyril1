package fr.afpa.jakartaee_cyril1.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class TemplateController implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {


        return "/WEB-INF/jsp/template.jsp";
    }
}