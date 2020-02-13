package org.apache.commons.math3.util;

import java.math.BigInteger;

import java.io.*;
import java.util.*;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFRow;

public class Harris {

    public static String fileName = "./excelsheet.xls";
    public static HashSet<String> idSet = new HashSet<String>();
    public static HashSet<String> aniSet = new HashSet<String>();
    public static int numUsers = 10;

    public static void main(String[] args) {
        createExcelFile();
    }

    public static void createExcelFile() {

        try {

            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("FirstSheet");

            HSSFRow rowhead = sheet.createRow((short) 0);
            rowhead.createCell(0).setCellValue("id_number");
            rowhead.createCell(1).setCellValue("pac");
            rowhead.createCell(2).setCellValue("dnis");
            rowhead.createCell(3).setCellValue("ani");

            int numCells = 4;
            int length = numUsers * numCells;

            int rowNum = 1;
            HSSFRow row = sheet.createRow((short) rowNum);

            for (int i = 0; i < length; i++) {

                int cellNum = i % 4;
                System.out.print("\n|cell " + cellNum);

                switch (cellNum) {
                case 0:
                    row.createCell(0).setCellValue(generateId());
                    System.out.print("row " + rowNum + "\n");
                    break;
                case 1:
                    row.createCell(1).setCellValue("876543");
                    break;
                case 2:
                    row.createCell(2).setCellValue("9058888888");
                    break;
                case 3:
                    row.createCell(3).setCellValue(generateAni());
                    rowNum++;
                    row = sheet.createRow((short) rowNum);
                    break;
                }
            }

            FileOutputStream fileOut = new FileOutputStream(fileName);
            System.out.println("hellllllllllo");
            workbook.write(fileOut);
            System.out.println("goodbye");
            fileOut.close();
            workbook.close();
            System.out.println("Your excel file has been generated!");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String generateId() {
        StringBuilder sb = new StringBuilder();
        // 0+8digits unique
        for (;;) {
            Random rnd = new Random();
            int n = 10000000 + rnd.nextInt(90000000);
            sb.append(0);
            sb.append(n);
            if (idSet.contains(sb.toString()) == false) {
                idSet.add(sb.toString());
                // System.out.println(sb.toString());
                break;
            }

        }
        return sb.toString();

    }

    public static String generateAni() {
        StringBuilder sb = new StringBuilder();
        // 0+8digits unique
        for (;;) {
            Random rnd = new Random();
            int n = 100000 + rnd.nextInt(900000);
            sb.append(0);
            sb.append(n);
            if (aniSet.contains(sb.toString()) == false) {
                aniSet.add(sb.toString());
                // System.out.println(sb.toString());
                break;
            }

        }
        return sb.toString();

    }

}
