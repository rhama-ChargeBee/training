package carDemo;

class Ford extends Car{
	private int year;
	private int manufacturerDiscount;

	public Ford(int year,int manufacturerDiscount, int speed, double regularPrice, String colour){
		super(speed, regularPrice,colour);
		this.year= year;
		this.manufacturerDiscount= manufacturerDiscount;
	}

	public double getSalePrice(){
		return (getRegularPrice()- manufacturerDiscount);
	}
}
