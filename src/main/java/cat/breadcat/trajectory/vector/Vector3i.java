package cat.breadcat.trajectory.vector;

import cat.breadcat.toolbox.exception.DivisionByZeroException;
import cat.breadcat.toolbox.util.MathUtils;

public final class Vector3i
{
    public static final Vector3i ZERO = new Vector3i(0, 0, 0);
    public static final Vector3i ONE = new Vector3i(1, 1, 1);
    public static final Vector3i UNIT_X = new Vector3i(1, 0, 0);
    public static final Vector3i UNIT_Y = new Vector3i(0, 1, 0);
    public static final Vector3i UNIT_Z = new Vector3i(0, 0, 1);

    public final long x;
    public final long y;
    public final long z;

    public Vector3i(long x, long y, long z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }


    private static long sumProducts(long a1, long a2, long a3,
                               long b1, long b2, long b3)
    {
        return a1 * b1 +
                a2 * b2 +
                a3 * b3;
    }


    public Vector3i add(Vector3i other)
    {
        return new Vector3i(
                x + other.x,
                y + other.y,
                z + other.z
        );
    }
    public Vector3i subtract(Vector3i other)
    {
        return new Vector3i(
                x - other.x,
                y - other.y,
                z - other.z
        );
    }
    public Vector3i multiply(long other)
    {
        return new Vector3i(
                x * other,
                y * other,
                z * other
        );
    }
    public Vector3i multiply(Vector3i other)
    {
        return new Vector3i(
                x * other.x,
                y * other.y,
                z * other.z
        );
    }
    public Vector3i divide(long other)
    {
        if(other == 0)
            throw new DivisionByZeroException("other");

        return new Vector3i(
                x / other,
                y / other,
                z / other
        );
    }
    public Vector3i divide(Vector3i other)
    {
        if(
                other.x == 0 &&
                other.y == 0 &&
                other.z == 0
        )
            throw new DivisionByZeroException("other");

        return new Vector3i(
                x / other.x,
                y / other.y,
                z / other.z
        );
    }

    public double length()
    {
        return Math.sqrt(lengthSquared());
    }
    public long lengthSquared()
    {
        return sumProducts(x, y, z, x, y, z);
    }
    public double distance(Vector3i other)
    {
        return Math.sqrt(distanceSquared(other));
    }
    public long distanceSquared(Vector3i other)
    {
        long dx = x - other.x;
        long dy = y - other.y;
        long dz = z - other.z;

        return sumProducts(dx, dy, dz, dx, dy, dz);
    }

    public long dot(Vector3i other)
    {
        return sumProducts(x, y, z, other.x, other.y, other.z);
    }
    public Vector3i cross(Vector3i other)
    {
        return new Vector3i(
                y * other.z - z * other.y,
                z * other.x - x * other.z,
                x * other.y - y * other.x
        );
    }
    public Vector3i negate()
    {
        return new Vector3i(-x, -y, -z);
    }

    @Override
    public String toString()
    {
        return "Vector3i(" + x + ", " + y + ", " + z + ")";
    }
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (!(obj instanceof Vector3i other)) return false;

        return x == other.x &&
                y == other.y &&
                z == other.z;
    }
    @Override
    public int hashCode()
    {
        int result = Long.hashCode(x);
        result = 31 * result + Long.hashCode(y);
        result = 31 * result + Long.hashCode(z);
        return result;
    }
}
