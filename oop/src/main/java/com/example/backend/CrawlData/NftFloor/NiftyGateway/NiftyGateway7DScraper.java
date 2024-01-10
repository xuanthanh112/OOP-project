package com.example.backend.CrawlData.NftFloor.NiftyGateway;

import com.example.backend.exceptionhand.Scraper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class NiftyGateway7DScraper implements Scraper {
	
	public Map<String, JSONObject> scrape(){
		Map<String, JSONObject> sex = new LinkedHashMap<>();
        Map<String, String> outputRows = new LinkedHashMap<>();

        int count = 1;
		for(int pageNum = 1; pageNum <= 2; pageNum++) {
			//link file json
			String url = "https://api.niftygateway.com/stats/rankings/?page=" + pageNum + "&page_size=50&sort=-seven_day_total_volume";
			try {
				
				final String data = Jsoup.connect(url).userAgent("Jsoup client").ignoreContentType(true).execute().body();
				JSONObject dataJson = new JSONObject(data);
				JSONArray results = (JSONArray) dataJson.get("results");//result là mảng chứa các đối tượng json
				
				// đọc các đối tượng và lưu lại vào class
				for(Object res : results) {
					JSONObject resJson = (JSONObject) res;
					JSONObject collectionJson = resJson.getJSONObject("collection");
					String name = collectionJson.get("niftyTitle")
							.toString()
							.replace("\"", "")
							.replace(":", "/")
							.replace("\\", "/");
						
					String id = collectionJson.get("niftyContractAddress").toString();
					String image = collectionJson.get("niftyDisplayImage").toString();
					double floorPrice = Double.parseDouble(resJson.getString("floorPrice").toString()) / 100.00;
					int numOfSales = Integer.parseInt(resJson.get("oneDayNumTotalSales").toString());
                    int numOwners = Integer.parseInt(resJson.get("numOwners").toString());
                    double volume = !resJson.get("oneDayTotalVolume").toString().equals("null") ?
                            Double.parseDouble(resJson.get("oneDayTotalVolume").toString())/ 100.0 : 0.0;
                    double volumeChange = !resJson.get("oneDayChange").toString().equals("null") ?
                            Double.parseDouble(resJson.get("oneDayChange").toString()) / 100.0 : 0.0;
                    int totalSupply = Integer.parseInt(resJson.get("totalSupply").toString());

                    String properties =
                            "\"name\":\"" + name + "\"," +
                            "\"id\":\"" + id + "\", " +
                            "\"url\":\"" + image + "\"," +
                            "\"floorPrice\":\"" + floorPrice + "\"," +
                            "\"numOfSales\":\"" + numOfSales + "\"," +
                            "\"numOwners\":\"" + numOwners + "\"," +
                            "\"volume\":\"" + volume + "\"," +
                            "\"volumeChange\":\"" + volumeChange + "\"," +
                            "\"totalSupply\":\"" + totalSupply + "\"";

                    if (!Objects.equals(name, "") && !Objects.equals(name, "null")) {
                        outputRows.put(String.valueOf(count), properties);
                        count++;
                    }
                }
				
                for (Map.Entry<String, String> row: outputRows.entrySet()) {
                    String valueString = '{' + row.getValue() + '}';
                    sex.put(row.getKey(), new JSONObject(valueString));
                }
                
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return sex;
	}
	
	public  void call() {
		NiftyGateway7DScraper ng = new NiftyGateway7DScraper();
		try (FileWriter fileWriter = new FileWriter("Data//NiftyGateway7D.json")) {
            String jsonString= modifyString(ng.scrape().toString());
            jsonString= "[" + removeNumbersBeforeBrace(jsonString) + "]" ;
            fileWriter.write(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
    private static String modifyString(String input) {
        // Biểu thức chính quy để xóa dấu = trước dấu {
        String regex = "=(?=[^{}]*\\{)";

        // Tạo Pattern từ biểu thức chính quy
        Pattern pattern = Pattern.compile(regex);
        String regexHead = "^\\{";
        String regexLast = "\\}$";
        input =input.replaceFirst(regexHead, "");
        input = input.replaceFirst(regexLast, "");
        // Tạo Matcher
        Matcher matcher = pattern.matcher(input);
        // Sử dụng phương thức replaceAll để xóa dấu =
        return matcher.replaceAll("");
    }
    private static String removeNumbersBeforeBrace(String input) {
        // Biểu thức chính quy để xóa số trước dấu {
        String regex = "\\d+(?=[^{}]*\\{)";

        // Tạo Pattern từ biểu thức chính quy
        Pattern pattern = Pattern.compile(regex);

        // Tạo Matcher
        Matcher matcher = pattern.matcher(input);

        // Sử dụng phương thức replaceAll để xóa số
        return matcher.replaceAll("");
    }
}


