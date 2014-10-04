import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;
import javax.swing.*;

public class GeneralNode extends List
{
    public boolean DRAW_LINE;
    public boolean type;
    private boolean imHead;//Head Node인지를 판별.
    public boolean scaleReady; // 후단 노드 움직일 때 유용.
    private int time, yMT, xMT;//yMT : y Moving Time. 뿐만아니라 후단 노드들의 움직임도 총괄하는 매개변수. xMT : x Moving Time. yMT와 역할 유사.
    private int temp;
    public boolean linked = false; // 연결 여부 확인.
    private boolean insert = false;
    boolean complete; // 이동 여부 확인
    int data = 0, data2 = 0; // data는 이 노드가 나타내는 숫자, data2는 다음 노드에 부여할 숫자.
    public int No;
    int x, y, x0, y0, fX, fY;
    boolean dmdtmtdmd;
    Color t = new Color(0,0,0,0);
    GreenfootImage img, txt, image;
    SelectNode selectN;
    //public static final int RANGE = 6;
    
    { DRAW_LINE = false; imHead = false; time = 0; }
    
    public GeneralNode(boolean isHead, int SP, int data_num)
    {
        No = SP;
        data = data_num;
        type = true;
        pro = true;
        complete = false;
        temp = 0;
        if(isHead)
        {
            imHead = true;
        }
        else    imHead = false;
    }
    
    private void initialize()
    {
        //초기화.
        time = 0;
        yMT = 0;
        No = 0;
        temp = 0 ;
        img = txt = image = null;
        x=y=x0=y0=fX=fY=0;
        imHead = false;
        DRAW_LINE = false;
        insert = false;
        type = false;
        moving = false;
        getWorld().removeObject(this);
    }
    
