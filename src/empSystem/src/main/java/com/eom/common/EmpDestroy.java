package com.eom.common;

import com.eom.model.Employee;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class EmpDestroy {
    private static String filePath = "C:\\Users\\NT930QAA\\workspace-employeeManagement\\EmployeeManagementSystem\\emp.txt";    // 설정 파일 분리 필요

    public static void storeFile(List<Employee> employees) {
        try {
            // 이어쓰기
            //  - FileWriter 인자: false(cf. true: 기존 파일에 이어쓰기)
            //  - write() → append()
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false));
            writer.append("직원번호" + "\t\t" + "이름" + "\t" + "전화번호" + "\t\t" + "직급" + "\t" + "이메일" + "\n");
            writer.append("========================================");
            for (Employee employee : employees) {
                writer.append("\n" + employee.storeEmp());
            }
            System.out.println("파일 저장 완료");
            writer.close();
        } catch (IOException e) {
            System.out.println("파일 저장에 실패하였습니다.");
        }
    }
}
