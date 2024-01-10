package com.example.backend.CrawlData.NftFloor.Binance;

import com.example.backend.exceptionhand.USDtoETHConversion;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BinanceAPICall1D {
    public  void call() {
        final double usdToEth = USDtoETHConversion.convert();
        try {
            List<String> outputRows = new ArrayList<>();
            // Tạo một URL object với URL của API
            URL url = new URL("https://www.binance.com/bapi/nft/v1/friendly/nft/ranking/trend-collection");

            // Mở kết nối đến URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Đặt phương thức yêu cầu thành POST
            connection.setRequestMethod("POST");

            // Bật cho phép việc gửi dữ liệu trong yêu cầu
            connection.setDoOutput(true);

            // Đặt Header cho việc gửi dữ liệu dưới dạng JSON
            connection.setRequestProperty("Content-Type", "application/json");

            // Dữ liệu JSON bạn muốn gửi
            String jsonData = "{"
                    + "\"network\": \"ALL\","
                    + "\"period\": \"1H\","
                    + "\"sortType\": \"volumeDesc\","
                    + "\"page\": 1,"
                    + "\"rows\": 100"
                    + "}";

            // Ghi dữ liệu JSON vào luồng ghi của yêu cầu
            DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
            dataOutputStream.writeBytes(jsonData);

            // Đóng luồng ghi
            dataOutputStream.close();

            // Nhận mã phản hồi
//            int responseCode = connection.getResponseCode();
            // Đọc phản hồi từ API
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = reader.readLine()) != null) {
                response.append(inputLine);
            }
            reader.close();
            JSONObject json = new JSONObject(response.toString());
            JSONObject data = (JSONObject) json.get("data");
            JSONArray rows = (JSONArray) data.get("rows");

            for (Object row : rows) {
                JSONObject rowJson = (JSONObject) row;
                String name = rowJson.get("title").toString().replace("\"", "'");
                String id = rowJson.get("collectionId").toString();
                String image = rowJson.get("coverUrl").toString();
                double floorPrice = Double.parseDouble(rowJson.get("floorPrice").toString());
                int numOwners = Integer.parseInt(rowJson.get("ownersCount").toString());
                double volume = Double.parseDouble(rowJson.get("volume").toString()) / usdToEth / 100.0;
                double volumeChange = Double.parseDouble(rowJson.get("volumeRate").toString()) / 100.0;
                int totalSupply = Integer.parseInt(rowJson.get("itemsCount").toString());

                String properties =
                        "{"+"\"name\": \"" + name + "\", " +
                                "\"id\": \"" + id + "\", " +
                                "\"url\": \"" + image + "\", " +
                                "\"floorPrice\": \"" + floorPrice + "\", " +
                                "\"numOwners\": \"" + numOwners + "\", " +
                                "\"volume\": \"" + volume + "\", " +
                                "\"volumeChange\": \"" + volumeChange + "\", " +
                                "\"totalSupply\": \"" + totalSupply + "\""+ "}";

                if (!Objects.equals(name, "") && !Objects.equals(name, "null")) {
                    outputRows.add(properties);
                }
            }

            try (FileWriter fileWriter = new FileWriter("Data//Binance1D.json")) {
                fileWriter.write(outputRows.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Đóng kết nối
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


