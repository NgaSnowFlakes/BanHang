package Entity;

public class Product {
	private int id;
	private String name;
	private String image;
	private Double price;
	private String title;
	private String desc;
	private int cateID;
	private int sell_ID;
	public Product() {
		
	}
	public Product(int id, String name, String image, Double price, String title, String desc) {
		
		this.id = id;
		this.name = name;
		this.image = image;
		this.price = price;
		this.title = title;
		this.desc = desc;
	}
	
	
	public Product(int id, String name, String image, Double price, String title, String desc, int cateID,
			int sell_ID) {
		this.name = name;
		this.image = image;
		this.price = price;
		this.title = title;
		this.desc = desc;
		this.cateID = cateID;
		this.sell_ID = sell_ID;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
	public int getCateID() {
		return cateID;
	}
	public void setCateID(int cateID) {
		this.cateID = cateID;
	}
	public int getSell_ID() {
		return sell_ID;
	}
	public void setSell_ID(int sell_ID) {
		this.sell_ID = sell_ID;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", image=" + image + ", price=" + price + ", title=" + title
				+ ", desc=" + desc + "]";
	}
	
	
}
