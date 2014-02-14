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

	Petal(int _waitTime)
	{
		locX = 0;
		locY = 0;
		h = 20;
		w = 60;
		counter = 0;
		closeCounts = 50;
		openCounts = 50;
		waitCoeff = _waitTime;

	}

	void setWaitTime(int _waiter)
	{
		waitVar = _waiter;
		waitCounts = waitCoeff + waitVar;
	}

	void update()
	{
		
		

		//close the petal
		if(counter > 0 && counter < closeCounts)
		{
			w = w - 1;
		}
		//open the petal
		if(counter > closeCounts && counter < closeCounts + openCounts)
		{
			w = w + 1;
		}
		//wait and then reset
		if(counter >= (closeCounts + openCounts + waitCounts))
		{
			counter = 0;
		}

		//increment counter
		counter++;

		
	}

	void display()
	{
		ellipse(locX, locY, w, h);

	}
}