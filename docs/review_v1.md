직원관리 프로그램_v1
=====
* 코드에 대한 이유를 기록
* 피드백 기대
- - -
## 목차
1. [설명](#설명)
2. [참고](#참고)

## 설명
* 캡슐화에 대한 고민
	* 자신(클래스)의 데이터를 자신이 처리하도록 책임
	* getter에 대한 고민
		* getter 생성 않고 루프에 해당하는 model 클래스(Employee)에서 자신의 데이터 직접 처리
* static에 대한 고민
	* 직원 목록(List<Employee>)은 프로그램 실행 중 유지되어야 → static 필드 선언
		* 클래스간 공유 가능하다는 오해 → ∵ static 멤버는 동일 클래스 모든 객체에 의해 공유
		* 클래스간 공유 위해서는 static 필드도 메서드 통해 전달 필요
	* 초기화/종료, CRUD 메소드 → static 선언 고민
		* 클래스 분리 고민
* 빌더 패턴
	* 입력 시 공란(Null)으로 입력 방지(점층적 생성자 패턴 대안)
	* 초기화 및 입력 시 다른 생성자 구현(오버로딩)
		* 초기화 시 public 고민
* 스트림 ≠ Loop
	* https://www.popit.kr/java8-stream%EC%9D%80-loop%EA%B0%80-%EC%95%84%EB%8B%88%EB%8B%A4/
	* https://homoefficio.github.io/2016/06/26/for-loop-%EB%A5%BC-Stream-forEach-%EB%A1%9C-%EB%B0%94%EA%BE%B8%EC%A7%80-%EB%A7%90%EC%95%84%EC%95%BC-%ED%95%A0-3%EA%B0%80%EC%A7%80-%EC%9D%B4%EC%9C%A0/
* 프로퍼티 파일
	* 변경 시 컴파일 불필요
	* 윈도우 ini 파일과 같은 기능
	* https://okky.kr/article/38761
	* https://javacan.tistory.com/entry/4
	* https://sthyun.tistory.com/entry/java%EC%97%90%EC%84%9C-property%ED%8C%8C%EC%9D%BC-%EC%89%BD%EA%B2%8C-%EC%B0%BE%EA%B8%B0-ClassLoader
* 로그 출력
* 파일 출력  
	```java
	// 이어쓰기
	//  - FileWriter 인자: false(cf. true: 기존 파일에 이어쓰기)
	//  - write() → append()
	BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false));
	writer.append("직원번호" + "\t\t" + "이름" + "\t" + "전화번호" + "\t\t" + "직급" + "\t" + "이메일" + "\n");
	```
	* [Java - Write to File](https://www.baeldung.com/java-write-to-file)
* 메인 메서드 실행 시 고민  
	```java
	System.out.println("프로그램을 시작합니다.");
    System.out.println("메뉴 선택: ");  // print로 하면 안 찍힘
	```

##### [목차로 이동](#목차)

## 참고
* [Why can't we can't have static outer classes](https://stackoverflow.com/questions/18036458/why-cant-we-have-static-outer-classes)
* [How can you use a protected constructor in Java?](https://www.quora.com/How-can-you-use-a-protected-constructor-in-Java)

##### [목차로 이동](#목차)