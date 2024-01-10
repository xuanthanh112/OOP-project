package com.example.backend.CrawlData.NftFloor.Rarible;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.example.backend.exceptionhand.USDtoETHConversion;
import org.json.JSONArray;
import org.json.JSONObject;

public class RaribleAPICall1D {
    public  void call() {
        final double usdToEth = USDtoETHConversion.convert();
        try {
            // Tạo một URL object với URL của API
            URL url = new URL("https://rarible.com/marketplace/api/v4/collections/search\n");

            // Mở kết nối đến URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Đặt phương thức yêu cầu thành POST
            connection.setRequestMethod("POST");

            // Bật cho phép việc gửi dữ liệu trong yêu cầu
            connection.setDoOutput(true);

            // Đặt Header cho việc gửi dữ liệu dưới dạng JSON
            connection.setRequestProperty("Content-Type", "application/json");

            // Dữ liệu JSON bạn muốn gửi
            String jsonData = "{\n" +
                    "    \"size\": 20,\n" +
                    "    \"filter\": {\n" +
                    "        \"verifiedOnly\": false,\n" +
                    "        \"sort\": \"VOLUME_DESC\",\n" +
                    "        \"blockchains\": [\n" +
                    "            \"ETHEREUM\"\n" +
                    "        ],\n" +
                    "        \"showInRanking\": false,\n" +
                    "        \"period\": \"DAY\",\n" +
                    "        \"hasCommunityMarketplace\": false,\n" +
                    "        \"currency\": \"NATIVE\"\n" +
                    "    }\n" +
                    "}";

            // Ghi dữ liệu JSON vào luồng ghi của yêu cầu
            DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
            dataOutputStream.writeBytes(jsonData);
            // Đóng luồng ghi
            dataOutputStream.close();
            // Đọc phản hồi từ API
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = reader.readLine()) != null) {
                response.append(inputLine);
            }
            reader.close();
            List<String> outputRows = new ArrayList<>();
            JSONArray rows = new JSONArray(response.toString());
            for (Object row : rows) {
                JSONObject rowJson = (JSONObject) row;
                JSONObject collection = (JSONObject) rowJson.get("collection");
                JSONArray coverMedia = (JSONArray) collection.get("coverMedia");
                JSONObject statistics = (JSONObject) rowJson.get("statistics");
                JSONObject collectionStatistics = (JSONObject) collection.get("statistics");

                String name = collection.get("name").toString().replace("\"", "'");
                String id = collection.get("id").toString();
                String image = "";
                if (!coverMedia.isEmpty()) {
                    image = coverMedia.getJSONObject(0).get("url").toString();
                }
                if (!statistics.has("floorPrice")) {
                    continue;
                }
                JSONObject floorPriceOneDay = (JSONObject) statistics.get("floorPrice");
                double floorPrice = Double.parseDouble(floorPriceOneDay.get("value").toString());

                JSONObject usdAmount = (JSONObject) statistics.get("usdAmount");
                double volume = Double.parseDouble(usdAmount.get("value").toString()) / usdToEth;
                double volumeChange = usdAmount.has("changePercent") ?
                        Double.parseDouble(usdAmount.get("changePercent").toString()) / 100.0 : 0.0;

                int numOwners = Integer.parseInt(collectionStatistics.get("ownerCountTotal").toString());
                int totalSupply = Integer.parseInt(collectionStatistics.get("itemCountTotal").toString());

                String properties = "{"  +
                        "\"name\": \"" + name + "\", " +
                                "\"id\": \"" + id + "\", " +
                                "\"url\": \"" + image + "\", " +
                                "\"floorPrice\": \"" + floorPrice + "\", " +
                                "\"numOwners\": \"" + numOwners + "\", " +
                                "\"volume\": \"" + volume + "\", " +
                                "\"volumeChange\": \"" + volumeChange + "\", " +
                                "\"totalSupply\": \"" + totalSupply + "\""
                        +"}";

                if (!Objects.equals(name, "") && !Objects.equals(name, "null")) {
                    outputRows.add(properties);
                }
            }


            try (FileWriter fileWriter = new FileWriter("Data//rarible1D.json")) {
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