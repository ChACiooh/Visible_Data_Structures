import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;
/**
 * Write a description of class Doll here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Barrel extends LinearQueue
{
    private int speed;
    private static final int ACCELERATION = 1;
    private int headtemp;
    private int limitWidth, printSp;
    Color tr = new Color(0,0,0,0);
    private int sp2;
    /**
     * Act - do whatever the Doll wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Barrel(int B, int pS)
    {
        pro = true;//초기 생성 확인
        headtemp = head;//임시 head변수. 현재의 head 값을 저장하여 후에 비교한다.
        speed = 2;
        limitWidth = B+20;//들어오는 매개 변수를 통해 거리를 제어한다.
        //printSp = sp;//sp는 static 변수이므로 직접 출력을 해서는 안 되기 때문에, 하나의 변수를 선언하여 그 값을 대신 저장하여 쓰도록 한다.
        sp2 = sp-1;
        printSp = pS;
    }

    public void act() 
    {
        // Add your action code here.
        if(pro)
        {
            pro = !pro;
            updateImage();
        }
        if(getX()>limitWidth)//한계 x좌표보다 크다면 push형태의 move를 감행한다.
        {
            movePush(limitWidth);
        }
        if(QueueEnded)
        {
            getWorld().removeObject(this);
        }
        else
        {
            //changedHead();
            movePop();
        }
    }    
    
    void movePush(int x)//들어가는 동작
    {
        pushEnd = false;
        if(getX()>=x+L)//감속을 위해 어느 정도 예비 거리로 40 픽셀을 두었다.
        {
            speed += ACCELERATION;
            setLocation(getX()-speed, getY());
        }
        else if(getX()<x+L)
        {
            while(getX()>x)//차이가 작으면, 1픽셀씩 이동시켜 안정되게 이동한다.
            {
                setLocation(getX()-1, getY());
            }
            if(!pushEnd)//pushend가 false 라면, 즉, 마지막에서
            {
                time = 1;
                speed = 2;//초기 속력 초기화
                //pushEnd = true;//pushend!
            }
        }
    }
    
    private void updateImage()//숫자 출력
    {
        GreenfootImage image = new GreenfootImage(getImage());
        GreenfootImage text = new GreenfootImage(""+printSp, 22, Color.WHITE, tr);
        image.drawImage(text, (image.getWidth()-text.getWidth())/2, 
            (image.getHeight()-text.getHeight())/2);
        setImage(image);
    }
    
    void movePop()//제거되는 동작
    {
        if(clicked && printSp == KQ[head-1] && head-1 == sp2)
        {            
            if(getX()>10)
            {
                setLocation(getX()-speed, getY());
                speed += ACCELERATION;
            }
            else
            {
                speed = 2;//속력 초기화
                clicked = false;//다시 pop을 누를 수 있도록 해준다.
                getWorld().removeObject(this);
            }
        }
    }
    
    void changedHead()
    {
        if(head>headtemp)//head가 만약 증가되어, 이 요소가 생성되었을 때 이후로 pop이 눌렸다면
        {
            limitWidth -=40;//이 요소의 한계 x좌표를 줄이고,
            headtemp = head;//headtemp에 다시 head를 저장함으로써 changeHead의 재호출을 막는다. 또한, 다시 pop이 수행될 수 있도록 원활히 한다.
        }
    }
}
