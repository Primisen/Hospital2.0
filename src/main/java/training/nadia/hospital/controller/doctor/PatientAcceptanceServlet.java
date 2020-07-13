package training.nadia.hospital.controller.doctor;

import training.nadia.hospital.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PatientAcceptanceServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        System.out.println(request.getParameter("patient"));

        doGet(request, response);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("user");
        long id = user.getId();

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(request.getPathInfo());
        requestDispatcher.forward(request, response);
    }
}
