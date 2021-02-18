package training.nadia.hospital.controller;

import org.apache.log4j.Logger;
import training.nadia.hospital.entity.Doctor;
import training.nadia.hospital.entity.Patient;
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
import java.util.Set;

@WebServlet("/treatment")
public class TreatmentServlet extends HttpServlet {

    private Logger logger = Logger.getRootLogger();
    private DoctorService doctorService = new DoctorServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Doctor doctor = (Doctor) request.getSession().getAttribute("user");

        Patient patient = findPatientById(doctor.getPatientsUndergoingTreatment(),
                Integer.parseInt(request.getParameter("patientId")));

        try {
            doctorService.dischargePatient(patient);
        } catch (ServiceException e) {
            logger.error(e.getMessage());
        }

        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            Doctor doctor = (Doctor) request.getSession().getAttribute("user");//попробовать вынести в приватное поле

            doctorService.identifyPatients(doctor);

            request.setAttribute("patientsUndergoingTreatment", doctor.getPatientsUndergoingTreatment());

        } catch (ServiceException e) {
            logger.error(e.getMessage());
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/page/patientTreatment.jsp");
        requestDispatcher.forward(request, response);
    }

    private Patient findPatientById(Set<Patient> patients, long id) {//!

        for (Patient patient : patients) {
            if (patient.getId() == id) {
                return patient;
            }
        }

        return null;
    }
}
