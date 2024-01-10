package com.example.backend.exceptionhand;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter {

    // Phương thức chuyển đổi ngày tháng từ một định dạng sang định dạng khác
    public static String convertDate(String inputDate, String inputFormatPattern, String outputFormatPattern) {
        try {
            // Định dạng mẫu của chuỗi đầu vào
            SimpleDateFormat inputFormat = new SimpleDateFormat(inputFormatPattern);

            // Định dạng mẫu mới cho định dạng ngày giờ chuẩn
            SimpleDateFormat outputFormat = new SimpleDateFormat(outputFormatPattern);

            // Parse chuỗi đầu vào thành đối tượng Date
            Date date = inputFormat.parse(inputDate);

            // Định dạng lại thành chuỗi theo định dạng mới
            return outputFormat.format(date);

        } catch (ParseException e) {
            // Nếu không thể phân tích được, in thông báo lỗi và trả về chuỗi gốc
            System.err.println("Error parsing date: " + inputDate);
            e.printStackTrace();
            return inputDate;
        }
    }
}
