package co.fastestpath.api.feedback;


class Feedback {

    private final String email;
    private final String body;

    public Feedback(String email, String body) {
        this.email = email;
        this.body = body;
    }

    public String getEmail() {
        return email;
    }

    public String getBody() {
        return body;
    }
}
