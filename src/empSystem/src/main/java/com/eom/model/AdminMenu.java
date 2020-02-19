package com.eom.model;

import java.util.List;

public enum AdminMenu {
    EXIT("종료"),
    INPUT_DATA("자료 입력"),
    PRINT_LIST("목록 출력"),
    PRINT("상세 정보 출력"),
    EDIT("수정"),
    DELETE("삭제");

    private String description;

    AdminMenu(String description) {
        this.description = description;
    }

    // JAVA 11
    final static List<AdminMenu> MENU = List.of(EXIT, INPUT_DATA, PRINT_LIST, PRINT, EDIT, DELETE);

    // 선택 범위 바깥의 메뉴 번호를 고를 수 없음을 AdminMenu 내에서 표현
    public static AdminMenu of(int selected) {
        if (selected < 0 || selected >= MENU.size()) {
            throw new IllegalArgumentException("지원하지 않는 메뉴 번호입니다.");
        }
        return MENU.get(selected);
    }

    // 사용자에게 메뉴 선택지 제안
    public static String getMenuString() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < MENU.size(); i++) {
            // %s: 문자, %d: 숫자, %f: 실수
            sb.append(String.format("%d: %s, ", i, MENU.get(i).description));
        }
        sb = sb.deleteCharAt(sb.length() - 2);
        return sb.toString();
    }
}
