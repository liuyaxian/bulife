package com.ruiya.util;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.beans.IntrospectionException;
import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExcelToSQLInsert {

    public static void main(String[] args) throws Exception {

             // 读取文件内容
//           readExcelData("D:\\企业微信\\WXWork\\1688851025356947\\Cache\\File\\2022-03\\备选池基金信息表-众禄生产环境全量标的 - 20220330.xlsx");

        readExcelData("C:\\Users\\liu_y\\Desktop\\新建 XLSX 工作表.xlsx");



    }


    public static void readExcelData(String fileUrl) throws IOException {
        // 文件流
//        InputStream inputStream = new FileInputStream(file.getAbsoluteFile());
        File file = new File(fileUrl);

        String fileName = file.getName().substring(0, file.getName().lastIndexOf("."));

        // 新建并获取工作薄
        XSSFWorkbook wb = new XSSFWorkbook(FileUtils.openInputStream(file));
        // Sheet(工作表)
//        HSSFSheet sheet = wb.getSheet("Sheet1");
        // 获取第一个sheet
        XSSFSheet sheet = wb.getSheetAt(0);
        // 获取第一行
        XSSFRow row = sheet.getRow(0);
        // 获取总列数
        int columNum = row.getPhysicalNumberOfCells();
        // 获取总行数
        int rowNum = sheet.getPhysicalNumberOfRows();
        int rowNumLsat = sheet.getLastRowNum();

        File file11 = new File("C:\\Users\\liu_y\\Desktop\\11.txt");
        Writer out =new FileWriter(file11);

        // 按照 C 列的  carton no 将数据汇总
        // 遍历所有行
        for (int i1 = 1; i1 < rowNum; i1++) {
            XSSFRow row_ = sheet.getRow(i1);
            if (row_ == null) {
                System.out.println("row_ 为空");
                continue;
            }
            // 遍历所有列获取数据
            // 文件名，
//            String insertsql = "INSERT INTO comb_fundtype\n" +
//                    " (fundid, fundrisklevel, fundtypecode, fundtypename, assetclasscode, assetclassname, assetcategorycode,\n" +
//                    " assetcategoryname) values ('%s','%s','%s','%s','%s','%s','%s', '%s');";
//            for (int i = 0; i < columNum; i++) {
//                XSSFCell cell = row_.getCell(i);
//                if (cell == null){
//                    System.out.println("cell 为空");
//                    continue;
//                }

//                insertsql =  String.format(insertsql, getCellValue(row_.getCell(0)),
//                                getCellValue(row_.getCell(2)),
//                                getCellValue(row_.getCell(3)),
//                                        getCellValue(row_.getCell(4)),
//                                                getCellValue(row_.getCell(5)),
//                                                        getCellValue(row_.getCell(6)),
//                        getCellValue(row_.getCell(7)),getCellValue(row_.getCell(8)));

            String insertsql = " INSERT INTO public.comb_fund_alternative_pool\n" +
                    "(servicetype, fundid)\n" +
                    "VALUES('3', '%s');";

            insertsql =  String.format(insertsql, getCellValue(row_.getCell(0)));
                System.out.println(insertsql);
                out.write(insertsql+ "\n");
        }
        out.close();
    }

    /**
     * 单元格的内容
     * @param xssfCell 单元格
     * @return
     */
    public static Object getCellValue(XSSFCell xssfCell) {
        if (xssfCell == null) {
            return "";
        }
        if (xssfCell.getCellType() == CellType.NUMERIC) {
            return String.valueOf(xssfCell.getNumericCellValue());
        } else if (xssfCell.getCellType() == CellType.BOOLEAN) {
            return xssfCell.getBooleanCellValue();
        } else {
            return xssfCell.getStringCellValue();
        }
    }


}
