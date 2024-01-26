package homeWork4;

public class Employee {
    private String name;
    private int employeeID;
    private String phoneNumber;
    private int workExperience;

    public Employee(String name, int employeeID, String phoneNumber, int workExperience) {
        this.name = name;
        this.employeeID = employeeID;
        this.phoneNumber = phoneNumber;
        this.workExperience = workExperience;
    }

    public String getName() {
        return name;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getWorkExperience() {
        return workExperience;
    }

}
