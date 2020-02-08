package com.eom.lifeCycle;

import com.eom.model.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EmpInit {
    // 파일 마지막 직원 번호
    private static Integer lastEmpNo;

    // 직원 정보 로드: 파일 → 메모리
    public static List<Employee> readFile() {
        String filePath = "C:\\Users\\NT930QAA\\workspace-employeeManagement\\EmployeeManagementSystem\\emp.txt";    // 설정 파일 분리 필요
        List<Employee> employees = new ArrayList<>();   // 반환 타입 선언(∵ try-catch 외부)
        // 파일을 라인 단위로 읽기
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String readTemp;
            String[] empTemp = new String[5];
            Employee employee;

            for (int i = 0; i < 2; i++) {
                readTemp = reader.readLine();    // 첫 두 줄 제외
            }
            while ((readTemp = reader.readLine()) != null) {
                empTemp = readTemp.split("\t");
                employee = new Employee
                        .Builder(Integer.parseInt(empTemp[0]), empTemp[1], empTemp[2], empTemp[3])
                        .email(empTemp[4])
                        .build();
                employees.add(employee);
                lastEmpNo = Integer.parseInt(empTemp[0]);
            }

            System.out.println("파일 로드 완료");
            reader.close();
        } catch (IOException e) {
            System.out.println("파일 로드에 실패하였습니다.");
        }
        return employees;
    }

    // 마지막 직원 번호 초기화
    public static Integer getLastEmpNo() {
        return lastEmpNo;
    }
}
