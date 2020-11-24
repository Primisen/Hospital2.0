package training.nadia.hospital.controller;

import org.apache.log4j.Logger;
import training.nadia.hospital.entity.*;
import training.nadia.hospital.service.AuthorizationService;
import training.nadia.hospital.exception.ServiceException;
import training.nadia.hospital.service.impl.AuthorizationServiceImpl;
import training.nadia.hospital.util.CopyData;

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

        User user = new User();
        user.setLogin(request.getParameter("login"));
        user.setPassword(request.getParameter("password"));

        AuthorizationService authorizationService = new AuthorizationServiceImpl();

        try {
            authorizationService.authorize(user);
        } catch (ServiceException e) {
            Logger logger = Logger.getRootLogger();
            logger.error(e.getMessage());

            request.setAttribute("exceptionMessage", e.getMessage());
            doGet(request, response);
        }

        if (user.getRole() == Role.PATIENT) {
            Patient patient = new Patient();
            CopyData.copy(user, patient);
            session.setAttribute("user", patient);
            response.sendRedirect("/patient");

        } else if (user.getRole() == Role.DOCTOR) {
            Doctor doctor = new Doctor();
            CopyData.copy(user, doctor);
            session.setAttribute("user", doctor);
            response.sendRedirect("/doctor");

        } else if (user.getRole() == Role.NURSE) {
            Nurse nurse = new Nurse();
            CopyData.copy(user, nurse);
            session.setAttribute("user", nurse);
            response.sendRedirect("/nurse");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/page/login.jsp");
        requestDispatcher.forward(request, response);
    }
}
