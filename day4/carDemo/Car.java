package carDemo;

abstract class Car{
	private int speed;
	private double regularPrice;
	private String colour;

	public Car(int speed, double regularPrice, String colour){
		this.speed=speed;
		this.regularPrice=regularPrice;
		this.colour=colour;
	}

	public abstract double getSalePrice();

	protected double getRegularPrice(){
		return regularPrice;
	}
}
