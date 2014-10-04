import greenfoot.*;
import java.awt.*;
import javax.swing.*;
import java.lang.*;

public class SearchBinaryTreetest extends Actor implements Delay
{
    public boolean pro;
    public static boolean SBTEnded = false;
    int i;

    protected static boolean inputMotion;
    protected static boolean click;
    protected static boolean Search;
    protected static boolean Addition;
    protected static boolean Remove;
    protected static boolean rclick;
    
    private boolean delay = true;
    
    private boolean test = false;
    
    GreenfootImage base = new GreenfootImage(getImage());
    GreenfootImage txt;
    Color t = new Color(0,0,0,0);
    
    private String input;
    int root;
    int data;
    int time = 0; // 속도랄까
    int vtime; // 스코프 이동 딜레이 변수
    int rtime; // Root로 돌아가는 시간 변수
    boolean down = false;
    private boolean isCreated = false;
    
    int yp;
    
    private static final int midRL = 40;
    private static final int lasRL = 80;
    
    int check = -1;
    
    INPUTtest inputButton;
    Canvas myCanvas;
    GotoRoottest GR;
    
    Vector vr, vt, v0, vtemp; //r:root, n:normal, p:previous
    Vector tt;
    private Vector drec; // 방향
    private Vector[] d = new Vector[5]; // 스코프 진행 방향. 0은 vt의 위치 벡터를 저장한다. 거의 상수.
    TreeNodetest tr, tn, t1, t2; //r: root, n:normal, p:previous, 1:left, 2:right
    private int directNumber;
    
    int underLine_Boundary;
    
    { SBTEnded = false;  Search = false; Addition = false; Remove = false; } // 인스턴스 초기화.
    
    SearchBinaryTreetest()
    {
        //pro = true;
        delay = true;
    }
    
    public void delay() // 필요
    {
        if(delay && time>=0)
        {
            time ++;
            if(time == 10)
            {
                delay = false;
                time = -1;
                pro = true;
            }
        }
    }
    
