package com.eom.common;

import com.eom.model.Employee;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class EmpDestroy {
    public static void storeFile(List<Employee> employees) {
        String filePath = "C:\\Users\\NT930QAA\\workspace-employeeManagement\\EmployeeManagementSystem\\emp.txt";    // 설정 파일 분리 필요
        try {
            // FileWriter - true: 기존 파일 이어쓰기
            // PrintWriter - true: 생성 파일 이어쓰기
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filePath, true)));
            writer.print("직원번호" + "\t\t" + "이름" + "\t" + "전화번호" + "\t\t" + "직급" + "\t" + "이메일" + "\n");
            writer.print("========================================");
            for(Employee employee : employees) {
                writer.print("\n" + employee.storeEmp());
            }
            System.out.println("파일 저장 완료");
        } catch (IOException e) {
            System.out.println("파일 저장에 실패하였습니다.");
        }
    }
}
