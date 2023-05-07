package api;

public class ResponseAnswerText {
    String error;

    public ResponseAnswerText(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public ResponseAnswerText() {
    }

}
