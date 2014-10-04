import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import javax.swing.*;
/**
 * Write a description of class Stack here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Stack extends Actor
{
    protected boolean pro;
    public static final int info = 9;//스택의 mission 제한 수.
    public static boolean StackEnded = false, StackGameCleared = false;
    public static int sp=0;//스택 포인터
    public static int y=0;
    public static boolean pushClicked = false;
    public static boolean popClicked = false;
    public static int sp2=0;//임시 스택포인터1
    public static boolean lastCleared = false;
    public static int check = 0;
    protected static int time = 0;
    private int delayTime = 0;
    private boolean flag = false;
    private String dataType;
    Push push;
    { StackEnded = false; StackGameCleared = false; lastCleared = false; check = 0; time = 0;}
    /**
     * Act - do whatever the Stack wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    Stack()
    {
        pro=true;
        dataType = null;//초기화.
        
    }
    
    protected int checkAnswer(boolean type)//type은 미션 보기를 이용하여 추출해낼 수 있다.
    {
        int clearCnt = 0;
        int i, j;//for문용 변수
        flag = false;//도중 탈출 여부 확인용
        if(type && sp>0)//참, 즉 AP일 때
        {
            for(i=0;i<sp;i++)
            {
                if(push.stacken[i].printSp>AP.AnswerAP[i])
                {
                    //답이 틀림. 즉, fail.
                    //해당 번째에 와야 할 숫자보다 큰 녀석이 존재할 때를 판단한다.
                    clearCnt = 0;
                    break;
                }
                
                for(j=i+1;j<sp;j++)
                {
                    if(push.stacken[j].printSp == AP.AnswerAP[i])
                    {
                        //답이 틀림. 즉, fail.
                        clearCnt = 0;
                        flag = true;
                        break;
                    }
                }
                
                if(flag)    break;
                
                if(push.stacken[i].printSp==AP.AnswerAP[i])
                {
                    clearCnt++;
                }
            }
        }
        else if(!type && sp>0)//거짓, 즉 GP일 때
        {
            for(i=0;i<sp;i++)
            {
                if(push.stacken[i].printSp>GP.AnswerGP[i])
                {
                    //답이 틀림. 즉, fail.
                    //해당 번째에 와야 할 숫자보다 큰 녀석이 존재할 때를 판단한다.
                    clearCnt = 0;
                    break;
                }
                
                for(j=i+1;j<sp;j++)
                {
                    if(push.stacken[j].printSp == GP.AnswerGP[i])
                    {
                        //답이 틀림. 즉, fail.
                        clearCnt = 0;
                        flag = true;
                        break;
                    }
                }
                
                if(flag)    break;
                
                if(push.stacken[i].printSp==GP.AnswerGP[i])
                {
                    clearCnt++;
                }
            }
        }
        return clearCnt;
    }
    
    public void act() 
    {
        // Add your action code here.
        if(pro)
        {
            //초기화 및 다른 객체의 형성 과정.
            pro=!pro;
            GreenfootImage img = new GreenfootImage(getImage());
            //img.scale(img.getWidth(), img.getHeight()*4/3+20);
            setImage(img);
            lastCleared = false;
            delayTime = 0;
            sp=0;
            check = 0;
            sp2=0;
            y=0;
            push = new Push();
            ((Board)getWorld()).newEB(3);
            getWorld().addObject(new SP(), getWorld().getWidth()/3, getWorld().getHeight()-20);
            getWorld().addObject(push, getWorld().getWidth()-80, getWorld().getHeight()-300);
            getWorld().addObject(new Pop(), getWorld().getWidth()-80, getWorld().getHeight()-190);
            getWorld().addObject(new HomeButton(1), 80, getWorld().getHeight()-80);
        }
        if(StackEnded)//모든 변수 초기화
        {
            delayTime = 0;
            lastCleared = false;
            check=0;
            sp=0;
            sp2=0;
            y=0;
            push = null;
            ((Board)getWorld()).deleteEB();
            //ShowMission.ShowMissionButtonEnded = true;
            getWorld().removeObject(this);
        }
        else if(((Board)getWorld()).SM != null)
        {
            if(!lastCleared)//끝나지 않았다면
            {
                if(((Board)getWorld()).SM.dataType==1)
                {
                    if(dataType==null)  dataType = "등차수열";
                    if(checkAnswer(true)==9)
                    {
                        lastCleared = true;
                        if(check == 0)
                            check = 1;
                    }
                    else if(sp>0 && checkAnswer(true)==0 && flag)
                    {
                        if(check == 0)  check = 2;
                    }
                }
                else if(((Board)getWorld()).SM.dataType==2)
                {
                    if(dataType==null)  dataType = "등비수열";
                    if(checkAnswer(false)==4)
                    {
                        lastCleared = true;
                        if(check == 0 ) check = 1;
                    }
                    else if(sp>0 && checkAnswer(false)==0 && flag)
                    {
                        if(check == 0)  check = 2;
                    }
                }
            }
            else if(lastCleared && delayTime>=0 && check == 1)
            {
                delayTime++;
                if(delayTime == 25) StackGameCleared = true;
            
                if(delayTime == 65)
                {
                    delayTime= -1;
                    JOptionPane.showMessageDialog(null, "Clear!\n당신도 이제 스택과 "+dataType+" 마스터!");
                    StackEnded = true;
                    HomeButton.reference = true;
                }
            }
            if(!lastCleared && check == 2 && delayTime>=0)
            {
                delayTime++;
                if(delayTime == 13)
                {
                    delayTime = -1;
                    JOptionPane.showMessageDialog(null, "Fail...\n다음 기회에...");
                    StackEnded = true;
                    HomeButton.reference = true;
                }
            }
        }
    }    
}
