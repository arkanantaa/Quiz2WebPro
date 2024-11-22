import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/UpdateProductServlet")
public class UpdateProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));
        
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Storify", "root", "")) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Products WHERE ProductId = ?");
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                request.setAttribute("product", rs);
                RequestDispatcher rd = request.getRequestDispatcher("UpdateProduct.jsp");
                rd.forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));
        String productName = request.getParameter("productName");
        int productQuantity = Integer.parseInt(request.getParameter("productQuantity"));
        double productPrice = Double.parseDouble(request.getParameter("productPrice"));

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Storify", "root", "")) {
            PreparedStatement ps = conn.prepareStatement("UPDATE Products SET ProductName = ?, ProductQuantity = ?, ProductPrice = ? WHERE ProductId = ?");
            ps.setString(1, productName);
            ps.setInt(2, productQuantity);
            ps.setDouble(3, productPrice);
            ps.setInt(4, productId);

            int result = ps.executeUpdate();
            if (result > 0) {
                response.sendRedirect("ProductList.jsp"); // Mengarahkan ke daftar produk setelah update
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
