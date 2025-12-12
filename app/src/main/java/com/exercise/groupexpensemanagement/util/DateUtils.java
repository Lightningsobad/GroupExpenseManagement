package com.exercise.groupexpensemanagement.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    private static final String DEFAULT_FORMAT = "dd/MM/yyyy";

    private static final SimpleDateFormat OUTPUT_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private static final SimpleDateFormat INPUT_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    // Hàm format mặc định dd/MM/yyyy
    public static String format(Date date) {
        if (date == null) return "";
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_FORMAT, Locale.getDefault());
        return sdf.format(date);
    }

    // Hàm format theo format tùy chọn
    public static String format(Date date, String pattern) {
        if (date == null) return "";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.getDefault());
        return sdf.format(date);
    }

    public static Date convertToDate(String dateStr) {
        try {
            // Parse từ string vào thành Date
            Date date = INPUT_FORMAT.parse(dateStr);

            // Format lại đúng định dạng yyyy-MM-dd rồi parse lần nữa
            String formatted = OUTPUT_FORMAT.format(date);
            return OUTPUT_FORMAT.parse(formatted);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String convertToServerDate(String inputDate) {
        try {
            // Bước 1: Parse từ format người dùng nhập dd/MM/yyyy
            SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            Date date = inputFormat.parse(inputDate);

            // Bước 2: Format sang chuẩn API yyyy-MM-dd'T'HH:mm:ss
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
            return outputFormat.format(date);

        } catch (Exception e) {
            e.printStackTrace();
            return null; // hoặc trả về ""
        }
    }
}
