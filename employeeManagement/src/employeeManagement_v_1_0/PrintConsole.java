package employeeManagement_v_1_0;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class PrintConsole {
	String path_fileName = "C:\\Users\\NT930QAA\\eclipse-workspace\\employeeList.txt";
	File file = new File(path_fileName);
	String empId = "";

	void printList() {
		ReadOrCreateFile readOrCreateFile = new ReadOrCreateFile();
		empId = readOrCreateFile.readFile().get(readOrCreateFile.readFile().size() - 1);
		if (empId.equals("0")) {
			System.out.println("직원이 존재하지 않습니다.");
		} else {
			try {
				FileReader fileReader = new FileReader(file); // 입력 스트림 생성
				BufferedReader bufferedReader = new BufferedReader(fileReader); // 입력 버퍼 생성
				System.out.println("직원번호" + "\t" + "이름");
				System.out.println("==============");
				String readLine = "";
				String[] temp = new String[5];
				while ((readLine = bufferedReader.readLine()) != null) {
					temp = null;
					temp = readLine.split("\t");
					System.out.println(temp[0] + "\t" + temp[1]);
				}
				bufferedReader.close();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("목록 출력 과정에서 오류가 발생했습니다.");
			}
		}
	}

	void printDetailList() {
		ReadOrCreateFile readOrCreateFile = new ReadOrCreateFile();
		empId = readOrCreateFile.readFile().get(readOrCreateFile.readFile().size() - 1);
		if (empId.equals("0")) {
			System.out.println("직원이 존재하지 않습니다.");
		} else {
			try {
				FileReader fileReader = new FileReader(file); // 입력 스트림 생성
				BufferedReader bufferedReader = new BufferedReader(fileReader); // 입력 버퍼 생성
				System.out.println("직원번호" + "\t" + "이름" + "\t" + "전화번호" + "\t" + "직급" + "\t" + "이메일");
				System.out.println("====================================");
				String readLine = "";
				while ((readLine = bufferedReader.readLine()) != null) {
					System.out.println(readLine);
				}
				bufferedReader.close();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("상세 출력 과정에서 오류가 발생했습니다.");
			}
		}
	}
}
