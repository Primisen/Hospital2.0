package training.nadia.hospital.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class MainServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

//        request.getSession().setAttribute(null);
//        request.getSession().removeAttribute("user");
        request.getSession().invalidate();
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
        requestDispatcher.forward(request, response);
    }
//
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

//        request.getSession().getAttribute(null);
//        request.getSession().invalidate();

//        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/page/index.jsp");
//        requestDispatcher.forward(request, response);
    }
}
