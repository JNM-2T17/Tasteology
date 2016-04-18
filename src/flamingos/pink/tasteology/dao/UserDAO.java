package flamingos.pink.tasteology.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import flamingos.pink.tasteology.model.BCrypt;

public class UserDAO {
	public static boolean addUser(String username, String password
									, String fName, String lName ) {
		Connection c = DBManager.getInstance().getConnection();
		
		String sql = "INSERT INTO tl_user(username,password,fName,lName) "
					+ "VALUES(?,?,?,?)";
		PreparedStatement ps;
		try {
			ps = c.prepareStatement(sql);
			ps.setString(1,username);
			
			String hash = BCrypt.hashpw(password,BCrypt.gensalt()); 
			ps.setString(2,hash);
			ps.setString(3,fName);
			ps.setString(4,lName);
	
			ps.executeQuery();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean checkPass(String username, String password) throws Exception{
		Connection c = DBManager.getInstance().getConnection();
		
		String sql = "SELECT password FROM tl_user WHERE username = ?";
		try {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if( rs.next() ) {
				String pass = rs.getString("password");
				return BCrypt.checkpw(password,pass);
			} else {
				throw new Exception("No such account");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}
	
	public static String getId(String username) {
		Connection c = DBManager.getInstance().getConnection();
		
		String sql = "SELECT md5(concat('pink',userId,'flamingos') as userId FROM tl_user WHERE username = ?";
		try {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if( rs.next() ) {
				return rs.getString("userId");
			} else {
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean verifyId(String id) {
		Connection c = DBManager.getInstance().getConnection();
		
		String sql = "SELECT userId FROM tl_user WHERE md5('pink',userId,'flamingos') = ?";
		try {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
