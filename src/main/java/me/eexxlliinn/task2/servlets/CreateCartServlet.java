package me.eexxlliinn.task2.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import me.eexxlliinn.task2.dtos.CartRequest;
import me.eexxlliinn.task2.dtos.CartResponse;
import me.eexxlliinn.task2.mappers.CartMapper;
import me.eexxlliinn.task2.services.CartService;
import me.eexxlliinn.task2.services.impl.CartServiceImpl;

import java.io.IOException;

@WebServlet("/cart/create")
public class CreateCartServlet extends HttpServlet {

    private final CartService cartService = new CartServiceImpl();
    private final CartMapper cartMapper = new CartMapper();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/create-cart.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CartRequest cartRequest = cartMapper.toDto(request);
        CartResponse cartResponse = cartService.createCart(cartRequest);
        response.sendRedirect("/cart/details?cartId=" + cartResponse.getCartId());
    }
}
