package org.gongice.util.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DBOperation {

    protected Statement statement;
    protected Connection connection;

    /**
     * Constructor, Create a new DBOperation object.
     *
     * @param connection a certain connection.
     */
    public DBOperation(Connection connection) {
        this.connection = connection;
    }

   

    /**
     * open select
     *
     * @param sql sql string
     * @return ResultSet
     * @throws SQLException this method
     */
    public ResultSet query(String sql) throws SQLException {
//    statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        return rs;
    }

	public ResultSet query(String sql, DBTYPE type) throws SQLException {
		if (type.equals(DBTYPE.ORACLE)) {
			statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY);
		} else {
			statement = connection.createStatement();
		}
		ResultSet rs = statement.executeQuery(sql);
		return rs;
	}
	
	 /**
     * Close select
     *
     * @throws SQLException this method
     */
    public void closeQuery() throws SQLException {
        if (statement != null)
            statement.close();
    }

    /**
     * Run a sql string
     *
     * @param sql sql string
     * @return int
     * @throws SQLException this method
     */
    public int runSql(String sql) throws SQLException {
        statement = connection.createStatement();
        int result = statement.executeUpdate(sql);
        statement.close();
        return result;
    }
}
