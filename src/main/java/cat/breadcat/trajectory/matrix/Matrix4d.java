package cat.breadcat.trajectory.matrix;

import cat.breadcat.toolbox.util.MathUtil;
import cat.breadcat.trajectory.exception.DivisionByZeroException;
import cat.breadcat.trajectory.vector.Vector4d;

public final class Matrix4d
{
    private static final double EPSILON = 1e-12;

    public static final Matrix4d ZERO = new Matrix4d(
            0, 0, 0, 0,
            0, 0, 0, 0,
            0, 0, 0, 0,
            0, 0, 0, 0
    );
    public static final Matrix4d IDENTITY = new Matrix4d(
            1, 0, 0, 0,
            0, 1, 0, 0,
            0, 0, 1, 0,
            0, 0, 0, 1
    );

    public final double m00, m01, m02, m03;
    public final double m10, m11, m12, m13;
    public final double m20, m21, m22, m23;
    public final double m30, m31, m32, m33;

    public Matrix4d(
            double m00, double m01, double m02, double m03,
            double m10, double m11, double m12, double m13,
            double m20, double m21, double m22, double m23,
            double m30, double m31, double m32, double m33
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


    private static double sumProducts(
            double a1, double a2, double a3, double a4,
            double b1, double b2, double b3, double b4
    )
    {
        return a1 * b1 + a2 * b2 + a3 * b3 + a4 * b4;
    }

    public Matrix4d add(Matrix4d other)
    {
        return new Matrix4d(
                m00 + other.m00, m01 + other.m01, m02 + other.m02, m03 + other.m03,
                m10 + other.m10, m11 + other.m11, m12 + other.m12, m13 + other.m13,
                m20 + other.m20, m21 + other.m21, m22 + other.m22, m23 + other.m23,
                m30 + other.m30, m31 + other.m31, m32 + other.m32, m33 + other.m33
        );
    }

    public Matrix4d subtract(Matrix4d other)
    {
        return new Matrix4d(
                m00 - other.m00, m01 - other.m01, m02 - other.m02, m03 - other.m03,
                m10 - other.m10, m11 - other.m11, m12 - other.m12, m13 - other.m13,
                m20 - other.m20, m21 - other.m21, m22 - other.m22, m23 - other.m23,
                m30 - other.m30, m31 - other.m31, m32 - other.m32, m33 - other.m33
        );
    }

    public Matrix4d multiply(double other)
    {
        return new Matrix4d(
                m00 * other, m01 * other, m02 * other, m03 * other,
                m10 * other, m11 * other, m12 * other, m13 * other,
                m20 * other, m21 * other, m22 * other, m23 * other,
                m30 * other, m31 * other, m32 * other, m33 * other
        );
    }

    public Vector4d multiply(Vector4d other)
    {
        return new Vector4d(
                sumProducts(m00, m01, m02, m03, other.x, other.y, other.z, other.w),
                sumProducts(m10, m11, m12, m13, other.x, other.y, other.z, other.w),
                sumProducts(m20, m21, m22, m23, other.x, other.y, other.z, other.w),
                sumProducts(m30, m31, m32, m33, other.x, other.y, other.z, other.w)
        );
    }

    public Matrix4d multiply(Matrix4d other)
    {
        return new Matrix4d(
                sumProducts(m00, m01, m02, m03, other.m00, other.m10, other.m20, other.m30), sumProducts(m00, m01, m02, m03, other.m01, other.m11, other.m21, other.m31), sumProducts(m00, m01, m02, m03, other.m02, other.m12, other.m22, other.m32), sumProducts(m00, m01, m02, m03, other.m03, other.m13, other.m23, other.m33),
                sumProducts(m10, m11, m12, m13, other.m00, other.m10, other.m20, other.m30), sumProducts(m10, m11, m12, m13, other.m01, other.m11, other.m21, other.m31), sumProducts(m10, m11, m12, m13, other.m02, other.m12, other.m22, other.m32), sumProducts(m10, m11, m12, m13, other.m03, other.m13, other.m23, other.m33),
                sumProducts(m20, m21, m22, m23, other.m00, other.m10, other.m20, other.m30), sumProducts(m20, m21, m22, m23, other.m01, other.m11, other.m21, other.m31), sumProducts(m20, m21, m22, m23, other.m02, other.m12, other.m22, other.m32), sumProducts(m20, m21, m22, m23, other.m03, other.m13, other.m23, other.m33),
                sumProducts(m30, m31, m32, m33, other.m00, other.m10, other.m20, other.m30), sumProducts(m30, m31, m32, m33, other.m01, other.m11, other.m21, other.m31), sumProducts(m30, m31, m32, m33, other.m02, other.m12, other.m22, other.m32), sumProducts(m30, m31, m32, m33, other.m03, other.m13, other.m23, other.m33)
        );
    }

    public Matrix4d divide(double other)
    {
        if(Double.compare(other, 0.0) == 0)
            throw new DivisionByZeroException("other");

        return new Matrix4d(
                m00 / other, m01 / other, m02 / other, m03 / other,
                m10 / other, m11 / other, m12 / other, m13 / other,
                m20 / other, m21 / other, m22 / other, m23 / other,
                m30 / other, m31 / other, m32 / other, m33 / other
        );
    }

    public Matrix4d divide(Matrix4d other)
    {
        return multiply(other.inverse());
    }


    public double determinant()
    {
        return m00 * (m11 * (m22 * m33 - m23 * m32) - m12 * (m21 * m33 - m31 * m23) + m13 * (m21 * m32 - m31 * m22)) -
                m01 * (m10 * (m22 * m33 - m23 * m32) - m12 * (m20 * m33 - m30 * m23) + m13 * (m20 * m32 - m30 * m22)) +
                m02 * (m10 * (m21 * m33 - m23 * m31) - m11 * (m20 * m33 - m30 * m23) + m13 * (m20 * m31 - m30 * m21)) -
                m03 * (m10 * (m21 * m32 - m22 * m31) - m11 * (m20 * m32 - m30 * m22) + m12 * (m20 * m31 - m30 * m21));
    }


    public Matrix4d negate()
    {
        return new Matrix4d(
                -m00, -m01, -m02, -m03,
                -m10, -m11, -m12, -m13,
                -m20, -m21, -m22, -m23,
                -m30, -m31, -m32, -m33
        );
    }

    public Matrix4d inverse()
    {
        double determinant = determinant();

        if(MathUtil.approximatelyEqual(determinant, 0.0, EPSILON))
            throw new DivisionByZeroException("determinant");


        double c00 = m11 * m22 * m33 -
                m11 * m23 * m32 -
                m21 * m12 * m33 +
                m21 * m13 * m32 +
                m31 * m12 * m23 -
                m31 * m13 * m22;

        double c01 = -m01 * m22 * m33 +
                m01 * m23 * m32 +
                m21 * m02 * m33 -
                m21 * m03 * m32 -
                m31 * m02 * m23 +
                m31 * m03 * m22;

        double c02 = m01 * m12 * m33 -
                m01 * m13 * m32 -
                m11 * m02 * m33 +
                m11 * m03 * m32 +
                m31 * m02 * m13 -
                m31 * m03 * m12;

        double c03 = -m01 * m12 * m23 +
                m01 * m13 * m22 +
                m11 * m02 * m23 -
                m11 * m03 * m22 -
                m21 * m02 * m13 +
                m21 * m03 * m12;

        double c10 = -m10 * m22 * m33 +
                m10 * m23 * m32 +
                m20 * m12 * m33 -
                m20 * m13 * m32 -
                m30 * m12 * m23 +
                m30 * m13 * m22;

        double c11 = m00 * m22 * m33 -
                m00 * m23 * m32 -
                m20 * m02 * m33 +
                m20 * m03 * m32 +
                m30 * m02 * m23 -
                m30 * m03 * m22;

        double c12 = -m00 * m12 * m33 +
                m00 * m13 * m32 +
                m10 * m02 * m33 -
                m10 * m03 * m32 -
                m30 * m02 * m13 +
                m30 * m03 * m12;

        double c13 = m00 * m12 * m23 -
                m00 * m13 * m22 -
                m10 * m02 * m23 +
                m10 * m03 * m22 +
                m20 * m02 * m13 -
                m20 * m03 * m12;

        double c20 = m10 * m21 * m33 -
                m10 * m23 * m31 -
                m20 * m11 * m33 +
                m20 * m13 * m31 +
                m30 * m11 * m23 -
                m30 * m13 * m21;

        double c21 = -m00 * m21 * m33 +
                m00 * m23 * m31 +
                m20 * m01 * m33 -
                m20 * m03 * m31 -
                m30 * m01 * m23 +
                m30 * m03 * m21;

        double c22 = m00 * m11 * m33 -
                m00 * m13 * m31 -
                m10 * m01 * m33 +
                m10 * m03 * m31 +
                m30 * m01 * m13 -
                m30 * m03 * m11;

        double c23 = -m00 * m11 * m23 +
                m00 * m13 * m21 +
                m10 * m01 * m23 -
                m10 * m03 * m21 -
                m20 * m01 * m13 +
                m20 * m03 * m11;

        double c30 = -m10 * m21 * m32 +
                m10 * m22 * m31 +
                m20 * m11 * m32 -
                m20 * m12 * m31 -
                m30 * m11 * m22 +
                m30 * m12 * m21;

        double c31 = m00 * m21 * m32 -
                m00  * m22 * m31 -
                m20  * m01 * m32 +
                m20  * m02 * m31 +
                m30 * m01 * m22 -
                m30 * m02 * m21;

        double c32 = -m00  * m11 * m32 +
                m00 * m12 * m31 +
                m10 * m01 * m32 -
                m10 * m02 * m31 -
                m30 * m01 * m12 +
                m30 * m02 * m11;

        double c33 = m00 * m11 * m22 -
                m00 * m12 * m21 -
                m10 * m01 * m22 +
                m10 * m02 * m21 +
                m20 * m01 * m12 -
                m20 * m02 * m11;


        return new Matrix4d(
                c00, c01, c02, c03,
                c10, c11, c12, c13,
                c20, c21, c22, c23,
                c30, c31, c32, c33
        ).divide(determinant);
    }

    public Matrix4d transpose()
    {
        return new Matrix4d(
                m00, m10, m20, m30,
                m01, m11, m21, m31,
                m02, m12, m22, m32,
                m03, m13, m23, m33
        );
    }


    @Override
    public String toString()
    {
        return "Matrix4d [\n"
                + "    [" + m00 + " " + m01 + " " + m02 + " " + m03 + "]\n"
                + "    [" + m10 + " " + m11 + " " + m12 + " " + m13 + "]\n"
                + "    [" + m20 + " " + m21 + " " + m22 + " " + m23 + "]\n"
                + "    [" + m30 + " " + m31 + " " + m32 + " " + m33 + "]\n"
                + "]";
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (!(obj instanceof Matrix4d other)) return false;

        return Double.compare(m00, other.m00) == 0 && Double.compare(m01, other.m01) == 0 && Double.compare(m02, other.m02) == 0 && Double.compare(m03, other.m03) == 0
                && Double.compare(m10, other.m10) == 0 && Double.compare(m11, other.m11) == 0 && Double.compare(m12, other.m12) == 0 && Double.compare(m13, other.m13) == 0
                && Double.compare(m20, other.m20) == 0 && Double.compare(m21, other.m21) == 0 && Double.compare(m22, other.m22) == 0 && Double.compare(m23, other.m23) == 0
                && Double.compare(m30, other.m30) == 0 && Double.compare(m31, other.m31) == 0 && Double.compare(m32, other.m32) == 0 && Double.compare(m33, other.m33) == 0;
    }

    @Override
    public int hashCode()
    {
        int result = Double.hashCode(m00);
        result = 31 * result + Double.hashCode(m01);
        result = 31 * result + Double.hashCode(m02);
        result = 31 * result + Double.hashCode(m03);

        result = 31 * result + Double.hashCode(m10);
        result = 31 * result + Double.hashCode(m11);
        result = 31 * result + Double.hashCode(m12);
        result = 31 * result + Double.hashCode(m13);

        result = 31 * result + Double.hashCode(m20);
        result = 31 * result + Double.hashCode(m21);
        result = 31 * result + Double.hashCode(m22);
        result = 31 * result + Double.hashCode(m23);

        result = 31 * result + Double.hashCode(m30);
        result = 31 * result + Double.hashCode(m31);
        result = 31 * result + Double.hashCode(m32);
        result = 31 * result + Double.hashCode(m33);

        return result;
    }
}
