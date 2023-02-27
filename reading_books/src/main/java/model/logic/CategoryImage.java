package model.logic;

public class CategoryImage {

	public String CategoryImage(String category) {
		
		String path;
		
		switch(category) {
		case "学習参考書":
			path = "学習参考書.png";
			break;
		case "専門書":
			path = "専門書.png";
			break;
		case "ビジネス":
			path = "ビジネス.png";
			break;
		case "文学・文芸":
			path = "文芸.png";
			break;
		case "趣味":
			path = "趣味.png";
			break;
		case "マンガ":
			path = "マンガ.png";
		break;
		default:
			path = "その他.png";
		}
		
		return path;
		
	}
}
