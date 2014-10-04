import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;
import java.lang.*;

public class Start extends Select
{
    boolean pro;
    public int thisXSize, thisYSize;
    public boolean produced;
    { delete = false; }
    Start()
    {
        delete = false;
        pro = true;
        produced = false;
        GreenfootImage image = new GreenfootImage(getImage());
        /*GreenfootImage text = new GreenfootImage("시작", 30, Color.RED, t);
        image.drawImage(text, (image.getWidth()-text.getWidth())/2, 
            (image.getHeight()-text.getHeight())/2);*/
        setImage(image);
        n = image;
        b = new GreenfootImage("Button-Log-Off-icon.png");
        thisXSize = getImage().getWidth();
        thisYSize = getImage().getHeight();
        //System.out.println(""+thisYSize);
    }
    
    public void updateImage(String file_name)
    {
        GreenfootImage image = new GreenfootImage(file_name);
        /*GreenfootImage text = new GreenfootImage("시작", 30, Color.RED, t);
        image.drawImage(text, (image.getWidth()-text.getWidth())/2, 
            (image.getHeight()-text.getHeight())/2);*/
        setImage(image);
    }
    
    public void act() 
    {
        // Add your action code here.
        if(pro)
        {
            pro = false;
            setImage(n);
        }
        try
        {
            if(produced)
            {
                x = Greenfoot.getMouseInfo().getX();
                y = Greenfoot.getMouseInfo().getY();
                if(x > getX()-thisXSize/2 && x<getX()+thisXSize/2 && y>getY()-thisYSize/2 && y<getY()+thisYSize/2)
                {
                    /*GreenfootImage txt = new GreenfootImage("시작", 30, Color.RED, t);
                    b.drawImage(txt, (b.getWidth()-txt.getWidth())/2, (b.getHeight()-txt.getHeight())/2-1);*/
                    setImage(b);
                    checkClicked(Greenfoot.mouseClicked( this ));
                }
                else    setImage(n);
            }
        }
        catch(NullPointerException npe){};
        
    }    
    
    public void checkClicked(boolean a)
    {
        if(a)
        {
            try
            {
                Greenfoot.playSound("click.wav");
            }catch(Exception e)
            {
                
            }
            //((Board)getWorld()).HTP.delete = true;
            delete = true;
            //setImage(n);
            int D = 30;
            getWorld().addObject(new SelectStack(), getWorld().getWidth()/3, getWorld().getHeight()*3/10+D+10);
            getWorld().addObject(new SelectLinearQueue(), getWorld().getWidth()/3, getWorld().getHeight()/2+10);
            getWorld().addObject(new SelectCircularQueue(), getWorld().getWidth()/3, getWorld().getHeight()*7/10-D+10);
            getWorld().addObject(new SelectList(), getWorld().getWidth()/3, getWorld().getHeight()*4/5+D);
            getWorld().addObject(new SelectMinHeap(), getWorld().getWidth()*2/3, getWorld().getHeight()*3/10+D+10);
            getWorld().addObject(new SelectMaxHeap(), getWorld().getWidth()*2/3, getWorld().getHeight()/2+10);
            getWorld().addObject(new SelectBinaryTree(), getWorld().getWidth()*2/3, getWorld().getHeight()*7/10-D+10);
            getWorld().addObject(new SelectSBTtest(), getWorld().getWidth()*2/3, getWorld().getHeight()*4/5+D);
            
            ((Board)getWorld()).GM.setSituation(1);
            getWorld().addObject(((Board)getWorld()).GM, 80, getWorld().getHeight()/2);
            getWorld().removeObject(this);
        }
        else if(delete)
        {
            //delete = false; // 초기화.
            getWorld().removeObject(this);
        }
    }
    
}
