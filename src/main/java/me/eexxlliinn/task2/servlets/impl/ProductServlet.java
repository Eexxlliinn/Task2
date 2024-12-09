package me.eexxlliinn.task2.servlets.impl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import me.eexxlliinn.task2.dtos.ProductRequest;
import me.eexxlliinn.task2.dtos.ProductResponse;
import me.eexxlliinn.task2.mappers.ProductMapper;
import me.eexxlliinn.task2.services.ProductService;
import me.eexxlliinn.task2.servlets.ProductOperations;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@WebServlet("/products")
@RequiredArgsConstructor
public class ProductServlet extends HttpServlet implements ProductOperations {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");
        try {
            switch (action) {
                case "showProduct":
                    showProduct(request, response);
                    break;
                case "showAllProducts":
                    showAllProducts(request, response);
                    break;
            }
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");
        try {
            switch (action) {
                case "createProduct":
                    createProduct(request, response);
                    break;
                case "updateProduct":
                    updateProduct(request, response);
                    break;
                case "deleteProduct":
                    deleteProduct(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, action);
            }
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    private void showProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UUID productId = UUID.fromString(request.getParameter("id"));
        ProductResponse productResponse = productService.getProductById(productId);
        request.setAttribute("product", productResponse);
        request.getRequestDispatcher("/product-details.jsp").forward(request, response);
    }

    private void showAllProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ProductResponse> productResponses = productService.getAllProducts();
        request.setAttribute("products", productResponses);
        request.getRequestDispatcher("/product-list.jsp").forward(request, response);
    }

    private void createProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductRequest productRequest = productMapper.toProductRequest(request);
        productService.createProduct(productRequest);
        response.sendRedirect("/products");
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UUID productId = UUID.fromString(request.getParameter("id"));
        ProductRequest productRequest = productMapper.toProductRequest(request);
        productService.updateProduct(productId, productRequest);
        response.sendRedirect("/products");
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UUID productId = UUID.fromString(request.getParameter("id"));
        productService.deleteProduct(productId);
        response.sendRedirect("/products");
    }
}
