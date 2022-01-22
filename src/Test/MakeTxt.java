package Test;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MakeTxt {
	public static void main(String[] args) throws IOException {
		//절대경로
		String path = "c:\\upload";
		//업로드할 디렉토리
		File uploadDir = new File(path, getPath());
		
		//경로가 존재하지 않을 경우 생성
		if(uploadDir.exists() == false) {
			uploadDir.mkdirs();
		}
		
		try {
			//파일 객체 생성
			File uploadFile = new File(uploadDir, "testTxt.txt");
			
			//실제 파일을 설정한 디렉토리에 생성
			FileOutputStream fos = new FileOutputStream(uploadFile);
			
			//입력된 값을 담을 변수 생성
			int write = 0;
			System.out.println("글자를 입력해 주세요 : 종료(A)입력");
			
			//입력된 값이 대문자 A(65)라면 종료
			while((write = System.in.read()) !=65) {
				//콘솔에 값을 입력하면 실제 텍스트파일에 입력이 된다.
				fos.write(write);
			}
			//생성종료
			fos.close();
			
			System.out.println("생성하신 파일을 읽겠습니다......\n\n\n\n");
			
			//업로드한 파일을 읽는다.
			FileInputStream fis = new FileInputStream(uploadFile);
			
			//읽어온 파일의 버퍼사이즈를 가져온다.
			int bufSize = fis.available();
			
			//가져온 버퍼 사이즈를 배열로 생성
			byte[] buf =new byte[bufSize];
			
			//버퍼 배열을 읽는다.
			fis.read(buf);
			
			System.out.println("====================================");
			System.out.println(new String(buf));			
			System.out.println("====================================");
			
			//파일 읽기 종료
			fis.close();
			
			//메모장 파일을 열어 확인 시켜준다.
			Desktop.getDesktop().edit(uploadFile);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//날짜별로 경로를 생성
	public static String getPath() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		return sdf.format(date).replace("/", File.separator);
	}
}
