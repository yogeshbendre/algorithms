import java.io.*;
import java.util.*;
import java.lang.*;
import Observer;

public interface  WeatherStation{
	
	protected int temp;
	protected int windSpeed;
	protected int pressure;
	
	public void registerObs(Observer obs);
	public void removeObs(Observer obs);
	public void changeWeather();
	public void notifyObs();
}


