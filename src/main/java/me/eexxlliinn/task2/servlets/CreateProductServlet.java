package me.eexxlliinn.task2.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import me.eexxlliinn.task2.dtos.ProductRequest;
import me.eexxlliinn.task2.mappers.ProductMapper;
import me.eexxlliinn.task2.services.ProductService;
import me.eexxlliinn.task2.services.impl.ProductServiceImpl;

import java.io.IOException;

@WebServlet("/products/create")
public class CreateProductServlet extends HttpServlet {

    private final ProductService productService = new ProductServiceImpl();
    private final ProductMapper productMapper = new ProductMapper();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/product-create.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductRequest productRequest = productMapper.toProductRequest(request);
        productService.createProduct(productRequest);
        response.sendRedirect("/products/all");
    }
}
