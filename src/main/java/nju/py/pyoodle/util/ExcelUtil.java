package nju.py.pyoodle.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 * @Author: py
 * @Date: 2019/3/18 上午11:24
 * @Version 1.0
 */
public class ExcelUtil {
    private final static String EXCEL_PATH = "/Users/py/J2EEStrorage/score.xlsx";
    public static ArrayList<Map<String, Integer>> readExcel() throws Exception {


        ArrayList<Map<String, Integer>> mapList = new ArrayList<>();
        File file = new File(EXCEL_PATH);
        //判断文件是否存在
        if (file.isFile() && file.exists()) {
            System.out.println(file.getPath());
            //获取文件的后缀名 \\ .是特殊字符
            String[] split = file.getName().split("\\.");
            System.out.println(split[1]);
            Workbook wb;
            if ("xls".equals(split[1])) {
//              //获取文件流对象
                FileInputStream inputStream = new FileInputStream(file);
                wb = new HSSFWorkbook(inputStream);
            }else if ("xlsx".equals(split[1])){
                wb = new XSSFWorkbook(file);
            }else {
                System.out.println("文件类型错误");
                return null;
            }

            //开始解析
            Sheet sheet = wb.getSheetAt(0);
            //第一行是列名，所以从第二行开始遍历
            int firstRowNum = sheet.getFirstRowNum() + 1;
            int lastRowNum = sheet.getLastRowNum();

            //遍历行
            for (int rIndex = firstRowNum; rIndex <= lastRowNum; rIndex++) {
                Map map =new HashMap();
                //获取当前行的内容
                Row row = sheet.getRow(rIndex);
                if (row != null) {
                    int firstCellNum = row.getFirstCellNum();
                    int lastCellNum = row.getLastCellNum();
                    for (int cIndex = firstCellNum; cIndex < lastCellNum; cIndex++) {
                        row.getCell(cIndex).setCellType(Cell.CELL_TYPE_STRING);
                        //获取单元格的值
                        String value = row.getCell(cIndex).getStringCellValue();
                        System.out.println(value);
                        //获取此单元格对应第一行的值
                        String key = sheet.getRow(0).getCell(cIndex).getStringCellValue();
                        System.out.println(key);
                        //第一行中的作为键，第n行的作为值
                        map.put(key, value);
                        System.out.println(map);
                    }
                }
                mapList.add(map);
                System.out.println("读取成功");
                System.out.println(mapList);
            }

        }

        return mapList;

    }

    public static void main(String[] args) {
        try {
            List<Map<String, Integer>> maps = readExcel();
            System.out.println(maps.size());
            for(Map<String, Integer> map: maps) {
                System.out.println(map.get("学号") + ": " + map.get("分数"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
