package servlets.users;

import domain.order.OrderDataBase;
import domain.users.Users;
import domain.users.UsersDataBase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");

        Users user = UsersDataBase.getUserByEmail(email);
        if (user == null){
            resp.sendRedirect("/login?errorMessage=true");
            return;
        }

        HttpSession session = req.getSession();
        session.setAttribute("current_user", user);
        session.setAttribute("is_admin", user.getRole().getId() == 8L);

        resp.sendRedirect("/order");

    }
}
