import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;


public class serverApp{
	static String line;
	static server s;
    public static void main(String[] args) throws IOException{
        s = new server();
        // server 객체 생성 
        s.main(args);
        // server main함수 실
        while((line = s.br.readLine())!=null){
        		s.into(line);
        		// 입력받은 문자열을 client에 전
        }
    }
    
}