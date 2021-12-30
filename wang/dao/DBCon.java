package com.wang.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DBCon {
    private static String driver;
    private static String url;
    private static String username;
    private static String password;

    //静态代码块，加载就初始化
    static {
        Properties properties = new Properties();
        //读取db.properties内容
        InputStream is = DBCon.class.getClassLoader().getResourceAsStream("db.properties");
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver=properties.getProperty("driver");
        url=properties.getProperty("url");
        username=properties.getProperty("username");
        password=properties.getProperty("password");
    }
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        //获取数据库的链接
        Connection connection=null;
        Class.forName(driver);
        connection = DriverManager.getConnection(url, username, password);
        return connection;
    }
    //查询公共类
    public static ResultSet execute(Connection connection,PreparedStatement preparedStatement, ResultSet rs ,String sql,Object[] params) throws SQLException {
        preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i <params.length ; i++) {

            //Object不能从0开始，数组要从0开始
            preparedStatement.setObject(i+1,params[i]);

        }
        /*
            for (int i = 1; i <params.length ; i++) {

            //Object不能从0开始，数组要从0开始
            preparedStatement.setObject(i,params[i-1]);
            这样写会报：No value specified for parameter 1
                                          没有为参数1指定值
            }
         */
        rs = preparedStatement.executeQuery();
        return  rs;

    }
    //增删改出公共方法
    public static int execute(Connection connection,  PreparedStatement preparedStatement,String sql, Object[] params) throws SQLException {
        preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i <params.length ; i++) {

            //Object不能从0开始，数组要从0开始
            preparedStatement.setObject(i+1,params[i]);

        }
        int updateRows = preparedStatement.executeUpdate();
        return  updateRows;

    }
    //释放资源
    public static boolean close(Connection connection, PreparedStatement preparedStatement, ResultSet rs) throws SQLException {
        boolean flag=true;
        if (rs!=null){
            rs.close();
            //GC回收
            rs=null;
        }else {
            flag=false;
        }
        if (connection!=null){
            connection.close();
            //GC回收
            connection=null;
        }else {
            flag=false;
        }
        if (preparedStatement!=null){
            preparedStatement.close();
            //GC回收
            preparedStatement=null;
        }else {
            flag=false;
        }
        return flag;
    }

}
