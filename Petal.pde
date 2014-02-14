class Petal {
	float locX;
	float locY;
	float h;
	float w;
	float waitCounts;
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
		waitCounts = _waitTime;

	}

	void update()
	{
		//increment counter
		
		println(counter);

		//close the petal
		if(counter > 0 && counter < closeCounts)
		{
			w = w - 1;
		}

		if(counter > closeCounts && counter < closeCounts + openCounts)
		{
			w = w + 1;
		}

		if(counter >= (closeCounts + openCounts + waitCounts))
		{
			counter = 0;
		}

		counter++;

		
	}

	void display()
	{
		ellipse(locX, locY, w, h);

	}
}