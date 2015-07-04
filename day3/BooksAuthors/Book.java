package BooksAuthors;

import java.util.*;

public class Book{
	private String name;
	private Author[] authors;
	private Double price;
	private int qtyInStock=0;

	public Book(String name, Author[] authors, double price, int qtyInStock){
		this.name=name;
		this.authors=authors;
		this.price= price;
		this.qtyInStock= qtyInStock;

	}
	public Book(String name, Author author, double price, int qtyInStock){
		Author[] temp=new Author[1];
		temp[0]=author; 
		this.name=name;
		this.authors=temp;
		this.price=price;
		this.qtyInStock=qtyInStock;
	}

	public String getName(){
		return name;
	}
	public Author[] getAuthors(){
		return authors;
	}
	public double getPrice(){
		return price;
	}
	public int getQtyInStock(){
		return qtyInStock;
	}

	public void setPrice(double price){
		this.price=price;
	}
	public void setQtyInStock(int qtyInStock){
		this.qtyInStock=qtyInStock;
	}
	public String toDisplay(){
		StringBuilder str= new StringBuilder(name+"by");

		for(int i=0; i<authors.length; i++){
			str.append( authors[i].getName());
			str.append(" (").append(authors[i].getGender()).append(") at ").append(authors[i].getEmail());

			if(i==(authors.length-1)){
				str.append('. ');
			}
			else if(i == (authors.length-2)){
				str.append(" and ");
			}
			else{
				str.append(', ');
			}
		}
		return str.toString();
	}
	public void printAuthors(){
		for(int i=0; i<authors.length; i++){
			System.out.println(authors[i].getName());
		}
	}

	public void addAuthor(Author author){
		Author[] temp= authors;
		temp= Arrays.copyOf(temp, temp.length + 1);
    	temp[temp.length - 1] = author;
    	authors=temp;

	}
	public static void main(String[] args) throws Exception{
		Scanner scan= new Scanner(System.in);
		String printStr;

		System.out.println("Enter the info, Book name, qty, price");
		String bookName= scan.nextLine();
		int qty= scan.nextInt();
		double price= scan.nextDouble();
		
		Author auth1= new Author("xxx", "xxx@abc.com", 'M');
		Author auth2= new Author("yyy", "yyy@abc.com", 'F');
		Author auth3= new Author("zzz", "zzz@abc.com", 'M');
		//Author[] authorsBook= [auth1, auth2];
		System.out.println("Creating a new book.... \n");
		Book newBook= new Book(bookName, auth1, price, qty);
		printStr= newBook.toDisplay();
		System.out.println(printStr);
		
		System.out.println("\nPrinting Authors.... ");
		newBook.printAuthors();
		
		System.out.println("\nAdding author yyy....");
		newBook.addAuthor(auth2);
		printStr= newBook.toDisplay();
		System.out.println(printStr);
		

		System.out.println("\nAdding author zzz....");
		newBook.addAuthor(auth3);
		printStr= newBook.toDisplay();
		System.out.println(printStr);

		System.out.println("\nPrinting authors: ");
		newBook.printAuthors();
	}
}