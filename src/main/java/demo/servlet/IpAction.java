package demo.servlet;

import demo.util.Db;

import javax.naming.ldap.PagedResultsControl;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.DataBuffer;
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
public class IpAction extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ip = req.getParameter("ip").trim();

        Connection connection = Db.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String sql = "SELECT geo\n" +
                "FROM db_1712.ip\n" +
                "WHERE inet_aton(?) BETWEEN inet_aton(min) AND inet_aton(max)";

        try {
            if (connection != null) {
                preparedStatement = connection.prepareStatement(sql);
            }else {
                Error.showErrorMessage(req,resp);
                return;
            }
            preparedStatement.setString(1,ip);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            String geo = resultSet.getString("geo");
            req.getSession().setAttribute("geo",geo);
            resp.sendRedirect("ip.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Db.close(resultSet,preparedStatement,connection);
        }
    }
}
