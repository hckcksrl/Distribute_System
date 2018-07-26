import java.io.*;
import java.net.*;
import java.util.*;


public class clientApp{
    public static void main(String[] args) throws IOException{
        client c = new client();
        // client 객체 생성 
        c.connection();
        // socket 연결 
        String line;
        BufferedReader keyboard =
                new BufferedReader(new InputStreamReader(System.in));
        // command line 에서 문자열을 입력하고 keyboard 변수에 할당하는 변수 
        while(true) {
        		line = keyboard.readLine();
        		if(line.equals("exit")) { 
        			c.into(line);
        			// 입력받은 문자열을 server에 전송 
        			c.close();
        			break;
        		}else {
        			c.into(line);
        			// 입력받은 문자열을 server에 전송 
        		}
        }
    }

}
