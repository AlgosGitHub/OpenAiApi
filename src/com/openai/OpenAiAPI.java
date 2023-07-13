package com.openai;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openai.types.ChatCompletionRecord;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class OpenAiAPI {

    private static final String API_ENDPOINT = "https://api.openai.com/v1/chat/completions";

    private static String processResponseStream(InputStream inputStream) throws Exception {

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;

        StringBuilder response = new StringBuilder();
        while ((line = reader.readLine()) != null) response.append(line);

        reader.close();

        String responseStr = response.toString();

        return responseStr;

    }

    public static ChatCompletionRecord send(String apiKey, JSONObject requestBody) throws Exception {

        URL url = new URL(API_ENDPOINT);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Authorization", "Bearer " + apiKey);
        connection.setDoOutput(true);

        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(requestBody.toString().getBytes());
        outputStream.flush();

        int responseCode = connection.getResponseCode();

        if(responseCode != 200)
            throw new IllegalStateException("Response Code = " + responseCode);

        // responseCode == 200

        // pull the json response from our HTTP call

        String responseStr = processResponseStream(connection.getInputStream());

        // wrap up the connection, we're done with our API call

        connection.disconnect();

        // process json into pojo

        return new ObjectMapper().readValue(responseStr, ChatCompletionRecord.class);

    }

}
