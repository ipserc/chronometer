package com.ipserc.chronometer;

public class Chronometer {
	private final static String HEADINFO = "Chronometer --- INFO: ";
	private final static String VERSION = "1.0 (2021_0213_2100)";

	/*
	 * MEMEBER VARIABLES
	 */
	
	private long startTime = 0l;
	private long duration = 0l;
	private int ms = 0, s = 0, m = 0, h = 0; 
	private long d = 0l;
	private double dec = 0.0;
	private double time = 0.0;
	private String strDuration;
	private long lapTime = 0l;
	private long lapStart = 0l;
	private long lapStop = 0l;
	private Boolean lapON = false;
	
	/*
	 * ***********************************************
	 * 	VERSION 
	 * ***********************************************
	 */
	
	/**
	 * Prints Class Version
	 */
	public static void version() {
		System.out.println(HEADINFO + "VERSION:" + VERSION); 
	}

	/*
	 * ***********************************************
	 * CONSTRUCTOR
	 * ***********************************************
	 */

	/**
	 * Class constructor. It starts the chronometer by default. You can override this start by calling the start method before.
	 */
	public Chronometer() {
		startTime = System.currentTimeMillis();
	}

	/*
	 * PRIVATE METHODS
	 */
	
	/**
	 * Updates the calculated values used to to extract the time values
	 * @param timeUnit The value to extract the units of time
	 * @return The next time value to use in the calculations
	 */
	private int getVal(int timeUnit) {
		time = (time / (double)timeUnit);
		dec = time % 1;
		time = time - dec;
		return (int)(dec * timeUnit);		
	}
	
	/**
	 * Evaluates the introduced duration, in the given time units
	 */
	private void eval() {
		ms = this.getVal(1000);
		s = this.getVal(60);
		m = this.getVal(60);
		h = this.getVal(24);
		d = (long)time;
		strDuration = String.format("%d(d) %02d:%02d:%02d.%03d", d, h, m, s, ms);		
	}
	
	/*
	 * ***********************************************
	 * SETTERS & GETTERS
	 * ***********************************************
	 */
	
	/**
	 * Sets a new duration and do the calculations
	 * @param duration The duration in milliseconds
	 */
	public void set(long duration) {
		this.duration = duration;
		time = (double)duration;
		this.eval();
	}
	
	/**
	 * Gets the duration in milliseconds
	 * @return The duration
	 */
	public long get() {
		return this.duration;
	}
	
	/**
	 * Gets the value of the milliseconds
	 * @return The value of the milliseconds
	 */
	public int millisecs() {
		return this.ms;
	}
	
	/**
	 * Gets the value of the seconds
	 * @return The value of the seconds
	 */
	public int secs() {
		return this.s;
	}
	
	/**
	 * Gets the value of the minutes
	 * @return The value of the minutes
	 */
	public int mins() {
		return this.m;
	}
	
	/**
	 * Gets the value of the hours
	 * @return The value of the hours
	 */
	public int hours() {
		return this.h;
	}

	/**
	 * Gets the value of the days
	 * @return The value of the days
	 */
	public long days() {
		return this.d;
	}

	/**
	 * Gets the value of the member variable lapON
	 * @return True if lap is ON, false if lap is OFF
	 */
	public boolean lapON() {
		return lapON;
	}
	
	/*
	 * ***********************************************
	 * METHODS
	 * ***********************************************
	 */

	/**
	 * Resets all the member variables to zero.
	 */
	private void reset() {
		startTime = 0l;
		duration = 0l;
		ms = 0; s = 0; m = 0; h = 0; 
		d = 0l;
		dec = 0.0;
		time = 0.0;
		lapTime = 0l;
		lapStart = 0l;
		lapStop = 0l;
		lapON = false;
	}
	
	/**
	 * Does a delay of n milliseconds
	 * @param The milliseconds to stop the activity
	 */
	public static void delay(int millisecs) {
	     try{
	         Thread.sleep(millisecs);
	      } 
	     catch(InterruptedException e) {}
	}
	
	/**
	 * Starts the chronometer. It Does a reset of all the values.
	 * @return The time at which the chronometer starts in milliseconds
	 */
	public long start() {
		reset();
		startTime = System.currentTimeMillis();
		return startTime;
	}
	
	/**
	 * Switches ON or OFF the lap function of the chronometer
	 * @return  For lap ON returns the moment in milliseconds at which the chronometer starts. For lap OFF returns the elapsed time in milliseconds
	 */
	public long lap() {
		if (lapON == false) {
			lapStart = System.currentTimeMillis();
			lapStop = 0;
			lapON = true;
			return lapStart; 
		}
		else {
			lapStop = System.currentTimeMillis();
			lapON = false;
			lapTime += lapStop - lapStart;
		}
		return lapStop - lapStart;
	}
	
	/**
	 * Stops the chronometer and sets the duration 
	 * @return The duration, the time recorded by the chronometer
	 */
	public long stop() {
		if (lapON) lap();
		duration = System.currentTimeMillis() - startTime - lapTime;
		set(duration);
		return duration;
	}
	
	/*
	 * ***********************************************
	 * STRING OUTPUT
	 * ***********************************************
	 */
	
	/**
	 * Returns a String representation of the duration expressed in days(d) hours:minutes:seconds.milliseconds
	 * @return A String representation of the duration expressed in days(d) hours:minutes:seconds.milliseconds
	 */
	public String toString() {
		return strDuration;
	}
	
	/**
	 * Returns the status of the lap function as a string 
	 * @return "ON" or "OFF"
	 */
	public String lapStatus() {
		return lapON ? "ON" : "OFF";
	}
}
