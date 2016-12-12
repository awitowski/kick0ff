package kickoff.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionProvider {

	private static DataSource dataSource;

	public static Connection getConnection() throws SQLException {
		return getDataSource().getConnection();
	}

	public static DataSource getDataSource() {
		if (dataSource == null) {
			Context initContext;
			try {
				initContext = new InitialContext();
				dataSource = (DataSource) initContext.lookup("java:comp/env/jdbc/excavation");
			} catch (NamingException e) {
				e.printStackTrace();
			}

		}
		return dataSource;
	}

}
