public class Matrix  
{
    int a, b, c, d;
    public Matrix()
    {
        a=b=c=d=0;
    }
    
    public Matrix(int a11, int a12, int a21, int a22)
    {
        a = a11;
        b = a12;
        c = a21;
        d = a22;
    }
    
    public static Matrix add(Matrix m1, Matrix m2)
    {
        return new Matrix(m1.a+m2.a, m1.b+m2.b, m1.c+m2.c, m1.d+m2.d);
    }
    
    public static Matrix product(Matrix m1, Matrix m2)
    {
        int w, x, y, z;
        w = m1.a*m2.a + m1.b*m2.c;
        x = m1.a*m2.b + m1.b*m2.d;
        y = m1.c*m2.a + m1.d*m2.c;
        z = m1.c*m2.b + m1.d*m2.d;
        return new Matrix(w,x,y,z);
    }
    
    public static int determinant(Matrix m)
    {
        return m.a*m.d - m.b*m.c;
    }
}
