package cat.breadcat.trajectory.matrix;

import cat.breadcat.toolbox.constant.MathConstants;
import cat.breadcat.toolbox.exception.DivisionByZeroException;
import cat.breadcat.toolbox.util.MathUtils;
import cat.breadcat.trajectory.vector.Vector4f;

public final class Matrix4f
{
    public static final Matrix4f ZERO = new Matrix4f(
            0, 0, 0, 0,
            0, 0, 0, 0,
            0, 0, 0, 0,
            0, 0, 0, 0
    );
    public static final Matrix4f IDENTITY = new Matrix4f(
            1, 0, 0, 0,
            0, 1, 0, 0,
            0, 0, 1, 0,
            0, 0, 0, 1
    );

    public final float m00, m01, m02, m03;
    public final float m10, m11, m12, m13;
    public final float m20, m21, m22, m23;
    public final float m30, m31, m32, m33;

    public Matrix4f(
            float m00, float m01, float m02, float m03,
            float m10, float m11, float m12, float m13,
            float m20, float m21, float m22, float m23,
            float m30, float m31, float m32, float m33
    )
    {
        this.m00 = m00;
        this.m01 = m01;
        this.m02 = m02;
        this.m03 = m03;

        this.m10 = m10;
        this.m11 = m11;
        this.m12 = m12;
        this.m13 = m13;

        this.m20 = m20;
        this.m21 = m21;
        this.m22 = m22;
        this.m23 = m23;
        
        this.m30 = m30;
        this.m31 = m31;
        this.m32 = m32;
        this.m33 = m33;
    }


    private static float sumProducts(
            float a1, float a2, float a3, float a4,
            float b1, float b2, float b3, float b4
    )
    {
        return a1 * b1 + a2 * b2 + a3 * b3 + a4 * b4;
    }

    private static boolean approximatelyEqual(
            float a1,
            float b1
    )
    {
        return MathUtils.approximatelyEqual(a1, b1, MathConstants.EPSILON_F);
    }


    public Matrix4f add(Matrix4f other)
    {
        return new Matrix4f(
                m00 + other.m00, m01 + other.m01, m02 + other.m02, m03 + other.m03,
                m10 + other.m10, m11 + other.m11, m12 + other.m12, m13 + other.m13,
                m20 + other.m20, m21 + other.m21, m22 + other.m22, m23 + other.m23,
                m30 + other.m30, m31 + other.m31, m32 + other.m32, m33 + other.m33
        );
    }
    public Matrix4f subtract(Matrix4f other)
    {
        return new Matrix4f(
                m00 - other.m00, m01 - other.m01, m02 - other.m02, m03 - other.m03,
                m10 - other.m10, m11 - other.m11, m12 - other.m12, m13 - other.m13,
                m20 - other.m20, m21 - other.m21, m22 - other.m22, m23 - other.m23,
                m30 - other.m30, m31 - other.m31, m32 - other.m32, m33 - other.m33
        );
    }
    public Matrix4f multiply(float scalar)
    {
        return new Matrix4f(
                m00 * scalar, m01 * scalar, m02 * scalar, m03 * scalar,
                m10 * scalar, m11 * scalar, m12 * scalar, m13 * scalar,
                m20 * scalar, m21 * scalar, m22 * scalar, m23 * scalar,
                m30 * scalar, m31 * scalar, m32 * scalar, m33 * scalar
        );
    }
    public Vector4f multiply(Vector4f other)
    {
        return new Vector4f(
                sumProducts(m00, m01, m02, m03, other.x, other.y, other.z, other.w),
                sumProducts(m10, m11, m12, m13, other.x, other.y, other.z, other.w),
                sumProducts(m20, m21, m22, m23, other.x, other.y, other.z, other.w),
                sumProducts(m30, m31, m32, m33, other.x, other.y, other.z, other.w)
        );
    }
    public Matrix4f multiply(Matrix4f other)
    {
        return new Matrix4f(
                sumProducts(m00, m01, m02, m03, other.m00, other.m10, other.m20, other.m30), sumProducts(m00, m01, m02, m03, other.m01, other.m11, other.m21, other.m31), sumProducts(m00, m01, m02, m03, other.m02, other.m12, other.m22, other.m32), sumProducts(m00, m01, m02, m03, other.m03, other.m13, other.m23, other.m33),
                sumProducts(m10, m11, m12, m13, other.m00, other.m10, other.m20, other.m30), sumProducts(m10, m11, m12, m13, other.m01, other.m11, other.m21, other.m31), sumProducts(m10, m11, m12, m13, other.m02, other.m12, other.m22, other.m32), sumProducts(m10, m11, m12, m13, other.m03, other.m13, other.m23, other.m33),
                sumProducts(m20, m21, m22, m23, other.m00, other.m10, other.m20, other.m30), sumProducts(m20, m21, m22, m23, other.m01, other.m11, other.m21, other.m31), sumProducts(m20, m21, m22, m23, other.m02, other.m12, other.m22, other.m32), sumProducts(m20, m21, m22, m23, other.m03, other.m13, other.m23, other.m33),
                sumProducts(m30, m31, m32, m33, other.m00, other.m10, other.m20, other.m30), sumProducts(m30, m31, m32, m33, other.m01, other.m11, other.m21, other.m31), sumProducts(m30, m31, m32, m33, other.m02, other.m12, other.m22, other.m32), sumProducts(m30, m31, m32, m33, other.m03, other.m13, other.m23, other.m33)
        );
    }
    public Matrix4f divide(float scalar)
    {
        if(Float.compare(scalar, 0.0f) == 0)
            throw new DivisionByZeroException("scalar");

        return new Matrix4f(
                m00 / scalar, m01 / scalar, m02 / scalar, m03 / scalar,
                m10 / scalar, m11 / scalar, m12 / scalar, m13 / scalar,
                m20 / scalar, m21 / scalar, m22 / scalar, m23 / scalar,
                m30 / scalar, m31 / scalar, m32 / scalar, m33 / scalar
        );
    }
    public Matrix4f divide(Matrix4f other)
    {
        return multiply(other.inverse());
    }

