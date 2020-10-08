package ru.haval.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import ru.haval.config.Config;
import ru.haval.share_class.s_class;

public class _connect {

	private static String url ;
    private static String user;
    private static String password;
    public static Connection con;
                
	public _connect() {

	}

	public static void init(){
		_connect.url = "jdbc:mysql://" +
				Config.getInstance().getAddress() +
				":" + Config.getInstance().getPort() +
				"/" + Config.getInstance().getDbName() +
				Config.getInstance().getDbParams();
		_connect.user = Config.getInstance().getDbUser();
		_connect.password = Config.getInstance().getDbPassword();
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
