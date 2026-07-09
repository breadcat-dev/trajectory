package cat.breadcat;

import cat.breadcat.trajectory.matrix.Matrix3d;
import cat.breadcat.trajectory.matrix.Matrix4d;

public class Main
{
    public static void main(String[] args)
    {
        Matrix4d m1 = new Matrix4d(
                0, 2, 1, 2,
                3, 0, 3, 4,
                5, 6, 0, 6,
                7, 8, 7, 0
        );

        Matrix4d m2 = Matrix4d.IDENTITY;
        Matrix4d m3 = m1.divide(m2);

        System.out.println(m3);
    }
}