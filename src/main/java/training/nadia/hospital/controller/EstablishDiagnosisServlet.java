package training.nadia.hospital.controller;

import training.nadia.hospital.entity.Doctor;
import training.nadia.hospital.entity.Patient;
import training.nadia.hospital.entity.Treatment;
import training.nadia.hospital.entity.TreatmentType;
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
import java.util.List;

@WebServlet("/reception")//изменить
public class EstablishDiagnosisServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter("ok") != null) {

            Doctor doctor = (Doctor) request.getSession().getAttribute("user");
            Patient patient = findPatientById(doctor.getPatientsToReceive(), Long.parseLong(request.getParameter("patientId")));

            String diagnosis = request.getParameter("diagnosis");

            Treatment treatment = new Treatment();
            treatment.setType(TreatmentType.valueOf(request.getParameter("treatmentTypeValue")));
            treatment.setNumberOfTherapies(Integer.parseInt(request.getParameter("numberOfProcedures")));

            DoctorService doctorService = new DoctorServiceImpl();
            try {
                doctorService.setDiagnosisAndTreatment(diagnosis, treatment, patient, doctor);
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }

        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            Doctor doctor = (Doctor) request.getSession().getAttribute("user");

            DoctorService doctorService = new DoctorServiceImpl();
            doctorService.getReceivingPatients(doctor);

            request.setAttribute("patients", doctor.getPatientsToReceive());
            request.setAttribute("treatmentType", TreatmentType.values());

        } catch (ServiceException e) {
            e.printStackTrace();
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/page/patientReception.jsp");
        requestDispatcher.forward(request, response);
    }

    private Patient findPatientById(List<Patient> patients, long id) {

        for (Patient patient : patients) {
            if (patient.getId() == id) {
                return patient;
            }
        }

        return null;
    }
}
