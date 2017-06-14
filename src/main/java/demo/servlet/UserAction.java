package demo.servlet;

import demo.util.Db;
import demo.util.Error;

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

/**
 * Created by zhenya.1291813139.com
 * on 2017/6/12.
 * Java EE_1702.
 */
@WebServlet(urlPatterns = "/user")
public class UserAction extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("register".equals(action)) { // action.equals NPE
            register(req, resp);
            return;
        }
        if ("login".equals(action)) {
            login(req, resp);
            return;
        }
        if ("logout".equals(action)) {
            logout(req, resp);
            return;
        }

        req.setAttribute("message", "出现了一点问题。。。");
        req.getRequestDispatcher("default.jsp").forward(req, resp);
    }


    private void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nick = req.getParameter("nick").trim();// 获取表单信息
        String mobile = req.getParameter("mobile").trim();
        String password = req.getParameter("password");

        if (nick.length() == 0 || mobile.length() == 0 || password.length() == 0) {
            req.setAttribute("message", "....");
            req.getRequestDispatcher("signup.jsp").forward(req, resp);
        }

//        String[] hobbies = req.getParameterValues("hobbies");
//        String[] cities = req.getParameterValues("cities");

        Connection connection = Db.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sqlNick = "SELECT * FROM db_javaee.user WHERE nick = ?";
            if (connection != null) {
                statement = connection.prepareStatement(sqlNick);
            } else {
                Error.showErrorMessage(req,resp);
                return;
            }
            statement.setString(1, nick);
            resultSet = statement.executeQuery();
            boolean isNickExist = resultSet.next();

            String sqlMobile = "SELECT * FROM db_javaee.user WHERE mobile = ?";
            statement = connection.prepareStatement(sqlMobile);
            statement.setString(1, mobile);
            resultSet = statement.executeQuery();
            boolean isMobileExist = resultSet.next();

            if (isNickExist) {
                req.setAttribute("message", "昵称已经存在");
                req.getRequestDispatcher("signup.jsp").forward(req, resp);
            } else if (isMobileExist) {
                req.setAttribute("message", "手机号已经存在");
                req.getRequestDispatcher("signup.jsp").forward(req, resp);
            } else {
                String sql = "INSERT INTO db_javaee.user VALUE (NULL ,?,?,?)";
                statement = connection.prepareStatement(sql);
                statement.setString(1, nick);
                statement.setString(2, mobile);
                statement.setString(3, password);
//                statement.setString(4, Arrays.toString(hobbies));
//                statement.setString(5, Arrays.toString(cities));
                statement.executeUpdate();
                resp.sendRedirect("default.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Db.close(resultSet, statement, (com.mysql.jdbc.Connection) connection);
        }
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String mobile = req.getParameter("mobile");
        String password = req.getParameter("password");

        Connection connection = Db.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "SELECT * FROM db_javaee.user WHERE mobile=? AND password=?";
            if (connection != null) {
                statement = connection.prepareStatement(sql);
            } else {
                Error.showErrorMessage(req,resp);
                return;
            }
            statement.setString(1, mobile);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                req.getSession().setAttribute("nick", resultSet.getString("nick"));
                //TODO ???
                resp.sendRedirect("student?action=queryAll");// ??
            } else {
                req.setAttribute("message", "手机号或密码错误");
                req.getRequestDispatcher("default.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Db.close(resultSet, statement, (com.mysql.jdbc.Connection) connection);
        }
    }


    private void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.getSession().invalidate();
        resp.sendRedirect("default.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
