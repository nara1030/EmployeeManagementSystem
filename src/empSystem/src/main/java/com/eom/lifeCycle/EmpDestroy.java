package com.eom.lifeCycle;

import com.eom.model.Employee;
import com.eom.model.EmployeeList;
import com.eom.util.print.Printable;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class EmpDestroy {
    private final Printable printable;

    // 저장 소스 초기화(현재: 파일)
    public EmpDestroy(Printable printable) {
        this.printable = printable;
    }

    public void storeFile() {
        String filePath = "C:\\Users\\NT930QAA\\workspace-employeeManagement\\EmployeeManagementSystem\\emp.txt";    // 설정 파일 분리 필요

        // 이어쓰기
        //  - FileWriter 인자: false(cf. true: 기존 파일에 이어쓰기)
        //  - write() → append()
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            writer.append("직원번호" + "\t\t" + "이름" + "\t" + "전화번호" + "\t\t" + "직급" + "\t" + "이메일" + "\n");
            writer.append("========================================");

            for (Employee employee : EmployeeList.getEmployeeList()) {
                writer.append("\n" + printable.printEmp(employee));
            }
            System.out.println("파일 저장 완료");
        } catch (IOException e) {
            System.out.println("파일 저장 실패");
            e.printStackTrace();
        }
    }
}
