package employeeManagement_v_1_0;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// 파일 존재 여부 확인
		ReadOrCreateFile readOrCreateFile = new ReadOrCreateFile();
		if (readOrCreateFile.existFileTest()) {
			// 파일 존재하는 경우 - 기존 파일 읽어오기
			readOrCreateFile.readFile();
		} else {
			// 파일 존재하지 않는 경우 - 파일 생성
			readOrCreateFile.createFile();
		}

		Scanner scanner = new Scanner(System.in);
		int menuInput;
		do {
			// 프로그램 실행
			System.out.println("콘솔 메뉴 선택");
			System.out.println("0. 종료");
			System.out.println("1. 직원 정보 입력");
			System.out.println("2. 직원 리스트");
			System.out.println("3. 직원 상세 정보 출력");
			System.out.println("4. 직원 정보 수정");
			System.out.println("5. 직원 정보 삭제");
			System.out.print("선택 메뉴: ");
			menuInput = scanner.nextInt();

			switch (menuInput) {
			case 0:
				System.out.println("프로그램 종료");
				System.exit(0);
				// break;
			case 1:
				InsertInfo insertInfo = new InsertInfo();
				insertInfo.insertInfo();
				break;
			case 2:
				PrintConsole printConsole1 = new PrintConsole();
				printConsole1.printList();
				break;
			case 3:
				PrintConsole printConsole2 = new PrintConsole();
				printConsole2.printDetailList();
				break;
			case 4:
				ReviseOrDeleteInfo reviseOrDeleteInfo1 = new ReviseOrDeleteInfo();
				reviseOrDeleteInfo1.reviseInfo();
				reviseOrDeleteInfo1.fromDummyToFile();
				break;
			case 5:
				ReviseOrDeleteInfo reviseOrDeleteInfo2 = new ReviseOrDeleteInfo();
				reviseOrDeleteInfo2.deleteInfo();
				reviseOrDeleteInfo2.fromDummyToFile();
				break;
			default:
				System.out.println("0~5까지의 숫자를 입력해주세요.");
				break;
			}
		} while (!(menuInput == 0));
		scanner.close();
	}

}
