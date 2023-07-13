package com.openai;

import com.openai.types.Message;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

public class Prompt {
    public static JSONArray convertToJsonArray(List<Message> messages) {

        JSONArray jsonArray = new JSONArray();

        for (Message message : messages) {

            JSONObject jsonMessage = new JSONObject();
            jsonMessage.put("role", message.role());
            jsonMessage.put("content", message.content());

            jsonArray.put(jsonMessage);

        }

        return jsonArray;

    }

    public static JSONObject convertToJsonObject(Map<String, Object> map) {

        JSONObject jsonObject = new JSONObject();

        for (Map.Entry<String, Object> entry : map.entrySet())
            jsonObject.put(entry.getKey(), entry.getValue());

        return jsonObject;

    }


    public static JSONObject makeChatCompletionCall(Parameters parameters, Conversation conversation) {

        JSONObject prompt = convertToJsonObject(parameters.parameters);
        JSONArray messages = convertToJsonArray(conversation.getMessageHistory());

        prompt.put("messages", messages);

        return prompt;

    }


}
