package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.entity.ReadBooksBean;

public class ReadingRecordsDAO {
	
	/**
	 * 読書記録の一覧を返します
	 * @return beanオブジェクトのリスト
	 */
	public List<ReadBooksBean>selectAllRecords(int useId){
		// beanリストをインスタンス化
		List<ReadBooksBean> rList = new ArrayList<ReadBooksBean>();
		// データベースへ接続
		try(Connection conn = ConnectionManager.getConnection()){
			// SELECT文を準備
			String sql = "SELECT t1.reading_id,"
						+"       t1.reading_date,"
						+"       t2.book_name,"
						+"       t1.read_pages,"
						+"       t1.reading_memos "
						+"FROM   reading_books.m_read_pages t1 "
						+"INNER JOIN reading_books.m_book t2 "
						+"ON     t1.book_id = t2.book_id "
						+"WHERE  t1.user_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, useId);
			// SELECTを実行
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int rRecordId = rs.getInt("reading_id");
				Date rDate = rs.getDate("reading_date");
				String rBookName = rs.getString("book_name");
				int rPages = rs.getInt("read_pages");
				String rMemos = rs.getNString("reading_memos");
				// 実行結果をインスタンスに格納
				ReadBooksBean rbb = new ReadBooksBean();
				rbb.setReadingId(rRecordId);
				rbb.setReadingDate(rDate);
				rbb.setBookName(rBookName);
				rbb.setReadPages(rPages);
				rbb.setReadingMemos(rMemos);
				// beanリストにインスタンスを追加
				rList.add(rbb);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("エラーが発生しました");
			return null;
		}
		
		return rList;
	}
	
	/**
	 * 読書記録の詳細を返します
	 * @return beanオブジェクト
	 */
	public ReadBooksBean selectRecord(int readingId) {
		// beanをインスタンス化
		ReadBooksBean rbb = new ReadBooksBean();
		// データベースへ接続
		try(Connection conn = ConnectionManager.getConnection()){
			// SELECT文を準備
			String sql = "SELECT t1.reading_date,"
					+ "       t2.book_name,"
					+ "       t1.read_pages,"
					+ "       t1.reading_memos "
					+ "FROM   reading_books.m_read_pages t1 INNER JOIN reading_books.m_book t2 "
					+ "ON     t1.book_id = t2.book_id "
					+ "WHERE  t1.reading_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, readingId);
			// SELECTを実行
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				Date rDate = rs.getDate("reading_date");
				String bookName = rs.getString("book_name");
				int rPages = rs.getInt("read_pages");
				String rMemos = rs.getString("reading_memos");
				// 実行結果をインスタンスに格納
				rbb.setReadingDate(rDate);
				rbb.setBookName(bookName);
				rbb.setReadPages(rPages);
				rbb.setReadingMemos(rMemos);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("エラーが発生しました");
			e.printStackTrace();
		}
		
		return rbb;
		
	}
	
	/**
	 * 読書記録作成・編集ページのプルダウンメニューに使用する、
	 * 本のリストを作成します。
	 * @return ユーザが所有している本のリスト
	 */
	public List<String>selectBookTitles(int useId){
		// インスタンス生成
		List<String> bookTitles = new ArrayList<String>();
		// データベースへ接続
		try(Connection conn = ConnectionManager.getConnection()){
			// SELECT文を準備
			String sql ="SELECT book_name FROM reading_books.m_book WHERE  user_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, useId);
			// SELECTを実行
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String bookName = rs.getString("book_name");
				// 実行結果をインスタンスに格納
				bookTitles.add(bookName);
			}		
					
			} catch (ClassNotFoundException | SQLException e) {
				System.out.println("エラーが発生しました");
				e.printStackTrace();
			}
		
		return bookTitles;
		
	}
	
	/**
	 * 読書記録を追加します
	 */
	public void addRecord(ReadBooksBean rbb) {
		// データベースへ接続
		try(Connection conn =ConnectionManager.getConnection()){
			// INSERT文を準備
			String sql = "INSERT INTO reading_books.m_read_pages "
					+ "            (reading_date, "
					+ "             book_id, "
					+ "             user_id, "
					+ "             read_pages, "
					+ "             reading_memos) "
					+ "    VALUES  (?, "
					+ "               (SELECT book_id "
					+ "                FROM   reading_books.m_book "
					+ "                WHERE  book_name = ?"
					+ "                ),"
					+ "             ?,?,?) ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setDate(1, (java.sql.Date)rbb.getReadingDate());
			pstmt.setString(2, rbb.getBookName());
			pstmt.setInt(3, rbb.getUserId());
			pstmt.setInt(4, rbb.getReadPages());
			pstmt.setString(5, rbb.getReadingMemos());
			// INSERTを実行
			int ud = pstmt.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
	
	/**
	 * 読書記録を更新します
	 */
	public void updateRecord(ReadBooksBean rbb) {
		// データベースへ接続
		try(Connection conn =ConnectionManager.getConnection()){
			// UPDATE文を準備
			String sql = "UPDATE reading_books.m_read_pages "
						+ "SET   reading_date = ?, "
						+ "      book_id = ("
						+ "          SELECT book_id "
						+ "          FROM   reading_books.m_book "
						+ "          WHERE  book_name = ?"
						+ "      ),"
						+ "      read_pages = ?, "
						+ "      reading_memos = ? "
						+ "WHERE reading_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setDate(1, (java.sql.Date)rbb.getReadingDate());
			pstmt.setString(2, rbb.getBookName());
			pstmt.setInt(3, rbb.getReadPages());
			pstmt.setString(4, rbb.getReadingMemos());
			pstmt.setInt(5, rbb.getReadingId());
			// UPDATEを実行
			int ud = pstmt.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("エラーが発生しました");
			e.printStackTrace();
		}
	}
	
	/**
	 * 読書記録を消去します
	 */
	public void deleteRecord(int readingId) {
		// データベースへ接続
		try(Connection conn =ConnectionManager.getConnection()){
			// DELETE文を準備
			String sql = "DELETE FROM reading_books.m_read_pages WHERE reading_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, readingId);
			// DETETEを実行
			int ud = pstmt.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("エラーが発生しました");
			e.printStackTrace();
		}
	}
};
