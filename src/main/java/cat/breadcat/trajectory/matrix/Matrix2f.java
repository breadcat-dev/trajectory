package cat.breadcat.trajectory.matrix;

import cat.breadcat.toolbox.exception.DivisionByZeroException;
import cat.breadcat.toolbox.util.MathUtils;
import cat.breadcat.trajectory.vector.Vector2f;

public final class Matrix2f
{
    private static final float EPSILON = 1e-12f;

    public static final Matrix2f ZERO = new Matrix2f(
            0, 0,
            0, 0
    );
    public static final Matrix2f IDENTITY = new Matrix2f(
            1, 0,
            0, 1
    );

    public final float m00, m01;
    public final float m10, m11;

    public Matrix2f(
            float m00, float m01,
            float m10, float m11
    )
    {
        this.m00 = m00;
        this.m01 = m01;

        this.m10 = m10;
        this.m11 = m11;
    }


    private static float sumProducts(
            float a1, float b1,
            float a2, float b2
    )
    {
        return a1 * b1 + a2 * b2;
    }


    public Matrix2f add(Matrix2f other)
    {
        return new Matrix2f(
                m00 + other.m00, m01 + other.m01,
                m10 + other.m10, m11 + other.m11
        );
    }
    public Matrix2f subtract(Matrix2f other)
    {
        return new Matrix2f(
                m00 - other.m00, m01 - other.m01,
                m10 - other.m10, m11 - other.m11
        );
    }
    public Matrix2f multiply(float other)
    {
        return new Matrix2f(
                m00 * other, m01 * other,
                m10 * other, m11 * other
        );
    }
    public Matrix2f multiply(Matrix2f other)
    {
        return new Matrix2f(
                sumProducts(m00, m01, other.m00, other.m10), sumProducts(m00, m01, other.m01, other.m11),
                sumProducts(m10, m11, other.m00, other.m10), sumProducts(m10, m11, other.m01, other.m11)
        );
    }
    public Vector2f multiply(Vector2f other)
    {
        return new Vector2f(
                sumProducts(m00, m01, other.x, other.y),
                sumProducts(m10, m11, other.x, other.y)
        );
    }
    public Matrix2f divide(float other)
    {
        if(Float.compare(other, 0.0f) == 0)
            throw new DivisionByZeroException("other");

        return new Matrix2f(
                m00 / other, m01 / other,
                m10 / other, m11 / other
        );
    }
    public Matrix2f divide(Matrix2f other)
    {
        return multiply(other.inverse());
    }

    public float determinant()
    {
        return m00 * m11 -
                m01 * m10;
    }

    public Matrix2f negate()
    {
        return new Matrix2f(
                -m00, -m01,
                -m10, -m11
        );
    }
    public Matrix2f inverse()
    {
        float determinant = determinant();

        if(MathUtils.approximatelyZero(determinant))
            throw new DivisionByZeroException("determinant");


        return new Matrix2f(
                m11, -m01,
                -m10, m00
        ).divide(determinant);
    }
    public Matrix2f transpose()
    {
        return new Matrix2f(m00, m10, m01, m11);
    }

    @Override
    public String toString()
    {
        return "Matrix2f [\n"
                + "    [" + m00 + " " + m01 + "]\n"
                + "    [" + m10 + " " + m11 + "]\n"
                + "]";
    }
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (!(obj instanceof Matrix2f other)) return false;

        return Float.compare(m00, other.m00) == 0 && Float.compare(m01, other.m01) == 0 &&
                Float.compare(m10, other.m10) == 0 && Float.compare(m11, other.m11) == 0;
    }
    @Override
    public int hashCode()
    {
        int result = Float.hashCode(m00);
        result = 31 * result + Float.hashCode(m01);

        result = 31 * result + Float.hashCode(m10);
        result = 31 * result + Float.hashCode(m11);

        return result;
    }
}
