package training.nadia.hospital.controller.patient;

import training.nadia.hospital.entity.Patient;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/patient")
public class PatientServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Patient patient = (Patient) request.getSession().getAttribute("user");
        request.setAttribute("patient", patient);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/page/patient/patient.jsp");
        requestDispatcher.forward(request, response);
    }
}
