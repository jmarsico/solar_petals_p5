import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import controlP5.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class solar_petals extends PApplet {




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


public void setup()
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

public void draw()
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

	public void setWaitTime(int _waiter)
	{
		waitVar = _waiter;
		waitCounts = waitCoeff + waitVar;
	}

	public void update()
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

	public void display()
	{
		ellipse(locX, locY, w, h);

	}
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "solar_petals" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
