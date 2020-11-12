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

@WebServlet("/treatment")
public class TreatmentServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{


    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        try {
            Doctor doctor = (Doctor) request.getSession().getAttribute("user");

            DoctorService doctorService = new DoctorServiceImpl();
            doctorService.getPatients(doctor);

            request.setAttribute("patients", doctor.getPatientsToCure());


        } catch (ServiceException e) {
            e.printStackTrace();
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/page/doctor/patientTreatment.jsp");
        requestDispatcher.forward(request, response);
    }
}
