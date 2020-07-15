package training.nadia.hospital.controller;

import training.nadia.hospital.entity.*;
import training.nadia.hospital.service.exception.ServiceException;
import training.nadia.hospital.service.impl.RegistrationServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        request.setAttribute("staffTypeDoctor", StaffType.DOCTOR.getId());
        request.setAttribute("staffTypeNurse", StaffType.NURSE.getId());

        User user;

//        user.setName(request.getParameter("name"));
//        user.setSurname(request.getParameter("surname"));
//        user.setLogin(request.getParameter("login"));
//        user.setPassword(request.getParameter("password"));
        try {


            if (request.getParameter("typeDoctor") != null &&
                    !request.getParameter("typeDoctor").equals("") && Integer.parseInt(request.getParameter("typeDoctor")) == StaffType.DOCTOR.getId()) {

                user = new Doctor();

                user.setName(request.getParameter("name"));
                user.setSurname(request.getParameter("surname"));
                user.setLogin(request.getParameter("login"));
                user.setPassword(request.getParameter("password"));

                user.setRoleId(Role.MEDICAL_STAFF.getId());

            } else if (request.getParameter("typeNurse") != null &&
                    !request.getParameter("typeNurse").equals("") && Integer.parseInt(request.getParameter("typeNurse")) == StaffType.NURSE.getId()) {

                user = new Nurse();

                user.setName(request.getParameter("name"));
                user.setSurname(request.getParameter("surname"));
                user.setLogin(request.getParameter("login"));
                user.setPassword(request.getParameter("password"));

                user.setRoleId(Role.MEDICAL_STAFF.getId());
            } else {

                user = new Patient();

                user.setName(request.getParameter("name"));
                user.setSurname(request.getParameter("surname"));
                user.setLogin(request.getParameter("login"));
                user.setPassword(request.getParameter("password"));

                user.setRoleId(Role.PATIENT.getId());

                RegistrationServiceImpl registrationService = new RegistrationServiceImpl();
                registrationService.register(user);

                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/patient");
                requestDispatcher.forward(request, response);

            }


        } catch (ServiceException e) {
            e.printStackTrace();//!
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/page/registration.jsp");
        requestDispatcher.forward(request, response);
    }
}
