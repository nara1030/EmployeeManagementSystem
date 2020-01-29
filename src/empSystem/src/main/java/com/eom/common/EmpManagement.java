package com.eom.common;

import com.eom.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmpManagement {
    private static List<Employee> employees = new ArrayList<>();    // static 변수 선언 고민

    // 직원 등록
    public void addEmp(Employee employee) {
        employees.add(employee);
    }

    // 직원 목록
    public void printEmpList() {
        employees.stream().forEach(Employee::printEmp);
    }

    // 사용자 화면 이동: 목록 → 상세 → 수정/삭제
    // 상세/수정/삭제: 직원 번호 입력(primitive가 아닌 object 인자 타입에 대한 고민)
    // 직원 상세
    public void printEmpDetail(Integer empNo) {

    }

    // 직원 수정
    public void modifyEmp(Integer empNo) {

    }

    // 직원 삭제
    public void deleteEmp(Integer empNo) {

    }
}
