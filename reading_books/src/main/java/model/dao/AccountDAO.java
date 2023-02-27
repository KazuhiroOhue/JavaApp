package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.entity.Login;

public class AccountDAO {
		
	/**
	 * ログインユーザの情報からアカウントを検索し、
	 * 該当するユーザのIDと名前を返します
	 */
	public Login execute(Login login) {
		// Loginクラスのインスタンスを生成
		Login user = new Login();
		// データベースへ接続
		try(Connection conn =ConnectionManager.getConnection()){
			// SELECT文を準備
			String sql = "SELECT * FROM reading_books.m_user WHERE user_name = ? AND password = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, login.getUserName());
			pstmt.setString(2, login.getPassword());
			// SELECTを実行、ResultSetオブジェクトに値があればtrue
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				user.setUserId(rs.getInt("user_id"));
				user.setUserName(rs.getString("user_name"));
			}
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("エラーが発生しました！！");;
		}
		return user;
	}
	
	/**
	 * 登録するユーザ名が、既に登録済みでないかを確認、
	 * 既存ユーザ名と重複しなければtrueを返します
	 */
	public boolean registerExecute(Login register) {
		
		boolean userName = true;
		// データベースへ接続
		try(Connection conn =ConnectionManager.getConnection()){
			// SELECT文を準備
			String sql = "SELECT * FROM reading_books.m_user WHERE user_name = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, register.getUserName());
			// SELECTを実行、ResultSetオブジェクトに値があれば"false"
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				userName = false;
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("エラーが発生しました！");
			return false;
		}
		return userName;
	}
	
	/**
	 * 新規アカウントを登録し、IDを返します
	 * @param register
	 * @return なし
	 */
	public void register(Login register) {
		// Loginクラスのインスタンスを生成
		try(Connection conn =ConnectionManager.getConnection()){
			// INSERT文を準備
			String sql = "INSERT INTO reading_books.m_user (user_name, password) VALUES (?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// データの取り出し
			String userName = register.getUserName();
			String password = register.getPassword();
			// プレースホルダへの値の設定
			pstmt.setString(1, userName);
			pstmt.setString(2, password);
			// INSERTを実行
			int ud = pstmt.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("エラーが発生しました！");
		}
	}
	
	/**
	 * ユーザ名からIDを取得します
	 * @param userName
	 * @return int
	 */
	public int getId(String userName) {
		
		int userId = 0;
		// データベースへ接続
		try(Connection conn =ConnectionManager.getConnection()){
			// SELECT文を準備
			String sql = "SELECT user_id FROM reading_books.m_user WHERE user_name = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// プレースホルダへの値の設定
			pstmt.setString(1, userName);
			// SELECTを実行
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				userId = rs.getInt("user_id");
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("エラーが発生しました！");
			e.printStackTrace();
		}
		return userId;
		
	}
}
