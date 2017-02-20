package com.dbp.option;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.dbp.util.Sqlserver;

/**
 * @author Pnoker
 * @mail peter-no@foxmail.com
 * @date 2017年2月17日
 * @description 新建一个数据表，包括serial 、typeserial 、tag 、value 、reachtime 字段
 */

public class TableOption {
	// 待创建的hart_data_year_month表结构
	public static String Hart_Data = " (serial int not null identity(1,1),typeserial nvarchar(20),tag int,value float,reachtime datetime)";

	/**
	 * 根据年、月创建hart_data_year_month表
	 */
	public boolean createTable(String tableName) {
		Sqlserver dbConnect = new Sqlserver();
		String creatTable_sql = "create table " + tableName + Hart_Data;
		try {
			if (!findTableByName(tableName)) {
				dbConnect.executeUpdate(creatTable_sql);
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		} finally {
			dbConnect.free();
		}
	}

	public boolean findTableByName(String tableName) {
		Sqlserver dbConnect = new Sqlserver();
		String sql = "select * from SysObjects where name = '" + tableName + "'";
		try {
			ResultSet rs = dbConnect.executeQuery(sql);
			if (rs.next()) {
				System.out.println("Table " + tableName + " has been created");
				return true;
			} else {
				System.out.println("Table " + tableName + " not exist");
				return false;
			}
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		} finally {
			dbConnect.free();
		}
	}

	public static void main(String[] args) {
		SimpleDateFormat sdfOfTimeNode = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		Date date = calendar.getTime();
		System.out.println(sdfOfTimeNode.format(date));
	}
}
