import greenfoot.*;
import java.awt.*;
import javax.swing.*;
import java.lang.*;

public class SearchBinaryTree extends Actor implements Delay
{
    public boolean pro;
    public static boolean SBTEnded = false;
    public static final int LIMIT = 31;
    public int range = LIMIT;
    protected int numOfNodes = 0;
    
    int[] x = new int[LIMIT+1];
    int[] y = new int[LIMIT+1];
    int gangyeok, i;

    protected static boolean inputMotion;
    protected static boolean click;
    protected static boolean Search;
    protected static boolean Addition;
    protected static boolean Remove;
    
    private boolean delay = true;
    
    GreenfootImage base = new GreenfootImage(getImage());
    GreenfootImage txt;
    Color t = new Color(0,0,0,0);
    
    private String input;
    int root;
    int data;
    int time; // 속도랄까
    
    TreeNode[] TN = new TreeNode[LIMIT+1];
    INPUT inputButton;
    Canvas myCanvas;
    Vector v0;
    Vector[] nodeVector = new Vector[3]; // 노드 뿐만 아니라 노드 사이에 연결된 라인까지 모두 표시
    Vector[] nodeLineVector = new Vector[5]; // 화면 바깥의 노드를 향하는 노드. nodeLineVector[0]은 현재 보이는 상위 노드의 상위 계층으로 향하도록.
    SearchBinaryTree leftNode, rightNode; // 자기 참조. List의 방식을 이용해 무한 이진 탐색 트리를 구현할 것이다.
    
    { SBTEnded = false;  click = false; Search = false; Addition = false; Remove = false; } // 인스턴스 초기화.
    
    SearchBinaryTree()
    {
        //pro = true;
        //delay = true;
    }
    
    SearchBinaryTree(int range_Num, Vector preLine)
    {
        //pro = true;
        delay = true;
        range = range_Num;
        nodeLineVector[0] = preLine;
    }
    
    public void act() 
    {
        delay();
        if(pro)
        {
            pro = !pro;
            inputMotion = false;
            
            numOfNodes = time = 0;
            boolean key;
            
            do
            {
                key = false;
                input = JOptionPane.showInputDialog(null, "루트 노드에 들어갈 정수를 입력해 주세요.");
                if(input == null)
                {
                    //SBTEnded = true;
                    //HomeButton.reference = true;
                    ((Board)getWorld()).GM.checkClicked(true);
                    return;
                }
                try
                {
                    root = Integer.parseInt(input);
                }
                catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null, "정확히 입력해 주세요.");
                    key = true;
                }
            }while(key);
            
            myCanvas = new Canvas();
            getWorld().addObject(myCanvas, getWorld().getWidth()/2, getWorld().getHeight()/2);
            getWorld().addObject(new INPUT(), getWorld().getWidth()-80, getWorld().getHeight()-190);
            ((Board)getWorld()).newEB(8);
            x[0] = y[0] = 0;
            i=1;
            gangyeok = getWorld().getWidth()/4;
            x[i] = getWorld().getWidth()/2;
            y[i] = getWorld().getHeight()/8;
            i++;
            //지점 좌표를 초기화하는 과정.
            while(i<TN.length)
            {
                y[i] = y[i/2]+100;
                if(i!=2 && y[i]!=y[i-1])
                {
                    gangyeok /= 2;
                }
                if(i%2!=0)
                {
                    x[i] = x[i/2]+gangyeok;
                }
                else
                {
                    x[i] = x[i/2]-gangyeok;
                }
                i++;
            }
            TN[1] = new TreeNode(root);
            getWorld().addObject(TN[1], x[1], y[1]);
            for(i=0;i<nodeVector.length;i++)
            {
                nodeVector[i] = new Vector();
                nodeVector[i].dx = (getWorld().getWidth())*(i+1)/4;
            }
            
            // 노드 위치 벡터 초기화
            nodeVector[0].dy = (getWorld().getHeight()-160)/4;
            nodeVector[1].dy = (getWorld().getHeight()-160)*3/4;
            nodeVector[2].dy = (getWorld().getHeight()-160)*3/4;
            
            numOfNodes++; // 루트 노드 수 체크
        }
        if(SBTEnded)
        {
            for(i=0;i<TN.length;i++)
            {
                if(TN[i] == null)   continue;
                getWorld().removeObject(TN[i]);
                TN[i] = null;
            }
            //myCanvas.getImage().clear();
            getWorld().removeObject(myCanvas);
            myCanvas = null;
            ((Board)getWorld()).deleteEB();
            getWorld().removeObject(this);
        }
        else
        {
            if(!inputMotion)
                checkKeys();
            else
            {
                inputData(data);
            }
            printData();
        }
    }    
    
    private void checkKeys()
    {
        if(!inputMotion && click)
        {
            if(numOfNodes == range)
            {
                JOptionPane.showMessageDialog(null, "이미 가득 차있습니다.");
            }
            input = null;
            input = JOptionPane.showInputDialog(null, "정수를 입력하세요.");
            if(input == null)
            {
                click = false;
                return;
            }
            try
            {
                data = Integer.parseInt(input);
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null, "올바르지 않은 입력");
                return;
            }
            i = 1;
            time = -1;
            inputMotion = true;
        }
    }
    
    public void delay()
    {
        if(delay)
        {
            time ++;
            if(time == 10)
            {
                delay = false;
                time = 0;
                pro = true;
            }
        }
    }
    
    private void printData()
    {
        GreenfootImage img = new GreenfootImage(base);
        if(inputMotion)
        {
            txt = new GreenfootImage("입력값 : "+data, 27, Color.BLACK, t);
            
        }
        else
        {
            txt = new GreenfootImage("입력값 없음", 27, Color.BLACK, t);
            
        }
        img.drawImage(txt, (img.getWidth()-txt.getWidth())/2, (img.getHeight()-txt.getHeight())/2);
        setImage(img);
    }
    
    private void inputData(int num) // 이진 탐색 트리 알고리즘의 구현부.
    {
        time++;
        if(time == 0)
        {
            if(i > range)
            {
                JOptionPane.showMessageDialog(null, "공간상의 문제로 더 이상 추가가 불가능합니다.");
                inputMotion = false;
                click = !click;
                return;
            }
            if(TN[i]!=null)
                TN[i].selected = true;
            else
            {
                TN[i] = new TreeNode(num);
                myCanvas.drawTreeLine(x[i/2], y[i/2], x[i], y[i]);
                getWorld().addObject(TN[i], x[i], y[i]);
                inputMotion = !inputMotion;
                time = -2;
                numOfNodes++;
                //인스턴스 초기화 과정으로 인해 자동으로 click이 false가 된다.
                return;
            }
        }
        if(time == 40 )
        {
            if(num < TN[i].No)
            {
                TN[i].selected = false;
                i = i*2;
            }
            else if(num > TN[i].No)
            {
                TN[i].selected = false;
                i = i*2+1;
            }
            else if (num == TN[i].No)
            {
                JOptionPane.showMessageDialog(null, "해당되는 데이터를 찾았습니다.");
                TN[i].selected = false;
                time = -2;
                inputMotion = !inputMotion;
                click = !click;
                return;
            }
            time = -1;
        }
    }
    
    private void scope(Vector drec)
    {
        
    }
}














