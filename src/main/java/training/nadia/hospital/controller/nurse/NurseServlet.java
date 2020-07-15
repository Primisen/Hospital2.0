package training.nadia.hospital.controller.nurse;

import training.nadia.hospital.entity.Nurse;
import training.nadia.hospital.service.impl.NurseServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/nurse")
public class NurseServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Nurse nurse = (Nurse) request.getSession().getAttribute("user");

        if (request.getParameter("patientProcedureKey") != null) {

            long patientId = Integer.parseInt(request.getParameter("patientProcedureKey"));

            NurseServiceImpl nurseService = new NurseServiceImpl();
            nurseService.performTheProcedure(nurse, patientId);
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Nurse nurse = (Nurse) request.getSession().getAttribute("user");

        request.setAttribute("nurse", nurse);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/page/nurse/nurse.jsp");
        requestDispatcher.forward(request, response);
    }
}
