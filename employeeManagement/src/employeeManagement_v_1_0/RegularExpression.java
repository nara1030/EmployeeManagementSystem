package employeeManagement_v_1_0;

public class RegularExpression {
	final static String patternPhoneNumber = "^[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}$";
	final static String patternEmail = "(^[0-9a-zA-Z_-]+@[0-9a-zA-Z]+[.][a-zA-Z]{2,4}$)";

	static boolean validationPhoneNumber(String param) {
		if (param.matches(patternPhoneNumber)) {
			System.out.println("올바른 전화번호 형식입니다.");
			return true;
		} else {
			System.out.println("전화번호가 입력 형식과 일치하지 않습니다.");
			System.out.println("다시 입력해주세요.");
			return false;
		}
	}

	static boolean validationEmail(String param) {
		if (param.matches(patternEmail)) {
			System.out.println("올바른 이메일 형식입니다.");
			return true;
		} else {
			System.out.println("이메일이 입력 형식과 일치하지 않습니다.");
			System.out.println("다시 입력해주세요.");
			return false;
		}
	}
}
