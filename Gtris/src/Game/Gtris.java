package Game;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.Timer;
import javax.swing.JFrame;

import java.util.Random;

public class Gtris extends Canvas implements KeyListener {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//variables
	private int [][] field = new int[6][10]; // field array control the game logic
	private ColourFrame [][] f = new ColourFrame [6][10]; //ColourFrame array control color blocks of board
	private boolean switch_frame = false;
	private int score=0,time=0,frame_count=0;
	ColourFrame cursor;
	Timer t;

	//Initialize arrays
	// define default color squares to start the game
	    public void init_board(Gtris g){
	        // initialize timer to create new color blocks
	        t = new Timer(1000,new ActionListener (){
	            Gtris g = new Gtris();
	            public void actionPerformed(ActionEvent e) 
	                {
	            		new_Block(g); // create 2 new blocks every second
	            		new_Block(g);//
	            		time++;// and count every second
	            		if(time>120){
	            			new_Block(g); //plus 2 blocks after 120 sceonds
	            			new_Block(g);
	            			}
	            		if(time>200){
	            			new_Block(g); //plus 1 block after 200 seconds
	            		}
	                 }
	            });
	        cursor = new ColourFrame(g,"cursor.png",0,9,0,612);
	        field[0][9]=5;
	        f [0][9]  = new ColourFrame(g,"red.png",0,9,0,612);
	        field[1][9]=3;
	        f [1][9]= new ColourFrame(g,"green.png",1,9,68,612);
	        field[2][9]=5;
	        f [2][9]= new ColourFrame(g,"red.png",2,9,136,612);
	        field[3][9]=1;
	        f [3][9]= new ColourFrame(g,"blue.png",3,9,204,612);
	        field[4][9]=2;
	        f [4][9] = new ColourFrame(g,"pink.png",4,9,272,612);
	        field[5][9]=2;
	        f [5][9] = new ColourFrame(g,"pink.png",5,9,340,612);
	        field[1][8]=4;
	        f [1][8] = new ColourFrame(g,"yellow.png",1,8,68,544);
	        field[2][8]=3;
	        f [2][8] = new ColourFrame(g,"green.png",2,8,136,544);
	        field[3][8]=4;
	        f [3][8] = new ColourFrame(g,"yellow.png",3,8,204,544);
	        field[4][8]=1;
	        f [4][8] = new ColourFrame(g,"blue.png",4,8,272,544); 
	        field[5][8]=3;
	        f [5][8] = new ColourFrame(g,"green.png",5,8,340,544);
	        field[2][7]=2;
	        f [2][7] = new ColourFrame(g,"pink.png",2,7,136,476);
	        field[3][7]=5;
	        f [3][7] = new ColourFrame(g,"red.png",3,7,204,476);
	        field[3][6]=2;
	        f [3][6]= new ColourFrame(g,"pink.png",3,6,204,408);
	        frame_count=14;
	    }
	    
	//Control the fall down of color blocks
	        public void blockMove_down(){
	            for (int j =8; j>-1;j--){ // search begins in 9 row
	                for (int i=5;i>-1;i--){// and check every column
	                    if((field[i][j+1]==0)&&(field[i][j]!=0)){ // if next down space from every block is 0 in field array
	                    if((f[i][j]!=null)&&(f[i][j+1]==null)){ // and null in block array
	                        //fall dawn
	                        int temp=0;
	                               
	                        temp= field[i][j];
	                        field[i][j] = 0;
	                        field[i][j+1]=temp;
	                        
	                        f[i][j].move_Down();
	                        f[i][j+1]=f[i][j];
	                        f[i][j] = null;
	                        
	                    }
	                    }
	                }
	            }
	            
	        }
	    
	//Generate new elements on the array
	    public void new_Block(Gtris g){
	            Random r = new Random(); 
	            int init_index=r.nextInt(6-0);// Select a random top empty space to generate new block
	            if(field [init_index][0]==0){  // If random space is empty > generate new block
	                int r1=r.nextInt(7-1); // generate color code
	                    if((r1>0)&&(r1<6)){
	                        field[init_index][0]= r1; // add to field array
	                        f [init_index][0] = new ColourFrame(g,returnColour(r1),init_index,0,(init_index)*68,0); //add to block array
	                        frame_count++;
	                         
	                    }
	            }       
	    }
	//this method return color string to new the blocks
	    public String returnColour(int i){
	        switch (i){
	        case 1:
	            return "blue.png";
	        case 2:
	            return "pink.png";
	        case 3:
	            return "green.png";
	        case 4:
	            return "yellow.png";
	        case 5:
	            return "red.png";
	        default:
	            return " ";
	        }
	        
	    }
	    
