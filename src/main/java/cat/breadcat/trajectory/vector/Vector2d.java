package cat.breadcat.trajectory.vector;

import cat.breadcat.toolbox.constant.MathConstants;
import cat.breadcat.toolbox.exception.DivisionByZeroException;
import cat.breadcat.toolbox.util.MathUtils;

public final class Vector2d
{
    public static final Vector2d ZERO = new Vector2d(0, 0);
    public static final Vector2d UNIT = new Vector2d(1, 1);
    public static final Vector2d UNIT_X = new Vector2d(1, 0);
    public static final Vector2d UNIT_Y = new Vector2d(0, 1);

    public final double x;
    public final double y;

    public Vector2d(double x, double y)
    {
        this.x = x;
        this.y = y;
    }


    private static double sumProducts(double a1, double a2,
                                      double b1, double b2)
    {
        return a1 * b1 +
                a2 * b2;
    }

    private static boolean approximatelyEqual(
            double a1,
            double b1
    )
    {
        return MathUtils.approximatelyEqual(a1, b1, MathConstants.EPSILON);
    }

    public Vector2d add(Vector2d other)
    {
        return new Vector2d(
                x + other.x,
                y + other.y
        );
    }
    public Vector2d subtract(Vector2d other)
    {
        return new Vector2d(
                x - other.x,
                y - other.y
        );
    }
    public Vector2d multiply(double scalar)
    {
        return new Vector2d(
                x * scalar,
                y * scalar
        );
    }
    public Vector2d multiply(Vector2d other)
    {
        return new Vector2d(
                x * other.x,
                y * other.y
        );
    }
    public Vector2d divide(double scalar)
    {
        if(Double.compare(scalar, 0.0) == 0)
            throw new DivisionByZeroException("scalar");

        return new Vector2d(
                x / scalar,
                y / scalar
        );
    }
    public Vector2d divide(Vector3f other)
    {
        if(
                Double.compare(other.x, 0.0) == 0 &&
                Double.compare(other.y, 0.0) == 0
        )
            throw new DivisionByZeroException("other");

        return new Vector2d(
                x / other.x,
                y / other.y
        );
    }

    public double length()
    {
        return Math.sqrt(lengthSquared());
    }
    public double lengthSquared()
    {
        return sumProducts(x, y, x, y);
    }
    public double distance(Vector2d other)
    {
        return Math.sqrt(distanceSquared(other));
    }
    public double distanceSquared(Vector2d other)
    {
        double dx = x - other.x;
        double dy = y - other.y;

        return sumProducts(dx, dy, dx, dy);
    }

    public double dot(Vector2d other)
    {
        return sumProducts(x, y, other.x, other.y);
    }
    public Vector2d normalize()
    {
        double length = length();

        if(MathUtils.approximatelyZero(length))
            throw new DivisionByZeroException("length");

        return divide(length);
    }
    public Vector2d negate()
    {
        return new Vector2d(-x, -y);
    }
    public Vector2d lerp(Vector2d other, double interpolation)
    {
        return new Vector2d(
                MathUtils.lerp(x, other.x, interpolation),
                MathUtils.lerp(y, other.y, interpolation)
        );
    }

    public boolean approximatelyEqual(Vector2d other)
    {
        return approximatelyEqual(x, other.x) && approximatelyEqual(y, other.y);
    }

    @Override
    public String toString()
    {
        return String.format(
                """
                Vector2d(%+.6f, %+.6f)
                """,
                x, y
        );
    }
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (!(obj instanceof Vector2d other)) return false;

        return Double.compare(x, other.x) == 0 &&
                Double.compare(y, other.y) == 0;
    }
    @Override
    public int hashCode()
    {
        int result = Double.hashCode(x);
        result = 31 * result + Double.hashCode(y);
        return result;
    }
}
