package Utilities;
import Pages.LoginPage;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

public class DbUtil {
	static Connection dbConnection = null;
//	LoginPage lg=new LoginPage();

	// Retrun Value from DB
	public String getOTPValueFromDB(String url, String user, String pass, String getValue, String From, String Where,String countryCode) throws Exception {
		String valueIs = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Properties info = new Properties();
			info.put("user", user );
			info.put("password", pass);
			dbConnection = DriverManager.getConnection(url, info);
			if (dbConnection != null) {
				System.out.println("Successfully connected to MySQL database test");
//				String qry = "SELECT "+ getValue+" FROM "+ From +" where "+ "mobile_no="+ Where;
				Statement s= dbConnection.createStatement();
				System.out.println("SELECT "+ getValue+" FROM "+ From+" Where"+" mobile_no="+ countryCode+Where+" order by created_on desc limit 1");
				ResultSet rs=s.executeQuery("SELECT "+ getValue+" FROM "+ From+" Where"+" mobile_no="+ countryCode+Where+" order by created_on desc limit 1");
				while(rs.next())
				{
					valueIs=rs.getString(1);
					System.out.println("Value to return is : "+valueIs);
				}
			}
		} catch (SQLException ex)
		{ System.out.println("An error occurred while connecting MySQL databse");
		ex.printStackTrace();
		}finally{
			dbConnection.close();
		}
		return valueIs;
	}

	// Retrun Value from DB
	public static String getDbValue(String url, String user, String pass, String getValue, String From, String Where) throws Exception {
//		Connection dbConnection = null;
		String valueIs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Properties info = new Properties();
			info.put("user", user );
			info.put("password", pass);
			dbConnection = DriverManager.getConnection(url, info);
			if (dbConnection != null) {
				System.out.println("Successfully connected to MySQL database test");
				Statement s= dbConnection.createStatement();
				ResultSet rs=s.executeQuery("SELECT "+ getValue+"  FROM "+ From +"  where "+ "user_id="+Where);
				while(rs.next())
				{
					valueIs=rs.getString(1);
					System.out.println("Value to return is : "+valueIs);
				}
			}
		} catch (SQLException ex)
		{ System.out.println("An error occurred while connecting MySQL databse");
			ex.printStackTrace();
		}finally{
			dbConnection.close();
		}
		return valueIs;
	}

	//To Check if value exists in DB or not
	public boolean checkValueExists(String url,String user,String  pass, String From, String Value) throws Exception {
		Connection dbConnection = null;
		boolean valueExist=false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Properties info = new Properties();
			info.put("user", user );
			info.put("password", pass);
			dbConnection = DriverManager.getConnection(url, info);
			if (dbConnection != null) {
				System.out.println("Successfully connected to MySQL database test");
				Statement s= dbConnection.createStatement();
				System.out.println("SELECT "+"user_id"+ " FROM "+ From +"  where "+ "user_id="+Value );
				ResultSet rs=s.executeQuery("SELECT "+"user_id"+ " FROM "+ From +" where "+ "user_id="+"'"+Value+"'");
				System.out.println(rs.isBeforeFirst());
				valueExist = rs.isBeforeFirst();
			}
		} catch (SQLException ex)
		{ System.out.println("An error occurred while connecting MySQL databse");
		ex.printStackTrace();
		}finally{
			dbConnection.close();
		}
		return valueExist;
	}
	//Check if value exists in specific column
	public static boolean checkValueExistsInColumn(String url,String user,String  pass, String From,String columnName, String Value) throws Exception {
		Connection dbConnection = null;
		boolean valueExist=false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Properties info = new Properties();
			info.put("user", user );
			info.put("password", pass);
			dbConnection = DriverManager.getConnection(url, info);
			if (dbConnection != null) {
				System.out.println("Successfully connected to MySQL database test");
				Statement s= dbConnection.createStatement();
				ResultSet rs=s.executeQuery("SELECT "+Value+ " FROM "+ From +"  where "+ columnName+"="+Value);
				System.out.println(rs.isBeforeFirst());
				valueExist = rs.isBeforeFirst();
			}
		} catch (SQLException ex)
		{ System.out.println("An error occurred while connecting MySQL databse");
		ex.printStackTrace();
		}finally{
			dbConnection.close();
		}
		return valueExist;
	}
	// Retrun Value from DB
	public static String getDbValue(String url, String user, String pass, String getValue, String From,String column, String Where) throws Exception {
//			Connection dbConnection = null;
		String valueIs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Properties info = new Properties();
			info.put("user", user );
			info.put("password", pass);
			System.out.println("url is :"+url);
			dbConnection = DriverManager.getConnection(url, info);
			if (dbConnection != null) {
				System.out.println("Successfully connected to MySQL database test");
//				System.out.println("Query : "+ "SELECT "+ getValue+"  FROM "+ From +"  where "+ column+ "="+Where);
				Statement s= dbConnection.createStatement();
				System.out.println("SELECT "+ getValue+"  FROM "+ From +"  where "+ column+ "="+Where+" order by id desc limit 1");
				ResultSet rs=s.executeQuery("SELECT "+ getValue+"  FROM "+ From +"  where "+ column+ "="+Where+" order by id desc limit 1");
				while(rs.next())
				{
					valueIs=rs.getString(1);
					System.out.println("Value to return is : "+valueIs);
				}
			}
		} catch (SQLException ex)
		{ System.out.println("An error occurred while connecting MySQL databse");
			ex.printStackTrace();
		}finally{
			dbConnection.close();
		}
		return valueIs;
	}
	// Return from SubQuery
	public static String getDbValueFromSubQuery(String url, String user, String pass, String getValue, String From,String column, String otherQuery) throws Exception {
//			Connection dbConnection = null;
		String valueIs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Properties info = new Properties();
			info.put("user", user );
			info.put("password", pass);
			dbConnection = DriverManager.getConnection(url, info);
			if (dbConnection != null) {
				System.out.println("Successfully connected to MySQL database test");
				Statement s= dbConnection.createStatement();
				ResultSet rs=s.executeQuery("SELECT "+ getValue+"  FROM "+ From +"  where "+ column+ " in ("+otherQuery+")");
				while(rs.next())
				{
					valueIs=rs.getString(1);
					System.out.println("Value to return is : "+valueIs);
				}
			}
		} catch (SQLException ex)
		{ System.out.println("An error occurred while connecting MySQL databse");
			ex.printStackTrace();
		}finally{
			dbConnection.close();
		}
		return valueIs;
	}

	public static String getCardNameFromDB(String url, String user, String pass, String getValue, String From,String column, String Where) throws Exception {
//			Connection dbConnection = null;
		String valueIs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Properties info = new Properties();
			info.put("user", user );
			info.put("password", pass);
			dbConnection = DriverManager.getConnection(url, info);
			if (dbConnection != null) {
				System.out.println("Successfully connected to MySQL database test");
				Statement s= dbConnection.createStatement();
				System.out.println("SELECT "+ getValue+"  FROM "+ From +"  where "+ column+ "="+Where+"");
				ResultSet rs=s.executeQuery("SELECT "+ getValue+"  FROM "+ From +"  where "+ column+ "="+Where);
				while(rs.next())
				{
					valueIs=rs.getString(1);
					System.out.println("Value to return is : "+valueIs);
				}
			}
		} catch (SQLException ex)
		{ System.out.println("An error occurred while connecting MySQL databse");
			ex.printStackTrace();
		}finally{
			dbConnection.close();
		}
		return valueIs;
	}

	//Check if Bill exists
	public static boolean checkValueExistsInDB(String url,String user,String  pass, String From,String columnName, String Value) throws Exception {
		Connection dbConnection = null;
		boolean valueExist=false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Properties info = new Properties();
			info.put("user", user );
			info.put("password", pass);
			dbConnection = DriverManager.getConnection(url, info);
			if (dbConnection != null) {
				System.out.println("Successfully connected to MySQL database test");
				Statement s= dbConnection.createStatement();
				System.out.println("SELECT "+columnName+ " FROM "+ From +"  where "+ columnName+"="+Value);
				ResultSet rs=s.executeQuery("SELECT "+columnName+ " FROM "+ From +"  where "+ columnName+"="+"'"+Value+"'");
				System.out.println(rs.isBeforeFirst());
				valueExist = rs.isBeforeFirst();
			}
		} catch (SQLException ex)
		{ System.out.println("An error occurred while connecting MySQL databse");
			ex.printStackTrace();
		}finally{
			dbConnection.close();
		}
		return valueExist;
	}

	public static void exportDbToExcel(String url, String user, String pass, String FROM) throws SQLException {
		String filePath =  System.getProperty("user.dir") + "\\src\\test\\resources\\ExcelData\\ExportedData\\";
		ExcelExport export = new ExcelExport();
		Connection dbConnection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Properties info = new Properties();
			info.put("user", user);
			info.put("password", pass);
			System.out.println("url is :"+url);
			dbConnection = DriverManager.getConnection(url, info);
			if (dbConnection != null) {
				System.out.println("Successfully connected to MySQL database test");
				Statement s = dbConnection.createStatement();
				ResultSet rs = s.executeQuery("SELECT * FROM "+FROM+" limit 5");
				XSSFWorkbook workbook = new XSSFWorkbook();
				XSSFSheet sheet = workbook.createSheet(FROM);
				export.writeHeaderLine(rs, sheet);
				export.writeDataLines(rs, sheet);
				String excelFilePath =filePath + export.getFileName(FROM.concat("_Export"));
				FileOutputStream outputStream = new FileOutputStream(excelFilePath);
				workbook.write(outputStream);
				workbook.close();
				s.close();
			}
		} catch (Exception ex) {
			System.out.println("An error occurred while connecting MySQL database");
			ex.printStackTrace();
		}finally {
			dbConnection.close();
		}
	}
	// Retrun Value from DB
	public static String getValue(String url, String user, String pass, String getValue, String From, String Where) throws Exception {
//		Connection dbConnection = null;
		String valueIs = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Properties info = new Properties();
			info.put("user", user );
			info.put("password", pass);
			dbConnection = DriverManager.getConnection(url, info);
			if (dbConnection != null) {
				System.out.println("Successfully connected to MySQL database test");
				Statement s= dbConnection.createStatement();
				ResultSet rs=s.executeQuery("SELECT "+ getValue+"  FROM "+ From +"  where "+ "biller_nick_name="+"'"+Where+"'");
				while(rs.next())
				{
					valueIs=rs.getString(1);
					System.out.println("Value to return is : "+valueIs);
				}
			}
		} catch (SQLException ex)
		{ System.out.println("An error occurred while connecting MySQL databse");
			ex.printStackTrace();
		}finally{
			dbConnection.close();
		}
		return valueIs;
	}

	public static String getBillPaymentStatusFromDb(String url, String user, String pass, String From, String Where, String billname) throws Exception {
		String valueIs = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Properties info = new Properties();
			info.put("user", user );
			info.put("password", pass);
			dbConnection = DriverManager.getConnection(url, info);
			if (dbConnection != null) {
				System.out.println("Successfully connected to MySQL database test");
				Statement s= dbConnection.createStatement();
				ResultSet rs=s.executeQuery("SELECT status FROM "+ From +"  where bill_amount= "+"'"+Where+"' and biller_name='"+billname +"' order by created_on Desc Limit 1");
				while(rs.next())
				{
					valueIs=rs.getString(1);
					System.out.println("Value to return is : "+valueIs);
				}
			}
		} catch (SQLException ex)
		{ System.out.println("An error occurred while connecting MySQL databse");
			ex.printStackTrace();
		}finally{
			dbConnection.close();
		}
		return valueIs;
	}

	public static String sqlFormatedList(List<String> list){
		StringBuilder sb = new StringBuilder();
		sb.append("('");
		for (String i : list){
			sb.append(i+"','");
		}
		sb.deleteCharAt(sb.length() -1);
		sb.deleteCharAt(sb.lastIndexOf(","));
		sb.append(")");
		return sb.toString();
	}

	// Retrun Value from DB
	public static String getDbValueMultipleParameter(String url, String user, String pass, String getValue, String From,String column, String Where,String column2, String Where2) throws Exception {
//			Connection dbConnection = null;
		String valueIs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Properties info = new Properties();
			info.put("user", user );
			info.put("password", pass);
			System.out.println("url is :"+url);
			dbConnection = DriverManager.getConnection(url, info);
			if (dbConnection != null) {
				System.out.println("Successfully connected to MySQL database test");
//				System.out.println("Query : "+ "SELECT "+ getValue+"  FROM "+ From +"  where "+ column+ "="+Where);
				Statement s= dbConnection.createStatement();
				System.out.println("SELECT "+ getValue+"  FROM "+ From +"  where "+ column+ "="+Where+" and "+column2 + "="+Where2+"");
				ResultSet rs=s.executeQuery("SELECT "+ getValue+"  FROM "+ From +"  where "+ column+ "="+Where+" and "+column2 + "="+Where2+"");
				while(rs.next())
				{
					valueIs=rs.getString(1);
					System.out.println("Value to return is : "+valueIs);
				}
			}
		} catch (SQLException ex)
		{ System.out.println("An error occurred while connecting MySQL databse");
			ex.printStackTrace();
		}finally{
			dbConnection.close();
		}
		return valueIs;
	}

}
