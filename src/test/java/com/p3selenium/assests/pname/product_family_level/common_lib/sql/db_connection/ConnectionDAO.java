/**
 * 
 */
package com.p3selenium.assests.pname.product_family_level.common_lib.sql.db_connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author ABHISHEK
 * 
 */
public abstract class ConnectionDAO {

	private static Connection con;

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		}
	}

	public static Connection getConnection() {
		try {
			if ((con == null) || (con.isClosed()))
				con = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/db", "abhi", "");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;

	}

}
