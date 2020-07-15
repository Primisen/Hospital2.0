package training.nadia.hospital.controller.doctor;

import training.nadia.hospital.entity.Doctor;
import training.nadia.hospital.entity.TreatmentType;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/reception")
public class ReceptionServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Doctor doctor = (Doctor) request.getSession().getAttribute("user");
        request.setAttribute("patients", doctor.getPatientsToReceive());

//        request.setAttribute("treatmentType", );

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/page/doctor/patientReception.jsp");
        requestDispatcher.forward(request, response);
    }
}
