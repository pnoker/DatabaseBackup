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
		calendar.set(2017, 6, 1);// 设置时间，一个月一个月分表,真实月份加 1
		Date dataOfTable = calendar.getTime();
		calendar.add(Calendar.MONTH, 1);// 下个月,时间节点
		Date dataOfTimeNode = calendar.getTime();
		TableOption tableOption = new TableOption();
		if (tableOption.createTable("wireless_data_" + sdfOfTable.format(dataOfTable))) {
			System.out.println("create success");
			DataOption dataOption = new DataOption();
			boolean isSuccess = dataOption.copyData("wireless_data", "wireless_data_" + sdfOfTable.format(dataOfTable),
					sdfOfTimeNode.format(dataOfTimeNode));
			if (isSuccess) {
				// 保留两个月的数据所以删除本月之前的数据
				dataOption.deleteData("wireless_data", sdfOfTimeNode.format(dataOfTable));
			}
		} else {
			System.out.println("create failure");
		}
	}

}
