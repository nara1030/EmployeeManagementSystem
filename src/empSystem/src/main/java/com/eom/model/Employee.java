package com.eom.model;

public class Employee {
    // 직원 정보
    private final Integer empNo;  // 자동 증가
    private String empName;
    private String phoneNum;    // 정규표현식
    private String empRank;
    private String email;   // 정규표현식

    // 기타
    private static int empNoCounter = 0;

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

    // 상세/수정/삭제
    public Integer getEmpNo() {
        return empNo;
    }

    // 출력
    public void printEmp() {
        System.out.println("직원번호: " + empNo + "\t" + "이름: " + empName + "\t" + "전화번호: " + phoneNum + "\t" + "직급: " + empRank + "\t" + "이메일: " + email);
    }
}
