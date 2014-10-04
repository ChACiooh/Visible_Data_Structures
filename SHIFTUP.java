import greenfoot.*;
import java.awt.*;

public class SHIFTUP extends Heap
{
    GreenfootImage base = new GreenfootImage(getImage());
    GreenfootImage txt, img;
    Color t = new Color(0,0,0,0);
    public SHIFTUP()
    {
        pro = true;
    }
    public void act() 
    {
        if(pro)
        {
            pro = false;
            //txt = new GreenfootImage("데이터 추가", 27, Color.RED, t);
            img = new GreenfootImage(base);
            //img.drawImage(txt, (img.getWidth()-txt.getWidth())/2, (img.getHeight()-txt.getHeight())/2);
            setImage(img);
            n = img;
        }
        if(!HeapEnded)
        {
            try
            {
                mx = Greenfoot.getMouseInfo().getX();
                my = Greenfoot.getMouseInfo().getY();
            } catch(NullPointerException npe){};
            if(mx>getX()-60 && mx<getX()+60 && my>getY()-24 && my<getY()+24 && !SU_newData)
            {
                img = new GreenfootImage("button-round-dark-arrow-up-icon.png");
                //txt = new GreenfootImage("데이터 추가", 27, Color.RED, t);
                //img.drawImage(txt, (img.getWidth()-txt.getWidth())/2, (img.getHeight()-txt.getHeight())/2);
                setImage(img);
            }
            else    setImage(n);
            checkClicked();
        }
        else    getWorld().removeObject(this);
    }    
    
    void checkClicked()
    {
        if(Greenfoot.mouseClicked(this) && !SU_newData && !SHIFTUP_Sort)
        {
            try
            {
                Greenfoot.playSound("click.wav");
            }catch(Exception e){};
            SU_newData = true;
        }
    }
}
