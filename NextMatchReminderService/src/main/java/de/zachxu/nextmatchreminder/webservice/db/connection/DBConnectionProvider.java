package de.zachxu.nextmatchreminder.webservice.db.connection;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.zachxu.nextmatchreminder.webservice.NMRProperty;
import de.zachxu.nextmatchreminder.webservice.NMRProperty.NMRParam;

public class DBConnectionProvider implements Closeable {
	
	private static final Logger LOG = LogManager.getLogger(DBConnectionProvider.class);
	
	private Connection mConnection;
	
	/**
	 * 
	 */
	public DBConnectionProvider()
	{
		try
		{
			Class.forName(NMRProperty.getProperty(NMRParam.DBDRIVER));
			
			mConnection = DriverManager.getConnection(NMRProperty.getDBURL(), 
					NMRProperty.getProperty(NMRParam.DBUSER), 
					NMRProperty.getProperty(NMRParam.DBPASSWORD));
		} catch (ClassNotFoundException | SQLException e)
		{
			LOG.error(e.getMessage(), e);
		}
	}
	
	/**
	 * 
	 * @param sql
	 * @return execute query
	 * @throws SQLException 
	 */
	public ResultSet executeQuery(String sql, Object...params) throws SQLException
	{
		 PreparedStatement stmt = mConnection.prepareStatement(sql);
		 
		 if (params != null)
		 {
			 for (int i = 0; i < params.length; i++)
			 {
				 stmt.setObject(i + 1, params[i]);
			 }
		 }
		 
		return stmt.executeQuery();
	}

	@Override
	public void close() throws IOException
	{
		if (mConnection != null)
		{
			try
			{
				mConnection.close();
			} catch (SQLException e)
			{
				LOG.error(e.getMessage(), e);
			}
		}
	}

	
}
