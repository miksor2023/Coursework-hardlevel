package courseworkhw;

import java.util.Locale;

public class EmployeeBook {
    public int subArraySize;//содержит количество записей в подмассиве без null-полей
    private final Employee[] employees;
    public EmployeeBook() {
        this.employees = new Employee[10];
    }


    //служебный метод, возврящает количество сотрудников" по заданному отделу, если dept=0, считается общее число
    private int getEmployeeQty(int dept) {
        int employeesQty = 0;
        for (Employee employee : employees ) {
            if (employee != null && (employee.getDepartment() == dept || dept == 0)) {
                employeesQty++;
            }
        }
        return employeesQty;
    }
    //метод - "вывести в консоль список сотрудников" (без null полей)
    public void printEmployeeListWithoutNull () {
        System.out.println("\nСписок данных всех сотрудников:");
        for (Employee employee : employees) {
            if (employee != null) {
                System.out.println(employee);
            }
        }
    }
    //метод "добавить сотрудника"
    public void addEmployee(String name, int department, double salary) {
        int flagEmployeeAdded = 0;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] == null) {
                Employee newEmloyee = new Employee(name, department, salary);
                employees[i] = newEmloyee;
                System.out.println("Запись о сотруднике " + employees[i].getName() + " ДОБАВЛЕНА");
                flagEmployeeAdded = 1;
                break;
            }
        }
        if (flagEmployeeAdded == 0) {//если запись не была сделана по причине отсутствия свободной ячейки...
            System.out.println("Запись добавить невозможно, хранилище заполнено");
        }
    }
    //метод "удалить сотрудника"
    public void deleteEmployee(int id) {
        for (Employee employee : employees) {
            if (employee != null && employee.getId() == id) {
                    System.out.println("\nЗапись о сотруднике " + employee.getName() + " УДАЛЕНА");
                    employee = null;
                    break;
            }
        }
    }
    //метод "посчитать сумму затрат в месяц"
    public double calculateSalarySum() {
        double sum = 0;
        for (Employee employee : employees) {
            if (employee != null) {
                sum = sum + employee.getSalary();
            }
        }
        return sum;
    }
    //метод "найти сотрудника с минимальной зарплатой"
    public String getMinSalaryEmploee() {
        String nameOfMinSalaryEmployee = null;
        Double minSalary = Double.MAX_VALUE;
        for (Employee employee: employees) {
            if(employee != null && employee.getSalary() < minSalary) {
                minSalary = employee.getSalary();
                nameOfMinSalaryEmployee = employee.getName();
            }
        }
        return nameOfMinSalaryEmployee;
    }

    //метод "найти сотрудника с максимальной зарплатой"
    public String getMaxSalaryEmploee() {
        String nameOfMaxSalaryEmployee = null;
        double maxSalary = Double.MIN_VALUE;
        for (Employee employee: employees) {
            if (employee != null && employee.getSalary() > maxSalary) {
                maxSalary = employee.getSalary();
                nameOfMaxSalaryEmployee = employee.getName();
            }
        }
        return nameOfMaxSalaryEmployee;
    }
    //метод "расчитать среднее значение зарплаты"
    public double calculateAverageSalary() {
        int employeesQty = getEmployeeQty(0);
        if (employeesQty != 0) {
            return calculateSalarySum()/employeesQty;
        } else {
            return 0D;
        }
    }

    //метод "вывести в консоль ФИО всех сотрудников"
    public void printNameList() {
        System.out.println("\nСписок имён всех сотрудников:");
        for (Employee employee : employees) {
            if (employee != null) {
                System.out.println(employee.getName());
            }
        }
    }
    //метод "индексация зарплат на заданный процент
    public void makeSalaryIndexation (int indexationPercentage) {
        double increaseRate = 1 + indexationPercentage / 100D;
        for (Employee employee : employees) {
            if (employee != null) {
                employee.setSalary(employee.getSalary() * increaseRate);
            }
        }
        System.out.println("\nЗарплата проиндексирована на " + indexationPercentage + "%");
    }
    //делаем служебный метод, собирающий массив сотрудников по заданному отделу
    private Employee[] makeDepartmentArray(int department) {
        //собираем массив сотрудника отдела
        int deptEmploeeIndex = 0;
        Employee[] deptEmployees = new Employee[getEmployeeQty(department)];
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null && employees[i].getDepartment() == department) {
                deptEmployees[deptEmploeeIndex] = employees[i];
                deptEmploeeIndex++;
            }
        }
        return deptEmployees;
    }
    //метод "вывести в консоль список сотрудников по заданному отделу"
    public void printDepartmentEmployeeList(int department) {
        Employee[] deptEmployees = makeDepartmentArray(department);
        if (deptEmployees.length != 0) {
            System.out.println("\nСписок сотрудников " + department + " отдела");
            for (Employee employee : deptEmployees) {
                System.out.printf(Locale.US, "Ф.И.О.: " + employee.getName() +
                        "; зарплата: %.2fр.; id: " + employee.getId() +
                        "\n", employee.getSalary());
            }
        } else {
            System.out.println("В отделе нет сотрудников");
        }
    }

    //метод "ищем сотрудника с минимальной зарплатой по отделу"
    public String getMinSalaryEmploeeInDept(int department) {
        Employee[] deptEmploees = makeDepartmentArray(department);
        String nameOfMinSalaryEmployee = null;
        Double minSalary = Double.MAX_VALUE;
        for (Employee employee: deptEmploees) {
            if(employee != null && employee.getSalary() < minSalary) {
                minSalary = employee.getSalary();
                nameOfMinSalaryEmployee = employee.getName();
            }
        }
        return nameOfMinSalaryEmployee;
    }


    //метод "ищем сотрудника с максимальной зарплатой по отделу"
    public String getMaxSalaryEmployeeInDept(int department) {
        Employee[] deptEmployees = makeDepartmentArray(department);
        String nameOfMaxSalaryEmployee = null;
        double maxSalary = Double.MIN_VALUE;
        for (Employee employee: deptEmployees) {
            if (employee != null && employee.getSalary() > maxSalary) {
                maxSalary = employee.getSalary();
                nameOfMaxSalaryEmployee = employee.getName();
            }
        }
        return nameOfMaxSalaryEmployee;
    }

    //метод "расчитать сумму затрат по отделу"
    public double calculateDeptSalarySum(int department) {
        Employee[] deptEmployees = makeDepartmentArray(department);
        if (deptEmployees.length != 0) {//проверка на пустой отдел
            double sum = 0;
            for (Employee employee : deptEmployees) {
                sum = sum + employee.getSalary();
            }
            return sum;
        } else {
            System.out.println("В базе нет сотрудников из отдела " + department);
            return 0;
        }
    }

    //метод "расчтать среднюю зарплату по отделу"
    public double calculateAverageDeptSalary(int department) {
        Employee[] deptEmployees = makeDepartmentArray(department);
        if (deptEmployees.length != 0) {//проверка на пустой отдел
            //условие - если количество людей в отделе совпадает с общим числом сотрудников
            if (deptEmployees.length == getEmployeeQty(0)) {
                throw new IllegalArgumentException("количество людей в отделе совпадает с общим числом сотрудников");
            } else {
                return calculateDeptSalarySum(department) / deptEmployees.length;
            }
        } else {
            System.out.println("В базе нет сотрудников из отдела " + department);
            return 0;
        }
    }

    //метод "индексировать зарплаты по отделу на заданный процент
    public void makeDeptSalaryIndexation (int indexationPercentage, int department) {
        Employee[] deptEmployees = makeDepartmentArray(department);
        if (deptEmployees.length != 0) {//проверка на пустой отдел
            double increaseRate = 1 + indexationPercentage / 100D;
            for (Employee employee : deptEmployees) {
                employee.setSalary(employee.getSalary() * increaseRate);
            }
            System.out.println("\nЗарплата по отделу " + department + " проиндексирована на " + indexationPercentage + "%");
        } else {
            System.out.println("В базе нет сотрудников из отдела " + department);
        }
    }

    //метод для вывода списка сотрудников с ЗП меньше заданного значения
    public void printEmployeesWithSalaryLessThan (double edgeSalary) {
        System.out.println("\nСписок сотрудников с зарплатой меньше " + edgeSalary + "р.");
        for (Employee employee : employees) {
            if (employee != null && employee.getSalary() < edgeSalary) {
                System.out.printf(Locale.US, "id: " + employee.getId() + "; Ф.И.О.: " +
                        employee.getName() + "; зарплата: %.2fр.\n", employee.getSalary());
            }
        }
    }

    //метод для вывода списка сотрудников с ЗП больше заданного значения
    public void printEmployeesWithSalaryMoreThan (double edgeSalary) {
        System.out.println("\nСписок сотрудников с зарплатой больше или равно " + edgeSalary + "р.");
        for (Employee employee : employees) {
            if (employee != null && employee.getSalary() >= edgeSalary) {
                System.out.printf(Locale.US, "id: " + employee.getId() + "; Ф.И.О.: " +
                        employee.getName() + "; зарплата: %.2fр.\n", employee.getSalary());
            }
        }
    }
    //метод изменить сотрудника
    public void modifyEmployee(String name, int department, int salary) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null) {//проверка на пустую ячейку
                if (employees[i].getName().equals(name)) {
                    employees[i].setDepartment(department);
                    employees[i].setSalary(salary);
                }
            }
        }
    }
    //метод "напечатать список отделов и их сотрудников"
    public void printEployeeListByDepartments() {
        System.out.println("\nCписок сотрудников по отделам: ");
        for (int department = 1; department <= 5; department++) {
            Employee[] deptEmployees = makeDepartmentArray(department);
            System.out.println("\nСписок сотрудников " + department + " отдела");
            for (int i = 0; i < deptEmployees.length; i++) {
                System.out.println("Ф.И.О.: " + deptEmployees[i].getName());
            }
            if (deptEmployees.length == 0) {
                System.out.println("В отделе нет сотрудников");
            }
        }
    }

}

