package org.example;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import java.net.URL;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println(chatGPT("how are you?"));
    }

    public static String chatGPT(String massage) {
        String API_URL = "https://api.openai.com/v1/chat/completions";
        String API_KEY = "****";

        try {
            URL obj = new URL(API_URL);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Authorization", "Bearer " + API_KEY);
            con.setRequestProperty("Content-Type", "application/json");

            String body = "{\"model\": \"gpt-3.5-turbo\", \"messages\": [{\"role\": \"user\", \"content\": \"" + massage + "\"}]}";
            con.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
            writer.write(body);
            writer.flush();
            writer.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            return (response.toString().split("\"content\":\"")[1].split("\"")[0].substring(4));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
