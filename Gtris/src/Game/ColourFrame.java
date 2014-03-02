package Game;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ColourFrame extends Canvas{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//variables
		private int x= 0, y=0, arrayx=0,arrayy=0;
		private int width = 68;
		private int height = 68;
		private String colour;
		Gtris s;
		public ColourFrame(Gtris s,String colour, int arrayX, int arrayY, int x, int y){
			this.s=s;
			this.arrayx= arrayX;
			this.arrayy=arrayY;
			this.x = x;
			this.y = y;
			this.colour =colour;
		}
		// this methods drawing every  block and are called from Gtris class
		public void update(Graphics g){
			//Initialize buffer
			Image img = createImage(this.getWidth(),this.getHeight());
			Graphics dbg= img.getGraphics();
			//Clear screen in background
			dbg.setColor(null);
			dbg.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
			//draw elements in background
			paint(dbg);
			//
			g.drawImage(img,this.x,this.y,this.width,this.height,this.s);  
		}
		// paint method
		public void paint(Graphics g){
			try{
				Image img= ImageIO.read(this.getClass().getResource(this.colour));
				
				g.drawImage(img,this.x,this.y,this.width,this.height,this.s);
				}catch(IOException e){
				
				}
		}
		// this methods are properties of variables to get and set information. 
		public void setColour(String c){
			colour=c;
		}
		
		public String getColour(){
			return colour;
		}
		
		public void setX(int x){
			this.x=x;
		}
		
		public int getX(){
			return this.x;
		}
		
		public void setY(int y){
			this.y=y;
		}
		
		public int getY(){
			return this.y;
		}
		
		public void setWith(int with){
			this.width=with;
		}
		
		public int getWidth(){
			return this.width;
		}
		
		public void setHeight(int height){
			this.height=height;
		}
		
		public int getHeight(){
			return this.height;
		}
		
		public void setArrax(int arrayx){
			this.arrayx=arrayx;
		}
		
		public int getArrax(){
			return this.arrayx;
		}
		
		public void setArray(int arrayy){
			this.arrayy=arrayy;
		}
		
		public int getArray(){
			return this.arrayy;
		}
		
		public void move_Down(){
			y= y+68;
		}
		
		public void move_Up(){
			y= y-68;
		} 
		
		public void move_Right(){
			x= x+68;
		} 
		
		public void move_Left(){
			x= x-68;
		} 
}
