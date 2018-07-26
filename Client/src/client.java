import java.io.*;
import java.net.*;
import java.util.*;

public class client {
	// 소켓연결의 위한 객체와 command line에서 문자열을 읽을 객체 생성 
	static Socket sock;
	static OutputStream out;
	static InputStream in;
	static PrintWriter pw;
	static BufferedReader br;
	static BufferedReader keyboard =
            new BufferedReader(new InputStreamReader(System.in));
	
	// 소켓 객체를 연결하는 함수 
	static void connection() {
		try {
			sock = new Socket("127.0.0.1", 10001);
			out = sock.getOutputStream();
			in = sock.getInputStream();
			pw = new PrintWriter(new OutputStreamWriter(out));
			br = new BufferedReader(new InputStreamReader(in));
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	
	// command line 에서 읽은 문자열을 server로 전송하는 함수 
	static void into(String line) {
		
		try{
            String line2 = null;
            	String a[] = line.split(" ");
            	if(a[0].equals("vi")){
            		pw.println(line);
            		// 입력받은 문자열을 server에 전송 
            		pw.flush();
            		// pw 버퍼 비우기 
            		while((line2=br.readLine())!=null){	
            			if(line2.equals("a")){
            				break;
            			}else {
            				System.out.println(line2);
            			}
            		}
            		System.out.println("입력");
            		String line3 = keyboard.readLine();
            		pw.println(line3);
            		pw.flush();
            		while((line2 =br.readLine())!=null) {
            			if(line2.equals("a")){
            				break;
            			}
            			else {
            				file(line2);
            			}
            		}
            	}else if(line.equals("ls -al")||line.equals("ls")||a[0].equals("cat")){
            		pw.println(line);
            		pw.flush();
            		while((line2=br.readLine())!=null){
            			if(line2.equals("a")){
            				break;
            			}else {
            				System.out.println(line2);
            				file(line2);
            				// server에서 전송한 데이터를 file에 write
            			}
            		}
            	}else if(line.equals("exit")) {
            		System.out.println("프로그램을 끝냅니다.");
            	}else {
            		System.out.println("잘못 입력하셨습니다. 다시입력하세요");
            	}
     }catch(Exception e){
            System.out.println(e);
     }
	}
	
	// 서버에서 받은 데이터를 파일에 write하는 함수 
	static void file(String line3) throws IOException {
	    File file = new File("new신인규홍찬");
        FileWriter fw = new FileWriter(file,true);
        fw.write(line3+"\n");
        fw.flush();
        fw.close();
	}
	// 소켓연결을 끊는 함수 
	static void close() throws IOException {
		pw.close();
        br.close();
        sock.close();
	}
}
