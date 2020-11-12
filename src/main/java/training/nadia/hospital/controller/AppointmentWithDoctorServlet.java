package training.nadia.hospital.controller;

import org.apache.log4j.Logger;
import training.nadia.hospital.entity.Doctor;
import training.nadia.hospital.entity.Patient;
import training.nadia.hospital.service.PatientService;
import training.nadia.hospital.service.UtilService;
import training.nadia.hospital.service.exception.ServiceException;
import training.nadia.hospital.service.impl.PatientServiceImpl;
import training.nadia.hospital.service.impl.UtilServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/appointment-with-doctor")
public class AppointmentWithDoctorServlet extends HttpServlet {

    private Logger logger = Logger.getRootLogger();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String doctorId = request.getParameter("doctorId");

        if (doctorId != null) {

            Patient patient = (Patient) request.getSession().getAttribute("user");

            Doctor doctor = new Doctor();
            doctor.setId(Integer.parseInt(doctorId));
            doctor.setName(request.getParameter("doctorName"));
            doctor.setSurname(request.getParameter("doctorSurname"));

            patient.setReceptionDoctor(doctor);
//            request.getSession().setAttribute("user", patient);

            try {
                PatientService patientService = new PatientServiceImpl();
                patientService.goToTheDoctor(patient, doctor);

            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }

        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            UtilService utilService = new UtilServiceImpl();
            List<Doctor> doctors = utilService.getAllDoctors();
            request.setAttribute("doctors", doctors);

        } catch (ServiceException e) {
            e.printStackTrace();
        }

        Patient patient = (Patient) request.getSession().getAttribute("user");
        request.setAttribute("patient", patient);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/page/patient/goToDoctor.jsp");
        dispatcher.forward(request, response);
    }
}
