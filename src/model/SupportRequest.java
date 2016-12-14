package model;

public class SupportRequest {

    private String email;
    private String answer;
    private int id;

    public SupportRequest(String email, String answer, int id) {

        if (email == null || answer == null || id < 0) {
            throw new NullPointerException();
        }

        this.email = email;
        this.answer = answer;
    }

    public String getEmail() {
        return email;
    }

    public String getAnswer() {
        return answer;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "SupportRequest{" +
                "email='" + email + '\'' +
                ", answer='" + answer + '\'' +
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
        return answer != null ? answer.equals(that.answer) : that.answer == null;

    }

    @Override
    public int hashCode() {
        int result = email != null ? email.hashCode() : 0;
        result = 31 * result + (answer != null ? answer.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }
}
