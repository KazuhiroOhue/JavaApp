package model.entity;

import java.io.Serializable;
import java.util.Date;

public class ReadBooksBean implements Serializable {
	/**
	 * 本のID
	 */
	private int bookId;
	/**
	 * 本の名前
	 */
	private String bookName;
	/**
	 * ユーザID
	 */
	private int userId;
	/**
	 * 本の画像パス
	 */
	private String imagePath;
	/**
	 * 本の購入日
	 */
	private Date purchaseDate;
	/**
	 * 本のカテゴリー
	 */
	private String bookCategory;
	/**
	 * 本の合計ページ
	 */
	private int totalPages;
	/**
	 * 本のメモ
	 */
	private String bookmemos;
	/**
	 * 読書記録ID
	 */
	private int readingId;
	/**
	 * 読書した日
	 */
	private Date readingDate;
	/**
	 * 読んだページ数
	 */
	private int readPages;
	/**
	 * どれだけ本を読んだか
	 */
	private double percentage;
	/**
	 * 読書のメモ
	 */
	private String readingMemos;

	public ReadBooksBean() {
		
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public String getBookCategory() {
		return bookCategory;
	}

	public void setBookCategory(String bookCategary) {
		this.bookCategory = bookCategary;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public String getBookmemos() {
		return bookmemos;
	}

	public void setBookmemos(String bookmemos) {
		this.bookmemos = bookmemos;
	}

	public Date getReadingDate() {
		return readingDate;
	}

	public void setReadingDate(Date readingDate) {
		this.readingDate = readingDate;
	}

	public int getReadPages() {
		return readPages;
	}

	public void setReadPages(int readPages) {
		this.readPages = readPages;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	public String getReadingMemos() {
		return readingMemos;
	}

	public void setReadingMemos(String readingMemos) {
		this.readingMemos = readingMemos;
	}
	
	public int getReadingId() {
		return readingId;
	}

	public void setReadingId(int readingId) {
		this.readingId = readingId;
	}
}
