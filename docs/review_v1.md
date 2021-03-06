직원관리 프로그램_v1
=====
## 목차
1. [피드백](#피드백)
2. [기타](#기타)
3. [참고](#참고)

## 피드백
1. 논리적으로 Employee가 콘솔과 출력을 알아야 하는 이유 없음  
(초기 Employee 클래스에 콘솔 출력 및 파일 출력 메소드 존재)
	* 이는 Employee가 외부 세계에 필요 이상으로 열려 있음을 의미  
	(만약 또다른 출력 기능이 추가된다면 Employee 클래스가 더 무거워짐)
	* 또한 파일 출력 메소드의 이름이 왜 storeEmp인가?(콘솔 출력 메소드의 경우 printEmp)
2. EmpInit 클래스는 자바 6 버전 스타일의 코드(try catch finally)
3. 대부분의 메소드가 static으로 구현  
(∵ static field의 사용을 위해) → [Singleton 대체]
	* 객체지향보다 절차지향적으로 구현되었음을 의미
		* 다형성 등 객체지향이 가지는 변경의 유연성 없음
	* 만약 static으로 사용 시, ArrayList보다 CopyOnWriteArrayList 권장
	* 추후 싱글톤 대체
		1. [static vs. Singleton](https://github.com/nara1030/ThisIsJava/blob/master/docs/etc/static_vs_singleton.md)
		2. [인스턴스 메서드는 static 멤버 모두 사용 가능](https://gmlwjd9405.github.io/2018/08/04/java-static.html)
4. ReadFile(Readable 인터페이스 구현체) 클래스에서 구체적인 파일 경로(`FILE_PATH`) 의존성 제거
	* 선언된 변수를 메소드의 매개변수를 통해 받으며 의존성 제거  
	(아직 메소드가 파일명 알고 있으므로 더 개선 가능)  
		```java
		public class ReadFile implements Readable {
			/** 
			 * private static final String FILE_PATH = "...";
			 *
			 * public ReadFile() throws IOException {
			 *	reader = new BufferedReader(new FileReader(FILE_PATH));
			 * }
			 */
			 
			 public ReadFile(String filePath) throws IOException {
			 }
		}
		```
	* 파일 주소는 설정 파일로 분리
		* 프로젝트 밖에 데이터 파일을 위치  
		(∵ 자바 런타임 실행 위치 ≠ 소스코드 위치)
		* 자바 실행 시 커맨드 라인 옵션으로 파일 주소 추가하는 방식 권장
		* classpath에서 resource 찾기: Class.getResource vs. ClassLoader.getResource
			* [1](https://whitecold89.tistory.com/9)
			* [2](https://devyongsik.tistory.com/171)
			* [3](https://unabated.tistory.com/entry/ClassgetResource-vs-ClassLoadergetResource)
			* [4](https://sthyun.tistory.com/entry/java%EC%97%90%EC%84%9C-property%ED%8C%8C%EC%9D%BC-%EC%89%BD%EA%B2%8C-%EC%B0%BE%EA%B8%B0-ClassLoader)
		* 기타  
			```java
			/*
			 * EmpMain: empSystem/src/main/java/com/eom/EmpMain
			 */
			System.out.println(EmpMain.class.getResource(""));
			// → file:/C:/Users/NT930QAA/IdeaProjects/empSystem/build/classes/java/main/com/eom/
			System.out.println(EmpMain.class.getResource("/"));
			// → file:/C:/Users/NT930QAA/IdeaProjects/empSystem/build/classes/java/main/
			final URL resource = EmpMain.class.getResource("/emp.txt");	// resources/emp.txt
			System.out.println(Paths.get(resource.toURI()).toAbsolutePath().toString());
			// → C:\Users\NT930QAA\IdeaProjects\empSystem\build\resources\main\emp.txt
			// 읽어들어야 되는 경로(C:\Users\NT930QAA\IdeaProjects\empSystem\src\main\resources\emp.txt)와
			// 다름에도 불구하고 왜 제대로 읽히지?(출력은 예상대로 안 됨)
			```
5. [To be continued](https://github.com/johngrib/EmployeeManagementSystem/pulls)
	1. 데이터 파일 경로를 ReadFile에서 분리하라
	2. 메소드를 적절히 추출하여 메뉴를 단순하게 고쳐라
	3. 변수를 선언하는 곳과 사용하는 곳의 거리를 좁혀라
	4. 메뉴의 표현을 분리하라
		* 자바 enum ≠ 타 언어의 enum  
		(이펙티브 자바 3판 참고)

##### [목차로 이동](#목차)

## 기타
* 객체 설계에 대한 고민: Employee
	* [객체의 올바른 설계 조건](https://github.com/nara1030/TIL/blob/master/docs/lecture_list/code_spitz/s83_object1/object1_week1_ch00-ch01.md#%EA%B0%9D%EC%B2%B4%EC%99%80-%EC%84%A4%EA%B3%84)에는 자신의 상태를 스스로 관리하는 것이라 알고 있음
		* 이는 [상태 데이터를 가지는 객체에서 데이터를 꺼내려 하지 말고 메시지를 보내라](https://github.com/nara1030/ThisIsJava/blob/master/docs/etc/double_dispatch.md#%EC%9A%B0%EC%95%84%ED%95%9C%ED%85%8C%ED%81%AC%EC%BD%94%EC%8A%A4)는 말과 일맥상통
	* 하지만 본인 데이터를 스스로 관리한다고 생각하니 객체에 기능이 과도해짐
		* 예를 들어 출력(ex. 콘솔, 파일 등)의 경우 논리적으로 생각해보면 Employee 클래스가 가져야 할 기능 아님
* 빌더 패턴
	* 입력 시 공란(Null)으로 입력 방지(점층적 생성자 패턴 대안)
	* 초기화 및 입력 시 다른 생성자 구현(오버로딩)
		* 초기화 시 public 고민(∵ 외부 공개 불필요)
	* 생성자 오버라이딩 불가?
* 스트림 ≠ Loop
	* [1](https://www.popit.kr/java8-stream%EC%9D%80-loop%EA%B0%80-%EC%95%84%EB%8B%88%EB%8B%A4/)
	* [2](https://homoefficio.github.io/2016/06/26/for-loop-%EB%A5%BC-Stream-forEach-%EB%A1%9C-%EB%B0%94%EA%BE%B8%EC%A7%80-%EB%A7%90%EC%95%84%EC%95%BC-%ED%95%A0-3%EA%B0%80%EC%A7%80-%EC%9D%B4%EC%9C%A0/)
	* [3](https://hamait.tistory.com/547)
	* [최대/최소 구하기: Loop/Collections/Stream](https://www.daleseo.com/java-min-max/)
* 프로퍼티 파일
	* 변경 시 컴파일 불필요
	* 윈도우 ini 파일과 같은 기능
	* https://okky.kr/article/38761
	* https://javacan.tistory.com/entry/4
* 자바 버전 업데이트: 8 → 11
	* [IDE 설정](https://github.com/HomoEfficio/dev-tips/blob/master/IntelliJ%20-%20Gradle%EC%9D%98%20%EC%9E%90%EB%B0%94%20%EB%B2%84%EC%A0%84%20%EC%84%A4%EC%A0%95.md)
	* Java 9
		* [Java 9의 불변 컬렉션 생성](https://www.daleseo.com/java9-immutable-collections/)
		* [What is the difference between List.of and Arrays.asList?](https://stackoverflow.com/questions/46579074/what-is-the-difference-between-list-of-and-arrays-aslist)
		* [Java List - List in Java](https://www.journaldev.com/11444/java-list)
	* Java 11
		* [Java 11 Tutorial](https://winterbe.com/posts/2018/09/24/java-11-tutorial/)
		* [From Java 8 to Java 11](https://codete.com/blog/java-8-java-11-quick-guide/)
		* [Big things in JDK 11](https://meetup.toast.com/posts/171)

- - -
* 프로젝트 시작 시 메뉴 두 번 넣어야 동작
* 파일 존재 안할 시 에러 발생 → Optional
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
* [UML 기본](https://geniusduck.tistory.com/entry/UML-%EA%B8%B0%EB%B3%B8%ED%8E%B8-%EA%B8%B0%EB%B3%B8-%ED%91%9C%EA%B8%B0-%ED%98%95%EC%8B%9D-%EB%B0%8F-%EA%B4%80%EA%B3%84%ED%91%9C%ED%98%84%EB%B2%95)
* Singleton: static의 대안
	* [왜 자바에서 static의 사용을 지양해야 하는가?](https://unabated.tistory.com/entry/%EC%99%9C-%EC%9E%90%EB%B0%94%EC%97%90%EC%84%9C-static%EC%9D%98-%EC%82%AC%EC%9A%A9%EC%9D%84-%EC%A7%80%EC%96%91%ED%95%B4%EC%95%BC-%ED%95%98%EB%8A%94%EA%B0%80)
		1. 객체 지향적이지 않음(∵ 재사용 불가)
		2. 멀티 스레드의 경우
	* [객체 지향의 구멍 static](https://whiteship.tistory.com/134)
		* static field는 this가 없다. instance method에는 숨겨진 파라미터로 this가 건네진다. 하지만 static method는 절차지향의 함수와 동일하므로 숨겨진 파라미터 this는 없다.
		* 글로벌 변수는 어디서든 참조할 수 있고 값을 변경할 수 있기 때문에 사용하지 않는 것이 좋다. 따라서 static은 public final을 붙여 상수로 사용하는 용도 외에는 자제하는 것이 좋다.
		* final 키워드의 주의할 것은 `final Date endDate = new Date();`에서 endDate가 가리키는 객체가 바뀔 수 없다는 것이지, 객체의 내용은 바뀔 수 있다는 것이다([레퍼런스의 위험성](https://whiteship.tistory.com/100)).
	* [Java Reference Object의 이해와 활용](http://blog.daum.net/_blog/BlogTypeView.do?blogid=04qAU&articleno=15309479&categoryId=452665&regdt=20100623131535)
	* [싱글톤 패턴의 단점과 대안](https://okky.kr/article/673659)
* [중첩 try-with-resources는 어떻게 작동할까?](https://multifrontgarden.tistory.com/192)
* [Java 버전별 변경점](https://johngrib.github.io/wiki/java-enhancements/)
* [JDK 14 기능정리](https://okky.kr/article/676912)

##### [목차로 이동](#목차)