	// this methods draw the color blocks 

	// paint method call paint in ColourFrame to draw every single block
	    public void paint(Graphics g){
	    super.paint(g); // clean Canvas 
	    if(check_ContinueGame()){
	     for (int i=0; i<6;i++){
	         for (int j=0; j<10;j++){
	                if(f[i][j]!=null){
	                    f[i][j].paint(g); // call the paint method from every single frame
	                }
	            }
	        }
	        cursor.paint(g); // call paint method from select block
	     	//
	        g.setColor(Color.GRAY);
	        g.setFont((new Font("Verdana", Font.BOLD, 30)));
	        g.drawString(String.valueOf("Score: "+score), 10, 30); // draw score in top left of board
	        g.drawString(String.valueOf("Time: "+time), 200, 30); // draw score in top right of board
	    }else{
	        g.setColor(Color.GRAY);
	        g.setFont((new Font("Verdana", Font.BOLD, 30)));
	        g.drawString(String.valueOf("Game Over"),  50, 200);
	        g.drawString(String.valueOf("Time: "+time+"  seconds"), 20, 250);
	        g.drawString(String.valueOf("Score: "+score), 20, 300);
	        g.drawString(String.valueOf("Final Score :"+score*time), 20, 350); 
	    }
	    }
	    
	// this method compare tetris forms in arrays and deletes
	    public void match(){
	        for(int z=1; z<6; z++){ //check every color
	            for (int j =9; j>-1;j--){ //  search begins in bottom right of board 
	                for (int i=5;i>-1;i--){// 
	                    if((field[i][j]!=0)&&(f[i][j]!=null)){ // and only match colors no empty spaces
	                        // match   ++
	                        // form     +
	                        //          +
	                        try{ // "try" blocks control "Index out exception" for board limits
	                        if(((field[i][j]==z)&&(field[i][j-1]==z))&&((field[i][j-2]==z)&&(field[i-1][j-2]==z))){
	                            field[i][j]=0;
	                            f[i][j]=null;
	                            field[i][j-1]=0;
	                            f[i][j-1]=null;
	                            field[i][j-2]=0;
	                            f[i][j-2]=null;
	                            field[i-1][j-2]=0;
	                            f[i-1][j-2]=null;
	                            frame_count=frame_count-4;
	                            score++;
	                        }
	                        }catch(ArrayIndexOutOfBoundsException e){}
	                        
	                        // match  ++
	                        // form   +
	                        //        +
	                        try{
	                        if(((field[i][j]==z)&&(field[i][j-1]==z))&&((field[i][j-2]==z)&&(field[i+1][j-2]==z))){
	                            field[i][j]=0;
	                            f[i][j]=null;
	                            field[i][j-1]=0;
	                            f[i][j-1]=null;
	                            field[i][j-2]=0;
	                            f[i][j-2]=null;
	                            field[i+1][j-2]=0;
	                            f[i+1][j-2]=null;
	                            frame_count=frame_count-4;
	                            score++;
	                        }
	                        }catch(ArrayIndexOutOfBoundsException e){}
	                        
	                        // match  +
	                        // form   +
	                        //        ++
	                        try{
	                        if(((field[i][j]==z)&&(field[i-1][j]==z))&&((field[i-1][j-1]==z)&&(field[i-1][j-2]==z))){
	                            field[i][j]=0;
	                            f[i][j]=null;
	                            field[i-1][j]=0;
	                            f[i-1][j]=null;
	                            field[i-1][j-1]=0;
	                            f[i-1][j-1]=null;
	                            field[i-1][j-2]=0;
	                            f[i-1][j-2]=null;
	                            frame_count=frame_count-4;
	                            score++;
	                        }
	                        }catch(ArrayIndexOutOfBoundsException e){}
	                        
	                        // match   +
	                        // form    +
	                        //        ++
	                        try{
	                        if(((field[i][j]==z)&&(field[i-1][j]==z))&&((field[i][j-1]==z)&&(field[i][j-2]==z))){
	                            field[i][j]=0;
	                            f[i][j]=null;
	                            field[i-1][j]=0;
	                            f[i-1][j]=null;
	                            field[i][j-1]=0;
	                            f[i][j-1]=null;
	                            field[i][j-2]=0;
	                            f[i][j-2]=null;
	                            frame_count=frame_count-4;
	                            score++;
	                        }
	                        }catch(ArrayIndexOutOfBoundsException e){}
	                        
	                        // match   +++
	                        // form      +
	                        try{
	                        if(((field[i][j]==z)&&(field[i][j-1]==z))&&((field[i-1][j-1]==z)&&(field[i-2][j-1]==z))){
	                            field[i][j]=0;
	                            f[i][j]=null;
	                            field[i][j-1]=0;
	                            f[i][j-1]=null;
	                            field[i-1][j-1]=0;
	                            f[i-1][j-1]=null;
	                            field[i-2][j-1]=0;
	                            f[i-2][j-1]=null;
	                            frame_count=frame_count-4;
	                            score++;
	                        }
	                        }catch(ArrayIndexOutOfBoundsException e){}
	                        
	                        // match     +
	                        // form    +++
	                        try{
	                        if(((field[i][j]==z)&&(field[i][j-1]==z))&&((field[i-1][j]==z)&&(field[i-2][j]==z))){
	                            field[i][j]=0;
	                            f[i][j]=null;
	                            field[i][j-1]=0;
	                            f[i][j-1]=null;
	                            field[i-1][j]=0;
	                            f[i-1][j]=null;
	                            field[i-2][j]=0;
	                            f[i-2][j]=null;
	                            frame_count=frame_count-4;
	                            score++;
	                        }
	                        }catch(ArrayIndexOutOfBoundsException e){}
	                        
	                        // match   +  
	                        // form    +++
	                        try{
	                        if(((field[i][j]==z)&&(field[i-1][j]==z))&&((field[i-2][j]==z)&&(field[i-2][j-1]==z))){
	                            field[i][j]=0;
	                            f[i][j]=null;
	                            field[i-1][j]=0;
	                            f[i-1][j]=null;
	                            field[i-2][j]=0;
	                            f[i-2][j]=null;
	                            field[i-2][j-1]=0;
	                            f[i-2][j-1]=null;
	                            frame_count=frame_count-4;
	                            score++;
	                        }
	                        }catch(ArrayIndexOutOfBoundsException e){}
	                        
	                        // match   +++  
	                        // form    +
	                        try{
	                        if(((field[i][j]==z)&&(field[i-1][j]==z))&&((field[i-2][j]==z)&&(field[i-2][j+1]==z))){
	                            field[i][j]=0;
	                            f[i][j]=null;
	                            field[i-1][j]=0;
	                            f[i-1][j]=null;
	                            field[i-2][j]=0;
	                            f[i-2][j]=null;
	                            field[i-2][j+1]=0;
	                            f[i-2][j+1]=null;
	                            frame_count=frame_count-4;
	                            score++;
	                        }
	                        }catch(ArrayIndexOutOfBoundsException e){}
	                        
	                        // match    ++  
	                        // form    ++
	                        try{
	                        if(((field[i][j]==z)&&(field[i+1][j]==z))&&((field[i+1][j-1]==z)&&(field[i+2][j-1]==z))){
	                            field[i][j]=0;
	                            f[i][j]=null;
	                            field[i+1][j]=0;
	                            f[i+1][j]=null;
	                            field[i+1][j-1]=0;
	                            f[i+1][j-1]=null;
	                            field[i+2][j-1]=0;
	                            f[i+2][j-1]=null;
	                            frame_count=frame_count-4;
	                            score++;
	                        }
	                        }catch(ArrayIndexOutOfBoundsException e){}
	                        
	                        // match   ++  
	                        // form     ++
	                        try{
	                        if(((field[i][j]==z)&&(field[i-1][j]==z))&&((field[i-1][j-1]==z)&&(field[i-2][j-1]==z))){
	                            field[i][j]=0;
	                            f[i][j]=null;
	                            field[i-1][j]=0;
	                            f[i-1][j]=null;
	                            field[i-1][j-1]=0;
	                            f[i-1][j-1]=null;
	                            field[i-2][j-1]=0;
	                            f[i-2][j-1]=null;
	                            frame_count=frame_count-4;
	                            score++;
	                        }
	                        }catch(ArrayIndexOutOfBoundsException e){}
	                        
	                        // match   +  
	                        // form    ++
	                        //          +
	                        try{
	                        if(((field[i][j]==z)&&(field[i][j-1]==z))&&((field[i-1][j-1]==z)&&(field[i-1][j-2]==z))){
	                            field[i][j]=0;
	                            f[i][j]=null;
	                            field[i][j-1]=0;
	                            f[i][j-1]=null;
	                            field[i-1][j-1]=0;
	                            f[i-1][j-1]=null;
	                            field[i-1][j-2]=0;
	                            f[i-1][j-2]=null;
	                            frame_count=frame_count-4;
	                            score++;
	                        }
	                        }catch(ArrayIndexOutOfBoundsException e){}
	                        
	                        // match    + 
	                        // form    ++
	                        //         +
	                        try{
	                        if(((field[i][j]==z)&&(field[i][j-1]==z))&&((field[i+1][j-1]==z)&&(field[i+1][j-2]==z))){
	                            field[i][j]=0;
	                            f[i][j]=null;
	                            field[i][j-1]=0;
	                            f[i][j-1]=null;
	                            field[i+1][j-1]=0;
	                            f[i+1][j-1]=null;
	                            field[i+1][j-2]=0;
	                            f[i+1][j-2]=null;
	                            frame_count=frame_count-4;
	                            score++;
	                        }
	                        }catch(ArrayIndexOutOfBoundsException e){}
	                        
	                        // match    + 
	                        // form     +
	                        //          +
	                        //          +
	                        try{
	                        if(((field[i][j]==z)&&(field[i][j-1]==z))&&((field[i][j-2]==z)&&(field[i][j-3]==z))){
	                            field[i][j]=0;
	                            f[i][j]=null;
	                            field[i][j-1]=0;
	                            f[i][j-1]=null;
	                            field[i][j-2]=0;
	                            f[i][j-2]=null;
	                            field[i][j-3]=0;
	                            f[i][j-3]=null;
	                            frame_count=frame_count-4;
	                            score++;
	                        }
	                        }catch(ArrayIndexOutOfBoundsException e){}
	                        
	                        // match    ++++ 
	                        // form     
	                        try{
	                        if(((field[i][j]==z)&&(field[i-1][j]==z))&&((field[i-2][j]==z)&&(field[i-3][j]==z))){
	                            field[i][j]=0;
	                            f[i][j]=null;
	                            field[i-1][j]=0;
	                            f[i-1][j]=null;
	                            field[i-2][j]=0;
	                            f[i-2][j]=null;
	                            field[i-3][j]=0;
	                            f[i-3][j]=null;
	                            frame_count=frame_count-4;
	                            score++;
	                        }
	                        }catch(ArrayIndexOutOfBoundsException e){}
	                        
	                        // match    ++
	                        // form     ++
	                        try{
	                        if(((field[i][j]==z)&&(field[i-1][j]==z))&&((field[i][j-1]==z)&&(field[i-1][j-1]==z))){
	                            field[i][j]=0;
	                            f[i][j]=null;
	                            field[i-1][j]=0;
	                            f[i-1][j]=null;
	                            field[i][j-1]=0;
	                            f[i][j-1]=null;
	                            field[i-1][j-1]=0;
	                            f[i-1][j-1]=null;
	                            frame_count=frame_count-4;
	                            score++;
	                        }
	                        }catch(ArrayIndexOutOfBoundsException e){}
	                        
	                    }
	                }
	            }
	        }
	    }
	    
