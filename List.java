import greenfoot.*;
import java.awt.*;
public class List extends Actor
{
    boolean pro;
    public static boolean ListEnded = false;
    public static boolean DC = false;//decreaseChange 확인 여부
    public static int sp = 0;
    public static final GreenfootImage Head_Image = new GreenfootImage("GeneralNode.png");//상수처리
    public static final int RANGE = 16;
    Arrow[] A = new Arrow[RANGE+1];
    GeneralNode[] GN = new GeneralNode[RANGE+1]; // Head를 제외함. 1부터 count.
    GreenfootImage img = new GreenfootImage(getImage());
    Color t = new Color(0,0,0,0);
    public static boolean moving = false;
    
    { ListEnded = false; DC = false;  } // 인스턴스 초기화. 
    
    public List()
    {
        pro = true;
    }
    
    public void act() 
    {
        // Add your action code here.
        if(pro)
        {
            sp = 0;
            moving = false;
            pro = !pro;//전환.
            ((Board)getWorld()).newEB(6);
            GN[0] = new GeneralNode(true, 0, 0);
            /*
            GreenfootImage txt = new GreenfootImage("Linked List", 35, Color.BLACK, t);
            img.drawImage(txt, (img.getWidth()-txt.getWidth())/2, (img.getHeight()-txt.getHeight())/2);
            setImage(img);*/
            getWorld().addObject(GN[0], 110, /*60*/getWorld().getHeight()/2-60);//적당히 배치.
            //getWorld().addObject(new WallOfList(), 5, getWorld().getHeight()/2);
        }
        if(ListEnded)
        {
            //초기화
            sp = 0;
            DC = false;
            img = null;
            ((Board)getWorld()).deleteEB();
            for(int i=0;i<A.length;i++) A[i] = null;
            for(int j=0;j<GN.length;j++)    GN[j] = null;
            getWorld().removeObject(this);
        }
        else//끝나지 않았을 때
        {
            if(DC)
            {
                //decrease를 바꾸어 준다.
                try
                {
                    A[sp].decreaseChange();
                    DC = false;
                } catch(NullPointerException npe) {}
                //try-catch 문을 이용함으로써 예외 발생을 막고, 동시에 DC의 전환을 그 후에 함으로써 아직 false로 바뀌지 않을 수 있게끔 한다.
            }
        }
    }  
    
    protected void NoChange(int num)
    {
        //제거되는 노드부터 작업 진행.
        int i;
        for(i=num;i<RANGE-1 && GN[i+1]!=null;i++)
        {
            GN[i]=GN[i+1];
            GN[i].No--;
        }
        if(i<RANGE) GN[i] = null;
        //목적은 GN[i]가 GN[i+1]의 정보를 받는 것이므로, null을 받아야 한다면 for문을 빠져나온 i값에 해당하는 GN[i]에 null을 대입하여야 한다.
    }
    
    protected void ArrowChange(int num)
    {
        int j;//제거되는 노드의 우측부터 작업 진행.
        for(j = num+1;j<RANGE-1 && A[j+1]!=null;j++)
        {
            A[j] = A[j+1];
            A[j].origin_X -= 90;
            if(A[j].origin_X < 50 ) A[j].origin_X = 752;
            A[j].No--;
        }
        if(j<RANGE) A[j] = null;
        //목적은 A[j]가 A[j+1]의 정보를 받는 것이므로, null을 받아야 한다면 for문을 빠져나온 j값에 해당하는 A[j]에 null을 대입하여야 한다.
    }
    
    public void addGeneralNode(int x, int y, int No, int data2)
    {
        //GeneralNode를 해당 좌표에 추가한다.
        //이때 No는 그 Node의 번호이며, 1~RANGE-1까지 존재한다.
        if(x>=getWorld().getWidth())
        {
            x = 110;
            y += 60;
        }
        
        
        GN[No] = new GeneralNode(false, No, data2);
        getWorld().addObject(GN[No], x, y);
    }
    
    protected void addArrow(int num, int x, int y)
    {
        //num은 1 이상 RANGE-1이하이다.
        if(A[num]!=null)
        {
            getWorld().removeObject(A[num]);
            A[num] = null;
        }
        /*if(num%6!=0)
            */A[num] = new Arrow(num);
        /*else
        {
            A[num] = new LastArrow1(num);
            x = getWorld().getWidth()/2 - 40;
            y += 35;
        }*/
        getWorld().addObject(A[num], x, y);
    }
}





















