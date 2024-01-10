package com.example.backend.CrawlData.Twitter;

import org.json.JSONObject;
import org.jsoup.nodes.Element;
import java.util.List;
import java.util.ListIterator;

import java.io.FileWriter;
import java.io.IOException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TweetHandler {
    public static JSONObject handle(Element element) {
        JSONObject jsonObject;
        String user = element.select("div[class='css-175oi2r r-1awozwy r-18u37iz r-1wbh5a2 r-dnmrzs'] span[class^='css-1qaijid r-bcqeeo r-qvutc0']").text().replace("\"", "'").replace("\\", "|");
        String content = element.select("div[data-testid='tweetText'] span[class^='css-1qaijid r-bcqeeo r-qvutc0']").text().replace("\"", "'").replace("\\", "|");
        List<String> hashtags = element.select("a[href^='/hashtag/']").eachText();
        for (ListIterator<String> hashtag = hashtags.listIterator(); hashtag.hasNext();) {
            hashtag.set("\"" + hashtag.next() + "\"");
        }
        String date = element.select("time").attr("datetime").substring(0, 9);
        //System.out.println("{user: \"" + user + "\", content: \"" + content + "\", hashtags: " + hashtags + ", date: " + date + "}");
        jsonObject = new JSONObject("{user: \"" + user + "\", content: \"" + content + "\", hashtags: " + hashtags + ", date: " + date + "}");
        return jsonObject;
    }
    public static void writeToFile(String json) {
        try (FileWriter fileWriter = new FileWriter("Data//Twitter.json")) {
            String fixedString= modifyString(json);
            fixedString= "[" + removeNumbersBeforeBrace((fixedString)) + "]" ;
            fileWriter.write(fixedString);
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