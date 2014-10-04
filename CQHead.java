import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;
import java.lang.*;
/**
 * Write a description of class CQHead here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CQHead extends CircularQueue
{
    private double R;
    Vector v = new Vector();
    Color tr = new Color(0,0,0,0);
    public boolean turnE;
    private int pp2;
    private double angle;
    /**
     * Act - do whatever the CQHead wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    CQHead()
    {
        pro = true;
        turnE = true;
        TDT = 0;
        pp2 = 0;
        angle = angle0;
        R = 215.0;
    }
    
    public void act() 
    {
        // Add your action code here.
        if(pro)
        {
            pro = !pro;
            GreenfootImage image = new GreenfootImage(getImage());
            GreenfootImage text = new GreenfootImage("Front", 30, Color.RED, tr);
            image.drawImage(text, (image.getWidth()-text.getWidth())/2, 
                    (image.getHeight()-text.getHeight())/2);
            setImage(image);
            //거리의 계산
            //int a = getWorld().getWidth()/2, b = getWorld().getHeight()/2;
            //R=Math.sqrt((v.square((double)(a-getX()), 2)+v.square((double)(b-getY()), 2)));
        }
        
        if(click)
        {
            if(TDT==0)  TDT=1;
            click = false;
        }
        if(TDT>0)
        {
            turning();
        }
        
        END(CQEnded);
    }    
    
    private void turning()
    {
        turnE = false;
        if(TDT<=90 && TDT>0)
        {
            if( ((Board)getWorld()).Cq.ct.mushproduced[pp2]==1 )
            {
                angle+=1.0;
                angle %= 360.0;
                setLocation(getWorld().getWidth()/2+(int)(v.cosX(angle-angle0, angle0, R)), 
                        getWorld().getHeight()/2-(int)(v.sinY(angle-angle0, angle0, R)));
                TDT+=2;
            }
            else
            {
                getWorld().addObject(new Empty(), getWorld().getWidth()/2, getWorld().getHeight()/2-30);
                click = false;
                turnE = true;
                TDT=0;
            }
            if(TDT==3)
            {
                getWorld().removeObject(((Board)getWorld()).Cq.ct.mush[pp2]);
            }
        }
        else//끝 상황
        {
            if(((Board)getWorld()).Cq.ct.mushproduced[pp2]==1)    ((Board)getWorld()).Cq.ct.mushproduced[pp2]=0;
            TDT=0;
            pp2++;
            pp2%=((Board)getWorld()).Cq.ct.mush.length;
            turnE = true;
            turnEnd = true;
        }
    }
}
