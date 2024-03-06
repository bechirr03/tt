package ChatBot;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.HashMap;
import java.util.Map;

public class ChatController {
    @FXML
    private TextField userInput = new TextField();

    @FXML
    private ListView<String> chatHistory;
    @FXML
    private Button sendButton;

    // Map to store the questions and answers
    private Map<String, ChatbotResponse> chatbotResponses = new HashMap<>();

    public void initialize() {
        // Add some sample questions and answers to the map
        chatbotResponses.put("hello", new ChatbotResponse("I am a chatbot!", "Welcome to ICity !\n how can i help you ?"));
        chatbotResponses.put("how do I sign up for the app",
                new ChatbotResponse("I am a chatbot!",
                        "To sign up, simply navigate to our \n" +
                                "homepage and click on the 'Sign Up'\n" +
                                "button. Follow the prompts to create\n" +
                                "your account."));
        chatbotResponses.put("what features does the app offer ?",
                new ChatbotResponse("I am a chatbot!",
                          "Our app offers a range of features,\n" +
                                "including public transport ticketing\n" +
                                "online marketplace shopping, and \n" +
                                  "event organization. Is there \n" +
                                  "something specific you'd like to \n" +
                                  "know more about?"));

        chatbotResponses.put("How can I find events happening near me?",
                new ChatbotResponse("I am a chatbot!",
                        "Our Events Organization module makes\n" +
                                "it easy to discover events in your \n" +
                                "area. Simply enter your location or \n" +
                                "browse by category to find upcoming \n" +
                                "concerts, festivals, and more"));

       /* chatbotResponses.put("what features does the app offer ?",
                new ChatbotResponse("I am a chatbot!",
                        "Our app offers a range of features,\n" +
                                "including public transport ticketing\n" +
                                "online marketplace shopping, and \n" +
                                "event organization. Is there \n" +
                                "something specific you'd like to \n" +
                                "know more about?"));

        chatbotResponses.put("what features does the app offer ?",
                new ChatbotResponse("I am a chatbot!",
                        "Our app offers a range of features,\n" +
                                "including public transport ticketing\n" +
                                "online marketplace shopping, and \n" +
                                "event organization. Is there \n" +
                                "something specific you'd like to \n" +
                                "know more about?"));*/
    }

    @FXML
    private void sendButtonClicked() {
        String userQuestion = userInput.getText();
        ChatbotResponse response = chatbotResponses.get(userQuestion);

        if (response != null) {
            chatHistory.getItems().add("User\n " + userQuestion);
            chatHistory.getItems().add("Chatbot\n " + response.getAnswer());
        } else {
            chatHistory.getItems().add("User: " + userQuestion);
            chatHistory.getItems().add("Chatbot: I'm sorry, I don't know the answer \nto that question.");
        }

        userInput.clear();
    }
}