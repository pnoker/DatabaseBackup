package com.dbp.option;

import java.sql.SQLException;

import com.dbp.util.Sqlserver;

/**
 * @author Pnoker
 * @mail peter-no@foxmail.com
 * @date 2017年2月18日
 * @description 数据操作，包括删除、复制操作
 */

public class DataOption {

	public String copyData(String fromTable, String toTable, String timeNode) {
		Sqlserver dbConnect = new Sqlserver();
		String sql = "insert into " + toTable + "(typeserial,tag,value,reachtime) select typeserial,tag,value,reachtime from " + fromTable + " where reachtime < '" + timeNode + " 00:00:00'";
		try {
			dbConnect.executeUpdate(sql);
			return "The data copy success," + fromTable + " - > " + toTable + " : reachtime < " + timeNode;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return "The data copy failure";
		} finally {
			dbConnect.free();
		}
	}

	public boolean deleteData(String fromTable, String timeNode) {
		Sqlserver dbConnect = new Sqlserver();
		String sql = "delete from " + fromTable + " where reachtime < '" + timeNode + " 00:00:00'";
		try {
			dbConnect.executeUpdate(sql);
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}finally {
			dbConnect.free();
		}
	}

}
