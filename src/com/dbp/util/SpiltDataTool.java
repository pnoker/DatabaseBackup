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
		calendar.set(2016, 7, 1);// 设置时间，一个月一个月分表
		Date dataOfTable = calendar.getTime();
		calendar.add(Calendar.MONTH, 1);// 下个月,时间节点
		Date dataOfTimeNode = calendar.getTime();
		TableOption tableOption = new TableOption();
		if (tableOption.createTable(sdfOfTable.format(dataOfTable))) {
			System.out.println("create success");
			DataOption dataOption = new DataOption();
			boolean isSuccess = dataOption.copyData("hart_data", "hart_data_" + sdfOfTable.format(dataOfTable), sdfOfTimeNode.format(dataOfTimeNode));
			if (isSuccess) {
				dataOption.deleteData("hart_data", sdfOfTimeNode.format(dataOfTimeNode));
			}
		} else {
			System.out.println("create failure");
		}
	}

}
