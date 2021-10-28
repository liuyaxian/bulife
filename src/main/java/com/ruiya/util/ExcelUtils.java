package com.ruiya.util;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.beans.IntrospectionException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExcelUtils {

    /**
     * 根据文件路径获取所有文件
     * @param path
     */
    public static void getAllFileByDir(String path) throws IOException, IntrospectionException, InstantiationException, IllegalAccessException {

        File file  = new File(path);
        if(!file.isDirectory()){
            System.out.println("没有找到该文件路径");
            return;
        }

        String [] filelist = file.list();
        Map<String, WeightALL>  numsMap = new HashMap<>();

        for (int i = 0; i < filelist.length; i++) {
            File readFile = new File(path + File.separator + filelist[i]);
            if (readFile.isDirectory()){
                System.out.println("是文件夹");
                return;
            }

            System.out.printf("绝对路径：", readFile.getAbsoluteFile());
            System.out.println("文件名："+readFile.getName());
            // 读取文件内容
            readExcelData(readFile, 0, numsMap);
        }
        // 将数据写入Excel 中
        // 按照 Mawbs 分组
        List<WeightALL> weightALLList  = new ArrayList<>();
        for (String s : numsMap.keySet()) {
            weightALLList.add(numsMap.get(s));
        }
        Collections.sort(weightALLList, new Comparator<WeightALL>() {
            @Override
            public int compare(WeightALL o1, WeightALL o2) {
                return o1.getNawbs().compareTo(o2.getNawbs());
            }
        });

        createExcel(weightALLList);
    }

    public static void readExcelData(File file, int index, Map<String, WeightALL>  numsMap) throws IOException {
        // 文件流
//        InputStream inputStream = new FileInputStream(file.getAbsoluteFile());
//        File file1 = new File("C:\\Users\\lenovo\\Documents\\WeChat Files\\wxid_q9bkvbb54cs122\\FileStorage\\File\\2021-10\\369-83049050.xlsx");

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

        // 按照 C 列的  carton no 将数据汇总
        // 遍历所有行
        for (int i1 = 1; i1 < rowNum; i1++) {
            XSSFRow row_ = sheet.getRow(i1);
            if (row_ == null){
                System.out.println("row_ 为空");
                continue;
            }
            // 遍历所有列获取数据
            // 文件名，

            //  箱号 Carton No E
            String  cartonNo = (String) getCellValue(row_.getCell(4));
            //  Parcel Weight(KG)  I (需要加和),  8
            BigDecimal parcelWeight = new BigDecimal((String)getCellValue(row_.getCell(8)));
            //   Carton Weight(KG)  J,
            BigDecimal cartonWeigth = new BigDecimal((String)getCellValue(row_.getCell(10)));
            //   Carton Volume  L
            BigDecimal CartonVolume = new BigDecimal((String) getCellValue(row_.getCell(11)));

            // 按照 CartonNo 汇总
            if (numsMap.containsKey(cartonNo)){

                WeightALL weightALL = numsMap.get(cartonNo);
                weightALL.setPcs(weightALL.getPcs()+1);
                weightALL.setParcelWeight(weightALL.getParcelWeight().add(parcelWeight));

            } else {
                WeightALL weightALL = new WeightALL();
                weightALL.setNawbs(fileName);
                weightALL.setCartonNo(cartonNo);
                weightALL.setPcs(1);
                weightALL.setParcelWeight(parcelWeight);

                weightALL.setCartonVolume(CartonVolume);
                weightALL.setCartonWeigth(cartonWeigth);
                numsMap.put(cartonNo, weightALL);
            }

//            for (int i = 0; i < columNum; i++) {
//                XSSFCell cell = row_.getCell(i);
//                if (cell == null){
//                    System.out.println("cell 为空");
//                    continue;
//                }
//                System.out.println(getCellValue(cell));
//
//            }

        }
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


    public static void createExcel(List<WeightALL> weightALLList) throws IOException, InstantiationException, IllegalAccessException, IntrospectionException {
        String [] rowHead = {"seq No","Mawbs", "Carton No","pcs","parcel weight","Carton weight","Carton volume"};

        //创建HSSFWorkbook对象
        XSSFWorkbook wb =  new  XSSFWorkbook();
        //创建HSSFSheet对象
        XSSFSheet sheet = wb.createSheet( "weight_all_in_one" );
        //创建HSSFRow对象
        XSSFRow firstRow = sheet.createRow( 0 );

        // 创建表格头：
        for (int i = 0; i < rowHead.length; i++) {
            //设置单元格的值
            XSSFCell cell=firstRow.createCell(i);
            //设置单元格的值
            cell.setCellValue(rowHead[i]);
        }


        // 根据 numsMap 中的数据常见表内容
        for (int i = 0; i < weightALLList.size(); i++) {
            //创建HSSFRow对象
            XSSFRow row = sheet.createRow(i+1);

            WeightALL weightALL = weightALLList.get(i);

            weightALL.setNum(i);
            for (int j = 0; j < rowHead.length; j++) {
                if (j ==0){
                    //设置单元格的值
                    XSSFCell cell=row.createCell(j);
                    cell.setCellValue(i+1);
                } else {
                    //设置单元格的值
                    XSSFCell cell=row.createCell(j);
                    //设置单元格的值
                    Object [] object = weightALL.csvLine();
                    cell.setCellValue((String)object[j]);
                }
            }
        }


        //输出Excel文件
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        String  date =  format.format(new Date());
        FileOutputStream output= new  FileOutputStream( "D:\\"+ date+ "workbook.xls" );
        wb.write(output);
        output.flush();

    }


    static class WeightALL {
        private int num;
        private String nawbs;
        private String cartonNo;
        private int pcs;
        private BigDecimal parcelWeight;
        private BigDecimal cartonWeigth;
        private BigDecimal CartonVolume;

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getNawbs() {
            return nawbs;
        }

        public void setNawbs(String nawbs) {
            this.nawbs = nawbs;
        }

        public String getCartonNo() {
            return cartonNo;
        }

        public void setCartonNo(String cartonNo) {
            this.cartonNo = cartonNo;
        }

        public int getPcs() {
            return pcs;
        }

        public void setPcs(int pcs) {
            this.pcs = pcs;
        }

        public BigDecimal getParcelWeight() {
            return parcelWeight;
        }

        public void setParcelWeight(BigDecimal parcelWeight) {
            this.parcelWeight = parcelWeight;
        }

        public BigDecimal getCartonWeigth() {
            return cartonWeigth;
        }

        public void setCartonWeigth(BigDecimal cartonWeigth) {
            this.cartonWeigth = cartonWeigth;
        }

        public BigDecimal getCartonVolume() {
            return CartonVolume;
        }

        public void setCartonVolume(BigDecimal cartonVolume) {
            CartonVolume = cartonVolume;
        }

        public String[] csvLine() {

            return new String[]{
                    "",
                    getNawbs(),
                    getCartonNo(),
                    String.valueOf(getPcs()),
                    String.valueOf(getParcelWeight()),
                    String.valueOf(getCartonWeigth()),
                    String.valueOf(getCartonVolume())};
        }
    }


    public static void main(String[] args) throws Exception {
        getAllFileByDir("C:\\Users\\lenovo\\Documents\\WeChat Files\\wxid_q9bkvbb54cs122\\FileStorage\\File\\2021-10");
    }
}