package carDemo;

abstract class Car{
	private int speed;
	private double regularPrice;
	private String colour;

	Car(int speed, double regularPrice, String colour){
		this.speed=speed;
		this.regularPrice=regularPrice;
		this.colour=colour;
	}

	abstract double getSalePrice();

	double getRegularPrice(){
		return regularPrice;
	}
}

class Truck extends Car{
	private int weight;

	Truck(int weight, int speed, double regularPrice, String colour){
		super(speed, regularPrice,colour);
		this.weight= weight;
	}

	double getSalePrice(){
		if(weight>2000){
			return (getRegularPrice() - (getRegularPrice()*0.1));
		}
		else{
			return (getRegularPrice() - (getRegularPrice()*0.2));
		}
	}
}

class Ford extends Car{
	private int year;
	private int manufacturerDiscount;

	Ford(int year,int manufacturerDiscount, int speed, double regularPrice, String colour){
		super(speed, regularPrice,colour);
		this.year= year;
		this.manufacturerDiscount= manufacturerDiscount;
	}

	double getSalePrice(){
		return (getRegularPrice()- manufacturerDiscount);
	}
}

class Sedan extends Car{
	private int length;

	Sedan(int len, int speed, double regularPrice, String colour){
		super(speed, regularPrice,colour);
		length= len;
	}

	double getSalePrice(){
		if(length>20){
			return (getRegularPrice() - (getRegularPrice()*0.05));
		}
		else{
			return (getRegularPrice() - (getRegularPrice()*0.1));
		}
	}
}

public class MyOwnAutoShop{
	public static void main(String[] args){
		Sedan sedan=new Sedan(15, 200, 20_00_000, "Silver");
		Ford ford1= new Ford(2000, 10_000, 160, 15_00_000, "Green");
		Ford ford2= new Ford(2010, 1_00_000, 180, 25_00_000, "Red");
		Truck truck=  new Truck(2020,120, 14_00_000, "White");

		System.out.println("Sedan....\nPrice:"+ sedan.getSalePrice());
		System.out.println("Ford....\nPrice1:"+ ford1.getSalePrice()+"\nPrice2:"+ ford2.getSalePrice());
		System.out.println("Truck....\nPrice:"+ truck.getSalePrice());

	}
	
}
