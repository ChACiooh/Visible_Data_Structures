import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;
import java.lang.*;
/**
 * Write a description of class CQTail here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CQTail extends CircularQueue
{
    Color tr = new Color(0,0,0,0);
    Vector v = new Vector();
    private double R;//중심으로부터의 거리 상수
    private static final double PI=3.141592653589793258;
    Kinoko[] mush = new Kinoko[7];
    int sp;
    int[] mushproduced = new int[7];
    /**
     * Act - do whatever the CQTail wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    CQTail()
    {
        pro = true;
        sp = 0;
        for(int i = 0 ; i<mushproduced.length;i++)   mushproduced[i]=0;
    }
    public void act() 
    {
        // Add your action code here.
        if(pro)
        {
            pro=!pro;
            GreenfootImage image = new GreenfootImage(getImage());
            GreenfootImage text = new GreenfootImage("Rear", 30, Color.YELLOW, tr);
            image.drawImage(text, (image.getWidth()-text.getWidth())/2, 
                    (image.getHeight()-text.getHeight())/2);
            setImage(image);
            //거리의 계산
            int a = getWorld().getWidth()/2, b = getWorld().getHeight()/2;
            R=Math.sqrt((v.square((double)(a-getX()), 2)+v.square((double)(b-getY()), 2)));
        }
        if(TDT>0)   turning();
        END(CQEnded);
    }    
    
    private void turning()
    {
        turnEnd = false;
        if(TDT<=90 && pp<mush.length && mushproduced[pp]==0)
        {
            if(TDT == 1)
            {
                mush[pp] = new Kinoko(data);//매개변수 sp는 그 버섯이 출력할 번호를 지정한다.
                getWorld().addObject(mush[pp], getWorld().getWidth()/2+(int)(v.cosX(ANGLE, angle0, R)), 
                    getWorld().getHeight()/2-(int)(v.sinY(ANGLE, angle0, R)));
            }
            setLocation(getWorld().getWidth()/2+(int)(v.cosX(ANGLE-angle0, angle0, R)), 
                        getWorld().getHeight()/2-(int)(v.sinY(ANGLE-angle0, angle0, R)));
            TDT+=2;
        }
        else
        {
            TDT=0;
            if(!turnEnd)
            {
                turnEnd = true;
                if(mushproduced[pp]==0)
                {
                    //over = false;
                    /*mush[pp] = new Kinoko(sp);//매개변수 sp는 그 호박이 출력할 번호를 지정한다.
                    getWorld().addObject(mush[pp], getWorld().getWidth()/2+(int)(v.cosX(ANGLE-45.0, angle0, R)), 
                    getWorld().getHeight()/2-(int)(v.sinY(ANGLE-45.0, angle0, R)));*/
                                               
                    mushproduced[pp]=1;
                    pp++; sp++;
                    pp%=mush.length;
                }
                else
                {
                    //over = true;
                    getWorld().addObject(new OverFlow(1), getWorld().getWidth()/2, getWorld().getHeight()/2-30);
                }
            }
        }
    }
    
}
