package training.nadia.hospital.controller;

import training.nadia.hospital.entity.Doctor;
import training.nadia.hospital.entity.MedicalStaff;
import training.nadia.hospital.entity.Nurse;
import training.nadia.hospital.entity.Role;
import training.nadia.hospital.entity.User;
import training.nadia.hospital.service.impl.UserServiceImpl;

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

        User user = new User();
        user.setLogin(request.getParameter("login"));
        user.setPassword(request.getParameter("password"));

        UserServiceImpl userService = new UserServiceImpl();
        userService.login(user);

        session.setAttribute("user", user);

        if (user.getRoleId() == Role.PATIENT.getId()) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/patient");
            dispatcher.forward(request, response);

        } else if (user.getRoleId() == Role.MEDICAL_STAFF.getId()) {

            MedicalStaff medicalStaff = userService.getStaff(user);

            if (medicalStaff instanceof Doctor) {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/doctor");
                dispatcher.forward(request, response);

            } else if (medicalStaff instanceof Nurse) {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/nurse");
                dispatcher.forward(request, response);
            }

        } else if (user.getRoleId() == Role.ADMINISTRATOR.getId()) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/administrator");
            dispatcher.forward(request, response);
        }

    }
}
