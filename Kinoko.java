import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;
import java.lang.*;

public class Kinoko extends CircularQueue
{
    private int printSp;
    Color tr = new Color(0,0,0,0);
    Vector v = new Vector();
    private double R;
    int font_size = 22;
    
    Kinoko()
    {
    }
    
    Kinoko(int a)
    {
        //printSp = a+1;
        printSp = a;
        if(a>=10000 || a<=-10000)    font_size = 19;
        if(a>=100000 || a<=-100000)   font_size = 16;
        pro = true;
    }
    public void act() 
    {
        // Add your action code here.
        if(pro)
        {
            pro = !pro;
            pro=!pro;
            GreenfootImage image = new GreenfootImage(getImage());
            GreenfootImage text = new GreenfootImage(""+printSp, font_size, Color.BLACK, tr);
            image.drawImage(text, (image.getWidth()-text.getWidth())/2, 
                    (image.getHeight()-text.getHeight())*2/3);
            setImage(image);
            //거리의 계산
            int a = getWorld().getWidth()/2, b = getWorld().getHeight()/2;
            R=Math.sqrt((v.square((double)(a-getX()), 2)+v.square((double)(b-getY()), 2)));
        }
        END(CQEnded);
    }    
    
    protected void deleteMove()//정의하도록 합시다.
    {}
}
