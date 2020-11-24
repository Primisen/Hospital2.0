package training.nadia.hospital.controller;

import org.apache.log4j.Logger;
import training.nadia.hospital.entity.Patient;
import training.nadia.hospital.service.PatientService;
import training.nadia.hospital.exception.ServiceException;
import training.nadia.hospital.service.impl.PatientServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/patient")
public class PatientServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            PatientService patientService = new PatientServiceImpl();
            Patient patient = (Patient) request.getSession().getAttribute("user");
            patientService.getTreatmentData(patient);

            request.setAttribute("patient", patient);

        } catch (ServiceException e) {
            Logger logger = Logger.getRootLogger();
            logger.error(e.getMessage());
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/page/patient.jsp");
        dispatcher.forward(request, response);
    }
}
