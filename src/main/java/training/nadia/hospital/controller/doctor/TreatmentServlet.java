package training.nadia.hospital.controller.doctor;

import training.nadia.hospital.entity.Doctor;

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

        Doctor doctor = (Doctor) request.getSession().getAttribute("user");
        request.setAttribute("patients", doctor.getPatientsToCure());

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/page/doctor/patientTreatment.jsp");
        requestDispatcher.forward(request, response);
    }
}
