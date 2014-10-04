import java.lang.*;
public class Vector
{
    /**
     * 이 클래스는 수학적인 것도 담당한다.
     */
    public static final double PI=3.141592653589793258;
    
    //Greenfoot의 삼각함수와 실제 삼각함수는 방향 및 초기 위상이 다르기 때문에 return 값이 다름.
    //srot은 Set Ratation이며, 이는 rad 값으로 받고, rot은 Rotation이며 이는 호도법으로 받는다.
    //r은 원의 중심과 해당 점 사이의 거리이다.
    int dx, dy, num = 0;
    Vector vp, v1, v2; // p:parent
    
    boolean isNull = true;
    //boolean isLeft;
    
    public Vector()
    {
        dx = dy = 0;
    }
    
    public Vector(int x, int y)
    {
        dx = x;
        dy = y;
    }
    
    public Vector(Vector v)
    {
        dx = v.dx;
        dy = v.dy;
        num = v.num;
        isNull = v.isNull;
    }
    
    public void setVector(Vector v)
    {
        dx = v.dx;
        dy = v.dy;
    }
    
    static boolean isParellel(Vector a, Vector b)
    {
        if(isZeroVector(a) || isZeroVector(b) || (a.dx)*(b.dy) == 0 || (a.dy)*(b.dx) == 0)  return false;
        else    return (a.dx)*(b.dy) == (a.dy)*(b.dx);
    }
    
    static boolean isZeroVector(Vector v)
    {
        return v.dx==0 && v.dy==0;
    }
    
    static double cosX(double rot, double srot, double r)
    {
        double a, b;
        a = srot;
        b=PI*rot/180.0;
        return r*Math.cos(b+a);
    }
    
    static double sinY(double rot, double srot, double r)
    {
        double a, b;
        a = srot;
        b=PI*rot/180.0;
        return r*Math.sin(b+a);
    }
    
    static double square(double under, int exp)
    {
        double a=1.0;
        for(int i=1;i<=exp;i++)
        {
            a*=under;
        }
        if(exp==0 && under!=0)  a=1.0;
        else if(under==0)   a=0.0;
        return a;
    }
    
    static int square(int under, int exp)
    {
        int a = 1;
        for(int j=1;j<=exp;j++)
        {
            a*=under;
        }
        if(exp == 0 && under!=0)    a=1;
        else if(under == 0) a=0;
        return a;
        
    }
    
    static void addVectorContents(Vector v1, Vector v2)
    {
        // v1에 더한다.
        v1.dx = v1.dx + v2.dx;
        v1.dy = v1.dy + v2.dy;
    }
    
    static Vector add(Vector v1, Vector v2) // 합
    {
        return new Vector(v1.dx + v2.dx, v1.dy + v2.dy);
    }
    
    static Vector substraction(Vector v1, Vector v2) // 차
    {
        return new Vector(v2.dx - v1.dx, v2.dy - v1.dy);
    }
    
    static int innerProduct(Vector v1, Vector v2) // 내적
    {
        return ((v1.dx)*(v2.dx) + (v1.dy)*(v2.dy));
    }
}