    public void act() 
    {
        delay();
        if(pro)
        {
            pro = !pro;
            click = false;
            rclick = true;
            inputMotion = false;
            d[0] = new Vector(0, 0);
            d[1] = new Vector(200, -320);
            d[2] = new Vector(-200, -320);
            d[3] = new Vector(200, 320);
            d[4] = new Vector(-200, 320);
            drec = new Vector();
            yp = getWorld().getHeight()*4/5;
            //vtime = 0;
            vtime = rtime = -1;
            time = -1;
            underLine_Boundary = getWorld().getHeight()*4/5;
            isCreated = false;
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
            inputButton = new INPUTtest();
            GR = new GotoRoottest();
            getWorld().addObject(myCanvas, getWorld().getWidth()/2, getWorld().getHeight()/2);
            getWorld().addObject(inputButton, getWorld().getWidth()-190, getWorld().getHeight()-80);
            getWorld().addObject(GR, getWorld().getWidth()-300, getWorld().getHeight()-80);
            ((Board)getWorld()).newEB(8);
            vr = new Vector(getWorld().getWidth()/2, getWorld().getHeight()/5);
            vr.isNull = false;
            vr.num = root;
            tr = new TreeNodetest(vr.num);
            getWorld().addObject(tr, vr.dx, vr.dy);
            vt = vr;
            //vt.v1 = new Vector(Vector.add(vt, d[4]));
            //vt.v2 = new Vector(Vector.add(vt, d[3]));
            //vt.v1.vp = vt.v2.vp = vt;
            v0 = new Vector(vr);
            //System.out.println("v0 : "+v0.toString()+"\nvr : "+vr.toString());
            tn = tr;
            t1 = t1 = null;
        }
        if(SBTEnded)
        {
            getWorld().removeObject(myCanvas);
            ((Board)getWorld()).deleteEB();
            vt = vr = null;
            tn = t1 = t2 = null;
            getWorld().removeObject(this);
        }
        else if(!delay) // 딜레이 끝난 후.
        {
            //drawLine();
            check = -1;
            drawUnderLine();
            printData();
            checkClick();
            if(inputMotion && click)
            {
                addNode();
            }
            else if(rclick && !inputMotion && vt != vr)
            {
                moveToRoot();
            }
            
            if(vt.vp!= null && vt.vp == vr && vtime == -1 && tn.No == vr.num && rtime == -1)
            {
                vt = vr;
                rclick = true;
            }
            
            if(isCreated)
            {
                //JOptionPane.showMessageDialog(null, data+" 추가 완료!");
                if(vt.vp != null)
                {
                    vt = vt.vp;
                }
                isCreated = false;
            }
            
            if(tn!= null)
            {
                if(/*vtime == -1 && */vt != vr && rtime < midRL)
                {
                    //여기부터
                    myCanvas.getImage().drawLine(tn.getX(), tn.getY(), tn.getX()+d[BST(tn.No, vr)].dx, tn.getY()+d[BST(tn.No, vr)].dy);
                }
                if(!Vector.isZeroVector(drec) && (Vector.isParellel(drec, d[1]) || Vector.isParellel(drec, d[2])))
                {
                    //myCanvas.getImage().drawLine(tn.getX(), tn.getY(), tn.getX()+drec.dx, tn.getY()+drec.dy);
                    
                }
                if(t1 != null)
                {
                    myCanvas.getImage().drawLine(tn.getX(), tn.getY(), tn.getX()+d[4].dx, tn.getY()+d[4].dy);
                }
                if(t2 != null)
                {
                    myCanvas.getImage().drawLine(tn.getX(), tn.getY(), tn.getX()+d[3].dx, tn.getY()+d[3].dy);
                }
            }
            else if(t1!=null)
            {
                myCanvas.getImage().drawLine(t1.getX(), t1.getY(), t1.getX()+d[1].dx, t1.getY()+d[1].dy);
                if(t1.t1 != null)
                {
                    myCanvas.getImage().drawLine(t1.getX(), t1.getY(), t1.getX()+d[4].dx, t1.getY()+d[4].dy);
                }
                if(t1.t2 != null)
                {
                    myCanvas.getImage().drawLine(t1.getX(), t1.getY(), t1.getX()+d[3].dx, t1.getY()+d[3].dy);
                }
            }
            else if(t2!=null)
            {
                myCanvas.getImage().drawLine(t2.getX(), t2.getY(), t2.getX()+d[2].dx, t2.getY()+d[2].dy);
                if(t2.t1 != null)
                {
                    myCanvas.getImage().drawLine(t2.getX(), t2.getY(), t2.getX()+d[4].dx, t2.getY()+d[4].dy);
                }
                if(t2.t2 != null)
                {
                    myCanvas.getImage().drawLine(t2.getX(), t2.getY(), t2.getX()+d[3].dx, t2.getY()+d[3].dy);
                }
            }
            
            if(vt!= null)
            {
                //왼쪽 아래로 내려가는 상황
                if(vt.v1 != null && !vt.v1.isNull)
                {
                    if(vt.v1.v1 != null && t1 != null && !vt.v1.v1.isNull)
                    {
                        int YL = t1.getY()+d[4].dy;
                        int XL = t1.getX()+d[4].dx;
                        if(YL > getWorld().getHeight()*4/5)
                        {
                            YL = underLine_Boundary;
                            XL = (t1.getY()-YL)*5/8+t1.getX();
                        }
                        myCanvas.getImage().drawLine(t1.getX(), t1.getY(), XL, YL);
                        
                        //왼쪽 아래의 왼쪽 아래의 왼쪽 아래
                        if(t1.t1 != null && vt.v1.v1.v1 != null && !vt.v1.v1.v1.isNull)
                        {
                            YL = (t1.getY()+d[4].dy)+d[4].dy;
                            XL = (t1.getX()+d[4].dx)+d[4].dx;
                            if(YL > getWorld().getHeight()*4/5)
                            {
                                YL = underLine_Boundary;
                                XL = ( (t1.getY()+d[4].dy) - YL)*5/8 + (t1.getX()+d[4].dx);
                            }
                            myCanvas.getImage().drawLine(t1.getX()+d[4].dx, t1.getY()+d[4].dy, XL, YL);
                        }
                        //왼쪽 아래의 왼쪽 아래의 오른쪽 아래
                        if(t1.t1 != null && vt.v1.v1.v2 != null && !vt.v1.v1.v2.isNull)
                        {
                            YL = (t1.getY()+d[4].dy)+d[3].dy;
                            XL = (t1.getX()+d[4].dx)+d[3].dx;
                            if(YL > getWorld().getHeight()*4/5)
                            {
                                YL = underLine_Boundary;
                                XL = ( YL - (t1.getY()+d[4].dy) )*5/8 + (t1.getX()+d[4].dx);
                            }
                            myCanvas.getImage().drawLine(t1.getX()+d[4].dx, t1.getY()+d[4].dy, XL, YL);
                        }
                        
                    }
                    if(vt.v1.v2 != null && t1 != null && !vt.v1.v2.isNull)
                    {
                        int YL = t1.getY()+d[3].dy;
                        int XL = t1.getX()+d[3].dx;
                        if(YL > getWorld().getHeight()*4/5)
                        {
                            YL = underLine_Boundary;
                            XL = (YL-t1.getY())*5/8+t1.getX();
                        }
                        if( Vector.isZeroVector(drec) || Vector.isParellel(d[1], drec) || vtime < 12 || vtime == 40)
                        {
                            myCanvas.getImage().drawLine(t1.getX(), t1.getY(), XL, YL);
                        }
                        
                        //왼쪽 아래의 오른쪽 아래의 왼쪽 아래
                        if(t1.t2 != null && vt.v1.v2.v1 != null && !vt.v1.v2.v1.isNull)
                        {
                            YL = (t1.getY()+d[3].dy)+d[4].dy;
                            XL = (t1.getX()+d[3].dx)+d[4].dx;
                            if(YL > getWorld().getHeight()*4/5)
                            {
                                YL = underLine_Boundary;
                                XL = ( (t1.getY()+d[3].dy) - YL)*5/8 + (t1.getX()+d[3].dx);
                            }
                            myCanvas.getImage().drawLine(t1.getX()+d[3].dx, t1.getY()+d[3].dy, XL, YL);
                        }
                        //왼쪽 아래의 오른쪽 아래의 오른쪽 아래
                        if(t1.t2 != null && vt.v1.v2.v2 != null && !vt.v1.v2.v2.isNull )
                        {
                            YL = (t1.getY()+d[3].dy)+d[3].dy;
                            XL = (t1.getX()+d[3].dx)+d[3].dx;
                            if(YL > getWorld().getHeight()*4/5)
                            {
                                YL = underLine_Boundary;
                                XL = ( YL - (t1.getY()+d[3].dy) )*5/8 + (t1.getX()+d[3].dx);
                            }
                            myCanvas.getImage().drawLine(t1.getX()+d[3].dx, t1.getY()+d[3].dy, XL, YL);
                        }
                        
                    }
                }
                
                if(vt.v2 != null && !vt.v2.isNull)
                {
                    //오른쪽 아래로 내려가는 상황
                    if(vt.v2.v1 != null && t2 != null && !vt.v2.v1.isNull)
                    {
                        int YL = t2.getY()+d[4].dy;
                        int XL = t2.getX()+d[4].dx;
                        if(YL > getWorld().getHeight()*4/5)
                        {
                            YL = underLine_Boundary;
                            XL = (t2.getY()-YL)*5/8+t2.getX();
                        }
                        if( Vector.isZeroVector(drec) || Vector.isParellel(d[2], drec) || vtime<12 || vtime == 40)
                        {
                            myCanvas.getImage().drawLine(t2.getX(), t2.getY(), XL, YL);
                        }
                        
                        //오른쪽 아래의 왼쪽 아래의 왼쪽 아래
                        if(t2.t1 != null && vt.v2.v1.v1 != null && !vt.v2.v1.v1.isNull)
                        {
                            YL = (t2.getY()+d[4].dy)+d[4].dy;
                            XL = (t2.getX()+d[4].dx)+d[4].dx;
                            if(YL > getWorld().getHeight()*4/5)
                            {
                                YL = underLine_Boundary;
                                XL = ( (t2.getY()+d[4].dy) - YL)*5/8 + (t2.getX()+d[4].dx);
                            }
                            myCanvas.getImage().drawLine(t2.getX()+d[4].dx, t2.getY()+d[4].dy, XL, YL);
                        }
                        //오른쪽 아래의 왼쪽 아래의 오른쪽 아래
                        if(t2.t1 != null && vt.v2.v1.v2 != null && !vt.v2.v1.v2.isNull)
                        {
                            YL = (t2.getY()+d[4].dy)+d[3].dy;
                            XL = (t2.getX()+d[4].dx)+d[3].dx;
                            if(YL > getWorld().getHeight()*4/5)
                            {
                                YL = underLine_Boundary;
                                XL = ( YL - (t2.getY()+d[4].dy) )*5/8 + (t2.getX()+d[4].dx);
                            }
                            myCanvas.getImage().drawLine(t2.getX()+d[4].dx, t2.getY()+d[4].dy, XL, YL);
                        }
                        
                    }
                    if(vt.v2.v2 != null && t2 != null && !vt.v2.v2.isNull)
                    {
                        int YL = t2.getY()+d[3].dy;
                        int XL = t2.getX()+d[3].dx;
                        if(YL > getWorld().getHeight()*4/5)
                        {
                            YL = underLine_Boundary;
                            XL = (YL-t2.getY())*5/8+t2.getX();
                        }
                        myCanvas.getImage().drawLine(t2.getX(), t2.getY(), XL, YL);
                        
                        //오른쪽 아래의 오른쪽 아래의 왼쪽 아래
                        if(t2.t2 != null && vt.v2.v2.v1 != null && !vt.v2.v2.v1.isNull)
                        {
                            YL = (t2.getY()+d[3].dy)+d[4].dy;
                            XL = (t2.getX()+d[3].dx)+d[4].dx;
                            if(YL > getWorld().getHeight()*4/5)
                            {
                                YL = underLine_Boundary;
                                XL = ( (t2.getY()+d[3].dy) - YL)*5/8 + (t2.getX()+d[3].dx);
                            }
                            myCanvas.getImage().drawLine(t2.getX()+d[3].dx, t2.getY()+d[3].dy, XL, YL);
                        }
                        //오른쪽 아래의 오른쪽 아래의 오른쪽 아래
                        if(t2.t2 != null && vt.v2.v2.v2 != null && !vt.v2.v2.v2.isNull)
                        {
                            YL = (t2.getY()+d[3].dy)+d[3].dy;
                            XL = (t2.getX()+d[3].dx)+d[3].dx;
                            if(YL > getWorld().getHeight()*4/5)
                            {
                                YL = underLine_Boundary;
                                XL = ( YL - (t2.getY()+d[3].dy) )*5/8 + (t2.getX()+d[3].dx);
                            }
                            myCanvas.getImage().drawLine(t2.getX()+d[3].dx, t2.getY()+d[3].dy, XL, YL);
                        }
                        
                    }
                }
            }
        }
    }

