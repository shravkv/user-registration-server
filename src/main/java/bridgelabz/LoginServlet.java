package bridgelabz;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

@WebServlet(
        description = "Login Servlet testing",
        urlPatterns = {"/LoginServlet"},
        initParams = {
                @WebInitParam(name = "user", value = "Sravank"),
                @WebInitParam(name = "password", value = "123456")
        }
)

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("user");
        String pwd = request.getParameter("pwd");
        String userId = getServletConfig().getInitParameter("user");
        String password = getServletConfig().getInitParameter("password");
        if (Pattern.matches("[A-Z][a-z]{2,}", userId) &&
                Pattern.matches("(?=.*?[A-Z])(?=.*?\\d)(?=.*?[a-z])(?=.*?[!@#$%^&*_()+-])[A-Za-z\\d!@#$%^&()*+_-]{8,}", password) &&
                (userId.equals(user) && password.equals(pwd))) {
            request.setAttribute("user", user);
            request.getRequestDispatcher("LoginSuccess.jsp").forward(request, response);
        } else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out = response.getWriter();
            out.println("<font color = red>Either username or password are wrong.<font>");
            rd.include(request, response);
        }
    }
}