    public void act() 
    {
        if(pro)
        {
            pro = !pro;
            scaleReady = false;
            img = new GreenfootImage(getImage());
            if(imHead)
            {
                img = new GreenfootImage("HeadNode.png");
            }
            x0 = img.getWidth()/2;
            y0 = img.getHeight()/2;
            fX = fY = yMT = xMT = 0;
            txt=image=null;
        }
        if(ListEnded)
        {
            initialize();
        }
        else
        {
            if(scaleReady)//사이즈 조절이 완료되었다면
            {
                if(imHead && No == 0)
                {
                    txt = new GreenfootImage("Head", 18, Color.WHITE, t);
                    image = new GreenfootImage(img);
                    image.drawImage(txt, (image.getWidth()-txt.getWidth())/2, (image.getHeight()-txt.getHeight())/2);
                    setImage(image);
                }
                else if(No==sp && No != 0)
                {
                    txt = new GreenfootImage(""+data+"\nnull", 18, Color.WHITE, t);
                    image = new GreenfootImage(img);
                    image.drawImage(txt, (image.getWidth()-txt.getWidth())/2, (image.getHeight()-txt.getHeight())/2);
                    setImage(image);
                }
                else
                {
                    txt = new GreenfootImage(""+data+"\n ", 18, Color.WHITE, t);
                    image = new GreenfootImage(img);
                    image.drawImage(txt, (image.getWidth()-txt.getWidth())/2, (image.getHeight()-txt.getHeight())/2);
                    setImage(image);
                }
                try
                {
                    int mouseX = Greenfoot.getMouseInfo().getX();
                    int mouseY = Greenfoot.getMouseInfo().getY();
                    if(!moving && mouseX>getX()-40 && mouseX<getX()+40 && mouseY>getY()-23 && mouseY<getY()+23)
                    {
                        if(selectN==null)
                        {
                            selectN = new SelectNode();
                            getWorld().addObject(selectN, getX(), getY());
                        }
                        
                    }
                    else if(selectN!=null)
                    {
                        getWorld().removeObject(selectN);
                        selectN = null;
                    }
                }
                catch(NullPointerException npe){};
                if(fX == 0 && fY == 0)
                {
                    fX = getX();
                    fY = getY();
                }
                if(!DRAW_LINE)
                {
                    if(temp == 0)
                        temp = clickAndJudgeNode();
                    if(time == 30)
                    {
                        time = 0;
                    }
                }
                if(DRAW_LINE && temp==1) // 화살표를 만든 후 새로운 노드를 추가하는 과정.
                {
                    if(!insert)
                    {
                        drawArrow(); // 화살표를 만드는 과정
                        if(time == 30)
                        {
                            DRAW_LINE = false;//원래대로 돌림으로써 다시 선을 그리는 일을 피한다.
                            if(/*!(getX()==500 && getY()==420)*/No<RANGE-1 )//마지막 노드가 아닐 때 노드를 추가한다.
                            {
                                ((Board)getWorld()).list.addGeneralNode(getX()+x, getY()+y, No+1, data2);
                                linked = true; // 연결
                                //연결되었다면, 이 노드는 더이상 Tail 노드가 아니다.
                                if(!imHead)
                                {
                                    image = new GreenfootImage(img);
                                    txt = new GreenfootImage(""+data+"\n ",18,Color.WHITE,t);
                                    image.drawImage(txt, (image.getWidth()-txt.getWidth())/2, (image.getHeight()-txt.getHeight())/2);
                                    setImage(image);
                                }
                            
                                sp = No+1;
                                temp = 0;
                            }
                            time = 0;
                        }
                    }
                    else if(xMT<30)//중간에 노드를 삽입하는 과정.
                    {
                        xMT++;
                        for(int i=sp;i>No;i--)
                        {
                            if(i+1==RANGE/2)//층이 나눠지는부분.
                            {
                                if(((Board)getWorld()).list.GN[i].complete == false)
                                {
                                    ((Board)getWorld()).list.GN[i].setLocation(((Board)getWorld()).list.GN[i].getX()-23, 
                                            ((Board)getWorld()).list.GN[i].getY()+2);
                                    /*((Board)getWorld()).list.A[i].setLocation(((Board)getWorld()).list.GN[i].getX()-55, 
                                            ((Board)getWorld()).list.GN[i].getY());
                                    ((Board)getWorld()).list.A[i].AS.setLocation(((Board)getWorld()).list.A[i].AS.getX()+3,
                                                                ((Board)getWorld()).list.A[i].AS.getY());*/
                                }
                                else
                                {
                                    ((Board)getWorld()).list.GN[i].setLocation(((Board)getWorld()).list.GN[i].getX()-21, 
                                            ((Board)getWorld()).list.GN[i].getY()+2);
                                    /*((Board)getWorld()).list.A[i].setLocation(((Board)getWorld()).list.GN[i].getX()-32,
                                            ((Board)getWorld()).list.GN[i].getY());
                                    ((Board)getWorld()).list.A[i].AS.setLocation(((Board)getWorld()).list.A[i].AS.getX()+3,
                                                                ((Board)getWorld()).list.A[i].AS.getY());*/
                                }
                                //((Board)getWorld()).list.A[i].decreaseChange();
                                ((Board)getWorld()).list.A[i].setLocation(((Board)getWorld()).list.A[i].getX()+3,
                                                                ((Board)getWorld()).list.A[i].getY());
                                    ((Board)getWorld()).list.A[i].AS.setLocation(((Board)getWorld()).list.A[i].AS.getX()+3,
                                                                ((Board)getWorld()).list.A[i].AS.getY());
                            }
                            else
                            {
                                if( i == sp && i==RANGE/2 &&  !((Board)getWorld()).list.GN[i].complete ) // 마지막 노드의 위치가 complete인지 아닌지에 따라 이동 방식이 달라짐.
                                {
                                    ((Board)getWorld()).list.GN[i].setLocation(((Board)getWorld()).list.GN[i].getX()+1, 
                                                        ((Board)getWorld()).list.GN[i].getY());
                                    if(xMT == 30)   ((Board)getWorld()).list.GN[i].complete = true;
                                }
                                else
                                ((Board)getWorld()).list.GN[i].setLocation(((Board)getWorld()).list.GN[i].getX()+3,
                                                                ((Board)getWorld()).list.GN[i].getY());
                                //if(i>=RANGE/2-1 && ((Board)getWorld()).list.A[i/2].getY()+60>((Board)getWorld()).list.A[i].getY())
                                if(i==RANGE/2)
                                {
                                    int d;
                                    ((Board)getWorld()).list.A[i].decreaseChange();
                                    if(!((Board)getWorld()).list.A[i].sizeComplete) d = 23;
                                    else    d = 21;
                                    ((Board)getWorld()).list.A[i].setLocation(((Board)getWorld()).list.A[i].getX()-d,
                                                                ((Board)getWorld()).list.A[i].getY()+2);
                                    
                                    ((Board)getWorld()).list.A[i].AS.setLocation(((Board)getWorld()).list.A[i].AS.getX()-21,
                                                                ((Board)getWorld()).list.A[i].AS.getY()+2);
                                                                
                                    if(xMT == 30)
                                    {
                                        ((Board)getWorld()).list.A[i].setLocation(((Board)getWorld()).list.GN[i].getX()-33, 
                                                            ((Board)getWorld()).list.A[i].getY());
                                        
                                    }
                                }
                                else
                                {
                                    ((Board)getWorld()).list.A[i].setLocation(((Board)getWorld()).list.A[i].getX()+3,
                                                                ((Board)getWorld()).list.A[i].getY());
                                    ((Board)getWorld()).list.A[i].AS.setLocation(((Board)getWorld()).list.A[i].AS.getX()+3,
                                                                ((Board)getWorld()).list.A[i].AS.getY());
                                }
                            }
                        }
                        
                        if(xMT==30)
                        {
                            xMT = 0;
                            insert = false;
                            int i;
                            for(i=sp+1;i>No+1;i--) // 노드 번호를 차례대로 옮기는 과정.
                            {
                                if( i<=sp && i>=RANGE/2 && !((Board)getWorld()).list.GN[i].complete )    
                                    ((Board)getWorld()).list.GN[i].complete = true;
                                
                                ((Board)getWorld()).list.GN[i] = ((Board)getWorld()).list.GN[i-1];
                                ((Board)getWorld()).list.GN[i].No++;
                                if(i==RANGE/2) ((Board)getWorld()).list.GN[i].complete = true;
                                ((Board)getWorld()).list.A[i] = ((Board)getWorld()).list.A[i-1];
                                ((Board)getWorld()).list.A[i].No++;
                                ((Board)getWorld()).list.A[i].origin_X+=90;
                                if(i==RANGE/2+1 && i<sp)  
                                {
                                    ((Board)getWorld()).list.A[i].setLocation(((Board)getWorld()).list.GN[i].getX()-53, 
                                                ((Board)getWorld()).list.A[i].getY());
                                        
                                    //((Board)getWorld()).list.A[i].amendArrow();
                                }
                            }
                            ((Board)getWorld()).list.GN[i] = new GeneralNode(false, i, data2);
                            ((Board)getWorld()).list.A[i] = new Arrow(i);
                            int tempX = getX()+90;
                            int tempY = getY();
                            if(No==RANGE/2-1)
                            {
                                tempX = 50;
                                tempY += 60;
                            }
                            
                            ((Board)getWorld()).addObject(((Board)getWorld()).list.GN[i], tempX, tempY);
                            ((Board)getWorld()).list.GN[i].complete = true;
                            ((Board)getWorld()).list.GN[i].linked = true;
                            tempX = getX()+72;
                            ((Board)getWorld()).addObject(((Board)getWorld()).list.A[i], tempX, getY());
                            ((Board)getWorld()).list.A[i].decreaseChange();
                            
                            
                            //((Board)getWorld()).list.GN[i].nameChange();
                            sp++;
                            if( sp>RANGE/2 && ((Board)getWorld()).list.A[sp].sizeComplete && 
                                   ((Board)getWorld()).list.A[sp].getX() != ((Board)getWorld()).list.GN[sp].getX()-53 )
                                {
                                       ((Board)getWorld()).list.A[sp].setLocation( ((Board)getWorld()).list.GN[sp].getX()-53, 
                                                                    ((Board)getWorld()).list.A[sp].getY());
                                }
                            DRAW_LINE = false;
                            temp = 0;
                            
                            //amendArrow();
                            return;
                        }
                    }
                }
                else if(temp == 2 && yMT < 30) // 화살표를 제거한 후 자신의 노드를 제거하는 과정. 또한 이후의 노드들의 움직임을 총괄한다.
                {
                    yMT++;
                    if(selectN!=null)
                    {
                        getWorld().removeObject(selectN);
                        selectN = null;
                    }
                    if(No<RANGE && ((Board)getWorld()).list.GN[No+1]==null)//현재 노드가 Tail이라면 이전 노드의 화살표를 없애버린다.
                    {
                        if(((Board)getWorld()).list.A[No]!=null)
                        {
                            //System.out.println("Called else if(temp ==2 ~ ) in act() method // No="+No+", a="+temp+" ");
                            getWorld().removeObject(((Board)getWorld()).list.A[No].AS);
                            getWorld().removeObject(((Board)getWorld()).list.A[No]);
                            ((Board)getWorld()).list.A[No]=null;
                        }
                    }
                    setLocation(getX(), getY()+(yMT%2));//살짝 가라앉는 효과
                    getImage().setTransparency(255-yMT*7); // 흐려지는 효과
                    //선택한 노드의 다음노드들을 이동시키는 과정
                    int i=1;
                    //try{
                    for(i=1; No+i<RANGE &&(((Board)getWorld()).list.GN[No+i]!=null || ((Board)getWorld()).list.A[No+1+i]!=null);i++)
                    {
                        if((No+i)!=RANGE/2)//No+i가 가장 좌측의 노드가 아닐때
                        {
                            if(!((Board)getWorld()).list.GN[No+i].linked)//Tail일 때
                            {
                                if(!((Board)getWorld()).list.GN[No+i].complete) // 땡겨지지 않은 채라면
                                {
                                    ((Board)getWorld()).list.GN[No+i].setLocation(((Board)getWorld()).list.GN[No+i].getX()-5, 
                                        ((Board)getWorld()).list.GN[No+i].getY());
                                    
                                }
                                else // 땡겨진 채라면
                                {
                                    ((Board)getWorld()).list.GN[No+i].setLocation(((Board)getWorld()).list.GN[No+i].getX()-3, 
                                        ((Board)getWorld()).list.GN[No+i].getY());
                                }
                            }
                            else // Tail이 아닐 때
                            {
                                DC = true;
                                try
                                {
                                    ((Board)getWorld()).list.GN[No+i].setLocation(((Board)getWorld()).list.GN[No+i].getX()-3, 
                                        ((Board)getWorld()).list.GN[No+i].getY());
                                    if( !(((Board)getWorld()).list.A[No+i+1] instanceof LastArrow1))
                                    {
                                        ((Board)getWorld()).list.A[No+i+1].setLocation(
                                            ((Board)getWorld()).list.A[No+i+1].getX()-3/*(2+(i+1)*(yMT%2))*/,
                                            ((Board)getWorld()).list.A[No+1+i].getY() );
                                        ((Board)getWorld()).list.A[No+i+1].AS.setLocation(
                                            ((Board)getWorld()).list.A[No+i+1].AS.getX()-3,
                                            ((Board)getWorld()).list.A[No+1+i].AS.getY() );
                                    }
                                }catch(Exception e){};
                            }
                        }
                        else //2층부터의 가장 좌측의 노드일 때
                        {
                            firstRoomMove(No+i);
                        }
                    }
                    //}catch(Exception e){ /*System.out.println(""+i);*/}
                    if(yMT == 30)
                    {
                        yMT = 0;
                        for(int k=1; No+k<RANGE && ((Board)getWorld()).list.GN[No+k]!=null;k++)
                            ((Board)getWorld()).list.GN[No+k].complete = true;//이동 완료.
                        /*
                        if(No%6==0 && ((Board)getWorld()).list.GN[No+1]==null)
                        {
                            //first Room이 선택되었을 경우 꺾이는 화살표 제거 단계. 단, 다음 노드가 없어야 한다.
                            //getWorld().removeObject(((Board)getWorld()).list.A[No].LA2[No/6 - 1]);
                            int l;
                            for(l=5;l>=0;l--)
                            {
                                if( ((Board)getWorld()).list.A[No].LA2[l]!=null)
                                {
                                    getWorld().removeObject(((Board)getWorld()).list.A[No].LA2[l]);
                                    //((Board)getWorld()).list.A[No].LA2[l].
                                    break;
                                }
                            }
                            if(l>=0)
                                ((Board)getWorld()).list.A[No].LA2[l] = null;
                            //complete.
                        }*/
                        //화살표의 정보를 넘겨주는 과정.
                        ((Board)getWorld()).list.ArrowChange(No);
                        try
                        {
                            ((Board)getWorld()).list.A[RANGE/2].amendArrow();
                        }catch(NullPointerException npe){};
                        if( No== RANGE-1 || ((Board)getWorld()).list.GN[No+1]==null) // 현재의 위치가 Tail이라면
                        {
                            ((Board)getWorld()).list.GN[No-1].linked = false; // 링크되지 않은 것으로 처리.
                            if(No>1) // 전단 노드가 Head가 아닐 때
                            {
                                //글자를 'Tail'로 바꾸는 과정.
                                txt = new GreenfootImage(""+data+"\nnull", 18, Color.WHITE, t);
                                ((Board)getWorld()).list.GN[No-1].image = new GreenfootImage(img);
                                ((Board)getWorld()).list.GN[No-1].image.drawImage(txt, (image.getWidth()-txt.getWidth())/2, 
                                                (image.getHeight()-txt.getHeight())/2);
                                ((Board)getWorld()).list.GN[No-1].setImage(((Board)getWorld()).list.GN[No-1].image);
                            }
                        }
                        sp--;
                        //정보를 넘겨주었으므로 최종 화살표는 sp-1이기 때문에 sp--를 해 주었다. 또한 노드 하나가 줄어들었기 때문이기도 하다.
                        if(sp>0 && ((Board)getWorld()).list.A[sp]!=null)
                            if(((Board)getWorld()).list.A[sp].getX()>((Board)getWorld()).list.A[sp].origin_X)
                                if( ( ((Board)getWorld()).list.A[sp] instanceof LastArrow1) || ((Board)getWorld()).list.A[sp+1]!=null)
                                {
                                    /*((Board)getWorld()).list.A[sp].setLocation(((Board)getWorld()).list.A[sp].getX()-15, 
                                        ((Board)getWorld()).list.A[sp].getY());*/
                                    getWorld().removeObject(((Board)getWorld()).list.A[sp].AS);
                                    getWorld().removeObject(((Board)getWorld()).list.A[sp]);
                                    ((Board)getWorld()).list.A[sp]=null;
                                    ((Board)getWorld()).list.A[sp]= new Arrow();
                                    getWorld().addObject(((Board)getWorld()).list.A[sp], ((Board)getWorld()).list.GN[sp-1].getX()+81, getY()-15);
                                    ((Board)getWorld()).list.A[sp].decreaseChange();//여기 GN[sp-1]을 수정!!
                                }
                        temp = 0;
                        ((Board)getWorld()).list.NoChange(No);
                        
                        initialize();
                    }
                }
                else
                {
                    DRAW_LINE = false;
                }
            }
            else if(!scaleReady)//사이즈 조절중...
            {
                //GreenfootImage GFI = new GreenfootImage(getImage());
                if(img.getWidth()>x0)
                {
                    img.scale(img.getWidth()-3, img.getHeight());
                }
                if(img.getHeight()>y0)
                {
                    img.scale(img.getWidth(), img.getHeight()-3);
                }
                setImage(img);
                
                //사이즈 조절 끝
                dmdtmtdmd = img.getWidth()>=x0-3 && img.getWidth()<=x0+3 && img.getHeight()>=y0-3 && img.getHeight()<=y0+3;
                if(dmdtmtdmd)
                {
                    image = new GreenfootImage(img);
                    if(imHead && sp==0)  txt = new GreenfootImage("Head", 18, Color.WHITE, t);
                    else if(No==sp)    txt = new GreenfootImage(""+data+"\nnull", 18, Color.WHITE, t);
                    else    txt = new GreenfootImage(""+data+"\n ", 18, Color.WHITE, t);
                    image.drawImage(txt, (image.getWidth()-txt.getWidth())/2, (image.getHeight()-txt.getHeight())/2);
                    setImage(image);
                    //System.out.println("가로 : "+image.getWidth()+", 세로 : "+image.getHeight());
                    scaleReady = true;
                    moving = false;
                }
                //GFI = null;
            }
            
        }
    }    
    
