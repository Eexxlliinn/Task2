package me.eexxlliinn.task2.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import me.eexxlliinn.task2.dtos.CartResponse;
import me.eexxlliinn.task2.services.CartService;
import me.eexxlliinn.task2.services.impl.CartServiceImpl;

import java.io.IOException;

@WebServlet("/cart/details")
public class CartDetailsServlet extends HttpServlet {

    private final CartService cartService = new CartServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long cartId = Long.parseLong(request.getParameter("cartId"));
        CartResponse cartResponse = cartService.getCartById(cartId);
        request.setAttribute("cart", cartResponse);
        request.getRequestDispatcher("/cart-details.jsp").forward(request, response);
    }
}
