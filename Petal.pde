class Petal {
	float locX;
	float locY;
	float h;
	float w;
	
	int waitCounts;
	int waitVar;
	int waitCoeff;

	int counter;
	int closeCounts;
	int openCounts;
	boolean state;
	boolean prevState;

	Petal(int _waitTime)
	{
		locX = 0;
		locY = 0;
		h = 20;
		w = 60;
		counter = 0;
		closeCounts = 70;
		openCounts = 90;
		//waitCoeff = _waitTime;
		waitCounts =_waitTime;
		state = false;


	}

	//function for changing wait time
	void setWaitTime(int _waiter)
	{
		waitVar = _waiter;
		//waitCounts = waitCoeff + waitVar;
	}

	void update()
	{
		
		//prepare for any state changes
		prevState = state;

		//close the petal
		if(counter > 0 && counter < closeCounts)
		{
			state = true;
			w = w - 0.5;
		}

		//open the petal
		if(counter > closeCounts && counter < closeCounts + openCounts)
		{
			state = false;	
			w = w + 0.5;
		}

		//wait and then reset
		if(counter >= (closeCounts + openCounts + waitCounts))
		{
			state = false;
			counter = 0;
			waitCounts = (int)random(0,waitVar);
		}

		//increment counter
		counter++;

		//constrain width
		w = constrain(w, 20, 60);


		//logging for turnOn and turn Off
		if(prevState == false && state == true)
		{
			println("turnedOn");
		}
		if(prevState == true && state == false)
		{
			println("turnedOFF");
		}

	}

	void display(boolean changeColor)
	{
		
		if(state && changeColor)
		{
			fill(170);
		}else{
			fill(200);
		}
		ellipse(locX, locY, w, h);

	}
}