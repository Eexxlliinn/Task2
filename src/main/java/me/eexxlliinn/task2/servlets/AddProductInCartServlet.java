package me.eexxlliinn.task2.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import me.eexxlliinn.task2.services.CartService;
import me.eexxlliinn.task2.services.impl.CartServiceImpl;

import java.io.IOException;

@WebServlet("/cart/add")
public class AddProductInCartServlet extends HttpServlet {

    private final CartService cartService = new CartServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long cartId = Long.parseLong(request.getParameter("cartId"));
        long productId = Long.parseLong(request.getParameter("productId"));
        int amount = Integer.parseInt(request.getParameter("amount"));
        cartService.addItemToCart(cartId, productId, amount);
        response.sendRedirect("/cart/details?cartId=" + cartId);
    }
}
