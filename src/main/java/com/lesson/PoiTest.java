package com.lesson;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.Iterator;

/**
 * @author liliang
 * @date 2018/11/15 18:06
 */
public class PoiTest {

    public static String readXls(String path) {
        StringBuilder text = new StringBuilder();
        try {
            FileInputStream is = new FileInputStream(path);
            HSSFWorkbook excel = new HSSFWorkbook(is);
            //获取第一个sheet
            HSSFSheet sheet0 = excel.getSheetAt(0);
            for (Row aSheet0 : sheet0) {
                HSSFRow row = (HSSFRow) aSheet0;
                for (Iterator iterator = row.cellIterator(); iterator.hasNext(); ) {
                    HSSFCell cell = (HSSFCell) iterator.next();
                    //根据单元的的类型 读取相应的结果
                    switch (cell.getCellTypeEnum()) {
                        case STRING:
                            text.append(cell.getStringCellValue()).append("\t");
                            break;
                        case NUMERIC:
                            text.append(cell.getNumericCellValue()).append("\t");
                            break;
                        case FORMULA:
                            text.append(cell.getCellFormula()).append("\t");
                            break;
                        default:
                    }
                }
                text.append("\n");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return text.toString();
    }

    public static String readXlsx(String path) {
        StringBuilder text = new StringBuilder();
        try {
            OPCPackage pkg = OPCPackage.open(path);
            XSSFWorkbook excel = new XSSFWorkbook(pkg);
            //获取第一个sheet
            XSSFSheet sheet0 = excel.getSheetAt(0);
            for (Row aSheet0 : sheet0) {
                XSSFRow row = (XSSFRow) aSheet0;
                for (Iterator iterator = row.cellIterator(); iterator.hasNext(); ) {
                    XSSFCell cell = (XSSFCell) iterator.next();
                    //根据单元的的类型 读取相应的结果
                    switch (cell.getCellTypeEnum()) {
                        case STRING:
                            text.append(cell.getStringCellValue()).append("\t");
                            break;
                        case NUMERIC:
                            text.append(cell.getNumericCellValue()).append("\t");
                            break;
                        case FORMULA:
                            text.append(cell.getCellFormula()).append("\t");
                            break;
                        default:
                    }
                }
                text.append("\n");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return text.toString();
    }



    public static void main(String[] args) {
        String path = "C:\\Users\\mayn\\Desktop\\测试.xlsx";
        String s = PoiTest.readXlsx(path);
        System.out.println(s);
    }
}
