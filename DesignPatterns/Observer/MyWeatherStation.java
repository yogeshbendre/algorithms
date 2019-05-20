import java.io.*;
import java.util.*;
import java.lang.*;
import WeatherStation;
import Observer;

public class MyWeatherStation implements WeatherStation{
	
	ArraList<Observer> obs;
	public MyWeatherStation(){
			temp = 25;
			windSpeed = 100;
			pressure = 1;
			obs = new ArrayList<Observer>();
		
	}
	public void registerObs(Observer ob){
		obs.add(ob);
	}
	public void removeObs(Observer ob){
		obs.remove(ob);
	}
	public void changeWeather(){
		temp++;
		pressure--;
		windSpeed = 200;
		notifyObs();
	}
	public void notifyObs(){
		for(Observer ob : obs){
			ob.update();
		}
	}
}


