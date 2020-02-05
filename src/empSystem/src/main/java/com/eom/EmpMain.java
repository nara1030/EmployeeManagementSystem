package com.eom;

import com.eom.common.EmpDestroy;
import com.eom.common.EmpInit;
import com.eom.common.EmpManagement;
import com.eom.model.Employee;
import com.eom.util.Validation;
import com.eom.util.exception.EmpNoExcessException;

import java.util.Scanner;

public class EmpMain {
    public static void main(String[] args) {
        // 객체 생성
        Scanner scanner = new Scanner(System.in);
        // 입력값: 메뉴, 이름/전화번호/직급/이메일
        int menu;
        Integer empNo;
        String empName;
        String phoneNum;
        String empRank;
        String email;
        // 초기화(public 생성자 고민)
        EmpManagement.setEmployees(EmpInit.readFile()); // 파일 → 메모리
        Employee.setEmpNoCounter(EmpInit.getLastEmpNo());   // 마지막 직원 번호 저장

        while (true) {
            System.out.println("프로그램을 시작합니다.");
            System.out.println("메뉴 선택: ");  // print로 하면 코드 실행 안 됨
            menu = scanner.nextInt();
            scanner.nextLine();

            switch (menu) {
                case 0: // 종료
                    // 메모리 → 파일
                    EmpDestroy.storeFile(EmpManagement.getEmployees());
                    System.out.println("시스템이 종료되었습니다.");
                    return;
                case 1: // 입력
                    System.out.println("이름: ");
                    empName = scanner.nextLine();
                    System.out.println("전화번호: ");
                    phoneNum = scanner.nextLine();
//                    boolean flag = Validation.checkPhoneNumFormat(phoneNum);
//                    while (!flag) {
//                        System.out.println("양식에 맞게 다시 입력해주세요.");
//                        System.out.println("예: 000-0000-0000");
//                        phoneNum = scanner.nextLine();
//                        flag = Validation.checkPhoneNumFormat(phoneNum);
//                    }

                    System.out.println("직급: ");
                    empRank = scanner.nextLine();
                    System.out.println("이메일: ");
                    email = scanner.nextLine();
//                    flag = Validation.checkEmailFormat(email);
//                    while (!flag) {
//                        System.out.println("양식에 맞게 다시 입력해주세요.");
//                        System.out.println("예: test@gmail.com");
//                        email = scanner.nextLine();
//                        flag = Validation.checkEmailFormat(email);
//                    }

                    Employee employee = null;
                    // throw 대신 try-catch문 사용
                    try {
                        employee = new Employee
                                .Builder(empName, phoneNum, empRank)
                                .email(email)
                                .build();
                    } catch (EmpNoExcessException e) {
                        System.out.println("직원 추가 중 에러 발생: " + e.getMessage());
                        e.printStackTrace();    // ∴ 프로그램 종료
                    }

                    EmpManagement.addEmp(employee);
                    break;
                case 2: // 목록
                    System.out.println("직원 목록을 출력합니다.");
                    EmpManagement.printEmpList();
                    break;
                case 3: // 상세
                    System.out.println("직원 상세 정보를 출력합니다.");
                    System.out.println("직원번호: ");
                    empNo = scanner.nextInt();
                    scanner.nextLine();

                    EmpManagement.printEmpDetail(empNo);
                    break;
                case 4: // 수정
                    System.out.println("해당 직원의 정보를 수정합니다.");
                    System.out.println("직원번호: ");
                    empNo = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("이름: ");
                    empName = scanner.nextLine();
                    System.out.println("전화번호: ");
                    phoneNum = scanner.nextLine();
                    System.out.println("직급: ");
                    empRank = scanner.nextLine();
                    System.out.println("이메일: ");
                    email = scanner.nextLine();

                    EmpManagement.modifyEmp(empNo, empName, phoneNum, empRank, email);
                    break;
                case 5: // 삭제
                    System.out.println("해당 직원을 삭제합니다.");
                    System.out.println("직원번호: ");
                    empNo = scanner.nextInt();
                    scanner.nextLine();

                    EmpManagement.deleteEmp(empNo);
                    break;
            }
        }
    }
}
