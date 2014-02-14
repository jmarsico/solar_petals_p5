
import controlP5.*;

int boxW = 1000;
int boxH = 500;
int xSpacing = 60;
int ySpacing = 30;
int rate = 0;

int col = boxW / xSpacing;
int row = boxH / ySpacing;
int numPetals = col * row;

Petal[] petals = new Petal[numPetals];

ControlP5 cp5;                //initiate instance of ControlP5 library


void setup()
{
	size(displayWidth, displayHeight);
	for(int i = 0; i < numPetals; i++)
	{
		int waiter = (int)random(0, 100);
		petals[i] = new Petal(waiter);
	}

	cp5 = new ControlP5(this);

	//slider control for gravity
	cp5.addSlider("rate")
	    .setPosition(boxW, 30)
	      .setRange(0, 200)
	        .setSize(20, 400)
	          .setColorCaptionLabel(255)
	            .setCaptionLabel("rate")
	              ;
}

void draw()
{
	background(0);
	
	cp5.show();

	
	fill(255);
	
		for(int i = 1; i < col; i++)
		{
			for(int j = 1; j < row; j ++)
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
		petals[i].setWaitTime(200 - rate);
	}


	
	
}