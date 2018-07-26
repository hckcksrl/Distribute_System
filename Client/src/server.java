import java.net.*;
import java.io.*;
import java.util.*;
import java.text.*;
public class server {
	// 소켓연결을 위한 변수 생성 
	static ServerSocket server;
	static Socket sock;
	static InetAddress inetaddr;
	static OutputStream out;
	static InputStream in;
	static PrintWriter pw;
	static BufferedReader br;
	static String lines;
	
	// 소켓을 연결하는 main함수 
	public static void main(String[] args) {
        try {
        		server = new ServerSocket(10001);
        		System.out.println("Wating Connect ..");
        		sock = server.accept();
        		inetaddr = sock.getInetAddress();
        		System.out.println(inetaddr.getHostAddress()+ " 로부터 접속했습니다.");
        		out = sock.getOutputStream();
        		in = sock.getInputStream();
        		pw = new PrintWriter(new OutputStreamWriter(out));
        		br = new BufferedReader(new InputStreamReader(in));
        	
        }catch(Exception e) {
        		System.out.println(e);
        }
	}
	// 입력받은 문자열을 server에 전송하는 함수 
	void into(String line){
		try{

				String a[] = line.split(" ");		
				if(a[0].equals("vi")) {
					write(a[1],pw);
					String line3 = br.readLine();
					file(line3,a[1]);
					date(pw);
					read(a[1],pw);
				}else {
					date(pw);
					writes(line,pw);
				}
			
		} catch(IOException e){
			System.out.println(e);
		}
	}
	
	// 소켓연결을 끊는 함수 
	static void close() throws IOException {
		pw.close();
        br.close();
        sock.close();
	}
	
	// client에서 전송한 파일이름의 파일을 열고 client에서 입력한 문자열을 파일에 write하는 함수 
	static void file(String line3,String t) throws IOException {
		File file = new File(t);
		FileWriter fw = new FileWriter(file,true);
		fw.write(line3+"\n");
		fw.flush();
		fw.close();
	}
	// client에서 전송한 파일이름의 파일을 외부프로그램을 실행하는 함수를 사용하여 읽고
	// 읽은 문자열을 client에 전송하는 함
	static void write(String k,PrintWriter pw) {
		String s;
		Process p;
		try {
			p = Runtime.getRuntime().exec("cat "+k);
			BufferedReader br3 = new BufferedReader(
					new InputStreamReader(p.getInputStream()));
			while ((s = br3.readLine()) != null)
				pw.println(s);
			pw.println("a");
			pw.flush();
			p.waitFor();
			p.exitValue();
			p.destroy();
		}catch (Exception e) {}
	}
	
	//client에서 전송한 문자열 외부프로그램을 실행하는 함수를 사용하여 실행하고 그 결과를
	//client에 전송하는 함
	static void writes(String lines,PrintWriter pw) {
    	   	String s;
		Process p;
		try {
			p = Runtime.getRuntime().exec(lines);
			BufferedReader br3 = new BufferedReader(
					new InputStreamReader(p.getInputStream()));
			while((s = br3.readLine()) != null)
              	pw.println(s);
			pw.println("a");
			pw.flush();
			p.waitFor();
			p.exitValue();
			p.destroy();		
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	// client에서 전송한 파일이름의 파일을 읽고 읽은 문자열을 client에 전송하는 함
	static void read(String line,PrintWriter pw) throws IOException {
		BufferedReader br2 = new BufferedReader(new FileReader(line));
		String s;                        		    
	    while ((s = br2.readLine()) != null)
			pw.println(s);
		pw.println("a");
		pw.flush();
	    br2.close();
	}
	
	// 지금 시간을 simpleDateFormat으로 읽는 함
	static void date(PrintWriter pw){
		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd, hh:mm:ss");
		String h = sdf.format(dt).toString();
		pw.println("\n\n"+h+"\n");
	}
}
