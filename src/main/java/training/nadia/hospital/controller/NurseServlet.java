package training.nadia.hospital.controller;

import org.apache.log4j.Logger;
import training.nadia.hospital.entity.Nurse;
import training.nadia.hospital.exception.ServiceException;
import training.nadia.hospital.service.NurseService;
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

    private Logger logger = Logger.getRootLogger();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Nurse nurse = (Nurse) request.getSession().getAttribute("user");

        if (request.getParameter("patientProcedureKey") != null) {

            long patientId = Integer.parseInt(request.getParameter("patientProcedureKey"));

            NurseServiceImpl nurseService = new NurseServiceImpl();
            try {
                nurseService.performTheTherapy(nurse, patientId);
            } catch (ServiceException e) {
                logger.error(e.getMessage());
            }
        }

        doGet(request, response);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Nurse nurse = (Nurse) request.getSession().getAttribute("user");

        NurseService nurseService = new NurseServiceImpl();
        try {
            nurseService.identifyNursePatients(nurse);
        } catch (ServiceException e) {
            logger.error(e.getMessage());
        }

        request.setAttribute("nurse", nurse);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/page/nurse.jsp");
        requestDispatcher.forward(request, response);
    }
}
