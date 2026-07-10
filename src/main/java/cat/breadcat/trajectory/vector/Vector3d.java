package cat.breadcat.trajectory.vector;

import cat.breadcat.toolbox.constant.MathConstants;
import cat.breadcat.toolbox.exception.DivisionByZeroException;
import cat.breadcat.toolbox.util.MathUtils;

public final class Vector3d
{
    public static final Vector3d ZERO = new Vector3d(0, 0, 0);
    public static final Vector3d UNIT = new Vector3d(1, 1, 1);
    public static final Vector3d UNIT_X = new Vector3d(1, 0, 0);
    public static final Vector3d UNIT_Y = new Vector3d(0, 1, 0);
    public static final Vector3d UNIT_Z = new Vector3d(0, 0, 1);

    public final double x;
    public final double y;
    public final double z;

    public Vector3d(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }


    private static double sumProducts(double a1, double a2, double a3,
                               double b1, double b2, double b3)
    {
        return a1 * b1 +
                a2 * b2 +
                a3 * b3;
    }

    private static boolean approximatelyEqual(
            double a1,
            double b1
    )
    {
        return MathUtils.approximatelyEqual(a1, b1, MathConstants.EPSILON);
    }


    public Vector3d add(Vector3d other)
    {
        return new Vector3d(
                x + other.x,
                y + other.y,
                z + other.z
        );
    }
    public Vector3d subtract(Vector3d other)
    {
        return new Vector3d(
                x - other.x,
                y - other.y,
                z - other.z
        );
    }
    public Vector3d multiply(double scalar)
    {
        return new Vector3d(
                x * scalar,
                y * scalar,
                z * scalar
        );
    }
    public Vector3d multiply(Vector3d other)
    {
        return new Vector3d(
                x * other.x,
                y * other.y,
                z * other.z
        );
    }
    public Vector3d divide(double scalar)
    {
        if(Double.compare(scalar, 0.0) == 0)
            throw new DivisionByZeroException("scalar");

        return new Vector3d(
                x / scalar,
                y / scalar,
                z / scalar
        );
    }
    public Vector3d divide(Vector3f other)
    {
        if(
                Double.compare(other.x, 0.0) == 0 &&
                        Double.compare(other.y, 0.0) == 0 &&
                        Double.compare(other.z, 0.0) == 0
        )
            throw new DivisionByZeroException("other");

        return new Vector3d(
                x / other.x,
                y / other.y,
                z / other.z
        );
    }

    public double length()
    {
        return Math.sqrt(lengthSquared());
    }
    public double lengthSquared()
    {
        return sumProducts(x, y, z, x, y, z);
    }
    public double distance(Vector3d other)
    {
        return Math.sqrt(distanceSquared(other));
    }
    public double distanceSquared(Vector3d other)
    {
        double dx = x - other.x;
        double dy = y - other.y;
        double dz = z - other.z;

        return sumProducts(dx, dy, dz, dx, dy, dz);
    }

    public double dot(Vector3d other)
    {
        return sumProducts(x, y, z, other.x, other.y, other.z);
    }
    public Vector3d cross(Vector3d other)
    {
        return new Vector3d(
                y * other.z - z * other.y,
                z * other.x - x * other.z,
                x * other.y - y * other.x
        );
    }
    public Vector3d normalize()
    {
        double length = length();

        if(MathUtils.approximatelyZero(length))
            throw new DivisionByZeroException("length");

        return divide(length);
    }
    public Vector3d negate()
    {
        return new Vector3d(-x, -y, -z);
    }
    public Vector3d lerp(Vector3d other, double interpolation)
    {
        return new Vector3d(
                MathUtils.lerp(x, other.x, interpolation),
                MathUtils.lerp(y, other.y, interpolation),
                MathUtils.lerp(z, other.z, interpolation)
        );
    }

    public boolean approximatelyEqual(Vector3d other)
    {
        return approximatelyEqual(x, other.x) && approximatelyEqual(y, other.y) && approximatelyEqual(z, other.z);
    }

    @Override
    public String toString()
    {
        return String.format(
                """
                Vector3d(%+.6f, %+.6f, %+.6f)
                """,
                x, y, z
        );
    }
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (!(obj instanceof Vector3d other)) return false;

        return Double.compare(x, other.x) == 0 &&
                Double.compare(y, other.y) == 0 &&
                Double.compare(z, other.z) == 0;
    }
    @Override
    public int hashCode()
    {
        int result = Double.hashCode(x);
        result = 31 * result + Double.hashCode(y);
        result = 31 * result + Double.hashCode(z);
        return result;
    }
}
