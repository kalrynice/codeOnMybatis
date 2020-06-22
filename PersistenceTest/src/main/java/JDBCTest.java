import java.sql.*;

public class JDBCTest {

    public static void main(String[] args) {
        select();
    }


    public static Connection getConnection() {
        Connection conn = null;
        try {
            //加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            //连接数据库
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatistest?useUnicode=true&characterEncoding=utf-8&useSSL=false", "root", "root");

        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return conn;
    }

    public static void select() {
        Connection conn = getConnection();
        String sql = "select *from user";
        try {
            PreparedStatement st = conn.prepareStatement(sql);

            ResultSet rs = st.executeQuery(); //执行SQL语句
            while (rs.next()) {
                System.out.print(rs.getString("id") + " ");
                System.out.println(rs.getString("username") + " ");
            }
            rs.close();
            st.close();
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
