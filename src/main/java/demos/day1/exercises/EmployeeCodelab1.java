package demos.day1.exercises;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class EmployeeCodelab1 {
    public static void main(String[] args) {
        EmployeeCodelab1 instance = new EmployeeCodelab1();
        List<Employee> employees = new ArrayList(List.of(
                new Employee("Alice", LocalDate.of(1985, 5, 15), "HR", 60000),
                new Employee("Bob", LocalDate.of(1990, 8, 20), "IT", 75000),
                new Employee("Charlie", LocalDate.of(1975, 12, 30), "Finance", 80000),
                new Employee("Diana", LocalDate.of(2000, 3, 10), "IT", 50000),
                new Employee("Eve", LocalDate.of(1995, 7, 25), "HR", 62000),
                new Employee("Frank", LocalDate.of(1988, 11, 5), "Finance", 72000),
                new Employee("Frankie", LocalDate.of(1989, 11, 3), "R/D", 7200),
                new Employee("Frankenstein", LocalDate.of(1987, 11, 4), "Front", 720),
                new Employee("Grace", LocalDate.of(1992, 1, 17), "IT", 68000)
        ));
        // 1. Find employees with highest salary
        Employee highestSal = employees.stream().max((emp1,emp2)-> emp1.salary > emp2.salary?1:emp1.salary == emp2.salary?0:-1).get();
        System.out.println(highestSal);
        // 2. Count the number of employees in each department
        Map<String, Long> empCountByDept = employees.stream().collect(Collectors.groupingBy(emp -> emp.department, Collectors.counting()));
        System.out.println(empCountByDept);
        // 3. Group employees by department and calculate the average salary for each department.
        Map<String, Double> empAvgSalary = employees.stream().collect(Collectors.groupingBy(e->e.department, Collectors.averagingDouble(e->e.getSalary())));
        System.out.println(empAvgSalary);
        // 4. Filter and display employees whose salary is above a certain threshold.
        System.out.println(instance.getEmpWithSalAbove(60000, employees));
        // 5. Calculate the age of each employee based on their birthdate.
        Function<Employee, Integer> getAge = emp -> Period.between(emp.birthDate, LocalDate.now()).getYears();
        Map<Employee, Integer> ageMap = employees
                .stream()
                .map(e -> Map.entry(e, e.getName().length()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                ));
//                .collect(Collectors.toMap(Function.identity(), getAge)); // Function.identety() gives us the element in the stream
        System.out.println(ageMap);
        // 6. Calculate the average age of all employees.
        OptionalDouble averageSalary = employees.stream()
                .mapToInt(emp->getAge.apply(emp)).average();
        System.out.println(averageSalary.getAsDouble());
        // 7. Find the three oldest employees.
        Set<Employee> oldest3 = employees.stream().sorted((emp1,emp2)->getAge.apply(emp2)-getAge.apply(emp1)).limit(3).collect(Collectors.toSet());
        System.out.println(oldest3);
        // 8. Filter and display employees who have birthdays in a specific month.
        Set<Employee> bornInNov = instance.getEmpBornInMonth(11, employees);
        System.out.println(bornInNov);
        // 9. Group employees by birth month and display the count of employees in each group.
        Map<Integer, Long> birthMonthGroups = employees
                .stream()
                .collect(Collectors
                        .groupingBy(
                                (emp)->emp.birthDate.getMonth().getValue(),
                                Collectors.counting()
                        ));
        System.out.println(birthMonthGroups);
        // 10. List all employees who has a birthday in the current month.
        Set<Employee> empsBornThisMonth = employees.stream()
                .filter((emp)->emp.birthDate.getMonth().getValue() == LocalDate.now().getMonthValue())
                .collect(Collectors.toSet());
        System.out.println(empsBornThisMonth);
        // 11. Create a method to sort employees based on different criteria, such as age, salary, or name.
        System.out.println("Sort by NAMES: "+instance.getSortedEmps(employees, "name"));
        System.out.println("Sort by DEPARTMENTS: "+instance.getSortedEmps(employees, "department"));
        System.out.println("Sort by BIRTHDAY: "+instance.getSortedEmps(employees, "birthday"));
        System.out.println("Sort by SALARY: "+instance.getSortedEmps(employees, "salary"));



    }

    public List<Employee> getEmpWithSalAbove(int threshold, List<Employee> emps){
        Predicate<Employee> salaryAboveThreshold = (emp)-> emp.salary > threshold;
        return emps
                .stream()
                .filter(salaryAboveThreshold)
                .toList();
    }
    public Set<Employee> getEmpBornInMonth(int month, List<Employee> employees){
        return employees
                .stream()
                .filter((emp)->emp.birthDate.getMonth().getValue() == month)
                .collect(Collectors.toSet());
    }
    public List<Employee> getSortedEmps(List<Employee> emps, String criteria){
        return switch (criteria) {
            case "name" ->
                    emps.stream().sorted((emp1, emp2) -> emp1.name.compareTo(emp2.name)).collect(Collectors.toList());
            case "birthday" ->
                    emps.stream().sorted((emp1, emp2) -> emp1.birthDate.compareTo(emp2.birthDate)).collect(Collectors.toList());
            case "salary" ->
                    emps.stream().sorted((emp1, emp2) -> Double.compare(emp1.salary, emp2.salary)).collect(Collectors.toList());
            case "department" ->
                    emps.stream().sorted((emp1, emp2) -> emp1.department.compareTo(emp2.department)).collect(Collectors.toList());
            default ->
                    throw new IllegalArgumentException("Sorting criteria must be one of these: 'name', 'birthday', 'salary' or 'department'");
        };
    }

    @Data
    @AllArgsConstructor
    public static class Employee{
        String name;
        LocalDate birthDate;
        String department;
        double salary;
    }
}
