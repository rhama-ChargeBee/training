package carDemo;

class Sedan extends Car{
	private int length;

	public Sedan(int len, int speed, double regularPrice, String colour){
		super(speed, regularPrice,colour);
		length= len;
	}

	public double getSalePrice(){
		if(length>20){
			return (getRegularPrice() - (getRegularPrice()*0.05));
		}
		else{
			return (getRegularPrice() - (getRegularPrice()*0.1));
		}
	}
}
