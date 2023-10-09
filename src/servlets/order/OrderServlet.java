package servlets.order;

import domain.category.Category;
import domain.category.CategoryDataBase;
import domain.order.Order;
import domain.order.OrderDataBase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("current_user") == null)
            resp.sendRedirect("/login?restricted=true");

        Category categories = CategoryDataBase.getCategoryById(1L);
        List<Order> orders = OrderDataBase.getAllOrders();

        req.setAttribute("categories", categories);
        req.setAttribute("orders", orders);

        req.getRequestDispatcher("/order.jsp").forward(req,resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("is_admin") != null &&
                (Boolean) req.getSession().getAttribute("is_admin")){

            String title = req.getParameter("title");
            String description = req.getParameter("description");
            Double price = Double.valueOf(req.getParameter("price"));
            Long authorId = Long.valueOf(req.getParameter("authorId"));
            Long categoryId = Long.valueOf(req.getParameter("categoryId"));

            OrderDataBase.addNewOrder(title,description,price,authorId,categoryId);

            req.getRequestDispatcher("/order.jsp").forward(req,resp);
        }
    }
}
