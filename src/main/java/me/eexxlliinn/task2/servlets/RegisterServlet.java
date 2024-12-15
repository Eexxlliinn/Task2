package me.eexxlliinn.task2.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import me.eexxlliinn.task2.dtos.UserRequest;
import me.eexxlliinn.task2.services.UserService;
import me.eexxlliinn.task2.services.impl.UserServiceImpl;

import java.io.IOException;

@WebServlet("/register")
@Slf4j
public class RegisterServlet extends HttpServlet {

    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername(request.getParameter("username"));
        userRequest.setPassword(request.getParameter("password"));
        userRequest.setEmail(request.getParameter("email"));
        log.info("Registering user: {} {} {}", userRequest.getUsername(), userRequest.getPassword(), userRequest.getEmail());
        try {
            userService.register(userRequest);
            log.info("Registered user: {}", userRequest.getUsername());
            response.sendRedirect("/login");
        } catch (Exception e) {
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }
    }
}
