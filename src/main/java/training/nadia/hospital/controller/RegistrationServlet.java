package training.nadia.hospital.controller;

import org.apache.log4j.Logger;
import training.nadia.hospital.entity.*;
import training.nadia.hospital.service.RegistrationService;
import training.nadia.hospital.service.exception.ServiceException;
import training.nadia.hospital.service.impl.RegistrationServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private Logger logger = Logger.getRootLogger();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        RegistrationService registrationService = new RegistrationServiceImpl();

        try {

            if (request.getParameter("doctorTypeId") != null) {
                Doctor doctor = new Doctor();
                setUserData(doctor, request);

                registrationService.register(doctor);

                session.setAttribute("user", doctor);
                response.sendRedirect("/doctor");

            } else if (request.getParameter("nurseTypeId") != null) {
                Nurse nurse = new Nurse();
                setUserData(nurse, request);

                registrationService.register(nurse);

                session.setAttribute("user", nurse);
                response.sendRedirect("/nurse");

            } else {
                Patient patient = new Patient();
                setUserData(patient, request);

                registrationService.register(patient);

                session.setAttribute("user", patient);
                response.sendRedirect("/patient");
            }

        } catch (ServiceException e) {
            request.setAttribute("errorMessage", e.getMessage());
            logger.error(e.getMessage());
            doGet(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/page/registration.jsp");
        requestDispatcher.forward(request, response);
    }

    private void setUserData(User user, HttpServletRequest request) {

        user.setName(request.getParameter("name"));
        user.setSurname(request.getParameter("surname"));
        user.setLogin(request.getParameter("login"));
        user.setPassword(request.getParameter("password"));
    }

}
