import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.GreenfootImage;
import java.lang.*;
import java.awt.*;
import java.io.*;

public class Board extends World
{
    Main main;
    Stack stack;
    Start start;
    LinearQueue Lq;
    CircularQueue Cq;
    private boolean flag = false; 
    public static int num;
    public static boolean first = true;
    ShowMission SM;
    List list;
    HowToPlay HTP;
    Explanations exp;
    Heap H;
    EB eb;
    boolean mom = false;
    GotoMain GM;
    SearchBinaryTreetest SBTtest;
    SearchBinaryTree SBT;
    public Board()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 800, 1); 
        first = true;
        main = new Main();
        start = new Start();
        HTP = new HowToPlay();
        GM = new GotoMain(1);
        flag = true;
        addObject(main, getWidth()/2, getHeight()/2);
        //addObject(start, getWidth()/2, getHeight()/2-70);
        //addObject(HTP, getWidth()/2, getHeight()/2+30);
        setPaintOrder(HomeButton.class, HeapData.class, BfE.class, CQHead.class, Select.class, HeapSort.class, SHIFTUP.class, SHIFTDOWN.class, Mix.class,
         MMConversion.class, CQTail.class, Kinoko.class, Select.class, TreeNodetest.class, SearchBinaryTreetest.class, TreeNode.class,SearchBinaryTree.class, 
         WallOfList.class, Arrow.class, LastArrow1.class, LastArrow2.class, GeneralNode.class, Canvas.class);
        Greenfoot.start();
    }
    /*
    
    public void act()
    {
        if(Greenfoot.isKeyDown("i"))
        {
            if(H!=null)
            {
                mom = H.maxOrmin;
                H.HeapEnded = true;
            }
        }
        else if(Greenfoot.isKeyDown("p"))
        {
            newHeap(0, mom);
        }
        else if(Greenfoot.isKeyDown("s"))
        {
            //Greenfoot.stop();
        }
    }
    */
   
    public void rtimeZeroForSBTtest()
    {
        SBTtest.rtime = 0;
    }
    
    public void newMain(int x, int y)
    {
        main = null;
        main = new Main();
        addObject(main, x, y);
    }
    
    public void deleteHeap()
    {
        removeObject(H);
        H = null;
    }
    
    public void newStart()
    {
        start = new Start();
        addObject(start, getWidth()/2, getHeight()/2-70);
    }
    
    public void newStart2()
    {
        start = new Start();
        start.produced = true;
        addObject(start, getWidth()/2, getHeight()/2-70);
    }
    
    public void newHowToPlay()
    {
        HTP = new HowToPlay();
        addObject(HTP, getWidth()/2, getHeight()/2+120);
    }
    
    public void newHowToPlay2()
    {
        HTP = new HowToPlay();
        HTP.produced = true;
        addObject(HTP, getWidth()/2, getHeight()/2+120);
    }
    
    public void newShowMission(String data_Type)
    {
        if(data_Type!=null)
        {
            SM = new ShowMission(data_Type);
            addObject(SM, getWidth()-80, getHeight()-80);
        }
        else
        {
            SM = null;
        }
    }
    
    public void newEB(int No)
    {
        eb = new EB(No);
        addObject(eb, getWidth()-80, getHeight()-80);
    }
    
    public void newEB(int No, int x, int y)
    {
        eb = new EB(No);
        addObject(eb, x, y);
    }
    
    public void deleteEB()
    {
        if(eb!=null)    removeObject(eb);
        eb = null;
    }
    
    public void newHeap(int option, boolean maxOrmin)
    {
        if(H!=null) return;
        H = new Heap(option, maxOrmin);
        addObject(H, getWidth()/2, 20);
    }
    
    public void newLinearQueue()
    {
        Lq = new LinearQueue();
    }
    
    public void newCircularQueue()
    {
        Cq = new CircularQueue();
    }
    
    public void newList()
    {
        list = new List();
        addObject(list, getWidth()/2, 50);
    }
    
    public void newExplanation()
    {
        exp = new Explanations();
        addObject(exp, getWidth()/2, getHeight()*3/7);
    }
    
    public void newSBTtest()
    {
        SBTtest = new SearchBinaryTreetest();
        addObject(SBTtest, getWidth()/2, 20);
    }
    
    public void newSBT()
    {
        SBT = new SearchBinaryTree();
        addObject(SBT, getWidth()/2, 20);
    }
}
















