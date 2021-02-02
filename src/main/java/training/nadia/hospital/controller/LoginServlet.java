package training.nadia.hospital.controller;

import org.apache.log4j.Logger;
import training.nadia.hospital.entity.*;
import training.nadia.hospital.service.AuthorizationService;
import training.nadia.hospital.exception.ServiceException;
import training.nadia.hospital.service.impl.AuthorizationServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        AuthorizationService authorizationService = new AuthorizationServiceImpl();

        try {
            User user = authorizationService.authorize(login, password);
            session.setAttribute("user", user);

            if (user.getRole() == Role.PATIENT) {
                response.sendRedirect("/patient");

            } else if (user.getRole() == Role.DOCTOR) {
                response.sendRedirect("/doctor");

            } else if (user.getRole() == Role.NURSE) {
                response.sendRedirect("/nurse");
            }

        } catch (ServiceException e) {
            Logger logger = Logger.getRootLogger();
            logger.error(e.getMessage());

            request.setAttribute("exceptionMessage", e.getMessage());
            doGet(request, response);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/page/login.jsp");
        requestDispatcher.forward(request, response);
    }
}
