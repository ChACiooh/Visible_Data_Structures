import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.lang.*;
public class Heap extends Actor implements Delay
{
    boolean pro;
    public static boolean HeapEnded = false;
    SHIFTDOWN ss;
    protected static final int H_LIMIT = 31, RAND_LIMIT = 50;
    public static int inputLIMIT;
    private String input;
    private String[] Datanum;
    static HeapData hd[];
    GreenfootImage n;
    GreenfootImage base = new GreenfootImage(getImage());
    Color t = new Color(0,0,0,0);
    int i, time, s, p, time2;
    int x[] = new int[H_LIMIT+1];
    int y[] = new int[H_LIMIT+1];
    int save[];
    int mx, my, num;
    private int gangyeok, cons;
    protected static int sp = 2; // 스택포인터. Shift UP을 구현하기 위해, 데이터를 추가할 때마다 1씩 증가시켜준다. H_LIMIT을 넘을 수 없다.
    static int option = 0;
    private int ddd = 0;
    boolean partSorting;
    private boolean mixMake;
    public static boolean maxOrmin = false, sortComplete = false, iInit = false;
    protected static boolean SHIFTDOWN_Sort = false, SHIFTUP_Sort = false, mixCan = false, SU_newData = false, ConverseCan = true;
    Mix mix;
    SHIFTUP su;
    MMConversion MMC;
    public static boolean HeapSorting = false, HeapSortCan = false, HeapSortComplete = false, HeapSortCompleted = false;
    static boolean er = false;
    static int Ne = H_LIMIT, check = H_LIMIT;
    HeapSort HS;
    Canvas myCanvas;
    int delay;
    { HeapEnded = false; HeapSorting = false; HeapSortCan = false; HeapSortComplete = false; HeapSortCompleted = false; }
    Heap()
    {
        //pro = true;
        delay = 0;
        mx = my = 0;
    }
    Heap(int o, boolean mm)
    {
        //pro = true;
        delay = 0;
        mx = my = 0;
        option = o;
        maxOrmin = mm;
        myCanvas = new Canvas();
        for(int ajebal=0;ajebal<100000000;ajebal++);
    }
    public void prodelay()
    {
        if(delay >=0 && delay <20)
        {
            delay++;
        }
        else if(delay != -1)
        {
            delay = -1;
            pro = true;
        }
    }
    public void act() 
    {
        // Add your action code here.
        if(!HeapEnded)
        {
            prodelay();
        }
        if(HeapEnded)
        {
            ((Board)getWorld()).deleteEB();
            //((Board)getWorld()).deleteLine();
            myCanvas.getImage().clear();
            getWorld().removeObject(myCanvas);
            myCanvas = null;
            sp = 2;
            partSorting = false;
            HeapSortCan = false;
            HeapSortComplete = false;
            HeapSorting = false;
            input = null;
            iInit = false;
            time = 0;
            time2 = -1;
            ddd = 0;
            num = 0;
            s = p = 0;
            sortComplete = false;
            SHIFTDOWN_Sort = false;
            SHIFTUP_Sort = false;
            for(i=0;i<=inputLIMIT;i++)
            {
                try
                {
                    getWorld().removeObject(hd[i]);
                    hd[i] = null;
                } catch(Exception e){};
            }
            
            inputLIMIT = H_LIMIT;
            //getWorld().removeObject(this);
            ((Board)getWorld()).deleteHeap();
        }
        else if(option==1)
        {
            //SHIFT DOWN
            if(pro)
            {
                pro = false;
                check = H_LIMIT;
                getWorld().addObject(myCanvas, getWorld().getWidth()/2, getWorld().getHeight()/2);
                do
                {
                    if(er && check>H_LIMIT || check<3)
                    {
                        JOptionPane.showMessageDialog(null, "정확히 입력해 주세요.");
                        check = H_LIMIT;
                    }
                    input = JOptionPane.showInputDialog(null, "몇 개 생성할래요?\n3개 이상이어야 하고, "+H_LIMIT+"개가 한계입니다.");
                    if(input == null)
                    {
                        //HeapEnded = true;
                        //HomeButton.reference = true;
                        ((Board)getWorld()).GM.checkClicked(true);
                        return;
                    }
                    try
                    {
                        er = true;
                        check = Integer.parseInt(input);
                    }catch(Exception e)
                    {
                        JOptionPane.showMessageDialog(null, "제대로 된 자연수를 입력하세요.");
                        check = 0;
                        er = false;
                    }
                }while(check>H_LIMIT || check<3);
                hd = new HeapData[check+1];
                save = new int[check+1];
                s = p = 0;
                sortComplete = false;
                SHIFTDOWN_Sort = false;
                HeapSorting = false;
                HeapSortComplete = false;
                HeapSortCan = false;
                partSorting = false;
                ConverseCan = true;
                time2 = -1;
                mixCan = false;
                iInit = false;
                
                ddd = 0;
                time = 0;
                sp = check;
                ss = new SHIFTDOWN();
                mix = new Mix();
                MMC = new MMConversion();
                HS = new HeapSort();
                ((Board)getWorld()).newEB(7);
                getWorld().addObject(ss, getWorld().getWidth()-190, getWorld().getHeight()-80);
                getWorld().addObject(mix, getWorld().getWidth()-50, 120);
                getWorld().addObject(MMC, getWorld().getWidth()-50, 50);
                getWorld().addObject(HS, getWorld().getWidth()-300, getWorld().getHeight()-80);
                num=0;
                boolean key;
                input = null;
                do
                {
                    key = false;
                    input = JOptionPane.showInputDialog(null, "콤마(,)로 구분하여 "+check+"개의 정수를 입력하세요.");
                    if(input == null)
                    {
                        //HeapEnded = true;
                        //HomeButton.reference = true;
                        ((Board)getWorld()).GM.checkClicked(true);
                        return;
                    }
                    Datanum = input.split(",", check);
                    if(Datanum.length < 3||Datanum.length>check)
                    {
                        JOptionPane.showMessageDialog(null, "올바르지 않은 입력입니다.\n수를 잘 헤아려 주세요.");
                        key = true;
                    }
                }while(key);
                
                do
                {
                    for(int r = 0 ; r<Datanum.length;r++)
                    {
                        boolean b = false;
                        try
                        {
                            num = Integer.parseInt(Datanum[r]);
                            hd[r+1] = new HeapData(num);
                            save[r+1] = num;
                        }catch(Exception e)
                        {
                            b = true;
                        }
                        if ( b )
                        {
                            key = true;
                            break;
                        } else  key = false;
                    }
                    if(key)
                    {
                        boolean c;
                        do
                        {
                            c = false;
                            input = JOptionPane.showInputDialog(null, "콤마(,)로 구분하여 "+check+"개의 정수를 입력하세요.");
                            if(input == null)
                            {
                                //HeapEnded = true;
                                //HomeButton.reference = true;
                                ((Board)getWorld()).GM.checkClicked(true);
                                return;
                            }
                            Datanum = input.split(",", check);
                            if(Datanum.length < 3||Datanum.length>check || Datanum.length!= hd.length)
                            {
                                JOptionPane.showMessageDialog(null, "올바르지 않은 입력입니다.");
                                c = true;
                            }
                        }while(c);
                    }
                }while(key);
                
                for(i=1;i<hd.length;i++)
                {
                    /*num = Greenfoot.getRandomNumber(RAND_LIMIT)+1;
                    if(save[num]==1)  save[num] = 0;
                    else
                    {
                        while(save[num]!=1)
                        {
                            num = Greenfoot.getRandomNumber(RAND_LIMIT)+1;
                        }
                        save[num] = 0;
                    }*/
                    
                    //hd[i] = new HeapData(num);
                    x[i] = 0;
                    y[i] = 0;
                }
                i=1;
                gangyeok = getWorld().getWidth()/4;
                x[i] = getWorld().getWidth()/2;
                y[i] = getWorld().getHeight()/8;
                i++;
                while(i<hd.length)
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
                myCanvas.drawHeapLine(x, y, check+1);
                for(i=1;i<hd.length;i++)
                {
                    getWorld().addObject(hd[i], x[i], y[i]);
                }
                
                Ne = inputLIMIT = check;
            }
            //produced.
            
            GreenfootImage printParent = new GreenfootImage(base);
            GreenfootImage pt;
            String nowType = "[MIN] ";
            if(maxOrmin)
            {
                nowType = "[MAX] ";
            }
            if(p!=0)   pt = new GreenfootImage(nowType+"현재 부모 노드 위치 : "+p, 27,Color.BLACK, t);
            else    pt = new GreenfootImage(nowType+"현재 부모 노드 위치 : 미정", 27, Color.BLACK, t);
            printParent.drawImage(pt, (printParent.getWidth()-pt.getWidth())/2, (printParent.getHeight()-pt.getHeight())/2);
            setImage(printParent);
            if(maxOrmin)
                shiftDown2(Ne);
            else if(!maxOrmin)
            {
                shiftDown(Ne);
            }
            if(HeapSortCan)
            {
                heapSort();
                if(p==0)    p=1;
            }
        }
        else if(option==0)
        {
            //shiftUp을 구현할 수 있다.
            if(pro)
            {
                pro = false;
                check = H_LIMIT;
                getWorld().addObject(myCanvas, getWorld().getWidth()/2, getWorld().getHeight()/2);
                do
                {
                    if(er && check>H_LIMIT || check<3)
                    {
                        JOptionPane.showMessageDialog(null, "정확히 입력해 주세요.");
                        check = H_LIMIT;
                    }
                    input = JOptionPane.showInputDialog(null, "몇 개 생성할까요?\n3개 이상이어야 하고, "+H_LIMIT+"개가 한계입니다.");
                    if(input == null)
                    {
                        //HeapEnded = true;
                        ((Board)getWorld()).GM.checkClicked(true);
                        return;
                    }
                    try
                    {
                        er = true;
                        check = Integer.parseInt(input);
                    }catch(Exception e)
                    {
                        JOptionPane.showMessageDialog(null, "제대로 된 자연수를 입력하세요.");
                        check = 0;
                        er = false;
                    }
                }while(check>H_LIMIT || check<3);
                Ne = inputLIMIT = check;
                hd = new HeapData[check+1];
                save = new int[check+1];
                sortComplete = false;
                sp = 2;
                SHIFTUP_Sort = false;
                mixMake = false;
                HeapSortCan = false;
                HeapSorting = false;
                HeapSortComplete = false;
                time2 = -1;
                SU_newData = false;
                ConverseCan = true;
                partSorting = false;
                mixCan = false;
                iInit = false;
                ddd = 0;
                s = p = 0;
                time = 0;
                cons = 65;
                su = new SHIFTUP();
                ((Board)getWorld()).newEB(7);
                i=1;
                num = Greenfoot.getRandomNumber(RAND_LIMIT)+1;
                hd[i] = new HeapData(num);
                //Ne = check;
                gangyeok = getWorld().getWidth()/4;
                x[i] = getWorld().getWidth()/2;
                y[i] = getWorld().getHeight()/8;
                getWorld().addObject(su, getWorld().getWidth()-190, getWorld().getHeight()-80);
                getWorld().addObject(hd[1], x[1], y[1]);
                i++;
                while(i<hd.length)
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
                //((Board)getWorld()).drawHeapLine(x, y);
            }
            
            if(SU_newData && (time>=0 && sp<=inputLIMIT))
            {
                if(time == 0)
                    newData();
                if(maxOrmin)
                {
                    shiftUp2(); // max
                }
                else if(!maxOrmin)    shiftUp(); // min
            }
            else if(SU_newData && sp>inputLIMIT)
            {
                //JOptionPane.showMessageDialog(null, "가득 찼습니다.");
                getWorld().addObject(new OverFlow(1), getWorld().getWidth()/2, getWorld().getHeight()-40);
                SU_newData = false;
            }
            else if(!SU_newData && sp>inputLIMIT)
            {
                if(!mixMake)
                {
                    mixMake = true;
                    mix = new Mix();
                    HS = new HeapSort();
                    getWorld().addObject(mix, getWorld().getWidth()-50, 120);
                    mixCan = true;
                    iInit = true;
                    i=1;
                    option = 1;
                    getWorld().removeObject(su);
                    su = null;
                    ss = new SHIFTDOWN();
                    MMC = new MMConversion();
                    getWorld().addObject(MMC, getWorld().getWidth()-50, 50);
                    getWorld().addObject(ss, getWorld().getWidth()-190, getWorld().getHeight()-80);
                    getWorld().addObject(HS, getWorld().getWidth()-300, getWorld().getHeight()-80);
                    SHIFTUP_Sort = true;
                    SHIFTDOWN_Sort = false;
                }
            }
            GreenfootImage printChild = new GreenfootImage(base);
            GreenfootImage ct;
            String nowType = "[MIN] ";
            if(maxOrmin)
            {
                nowType = "[MAX] ";
            }
            if(s!=0)    ct = new GreenfootImage(nowType+"갱신된 자식 노드 위치 : "+s, 27,Color.BLACK, t);
            else    ct = new GreenfootImage(nowType+"갱신된 자식 노드 위치 : 미정", 27, Color.BLACK, t);
            printChild.drawImage(ct, (printChild.getWidth()-ct.getWidth())/2, (printChild.getHeight()-ct.getHeight())/2);
            setImage(printChild);
        }
    }    
    
