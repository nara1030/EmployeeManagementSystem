package com.eom;

import com.eom.exception.EmpNoExcessException;
import com.eom.lifeCycle.EmpDestroy;
import com.eom.lifeCycle.EmpInit;
import com.eom.lifeCycle.EmpManagement;
import com.eom.model.Employee;
import com.eom.util.print.PrintConsole;
import com.eom.util.print.PrintFile;

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

        // 초기화
        EmpInit empInit = new EmpInit();
        empInit.readFile();
        Employee.setEmpNoCounter(empInit.getLastEmpNo());

        // 출력/저장 소스 지정
        EmpManagement empManagement = new EmpManagement(new PrintConsole());
        EmpDestroy empDestroy = new EmpDestroy(new PrintFile());

        System.out.println("프로그램을 시작합니다.");
        while (true) {
            System.out.println("메뉴 선택: ");  // print로 하면 코드 실행 안 됨
            menu = scanner.nextInt();
            scanner.nextLine();

            switch (menu) {
                case 0: // 종료
                    // 메모리 → 파일
                    empDestroy.storeFile();
                    System.out.println("시스템이 종료되었습니다.");
                    return;
                case 1: // 입력
                    System.out.println("이름: ");
                    empName = scanner.nextLine();
                    System.out.println("전화번호: ");
                    phoneNum = scanner.nextLine();
                    System.out.println("직급: ");
                    empRank = scanner.nextLine();
                    System.out.println("이메일: ");
                    email = scanner.nextLine();

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

                    empManagement.addEmp(employee);
                    break;
                case 2: // 목록
                    System.out.println("직원 목록을 출력합니다.");
                    empManagement.printEmp();
                    break;
                case 3: // 상세
                    System.out.println("직원 상세 정보를 출력합니다.");
                    System.out.println("직원번호: ");
                    empNo = scanner.nextInt();
                    scanner.nextLine();

                    empManagement.printEmpDetail(empNo);
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

                    empManagement.modifyEmp(empNo, empName, phoneNum, empRank, email);
                    break;
                case 5: // 삭제
                    System.out.println("해당 직원을 삭제합니다.");
                    System.out.println("직원번호: ");
                    empNo = scanner.nextInt();
                    scanner.nextLine();

                    empManagement.deleteEmp(empNo);
                    break;
            }
        }
    }
}
