package demo.servlet;

import demo.model.Student;
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
            add(req, resp);
            return;
        }
        if ("queryAll".equals(action)) {
            try {
                queryAll(req, resp);
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
        if ("batchRemove".equals(action)) {
            batchRemove(req, resp);
            return;
        }

        req.setAttribute("message", "出了一点问题哦");
        req.getRequestDispatcher("default.sql").forward(req, resp);
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
            } else {
                req.setAttribute("message", "Error...");
                return;
            }

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, gender);
            preparedStatement.setString(3, dob);

            preparedStatement.executeUpdate();

            //TODO ???
            resp.sendRedirect("student?action=queryAll");//???不跳回index.jsp页面，而是发起一个新的请求，queryAll查询到最新的结果后再回到index.jsp页面
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Db.close(null, preparedStatement, (com.mysql.jdbc.Connection) connection);
        }
    }

    private void queryAll(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        Connection connection = Db.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String sql = "SELECT * FROM db_javaee.student ORDER BY id";
        try {
            if (connection != null) {
                preparedStatement = connection.prepareStatement(sql);
            } else {
                req.setAttribute("message", "Error...");
                req.getRequestDispatcher("index.jsp").forward(req, resp);
                return;
            }
            resultSet = preparedStatement.executeQuery();// 结果集在jsp页面中显示不出来  module类
            List<Student> students = new ArrayList<>();// ???  利用集合类
            while (resultSet.next()) {
                Student student = new Student(// 做一module类People的实例对象，调用有参构造方法
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("gender"),
                        resultSet.getString("dob"));
                students.add(student);// 存入集合类
            }
            req.getSession().setAttribute("students", students); // 在session属性范围范围内设置属性，value=students
            resp.sendRedirect("index.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Db.close(resultSet, preparedStatement, (com.mysql.jdbc.Connection) connection); // ?
        }
    }

    private void queryById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));

        Connection connection = Db.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String sql = "SELECT * FROM db_javaee.student WHERE id = ?";

        try {
            if (connection != null) {
                preparedStatement = connection.prepareStatement(sql);
            } else {
                Error.showErrorMessage(req, resp);
                return;
            }
            preparedStatement.setInt(1, id);//设置未定义参数
            resultSet = preparedStatement.executeQuery();// 执行查询操作
            resultSet.next();// 直接定位到第一条记录
            Student student = new Student(// 将这条记录封装为模型类的实例(参数为取得的所有列)
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("gender"),
                    resultSet.getString("dob"));
            req.getSession().setAttribute("student", student); // 只有一条数据(一个学生的一条数据)（value=student）
            resp.sendRedirect("edit.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Db.close(resultSet, preparedStatement, (com.mysql.jdbc.Connection) connection); // ?
        }
    }

    private void modify(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String gender = req.getParameter("gender");
        String dob = req.getParameter("dob");

        Connection connection = Db.getConnection();
        PreparedStatement preparedStatement = null;

        String sql = "UPDATE db_javaee.student SET name = ?, gender = ?, dob = ? WHERE id = ?";

        try {
            if (connection != null) {
                preparedStatement = connection.prepareStatement(sql);
            } else {
                Error.showErrorMessage(req, resp);
                return;
            }
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, gender);
            preparedStatement.setString(3, dob);
            preparedStatement.setInt(4, id);

            preparedStatement.executeUpdate();

            resp.sendRedirect("student?action=queryAll");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Db.close(null, preparedStatement, (com.mysql.jdbc.Connection) connection);
        }
    }

    private void removeById(int id, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = Db.getConnection();
        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM db_javaee.student WHERE id = ?";

        try{
            if(connection != null){
                    preparedStatement = connection.prepareStatement(sql);
                }else{
                   Error.showErrorMessage(req, resp);
                    return;
                }
                preparedStatement.setInt(1,id);
                preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Db.close(null,preparedStatement, (com.mysql.jdbc.Connection) connection);
        }

    }

    private void remove(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));

        removeById(id,req,resp);
        resp.sendRedirect("student?action=quryAll");

    }


    private void batchRemove(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] ids = req.getParameterValues("ids");
        if (ids == null) {
            req.setAttribute("message","有问题");
            req.getRequestDispatcher("index.jsp").forward(req,resp);
            return;
        }
        for (String idString : ids) {
            int id = Integer.parseInt(idString);
            removeById(id,req,resp);
        }
        resp.sendRedirect("student?action=quryAll");
    }

//    private boolean isConnected(Connection connection, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        if(connection == null){
//
//        req.setAttribute("message", "Error...");
//        req.getRequestDispatcher("default.jsp").forward(req, resp);
//        return false;
//        }
//        return true;
//    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
