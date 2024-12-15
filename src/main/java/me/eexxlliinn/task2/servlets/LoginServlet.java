package me.eexxlliinn.task2.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import me.eexxlliinn.task2.dtos.CartResponse;
import me.eexxlliinn.task2.entities.UserEntity;
import me.eexxlliinn.task2.services.CartService;
import me.eexxlliinn.task2.services.UserService;
import me.eexxlliinn.task2.services.impl.CartServiceImpl;
import me.eexxlliinn.task2.services.impl.UserServiceImpl;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final CartService cartService = new CartServiceImpl();
    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        try {
            UserEntity user = userService.login(username, password);
            if (user == null) {
                request.getRequestDispatcher("/login.jsp").forward(request, response);
                throw new RuntimeException("Invalid username or password");
            }
            CartResponse cartResponse = cartService.getActiveCart(user.getId());
            HttpSession session = request.getSession();
            session.setAttribute("cartId", cartResponse.getCartId());
            session.setAttribute("userId", user.getId());
            response.sendRedirect("/products/all");
        } catch (Exception e) {
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }

    }
}
