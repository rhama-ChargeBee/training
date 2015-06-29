package serviceStation;

import java.util.*;
import java.io.*;

public enum ServiceType {
    CAR(20_000f),
    BIKE(10_000f),
    BUS(30_000f),
    TRUCK(40_000f);
    private float serviceCharge;
    public ServiceType(float charge){
    	serviceCharge=charge;
    }
    
    public float serviceCharge(){
    	return serviceCharge;
    }
}