    public static void mmConversion()
    {
        maxOrmin = !maxOrmin;
        iInit = false;
        for(int kkk = 1; kkk < hd.length; kkk++)
        {
            if(hd[kkk].select)
            {
                hd[kkk].printNum();
            }
        }
        Ne = check;
        if(sortComplete)    sortComplete = false;
        SHIFTDOWN_Sort = false;
        SHIFTUP_Sort = false;
    }
    
    public void delay()
    {
        ddd++;
        if(ddd==40)
        {
            if(SHIFTDOWN_Sort)
            {
                JOptionPane.showMessageDialog(null, "SHIFT DOWN 완료!");
                p = 0;
            }
            ddd = -1;
            mixCan = true;
        }
    }
    
    public void heapSort() // N은 마지막 노드의 위치를 의미한다.
    {
        if(!sortComplete)   return;
        if(Ne<=1)
        {
            HeapSorting = false;
            HeapSortCan = false;
            HeapSortComplete = true;
            return;
        }
        time2++;
        if(time2 == 30)
        {
            hd[1].selected();   hd[Ne].selected();
        }
        if(time2 == 50)
        {
            swapHd(1, Ne);
            hd[1].setLocation(x[1], y[1]);
            hd[Ne].setLocation(x[Ne], y[Ne]);
            Ne--;
            time2 = -1;
            if(sortComplete)    sortComplete = false;
            if(!iInit)  iInit = true;
            SHIFTDOWN_Sort = true;
            HeapSortCan = false;
        }
    }
    
