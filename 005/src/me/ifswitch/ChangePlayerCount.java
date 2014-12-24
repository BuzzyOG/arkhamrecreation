package me.ifswitch;

import java.util.Random;

public class ChangePlayerCount implements Runnable {

	public static int actual_count = 100;
	
	public int maxInt = 480;

	@Override
	public void run() {
		Random rand = new Random();
		int random_int = rand.nextInt(9 - 0 + 1) + 0;

		System.out.println("PlayerCount: " + actual_count);

			switch (random_int) {
			case 0:
				if(actual_count < maxInt)
				actual_count = actual_count + 2;
				else
					actual_count = actual_count - 2;
				break;
			case 1:
				if(actual_count < maxInt)
				actual_count = actual_count + 7;
				else
					actual_count = actual_count - 7;
				break;
			case 2:
				if(actual_count < maxInt) 
					actual_count = actual_count - 3;
					else
						actual_count = actual_count - 3;
				break;
			case 3:
				if(actual_count < maxInt) 
					actual_count = actual_count - 10;
				else
					actual_count = actual_count + 10;
				break;
			case 4:
				if(actual_count < maxInt)
				actual_count = actual_count + 21;
				else
					actual_count = actual_count - 7;
				break;
			case 5:
				if(actual_count < maxInt)
					actual_count = actual_count - 18;
				else
					actual_count = actual_count + 6;
				break;
			case 6:
				if(actual_count < maxInt)
				actual_count = actual_count + 12;
				else
					actual_count = actual_count - 11;
				break;
			case 7:
				if(actual_count < maxInt)
				actual_count = actual_count + 5;
				else
					actual_count = actual_count - 5;
				break;
			case 8:
				if(actual_count < maxInt) 
					actual_count = actual_count - 12;
				else
					actual_count = actual_count + 7;
				break;
			case 9:
				if(actual_count < maxInt)
				actual_count = actual_count + 12;
				else
					actual_count = actual_count - 7;
				break;
			}
		}


}
