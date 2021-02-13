package TestChronometer;

import com.ipserc.chronometer.Chronometer;

public class test_chrono {

	public static void main(String[] args) {
		Chronometer chrono1 = new Chronometer();
		long mstime;
		
		chrono1.start();
		Chronometer.delay(2000);
		chrono1.stop();
		System.out.println("Chrono 1: "+ chrono1.toString());

		chrono1.start();
		Chronometer.delay(2000);
		mstime = chrono1.lap(); //ON
		System.out.println("lap 1 " + chrono1.lapStatus() + " :" + mstime);
		Chronometer.delay(3000);
		mstime = chrono1.lap(); //OFF
		System.out.println("lap 1 " + chrono1.lapStatus() + ":" + mstime);
		Chronometer.delay(2000);
		mstime = chrono1.lap(); //ON
		System.out.println("lap 2 " + chrono1.lapStatus() + " :" + mstime);
		Chronometer.delay(1000);
		mstime = chrono1.lap(); //OFF
		System.out.println("lap 2 " + chrono1.lapStatus() + ":" + mstime);
		Chronometer.delay(2000);
		chrono1.stop();
		System.out.println("Chrono 1: "+ chrono1.toString());
	}
}
