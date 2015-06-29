package carDemo;

class Truck extends Car{
	private int weight;

	public Truck(int weight, int speed, double regularPrice, String colour){
		super(speed, regularPrice,colour);
		this.weight= weight;
	}

	public double getSalePrice(){
		if(weight>2000){
			return (getRegularPrice() - (getRegularPrice()*0.1));
		}
		else{
			return (getRegularPrice() - (getRegularPrice()*0.2));
		}
	}
}
