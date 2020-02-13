import java.io.*;
import java.util.*;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFRow;

public class Generator {

    public static String fileName = "sampleUsers";
    public static int numUsers = 50000;
    public static String extension = ".csv";

    public static HashSet<String> idSet = new HashSet<String>();
    public static HashSet<String> aniSet = new HashSet<String>();
    public static String filePath = "./" + fileName + "_" + numUsers + extension.toLowerCase();

    public static void main(String[] args) {
        if (extension.contains("csv")) {
            createCSVExcelFile();
        } else if (extension.contains("xls")) {
            createXLSExcelFile();
        }
    }

    public static void createXLSExcelFile() {

        try {

            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Users");

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

                switch (cellNum) {
                case 0:
                    row.createCell(0).setCellValue(generateId());
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

            FileOutputStream fileOut = new FileOutputStream(filePath);
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
            System.out.println("Your excel XLS file for " + numUsers + " users has been generated!");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void createCSVExcelFile() {
        try (PrintWriter writer = new PrintWriter(new File(filePath))) {

            StringBuilder sb = new StringBuilder();
            sb.append("id_number,");
            sb.append("pac,");
            sb.append("dnis,");
            sb.append("ani,");
            sb.append('\n');

            int numCells = 4;
            int length = numUsers * numCells;

            for (int i = 0; i < length; i++) {

                int cellNum = i % 4;

                switch (cellNum) {
                case 0:
                    sb.append(generateId() + ",");
                    break;
                case 1:
                    sb.append("876543" + ",");
                    break;
                case 2:
                    sb.append("9058888888" + ",");
                    break;
                case 3:
                    sb.append(generateAni() + "\n");
                    break;
                }
            }

            writer.write(sb.toString());

            System.out.println("Your excel CSV file for " + numUsers + " users has been generated!");

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
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
        // 9052+6digits unique
        for (;;) {
            Random rnd = new Random();
            int n = 100000 + rnd.nextInt(900000);
            sb.append(9052);
            sb.append(n);
            if (aniSet.contains(sb.toString()) == false) {
                aniSet.add(sb.toString());
                break;
            }

        }
        return sb.toString();

    }

}
