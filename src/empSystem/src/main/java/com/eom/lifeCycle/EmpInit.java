package com.eom.lifeCycle;

import com.eom.model.Employee;
import com.eom.model.EmployeeList;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class EmpInit {
    // 파일 마지막 직원 번호
    private Integer lastEmpNo;

    // 직원 정보 로드: 파일 → 메모리
    public void readFile() {
        String filePath = "C:\\Users\\NT930QAA\\workspace-employeeManagement\\EmployeeManagementSystem\\emp.txt";    // 설정 파일 분리 필요

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
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
                EmployeeList.getEmployeeList().add(employee);
                lastEmpNo = Integer.parseInt(empTemp[0]);
            }

            System.out.println("파일 로드 완료");
        } catch (FileNotFoundException e) {
            System.out.println("파일 로드 실패: 파일 없음");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("파일 로드 실패: IOE");
            e.printStackTrace();
        }
    }

    // 마지막 직원 번호 초기화
    public Integer getLastEmpNo() {
        return lastEmpNo;
    }
}
