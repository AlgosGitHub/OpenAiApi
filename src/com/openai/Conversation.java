package com.openai;

import com.openai.types.Message;

import java.util.ArrayList;
import java.util.List;

public class Conversation {
    private List<Message> messageHistory;

    public Conversation() {
        messageHistory = new ArrayList<>();
    }

    public void addMessage(String role, String message) {
        Message newMessage = new Message(role, message);
        messageHistory.add(newMessage);
    }

    public void insertMessage(int index, String role, String message) {
        Message newMessage = new Message(role, message);
        messageHistory.add(index, newMessage);
    }

    public void replaceMessage(int index, String role, String message) {
        if (index >= 0 && index < messageHistory.size()) {
            Message newMessage = new Message(role, message);
            messageHistory.set(index, newMessage);
        } else {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
    }

    public void removeMessage(int index) {
        if (index >= 0 && index < messageHistory.size()) {
            messageHistory.remove(index);
        } else {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
    }

    public void clearMessageHistory() {
        messageHistory.clear();
    }

    // Getter for messageHistory, if needed
    public List<Message> getMessageHistory() {
        return messageHistory;
    }


}
