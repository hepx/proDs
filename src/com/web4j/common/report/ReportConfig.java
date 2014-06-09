package com.web4j.common.report;

import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
/**
 * 报表参数
 * @author xixi
 * @date 2013-6-7
 *
 */
public class ReportConfig {

	//样式
	public static StyleBuilder getBoldStl() {
		return DynamicReports.stl.style().bold();
	}
	public static StyleBuilder getBoldCenteredStl() {
		return DynamicReports.stl.style(getBoldStl()).setHorizontalAlignment(HorizontalAlignment.CENTER);
	}
	public static StyleBuilder getTitleStl() {
		return DynamicReports.stl.style(getBoldCenteredStl()).setFontSize(16);
	}
	public static StyleBuilder getColumnTitleStl() {
		return DynamicReports.stl.style(getBoldCenteredStl()).setBorder(DynamicReports.stl.pen1Point());
	}
	
	
}
