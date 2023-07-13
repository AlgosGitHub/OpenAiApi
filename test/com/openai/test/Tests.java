package com.openai.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openai.*;
import com.openai.types.ChatCompletionRecord;
import com.openai.types.Choice;
import org.json.JSONObject;

import java.util.List;

import static com.openai.test.SECRETS.apiKey;

public class Tests {

    String systemPrompt = "You will be given a set of company names. Output a CSV with all values in quotes that has the following columns: \n" +
            "- (String) [\"Company\"] The Company Name\n" +
            "- (String) [\"URL\"] Company's Official Website URL. Make an educated guess if you don't know it. Only include the scheme, second-level and top-level domain. Don't include subdomains, subdirectories, or queries.\n" +
            "- (Boolean) [\"Guess\"] {True or False} Was this URL an educated guess? \n" +
            "- (String) [\"Confidence\"] {High, Medium, or Low} Confidence level that the inserted URL is the correct one. ";

    String testInput = "APPLE INC, TESLA MOTORS, FORD MOTORS, CARVANA, PELOTON, META PLATFORMS, WISDOMTREE TARGET DATE FUND";

    public void testCharlie() throws Exception {

        Parameters parameters = new Parameters();
        parameters.setParameter("model", "gpt-3.5-turbo");
        parameters.setParameter("temperature", 0);
        parameters.setParameter("n", 3);
        //max tokens, stop, etc.

        Conversation conversation = new Conversation();

        conversation.addMessage("system", systemPrompt);
        conversation.addMessage("user", testInput);

        JSONObject apiCall = Prompt.makeChatCompletionCall(parameters, conversation);

        ChatCompletionRecord response = OpenAiAPI.send(apiKey, apiCall);

        for(Choice choice : response.choices()) {
            System.out.println(choice);
        }

    }

    public void testRecords() throws Exception {
        String jsonString = "{\"id\":\"chatcmpl-7OdSvXz3SXFLy1jVgiFn8pLooXzfX\",\"object\":\"chat.completion\",\"created\":1686105933,\"model\":\"gpt-3.5-turbo-0301\",\"usage\":{\"prompt_tokens\":10,\"completion_tokens\":10,\"total_tokens\":20},\"choices\":[{\"message\":{\"role\":\"assistant\",\"content\":\"Hello there! How can I assist you today?\"},\"finish_reason\":\"stop\",\"index\":0}]}";

        ObjectMapper objectMapper = new ObjectMapper();
        ChatCompletionRecord record = objectMapper.readValue(jsonString, ChatCompletionRecord.class);

        // Access the record's fields
        System.out.println(record.id());
        System.out.println(record.created());
        System.out.println(record.model());
        System.out.println(record.usage().promptTokens());
        System.out.println(record.choices()[0].message().content());
    }

    public static void main(String[] args) throws Exception {
        Tests tests = new Tests();
        tests.testCharlie();
    }

}
