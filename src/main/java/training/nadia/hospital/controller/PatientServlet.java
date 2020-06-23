package training.nadia.hospital.controller;

import training.nadia.hospital.entity.Doctor;
import training.nadia.hospital.service.impl.DoctorServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/patient")
public class PatientServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
        request.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(request.getParameter("doctorId"));



        //записать id пользователю;
//        String s = request.getParameter("doctorId");
//        System.out.println(s);
//
//        UserServiceImpl userService = new UserServiceImpl();
//        User us = userService.findById(Integer.parseInt(s));
//
//        System.out.println(us.getName() + " " + us.getSurname() + " " + us.getLogin());
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DoctorServiceImpl doctorService = new DoctorServiceImpl();
        List<Doctor> doctors = doctorService.getAllDoctors();

        request.setAttribute("doctors", doctors);

        RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher("/patient.jsp");

        dispatcher.forward(request, response);
    }
}
