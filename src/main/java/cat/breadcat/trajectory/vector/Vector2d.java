package cat.breadcat.trajectory.vector;

import cat.breadcat.toolbox.exception.DivisionByZeroException;
import cat.breadcat.toolbox.util.MathUtils;

public final class Vector2d
{
    public static final Vector2d ZERO = new Vector2d(0, 0);
    public static final Vector2d ONE = new Vector2d(1, 1);
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
    public Vector2d multiply(double other)
    {
        return new Vector2d(
                x * other,
                y * other
        );
    }
    public Vector2d multiply(Vector2d other)
    {
        return new Vector2d(
                x * other.x,
                y * other.y
        );
    }
    public Vector2d divide(double other)
    {
        if(Double.compare(other, 0.0) == 0)
            throw new DivisionByZeroException("other");

        return new Vector2d(
                x / other,
                y / other
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

    @Override
    public String toString()
    {
        return "Vector2d(" + x + ", " + y + ")";
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
