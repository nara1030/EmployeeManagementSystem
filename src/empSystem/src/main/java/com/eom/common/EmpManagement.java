package com.eom.common;

import com.eom.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmpManagement {
    private static List<Employee> employees = new ArrayList<>();    // static 변수 선언 고민

    // 초기화 시 직원 목록 로드
    public static void setEmployees(List<Employee> _employees) {
        employees = _employees;
    }

    // 종료 시 직원 목록 저장
    public static List<Employee> getEmployees() {
        return employees;
    }

    // 직원 등록
    public static void addEmp(Employee employee) {  // static 메소드 선언 고민
        employees.add(employee);
    }

    // 직원 목록
    public static void printEmpList() {
        System.out.println("직원번호" + "\t\t" + "이름" + "\t\t" + "전화번호" + "\t\t" + "직급" + "\t\t" + "이메일");
        System.out.println("========================================");
        employees.stream().forEach(Employee::printEmp);
    }

    // 사용자 화면 이동: 목록 → 상세 → 수정/삭제
    // 상세/수정/삭제: 직원 번호 입력(primitive가 아닌 object 인자 타입에 대한 고민)
    // 직원 상세
    public static void printEmpDetail(Integer empNo) {
        employees.stream().filter(emps -> emps.findEmpByEmpNo(empNo) != null).forEach(Employee::printEmp);
    }

    // 직원 수정: Employee 객체로 넘기면 getter 메서드 필요
    public static void modifyEmp(Integer empNo, String empName, String phoneNum, String empRank, String email) {
        employees.stream().filter(emps -> emps.findEmpByEmpNo(empNo) != null).forEach(emp -> {
            emp.modifyEmp(empName, phoneNum, empRank, email);
        });
    }

    // 직원 삭제
    public static void deleteEmp(Integer empNo) {
        employees.stream().filter(emps -> emps.findEmpByEmpNo(empNo) != null).forEach(Employee::deleteEmp);
    }
}
