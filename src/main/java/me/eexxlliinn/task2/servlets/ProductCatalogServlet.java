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
import java.util.List;

@WebServlet("/products/all")
public class ProductCatalogServlet extends HttpServlet {

    private final ProductService productService = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ProductResponse> productResponses = productService.getAllProducts();
        request.setAttribute("products", productResponses);
        request.getRequestDispatcher("/product-list.jsp").forward(request, response);
    }

}
