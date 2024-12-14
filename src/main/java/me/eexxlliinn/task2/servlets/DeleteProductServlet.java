package me.eexxlliinn.task2.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import me.eexxlliinn.task2.services.ProductService;
import me.eexxlliinn.task2.services.impl.ProductServiceImpl;

import java.io.IOException;

@WebServlet("/products/delete")
public class DeleteProductServlet extends HttpServlet {

    private final ProductService productService = new ProductServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long productId = Long.parseLong(request.getParameter("id"));
        productService.deleteProduct(productId);
        response.sendRedirect("/products/all");
    }

}
