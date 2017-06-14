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
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by zhenya.1291813139.com
 * on 2017/6/14.
 * Java EE_1702.
 */
@WebServlet(urlPatterns = "/ip")
public class IpAction extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ip = req.getParameter("ip").trim();

        if (ip.length() == 0) {
            ip = req.getRemoteAddr();
        }
        req.getSession().setAttribute("geo", getGeo(ip));
        req.getRequestDispatcher("ip.jsp").forward(req, resp);
    }

    public static String getGeo(String ip) {
        Connection connection = Db.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


        String sql = "SELECT geo\n" +
                "FROM db_1712.ip\n" +
                "WHERE inet_aton(?) BETWEEN inet_aton(min) AND inet_aton(max)";

        try {
            if (connection != null) {
                preparedStatement = connection.prepareStatement(sql);
            } else {
//                Error.showErrorMessage(req, resp);
                return null;
            }
            preparedStatement.setString(1, ip);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            return resultSet.getString("geo");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Db.close(resultSet, preparedStatement, (com.mysql.jdbc.Connection) connection);
        }
        return null;
    }
}