    public float determinant()
    {
        return m00 * (m11 * (m22 * m33 - m23 * m32) - m12 * (m21 * m33 - m31 * m23) + m13 * (m21 * m32 - m31 * m22)) -
                m01 * (m10 * (m22 * m33 - m23 * m32) - m12 * (m20 * m33 - m30 * m23) + m13 * (m20 * m32 - m30 * m22)) +
                m02 * (m10 * (m21 * m33 - m23 * m31) - m11 * (m20 * m33 - m30 * m23) + m13 * (m20 * m31 - m30 * m21)) -
                m03 * (m10 * (m21 * m32 - m22 * m31) - m11 * (m20 * m32 - m30 * m22) + m12 * (m20 * m31 - m30 * m21));
    }

    public Matrix4f negate()
    {
        return new Matrix4f(
                -m00, -m01, -m02, -m03,
                -m10, -m11, -m12, -m13,
                -m20, -m21, -m22, -m23,
                -m30, -m31, -m32, -m33
        );
    }
    public Matrix4f inverse()
    {
        float determinant = determinant();

        if(MathUtils.approximatelyZero(determinant))
            throw new DivisionByZeroException("determinant");


        float c00 = m11 * m22 * m33 -
                m11 * m23 * m32 -
                m21 * m12 * m33 +
                m21 * m13 * m32 +
                m31 * m12 * m23 -
                m31 * m13 * m22;

        float c01 = -m01 * m22 * m33 +
                m01 * m23 * m32 +
                m21 * m02 * m33 -
                m21 * m03 * m32 -
                m31 * m02 * m23 +
                m31 * m03 * m22;

        float c02 = m01 * m12 * m33 -
                m01 * m13 * m32 -
                m11 * m02 * m33 +
                m11 * m03 * m32 +
                m31 * m02 * m13 -
                m31 * m03 * m12;

        float c03 = -m01 * m12 * m23 +
                m01 * m13 * m22 +
                m11 * m02 * m23 -
                m11 * m03 * m22 -
                m21 * m02 * m13 +
                m21 * m03 * m12;

        float c10 = -m10 * m22 * m33 +
                m10 * m23 * m32 +
                m20 * m12 * m33 -
                m20 * m13 * m32 -
                m30 * m12 * m23 +
                m30 * m13 * m22;

        float c11 = m00 * m22 * m33 -
                m00 * m23 * m32 -
                m20 * m02 * m33 +
                m20 * m03 * m32 +
                m30 * m02 * m23 -
                m30 * m03 * m22;

        float c12 = -m00 * m12 * m33 +
                m00 * m13 * m32 +
                m10 * m02 * m33 -
                m10 * m03 * m32 -
                m30 * m02 * m13 +
                m30 * m03 * m12;

        float c13 = m00 * m12 * m23 -
                m00 * m13 * m22 -
                m10 * m02 * m23 +
                m10 * m03 * m22 +
                m20 * m02 * m13 -
                m20 * m03 * m12;

        float c20 = m10 * m21 * m33 -
                m10 * m23 * m31 -
                m20 * m11 * m33 +
                m20 * m13 * m31 +
                m30 * m11 * m23 -
                m30 * m13 * m21;

        float c21 = -m00 * m21 * m33 +
                m00 * m23 * m31 +
                m20 * m01 * m33 -
                m20 * m03 * m31 -
                m30 * m01 * m23 +
                m30 * m03 * m21;

        float c22 = m00 * m11 * m33 -
                m00 * m13 * m31 -
                m10 * m01 * m33 +
                m10 * m03 * m31 +
                m30 * m01 * m13 -
                m30 * m03 * m11;

        float c23 = -m00 * m11 * m23 +
                m00 * m13 * m21 +
                m10 * m01 * m23 -
                m10 * m03 * m21 -
                m20 * m01 * m13 +
                m20 * m03 * m11;

        float c30 = -m10 * m21 * m32 +
                m10 * m22 * m31 +
                m20 * m11 * m32 -
                m20 * m12 * m31 -
                m30 * m11 * m22 +
                m30 * m12 * m21;

        float c31 = m00 * m21 * m32 -
                m00  * m22 * m31 -
                m20  * m01 * m32 +
                m20  * m02 * m31 +
                m30 * m01 * m22 -
                m30 * m02 * m21;

        float c32 = -m00  * m11 * m32 +
                m00 * m12 * m31 +
                m10 * m01 * m32 -
                m10 * m02 * m31 -
                m30 * m01 * m12 +
                m30 * m02 * m11;

        float c33 = m00 * m11 * m22 -
                m00 * m12 * m21 -
                m10 * m01 * m22 +
                m10 * m02 * m21 +
                m20 * m01 * m12 -
                m20 * m02 * m11;


        return new Matrix4f(
                c00, c01, c02, c03,
                c10, c11, c12, c13,
                c20, c21, c22, c23,
                c30, c31, c32, c33
        ).divide(determinant);
    }
    public Matrix4f transpose()
    {
        return new Matrix4f(
                m00, m10, m20, m30,
                m01, m11, m21, m31,
                m02, m12, m22, m32,
                m03, m13, m23, m33
        );
    }

