package training.nadia.hospital.controller;

import training.nadia.hospital.entity.Doctor;
import training.nadia.hospital.service.DoctorService;
import training.nadia.hospital.service.exception.ServiceException;
import training.nadia.hospital.service.impl.DoctorServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/doctor")
public class DoctorServlet extends HttpServlet {

    //логироать здесь

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Doctor doctor = (Doctor) request.getSession().getAttribute("user");
        request.setAttribute("doctor", doctor);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/page/doctor/doctor.jsp");
        dispatcher.forward(request, response);
    }
}
