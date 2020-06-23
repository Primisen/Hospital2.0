package training.nadia.hospital.controller;

import training.nadia.hospital.entity.Patient;
import training.nadia.hospital.service.impl.PatientServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/doctor")
public class DoctorServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PatientServiceImpl utilService = new PatientServiceImpl();
        List<Patient> patients = utilService.getAllPatients();

        request.setAttribute("patients", patients);

        RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher("/doctor.jsp");

        dispatcher.forward(request, response);
    }
}
