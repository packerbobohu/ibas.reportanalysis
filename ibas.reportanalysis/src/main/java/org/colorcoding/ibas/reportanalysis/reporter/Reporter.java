package org.colorcoding.ibas.reportanalysis.reporter;

import org.colorcoding.ibas.bobas.data.DataTable;
import org.colorcoding.ibas.bobas.data.IDataTable;
import org.colorcoding.ibas.bobas.data.IDataTableColumn;
import org.colorcoding.ibas.bobas.data.IDataTableRow;
import org.colorcoding.ibas.bobas.data.KeyText;

public abstract class Reporter implements IReporter {

	/**
	 * 创建键值表
	 * 
	 * @param values
	 * @return
	 */
	protected IDataTable create(KeyText[] values) {
		IDataTable table = new DataTable();
		if (values != null) {
			for (KeyText item : values) {
				IDataTableColumn columnKey = table.getColumns().create();
				columnKey.setName("Key");
				columnKey.setDataType(String.class);
				IDataTableColumn columnValue = table.getColumns().create();
				columnValue.setName("Value");
				columnValue.setDataType(String.class);
				IDataTableRow row = table.getRows().create();
				row.setValue(columnKey, item.key);
				row.setValue(columnValue, item.text);
			}
		}
		return table;
	}
}
