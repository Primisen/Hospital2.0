package training.nadia.hospital.controller;

import org.apache.log4j.Logger;
import training.nadia.hospital.entity.Doctor;
import training.nadia.hospital.service.DoctorService;
import training.nadia.hospital.exception.ServiceException;
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

    private Logger logger = Logger.getRootLogger();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            Doctor doctor = (Doctor) request.getSession().getAttribute("user");

            DoctorService doctorService = new DoctorServiceImpl();
            doctorService.getPatients(doctor);

            request.setAttribute("patients", doctor.getPatientsToCure());//???они же одинаковые
            request.setAttribute("patientsList", doctor.getPatientsToCure());


        } catch (ServiceException e) {
            logger.error(e.getMessage());
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/page/patientTreatment.jsp");
        requestDispatcher.forward(request, response);
    }
}
