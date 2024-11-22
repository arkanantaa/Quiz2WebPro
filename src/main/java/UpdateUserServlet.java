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


@WebServlet("/UpdateUserServlet")
public class UpdateUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Storify", "root", "")) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Users WHERE UserId = ?");
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                request.setAttribute("user", rs);
                RequestDispatcher rd = request.getRequestDispatcher("UpdateUser.jsp");
                rd.forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        String userName = request.getParameter("userName");
        String userFullName = request.getParameter("userFullName");
        String userEmail = request.getParameter("userEmail");
        String userAddress = request.getParameter("userAddress");

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Storify", "root", "")) {
            PreparedStatement ps = conn.prepareStatement("UPDATE Users SET UserName = ?, UserFullName = ?, UserEmail = ?, UserAddress = ? WHERE UserId = ?");
            ps.setString(1, userName);
            ps.setString(2, userFullName);
            ps.setString(3, userEmail);
            ps.setString(4, userAddress);
            ps.setInt(5, userId);

            int result = ps.executeUpdate();
            if (result > 0) {
                response.sendRedirect("Profile.jsp"); // Mengarahkan ke halaman profil setelah update
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
