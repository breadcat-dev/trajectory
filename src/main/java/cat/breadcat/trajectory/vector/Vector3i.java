package cat.breadcat.trajectory.vector;

public final class Vector3i
{
    public static final Vector3i ZERO = new Vector3i(0, 0, 0);
    public static final Vector3i ONE  = new Vector3i(1, 1, 1);
    public static final Vector3i UNIT_X  = new Vector3i(1, 0, 0);
    public static final Vector3i UNIT_Y  = new Vector3i(0, 1, 0);
    public static final Vector3i UNIT_Z  = new Vector3i(0, 0, 1);

    public final int x;
    public final int y;
    public final int z;

    public Vector3i(int x, int y, int z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }


    private long sumProducts(long a, long b, long c)
    {
        return x * a + y * b + z * c;
    }


    public Vector3i add(Vector3i other)
    {
        return new Vector3i(x + other.x, y + other.y, z + other.z);
    }

    public Vector3i subtract(Vector3i other)
    {
        return new Vector3i(x - other.x, y - other.y, z - other.z);
    }

    public Vector3i multiply(Vector3i other)
    {
        return new Vector3i(x * other.x, y * other.y, z * other.z);
    }

    public Vector3i multiply(int other)
    {
        return new Vector3i(x * other, y * other, z * other);
    }

    public Vector3i divide(Vector3i other)
    {
        return new Vector3i(x / other.x, y / other.y, z / other.z);
    }

    public Vector3i divide(int other)
    {
        return new Vector3i(x / other, y / other, z / other);
    }


    public long dot(Vector3i other)
    {
        return sumProducts(other.x, other.y, other.z);
    }

    public Vector3i cross(Vector3i other)
    {
        return new Vector3i(y * other.z - z * other.y, z * other.x - x * other.z, x * other.y - y * other.x);
    }


    public double length()
    {
        return (float)Math.sqrt(lengthSquared());
    }

    public long lengthSquared()
    {
        return sumProducts(x, y, z);
    }

    public double distance(Vector3i other)
    {
        return (float)Math.sqrt(distanceSquared(other));
    }

    public long distanceSquared(Vector3i other)
    {
        long dx = x - other.x;
        long dy = y - other.y;
        long dz = z - other.z;

        return dx * dx + dy * dy + dz * dz;
    }


    public Vector3i negate()
    {
        return new Vector3i(-x, -y, -z);
    }


    public boolean isZero()
    {
        return x == 0 && y == 0 && z == 0;
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

        return Integer.compare(x, other.x) == 0 && Integer.compare(y, other.y) == 0 && Integer.compare(z, other.z) == 0;
    }

    @Override
    public int hashCode()
    {
        int result = Integer.hashCode(x);
        result = 31 * result + Integer.hashCode(y);
        result = 31 * result + Integer.hashCode(z);
        return result;
    }
}
