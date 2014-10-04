import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * 얘가 생성되었다는 것은, LastArrow2.class 또한 생성되어야 함을 말한다.
 * 41까지의 자연수 중 6으로 나누어 떨어지는 것은 모두 6개이므로
 * 이 클래스를 통해 LastArrow2 객체를 6개만 만들면 된다.
 */
public class LastArrow1 extends Arrow
{

    LastArrow1(int SP)
    {
        No = SP;
        pro = true;
    }
    public void act() 
    {
        // Add your action code here.
        if(pro)
        {
            pro = !pro;
            LA2[No/6 - 1] = new LastArrow2();
            getWorld().addObject(LA2[No/6-1], 32, getY()+17);
        }
        if(ListEnded)
        {
            No = 0;
            for(int i =0;i<LA2.length;i++)
            {
                getWorld().removeObject(LA2[i]);
                LA2[i] = null;
            }
            getWorld().removeObject(this);
        }
    }    
    
    public void removeCurbArrow()
    {
        getWorld().removeObject(LA2[No/6-1]);
        LA2[No/6-1] = null;
        getWorld().removeObject(this);
    }
}
