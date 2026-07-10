package cat.breadcat.trajectory.vector;

import cat.breadcat.toolbox.constant.MathConstants;
import cat.breadcat.toolbox.exception.DivisionByZeroException;
import cat.breadcat.toolbox.util.MathUtils;
import cat.breadcat.trajectory.matrix.Matrix4d;

public final class Vector4d
{
    public static final Vector4d ZERO = new Vector4d(0, 0, 0, 0);
    public static final Vector4d UNIT = new Vector4d(1, 1, 1, 1);
    public static final Vector4d UNIT_X = new Vector4d(1, 0, 0, 0);
    public static final Vector4d UNIT_Y = new Vector4d(0, 1, 0, 0);
    public static final Vector4d UNIT_Z = new Vector4d(0, 0, 1, 0);
    public static final Vector4d UNIT_W = new Vector4d(0, 0, 0, 1);

    public final double x;
    public final double y;
    public final double z;
    public final double w;

    public Vector4d(double x, double y, double z, double w)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }


    private static double sumProducts(double a1, double a2, double a3, double a4,
                               double b1, double b2, double b3, double b4)
    {
        return a1 * b1 +
                a2 * b2 +
                a3 * b3 +
                a4 * b4;
    }

    private static boolean approximatelyEqual(
            double a1,
            double b1
    )
    {
        return MathUtils.approximatelyEqual(a1, b1, MathConstants.EPSILON);
    }


    public Vector4d add(Vector4d other)
    {
        return new Vector4d(
                x + other.x,
                y + other.y,
                z + other.z,
                w + other.w
        );
    }
    public Vector4d subtract(Vector4d other)
    {
        return new Vector4d(
                x - other.x,
                y - other.y,
                z - other.z,
                w - other.w
        );
    }
    public Vector4d multiply(double scalar)
    {
        return new Vector4d(
                x * scalar,
                y * scalar,
                z * scalar,
                w * scalar
        );
    }
    public Vector4d multiply(Vector4d other)
    {
        return new Vector4d(
                x * other.x,
                y * other.y,
                z * other.z,
                w * other.w
        );
    }
    public Vector4d divide(double scalar)
    {
        if(Double.compare(scalar, 0.0) == 0)
            throw new DivisionByZeroException("scalar");

        return new Vector4d(
                x / scalar,
                y / scalar,
                z / scalar,
                w / scalar
        );
    }
    public Vector4d divide(Vector4d other)
    {
        if(
                Double.compare(other.x, 0.0) == 0 &&
                Double.compare(other.y, 0.0) == 0 &&
                Double.compare(other.z, 0.0) == 0 &&
                Double.compare(other.w, 0.0) == 0
        )
            throw new DivisionByZeroException("other");

        return new Vector4d(
                x / other.x,
                y / other.y,
                z / other.z,
                w / other.w
        );
    }

    public double length()
    {
        return Math.sqrt(lengthSquared());
    }
    public double lengthSquared()
    {
        return sumProducts(x, y, z, w, x, y, z, w);
    }
    public double distance(Vector4d other)
    {
        return Math.sqrt(distanceSquared(other));
    }
    public double distanceSquared(Vector4d other)
    {
        double dx = x - other.x;
        double dy = y - other.y;
        double dz = z - other.z;
        double dt = w - other.w;

        return sumProducts(dx, dy, dz, dt, dx, dy, dz, dt);
    }

    public double dot(Vector4d other)
    {
        return sumProducts(x, y, z, w, other.x, other.y, other.z, other.w);
    }
    public Vector4d normalize()
    {
        double length = length();

        if(MathUtils.approximatelyZero(length))
            throw new DivisionByZeroException("length");

        return divide(length);
    }
    public Vector4d negate()
    {
        return new Vector4d(-x, -y, -z, -w);
    }
    public Vector4d lerp(Vector4d other, double interpolation)
    {
        return new Vector4d(
                MathUtils.lerp(x, other.x, interpolation),
                MathUtils.lerp(y, other.y, interpolation),
                MathUtils.lerp(z, other.z, interpolation),
                MathUtils.lerp(w, other.w, interpolation)
        );
    }

    public boolean approximatelyEqual(Vector4d other)
    {
        return approximatelyEqual(x, other.x) && approximatelyEqual(y, other.y) && approximatelyEqual(z, other.z) && approximatelyEqual(w, other.w);
    }

    @Override
    public String toString()
    {
        return String.format(
                """
                Vector4d(%+.6f, %+.6f, %+.6f, %+.6f)
                """,
                x, y, z, w
        );
    }
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (!(obj instanceof Vector4d other)) return false;

        return Double.compare(x, other.x) == 0 &&
                Double.compare(y, other.y) == 0 &&
                Double.compare(z, other.z) == 0 &&
                Double.compare(w, other.w) == 0;
    }
    @Override
    public int hashCode()
    {
        int result = Double.hashCode(x);
        result = 31 * result + Double.hashCode(y);
        result = 31 * result + Double.hashCode(z);
        result = 31 * result + Double.hashCode(w);
        return result;
    }
}