    public boolean approximatelyEqual(Matrix4f other)
    {
        return
                approximatelyEqual(m00, other.m00) && approximatelyEqual(m01, other.m01) && approximatelyEqual(m02, other.m02) && approximatelyEqual(m03, other.m03) &&
                approximatelyEqual(m10, other.m10) && approximatelyEqual(m11, other.m11) && approximatelyEqual(m12, other.m12) && approximatelyEqual(m13, other.m13) &&
                approximatelyEqual(m20, other.m20) && approximatelyEqual(m21, other.m21) && approximatelyEqual(m22, other.m22) && approximatelyEqual(m23, other.m23) &&
                approximatelyEqual(m30, other.m30) && approximatelyEqual(m31, other.m31) && approximatelyEqual(m32, other.m32) && approximatelyEqual(m33, other.m33);
    }

    @Override
    public String toString()
    {
        return String.format(
                """
                Matrix4f
                [
                    [%+.6f, %+.6f, %+.6f, %+.6f]
                    [%+.6f, %+.6f, %+.6f, %+.6f]
                    [%+.6f, %+.6f, %+.6f, %+.6f]
                    [%+.6f, %+.6f, %+.6f, %+.6f]
                ]
                """,
                m00, m01, m02, m03,
                m10, m11, m12, m13,
                m20, m21, m22, m23,
                m30, m31, m32, m33
        );
    }
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (!(obj instanceof Matrix4f other)) return false;

        return
                Float.compare(m00, other.m00) == 0 && Float.compare(m01, other.m01) == 0 && Float.compare(m02, other.m02) == 0 && Float.compare(m03, other.m03) == 0 && 
                Float.compare(m10, other.m10) == 0 && Float.compare(m11, other.m11) == 0 && Float.compare(m12, other.m12) == 0 && Float.compare(m13, other.m13) == 0 &&
                Float.compare(m20, other.m20) == 0 && Float.compare(m21, other.m21) == 0 && Float.compare(m22, other.m22) == 0 && Float.compare(m23, other.m23) == 0 &&
                Float.compare(m30, other.m30) == 0 && Float.compare(m31, other.m31) == 0 && Float.compare(m32, other.m32) == 0 && Float.compare(m33, other.m33) == 0;
    }
    @Override
    public int hashCode()
    {
        int result = Float.hashCode(m00);
        result = 31 * result + Float.hashCode(m01);
        result = 31 * result + Float.hashCode(m02);
        result = 31 * result + Float.hashCode(m03);

        result = 31 * result + Float.hashCode(m10);
        result = 31 * result + Float.hashCode(m11);
        result = 31 * result + Float.hashCode(m12);
        result = 31 * result + Float.hashCode(m13);

        result = 31 * result + Float.hashCode(m20);
        result = 31 * result + Float.hashCode(m21);
        result = 31 * result + Float.hashCode(m22);
        result = 31 * result + Float.hashCode(m23);

        result = 31 * result + Float.hashCode(m30);
        result = 31 * result + Float.hashCode(m31);
        result = 31 * result + Float.hashCode(m32);
        result = 31 * result + Float.hashCode(m33);

        return result;
    }
}
