package cat.breadcat;

import cat.breadcat.trajectory.matrix.Matrix4d;

import java.util.Random;

public class Main
{
    public static void main(String[] args)
    {
        Random random = new Random(69420);

        for (int i = 0; i < 10_000; i++)
        {
            Matrix4d mat = new Matrix4d(
                    random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble(),
                    random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble(),
                    random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble(),
                    random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble()
            );

            Matrix4d inv = mat.inverse();
            Matrix4d mul = mat.multiply(inv);

            if(!mul.approximatelyEqual(Matrix4d.IDENTITY))
            {
                System.out.println(mat);
                System.out.println(inv);
                System.out.println(mul);

                throw new RuntimeException("CRASH");
            }
        }

        System.out.println("SUCCESS");
    }
}