package homeWork4;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        
        // Создаем список сотрудников
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Ревунов Денис Петрович",1, "8-901-765-23-47", 14));
        employees.add(new Employee("Котельникова Надежда Ивановна", 2, "8-965-908-78-78", 13));
        employees.add(new Employee("Громин Владимир Семенович", 3, "8-939-059-41-14", 4));
        employees.add(new Employee("Коляденко Ольга Григорьевна", 4, "8-951-285-18-00", 35));
        employees.add(new Employee("Жукова Валентина Александровна", 5, "8-901-666-76-11", 20));
        employees.add(new Employee("Хаджимуратов Мурат Гаджиевич", 6, "8-962-777-99-55", 6));
        employees.add(new Employee("Украинец Людмила Владимировна", 7, "8-962-222-99-10", 17));

        // Создаем объект справочника сотрудников
        EmployeeHandbook list = new EmployeeHandbook(employees);

        // Ищем сотрудников по стажу
        List<Employee> employeeWithExperience = list.findEmployeeByWorkExperience(35);
        System.out.println("Сотрудники со стажем 35 лет:");
        for (Employee employee : employeeWithExperience) {
            System.out.println(employee.getName());
        }

        // Выводим номера телефонов сотрудников по имени
        List<String> PhoneNumByName = list.findPhoneNumbersByName("Коляденко Ольга Григорьевна");
        System.out.println("Номер телефона сотрудника Коляденко Ольга Григорьевна:");
        for (String phoneNumber : PhoneNumByName) {
            System.out.println(phoneNumber);
        }

        // Ищем сотрудника по табельному номеру
        Employee employeeWithCertainID = list.findEmployeeByEmployeeId(6);
        if (employeeWithCertainID != null) {
            System.out.println("Сотрудник с табельным номером 6 найден: " + employeeWithCertainID.getName());
        } else {
            System.out.println("Сотрудник с табельным номером 6 не найден");
        }
    }
}
