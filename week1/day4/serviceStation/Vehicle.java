package serviceStation;

import java.util.*;
import java.io.*;

class Vehicle{
	private String brand;
	private String colour;
	private ServiceType service;//make this enum... check it later...

	public void setValues(String brand, String colour, ServiceType service){
		this.brand=brand;
		this.colour= colour;
		this.service= service;
	}

	public ServiceType getType(){
		return service;
	}

	public float getCharge(){
		return service.serviceCharge();
	}
}