    public void printDmdtmtdmd()
    {
        System.out.println(""+dmdtmtdmd);
    }
    
    private void amendArrow()
    {
        if( ((Board)getWorld()).list.A[No]!=null && No!=RANGE/2 && getX() - 53 != ((Board)getWorld()).list.A[No].getX() 
                    && ((Board)getWorld()).list.A[No].sizeComplete)
        {
            ((Board)getWorld()).list.A[No].setLocation(getX()-53, getY());
        }
    }
    
    private void drawArrow()
    {
        /**
         * DRAW_LINE이 true가 되면 이 메서드를 호출한다.
         * 이때 일정한 방향, 모양, 크기로 화살표를 생성한다.
         */
        if(time == 0 )//맨 처음
        {
            DC = true;
            if(No!=0 && No%8 == 0)
            {
                LastArrow2.D = true;
                /**
                 * static 변수이므로 애매한 LastArrow2 객체의 D 변수를 통틀어 조절할 수 있다.
                 */
                //((Board)getWorld()).list.A[No].D = true;
            }
        }
        if(time<30)
        {
            if(!complete)
                setLocation(getX()-2, getY());
            else if(No == RANGE-1)  time=29;
                
            time++;
        }
        if(time == 30)
        {
            if(No<RANGE-1)//마지막 노드가 아닐 때
            {
                ((Board)getWorld()).list.addArrow(No+1, getX()+72, getY());
            }
            x = 150;//새로운 노드를 추가할 좌표 x
            y = 0;//새로운 노드를 추가할 좌표 y
            if(/*getX()>=getWorld().getWidth()-130 && getX()<=getWorld().getWidth()-50*/ No==RANGE-1)//마지막 칸일 때
            {
                x = -390;
                y = 60;
                getWorld().addObject(new OverFlow(1), getWorld().getWidth()/2, getWorld().getHeight()/7);
                moving = false;
                /*if(temp!=0) temp=0;
                return;*/
            }
            complete = true;
            if(temp!=0) temp = 0;
        }
    }
    
