package com.eom.util.print;

import com.eom.model.Employee;

public class PrintConsole implements Printable {
    @Override
    public String printEmp(Employee employee) {
        return employee.getEmpNo() + "\t\t" + employee.getEmpName() + "\t\t" + employee.getPhoneNum() + "\t\t" + employee.getEmpRank() + "\t\t" + employee.getEmail();
    }
}