    private void addNode()
    {
        if(vtime <-1)
        {
            //확인용 딜레이.
            vtime++;
            if(vtime == -1) vtime = 0;
        }
        if(vtime>=0 && vtime<40)
        {
            if(vtime == 0)
            {
                //recursiveNull(vr);
            }
            vtime++;
            if(directNumber == 1 && t1!=null) // 우측 상향으로 이동, 즉 스코프는 좌측 하향 이동
            {
                down = true;
                if(tn!=null)
                {
                    tn.setLocation(tn.getX()+drec.dx, tn.getY()+drec.dy);
                }
                    
                t1.setLocation(t1.getX()+drec.dx, t1.getY()+drec.dy);
                
                if(t2!=null)
                {
                    t2.setLocation(t2.getX()+drec.dx, t2.getY()+drec.dy);
                }
                if(vtime == 20)
                {
                    //recursiveNull(vr);
                    getWorld().removeObject(tn);
                    tn = null;
                    if(t2!=null)
                    {
                        getWorld().removeObject(t2);
                        t2 = null;
                    }
                    try
                    {
                        if(vt.v1.v1 != null && !vt.v1.v1.isNull)
                        {
                            t1.t1 = new TreeNodetest(vt.v1.v1.num);
                            getWorld().addObject(t1.t1, t1.getX()+d[4].dx, t1.getY()+d[4].dy);
                        }
                        if(vt.v1.v2 != null && !vt.v1.v2.isNull)
                        {
                            t1.t2 = new TreeNodetest(vt.v1.v2.num);
                            getWorld().addObject(t1.t2, t1.getX()+d[3].dx, t1.getY()+d[3].dy);
                        }
                    }catch(Exception e){};
                }
                
                if(vtime > 20)
                {
                    if(t1.t1 != null)
                        t1.t1.setLocation(t1.t1.getX()+drec.dx, t1.t1.getY()+drec.dy);
                    if(t1.t2 != null)
                        t1.t2.setLocation(t1.t2.getX()+drec.dx, t1.t2.getY()+drec.dy);
                }
                
                if(vtime == 40)
                {
                    tn = t1;
                    if(vt.v1 == null /*&& !down*/)
                    {
                        vt.v1 = Vector.add(vt, d[4]);
                        
                    }
                    else if(vt.v1 != null)
                    {
                        vt.v1.vp = vt;
                        vt = vt.v1;
                    }
                    
                    if(tn.t1 == null)
                        t1 = null;
                    else    t1 = tn.t1;
                    
                    if(tn.t2 == null)
                        t2 = null;
                    else    t2 = tn.t2;
                }
            }
            else if(directNumber == 2 && t2!=null)
            {
                down = true;
                if(tn!=null)
                    tn.setLocation(tn.getX()+drec.dx, tn.getY()+drec.dy);
                    
                t2.setLocation(t2.getX()+drec.dx, t2.getY()+drec.dy);
                
                if(t1!=null)
                {
                    t1.setLocation(t1.getX()+drec.dx, t1.getY()+drec.dy);
                }
                
                if(vtime == 20)
                {
                    //recursiveNull(vr);
                    getWorld().removeObject(tn);
                    tn = null;
                    if(t1!=null)
                    {
                        getWorld().removeObject(t1);
                        t1 = null;
                    }
                    
                    try
                    {
                        if(vt.v2.v1 != null && !vt.v2.v1.isNull)
                        {
                            t2.t1 = new TreeNodetest(vt.v2.v1.num);
                            getWorld().addObject(t2.t1, t2.getX()+d[4].dx, t2.getY()+d[4].dy);
                        }
                        if(vt.v2.v2 != null && !vt.v2.v2.isNull)
                        {
                            t2.t2 = new TreeNodetest(vt.v2.v2.num);
                            getWorld().addObject(t2.t2, t2.getX()+d[3].dx, t2.getY()+d[3].dy);
                        }
                    }catch(Exception e){};
                }
                
                if(vtime > 20)
                {
                    if(t2.t1 != null)
                        t2.t1.setLocation(t2.t1.getX()+drec.dx, t2.t1.getY()+drec.dy);
                    if(t2.t2 != null)
                        t2.t2.setLocation(t2.t2.getX()+drec.dx, t2.t2.getY()+drec.dy);
                }
                
                if(vtime == 40)
                {
                    tn = t2;
                    if(vt.v2 == null/* && !down*/)
                    {
                        vt.v2 = Vector.add(vt, d[3]);
                    }
                    else if(vt.v2!=null)
                    {
                        vt.v2.vp = vt;
                        vt = vt.v2;
                    }
                    
                    if(tn.t1 == null)
                        t1 = null;
                    else    t1 = tn.t1;
                    
                    if(tn.t2 == null)
                        t2 = null;
                    else    t2 = tn.t2;
                }
            }
        }
        else if(vtime == 40) // 종결
        {
            if(vt.v1==null && data<tn.No) // directNumber == 0
            {
                t1 = new TreeNodetest(data);
                vt.v1 = Vector.add(vt, d[4]);
                vt.v1.isNull = false;
                //t1.setTVector(vt.v1);
                
                //양쪽으로 연결시키는 과정
                vtemp = vt;
                vt = vt.v1;
                vt.vp = vtemp;
                drec = new Vector();
                //System.out.println("t1 생성\n"+vt.toString()+"\n"+vt.vp.toString()+"\n");
                getWorld().addObject(t1, v0.dx+d[4].dx, v0.dy+d[4].dy);
                t1.delay = 0;
                vt.num = data;
                inputMotion = false;
                rclick = false;
                click = false;
                recursiveInit(vr, d[directNumber]);
                vtime = -1;
                down = false;
                //recursiveNull(vr);
                isCreated = true;
            }
            else if(vt.v2 == null && data>tn.No)
            {
                t2 = new TreeNodetest(data);
                vt.v2 = Vector.add(vt, d[3]);
                vt.v2.isNull = false;
                //t2.setTVector(vt.v2);
                
                //양쪽으로 연결시키는 과정
                vtemp = vt;
                vt = vt.v2;
                vt.vp = vtemp;
                drec = new Vector();
                //System.out.println("t2 생성\n"+vt.toString()+"\n"+vt.vp.toString()+"\n");
                getWorld().addObject(t2, v0.dx+d[3].dx, v0.dy+d[3].dy);
                t2.delay = 0;
                vt.num = data;
                inputMotion = false;
                rclick = false;
                click = false;
                recursiveInit(vr, d[directNumber]);
                vtime = -1;
                down = false;
                //recursiveNull(vr);
                isCreated = true;
            }
            else
            {
                vtime = -20;
                if(directNumber == 1)
                {
                    //tn = t1;
                    vtemp = vt;
                    if(vt.v1 == null)
                    {
                        vt.v1 = Vector.add(vt, d[4]);
                    }
                    else if(vt.v1.isNull)
                    {
                        vt.v1 = null;
                        //vtime = -20;
                    }
                    /*vt = vt.v1;
                    //System.out.println("ㅇㅋ");
                    vt.vp = vtemp;*/
                    initDirect(data);
                }
                else if(directNumber == 2)
                {
                    //tn = t2;
                    vtemp = vt;
                    if(vt.v2 == null)
                    {
                        vt.v2 = Vector.add(vt, d[3]);
                    }
                    else if(vt.v2.isNull)
                    {
                        vt.v2 = null;
                        //vtime = 0;
                    }
                    /*vt = vt.v2;
                    vt.vp = vtemp;*/
                    initDirect(data);
                }
            }
        }
    }

