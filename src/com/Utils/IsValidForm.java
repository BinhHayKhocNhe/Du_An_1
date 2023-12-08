/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

/**
 *
 * @author Duong Minh Binh
 */
public class IsValidForm {

    private static final String datePattern = "yyyy-MM-dd";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);

    public static void refreshForm(JTextComponent... textComponents) {
        for (JTextComponent textComponent : textComponents) {
            textComponent.setText("");
        }
    }

    public static boolean checkNull(JTextComponent... textComponents) {
        for (JTextComponent textComponent : textComponents) {
            if (textComponent.getText().isEmpty()) {
                Message.alert(null, "Please enter complete information !");
                textComponent.requestFocus();
                return false;
            }
        }
        return true;
    }

    public static boolean isValidEmail(String email) {
        final Pattern pattern
                = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
        return pattern.matcher(email).matches();
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("\\d{10}");
    }

    public static void fillComboBox(DefaultComboBoxModel model, List<?> data, JComboBox comboBox) {
        model.removeAllElements();
        for (Object item : data) {
            model.addElement(item);
        }
        comboBox.setModel(model);
    }

    public static boolean isValidDate(String dateString) {
        try {
            dateFormat.setLenient(false); // Tắt tính linh hoạt của định dạng
            Date inputDate = dateFormat.parse(dateString);

            // Kiểm tra nếu là ngày đã qua
            Date currentDate = new Date();
            if (inputDate.before(currentDate)) {
                Message.alert(null, "Cannot enter a date in the past.");
                return false;
            }

            return true; // Nếu không có ngoại lệ và ngày không phải là ngày đã qua, chuỗi là hợp lệ
        } catch (ParseException e) {
            Message.alert(null, "Invalid date string. Note (yyyy-MM-dd)");
            return false; // Nếu có ngoại lệ, chuỗi không hợp lệ
        }
    }

    //kiểm tra ngày hiện tại
    public static boolean canUpdateSchedule(Date schoolDay) {
        try {
            Date currentDate = new Date();
            // Kiểm tra nếu ngày trong trường School_Day đã qua ngày hiện tại
            return schoolDay.after(currentDate);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
