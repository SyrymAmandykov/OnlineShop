package servlets.users;

import domain.users.Users;
import domain.users.UsersDataBase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/auth/registration")
public class RegistrationUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/registration_new_user.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String fullName = req.getParameter("fullName");
        Integer phoneNumber = Integer.valueOf(req.getParameter("phoneNumber"));

        UsersDataBase.addNewUser(email, password, fullName, phoneNumber, 8L);
        resp.sendRedirect("/login?afterRegistration=true");
    }
}
