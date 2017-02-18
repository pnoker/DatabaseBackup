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
		calendar.add(Calendar.MONTH, -1);// 上个月
		Date date = calendar.getTime();
		TableOption tableOption = new TableOption();
		if (tableOption.createTable(sdfOfTable.format(date))) {// 创建上个月的表
			System.out.println("create success");
			DataOption dataOption = new DataOption();
			calendar.add(Calendar.MONTH, 1);// 恢复到本月
			date = calendar.getTime();
			dataOption.copyData("hart_data", "hart_data_" + sdfOfTable.format(date), sdfOfTimeNode.format(date));
			dataOption.deleteData("hart_data", sdfOfTimeNode.format(date));
		} else {
			System.out.println("create failure");
		}
	}
}