    private void recursiveNull(Vector v)
    {
        if(v == null)   return;
        recursiveNull(v.v1);
        recursiveNull(v.v2);
        if(v != null && v.isNull)
        {
            //System.out.println("널 된 게 있음!");
            test = true;
            v = null;
            return;
        }
    }
    
    private void recursiveInit(Vector v, Vector d)
    {
        /**
         * 벡터의 좌표를 원하는대로 초기화하는 메서드. 재귀호출용.
         */
        if(v == null)   return;
        else
        {
            recursiveInit(v.v1, d);
            recursiveInit(v.v2, d);
            Vector.addVectorContents(v, d);
        }
    }
    
    private void recursiveInit(Vector v)
    {
        /**
         * 좌표만 초기화하는 메서드.
         */
        if(v == null || v.isNull)   return;
        else
        {
            if(v.vp!= null && v.vp.v1 == v)
            {
                v.setVector(Vector.add(v.vp, d[4]));
            }
            else if(v.vp !=null && v.vp.v2 == v)
            {
                v.setVector(Vector.add(v.vp, d[3]));
            }
            else if(v.vp == null && v == vr)
            {
                v.setVector(v0);
            }
            recursiveInit(v.v1);
            recursiveInit(v.v2);
        }
    }
    
    
    private void moveToRoot()
    {
        /**
         * 투명해지고 몇 초 후에 다시 루트 위치에서 나타나는 것처럼!
         */
        if(vt == vr)
        {
            rclick = false;
            inputMotion = false;
            rtime = -1;
            return;
        }
        if(rtime>=0 && rtime<lasRL)
        {
            rtime++;
            //myCanvas.getImage().clear();
            if(rtime<midRL)
            {
                tn.getImage().setTransparency((int)(255-(rtime*255/(midRL-1))));
                if(t1!=null)
                {
                    t1.getImage().setTransparency(255-(rtime*255/(midRL-1)));
                }
                if(t2!=null)
                {
                    t2.getImage().setTransparency(255-(rtime*255/(midRL-1)));
                }
            }
            else // rtime>=midRL
            {
                if(rtime == midRL)
                {
                    getWorld().removeObject(tn);
                    tn = null;
                    if(t1!=null)
                    {
                        getWorld().removeObject(t1);
                        t1 = null;
                    }
                    if(t2!=null)
                    {
                        getWorld().removeObject(t2);
                        t2 = null;
                    }
                    
                    vr.setVector(v0); // 좌표만 바꿔주는 것
                    recursiveInit(vr); // 루트 노드 이하의 모든 생성되어 있는 벡터의 좌표 초기화.
                    //vt = vr;
                    rtime = lasRL-1;
                    Greenfoot.playSound("summoner flash.wav");
                    tn = new TreeNodetest(vr.num);
                    getWorld().addObject(tn, v0.dx, v0.dy);
                    tn.getImage().setTransparency(0);
                    if(vr.v1!= null && !vr.v1.isNull)
                    {
                        t1 = new TreeNodetest(vr.v1.num);
                        getWorld().addObject(t1, v0.dx+d[4].dx, v0.dy+d[4].dy);
                        t1.getImage().setTransparency(0);
                    }
                    if(vr.v2!= null && !vr.v2.isNull)
                    {
                        t2 = new TreeNodetest(vr.v2.num);
                        getWorld().addObject(t2, v0.dx+d[3].dx, v0.dy+d[3].dy);
                        t2.getImage().setTransparency(0);
                    }
                }
                else // rtime > midRL
                {
                    int a = 255*(rtime-midRL)/midRL;
                    //tn.getImage().setTransparency(a);
                    tn.controlTransparency(a);
                    //System.out.println(a);
                    if(vr.v1 != null && !vr.v1.isNull)
                    {
                        t1.getImage().setTransparency(255*(rtime-midRL)/midRL);
                    }
                    if(vr.v2 != null && !vr.v2.isNull)
                    {
                        t2.getImage().setTransparency(255*(rtime-midRL)/midRL);
                    }
                }
            }
        }
        else if(rtime == lasRL)
        {
            //System.out.println("???");
            rtime = -1;
            check = -1;
            //rclick = false;
            //inputMotion = false;
            recursiveNull(vr);
            vt = vr;
        }
        
    }
    
