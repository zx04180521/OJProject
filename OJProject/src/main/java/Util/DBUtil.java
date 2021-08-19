package Util;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {
    //连接数据库的URL
    public static String URL = "jdbc:mysql://localhost:3306/ojProject?characterEncoding=utf8&useSSL=true";
    //数据库的用户名
    public static String USER = "root";
    //数据库的密码
    public static String PASSWORD = "123456";
    //数据库连接池对象
    public static volatile MysqlDataSource dataSource = null;
    //创建数据库连接对象的方法
    // 在这个方法中，使用的双重判断加上synchronize锁的单例模式
    // 能够保证在多线程下只创建出一个数据库连接池对象
    public static MysqlDataSource getDataSource() {
        if (dataSource == null) {
            synchronized (DBUtil.class) {
                if (dataSource == null) {
                    dataSource = new MysqlDataSource();
                    dataSource.setURL(URL);
                    dataSource.setUser(USER);
                    dataSource.setPassword(PASSWORD);
                }
            }
        }
        return dataSource;
    }

    //获取数据库连接的方法
    public static Connection getConnection() {
        try {
            return getDataSource().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //关闭数据库连接的方法
    //其中关闭数据库连接必须按照ResultSet、PrepareStatement、Connection的顺序关闭
    public static void close(Connection connection, PreparedStatement statement, ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
