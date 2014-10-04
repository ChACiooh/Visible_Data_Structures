import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;

public class GotoMain extends Select
{
    int x, y;
    GreenfootImage n = new GreenfootImage(getImage());
    Color t = new Color(0,0,0,0);
    public int situ;
    private int time;
    public GotoMain(int No)
    {
        situ = No;
        f = g = true;
        x = y =0;
    }
    public void act() 
    {
        if(f)
        {
            f = false;
            x = y = 0;
            time = 0;
            /*GreenfootImage txt = new GreenfootImage("이전", 25, Color.WHITE, t);
            n.drawImage(txt, (n.getWidth()-txt.getWidth())/2+10, (n.getHeight()-txt.getHeight())/2);
            setImage(n);*/
        }
        
        modeMove();
        /*if(g)
        {*/
            try
            {
                x = Greenfoot.getMouseInfo().getX();
                y = Greenfoot.getMouseInfo().getY();
            }catch(NullPointerException npe){  }
            if(x>getX()-30&&x<getX()+30&&y>getY()-30&&y<getY()+30)
            {
                GreenfootImage img = new GreenfootImage("button-round-end-icon.png");
                setImage(img);
                if(time<3)
                    time++;
                if(time == 3)
                checkClicked(Greenfoot.mouseClicked(this));
            }
            else
            {
                time = 0;
                setImage(n);
            }
            
        /* }
        else
        {
            getWorld().removeObject(this);
        }*/
    }    
    
    private void modeMove()
    {
        if(situ == 2 && getY()>getWorld().getHeight()/8+20)
        {
            setLocation(getX(), getY()-20);
        }
        else if(situ == 1 && getY()<getWorld().getHeight()/2)
        {
            setLocation(getX(), getY()+20);
        }
    }
    
    public void setSituation(int No)
    {
        situ = No;
    }
    
    public void checkClicked(boolean a)
    {
        if(a && situ == 1)
        {
            try
            {
                Greenfoot.playSound("click.wav");
            }catch(Exception e){};
            g = false;
            Board.first = false;
            ((Board)getWorld()).newStart2();
            ((Board)getWorld()).newHowToPlay2();
            time = 0;
            getWorld().removeObject(this);
        }
        else if(a && situ == 2)
        {
            try
            {
                Greenfoot.playSound("click.wav");
            }catch(Exception e){};
            time = 0;
            Board.first = true;
            Stack.StackEnded = true;
            List.ListEnded = true;
            LinearQueue.QueueEnded = true;
            CircularQueue.CQEnded = true;
            Heap.HeapEnded = true;
            SearchBinaryTreetest.SBTEnded = true;
            SearchBinaryTree.SBTEnded = true;
            ShowMission.ShowMissionButtonEnded = true;
            getWorld().addObject(((Board)getWorld()).main, getWorld().getWidth()/2, getWorld().getHeight()/2);
            ((Board)getWorld()).main.addPannels();
            HomeButton.ref2 = true;
            int D = 30;
            getWorld().addObject(new SelectStack(), getWorld().getWidth()/3, getWorld().getHeight()*3/10+D+10);
            getWorld().addObject(new SelectLinearQueue(), getWorld().getWidth()/3, getWorld().getHeight()/2+10);
            getWorld().addObject(new SelectCircularQueue(), getWorld().getWidth()/3, getWorld().getHeight()*7/10-D+10);
            getWorld().addObject(new SelectList(), getWorld().getWidth()/3, getWorld().getHeight()*4/5+D);
            getWorld().addObject(new SelectMinHeap(), getWorld().getWidth()*2/3, getWorld().getHeight()*3/10+D+10);
            getWorld().addObject(new SelectMaxHeap(), getWorld().getWidth()*2/3, getWorld().getHeight()/2+10);
            getWorld().addObject(new SelectBinaryTree(), getWorld().getWidth()*2/3, getWorld().getHeight()*7/10-D+10);
            getWorld().addObject(new SelectSBTtest(), getWorld().getWidth()*2/3, getWorld().getHeight()*4/5+D);
            
            setSituation(1);
        }
    }
}
