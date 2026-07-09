package cat.breadcat.trajectory.matrix;

import cat.breadcat.toolbox.exception.DivisionByZeroException;
import cat.breadcat.toolbox.util.MathUtils;
import cat.breadcat.trajectory.vector.Vector3d;

public final class Matrix3d
{
    private static final double EPSILON = 1e-12;

    public static final Matrix3d ZERO = new Matrix3d(
            0, 0, 0,
            0, 0, 0,
            0, 0, 0
    );
    public static final Matrix3d IDENTITY = new Matrix3d(
            1, 0, 0,
            0, 1, 0,
            0, 0, 1
    );

    public final double m00, m01, m02;
    public final double m10, m11, m12;
    public final double m20, m21, m22;

    public Matrix3d(
            double m00, double m01, double m02,
            double m10, double m11, double m12,
            double m20, double m21, double m22
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


    private static double sumProducts(
            double a1, double a2, double a3,
            double b1, double b2, double b3
    )
    {
        return a1 * b1 + a2 * b2 + a3 * b3;
    }


    public Matrix3d add(Matrix3d other)
    {
        return new Matrix3d(
                m00 + other.m00, m01 + other.m01, m02 + other.m02,
                m10 + other.m10, m11 + other.m11, m12 + other.m12,
                m20 + other.m20, m21 + other.m21, m22 + other.m22
        );
    }
    public Matrix3d subtract(Matrix3d other)
    {
        return new Matrix3d(
                m00 - other.m00, m01 - other.m01, m02 - other.m02,
                m10 - other.m10, m11 - other.m11, m12 - other.m12,
                m20 - other.m20, m21 - other.m21, m22 - other.m22
        );
    }
    public Matrix3d multiply(double other)
    {
        return new Matrix3d(
                m00 * other, m01 * other, m02 * other,
                m10 * other, m11 * other, m12 * other,
                m20 * other, m21 * other, m22 * other
        );
    }
    public Vector3d multiply(Vector3d other)
    {
        return new Vector3d(
                sumProducts(m00, m01, m02, other.x, other.y, other.z),
                sumProducts(m10, m11, m12, other.x, other.y, other.z),
                sumProducts(m20, m21, m22, other.x, other.y, other.z)
        );
    }
    public Matrix3d multiply(Matrix3d other)
    {
        return new Matrix3d(
                sumProducts(m00, m01, m02, other.m00, other.m10, other.m20), sumProducts(m00, m01, m02, other.m01, other.m11, other.m21), sumProducts(m00, m01, m02, other.m02, other.m12, other.m22),
                sumProducts(m10, m11, m12, other.m00, other.m10, other.m20), sumProducts(m10, m11, m12, other.m01, other.m11, other.m21), sumProducts(m10, m11, m12, other.m02, other.m12, other.m22),
                sumProducts(m20, m21, m22, other.m00, other.m10, other.m20), sumProducts(m20, m21, m22, other.m01, other.m11, other.m21), sumProducts(m20, m21, m22, other.m02, other.m12, other.m22)
        );
    }
    public Matrix3d divide(double other)
    {
        if(Double.compare(other, 0.0) == 0)
            throw new DivisionByZeroException("other");

        return new Matrix3d(
                m00 / other, m01 / other, m02 / other,
                m10 / other, m11 / other, m12 / other,
                m20 / other, m21 / other, m22 / other
        );
    }
    public Matrix3d divide(Matrix3d other)
    {
        return multiply(other.inverse());
    }

    public double determinant()
    {
        return m00 * (m11 * m22 - m12 * m21) -
                m01 * (m10 * m22 - m20 * m12) +
                m02 * (m10 * m21 - m20 * m11);
    }

    public Matrix3d negate()
    {
        return new Matrix3d(
                -m00, -m01, -m02,
                -m10, -m11, -m12,
                -m20, -m21, -m22
        );
    }
    public Matrix3d inverse()
    {
        double determinant = determinant();

        if(MathUtils.approximatelyZero(determinant))
            throw new DivisionByZeroException("determinant");


        double c00 =  (m11 * m22 - m12 * m21);
        double c01 = -(m10 * m22 - m12 * m20);
        double c02 =  (m10 * m21 - m11 * m20);

        double c10 = -(m01 * m22 - m02 * m21);
        double c11 =  (m00 * m22 - m02 * m20);
        double c12 = -(m00 * m21 - m01 * m20);

        double c20 =  (m01 * m12 - m02 * m11);
        double c21 = -(m00 * m12 - m02 * m10);
        double c22 =  (m00 * m11 - m01 * m10);

        return new Matrix3d(
                c00, c10, c20,
                c01, c11, c21,
                c02, c12, c22
        ).divide(determinant);
    }
    public Matrix3d transpose()
    {
        return new Matrix3d(
                m00, m10, m20,
                m01, m11, m21,
                m02, m12, m22
        );
    }

    @Override
    public String toString()
    {
        return "Matrix3d [\n"
                + "    [" + m00 + " " + m01 + " " + m02 + "]\n"
                + "    [" + m10 + " " + m11 + " " + m12 + "]\n"
                + "    [" + m20 + " " + m21 + " " + m22 + "]\n"
                + "]";
    }
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (!(obj instanceof Matrix3d other)) return false;

        return Double.compare(m00, other.m00) == 0 && Double.compare(m01, other.m01) == 0 && Double.compare(m02, other.m02) == 0 &&
                Double.compare(m10, other.m10) == 0 && Double.compare(m11, other.m11) == 0 && Double.compare(m12, other.m12) == 0 &&
                Double.compare(m20, other.m20) == 0 && Double.compare(m21, other.m21) == 0 && Double.compare(m22, other.m22) == 0;
    }
    @Override
    public int hashCode()
    {
        int result = Double.hashCode(m00);
        result = 31 * result + Double.hashCode(m01);
        result = 31 * result + Double.hashCode(m02);

        result = 31 * result + Double.hashCode(m10);
        result = 31 * result + Double.hashCode(m11);
        result = 31 * result + Double.hashCode(m12);

        result = 31 * result + Double.hashCode(m20);
        result = 31 * result + Double.hashCode(m21);
        result = 31 * result + Double.hashCode(m22);

        return result;
    }
}
