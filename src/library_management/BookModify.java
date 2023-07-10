package library_management;

import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
//import java.util.ArrayList;
//import java.util.List;
import java.util.Vector;

import connectDB.ConnectDB;

public class BookModify
{
	Connection conn = null;
	public BookModify()
	{
		
	}
	
	public ArrayList<Book> getBookList()
	{
		ArrayList<Book> bookList = new ArrayList<Book>();
		try 
		{
			conn = ConnectDB.getConnection();
			Statement stmt = conn.createStatement();
//			String sql = "call sp_findAllBook()";
			String sql = "SELECT * FROM fc_findallbook();";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				
				Book book = new Book(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5),
									rs.getInt(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10));
				
				bookList.add(book);
			}
			
			stmt.close();
			ConnectDB.closeConnection(conn);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return bookList;
	}
	
	public void addBook(Book book)
	{
		try {
			conn = ConnectDB.getConnection();
			String sql = "call sp_insertBook(?,?,?,?,?,?,?,?,?);";
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, book.getBookName());
			stmt.setInt(2, book.getPageNo());
			stmt.setString(3, book.getLanguage());
			stmt.setInt(4, book.getPrice());
			stmt.setInt(5, book.getAmount());
			stmt.setString(6, book.getPublishYear());
			stmt.setString(7, book.getType());
			stmt.setString(8, book.getAuthor());
			stmt.setString(9, book.getPublisher());
			stmt.execute();
			
			stmt.close();
			ConnectDB.closeConnection(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteBook(int bookId)
	{
		try {
			conn = ConnectDB.getConnection();
			String sql = "call sp_deleteBook(?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, bookId);
			stmt.execute();
			
			stmt.close();
			ConnectDB.closeConnection(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateBook(Book book)
	{

		try {
			conn = ConnectDB.getConnection();
			String sql = "call sp_updateBook(?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, book.getBookName());
			stmt.setInt(2, book.getPageNo());
			stmt.setString(3, book.getLanguage());
			stmt.setInt(4, book.getPrice());
			stmt.setInt(5, book.getAmount());
			stmt.setString(6, book.getPublishYear());
			stmt.setString(7, book.getType());
			stmt.setString(8, book.getAuthor());
			stmt.setString(9, book.getPublisher());
			stmt.setInt(10, book.getBookId());
			stmt.execute();
			
			stmt.close();
			ConnectDB.closeConnection(conn);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

//	
	public ArrayList<Book> findBookBy(String sql, String parameter)
	{
		ArrayList<Book> bookList = new ArrayList<Book>();

		try 
		{	
			conn = ConnectDB.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%"+parameter+"%");
			stmt.execute();
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				Book book = new Book(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5),
						rs.getInt(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10));
				bookList.add(book);
			}
			
			stmt.close();
			ConnectDB.closeConnection(conn);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return bookList;
	}
	
	public ArrayList<Book> sortAZPageNo(String sql)
	{
		ArrayList<Book> bookList = new ArrayList<Book>();
		try {
			conn = ConnectDB.getConnection();
			Statement stmt = conn.createStatement();
//			String sql = "SELECT * FROM sach ORDER BY sotrang ASC";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				Book book = new Book(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5),
						rs.getInt(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10));
				bookList.add(book);
			}
			
			
			stmt.close();
			ConnectDB.closeConnection(conn);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return bookList;
		
	}

	public int checkSL(int bookId) {
	    int sl = 0;
	    
	    try {
	        conn = ConnectDB.getConnection();
	        String sql = "SELECT fc_getBookQuantity(?)";
	        PreparedStatement stmt = conn.prepareStatement(sql);
	        
	        stmt.setInt(1, bookId);
	        
	        ResultSet rs = stmt.executeQuery();
	        
	        if (rs.next()) {
	            sl = rs.getInt(1);
	        }
	        
	        stmt.close();
	        ConnectDB.closeConnection(conn);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return sl;
	}

	public void updateSL(int bookId, int sl) {
		
	    try {
	        conn = ConnectDB.getConnection();
	        String sql = "SELECT fc_updateBookQuantity(?, ?)";
	        PreparedStatement stmt = conn.prepareStatement(sql);
	        
	        stmt.setInt(1, bookId);
	        stmt.setInt(2, sl);
			stmt.execute();
	        
	        stmt.close();
	        ConnectDB.closeConnection(conn);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

}

