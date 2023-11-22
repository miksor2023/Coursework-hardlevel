package courseworkhw;

import java.util.Locale;

public class Employee {
    private final String name;
    private int department;
    private double salary;
    private int id;
    private static int counter = 1;

    public Employee(String name, int department, double salary) {
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.id = counter;
        counter++;
    }

    public String getName() {
        return name;
    }

    public int getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    public int getId() {
        return id;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "Ф.И.О.: " + name +
                "; отдел: " + department +
                "; зарплата: %.2fр.; id: " + id, salary);
    }
}

