package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.LogonDataBean;

public class LogonDao {

	private static LogonDao instance = new LogonDao();

	public static LogonDao getInstance() {
		return instance;
	}

	private LogonDao() {
	}

	private Connection getConnection() throws Exception {
		Connection conn = null;
		try {
			String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:orcl";
			String dbId = "users";
			String dbPass = "1111";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbcUrl, dbId, dbPass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	// 중복 닉네임체크
	public String confirmName(String name) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = -1;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select name from member where name = ?");
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				x = 1;
			} else {
				x = -1;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}

		return x + "";
	}

	// 중복 이메일체크
	public String confirmEmail(String email) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = -1;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select email from member where email = ?");
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				x = 1;
			} else {
				x = -1;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}

		return x + "";
	}

	// 회원가입
	public void insertMember(LogonDataBean member) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("insert into member values(member_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt.setString(1, member.getEmail());
			pstmt.setString(2, member.getGender());
			pstmt.setString(3, member.getProfileImg());
			pstmt.setString(4, member.getName());
			pstmt.setString(5, member.getPassword());
			pstmt.setString(6, member.getPhone());
			pstmt.setString(7, member.getBirthday());
			pstmt.setString(8, member.getAddress());
			pstmt.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
	}

	// 회원 로그인 체크
	public int userCheck(String email, String password) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String dbpasswd = "";
		int x = -1;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select password from member where email = ?");
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dbpasswd = rs.getString("password");
				if (dbpasswd.equals(password))
					x = 1;
				else
					x = 0;
			} else
				x = -1;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}

		return x;
	}

	// 회원이름
	public String findName(String memEmail) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String name = "";

		try {
			conn = getConnection();
			String sql = "select name from member where email = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memEmail);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				name = rs.getString(1);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
		return name;
	}

	public LogonDataBean getUser(String memEmail){
			Connection conn=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			LogonDataBean user = new LogonDataBean();
				
			try{
				conn=getConnection();
				String sql="select * from member where email = ?";
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, memEmail);
				rs=pstmt.executeQuery();
				if(rs.next()){
					user.setAddress(rs.getString("address"));
					user.setBirthday(rs.getString("birthday"));
					user.setGender(rs.getString("gender"));
					user.setName(rs.getString("name"));
					user.setPhone(rs.getString("phone"));
					
				}			
			}catch(Exception ex){
				ex.printStackTrace();
			}finally{
				if(rs!=null) try{rs.close();} catch(SQLException ex){}
				if(pstmt!=null) try{pstmt.close();} catch(SQLException ex){}
				if(conn!=null) try{conn.close();} catch(SQLException ex){}
			}
			return user;
		}

}
