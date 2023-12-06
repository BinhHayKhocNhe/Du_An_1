/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Utils;

import com.Entity.Teacher;
import org.apache.poi.ss.usermodel.*;
import java.sql.ResultSet;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author Duong Minh Binh
 */
public class ExcelUtils {

    public static Workbook exportToExcelTeacher(List<Teacher> dataList) {
        final String sql = "SELECT * FROM Teacher;";
        Workbook workbook = new HSSFWorkbook();
        try {
            Sheet sheet = workbook.createSheet("Data Sheet");
            // Tạo header
            Row headerRow = sheet.createRow(0);
            String[] columns = {"ID Teacher", "First Name", "Middle Name", "Last Name", "Email", "Phone", "Gender",
                "Status", "Level", "Address", "Date of birth"};
            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
            }
            ResultSet resultSet = JDBCHelper.executeQuery(sql);
            int rowNum = 1;
            while (resultSet.next()) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(resultSet.getString("ID_Teacher"));
                row.createCell(1).setCellValue(resultSet.getString("First_Name"));
                row.createCell(2).setCellValue(resultSet.getString("Middle_Name"));
                row.createCell(3).setCellValue(resultSet.getString("Last_Name"));
                row.createCell(4).setCellValue(resultSet.getString("Email"));
                row.createCell(5).setCellValue(resultSet.getString("Phone_Number"));
                
                boolean isMale = resultSet.getBoolean("Gender");
                String genderLabel = (isMale) ? "Male" : "Female";
                row.createCell(6).setCellValue(genderLabel);
                
                boolean isStatus = resultSet.getBoolean("Status_Teacher");
                String status = (isStatus) ? "ON" : "OFF";
                row.createCell(7).setCellValue(status);
                
                row.createCell(8).setCellValue(resultSet.getString("Level_Teacher"));
                row.createCell(9).setCellValue(resultSet.getString("Address_Teacher"));
                row.createCell(10).setCellValue(resultSet.getString("Year_Of_Birth") + "-"
                        + resultSet.getString("Month_Of_Birth") + "-" + resultSet.getString("Date_Of_Birth"));
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return workbook;
    }
}
