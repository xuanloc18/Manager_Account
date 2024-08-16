package database;

import java.sql.Connection;

public interface JDBCUtill {
	public Connection getConnection() ;
	public void closeConnection(Connection c) ;
	public void printInfo(Connection c);
}
