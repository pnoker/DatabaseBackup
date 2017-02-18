package com.dbp.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.dbp.option.DataOption;
import com.dbp.option.TableOption;

/**
 * @author Pnoker
 * @mail peter-no@foxmail.com
 * @date 2016年8月1日
 * @description
 */

public class SpiltDataTool {

	public static void main(String[] args) {
		SimpleDateFormat sdfOfTimeNode = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdfOfTable = new SimpleDateFormat("yyyy_MM");
		Calendar calendar = Calendar.getInstance();
		calendar.set(2016, 7, 1);//设置时间，一个月一个月分表
		Date date = calendar.getTime();
		TableOption tableOption = new TableOption();
		if (tableOption.createTable(sdfOfTable.format(date))) {
			System.out.println("create success");
			DataOption dataOption = new DataOption();
			calendar.add(Calendar.MONTH, 1);// 下个月
			date = calendar.getTime();
			dataOption.copyData("hart_data", "hart_data_" + sdfOfTable.format(date), sdfOfTimeNode.format(date));
			dataOption.deleteData("hart_data", sdfOfTimeNode.format(date));
		} else {
			System.out.println("create failure");
		}
	}

}
