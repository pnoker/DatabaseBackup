package com.abp.main;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.dbp.option.DataOption;
import com.dbp.option.TableOption;

public class mainThread {
	SimpleDateFormat sdfOfTimeNode = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat sdfOfTable = new SimpleDateFormat("yyyy_MM");

	public void startMainThread() throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);// 设置为上个月
		Date dataOfTable = calendar.getTime();// 表名为上个月的
		calendar.add(Calendar.MONTH, 1);// 时间节点，设置为本月的
		Date dataOfTimeNode = calendar.getTime();// 设置时间节点为本月的开始节点
		TableOption tableOption = new TableOption();
		// hart_data
		if (tableOption.createTable("hart_data_" + sdfOfTable.format(dataOfTable))) {// 创建上个月hart_data_year_month的表
			System.out.println("hart_data_" + sdfOfTable.format(dataOfTable) + " create success");
			DataOption dataOption = new DataOption();
			boolean isSuccsee = dataOption.copyData("hart_data", "hart_data_" + sdfOfTable.format(dataOfTable), sdfOfTimeNode.format(dataOfTimeNode));
			if (isSuccsee) {
				dataOption.deleteData("hart_data", sdfOfTimeNode.format(dataOfTimeNode));
			}
		} else {
			System.out.println("hart_data_" + sdfOfTable.format(dataOfTable) + " create failure");
		}
		// shui_data
		if (tableOption.createTable("shui_data_" + sdfOfTable.format(dataOfTable))) {// 创建上个月shui_data_year_month的表
			System.out.println("shui_data_" + sdfOfTable.format(dataOfTable) + " create success");
			DataOption dataOption = new DataOption();
			boolean isSuccsee = dataOption.copyData("shui_data", "shui_data_" + sdfOfTable.format(dataOfTable), sdfOfTimeNode.format(dataOfTimeNode));
			if (isSuccsee) {
				dataOption.deleteData("shui_data", sdfOfTimeNode.format(dataOfTimeNode));
			}
		} else {
			System.out.println("shui_data_" + sdfOfTable.format(dataOfTable) + " create failure");
		}
	}
}
