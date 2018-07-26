import java.io.*;
import java.net.*;


public class client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
		Socket sock = new Socket("127.0.0.1", 10001);
		OutputStream out = sock.getOutputStream();
		InputStream in = sock.getInputStream();
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(out));
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		BufferedReader keyboard =
                new BufferedReader(new InputStreamReader(System.in));
		String line = keyboard.readLine();
		String s = encrypt(line);
		String line2 = keyboard.readLine();
		String s2 = encrypt(line2);
		pw.println(s);
		pw.flush();
		pw.println(s2);
		
		pw.flush();
		
		line = br.readLine();
		System.out.println(line);
		pw.close();
		br.close();
		sock.close();
		
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	static String encrypt(String line) {
		char buf[] = line.toCharArray();
		char key[] = {'a','b','c'};
		char buff[] = new char[line.length()];
		int k = 0;
		int t = 0;
		while(k<line.length()) {
			if(t<3) {
				buff[k] = (char) (buf[k]+key[t]);
				t++;
			}else {
				t = 0;
				buff[k] = (char) (buf[k]+key[t]);
				t++;
			}
			k++;
		}
		String str = String.valueOf(buff);
		return str;
	}
	

}
