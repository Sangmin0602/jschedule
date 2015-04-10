package testutils.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
/**
 * 쿼리 파일에서 각각의 쿼리를 추출해서 실행하는 역할을 합니다.
 * 
 * 
 * @author chminseo
 *
 */
public class QueryExecutor {
	public static String NL = System.getProperty("line.separator");
	
	private Connection conn ;
	public QueryExecutor(Connection conn) {
		this.conn = conn;
	}

	private void close(Connection conn, Statement stmt, ResultSet rs) {
		if ( conn != null) {
			try {
				conn.clearWarnings();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if ( stmt != null ) {
			try {
				stmt.clearWarnings();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if ( rs != null) {
			try {
				rs.clearWarnings();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void rollback(Connection conn) {
		try {
			conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 하나의 쿼리를 실행함.
	 * @param query
	 */
	public void executeQuery(Statement stmt, String query) {
		try {
//			stmt = conn.createStatement();
			stmt.execute(query);
			stmt.clearWarnings();
//			System.out.println("Query U[" + stmt.getUpdateCount() + "] : " + query);
//			System.out.println("      " + stmt.getWarnings());
		} catch (SQLException e) {
			rollback(conn);
			e.printStackTrace();
		} finally {
		}
	}

	/**
	 * 주어진 sql file 의 스트림을 모두 실행함.
	 * @param in
	 * @throws IOException
	 * @throws SQLException
	 */
	public void runQueries(InputStream in, SqlParser parser) throws IOException, SQLException {
		List<Sql> queries = parser.parse(in);
		Iterator<Sql> itr = queries.iterator();
		Statement stmt = conn.createStatement();
		while ( itr.hasNext()) {
			Sql sql = itr.next();
			executeQuery(stmt, sql.getQuery());
		}
		conn.commit();
	}
}