    private int clickAndJudgeNode() // 노드 추가와 제거를 주관한다.
    {
        int result = 0, a=2;
        if(Greenfoot.mouseClicked(null) && (Greenfoot.getMouseInfo().getX()<=getX()+40 && Greenfoot.getMouseInfo().getX()>=getX()-40
            && Greenfoot.getMouseInfo().getY()<=getY()+23 && Greenfoot.getMouseInfo().getY()>=getY()-23) && !moving)
        {
            try
            {
                Greenfoot.playSound("click.wav");
            }catch(Exception e){};
            a = checkMessage();
            if(a == 1)
            {
                if(type)//General Node 생성
                {
                    if(!linked) // 연결되어 있지 않다면
                    {
                        if(RANGE-1 == sp)
                        {
                            result = 0;
                            linked = true;
                            DRAW_LINE = false;
                            getWorld().addObject(new OverFlow(1), getWorld().getWidth()/2, getWorld().getHeight()/7);
                            moving = false;
                            return result;
                        }
                        
                        boolean key;
                        String input;
                        do
                        {
                            key = false;
                            input = JOptionPane.showInputDialog(null, "노드에 들어갈 정수를 입력해 주세요.");
                            if(input == null)
                            {
                                result = 0;
                                return result;
                            }
                            try
                            {
                                data2 = Integer.parseInt(input);
                            }
                            catch(Exception e)
                            {
                                JOptionPane.showMessageDialog(null, "정확히 입력해 주세요.");
                                key = true;
                            }
                        }while(key);
                        
                        DRAW_LINE = true;
                        result = 1;
                        moving = true;
                    }
                    else
                    {
                        if(RANGE-1==sp)
                        {
                            DRAW_LINE = false;
                            result = 0;
                            getWorld().addObject(new OverFlow(1), getWorld().getWidth()/2, getWorld().getHeight()/7);
                            moving = false;
                        }
                        else
                        {
                            boolean key;
                            String input;
                            do
                            {
                                key = false;
                                input = JOptionPane.showInputDialog(null, "노드에 들어갈 정수를 입력해 주세요.");
                                if(input == null)
                                {
                                    result = 0;
                                    return result;
                                }
                                try
                                {
                                    data2 = Integer.parseInt(input);
                                }
                                catch(Exception e)
                                {
                                    JOptionPane.showMessageDialog(null, "정확히 입력해 주세요.");
                                    key = true;
                                }
                            }while(key);
                            result = 1;
                            /*for(int i=sp;i>No+1;i--)
                            {
                                ((Board)getWorld()).list.GN[i] = ((Board)getWorld()).list.GN[i-1];
                                ((Board)getWorld()).list.A[i] = ((Board)getWorld()).list.A[i-1];
                            }*/
                            DRAW_LINE = true;
                            insert = true;
                            moving = true;
                        }
                    }
                }
            }
            else if( a == 0 ) // 제거
            {
                result = 2;
                if(No+1<RANGE && ((Board)getWorld()).list.A[No+1]!=null)
                {
                    //System.out.println("Called clickAndJudgeNode() method // No="+No+", a="+a+" ");
                    //우측 화살표 제거
                    if( sp>5 && ((Board)getWorld()).list.A[No+1].LA2[sp/6-1]!=null)
                    {
                        getWorld().removeObject(((Board)getWorld()).list.A[No+1].LA2[sp/6-1]);
                            ((Board)getWorld()).list.A[No+1].LA2[sp/6-1]=null;
                    }
                    getWorld().removeObject(((Board)getWorld()).list.A[No+1].AS);
                    getWorld().removeObject(((Board)getWorld()).list.A[No+1]);
                    ((Board)getWorld()).list.A[No+1] = null;
                }
                moving = true;
            }
            if(a<2 && selectN!=null)
            {
                getWorld().removeObject(selectN);
                selectN = null;
            }
        }
        return result;
    }
    