    void shiftUp()//MinHeap
    {
        if(SHIFTUP_Sort && !sortComplete)
        {
            time++; ConverseCan = false;
            if(!iInit)
            {
                iInit = true;
                s = sp;
                p=s/2;
            }
           
            if(time>=10 && p>0)
            {    
                if(hd[p].N<=hd[s].N)
                {
                    time = 999;
                }else if(p<=0)  time = 999;
                
                if(time == 30)
                {
                    hd[s].selected();   hd[p].selected();
                    //System.out.println("나오긴 하는건가?");
                }
                
                if(time == 50)
                {
                    swapHd(s, p);
                    hd[p].setLocation(x[p], y[p]);
                    hd[s].setLocation(x[s], y[s]);
                    hd[s].changed();    hd[p].changed();
                    s = p;  p = s/2;
                    time = 1;
                }
                if(time == 999)
                {
                    p = 0;
                }
            }
            if(p<=0)
            {
                iInit = false;
                SHIFTUP_Sort = false;
                SU_newData = false;
                sortComplete = true;
                sp++;
                time = 0;
            }
            
        }
        else if(p<=0)
        {
            time = 0;   ConverseCan = true;
        }
    }
    
    void shiftUp2() // MaxHeap
    {
        if(SHIFTUP_Sort && !sortComplete)
        {
            time++; ConverseCan = false;
            if(!iInit)
            {
                iInit = true;
                s = sp;
                p=s/2;
            }
           
            if(time>=10 && p>0)
            {    
                if(hd[p].N>=hd[s].N)
                {
                    time = 999;
                }else if(p<=0)  time = 999;
                
                if(time == 30)
                {
                    hd[s].selected();   hd[p].selected();
                    //System.out.println("나오긴 하는건가?");
                }
                
                if(time == 50)
                {
                    swapHd(s, p);
                    hd[p].setLocation(x[p], y[p]);
                    hd[s].setLocation(x[s], y[s]);
                    hd[s].changed();    hd[p].changed();
                    s = p;  p = s/2;
                    time = 1;
                }
                if(time == 999)
                {
                    p = 0;
                }
            }
            if(p<=0)
            {
                iInit = false;
                SHIFTUP_Sort = false;
                SU_newData = false;
                sortComplete = true;
                sp++;
                time = 0;
            }
            
        }
        else if(p<=0)
        {
            time = 0;   ConverseCan = true;
        }
    }
    
