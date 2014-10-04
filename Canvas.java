import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;

public class Canvas extends Actor
{
    GreenfootImage base = new GreenfootImage(getImage());
    public Canvas(){}
    
    public void act() 
    {
        // Add your action code here.
    }    
    
    public void drawHeapLine(int sp, int[]x, int[]y)
    {
        GreenfootImage img = new GreenfootImage(base);
        img.setColor(Color.BLACK);
        for(int i=2;i<=sp;i++)
            img.drawLine(x[i/2], y[i/2], x[i], y[i]);
        setImage(img);
    }
    
    public void drawTreeLine(int x1, int y1, int x2, int y2)
    {
        GreenfootImage img = new GreenfootImage(getImage());
        img.setColor(Color.BLACK);
        img.drawLine(x1, y1, x2, y2);
        setImage(img);
    }
    
    public void drawHeapLine(int[] x, int[] y, int length)
    {
        GreenfootImage img = new GreenfootImage(base);
        img.setColor(Color.BLACK);
        for(int i=2;i<length;i++)
            img.drawLine(x[i/2], y[i/2], x[i], y[i]);
        setImage(img);
    }
    
    
}
