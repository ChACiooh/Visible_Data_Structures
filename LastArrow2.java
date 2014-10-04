import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LastArrow2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LastArrow2 extends Arrow
{
    public static boolean D = false;
    /**
     * static변수를 활용함으로써, 애매한 이 객체를 적절히 조절할 수 있게 해 준다.
     * 인스턴스 초기화를 이용함으로써 처음부터 움직이는 것을 막았다.
     * decreaseWidth()를 오버라이딩했으며, 이때 길이 조절 여부 확인 변수를 다시 false로 바꾸지 않음으로써
     * 이전 객체의 조건 만족으로 인한 현재 객체의 이동 차단을 막았다.
     * 어차피 if문을 만족하여 act를 한 후엔 D가 true일지라도 아무런 작업을 하지 않는다.
     */
    { D = false; }
    public LastArrow2()
    {
        pro = true;
    }
    public void act() 
    {
        // Add your action code here.
        if(pro)
        {
            pro = !pro;
            No = sp/6;
            origin_Width = img.getWidth()/2;
            origin_X = getX();
        }
        decreaseWidth();
        if(ListEnded)
        {
            D = false;
            //getWorld().removeObject(this);
        }
    }    
    
    private void decreaseWidth()//크기를 줄이고 이동하는 메서드
    {
        if(D)
        {
            if(getX()>origin_X-25)  setLocation(getX()-5, getY());
            if(img.getWidth()>origin_Width)
            {
                img.scale(img.getWidth()/2, img.getHeight());
                setImage(img);
            }
        }
    }
}
