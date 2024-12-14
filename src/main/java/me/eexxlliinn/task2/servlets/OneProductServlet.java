package me.eexxlliinn.task2.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import me.eexxlliinn.task2.dtos.ProductResponse;
import me.eexxlliinn.task2.services.ProductService;
import me.eexxlliinn.task2.services.impl.ProductServiceImpl;

import java.io.IOException;

@WebServlet("/products/show")
public class OneProductServlet extends HttpServlet {

    private final ProductService productService = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long productId = Long.parseLong(request.getParameter("id"));
        ProductResponse productResponse = productService.getProductById(productId);
        request.setAttribute("product", productResponse);
        request.getRequestDispatcher("/product-details.jsp").forward(request, response);
    }
}
