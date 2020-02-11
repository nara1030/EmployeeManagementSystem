package com.eom.util.print;

import com.eom.model.Employee;
import com.eom.util.format.EmpNoFormat;

public class PrintFile implements Printable {
    @Override
    public String printEmp(Employee employee) {
        return EmpNoFormat.changeNumberFormat(employee.getEmpNo()) + "\t" + employee.getEmpName() + "\t" + employee.getPhoneNum() + "\t" + employee.getEmpRank() + "\t" + employee.getEmail();
    }
}
