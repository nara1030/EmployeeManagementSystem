package employeeManagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadOrCreateFile {
	String path_fileName = "C:\\Users\\NT930QAA\\eclipse-workspace\\employeeList.txt";
	File file = new File(path_fileName);

	// 메소드 실행 때마다 초기화되면 안 됨
	static ArrayList<String> empIdList = new ArrayList<String>();

	// 파일 존재 여부 확인
	boolean existFileTest() {
		if (file.isFile()) {
			System.out.println("파일이 존재합니다.");
			return true;
		} else {
			System.out.println("파일이 존재하지 않습니다.");
			return false;
		}
	}

	// 파일 생성
	void createFile() {
		try {
			if (file.createNewFile()) {
				System.out.println("파일이 생성되었습니다.");
			} else {
				System.out.println("파일을 생성하지 못했습니다.");
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("파일을 생성하는 과정에서 오류가 발생했습니다.");
		}
	}

	// 파일 읽기 - 사원 번호 담긴 배열 리턴
	// 줄 번호와 사원 번호가 다를 수 있기 때문에 저장
	ArrayList<String> readFile() {
		// try-catch문 메소드 밖 사용 불가(객체 메소드 밖 생성 위해)
		try {
			FileReader fileReader = new FileReader(file); // 입력 스트림 생성
			BufferedReader bufferedReader = new BufferedReader(fileReader); // 입력 버퍼 생성

			String readLine = "";
			String[] temp;

			if ((readLine = bufferedReader.readLine()) == null) {
				// 한 줄도 없는 경우
				empIdList.add("0");
				bufferedReader.close();
				return empIdList;
			} else {
				// 한 줄 이상 있는 경우
				do {
					// readLine()은 끝의 개행 문자를 포함하지 않는다.
					// System.out.println(readLine);
					temp = readLine.split("\t");
					empIdList.add(temp[0]);
				} while ((readLine = bufferedReader.readLine()) != null);
				bufferedReader.close();
				return empIdList;
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("파일을 읽는 과정에서 오류가 발생했습니다.");
			return null; // 수정 필요
		}
	}
}
