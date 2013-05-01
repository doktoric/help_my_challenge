package com.acme.challenge;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class ExcelExporter {

    public static WritableCellFormat cellFormat;

    public void exportMapsToExcel(Map<Date, Integer> urlStatistics, Map<Date, Integer> generalStatistics, Map<Date, Integer> exportStatistics)
        throws IOException, WriteException {
        File file = new File("e://downloads//result2.xls");

        WorkbookSettings wbSettings = new WorkbookSettings();
        wbSettings.setLocale(new Locale("en", "EN"));

        WritableWorkbook workbook = Workbook.createWorkbook(file, wbSettings);

        WritableFont times10pt = new WritableFont(WritableFont.TIMES, 10);
        cellFormat = new WritableCellFormat(times10pt);
        cellFormat.setWrap(true);

        writeStatsToSheet(urlStatistics, workbook, "url", 1);
        writeStatsToSheet(generalStatistics, workbook, "general", 15);
        writeStatsToSheet(exportStatistics, workbook, "export", 30);

        workbook.write();
        workbook.close();
    }

    private void writeStatsToSheet(Map<Date, Integer> statistics, WritableWorkbook workbook, String sheetName, int sheetNumber)
        throws WriteException, RowsExceededException {
        int i = 1;
        WritableSheet sheet = workbook.createSheet(sheetName + "" + sheetNumber, sheetNumber++);
        for (Date date : statistics.keySet()) {
            if (i % 60000 == 0) {
                sheet = workbook.createSheet(sheetName + "" + sheetNumber, sheetNumber++);
                i = 1;
            }
            writeLabelToSheet(sheet, 0, i, date.toString());
            writeNumberToSheet(sheet, 1, i++, statistics.get(date).toString());
        }

    }

    public void writeLabelToSheet(WritableSheet sheet, int column, int row, String s) throws WriteException, RowsExceededException {
        Label label = new Label(column, row, s, cellFormat);
        sheet.addCell(label);
    }

    public void writeNumberToSheet(WritableSheet sheet, int column, int row, String s) throws WriteException, RowsExceededException {
        Number number = new Number(column, row, Double.valueOf(s));
        sheet.addCell(number);
    }

}
