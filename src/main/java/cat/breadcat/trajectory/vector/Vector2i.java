package cat.breadcat.trajectory.vector;

import cat.breadcat.toolbox.exception.DivisionByZeroException;
import cat.breadcat.toolbox.util.MathUtils;

public final class Vector2i
{
    public static final Vector2i ZERO = new Vector2i(0, 0);
    public static final Vector2i ONE = new Vector2i(1, 1);
    public static final Vector2i UNIT_X = new Vector2i(1, 0);
    public static final Vector2i UNIT_Y = new Vector2i(0, 1);

    public final long x;
    public final long y;

    public Vector2i(long x, long y)
    {
        this.x = x;
        this.y = y;
    }


    private static long sumProducts(long a1, long a2,
                                    long b1, long b2)
    {
        return a1 * b1 +
                a2 * b2;
    }


    public Vector2i add(Vector2i other)
    {
        return new Vector2i(
                x + other.x,
                y + other.y
        );
    }
    public Vector2i subtract(Vector2i other)
    {
        return new Vector2i(
                x - other.x,
                y - other.y
        );
    }
    public Vector2i multiply(long other)
    {
        return new Vector2i(
                x * other,
                y * other
        );
    }
    public Vector2i multiply(Vector2i other)
    {
        return new Vector2i(
                x * other.x,
                y * other.y
        );
    }
    public Vector2i divide(long other)
    {
        if(other == 0)
            throw new DivisionByZeroException("other");

        return new Vector2i(
                x / other,
                y / other
        );
    }
    public Vector2i divide(Vector2i other)
    {
        if(
                other.x == 0 &&
                other.y == 0
        )
            throw new DivisionByZeroException("other");

        return new Vector2i(
                x / other.x,
                y / other.y
        );
    }

    public double length()
    {
        return Math.sqrt(lengthSquared());
    }
    public long lengthSquared()
    {
        return sumProducts(x, y, x, y);
    }
    public double distance(Vector2i other)
    {
        return Math.sqrt(distanceSquared(other));
    }
    public long distanceSquared(Vector2i other)
    {
        long dx = x - other.x;
        long dy = y - other.y;

        return sumProducts(dx, dy, dx, dy);
    }

    public long dot(Vector2i other)
    {
        return sumProducts(x, y, other.x, other.y);
    }
    public Vector2i negate()
    {
        return new Vector2i(-x, -y);
    }

    @Override
    public String toString()
    {
        return "Vector2i(" + x + ", " + y + ")";
    }
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (!(obj instanceof Vector2i other)) return false;

        return x == other.x &&
                y == other.y;
    }
    @Override
    public int hashCode()
    {
        int result = Long.hashCode(x);
        result = 31 * result + Long.hashCode(y);
        return result;
    }
}
