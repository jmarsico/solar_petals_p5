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




int boxW = 1000;		//width of bounding box
int boxH = 500;			//height of bounding box
int xSpacing = 60;		//horizontal spacing
int ySpacing = 30;		//vertical spacing
int waitTime = 0;			//time/rate variable 
int maxWait = 2000; 		//set a max for time/rate variable

int col = boxW / xSpacing;		//number of columns
int row = boxH / ySpacing;		//number of rows
int numPetals = col * row;		//total number of petals
int numPetalsOn = 0;			//keep track of how many petals are being powered
boolean changeColor = false;

//make room for petals
Petal[] petals = new Petal[numPetals];

ControlP5 cp5;                //make room for instance of ControlP5 library



//////////////////// SETUP /////////////////////////////////
public void setup()
{
	size(displayWidth, displayHeight);
	
	//initialize new petals with a random wait time
	for(int i = 0; i < numPetals; i++)
	{
		int initialWaitTime = (int)random(0, 200);
		petals[i] = new Petal(initialWaitTime);
	}

	//initialize GUI
	cp5 = new ControlP5(this);

	//slider control for waitTime
	cp5.addSlider("waitTime")
	    .setPosition(boxW, 30)
	      .setRange(0, maxWait)
	        .setSize(20, 400)
	          //.setColorCaptionLabel(255)
	            .setCaptionLabel("rate")
	              ;
    // create a toggle
	cp5.addToggle("changeColor")
     	.setPosition(boxW + 45 ,410)
     	  .setSize(20,20)
     	    .setCaptionLabel("show ONs")
     ;
}


//////////////////// DRAW /////////////////////////////////
public void draw()
{
	background(80);
	
	
	cp5.show();

	//draw the petals
	noFill();
	stroke(150, 150, 150);
	rect(0,0,boxW-20, boxH);
	noStroke();
	
		for(int i = 1; i < col; i++)
		{
			for(int j = 1; j < row; j ++)
			{
				pushMatrix();
					translate(i*xSpacing, j*ySpacing);
					petals[i*j].display(changeColor);
				popMatrix();

			}
		}
	
	//do other things with the petals
	for(int i= 0; i < numPetals; i++)
	{
		//update petals
		petals[i].update();
		//change the wait time between closes
		petals[i].setWaitTime(maxWait - waitTime);


		//get a count of how many petals are currently "on"
		if(petals[i].state == true && petals[i].prevState == false)
		{
			numPetalsOn++;
		}
		if(petals[i].state == false && petals[i].prevState == true)
		{
			numPetalsOn--;
		}

	}

	//write out the number of petals that are on
	fill(255, 255, 255);
	text("number of petals on: " + numPetalsOn, boxW , boxH); 
	text("current draw @ 4.5v: " + (int)(numPetalsOn * 0.65f), boxW, boxH + 13);


	
	
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
	public void setWaitTime(int _waiter)
	{
		waitVar = _waiter;
		//waitCounts = waitCoeff + waitVar;
	}

	public void update()
	{
		
		//prepare for any state changes
		prevState = state;

		//close the petal
		if(counter > 0 && counter < closeCounts)
		{
			state = true;
			w = w - 0.5f;
		}

		//open the petal
		if(counter > closeCounts && counter < closeCounts + openCounts)
		{
			state = false;	
			w = w + 0.5f;
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

	public void display(boolean changeColor)
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
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "solar_petals" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
