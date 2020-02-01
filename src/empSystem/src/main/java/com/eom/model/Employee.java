package com.eom.model;

import com.eom.util.Format;

public class Employee {
    // 직원 정보
    private final Integer empNo;  // 자동 증가
    private String empName;
    private String phoneNum;    // 정규표현식
    private String empRank;
    private String email;   // 정규표현식

    // 직원 번호 자동 증가(파일 목록 마지막 번호 초기화)
    private static int empNoCounter;

    // 직원 번호 초기화
    public static void setEmpNoCounter(Integer lastEmpNo) {
        empNoCounter = ++lastEmpNo;
    }

    private Employee(Builder builder) {
        this.empNo = builder._empNo;
        this.empName = builder._empName;
        this.phoneNum = builder._phoneNum;
        this.empRank = builder._empRank;
        this.email = builder._email;
    }

    // 입력 시 Null 방지 → Builder Pattern(∵ 점층적 생성자 패턴 단점 보완)
    // 코드 수정 및 가독성 보완
    public static class Builder {
        // 필수 인자
        private final Integer _empNo;
        private String _empName;
        private String _phoneNum;
        private String _empRank;

        // 선택 인자
        private String _email;

        // 초기화 시 생성자
        public Builder(Integer _empNo, String _empName, String _phoneNum, String _empRank) {
            this._empNo = _empNo;
            this._empName = _empName;
            this._phoneNum = _phoneNum;
            this._empRank = _empRank;
        }

        // 직원 추가 시 생성자
        public Builder(String _empName, String _phoneNum, String _empRank) {
            this._empNo = empNoCounter++;
            this._empName = _empName;
            this._phoneNum = _phoneNum;
            this._empRank = _empRank;
        }

        public Builder email(String email) {
            _email = email;
            return this;
        }

        public Employee build() {
            return new Employee(this);
        }
    }

    // 수정 필요(단축키: alt + ins)
    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setEmpRank(String empRank) {
        this.empRank = empRank;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // 상세/수정/삭제 시 반환
    public Employee findEmpByEmpNo(Integer empNo) {
        if (this.empNo.equals(empNo)) {
            return this;
        }
        return null;    // if문 제거 방법 고민
    }

    // 수정
    public void modifyEmp(String empName, String phoneNum, String empRank, String email) {
        this.setEmpName(empName);
        this.setPhoneNum(phoneNum);
        this.setEmpRank(empRank);
        this.setEmail(email);
    }

    // 삭제
    public void deleteEmp() {
        this.setEmpName("-");
        this.setPhoneNum("-");
        this.setEmpRank("-");
        this.setEmail("-");
    }

    // 콘솔 출력
    public void printEmp() {
        System.out.println(empNo + "\t\t" + empName + "\t\t" + phoneNum + "\t\t" + empRank + "\t\t" + email);
    }

    // 파일 출력
    public String storeEmp() {
        return Format.changeNumberFormat(empNo) + "\t" + empName + "\t" + phoneNum + "\t" + empRank + "\t" + email;
    }
}
