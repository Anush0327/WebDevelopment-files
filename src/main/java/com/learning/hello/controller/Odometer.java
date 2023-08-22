package com.learning.hello.controller;

public class Odometer{
	public int reading;
	public Odometer(int length) {
		int sum=1;
		for(int i=2;i<=length;i++) {
			sum=sum*10;
			sum+=i;
		}
		reading=sum;
	}
	public Odometer(int reading,int length) {
		this.reading = reading;
	}
	public Odometer(Odometer o) {
		reading=o.reading;
	}
	public int highestReading() {
		int length = this.size();
		int read=0;
		for(int i=9-length+1;i<=9;i++)
		{
			read=read*10;
			read+=i;
		}
		return read;
	}
	public int lowestReading() {
		int length = this.size();
		int read=0;
		for(int i=1;i<=length;i++)
		{
			read=read*10;
			read+=i;
		}
		return read;
		//--module-path /path/to/javafx-sdk-20/lib --add-modules javafx.controls,javafx.fxml
	}
	public int size() {
		return  String.valueOf(reading).length(); 
	}
	public boolean isAscending() {
		int temp = reading;
		int prev=10;
		int curr=0;
		while(temp>0) {
			curr=temp%10;
			if(curr>=prev)
				return false;
			else {
				prev = curr;
				temp/=10;
			}
		}
		return true;
	}
	public void incrementReading() {
		do {
			if(reading>=this.highestReading()) {
				reading = this.lowestReading();
				break;
			}
			reading++;
		}while(!this.isAscending()); 
	}
	public Odometer nextReading() {
		Odometer newReading = new Odometer(this);
		do {
			if(newReading.reading>=this.highestReading()) {
				newReading.reading = this.lowestReading();
				return newReading;
			}
			newReading.reading++;
		}while(!newReading.isAscending()); 
		return newReading;
	}
	public void decrementReading() {
		do {
			if(reading<=this.lowestReading()) {
				reading = this.highestReading();
				break;
			}
			reading--;
		}while(!this.isAscending()); 
	}
	public Odometer prevReading() {
		Odometer newReading = new Odometer(this);
		do {
			if(newReading.reading<=this.lowestReading()) {
				newReading.reading = this.highestReading();
				return newReading;
			}
			newReading.reading--;
		}while(!newReading.isAscending()); 
		return newReading;
	}
	public void reset() {
		reading = lowestReading();
	}
	@Override
	public String toString() {
		return String.valueOf(reading);
	}
	public Integer getReading() {
		// TODO Auto-generated method stub
		return reading;
	}
	public void setReading(int i) {
		// TODO Auto-generated method stub
		reading = i;
	}
}