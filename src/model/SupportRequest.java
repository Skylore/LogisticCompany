package model;

import java.io.Serializable;

public class SupportRequest implements Serializable{

    private String email;
    private String question;
    private int id;

    public SupportRequest(String email, String question, int id) {

        if (email == null || question == null || id < 0) {
            throw new NullPointerException();
        }

        this.email = email;
        this.question = question;
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public String getQuestion() {
        return question;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "SupportRequest{" +
                "email='" + email + '\'' +
                ", question='" + question + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SupportRequest that = (SupportRequest) o;

        if (id != that.id) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        return question != null ? question.equals(that.question) : that.question == null;

    }

    @Override
    public int hashCode() {
        int result = email != null ? email.hashCode() : 0;
        result = 31 * result + (question != null ? question.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }
}
