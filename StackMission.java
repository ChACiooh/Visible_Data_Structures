import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;
/**
 * Write a description of class StackMission here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StackMission extends Stack implements Mission
{
    /**
     * Act - do whatever the StackMission wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    AP ap;
    GP gp;
    protected static boolean StackMissionEnded = false;
    GreenfootImage text = new GreenfootImage("Else, press ENTER!", 40, Color.BLACK, new Color(0,0,0,0));
    int result = 0;
    {StackMissionEnded = false;}
    public StackMission()
    {
        pro = true;
        result = 0;
    }
    
    public void act() 
    {
        // Add your action code here.
        if(pro)
        {
            pro = !pro;
            GreenfootImage img = new GreenfootImage(getImage());
            //img.scale(getWorld().getWidth()+12, getWorld().getHeight()+12);
            img.drawImage(text, (img.getWidth()-text.getWidth())/2, (img.getHeight()-text.getHeight())/4*3);
            setImage(img);
            img = null;
            ap = new AP();
            gp = new GP();
            getWorld().addObject(ap, getWorld().getWidth()/2, getWorld().getHeight()/2-50);
            getWorld().addObject(gp, getWorld().getWidth()/2, getWorld().getHeight()/2+50);
        }
        if(StackEnded)
        {
            result = 0;
            getWorld().removeObject(ap);
            getWorld().removeObject(gp);
            getWorld().removeObject(this);
        }
        else
        {
            if(printMission())
            {
                Begin();
            }
            if(StackMissionEnded)//종결 판단
            {
                ((Board)getWorld()).stack = new Stack();
                for(int i=0;i<((Board)getWorld()).main.pannel.length;i++)
                    getWorld().removeObject(((Board)getWorld()).main.pannel[i]);
                getWorld().removeObject(((Board)getWorld()).main);
                getWorld().addObject(((Board)getWorld()).stack, getWorld().getWidth()/2, getWorld().getHeight()*2/3);
                if(result == 1)
                    ((Board)getWorld()).newShowMission("Stack_AP");
                else if(result == 2)
                    ((Board)getWorld()).newShowMission("Stack_GP");
                else if(result == 3)
                ((Board)getWorld()).newShowMission(null);
                result = 0;//초기화.
                getWorld().removeObject(this);
            }
        }
    }    
    
    public boolean printMission()
    {
        //System.out.println("나오고 있우?");
        if(Greenfoot.mouseClicked(ap))//등차수열이면 result 는 1
        {
            Greenfoot.playSound("click.wav");
            result = 1;
        }
        else if(Greenfoot.mouseClicked(gp))//등비수열이면 result는 2
        {
            Greenfoot.playSound("click.wav");
            result = 2;
        }
        else if(Greenfoot.isKeyDown("\n"))//enter를 누르면 수열을 진행하지 않는다.
        {
            result = 3;
        }
        if(result != 0) return true;
        
        return false;
    }
    
    public void Begin()
    {
        //미션을 출력하고 사라지는 과정.
        if(result == 1)//AP
        {
            ap.printAP();
        }
        else if(result == 2)//GP
        {
            gp.printGP();
        }
        else
        {
            StackMissionEnded = true;
        }
        try
        {
            getWorld().removeObject(ap);
            getWorld().removeObject(gp);
        } catch(NullPointerException npe) {};
    }
    
}
