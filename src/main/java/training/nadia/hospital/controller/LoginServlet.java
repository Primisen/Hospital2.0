package training.nadia.hospital.controller;

import training.nadia.hospital.entity.*;
import training.nadia.hospital.service.exception.ServiceException;
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
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        AuthorizationServiceImpl authorizationService = new AuthorizationServiceImpl();

        User user = new User();

        try {
            user = authorizationService.getUser(login, password);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        if (user instanceof Patient) {
            Patient patient = (Patient) user;
            session.setAttribute("user", patient); // user + id
            response.sendRedirect("/patient");

        } else if (user instanceof Doctor) {
            Doctor doctor = (Doctor) user;
            session.setAttribute("user", doctor);
            response.sendRedirect("/doctor");

        } else if (user instanceof Nurse) {
            Nurse nurse = (Nurse) user;
            session.setAttribute("user", nurse);
            response.sendRedirect("/nurse");

        } else if (user instanceof Administrator) {
            Administrator administrator = (Administrator) user;
            session.setAttribute("user", administrator);
            response.sendRedirect("/administrator");
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
        requestDispatcher.forward(request, response);
    }
}
