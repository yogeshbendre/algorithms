import java.io.*;
import java.util.*;
import java.lang.*;
import WeatherStation;
import Observer;

public class MyObserver{
	
	public MyObserver(WeatherStation ws){
		this.ws = ws;
		ws.registerObs(this);
	}
	public void update(){
		System.out.println("Got weather update");
	}
}


