import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;
import javax.swing.*;

public class HomeButton extends Actor
{
    int t;
    private static final int BOARD = 1, LIST = 2;
    //BOARD는 스택, 큐, 힙, 설명서를 의미하고, LIST는 리스트를 의미함.
    public static boolean reference = false;
    public static boolean ref2 = false;
    GreenfootImage base = new GreenfootImage(getImage());
    int x, y;
    int thisXSize, thisYSize;
    { reference = false; ref2 = false; }
    public HomeButton(int a)
    {
        t = a;
        x=y=0;
        thisXSize = getImage().getWidth();
        thisYSize = getImage().getHeight();
        firstChange();
    }
    
    public static void firstChange()//static으로 함으로써 다른 클래스들이 참조할 수 있게 함.
    {
        Board.first = !Board.first;
    }
    
    public void act() 
    {
        try
        {
            x = Greenfoot.getMouseInfo().getX();
            y = Greenfoot.getMouseInfo().getY();
        }catch(NullPointerException npe){  };
        if(x>getX()-thisXSize/2 && x<getX()+thisXSize/2 && y>getY()-thisYSize/2 && y<getY()+thisYSize/2)
        {
            setImage("3-Gray-Home-icon.png");
            
        } else  setImage(base);
        checkClickedThis(Greenfoot.mouseClicked(this));
        if(ref2)
        {
            getWorld().removeObject(this);
        }
    }    
    
    private void checkClickedThis(boolean a)
    {
        if(a || reference)
        {
            if(a)
            {
                try
                {
                    Greenfoot.playSound("click.wav");
                }catch(Exception e){};
            }
            
            if(reference)   Board.first = true;
            else if(!reference) Board.first = false;//명시.
            
            if( t == BOARD )
            {
                ShowMission.ShowMissionButtonEnded = true;
                ((Board)getWorld()).stack.StackEnded=true;
                ((Board)getWorld()).Lq.QueueEnded=true;
                ((Board)getWorld()).Cq.CQEnded=true;
                ((Board)getWorld()).exp.expFin=true;
                ((Board)getWorld()).H.HeapEnded = true;
                SearchBinaryTreetest.SBTEnded = true;
            }
            else if( t == LIST )//List의 경우 조작할 항목이 많아 따로 두었다.
            {
                List.ListEnded = true;
            }
            ((Board)getWorld()).main = new Main();
            getWorld().addObject(((Board)getWorld()).main, getWorld().getWidth()/2, getWorld().getHeight()/2);
            if(reference)
            {
                //((Board)getWorld()).newStart();//Start 버튼 생성.
                //((Board)getWorld()).newHowToPlay();//How to play 버튼 생성.
                reference = !reference; //값을 정반대로 돌려놓는다.
            }
            
            t = 0;
            if(((Board)getWorld()).GM!=null)
                getWorld().removeObject(((Board)getWorld()).GM);
            getWorld().removeObject(this);
        }
    }
    
}
