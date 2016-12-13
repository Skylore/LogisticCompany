package model;

public class WorkRequest {

    private String name;
    private String email;
    private String goal;
    private int salary;

    public WorkRequest(String name, String email, String goal, int salary) {
        this.name = name;
        this.email = email;
        this.goal = goal;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getGoal() {
        return goal;
    }

    public int getSalary() {
        return salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WorkRequest that = (WorkRequest) o;

        if (salary != that.salary) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        return goal != null ? goal.equals(that.goal) : that.goal == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (goal != null ? goal.hashCode() : 0);
        result = 31 * result + salary;
        return result;
    }

    @Override
    public String toString() {
        return "WorkRequest{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", goal='" + goal + '\'' +
                ", salary=" + salary +
                '}';
    }
}
