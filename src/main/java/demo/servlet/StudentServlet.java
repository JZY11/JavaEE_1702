package demo.servlet;

import demo.util.Db;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by zhenya.1291813139.com
 * on 2017/6/12.
 * Java EE_1702.
 */
@WebServlet(urlPatterns = "/student")
public class StudentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("add".equals(action)) {
            add(req,resp);
            return;
        }

        req.setAttribute("message", "出了一点问题哦");
        req.getRequestDispatcher("index.sql").forward(req,resp);
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        String gender = req.getParameter("gender");
        String dob = req.getParameter("dob");

        Connection connection = Db.getConnection();
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO db_javaee.student VALUE (NULL ,?,?,?)";

        try {
            if (connection != null) {
                preparedStatement = connection.prepareStatement(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
