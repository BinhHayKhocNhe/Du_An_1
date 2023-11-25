/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Utils;

import java.util.regex.Pattern;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

/**
 *
 * @author Duong Minh Binh
 */
public class IsValidForm {

    public static void refreshForm(JTextComponent... textComponents) {
        for (JTextComponent textComponent : textComponents) {
            textComponent.setText("");
        }
    }

    public static boolean checkNullInformation(JTextField... textFields) {
        for (JTextField textField : textFields) {
            if (textField.getText().isEmpty()) {
                Message.alert(null, "Please enter complete information !");
                textField.requestFocus();
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
}