    public void newData()
    {
        num = 0;
        time = 0;
        /*do
        {
            num = Greenfoot.getRandomNumber(RAND_LIMIT)+1;
        } while(save[num]!=1);
        save[num] = 0;*/
        boolean key;
        String input2;
        do
        {
            key = true;
            input2 = JOptionPane.showInputDialog(null, "정수를 입력하세요.");
            if(input2==null)
            {
                SU_newData = false;
                return;
            }
            try
            {
                num = Integer.parseInt(input2);
                key = false;
            }catch(Exception e)
            {
                JOptionPane.showMessageDialog(null, "정수를 입력하세요!");
                key = true;
            }
        }while(key);
        hd[sp] = new HeapData(num);
        save[sp] = num;
        myCanvas.drawHeapLine(sp, x, y);
        getWorld().addObject(hd[sp], x[sp], y[sp]);
        iInit = false;
        SHIFTUP_Sort = true;
        sortComplete = false;
        ConverseCan = false;
        partSorting = false;
    }
    
    void shiftDown(int n)//MinHeap
    {
        if(!sortComplete && SHIFTDOWN_Sort)
            {
                time++; ConverseCan = false;
                if(!iInit)
                {
                    i = n/2;
                    iInit = true;
                }
                if(time>=30 && i>0)
                {
                    //이 메서드 내부를 여기서 직접 구현하도록 하자.
                    if(!partSorting)
                    {
                        p=i;
                        s=2*p;
                        partSorting = true;
                    }
                    if (s<=n && partSorting)
                    {
                        if(s<n && hd[s+1].N<=hd[s].N && s%2==0)    s++;
                        if(hd[p].N<=hd[s].N)
                        {
                            time = 999;
                        }
                        //바꾸는 과정. 여기서가 중요하다!
                        if(time==30)
                        {
                            hd[p].selected();   hd[s].selected();
                        }
                        
                        if(time==50)
                        {
                            swapHd(s, p);
                            hd[p].setLocation(x[p], y[p]);
                            hd[s].setLocation(x[s], y[s]);
                            hd[p].changed();    
                            hd[s].changed();
                            p=s;    s=2*p;
                            time = 0;
                        }
                        
                    }
                    if(s>n || time == 999)
                    {
                        i--;
                        partSorting = false;
                        time = 0;
                    }
                }
                else if(i<=0)
                {
                    sortComplete = true;
                    delay();
                    i=1;
                }
            }
            else
            {
                time = 0;
                if(sortComplete && HeapSorting)
                {
                    HeapSortCan = true;
                }
                else if(sortComplete && ddd!=-1 && !HeapSortComplete)
                {
                    delay();
                }
                else if(ddd==-1)  ConverseCan = true;
                else if(HeapSortComplete)
                {
                    JOptionPane.showMessageDialog(null, "MIN힙 정렬 완료!");
                    HeapSortComplete = false;
                    HeapSortCompleted = false;
                    ddd = -1;
                    p = 0;
                }
            }
    }
    
