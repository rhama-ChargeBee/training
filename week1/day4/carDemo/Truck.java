package carDemo;

class Truck extends Car{
	private int weight;

	public Truck(int weight, int speed, double regularPrice, String colour){
		super(speed, regularPrice,colour);
		this.weight= weight;
	}

	public double getSalePrice(){
		return  getRegularPrice() * ( (weight>2000)? 0.9 : 0.8 ) ;
		
	}
}
