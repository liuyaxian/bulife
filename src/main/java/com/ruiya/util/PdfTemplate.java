package com.ruiya.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.*;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class PdfTemplate {

    /**
     * 赋值并生成新的PDF文档
     *
     * @param templatePDF pdf模版
     * @param outFile     输出的PDF Name
     * @param hashMap     templatePDF对应的数据
     * @author JIA-G-Y
     * 二〇一二年八月二十日 17:20:36
     */
    public static void doSomeThing(String templatePDF, String outFile, HashMap<String, String> hashMap) {
        try {
            FileOutputStream fos = new FileOutputStream(outFile);
            PdfReader reader = new PdfReader(templatePDF);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfStamper stamp = new PdfStamper(reader, baos);
            AcroFields form = stamp.getAcroFields();
            form = setField(form, hashMap);
            stamp.setFormFlattening(true);
            stamp.close();
            Document doc = new Document();
            PdfCopy pdfCopy = new PdfCopy(doc, fos);
            doc.open();
            PdfImportedPage impPage = pdfCopy.getImportedPage(new PdfReader(baos.toByteArray()), 3);
                pdfCopy.addPage(impPage);
            doc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }

    @SuppressWarnings({"unchecked", "unchecked"})
    public static AcroFields setField(AcroFields form, HashMap<String, String> fieldMap) {
        Set it = fieldMap.keySet();
        Iterator itr = it.iterator();
        while (itr.hasNext()) {
            try {
                Object temp = itr.next();
                form.setField(temp.toString(), fieldMap.get(temp.toString()));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        }
        return form;
    }

    public static void main(String[] args) throws DocumentException, IOException {
        HashMap<String, String> textFieldMap = new HashMap<String, String>();
        textFieldMap.put("1", "刘思");
        textFieldMap.put("2", "√");
        textFieldMap.put("5", "1234565215522112");
        textFieldMap.put("6", "深圳市罗湖区梨园路");
        textFieldMap.put("8", "075555");
        textFieldMap.put("9","125555555");
        textFieldMap.put("9","liu@qq.com");
        textFieldMap.put("10", "代理人");
        textFieldMap.put("11", "√");
        textFieldMap.put("14", "11003244545454");
        textFieldMap.put("15", "深圳市福田区");
        textFieldMap.put("16", "2121212");
        textFieldMap.put("17", "1111123232");
        textFieldMap.put("18", "dalireien@126.com");
        textFieldMap.put("19", "法人");
        textFieldMap.put("20", "G23232323232424");
        textFieldMap.put("21", "夯实");
        textFieldMap.put("22", "jiajiajiaji");
        textFieldMap.put("23", "232312311");
        textFieldMap.put("24", "王五");
        textFieldMap.put("25", "1121211111");
        textFieldMap.put("26", "sdfsd@11.com");
        textFieldMap.put("27", "中国人民银行罗湖支行");
        textFieldMap.put("28", "62356478787888888888");
        textFieldMap.put("29", "人民银行");
        textFieldMap.put("30", "30000");
        textFieldMap.put("31", "2021");
        textFieldMap.put("32", "05");
        textFieldMap.put("33", "27");
        textFieldMap.put("1", "已确认");
        textFieldMap.put("year", "2021");
        textFieldMap.put("month", "06");
        textFieldMap.put("day", "23");
//        PdfTemplate.doSomeThing("E:\\项目相关\\openAPI\\滨海项目\\滨海相关合同\\合同\\contract_SCW16511数字.pdf","NewsPDF"+".pdf", textFieldMap);

        //设置编码
//        BaseFont baseFont = BaseFont.createFont("simsun.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        // 不支持中文
        String filename = "E:\\项目相关\\openAPI\\滨海项目\\滨海相关合同\\揭示书\\泓钰鸿运来1号私募证券投资基金风险揭示书签名.pdf";
        String destFilePath = "E:\\项目相关\\openAPI\\滨海项目\\滨海相关合同\\合同\\contract_SCW165[已签署]6.pdf";
        BaseFont baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
        PdfReader pdfReader=new PdfReader(filename);
        PdfStamper pdfStamper=new PdfStamper(pdfReader, new FileOutputStream(destFilePath));
        AcroFields form = pdfStamper.getAcroFields();
        form.addSubstitutionFont(baseFont);

        //写入数据
        for(String key : textFieldMap.keySet()){
            String value=textFieldMap.get(key).toString();
            //key对应模板数据域的名称
            form.setField(key,value, value);
        }
        pdfStamper.setFormFlattening(true);
        pdfStamper.close();
    }


    /***
     * 组装pdf文件中的数据
     * @param custNo
     * @param fundId
     * @param contractTypeCode
     * @param tradeAcco
     * @param subAmt
     * @return
     */
    public Map<String, String> setTextValueInfo(String custNo, String fundId, String contractTypeCode, String tradeAcco, BigDecimal subAmt) {
        Map<String, String> fieldMap = new HashMap<>(16);
        // 只查询个人户信息

        setProductInfo(fundId, contractTemplateTextValueInfo);

        if (CONTRACT_TYPE_HT.equals(contractTypeCode)) {
            fieldMap.put("name", contractTemplateTextValueInfo.getName());
            setIdtypeDesc(contractTemplateTextValueInfo.getIdType(), fieldMap);
            fieldMap.put("idNo", contractTemplateTextValueInfo.getIdNo());
            fieldMap.put("idNo", contractTemplateTextValueInfo.getIdNo());
            setSubAmtChar(contractTemplateTextValueInfo.getIdNo(), fieldMap);
            fieldMap.put("phone", contractTemplateTextValueInfo.getPhone());
            fieldMap.put("addr", contractTemplateTextValueInfo.getAddr());
            fieldMap.put("bankAccoName", contractTemplateTextValueInfo.getName());
            fieldMap.put("bankAcco", contractTemplateTextValueInfo.getBankAcco());
            fieldMap.put("bankNm", contractTemplateTextValueInfo.getBankNm());
            // 处理购买金额，
            fieldMap.put("subAmt", String.valueOf(contractTemplateTextValueInfo.getSubamt()));
            fieldMap.put("subAmtTenTho", String.valueOf(contractTemplateTextValueInfo.getSubamt().divide(BigDecimal.valueOf(10000), 2,  BigDecimal.ROUND_HALF_UP)));
            // 人民币（大写）
            fieldMap.put("subAmtRMBcapital",  MathHelper.number2CNMontrayUnit(subAmt));
            // 人民币（小写）
            setSubAmtChar(subAmt,  fieldMap);
        } else if (CONTRACT_TYPE_FXJSS.equals(contractTypeCode)){
            fieldMap.put("1", "已确认");
        } else if (CONTRACT_TYPE_HFWJ.equals(contractTypeCode)) {
            fieldMap.put("1", "√");
            fieldMap.put("name", contractTemplateTextValueInfo.getName());
            fieldMap.put("fundName", contractTemplateTextValueInfo.getFundName());
            fieldMap.put("custRiskLevel", contractTemplateTextValueInfo.getCustRiskLevel());
            fieldMap.put("riskLevel", contractTemplateTextValueInfo.getRiskLevel());
        }
        fieldMap.put("year", String.valueOf(DateHelper.getCurrentDateYear()));
        fieldMap.put("month", String.valueOf(DateHelper.getCurrentDateMonth()));
        fieldMap.put("day", String.valueOf(DateHelper.getCurrentDateDay()));
        return  fieldMap;
    }

    /**
     * 证件类型
     * @param idType
     * @param fieldMap
     */
    public void setIdtypeDesc(String idType,  Map<String, String> fieldMap) {
        if ("0".equals(idType)) {
            fieldMap.put("idType", "身份证");
            fieldMap.put("idTypeSfz", "√");
        }
    }

    /***
     * 小写数字
     * @param subAmt
     * @param fieldMap
     */
    public void setSubAmtChar(BigDecimal subAmt,  Map<String, String> fieldMap){
        char [] subAmtChar  = MathHelper.numberToCharArray(subAmt);
        for (int i = subAmtChar.length -1;  i < 0; i--) {
            String  keys = "subAmt" + i;
            fieldMap.put(keys, String.valueOf(subAmtChar[i]));
        }
    }
    /***
     * 身份证号码
     * @param idNo
     * @param fieldMap
     */
    public void setSubAmtChar(String idNo,  Map<String, String> fieldMap){
        char [] idNoChar  = idNo.toCharArray();
        for (int i =0;  i < idNoChar.length; i++) {
            String  keys = "idNo" + i;
            fieldMap.put(keys, String.valueOf(idNoChar[i]));
        }
    }
}
