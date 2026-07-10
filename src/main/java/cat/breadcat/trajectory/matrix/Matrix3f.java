package cat.breadcat.trajectory.matrix;

import cat.breadcat.toolbox.constant.MathConstants;
import cat.breadcat.toolbox.exception.DivisionByZeroException;
import cat.breadcat.toolbox.util.MathUtils;
import cat.breadcat.trajectory.vector.Vector3f;

public final class Matrix3f
{
    public static final Matrix3f ZERO = new Matrix3f(
            0, 0, 0,
            0, 0, 0,
            0, 0, 0
    );
    public static final Matrix3f IDENTITY = new Matrix3f(
            1, 0, 0,
            0, 1, 0,
            0, 0, 1
    );

    public final float m00, m01, m02;
    public final float m10, m11, m12;
    public final float m20, m21, m22;

    public Matrix3f(
            float m00, float m01, float m02,
            float m10, float m11, float m12,
            float m20, float m21, float m22
    )
    {
        this.m00 = m00;
        this.m01 = m01;
        this.m02 = m02;

        this.m10 = m10;
        this.m11 = m11;
        this.m12 = m12;

        this.m20 = m20;
        this.m21 = m21;
        this.m22 = m22;
    }


    private static float sumProducts(
            float a1, float a2, float a3,
            float b1, float b2, float b3
    )
    {
        return a1 * b1 + a2 * b2 + a3 * b3;
    }

    private static boolean approximatelyEqual(
            float a1,
            float b1
    )
    {
        return MathUtils.approximatelyEqual(a1, b1, MathConstants.EPSILON_F);
    }


    public Matrix3f add(Matrix3f other)
    {
        return new Matrix3f(
                m00 + other.m00, m01 + other.m01, m02 + other.m02,
                m10 + other.m10, m11 + other.m11, m12 + other.m12,
                m20 + other.m20, m21 + other.m21, m22 + other.m22
        );
    }
    public Matrix3f subtract(Matrix3f other)
    {
        return new Matrix3f(
                m00 - other.m00, m01 - other.m01, m02 - other.m02,
                m10 - other.m10, m11 - other.m11, m12 - other.m12,
                m20 - other.m20, m21 - other.m21, m22 - other.m22
        );
    }
    public Matrix3f multiply(float other)
    {
        return new Matrix3f(
                m00 * other, m01 * other, m02 * other,
                m10 * other, m11 * other, m12 * other,
                m20 * other, m21 * other, m22 * other
        );
    }
    public Vector3f multiply(Vector3f other)
    {
        return new Vector3f(
                sumProducts(m00, m01, m02, other.x, other.y, other.z),
                sumProducts(m10, m11, m12, other.x, other.y, other.z),
                sumProducts(m20, m21, m22, other.x, other.y, other.z)
        );
    }
    public Matrix3f multiply(Matrix3f other)
    {
        return new Matrix3f(
                sumProducts(m00, m01, m02, other.m00, other.m10, other.m20), sumProducts(m00, m01, m02, other.m01, other.m11, other.m21), sumProducts(m00, m01, m02, other.m02, other.m12, other.m22),
                sumProducts(m10, m11, m12, other.m00, other.m10, other.m20), sumProducts(m10, m11, m12, other.m01, other.m11, other.m21), sumProducts(m10, m11, m12, other.m02, other.m12, other.m22),
                sumProducts(m20, m21, m22, other.m00, other.m10, other.m20), sumProducts(m20, m21, m22, other.m01, other.m11, other.m21), sumProducts(m20, m21, m22, other.m02, other.m12, other.m22)
        );
    }
    public Matrix3f divide(float other)
    {
        if(Float.compare(other, 0.0f) == 0)
            throw new DivisionByZeroException("other");

        return new Matrix3f(
                m00 / other, m01 / other, m02 / other,
                m10 / other, m11 / other, m12 / other,
                m20 / other, m21 / other, m22 / other
        );
    }
    public Matrix3f divide(Matrix3f other)
    {
        return multiply(other.inverse());
    }

    public float determinant()
    {
        return m00 * (m11 * m22 - m12 * m21) -
                m01 * (m10 * m22 - m20 * m12) +
                m02 * (m10 * m21 - m20 * m11);
    }

    public Matrix3f negate()
    {
        return new Matrix3f(
                -m00, -m01, -m02,
                -m10, -m11, -m12,
                -m20, -m21, -m22
        );
    }
    public Matrix3f inverse()
    {
        float determinant = determinant();

        if(MathUtils.approximatelyZero(determinant))
            throw new DivisionByZeroException("determinant");


        float c00 =  (m11 * m22 - m12 * m21);
        float c01 = -(m10 * m22 - m12 * m20);
        float c02 =  (m10 * m21 - m11 * m20);

        float c10 = -(m01 * m22 - m02 * m21);
        float c11 =  (m00 * m22 - m02 * m20);
        float c12 = -(m00 * m21 - m01 * m20);

        float c20 =  (m01 * m12 - m02 * m11);
        float c21 = -(m00 * m12 - m02 * m10);
        float c22 =  (m00 * m11 - m01 * m10);

        return new Matrix3f(
                c00, c10, c20,
                c01, c11, c21,
                c02, c12, c22
        ).divide(determinant);
    }
    public Matrix3f transpose()
    {
        return new Matrix3f(
                m00, m10, m20,
                m01, m11, m21,
                m02, m12, m22
        );
    }

    public boolean approximatelyEqual(Matrix3f other)
    {
        return
                approximatelyEqual(m00, other.m00) && approximatelyEqual(m01, other.m01) && approximatelyEqual(m02, other.m02) &&
                approximatelyEqual(m10, other.m10) && approximatelyEqual(m11, other.m11) && approximatelyEqual(m12, other.m12) &&
                approximatelyEqual(m20, other.m20) && approximatelyEqual(m21, other.m21) && approximatelyEqual(m22, other.m22);
    }

    @Override
    public String toString()
    {
        return String.format(
                """
                Matrix3f
                [
                    [%+.6f, %+.6f, %+.6f]
                    [%+.6f, %+.6f, %+.6f]
                    [%+.6f, %+.6f, %+.6f]
                ]
                """,
                m00, m01, m02,
                m10, m11, m12,
                m20, m21, m22
        );
    }
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (!(obj instanceof Matrix3f other)) return false;

        return
                Float.compare(m00, other.m00) == 0 && Float.compare(m01, other.m01) == 0 && Float.compare(m02, other.m02) == 0 &&
                Float.compare(m10, other.m10) == 0 && Float.compare(m11, other.m11) == 0 && Float.compare(m12, other.m12) == 0 &&
                Float.compare(m20, other.m20) == 0 && Float.compare(m21, other.m21) == 0 && Float.compare(m22, other.m22) == 0;
    }
    @Override
    public int hashCode()
    {
        int result = Float.hashCode(m00);
        result = 31 * result + Float.hashCode(m01);
        result = 31 * result + Float.hashCode(m02);

        result = 31 * result + Float.hashCode(m10);
        result = 31 * result + Float.hashCode(m11);
        result = 31 * result + Float.hashCode(m12);

        result = 31 * result + Float.hashCode(m20);
        result = 31 * result + Float.hashCode(m21);
        result = 31 * result + Float.hashCode(m22);

        return result;
    }
}
