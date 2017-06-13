package demo.servlet;

import demo.model.Student;
import demo.util.Db;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
        if ("queryAll".equals(action)) {
            queryAll(req, resp);
            return;
        }

        if ("queryById".equals(action)) {
            queryById(req, resp);
            return;
        }

        if ("modify".equals(action)) {
            modify(req, resp);
            return;
        }

        if ("remove".equals(action)) {
            remove(req, resp);
            return;
        }

        req.setAttribute("message", "出了一点问题哦");
        req.getRequestDispatcher("index.sql").forward(req,resp);
    }


    private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String gender = req.getParameter("gender");
        String dob = req.getParameter("dob");

        Connection connection = Db.getConnection();
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO db_javaee.student VALUE (NULL ,?,?,?)";

        try {
            if (connection != null) {
                preparedStatement = connection.prepareStatement(sql);
            }else {
                req.setAttribute("message", "It's a error...");
                req.getRequestDispatcher("index.jsp").forward(req, resp);
                return;
            }

            preparedStatement.setString(1,name);
            preparedStatement.setString(2,gender);
            preparedStatement.setString(3,dob);

            preparedStatement.executeUpdate();

            //TODO ???
            resp.sendRedirect("student?action=queryAll");//???
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Db.close(null,preparedStatement, (com.mysql.jdbc.Connection) connection);
        }
    }

    private void queryAll(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        Connection connection = Db.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String sql = "";
        try{
            if (connection != null) {
                preparedStatement = connection.prepareStatement(sql);
            }else {
                req.setAttribute("message", "It's a error...");
                req.getRequestDispatcher("index.jsp").forward(req, resp);
                return;
            }
            resultSet = preparedStatement.executeQuery();
            List<Student> students = new ArrayList<>();
            while(resultSet.next()){
                Student student = new Student(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("gender"),
                        resultSet.getString("dob"));
                students.add(student);
            }
            req.getSession().setAttribute("students", students); // ?
            resp.sendRedirect("index.jsp");
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Db.close(resultSet, preparedStatement, (com.mysql.jdbc.Connection) connection); // ?
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
