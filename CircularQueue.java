import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.lang.*;
import javax.swing.*;

public class CircularQueue extends Actor
{
    public boolean pro;//초기
    public static boolean CQEnded;//종결
    static boolean checkClicked;//마우스 클릭 인지 -- Push에 해당한다.
    static boolean click=false;//마우스 클릭 인지 -- Pop에 해당한다.
    static int sp = 0;//출력 숫자 변수
    public int TDT=0;//Turning Delay Times
    public boolean TE=true;//Turning Ended
    public static final int info = 7;//mission 수 제한
    public static final double PI = 3.141592653589793258;
    public static double ANGLE = 5*PI/8.0;
    public static final double angle0 = 5*PI/8.0;//초기 위상 각도. 상수이다.
    public static final int A = 283, B = 116;//초기 위상 x, y 좌표. 상수이다.
    public static boolean turnEnd = true;//회전이 끝났을 때 CQTail로 하여금 Kinoko를 쉽게 소환할 수 있게끔 하는 변수.
    public static int pp = 0;//호박의 배열로서의 생성 첨자. 초기엔 0으로 초기화하며 CQTail이 알아서 작업한다.
    public static boolean over = false;// overflow 여부 확인 변수. true일 때 CircularQueue는 회전하지 않아야 한다.
    protected static int data = 0;
    public static boolean dataSet = false;
    CQTail ct;
    CQHead ch;
    { CQEnded = false; checkClicked = false; turnEnd = true; over = false; click = false; data = 0; dataSet = false; } //인스턴스 초기화의 명시.
    /**
     * Act - do whatever the CircularQueue wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    CircularQueue()
    {
        pro = true;
    }
    
    public void act() 
    {
        // Add your action code here.
        if(pro)//initialize
        {
            pro = !pro;
            initializeParameters();
            ct = new CQTail();
            ch = new CQHead();
            getWorld().addObject(new HomeButton(1), 80, getWorld().getHeight()-80);
            getWorld().addObject(new PushCQ(), getWorld().getWidth()/2 - 55, getWorld().getHeight()/2+110);
            getWorld().addObject(new PopCQ(), getWorld().getWidth()/2 + 55, getWorld().getHeight()/2+110);
            ((Board)getWorld()).newEB(5);
            getWorld().addObject(ct, A, B);
            getWorld().addObject(ch, getX()+(int)(215*Math.cos(5*PI/8.0)), getY()-(int)(215*Math.sin(5*PI/8.0)));
        }
        if(!ch.turnE)   TE=false;
        else    TE=true;
        if(checkClicked && TE)
        {
            if(!dataSet)
            {
                boolean key;
                String input;
                do
                {
                    key = false;
                    input = JOptionPane.showInputDialog(null, "정수를 입력해 주세요.");
                    if(input == null)
                    {
                        checkClicked = false;
                        return;
                    }
                    try
                    {
                        data = Integer.parseInt(input);
                    }
                    catch(Exception e)
                    {
                        JOptionPane.showMessageDialog(null, "정확히 입력해 주세요.");
                        key = true;
                    }
                }while(key);
                dataSet = true;
            }
       
            
            
            if(TDT==0)
            {
                TDT=1;
                if(ct.TDT==0)
                ct.TDT=1;
            }
            else    TE=false;
        }
        if(TDT>0)   rotate();
        
        END(CQEnded);
    }    
    
    void END(boolean a)
    { 
        try
        {
            if(a)//하위 클래스들이 안정적으로 사용할 수 있게끔 함.
            {
                initializeParameters();
                ((Board)getWorld()).deleteEB();
                getWorld().removeObject(this);
            }
        } catch(Exception e){}; 
    }
    
    void initializeParameters()
    {
        ANGLE = angle0;
        TDT = 0;
        sp = 0;
        pp = 0;
    }
    
    void rotate()
    {
        if(ct.mushproduced[pp] == 1) over = true;
        else    over = false;
        if(TDT<=90 && (!over))
        {
            TE=false;
            //turn(-TDT%2); 이 큐 자체는 돌지 않는다.
            TDT+=2;
            ANGLE+=1.0;
            ANGLE %= 360.0;
        }
        else
        {
            dataSet = false;
            TDT=0;
            TE=true;
            checkClicked = false;
        }
    }
}