    private int checkMessage()
    {
        int res = 2;
        if(imHead)  res = 1;
        else    res = JOptionPane.showConfirmDialog(null, "삭제=예  추가=아니오");
        // 0 : 삭제, 1 : 추가, 2 : 아무것도 안 함
        return res;
    }
    
    private void firstRoomMove(int num)
    {
        /**
         * Node들이 층을 이루고 있으므로 가장 좌측에 있는 것을 'first Room'이라고 칭하였다.
         */
        //if( ((Board)getWorld()).list.GN[num+1] == null && ((Board)getWorld()).list.GN[num].complete == false) 
        if( sp == num )
        {
            // 이 Node가 최근 생성된 Tail일 경우
            if( ((Board)getWorld()).list.GN[num].complete )
                ((Board)getWorld()).list.GN[num].setLocation( ((Board)getWorld()).list.GN[num].getX()+21, ((Board)getWorld()).list.GN[num].getY()-2);
            else
                ((Board)getWorld()).list.GN[num].setLocation( ((Board)getWorld()).list.GN[num].getX()+19, ((Board)getWorld()).list.GN[num].getY()-2);
            //((Board)getWorld()).list.A[num].decreaseChange();
            //((Board)getWorld()).list.A[num].setLocation(((Board)getWorld()).list.A[num].getX()+23, ((Board)getWorld()).list.A[num].getY()-2);
            //((Board)getWorld()).list.A[num].AS.setLocation( ((Board)getWorld()).list.A[num].AS.getX()+21, ((Board)getWorld()).list.A[num].AS.getY()-2 );
        }
        else //최근 생성된 Tail이이 아닐 경우
        {
            ((Board)getWorld()).list.GN[num].setLocation( ((Board)getWorld()).list.GN[num].getX()+21, ((Board)getWorld()).list.GN[num].getY()-2);
            ((Board)getWorld()).list.A[num+1].setLocation( ((Board)getWorld()).list.A[num+1].getX()+21, ((Board)getWorld()).list.A[num+1].getY()-2 );
            ((Board)getWorld()).list.A[num+1].AS.setLocation( ((Board)getWorld()).list.A[num+1].AS.getX()+21, ((Board)getWorld()).list.A[num+1].AS.getY()-2 );
        }
    }
    
}






