    private void checkClick()
    {
        if(click && !inputMotion)
        {
            boolean key;
            do
            {
                key = false;
                input = JOptionPane.showInputDialog(null, "찾고자 하는 데이터(정수)를 입력해 주세요.\n해당 데이터가 존재하지 않으면 자동으로 추가됩니다.");
                if(input == null)
                {
                    click = false; 
                    inputMotion = false;
                    return;
                }
                try
                {
                    data = Integer.parseInt(input);
                }
                catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null, "올바르지 않은 입력");
                    key = true;
                }
            } while(key);
            inputMotion = true;
            vtime = 0;
            //vt = vr;
            initDirect(data);
        }
    }
    
    private void initDirect(int num) // 필요
    {
        drec = new Vector(); // 초기화
        if(num == tn.No)
        {
            JOptionPane.showMessageDialog(null, "데이터를 찾았습니다.");
            if(vt.v1 != null && vt.v1.isNull)
            {
                vt.v1 = null;
            }
            if(vt.v2 != null && vt.v2.isNull)
            {
                vt.v2 = null;
            }
            click = false;
            /*if(vt == vr)    rclick = true;
            else            rclick = false;*/
            if(vt == vr)
                rclick = true;
            else    rclick = false;
            
            vtime = -1;
            inputMotion = false;
            //directNumber = 0;
        }
        else if(num > tn.No)
        {
            if(t2 == null)
            {
                directNumber = 0;
                return;
            }
            drec.setVector(d[2]);
            drec.dx /= 40;  drec.dy /= 40;
            directNumber = 2;
        }
        else if(num < tn.No)
        {
            if(t1 == null)
            {
                directNumber = 0;
                return;
            }
            drec.setVector(d[1]);
            drec.dx /= 40;  drec.dy /= 40;
            directNumber = 1;
        }
    }
    
    private void drawUnderLine()
    {
        myCanvas.getImage().clear();
        myCanvas.getImage().drawLine(0, underLine_Boundary, getWorld().getWidth(), underLine_Boundary);
    }
    
    private void drawLine()
    {
        /*myCanvas.getImage().clear();
        if(vt.vp != null)
        {
            tt = Vector.substraction(vt.vp, vt);
            myCanvas.getImage().drawLine(v0.dx+drec.dx, v0.dy+drec.dy, v0.dx+tt.dx+drec.dx, v0.dy+tt.dx+drec.dy);
        }
        if(vt.v1 != null && !vt.v1.isNull)
        {
            myCanvas.getImage().drawLine(v0.dx+drec.dx, v0.dy+drec.dy, v0.dx+d[4].dx+drec.dx, v0.dy+d[4].dy+drec.dy);
        }
        if(vt.v2 != null && !vt.v2.isNull)
        {
            myCanvas.getImage().drawLine(v0.dx+drec.dx, v0.dy+drec.dy, v0.dx+drec.dy+d[3].dx, v0.dx+drec.dy+d[3].dy);
        }*/
    }
    
    private void printData() // 필요
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
    
    private void findProblemCord()
    {
        recursiveNull(vr);
        if(test)
        {
            System.out.println("rtime : "+rtime+"\nvtime : "+vtime+"\n");
            test = false;
        }
    }
    
    public int BST(int Num, Vector v)
    {
        if( v == null || v.isNull ) return 0;
        
        if( Num == v.num )
        {
            if(v.vp == null)
            {
                return 0;
            }
            
            if(v.vp.v1 !=null && !v.vp.v1.isNull && v.vp.v1.num == Num )
            {
                check = 1;
                return check;
            }
            else if( v.vp.v2 != null && !v.vp.v2.isNull && v.vp.v2.num == Num)
            {
                check = 2;
                return check;
            }
        }
        else if(Num < v.num)
        {
            BST(Num, v.v1);
            if(check != -1) return check;
        }
        else if(Num > v.num)
        {
            BST(Num, v.v2);
            if(check != -1) return check;
        }
        return check;
    }
   
}














