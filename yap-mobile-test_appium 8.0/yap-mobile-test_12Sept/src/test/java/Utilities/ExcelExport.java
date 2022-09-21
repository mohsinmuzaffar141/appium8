package Utilities;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class ExcelExport {

    public void writeHeaderLine(ResultSet result, XSSFSheet sheet) throws SQLException {
        // write header line containing column names
        ResultSetMetaData metaData = result.getMetaData();
        int numberOfColumns = metaData.getColumnCount();
        Row headerRow = sheet.createRow(0);

        // exclude the first column which is the ID field
        for (int i = 1; i <= numberOfColumns; i++) {
            String columnName = metaData.getColumnName(i);
            Cell headerCell = headerRow.createCell(i - 1);
            headerCell.setCellValue(columnName);
        }
    }
    public void writeDataLines(ResultSet result, XSSFSheet sheet) throws SQLException {
        ResultSetMetaData metaData = result.getMetaData();
        int numberOfColumns = metaData.getColumnCount();
        int rowCount = 1;
        while (result.next()) {
            Row row = sheet.createRow(rowCount++);
            for (int i = 2; i <= numberOfColumns; i++) {
                Object valueObject = result.getObject(i);
                Cell cell = row.createCell(i - 2);
                if (valueObject instanceof Boolean)
                    cell.setCellValue((Boolean) valueObject);
                else if (valueObject instanceof Double)
                    cell.setCellValue((double) valueObject);
                else if (valueObject instanceof Float)
                    cell.setCellValue((float) valueObject);
                else if (valueObject instanceof Integer)
                    cell.setCellValue((Integer) valueObject);
                else if (valueObject instanceof LocalDateTime)
                    cell.setCellValue(String.valueOf(valueObject));
                else if (valueObject instanceof Date)
                    cell.setCellValue((Date) valueObject);
                else cell.setCellValue((String) valueObject);

            }

        }
    }
    public String getFileName(String baseName) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String dateTimeInfo = dateFormat.format(new Date());
        return baseName.concat(String.format("_%s.xls", dateTimeInfo));
    }
}
