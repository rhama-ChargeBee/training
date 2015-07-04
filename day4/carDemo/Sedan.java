package carDemo;

class Sedan extends Car{
	private int length;

	public Sedan(int len, int speed, double regularPrice, String colour){
		super(speed, regularPrice,colour);
		length= len;
	}

	public double getSalePrice(){
		return getRegularPrice()*(length>20? 0.95:0.9);
	}
	
}
