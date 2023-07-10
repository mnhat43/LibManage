package library_management;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import connectDB.ConnectDB;

public class PunishmentModify
{
	Connection conn = null;
	public PunishmentModify()
	{
		
	}
	// Phạt - Đã trả
	public ArrayList<Punishment> getPunishmentList()
	{
		ArrayList<Punishment> punishmentList = new ArrayList<Punishment>();

		try {
			conn = ConnectDB.getConnection();
			String sql = "SELECT * FROM fc_punish();";
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				Punishment punishment = new Punishment();
				punishment.setLoanId(rs.getInt(1));
				punishment.setReaderId(rs.getInt(2));
				punishment.setFullname(rs.getString(3));
				punishment.setBookId(rs.getInt(4));
				punishment.setBook(rs.getString(5));
				punishment.setLoanNo(rs.getInt(6));
				punishment.setDaysLate(rs.getInt(7));
				punishment.setTotal(rs.getInt(8));
				punishment.setStatus(rs.getString(9));
				punishmentList.add(punishment);
			}
		
			stmt.close();
			ConnectDB.closeConnection(conn);
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return punishmentList;
	}
	
	// Phạt - Chưa trả
	public ArrayList<Punishment> getPunishmentListReturnYet()
	{
		ArrayList<Punishment> punishmentListReturnYet = new ArrayList<Punishment>();

		try {
			conn = ConnectDB.getConnection();
			String sql = "SELECT * FROM fc_punishReturnYet();";
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				Punishment punishment = new Punishment();
				punishment.setLoanId(rs.getInt(1));
				punishment.setReaderId(rs.getInt(2));
				punishment.setFullname(rs.getString(3));
				punishment.setBookId(rs.getInt(4));
				punishment.setBook(rs.getString(5));
				punishment.setLoanNo(rs.getInt(6));
				punishment.setDaysLate(rs.getInt(7));
				punishment.setTotal(rs.getInt(8));
				punishment.setStatus(rs.getString(9));
				punishmentListReturnYet.add(punishment);
			}
			
			stmt.close();
			ConnectDB.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return punishmentListReturnYet;
	}
}
