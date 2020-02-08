직원관리 프로그램_v1
=====
* 코드 설명 및 피드백
* 피드백 부분은 *이탤릭체*
- - -
## 목차
1. [코드](#코드)
2. [참고](#참고)

## 코드
* 객체 설계에 대한 고민: Employee
	* [객체의 올바른 설계 조건](https://github.com/nara1030/TIL/blob/master/docs/lecture_list/code_spitz/s83_object1/object1_week1_ch00-ch01.md#%EA%B0%9D%EC%B2%B4%EC%99%80-%EC%84%A4%EA%B3%84)에는 자신의 상태를 스스로 관리하는 것이라 알고 있음
		* 이는 [상태 데이터를 가지는 객체에서 데이터를 꺼내려 하지 말고 메시지를 보내라](https://github.com/nara1030/ThisIsJava/blob/master/docs/etc/double_dispatch.md#%EC%9A%B0%EC%95%84%ED%95%9C%ED%85%8C%ED%81%AC%EC%BD%94%EC%8A%A4)는 말과 일맥상통
	* 하지만 본인 데이터를 스스로 관리한다고 생각하니 객체에 기능이 과도해짐
		* 예를 들어 출력(ex. 콘솔, 파일 등)의 경우 *논리적으로 생각해보면 Employee 클래스가 가져야 할 기능 아님*
* static에 대한 고민: EmpManagement
	* 직원 목록(List<Employee>)은 프로그램 실행 중 유지되어야 → static 필드 선언
		* static 멤버 선언 시 *CopyOnWriteArrayList를 사용 권장*
	* static 메소드에서 사용하기 위해 필드(ex. Printable) static 선언 → 인스턴스 멤버 선언 방법 찾기
		* 컨테이너 호출 메서드 static 선언하진 않음
	* static 멤버에 대해 배운 점
		* 클래스간 공유 가능하다는 오해 → ∵ static 멤버는 동일 클래스 모든 객체에 의해 공유
		* 클래스간 공유 위해서는 static 필드도 메서드 통해 전달 필요
* *예전 스타일 코드에 대한 고민*: EmpInit
* 빌더 패턴
	* 입력 시 공란(Null)으로 입력 방지(점층적 생성자 패턴 대안)
	* 초기화 및 입력 시 다른 생성자 구현(오버로딩)
		* 초기화 시 public 고민(∵ 외부 공개 불필요)
* 스트림 ≠ Loop
	* https://www.popit.kr/java8-stream%EC%9D%80-loop%EA%B0%80-%EC%95%84%EB%8B%88%EB%8B%A4/
	* https://homoefficio.github.io/2016/06/26/for-loop-%EB%A5%BC-Stream-forEach-%EB%A1%9C-%EB%B0%94%EA%BE%B8%EC%A7%80-%EB%A7%90%EC%95%84%EC%95%BC-%ED%95%A0-3%EA%B0%80%EC%A7%80-%EC%9D%B4%EC%9C%A0/
	* https://hamait.tistory.com/547
* 프로퍼티 파일
	* 변경 시 컴파일 불필요
	* 윈도우 ini 파일과 같은 기능
	* https://okky.kr/article/38761
	* https://javacan.tistory.com/entry/4
	* https://sthyun.tistory.com/entry/java%EC%97%90%EC%84%9C-property%ED%8C%8C%EC%9D%BC-%EC%89%BD%EA%B2%8C-%EC%B0%BE%EA%B8%B0-ClassLoader
	* *파일경로로 파일 식별하는 것에 대한 고민*

- - -

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

##### [목차로 이동](#목차)