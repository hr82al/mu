package ru.haval.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import ru.haval.share_class.s_class;

public class _connect {

	private static final String url = "jdbc:mysql://10.168.150.74:3306/hmmr_mu?serverTimezone=UTC&useSSL=false";
    private static final String user = "hmmr";
    private static final String password = "Ro145437";
    public static Connection con;
                
	public _connect() {
		
	}
	/*****************************************
	 * Подключаемся к БД MySQL				 *
	 *****************************************/
	public void ConToDb()
	{
		try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
        	s_class._AlertDialog(e.getMessage(), "Внимание! Ошибка подключения к БД.");
        }
	}
	
	public Connection ConToDb1()
	{
		try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
        	s_class._AlertDialog(e.getMessage(), "Внимание! Ошибка подключения к БД.");
        }
		return con;
	}

}
