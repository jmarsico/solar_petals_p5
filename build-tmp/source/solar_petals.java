import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

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

int col = boxW / xSpacing;
int row = boxH / ySpacing;
int numPetals = col * row;

Petal[] petals = new Petal[numPetals];


public void setup()
{
	size(displayWidth, displayHeight);
	for(int i = 0; i < numPetals; i++)
	{
		int waiter = (int)random(0, 100);
		petals[i] = new Petal(waiter);
	}
}

public void draw()
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

	public void update()
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
