/**
 * 
 */
package com.p3selenium.assests.pname.product_family_level.common_lib.sql.db_connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.p3selenium.assests.pname.product_family_level.common_lib.data_source.LoadProperty;

/**
 * @author ABHISHEK
 * 
 */
public abstract class ConnectionDAO {

	private static Connection con;
	private static String db_connection;
	static {
		try {
			db_connection = LoadProperty.getVar("db_connection",
					"config");
			if (db_connection == "mysql") {
				Class.forName("com.mysql.jdbc.Driver");
			}

			else {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			}
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		}
	}

	public static Connection getConnection() {
		try {
			if ((con == null) || (con.isClosed()))
				if (db_connection == "mysql") {
					con = DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/db", "abhi", "");
				} else {
					con = DriverManager.getConnection(
							"jdbc:oracle:thin:@localhost:1521:db", "abhi", "");
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;

	}

}