	//this method control "select" block 
	    public void move_cursor(char n){

	        switch(n){
	        case 'u': // case move "select" to up
	                if(cursor.getY()-68 >= 0){
	                    cursor.move_Up();
	                    cursor.setArray(cursor.getArray()-1);
	                    if(switch_frame){// change event with next up block, no empty space
	                        if(field[cursor.getArrax()][cursor.getArray()]!=0){ 
	                            int temp = field[cursor.getArrax()][cursor.getArray()+1];
	                            field[cursor.getArrax()][cursor.getArray()+1]= field[cursor.getArrax()][cursor.getArray()];
	                            field[cursor.getArrax()][cursor.getArray()] = temp;
	                            String color_temp =f[cursor.getArrax()][cursor.getArray()].getColour();
	                            f[cursor.getArrax()][cursor.getArray()].setColour(f[cursor.getArrax()][cursor.getArray()+1].getColour());
	                            f[cursor.getArrax()][cursor.getArray()+1].setColour(color_temp);
	                            switch_frame=false; 
	                        }else{
	                            switch_frame=false;
	                        }
	                    }
	                }
	            break;
	        case 'd': // case move "select" to down
	                if(cursor.getY()+68 < 680){
	                    cursor.move_Down();
	                    cursor.setArray(cursor.getArray()+1);
	                    if(switch_frame){//  change event with next down block, no empty space
	                        if(field[cursor.getArrax()][cursor.getArray()]!=0){
	                            int temp = field[cursor.getArrax()][cursor.getArray()-1];
	                            field[cursor.getArrax()][cursor.getArray()-1]= field[cursor.getArrax()][cursor.getArray()];
	                            field[cursor.getArrax()][cursor.getArray()] = temp;
	                            String color_temp =f[cursor.getArrax()][cursor.getArray()].getColour();
	                            f[cursor.getArrax()][cursor.getArray()].setColour(f[cursor.getArrax()][cursor.getArray()-1].getColour());
	                            f[cursor.getArrax()][cursor.getArray()-1].setColour(color_temp);
	                            switch_frame=false; 
	                        }else{
	                            switch_frame=false;
	                        }
	                    }
	                }
	            break;
	        case 'r': // case move "select" to right
	                if(cursor.getX()+68 < 408){
	                    cursor.move_Right();
	                    cursor.setArrax(cursor.getArrax()+1);
	                    if(switch_frame){//  change event with next right block, no empty space
	                        if(field[cursor.getArrax()][cursor.getArray()]!=0){
	                            int temp = field[cursor.getArrax()-1][cursor.getArray()];
	                            field[cursor.getArrax()-1][cursor.getArray()]= field[cursor.getArrax()][cursor.getArray()];
	                            field[cursor.getArrax()][cursor.getArray()] = temp;
	                            String color_temp =f[cursor.getArrax()][cursor.getArray()].getColour();
	                            f[cursor.getArrax()][cursor.getArray()].setColour(f[cursor.getArrax()-1][cursor.getArray()].getColour());
	                            f[cursor.getArrax()-1][cursor.getArray()].setColour(color_temp);
	                            switch_frame=false; 
	                        }else{
	                            switch_frame=false;
	                        }
	                    }
	                }
	            break;
	        case 'l': // case move "select" to left
	                if(cursor.getX()-68 >=0){
	                    cursor.move_Left();
	                    cursor.setArrax(cursor.getArrax()-1);
	                    if(switch_frame){//  change event with next left block, no empty space
	                        if(field[cursor.getArrax()][cursor.getArray()]!=0){
	                            int temp = field[cursor.getArrax()+1][cursor.getArray()];
	                            field[cursor.getArrax()+1][cursor.getArray()]= field[cursor.getArrax()][cursor.getArray()];
	                            field[cursor.getArrax()][cursor.getArray()] = temp;
	                            String color_temp =f[cursor.getArrax()][cursor.getArray()].getColour();
	                            f[cursor.getArrax()][cursor.getArray()].setColour(f[cursor.getArrax()+1][cursor.getArray()].getColour());
	                            f[cursor.getArrax()+1][cursor.getArray()].setColour(color_temp);
	                            switch_frame=false; 
	                        }else{
	                            switch_frame=false;
	                        }
	                    }
	                }
	            break;
	        case 's': //if pressed space bar you can change a block with up, down, left or right next block, no empty spaces
	                if(!switch_frame){
	                    if(field[cursor.getArrax()][cursor.getArray()]!=0){
	                    switch_frame=true;
	                    }
	                }
	            break;
	        }
	    }
	//  this methods control key events and I can overwrite, but only overwrite Keypressed
	    public void keyReleased(KeyEvent e){  } 
	    public void keyTyped(KeyEvent e) {  }
	// when pressed any of this keys, the player can move "select block" and change blocks
	    public void keyPressed(KeyEvent e) {
	        if(check_ContinueGame()){ // when game finish, disable key events to fix found bugs
	            int key=e.getKeyCode();
	            if (key==KeyEvent.VK_UP){
	                move_cursor('u');
	            }
	            if (key==KeyEvent.VK_DOWN){
	                move_cursor('d');
	            }
	            if (key==KeyEvent.VK_RIGHT){
	                move_cursor('r');
	            }
	            if (key==KeyEvent.VK_LEFT){
	                move_cursor('l');
	            }
	            if (key==KeyEvent.VK_SPACE){
	                move_cursor('s');
	            }
	        }
	    }
	    
	//this method check if game continue (no game over)
	    public boolean check_ContinueGame(){
	        if(frame_count>=59)
	            return false;
	        else
	            return true;
	    }
	// main method 
	    public static void main(String [] args) throws InterruptedException{
	        //initialize JFrame
	        JFrame board = new JFrame("Gtris game");
	        Gtris gt = new Gtris();
	        gt.init_board(gt);
	        gt.setFocusable(true);
	        gt.addKeyListener(gt);
	        board.add(gt);
	        board.setSize(408,680);
	        board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        board.setLocationRelativeTo(null);
	        board.setResizable(false);
	        board.setVisible(true);
	        //game loop
	        gt.t.start();
	        while(gt.check_ContinueGame()){ //while no game over
	            gt.match();
	            gt.blockMove_down();
	            gt.repaint();
	            Thread.sleep(100);
	        }
	        //if game over
	       gt.t.stop();
	       gt.repaint();
	    }
}