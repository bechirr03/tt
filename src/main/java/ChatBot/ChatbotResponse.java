package ChatBot;

public class ChatbotResponse {
    private String question;
    private String answer;

    public ChatbotResponse(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }
}