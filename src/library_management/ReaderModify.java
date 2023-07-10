package library_management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import connectDB.ConnectDB;

public class ReaderModify {
	Connection conn = null;
	public ReaderModify()
	{
		
	}
	
	public ArrayList<Reader> getReaderList()
	{
		ArrayList<Reader> readerList = new ArrayList<Reader>();

		try {
			conn = ConnectDB.getConnection();
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM fc_findAllReader();";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				Reader reader = new Reader();
				reader.setReaderId(rs.getInt(1));
				reader.setSurname(rs.getString(2));
				reader.setName(rs.getString(3));
				reader.setIdentityCard(rs.getString(4));
				reader.setPhoneNo(rs.getString(5));
				reader.setCardIssueDate(rs.getString(6));
				reader.setJob(rs.getString(7));
				
				readerList.add(reader);
			}
			
			stmt.close();
			ConnectDB.closeConnection(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return readerList;
	}
	
	public void addReader(Reader reader)
	{
		try {
			conn = ConnectDB.getConnection();
			String sql = "call sp_insertReader(?,?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, reader.getSurname());
			stmt.setString(2, reader.getName());
			stmt.setString(3, reader.getIdentityCard());
			stmt.setString(4, reader.getPhoneNo());
			stmt.setString(5, reader.getJob());
			stmt.execute();
			
			stmt.close();
			ConnectDB.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateReader(Reader reader)
	{
		try {
			conn = ConnectDB.getConnection();
			String sql = "call sp_updateReader(?,?,?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, reader.getSurname());
			stmt.setString(2, reader.getName());
			stmt.setString(3, reader.getIdentityCard());
			stmt.setString(4, reader.getPhoneNo());
			stmt.setString(5, reader.getJob());
			stmt.setInt(6, reader.getReaderId());
			stmt.execute();
			
			stmt.close();
			ConnectDB.closeConnection(conn);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void deleteReader(int readerId)
	{
		try 
		{
			conn = ConnectDB.getConnection();
			String sql = "call sp_deleteReader(?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
	
			stmt.setInt(1,readerId);
			stmt.execute();
			
			stmt.close();
			ConnectDB.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Reader> findReaderByAll(String sql, String parameter)
	{
		ArrayList<Reader> readerList = new ArrayList<Reader>();

		try 
		{
			conn = ConnectDB.getConnection();
//			String sql = "SELECT * FROM sp_findReaderByAll(?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%"+parameter+"%");
			stmt.execute();
			
			System.out.println(sql);
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next())
			{
				Reader reader = new Reader(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7));
				readerList.add(reader);
			}

			stmt.close();
			ConnectDB.closeConnection(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return readerList;
	}
}
