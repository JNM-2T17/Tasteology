package flamingos.pink.tasteology.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
	private String driverName;
	private String url;
	private String dbName;
	private String username;
	private String password;
	private static DBManager instance = null;
	
	private DBManager(String driverName,String url,String dbName
						,String username,String password) {
		this.driverName = driverName; //com.mysql.jdbc.DriverManager
		this.url = url; //jdbc.mysql://127.0.0.1:3306/
		this.dbName = dbName; //db_hpq
		this.username = username; //root
		this.password = password;
	}

	public Connection getConnection() {
		try {
			return DriverManager.getConnection(url + dbName,username,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static DBManager getInstance(){
		if(instance!=null){
			return instance;
		} else {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			instance = new DBManager("com.mysql.jdbc.DriverManager"
					,"jdbc:mysql://127.0.0.1:3306/"
					,"pf_tasteology","root","");
			return instance;
		}
	}
	
	public String getDriverName() {
		return driverName;
	}

	public String getUrl() {
		return url;
	}

	public String getDbName() {
		return dbName;
	}

	public String getUsername() {
		return username;
	}
}
