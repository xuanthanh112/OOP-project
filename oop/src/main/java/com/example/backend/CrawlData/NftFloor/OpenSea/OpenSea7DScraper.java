package com.example.backend.CrawlData.NftFloor.OpenSea;

import com.example.backend.exceptionhand.Scraper;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OpenSea7DScraper implements Scraper {

    public Map<String, JSONObject> scrape() {
        Map<String, JSONObject> sex = new LinkedHashMap<>();
        String url = "https://opensea.io/rankings/trending?sortBy=seven_day_volume";
        List<JSONObject> ops = new ArrayList<>();
        try {
            final String json = Jsoup.connect(url).userAgent("Jsoup client").ignoreContentType(true).execute().body();
            Document doc = Jsoup.parse(json);
            Element element = doc.select("script#__NEXT_DATA__").first();

            String data = element.data();

            JSONObject dataJson = new JSONObject(data);
            JSONObject props = (JSONObject) dataJson.get("props");
            JSONObject pageProps = (JSONObject) props.get("pageProps");
            JSONObject initialRecords = (JSONObject) pageProps.get("initialRecords");

            Map<String, String> outputRows = new LinkedHashMap<>();

            for (String s : initialRecords.keySet()) {
                JSONObject record = (JSONObject) initialRecords.get(s);
                if (record.has("name")) {
                    String id = record.get("__id").toString();
                    String name = record.get("name").toString();
                    String image = record.get("logo").toString();
                    String properties =
                            "\"name\": " + "\"" + name + "\", " +
                                    "\"url\": " + "\"" + image + "\"";
                    if (!outputRows.containsKey(id)) {
                        outputRows.put(id, "\"id\": " + "\"" + id + "\"");
                    }
                    outputRows.put(id, outputRows.get(id) + ", " + properties);
                }
                else if (record.has("symbol")) {
                    String client = record.get("__id").toString();
                    String id = client.split(":")[1];
                    String type = client.split(":")[4];
                    String properties = "";
                    if (Objects.equals(type, "volume")) {
                        properties =
                                "\"volume\": " + "\"" + record.get("unit").toString() + "\"";
                    }
                    else if (Objects.equals(type, "floorPrice")) {
                        properties =
                                "\"floorPrice\": " + "\"" + record.get("unit").toString() + "\"";
                    }
                    if (!outputRows.containsKey(id)) {
                        outputRows.put(id, "\"id\": " + "\"" + id + "\"");
                    }
                    outputRows.put(id, outputRows.get(id) + ", " + properties);
                }
                else if (record.has("floorPrice")) {
                    String id = record.get("__id").toString().split(":")[1];
                    String properties =
                            "\"numOfSales\": " + "\"" + record.get("numOfSales").toString() + "\"" +
                                    ", \"numOwners\": " + "\"" + record.get("numOwners").toString() + "\"" +
                                    ", \"volumeChange\": " + "\"" + record.get("volumeChange").toString() + "\"" +
                                    ", \"totalSupply\": " + "\"" + record.get("totalSupply").toString() + "\"";
                    if (!outputRows.containsKey(id)) {
                        outputRows.put(id, "\"id\": " + "\"" + id + "\"");
                    }
                    outputRows.put(id, outputRows.get(id) + ", " + properties);
                }
            }
            int count =0;
            for (Map.Entry<String, String> row: outputRows.entrySet()) {
                //System.out.println(row.getValue());
                String valueString =  "{" + row.getValue() + '}';
                ops.add(new JSONObject(valueString));
                sex.put( String.valueOf(count), new JSONObject(valueString));
                count++;
            }

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

       return sex;
    }
    public void call() {
        OpenSea7DScraper os = new OpenSea7DScraper();
        try (FileWriter fileWriter = new FileWriter("Data//OpenSea7D.json")) {
            String fixedString= modifyString(os.scrape().toString());
            fixedString= "[" + removeNumbersBeforeBrace((fixedString)) + "]" ;
            fileWriter.write(fixedString);
            //System.out.println(fixedString);
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