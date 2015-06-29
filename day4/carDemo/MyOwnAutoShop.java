package carDemo;

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