    void shiftDown2(int n)//MaxHeap
    {
        if(!sortComplete && SHIFTDOWN_Sort)
            {
                ConverseCan = false;
                time++;
                if(!iInit)
                {
                    i = n/2;
                    iInit = true;
                }
                if(time>=30 && i>0)
                {
                    //이 메서드 내부를 여기서 직접 구현하도록 하자.
                    if(!partSorting)
                    {
                        p=i;
                        s=2*p;
                        partSorting = true;
                    }
                    if (s<=n && partSorting)
                    {
                        if(s<n && hd[s+1].N>=hd[s].N && s%2==0)    s++;
                        if(hd[p].N>=hd[s].N)
                        {
                            time = 999;
                        }
                        //바꾸는 과정. 여기서가 중요하다!
                        if(time==30)
                        {
                            hd[p].selected();   hd[s].selected();
                        }
                        
                        if(time==50)
                        {
                            swapHd(s, p);
                            hd[p].setLocation(x[p], y[p]);
                            hd[s].setLocation(x[s], y[s]);
                            hd[p].changed();    
                            hd[s].changed();
                            p=s;    s=2*p;
                            time = 0;
                        }
                        
                    }
                    if(s>n || time == 999)
                    {
                        i--;
                        partSorting = false;
                        time = 0;
                    }
                }
                else if(i<=0)
                {
                    sortComplete = true;
                    delay();
                    i=1;
                }
            }
            else
            {
                time = 0;
                if(sortComplete && HeapSorting)
                {
                    HeapSortCan = true;
                }
                else if(sortComplete && ddd!=-1 && !HeapSortComplete)
                {
                    delay();
                }
                else if(ddd==-1)    ConverseCan = true;
                else if(HeapSortComplete)
                {
                    JOptionPane.showMessageDialog(null, "MAX힙 정렬 완료!");
                    HeapSortComplete = false;
                    HeapSortCompleted = false;
                    ddd = -1;
                }
            }
    }
    
