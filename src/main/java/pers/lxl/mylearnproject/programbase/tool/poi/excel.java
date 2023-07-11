package pers.lxl.mylearnproject.programbase.tool.poi;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

public class excel {
    static String Path = "D:\\Projects\\MY\\mylearnproject\\src\\main\\resources\\static\\poi\\";
    public static void main(String[] args) throws IOException {
        // writeXssfWorkbook();

        try (FileInputStream fis = new FileInputStream(Path + "xlstest.xls");
        ) {
            //  Workbook workbook = new XSSFWorkbook(fis);
            Workbook workbook = new XSSFWorkbook(fis);
            //3.获取表
            Sheet sheet = workbook.getSheetAt(0);
            //4.获取第一行
            Row row1 = sheet.getRow(0);
            //5.获取第一列
            Cell cell1 = row1.getCell(0);
            //6.获取第一行第一列的值
            String stringCellValue = cell1.getStringCellValue();
            //获取第二列
            Cell cell2 = row1.getCell(1);
            //获取第一行第二列的值
            String numericCellValue = cell2.getStringCellValue();
            System.out.println(stringCellValue+" | "+numericCellValue);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

   static void writeXssfWorkbook(){
        // 创建excel
        // 创建WorkBook，一个WorkBook代表一个Excel文件
        // Workbook workbook = new HSSFWorkbook();//03 xls
        Workbook workbook = new XSSFWorkbook();//07 xlsx
        try (
                FileOutputStream out = new FileOutputStream(Path+"xlstest.xls")
        ) {
            //     创建工作簿Sheet
            Sheet sheet = workbook.createSheet("数据");
            Sheet sheet1 = workbook.createSheet("数据1");
            Sheet sheet2 = workbook.createSheet("数据2");
            //     创建数据航Row
            Row row = sheet.createRow(0);
            Row row1 = sheet.createRow(1);
            //     创建单元格Cell
            Cell cell11 = row.createCell(0);
            cell11.setCellValue("UUID值");
            Cell cell12 = row1.createCell(0);
            cell12.setCellValue(UUID.randomUUID().toString());


            Cell cell21 = row.createCell(1);
            cell21.setCellValue("Math随机值");
            Cell cell22 = row1.createCell(1);
            cell22.setCellValue(Math.random() * 1000);


            Cell cell31 = row.createCell(2);
            cell31.setCellValue("当前时间");
            Cell cell32 = row1.createCell(2);
            cell32.setCellValue(String.valueOf(LocalDateTime.now()));

            // 将Workbook对象中包含的数据，通过输出流，写入至Excel文件
            workbook.write(out);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
