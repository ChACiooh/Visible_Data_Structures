import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;
public class HeapData extends Heap
{
    /**
     * Act - do whatever the HeapData wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public int N;
    int tt;
    boolean select;
    private int font_size;
    HeapData(int num)
    {
        N = num;
        select = false;
        tt = 0;
        font_size = 23;
        if(N>=10000 || N<=-10000)    font_size = 19;
        if(N>=100000 || N<=-100000)   font_size = 16;
        GreenfootImage txt = new GreenfootImage(""+N, font_size, Color.WHITE, t);
        GreenfootImage img = new GreenfootImage(base);
        img.drawImage(txt, (img.getWidth()-txt.getWidth())/2, (img.getHeight()-txt.getHeight())/2);
        setImage(img);
    }
    public void act() 
    {
        // Add your action code here.
        if(!HeapEnded)
        {
            if(tt>0)    tt++;
            if((tt == 10 || tt == 0) && !select)    printNum();
        }
    }    
    
    public void printNum()
    {
        GreenfootImage txt = new GreenfootImage(""+N, font_size, Color.WHITE, t);
        GreenfootImage img = new GreenfootImage(base);
        img.drawImage(txt, (img.getWidth()-txt.getWidth())/2, (img.getHeight()-txt.getHeight())/2);
        setImage(img);
        tt = 0;
    }
    
    public void selected()
    {
        GreenfootImage txt = new GreenfootImage(""+N, font_size, Color.WHITE, t);
        GreenfootImage img = new GreenfootImage("SH3.png");
        img.drawImage(txt, (img.getWidth()-txt.getWidth())/2, (img.getHeight()-txt.getHeight())/2);
        setImage(img);
        select = true;
    }
    
    public void changed()
    {
        GreenfootImage txt = new GreenfootImage(""+N, font_size, Color.WHITE, t);
        GreenfootImage img = new GreenfootImage("SH2.png");
        img.drawImage(txt, (img.getWidth()-txt.getWidth())/2, (img.getHeight()-txt.getHeight())/2);
        setImage(img);
        tt=1;
        select = false;
    }
    
    public void beNormal()
    {
        GreenfootImage txt = new GreenfootImage(""+N, 27, Color.WHITE, t);
        GreenfootImage img = new GreenfootImage(base);
        img.drawImage(txt, (img.getWidth()-txt.getWidth())/2, (img.getHeight()-txt.getHeight())/2);
        setImage(img);
    }
}