    public void init()//shift down용
    {
        sortComplete = false;
        SHIFTDOWN_Sort = false;
        SHIFTUP_Sort = false;
        HeapSorting = false;
        HeapSortComplete = false;
        HeapSortCompleted = false;
        HeapSortCan = false;
        Ne = inputLIMIT;
        time2 = -1;
        partSorting = false;
        iInit = false;
        ddd = 0;
        s = p = 0;
        time = 0;
        int[] aaa = new int[save.length];
        for(i=0;i<aaa.length;i++)   aaa[i] = 1;
        for(i=1;i<hd.length;i++)
        {
            if(aaa[i] == 0) continue;
            int j = Greenfoot.getRandomNumber(hd.length-1)+1;
            if(aaa[j] == 1)
            {
                swapHd(i, j);
                aaa[j] = 0;
                aaa[i] = 0;
            }
            else
            {
                int jamkan = 0;
                for(int kk=0;kk<aaa.length;kk++)    jamkan+=aaa[kk];
                if(jamkan!=0)   i--;
                else    break;
            }
        }
        for(i=1;i<hd.length;i++)
        {
            if(hd[i].select)
            {
                hd[i].printNum();
            }
            hd[i].setLocation(x[i], y[i]);
        }
    }
    
    private void swapHd(int a, int b)
    {
        HeapData temp;
        temp = hd[a];
        hd[a] = hd[b];
        hd[b] = temp;
    }
}
