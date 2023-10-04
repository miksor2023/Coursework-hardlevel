package courseworkhw;

import java.util.Locale;

public class EmployeeBook {
    public static int subArraySize;//содержит количество записей в подмассиве без null-полей
    private final Employee[] employees;
    public EmployeeBook() {
        this.employees = new Employee[10];
    }

    //служебный метод сборка подмассива без пустых полей
    public Employee[] createSubArrayWithoutNull() {
        Employee[] employeesSubArray = new Employee[employees.length];
        subArraySize = 0;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null) {
                employeesSubArray[subArraySize] = employees[i];
                subArraySize++;
            }
        }
        return employeesSubArray;
    }

    //метод - "вывести в консоль список сотрудников" (без null полей)
    public void printEmployeeListWithoutNull () {
        Employee[] subArray = createSubArrayWithoutNull();
        System.out.println("\nCписок сотрудников: ");
        for (int i = 0; i < subArraySize; i++) {
            System.out.println(subArray[i]);
        }
        System.out.println("Всего заполненных полей: " + subArraySize);
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
        for (int i = 0; i < Employee.getEmploeeQty(); i++) {
            if (employees[i] != null) {//проверка на пустую ячейку
                if (employees[i].getId() == id) {
                    System.out.println("\nЗапись о сотруднике " + employees[i].getName() + " УДАЛЕНА");
                    employees[i] = null;
                    break;
                }
            }
        }
    }
    //тестовый метод для вывода всего массива employees[]
    public void printAllArray() {
        System.out.println("\nПечать всего массива employees[]");
        for (Employee entry : employees) {
            System.out.println(entry);
        }
    }
    //метод "посчитать сумму затрат в месяц"
    public double calculateSalarySum() {
        Employee[] subArray = createSubArrayWithoutNull();
        double sum = 0;
        for (int i = 0; i < subArraySize; i++) {
            sum = sum + subArray[i].getSalary();
        }
        return sum;
    }
    //метод "найти сотрудника с минимальной зарплатой"
    public void printNameOfMinSalaryEmploee() {
        Employee[] subArray = createSubArrayWithoutNull();
        double minSalary = subArray[0].getSalary();
        int index = 0;
        for (int i = 1; i < subArraySize ; i++) {
            if (subArray[i].getSalary() < minSalary) {
                minSalary = subArray[i].getSalary();
                index = i;
            }
        }
        System.out.println("Сотрудник с минимальной зарплатой: " + subArray[index].getName());
    }
    //метод "найти сотрудника с максимальной зарплатой"
    public void printNameOfMaxSalaryEmploee() {
        Employee[] subArray = createSubArrayWithoutNull();
        double maxSalary = subArray[0].getSalary();
        int index = 0;
        for (int i = 1; i < subArraySize; i++) {
            if (subArray[i].getSalary() > maxSalary) {
                maxSalary = subArray[i].getSalary();
                index = i;
            }
        }
        System.out.println("Сотрудник с максимальной зарплатой: " + subArray[index].getName());
    }
    //метод "расчитать среднее значение зарплаты"
    public double calculateAverageSalary() {
        return calculateSalarySum()/subArraySize;
    }

    //метод "вывести в консоль ФИО всех сотрудников"
    public void printNameList() {
        Employee[] subArray = createSubArrayWithoutNull();
        System.out.println("\nСписок имён всех сотрудников:");
        for (int i = 0; i < subArraySize; i++) {
            System.out.println(subArray[i].getName());
        }
    }
    //метод "индексация зарплат на заданный процент
    public void makeSalaryIndexation (int indexationPercentage) {
        Employee[] subArray = createSubArrayWithoutNull();
        double increaseRate = 1 + indexationPercentage / 100D;
        for (int i = 0; i < subArraySize; i++) {
            subArray[i].setSalary(subArray[i].getSalary() * increaseRate );
        }
        System.out.println("\nЗарплата проиндексирована на " + indexationPercentage + "%");
    }
    //делаем служебный метод, собирающий массив сотрудников по заданному отделу
    public Employee[] makeDepartmentArray(int department) {
        Employee[] subArray = createSubArrayWithoutNull();
        //собираем массив сотрудника отдела
        int deptEmploeeQty = 0;
        Employee[] deptEmployees = new Employee[subArraySize];
        for (int i = 0; i < subArraySize; i++) {
            if (subArray[i].getDepartment() == department) {
                deptEmployees[deptEmploeeQty] = subArray[i];
                deptEmploeeQty++;
            }
        }
        //создаём массив без пустых полей и возвращаем его
        Employee[] deptEmployeesModified = new Employee[deptEmploeeQty];
        for (int i = 0; i < deptEmploeeQty; i++) {
            deptEmployeesModified[i] = deptEmployees[i];
        }
        return deptEmployeesModified;
    }
    //служебный метод "вывести в консоль список сотрудников по заданному отделу"
    public void printDepartmentEmployeeList(int department) {
        Employee[] deptEmployees = makeDepartmentArray(department);
        System.out.println("\nСписок сотрудников " + department + " отдела");
        for (int i = 0; i < deptEmployees.length; i++) {
            System.out.printf(Locale.US, "Ф.И.О.: " + deptEmployees[i].getName() +
                    "; зарплата: %.2fр.; id: " + deptEmployees[i].getId() +
                    "\n", deptEmployees[i].getSalary());
        }
        if (deptEmployees.length == 0) {
            System.out.println("В отделе нет сотрудников");
        }
    }

    //метод "ищем сотрудника с минимальной зарплатой по отделу"
    public void printMinSalaryEmployeeInDept(int department) {
        Employee[] deptEmployees = makeDepartmentArray(department);
        if (deptEmployees.length != 0) {//проверка на пустой отдел
            double minSalary = deptEmployees[0].getSalary();
            int index = 0;
            for (int i = 1; i < deptEmployees.length; i++) {
                if (deptEmployees[i].getSalary() < minSalary) {
                    minSalary = deptEmployees[i].getSalary();
                    index = i;
                }
            }
            System.out.println("\nСотрудник с минимальной зарплатой в отделе " + department +
                    ": " + deptEmployees[index].getName());
        } else {
            System.out.println("В базе нет сотрудников из отдела " + department);
        }
    }

    //метод "ищем сотрудника с минимальной зарплатой по отделу"
    public  void printMaxSalaryEmployeeInDept(int department) {
        Employee[] deptEmployees = makeDepartmentArray(department);
        if (deptEmployees.length != 0) {//проверка на пустой отдел
            double maxSalary = deptEmployees[0].getSalary();
            int index = 0;
            for (int i = 1; i < deptEmployees.length; i++) {
                if (deptEmployees[i].getSalary() > maxSalary) {
                    maxSalary = deptEmployees[i].getSalary();
                    index = i;
                }
            }
            System.out.println("\nСотрудник с максимальной зарплатой в отделе " + department +
                    ": " + deptEmployees[index].getName());
        } else {
            System.out.println("В базе нет сотрудников из отдела " + department);
        }
    }

    //метод "расчитать сумму затрат по отделу"
    public double calculateDeptSalarySum(int department) {
        Employee[] deptEmployees = makeDepartmentArray(department);
        if (deptEmployees.length != 0) {//проверка на пустой отдел
            double sum = 0;
            for (int i = 0; i < deptEmployees.length; i++) {
                sum = sum + deptEmployees[i].getSalary();
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
            if (deptEmployees.length == subArraySize) {
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
            for (int i = 0; i < deptEmployees.length; i++) {
                deptEmployees[i].setSalary(deptEmployees[i].getSalary() * increaseRate);
            }
            System.out.println("\nЗарплата по отделу " + department + " проиндексирована на " + indexationPercentage + "%");
        } else {
            System.out.println("В базе нет сотрудников из отдела " + department);
        }
    }

    //метод для вывода списка сотрудников с ЗП меньше заданного значения
    public void printEmployeesWithSalaryLessThan (double edgeSalary) {
        Employee[] subArray = createSubArrayWithoutNull();
        System.out.println("\nСписок сотрудников с зарплатой меньше " + edgeSalary + "р.");
        for (int i = 0; i < subArraySize; i++) {
            if (subArray[i].getSalary() < edgeSalary) {
                System.out.printf(Locale.US, "id: " + subArray[i].getId() + "; Ф.И.О.: " +
                        subArray[i].getName() + "; зарплата: %.2fр.\n", subArray[i].getSalary());
            }
        }
    }

    //метод для вывода списка сотрудников с ЗП больше заданного значения
    public void printEmployeesWithSalaryMoreThan (double edgeSalary) {
        Employee[] subArray = createSubArrayWithoutNull();
        System.out.println("\nСписок сотрудников с зарплатой больше или равно " + edgeSalary + "р.");
        for (int i = 0; i < subArraySize; i++) {
            if (subArray[i].getSalary() >= edgeSalary) {
                System.out.printf(Locale.US, "id: " + subArray[i].getId() + "; Ф.И.О.: " +
                        subArray[i].getName() + "; зарплата: %.2fр.\n", subArray[i].getSalary());
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

