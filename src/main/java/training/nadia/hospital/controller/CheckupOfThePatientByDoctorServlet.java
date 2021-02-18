package training.nadia.hospital.controller;

import org.apache.log4j.Logger;
import training.nadia.hospital.entity.Doctor;
import training.nadia.hospital.entity.Patient;
import training.nadia.hospital.entity.Treatment;
import training.nadia.hospital.entity.TreatmentType;
import training.nadia.hospital.service.DoctorService;
import training.nadia.hospital.exception.ServiceException;
import training.nadia.hospital.service.impl.DoctorServiceImpl;
import training.nadia.hospital.util.Utility;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/checkup")
public class CheckupOfThePatientByDoctorServlet extends HttpServlet {

    private Logger logger = Logger.getRootLogger();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter("submit_button") != null) {

            Doctor doctor = (Doctor) request.getSession().getAttribute("user");
            Patient patient = Utility.findPatientById(doctor.getPatientsWhoNeedToBeCheckup(), Long.parseLong(request.getParameter("patientId")));

            String diagnosis = request.getParameter("diagnosis");

            Treatment treatment = new Treatment();
            treatment.setType(TreatmentType.valueOf(request.getParameter("treatmentTypeValue")));
            treatment.setNumberOfTherapies(Integer.parseInt(request.getParameter("numberOfProcedures")));

            DoctorService doctorService = new DoctorServiceImpl();
            try {
                doctorService.setDiagnosisAndTreatment(diagnosis, treatment, patient, doctor);

//                HttpSession session = request.getSession();//?
//                session.setAttribute("user", doctor);

            } catch (ServiceException e) {
                logger.error(e.getMessage());
            }
        }

//        request.getSession().removeAttribute("user");
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            Doctor doctor = (Doctor) request.getSession().getAttribute("user");

            DoctorService doctorService = new DoctorServiceImpl();
            doctorService.identifyPatientsWhoNeedToBeCheckup(doctor);

            request.setAttribute("patients", doctor.getPatientsWhoNeedToBeCheckup());
            request.setAttribute("treatmentType", TreatmentType.values());

        } catch (ServiceException e) {
            logger.error(e.getMessage());
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/page/patientCheckup.jsp");//!разобраться
        requestDispatcher.forward(request, response);
    }
}
