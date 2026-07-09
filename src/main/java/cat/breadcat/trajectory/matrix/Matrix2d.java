package cat.breadcat.trajectory.matrix;

import cat.breadcat.toolbox.util.MathUtil;
import cat.breadcat.trajectory.exception.DivisionByZeroException;
import cat.breadcat.trajectory.vector.Vector2d;

public final class Matrix2d
{
    private static final double EPSILON = 1e-12;

    public static final Matrix2d ZERO = new Matrix2d(
            0, 0,
            0, 0
    );
    public static final Matrix2d IDENTITY = new Matrix2d(
            1, 0,
            0, 1
    );

    public final double m00, m01;
    public final double m10, m11;

    public Matrix2d(
            double m00, double m01,
            double m10, double m11
    )
    {
        this.m00 = m00;
        this.m01 = m01;

        this.m10 = m10;
        this.m11 = m11;
    }


    private static double sumProducts(
            double a1, double a2,
            double b1, double b2
    )
    {
        return a1 * b1 + a2 * b2;
    }


    public Matrix2d add(Matrix2d other)
    {
        return new Matrix2d(
                m00 + other.m00, m01 + other.m01,
                m10 + other.m10, m11 + other.m11
        );
    }

    public Matrix2d subtract(Matrix2d other)
    {
        return new Matrix2d(
                m00 - other.m00, m01 - other.m01,
                m10 - other.m10, m11 - other.m11
        );
    }

    public Matrix2d multiply(double other)
    {
        return new Matrix2d(
                m00 * other, m01 * other,
                m10 * other, m11 * other
        );
    }

    public Matrix2d multiply(Matrix2d other)
    {
        return new Matrix2d(
                sumProducts(m00, m01, other.m00, other.m10), sumProducts(m00, m01, other.m01, other.m11),
                sumProducts(m10, m11, other.m00, other.m10), sumProducts(m10, m11, other.m01, other.m11)
        );
    }

    public Vector2d multiply(Vector2d other)
    {
        return new Vector2d(
                sumProducts(m00, m01, other.x, other.y),
                sumProducts(m10, m11, other.x, other.y)
        );
    }

    public Matrix2d divide(double other)
    {
        if(Double.compare(other, 0.0) == 0)
            throw new DivisionByZeroException("other");

        return new Matrix2d(
                m00 / other, m01 / other,
                m10 / other, m11 / other
        );
    }

    public Matrix2d divide(Matrix2d other)
    {
        return multiply(other.inverse());
    }


    public double determinant()
    {
        return m00 * m11 -
                m01 * m10;
    }


    public Matrix2d negate()
    {
        return new Matrix2d(
                -m00, -m01,
                -m10, -m11
        );
    }

    public Matrix2d inverse()
    {
        double determinant = determinant();

        if(MathUtil.approximatelyEqual(determinant, 0.0, EPSILON))
            throw new DivisionByZeroException("determinant");


        return new Matrix2d(
                m11, -m01,
                -m10, m00
        ).divide(determinant);
    }

    public Matrix2d transpose()
    {
        return new Matrix2d(m00, m10, m01, m11);
    }


    @Override
    public String toString()
    {
        return "Matrix2d [\n"
                + "    [" + m00 + " " + m01 + "]\n"
                + "    [" + m10 + " " + m11 + "]\n"
                + "]";
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (!(obj instanceof Matrix2d other)) return false;

        return Double.compare(m00, other.m00) == 0 && Double.compare(m01, other.m01) == 0 &&
                Double.compare(m10, other.m10) == 0 && Double.compare(m11, other.m11) == 0;
    }

    @Override
    public int hashCode()
    {
        int result = Double.hashCode(m00);
        result = 31 * result + Double.hashCode(m01);

        result = 31 * result + Double.hashCode(m10);
        result = 31 * result + Double.hashCode(m11);

        return result;
    }
}