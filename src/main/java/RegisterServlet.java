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

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public RegisterServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PrintWriter output = response.getWriter();
            response.setContentType("text/html");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Storify", "root", "");

            String email = request.getParameter("txtEmail");
            String user = request.getParameter("txtUsername");
            String pass = request.getParameter("txtPassword");
            String dob = request.getParameter("txtDOB");
            String fullName = request.getParameter("txtFullName");
            String address = request.getParameter("txtAddress");

            PreparedStatement psCheck = conn.prepareStatement("SELECT UserName FROM Users WHERE UserName = ? OR UserEmail = ?");
            psCheck.setString(1, user);
            psCheck.setString(2, email);
            ResultSet rsCheck = psCheck.executeQuery();

            if (rsCheck.next()) {
                output.println("<script type='text/javascript'>");
                output.println("alert('Username or Email already exists!');");
                output.println("location='register.jsp';");
                output.println("</script>");
            } else {
                PreparedStatement ps = conn.prepareStatement("INSERT INTO Users (UserEmail, UserName, UserPassword, UserDOB, UserFullName, UserAddress) VALUES (?, ?, ?, ?, ?, ?)");
                ps.setString(1, email);
                ps.setString(2, user);
                ps.setString(3, pass);
                ps.setString(4, dob);
                ps.setString(5, fullName);
                ps.setString(6, address);
                int result = ps.executeUpdate();

                if (result > 0) {
                    output.println("<script type='text/javascript'>");
                    output.println("alert('Registration successful!');");
                    output.println("location='login.jsp';");
                    output.println("</script>");
                } else {
                    output.println("<script type='text/javascript'>");
                    output.println("alert('Registration failed!');");
                    output.println("location='register.jsp';");
                    output.println("</script>");
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }
}
