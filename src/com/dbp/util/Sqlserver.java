package com.dbp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Pnoker
 * @mail peter-no@foxmail.com
 * @date 2017年2月17日
 * @description Sql Server 数据操作
 */
public class Sqlserver {
	private Connection connection = null;
	public Statement statement = null;
	private ResultSet result = null;

	public Sqlserver() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://localhost:1433;databaseName=tianjincollect;user=sa;password=yangfan";
			connection = DriverManager.getConnection(url);
			statement = connection.createStatement();
		} catch (SQLException ex) {
			System.out.println("ERROR : " + ex.getMessage());
		} catch (ClassNotFoundException ex) {
			System.out.println("ERROR : " + ex.getMessage());
		}
	}

	public ResultSet executeQuery(String sql) throws SQLException {
		System.out.println("execute SQL : " + sql);
		result = statement.executeQuery(sql);
		return result;
	}

	public int executeUpdate(String sql) throws SQLException {
		System.out.println("execute SQL : " + sql);
		int updatenum = 0;
		updatenum = statement.executeUpdate(sql);
		return updatenum;
	}

	public void free() {
		try {
			if (result != null)
				result.close();
			if (statement != null)
				statement.close();
			if (connection != null)
				connection.close();
		} catch (SQLException se) {
			System.out.println("ERROR : " + se.getMessage());
		}
	}

}
