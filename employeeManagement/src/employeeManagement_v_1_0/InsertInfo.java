package employeeManagement_v_1_0;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class InsertInfo {
	String empId = ""; // 자동 증가
	String empNm = "";
	String phoneNumber = "";
	String empJobGrade = "";
	String emailAddr = "";
	String infoInserted = "";

	// 사용자 입력
	Scanner scanner = new Scanner(System.in);

	// 파일 쓰기 정보
	String path_fileName = "C:\\Users\\NT930QAA\\eclipse-workspace\\employeeList.txt";
	File file = new File(path_fileName);

	void insertInfo() {
		System.out.println("직원 정보를 입력해주세요.");
		System.out.print("이름: ");
		empNm = scanner.next();
		System.out.print("전화번호: ");
		do {
			phoneNumber = scanner.next();
		} while (!(RegularExpression.validationPhoneNumber(phoneNumber)));
		System.out.print("직급: ");
		empJobGrade = scanner.next();
		System.out.print("이메일: ");
		do {
			emailAddr = scanner.next();
		} while (!(RegularExpression.validationEmail(emailAddr)));

		// 기존 파일 읽어오기 - 직원 번호 = 직원 번호 + 1
		ReadOrCreateFile readOrCreateFile = new ReadOrCreateFile();
		// System.out.println(readOrCreateFile.readFile().size()); // 값 확인 필요
		empId = readOrCreateFile.readFile().get(readOrCreateFile.readFile().size() - 1);
		empId = String.valueOf((int) Integer.parseInt(empId) + 1);

		// 직원 번호 형식 통일: 정규 표현식 이용 수정 필요
		if (Integer.parseInt(empId) < 10) {
			empId = "00" + empId;
		} else if (Integer.parseInt(empId) < 100) {
			empId = "0" + empId;
		} else if (Integer.parseInt(empId) < 1000) {
		}

		infoInserted = empId + "\t" + empNm + "\t" + phoneNumber + "\t" + empJobGrade + "\t" + emailAddr + "\r\n";
		System.out.println("입력된 정보: " + infoInserted);

		try {
			FileWriter fileWriter = new FileWriter(file, true); // 출력 스트림 생성
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter); // 출력 버퍼 생성

			bufferedWriter.write(infoInserted);
			bufferedWriter.flush(); // 파악 필요
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("파일을 쓰는 과정에서 오류가 발생했습니다.");
		}
	}
}
