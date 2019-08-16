package com.mipo.common.export;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.List;

public class ExcelExport {

	public static <T> void exportList(List<T> returnList, List<SQLField> fieldList, OutputStream output, boolean bool) throws IOException {
		SXSSFWorkbook workbook = new SXSSFWorkbook();
		Sheet sheet = workbook.createSheet("sheet");
		Row columnRow = sheet.createRow(0);
		for (int i = 0; i < fieldList.size(); i++) {
			Cell columnCell = columnRow.createCell(i);
			columnCell.setCellValue(fieldList.get(i).getDiscription());
		}
		for (int j = 1; j < returnList.size() + 1; j++) {
			Row row = sheet.createRow(j);
			for (int i = 0; i < fieldList.size(); i++) {
				SQLFieldTransform transform = fieldList.get(i).getTransformer();
				Object bean = returnList.get(j - 1);
				String fieldName = fieldList.get(i).getFieldName();

				Object o = byReFlect(bean, fieldName);
				// 有转换器则经过转换再输出
				if (transform != null) {
					o = transform.transform(o, bool);
				}

				Cell cell = row.createCell(i);
				if(o == null){
					cell.setCellValue("");
				} else if (o instanceof Integer || o instanceof BigDecimal || o instanceof Double || o instanceof Long) {
					cell.setCellValue(Double.parseDouble(o.toString()));
				} else {
					cell.setCellValue(o.toString());
				}
			}
		}

		for (int i = 0; i < fieldList.size(); i++) {
			sheet.autoSizeColumn(i, true);
		}

		workbook.write(output);
		workbook.dispose();
	}


	/**
	 * 通过反射获取属性
	 * @param obj
	 * @param fieldName
	 * @return
	 */
	private static Object byReFlect(Object obj, String fieldName) {
		try {
			PropertyDescriptor pd = new PropertyDescriptor(fieldName, obj.getClass());
			Method getMethod = pd.getReadMethod();
			return getMethod.invoke(obj);
		} catch (Exception e) {
			return null;
		}
	}

}
