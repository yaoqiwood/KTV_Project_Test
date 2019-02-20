package dao;

import java.sql.*;

//数据库连接的单例
public class DbUtil {

	private Connection connection;
	private static DbUtil dbUtil;

	public static DbUtil getDbUtil() {
		if (dbUtil == null) {
			dbUtil = new DbUtil();
		}
		return dbUtil;
	}

	private DbUtil() {
		// 连接数据库
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:../DB/server.db");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Connection getConnection() {
		return connection;
	}

	// 释放资源的方法
	public void release(PreparedStatement ps, ResultSet rs) {

		try {
			if (ps != null) {
				ps.close();
			}
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
