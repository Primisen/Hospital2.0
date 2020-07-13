package training.nadia.hospital.controller.patient;

import org.apache.log4j.Logger;
import training.nadia.hospital.entity.Doctor;
import training.nadia.hospital.entity.Patient;
import training.nadia.hospital.service.PatientService;
import training.nadia.hospital.service.exception.ServiceException;
import training.nadia.hospital.service.impl.DoctorServiceImpl;
import training.nadia.hospital.service.impl.PatientServiceImpl;

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
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//        DoctorServiceImpl doctorService = new DoctorServiceImpl();
//        List<Doctor> doctors = null;
//
//        try {
//            doctors = doctorService.getAllDoctors();
//
//        } catch (ServiceException e) {
//            logger.error(e.getMessage());
//        }
//
//        request.setAttribute("doctors", doctors);

        request.setCharacterEncoding("UTF-8");

        String parameter = request.getParameter("doctorId");

        if (parameter != null) {

            long doctorId = Integer.parseInt(parameter);

            Patient patient = (Patient) request.getSession().getAttribute("user");

            PatientServiceImpl patientService = new PatientServiceImpl();
            patientService.goToTheDoctor(patient.getId(), doctorId);

            request.getSession().setAttribute("user", patient);

            response.sendRedirect("/patient");

        } else {

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/page/patient/goToDoctor.jsp");
            requestDispatcher.forward(request, response);
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DoctorServiceImpl doctorService = new DoctorServiceImpl();
        List<Doctor> doctors = null;
        try {
            doctors = doctorService.getAllDoctors();
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        request.setAttribute("doctors", doctors);

        request.setCharacterEncoding("UTF-8");

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/page/patient/goToDoctor.jsp");
        requestDispatcher.forward(request, response);
    }
}
