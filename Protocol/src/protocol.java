import java.io.*;
import java.net.*;
import java.sql.*;


public class protocol {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ResultSet rs =null;
		PreparedStatement pstmt=null;
		Connection conn = null ;
		try {
			ServerSocket server = new ServerSocket(10001);
    			System.out.println("Wating Connect ..");
    			Socket sock = server.accept();
    			InetAddress inetaddr = sock.getInetAddress();
    			System.out.println(inetaddr.getHostAddress()+ " 로부터 접속했습니다.");
    			OutputStream out = sock.getOutputStream();
    			InputStream in = sock.getInputStream();
    			PrintWriter pw = new PrintWriter(new OutputStreamWriter(out));
    			BufferedReader br = new BufferedReader(new InputStreamReader(in));			
			
    			String line1 = br.readLine();
    			String line2 = br.readLine();
    			String s = decrypt(line1);
    			String s2 = decrypt(line2);
    			
    			String dbId = "root";
			String dbPass = "1111";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/login?autoReconnect=true&useSSL=false",dbId,dbPass);
			String sql = "select * from login where id = ? and passwords = ?";
			pstmt = conn.prepareStatement(sql);
			System.out.println(s);
			System.out.println(s2);
			
			pstmt.setString(1, s);
			pstmt.setString(2, s2);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				System.out.println("로그인 했습니다.");
				pw.println("로그인했습니다.");
				pw.flush();
			}else {
				System.out.println("실패했습니다.");
				pw.println("로그인못했습니다.");
				pw.flush();
				pw.close();
				br.close();
				sock.close();
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null)try {rs.close();}catch(SQLException sqle) {}
			if(pstmt != null)try {pstmt.close();}catch(SQLException sqle) {}
			if(conn != null)try {conn.close();}catch(SQLException sqle) {}
		}
		
	}
	static String decrypt(String line) {
		char buf[] = line.toCharArray();
		char key[] = {'a','b','c'};
		char buff[] = new char[line.length()];
		int k = 0;
		int t = 0;
		System.out.println(line.length());
		while(k<line.length()) {
			if(t<3) {
				buff[k] = (char) (buf[k]-key[t]);
				t++;
			}else {
				t = 0;
				buff[k] = (char) (buf[k]-key[t]);
				t++;
			}
			k++;
		}
		String str = String.valueOf(buff);
		return str;
	}
	

}
