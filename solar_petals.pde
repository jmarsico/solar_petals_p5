

int boxW = 1000;
int boxH = 500;
int xSpacing = 60;
int ySpacing = 30;

int col = boxW / xSpacing;
int row = boxH / ySpacing;
int numPetals = col * row;

Petal[] petals = new Petal[numPetals];


void setup()
{
	size(displayWidth, displayHeight);
	for(int i = 0; i < numPetals; i++)
	{
		int waiter = (int)random(0, 100);
		petals[i] = new Petal(waiter);
	}
}

void draw()
{
	background(0);
	
	

	println(petals[0].counter);
	
	fill(255);
	
		for(int i = 0; i < col; i++)
		{
			for(int j = 0; j < row; j ++)
			{
				pushMatrix();
				translate(i*xSpacing, j*ySpacing);
				petals[i*j].display();
				popMatrix();

			}
		}
	for(int i= 0; i < numPetals; i++)
	{
		petals[i].update();
	}

	
	
}