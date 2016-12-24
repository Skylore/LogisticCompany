package model;

public class SupportRequest {

    private String email;
    private String question;

    public SupportRequest(String email, String question) {

        if (email == null || question == null) {
            throw new NullPointerException();
        }

        this.email = email;
        this.question = question;
    }

    public String getEmail() {
        return email;
    }

    public String getQuestion() {
        return question;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SupportRequest that = (SupportRequest) o;

        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        return question != null ? question.equals(that.question) : that.question == null;
    }

    @Override
    public int hashCode() {
        int result = email != null ? email.hashCode() : 0;
        result = 31 * result + (question != null ? question.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SupportRequest{" +
                "email='" + email + '\'' +
                ", question='" + question + '\'' +
                '}';
    }
}
