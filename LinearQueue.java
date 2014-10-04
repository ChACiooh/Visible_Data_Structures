import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import javax.swing.*;
/**
 * Write a description of class Queue here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LinearQueue extends Actor
{
    boolean pro;//맨 처음 생성되었는지의 여부
    public static final int info = 13; //mission 수 제한.
    public static boolean QueueEnded = false, clicked = false, pushEnd = true;//clicked 는 pop에 관여한다.
    public static int head = 0, tail = 0;
    public static int sp = 1;//초기값은 1이다. < 요소의 번호
    public static boolean tpRemoved = false;
    public static final int L = 40; // 거리 상수
    protected static int time = 0; // 딜레이 시간 
    private LQIndex[] li = new LQIndex[18];
    protected static int[] KQ = new int[18];
    
    { QueueEnded = false; clicked = false; tpRemoved = false; time = 0; }//인스턴스 초기화. static 변수의 사용 흔적을 지운다.
    TailPointer tp;

    LinearQueue()
    {
        pro = true;
        tpRemoved = false;
    }

    public void act() 
    {
        // Add your action code here.
        if(pro)
        {
            pro = false;
            ((Board)getWorld()).newEB(4);
            tp = new TailPointer();
            getWorld().addObject(new HomeButton(1), 80, getWorld().getHeight()-80);
            getWorld().addObject(new PushLQ(), getWorld().getWidth()-300, getWorld().getHeight()-80);
            getWorld().addObject(new PopLQ(), getWorld().getWidth()-190, getWorld().getHeight()-80);
            getWorld().addObject(new HeadPointer(), 60, getY()-80);
            getWorld().addObject(tp, 60, getY()+80);
            int i;
            for(i=0;i<li.length;i++)
            {
                li[i] = new LQIndex(i);
                getWorld().addObject(li[i], getImage().getWidth()*(i+1)/18+22, getY()-45);
            }
        }
        if(QueueEnded)
        {
            //주요 변수를 초기화시킨다.
            ((Board)getWorld()).deleteEB();
            init();
            tpRemoved = false;
            getWorld().removeObject(this);
        }
    }    
    
    public static void init()
    {
        time = 0;
        head = tail = 0;
        sp = 1;
    }
}
