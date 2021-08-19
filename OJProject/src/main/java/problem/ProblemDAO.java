package problem;

import Util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProblemDAO {
    //向数据库中插入一条数据
    public void insert(Problem problem){
        //获取连接
        Connection connection= DBUtil.getConnection();
        PreparedStatement statement=null;
        try {
            //拼装SQL语句
            String sql="insert into oj_table values(null,?,?,?,?,?)";
            statement=connection.prepareStatement(sql);
            statement.setString(1,problem.getTitle());
            statement.setString(2,problem.getLevel());
            statement.setString(3,problem.getDescription());
            statement.setString(4,problem.getTemplateCode());
            statement.setString(5,problem.getTestCode());
            //执行SQL
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //释放连接
            DBUtil.close(connection,statement,null);
        }
    }

    public boolean delete(int problemId){
        //建立连接
        Connection connection=DBUtil.getConnection();
        PreparedStatement statement=null;
        //拼装SQL
        String sql="delete from  oj_table where id=?";
        int res=0;
        try {
            statement=connection.prepareStatement(sql);
            statement.setInt(1,problemId);
            //执行SQL
            res=statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //释放连接
            DBUtil.close(connection,statement,null);
            return res>0;
        }
    }

    //查找全部题目（用来实现题目列表功能）
    //只需要查找 id  title  level
    public List<Problem> selectAll(){
        List<Problem> problems=new ArrayList<>();
        //建立连接
        Connection connection=DBUtil.getConnection();
        PreparedStatement statement=null;
        //拼装SQL
        String sql="select id,title,level from oj_table";
        try {
            statement=connection.prepareStatement(sql);
            //执行SQL
            ResultSet resultSet=statement.executeQuery();
            while(resultSet.next()){
                Problem problem=new Problem();
                problem.setId(resultSet.getInt("id"));
                problem.setTitle(resultSet.getString("title"));
                problem.setLevel(resultSet.getString("level"));
                problems.add(problem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(connection,statement,null);
        }
        return problems;
    }

    //查找指定题目（实现题目详情页面功能）
    //需要Problem的每个字段
    public Problem selectOne(int problemId){
        //建立连接
        Connection connection=DBUtil.getConnection();
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        //构造SQL
        String sql="select * from oj_table where id=?";
        try {
            statement=connection.prepareStatement(sql);
            statement.setInt(1,problemId);
            resultSet=statement.executeQuery();

            if (resultSet.next()) {
                Problem problem=new Problem();
                problem.setId(resultSet.getInt("id"));
                problem.setTitle(resultSet.getString("title"));
                problem.setLevel(resultSet.getString("level"));
                problem.setDescription(resultSet.getString("description"));
                problem.setTemplateCode(resultSet.getString("templateCode"));
                problem.setTestCode(resultSet.getString("testCode"));
                return problem;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.close(connection,statement,resultSet);
        }
        return null;
    }
}
