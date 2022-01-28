package Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimePractice {
	public static void main(String[] args) {
		//https://velog.io/@hth9876/LocalDate-LocalTime-LocalDateTime
		
		LocalDateTime now = LocalDateTime.now();
		
		//날짜 세팅
		LocalDateTime setDate = LocalDateTime.of(1994,  06, 12, 10, 45);
		
		String formatDate = setDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		
		System.out.println("setDate >>> "+ setDate);
		System.out.println("formatDate >>> "+ formatDate);
		System.out.println("-------------------------------------");
		System.out.println("현재시각 >>> " + now);
		System.out.println("하루 후  >>> " + now.plusDays(1L));
		System.out.println("2주 일 전 >>> " + now.plusWeeks(2L));
		System.out.println("한 달 전 >>> " + now.minusMonths(1L));
		System.out.println("한 달 후 >>> " + now.plusMonths(1L));
		
		//String ->LocalDateTime
		//T까지 넣어가며 완벽한 문자열을 넣던가(없으면 에러)
		LocalDateTime strDate = LocalDateTime.parse("2022-10-12T12:34:56");
		
		LocalDateTime strDate2 = LocalDateTime.parse("2022-10-12 12:34:56", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		
		System.out.println(strDate);
		System.out.println(strDate2);
		
		
	}
}
