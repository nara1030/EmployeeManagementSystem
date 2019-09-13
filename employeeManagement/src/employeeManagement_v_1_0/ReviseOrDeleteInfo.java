package employeeManagement_v_1_0;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ReviseOrDeleteInfo {
	int inputEmpId;

	String empId = "";
	String empNm = "";
	String phoneNumber = "";
	String empJobGrade = "";
	String emailAddr = "";

	String path_fileName = "C:\\Users\\NT930QAA\\eclipse-workspace\\employeeList.txt";
	String path_dummyFileName = "C:\\Users\\NT930QAA\\eclipse-workspace\\temp.txt";
	File file = new File(path_fileName);
	File dummyFile = new File(path_dummyFileName);

	// 사용자 입력 - 직원 번호
	Scanner scanner = new Scanner(System.in);

	void reviseInfo() {
		ReadOrCreateFile readOrCreateFile = new ReadOrCreateFile();
		empId = readOrCreateFile.readFile().get(readOrCreateFile.readFile().size() - 1);
		if (empId.equals("0")) {
			// 직원 없을 시
			System.out.println("직원이 존재하지 않아 수정할 정보가 없습니다.");
		} else {
			// 직원 있을 시
			System.out.print("수정할 직원 번호: ");
			inputEmpId = scanner.nextInt();

			try {
				if (dummyFile.createNewFile()) {
					System.out.println("더미 파일이 생성되었습니다.");
				} else {
					System.out.println("더미 파일을 생성하지 못했습니다.");
				}
				FileReader fileReader = new FileReader(file); // 입력 스트림 생성
				BufferedReader bufferedReader = new BufferedReader(fileReader); // 입력 버퍼 생성
				FileWriter fileWriter = new FileWriter(dummyFile, true); // 출력 스트림 생성
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter); // 출력 버퍼 생성

				String readLine = "";
				String reviseLine = "";
				String temp;
				while ((readLine = bufferedReader.readLine()) != null) {
					// readLine()은 끝의 개행 문자를 포함하지 않는다.
					temp = readLine.split("\t")[0];

					if (inputEmpId == Integer.parseInt(temp)) {
						// 같을 경우 - 직원 정보 새로 더미 파일 입력
						System.out.println("수정 직원 정보: " + readLine);
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

						reviseLine = temp + "\t" + empNm + "\t" + phoneNumber  + "\t" + empJobGrade + "\t" + emailAddr + "\r\n";
						System.out.println("수정된 정보: " + reviseLine);

						bufferedWriter.write(reviseLine);
					} else {
						// 다를 경우 - 직원 정보 수정 없이 더미 파일 복사
						readLine = readLine + "\r\n";

						bufferedWriter.write(readLine);
					}
				}
				bufferedReader.close();
				bufferedWriter.flush();
				bufferedWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("파일을 수정하는 과정에서 오류가 발생했습니다.");
			}
		}
	}

	void deleteInfo() {
		ReadOrCreateFile readOrCreateFile = new ReadOrCreateFile();
		empId = readOrCreateFile.readFile().get(readOrCreateFile.readFile().size() - 1);
		if (empId.equals("0")) {
			// 직원 없을 시
			System.out.println("직원이 존재하지 않아 삭제할 정보가 없습니다.");
		} else {
			// 직원 있을 시
			System.out.print("삭제할 직원 번호: ");
			inputEmpId = scanner.nextInt();

			try {
				if (dummyFile.createNewFile()) {
					System.out.println("더미 파일이 생성되었습니다.");
				} else {
					System.out.println("더미 파일을 생성하지 못했습니다.");
				}

				FileReader fileReader = new FileReader(file); // 입력 스트림 생성
				BufferedReader bufferedReader = new BufferedReader(fileReader); // 입력 버퍼 생성
				FileWriter fileWriter = new FileWriter(dummyFile, true); // 출력 스트림 생성
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter); // 출력 버퍼 생성

				String readLine = "";
				String temp;
				while ((readLine = bufferedReader.readLine()) != null) {
					// readLine()은 끝의 개행 문자를 포함하지 않는다.
					temp = readLine.split("\t")[0];

					if (inputEmpId == Integer.parseInt(temp)) {
						// 같을 경우 - 직원 정보 새로 더미 파일 입력
						// bufferedWriter.write("\r\n");
						System.out.println("삭제 직원 정보: " + readLine);
					} else {
						// 다를 경우 - 직원 정보 수정 없이 더미 파일 복사
						readLine = readLine + "\r\n";

						bufferedWriter.write(readLine);
					}
				}
				bufferedReader.close();
				bufferedWriter.flush();
				bufferedWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("파일을 삭제하는 과정에서 오류가 발생했습니다.");
			}
		}
	}

	void fromDummyToFile() {
		ReadOrCreateFile readOrCreateFile = new ReadOrCreateFile();
		empId = readOrCreateFile.readFile().get(readOrCreateFile.readFile().size() - 1);
		if (empId.equals("0")) {
			// 직원 없을 시
			System.out.println("직원이 존재하지 않아 파일 복사 불필요합니다.");
		} else {
			// 직원 있을 시
			try {
				if (file.delete()) {
					file.createNewFile();
					System.out.println("파일이 초기화 되었습니다.");
				} else {
					System.out.println("파일을 초기화하지 못했습니다.");
				}
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("파일을 초기화하는 과정에서 오류가 발생했습니다.");
			}

			try {
				FileReader fileReader = new FileReader(dummyFile); // 입력 스트림 생성
				BufferedReader bufferedReader = new BufferedReader(fileReader); // 입력 버퍼 생성
				FileWriter fileWriter = new FileWriter(file, true); // 출력 스트림 생성
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter); // 출력 버퍼 생성

				String readLine = "";
				while ((readLine = bufferedReader.readLine()) != null) {
					readLine = readLine + "\r\n";
					bufferedWriter.write(readLine);
				}
				bufferedReader.close();
				bufferedWriter.flush();
				bufferedWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("더미 내용을 복사하는 과정에서 오류가 발생했습니다.");
			}

			// 더미 파일 삭제
			if (dummyFile.delete()) {
				System.out.println("더미 파일 삭제에 성공했습니다.");
			} else {
				System.out.println("더미 파일 삭제에 실패했습니다.");
			}
		}
	}
}
