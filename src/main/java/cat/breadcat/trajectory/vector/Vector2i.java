package cat.breadcat.trajectory.vector;

public final class Vector2i
{
    public static final Vector2i ZERO = new Vector2i(0, 0);
    public static final Vector2i ONE  = new Vector2i(1, 1);
    public static final Vector2i UNIT_X  = new Vector2i(1, 0);
    public static final Vector2i UNIT_Y  = new Vector2i(0, 1);
    
    public final int x;
    public final int y;

    public Vector2i(int x, int y)
    {
        this.x = x;
        this.y = y;
    }


    private long sumProducts(long a, long b)
    {
        return x * a + y * b;
    }


    public Vector2i add(Vector2i other)
    {
        return new Vector2i(x + other.x, y + other.y);
    }

    public Vector2i subtract(Vector2i other)
    {
        return new Vector2i(x - other.x, y - other.y);
    }

    public Vector2i multiply(Vector2i other)
    {
        return new Vector2i(x * other.x, y * other.y);
    }

    public Vector2i multiply(int other)
    {
        return new Vector2i(x * other, y * other);
    }

    public Vector2i divide(Vector2i other)
    {
        return new Vector2i(x / other.x, y / other.y);
    }

    public Vector2i divide(int other)
    {
        return new Vector2i(x / other, y / other);
    }


    public long dot(Vector2i other)
    {
        return sumProducts(other.x, other.y);
    }


    public double length()
    {
        return Math.sqrt(lengthSquared());
    }

    public long lengthSquared()
    {
        return sumProducts(x, y);
    }

    public double distance(Vector2i other)
    {
        return Math.sqrt(distanceSquared(other));
    }

    public long distanceSquared(Vector2i other)
    {
        long dx = x - other.x;
        long dy = y - other.y;

        return dx * dx + dy * dy;
    }


    public Vector2i negate()
    {
        return new Vector2i(-x, -y);
    }


    public boolean isZero()
    {
        return x == 0 && y == 0;
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

        return x == other.x && y == other.y;
    }

    @Override
    public int hashCode()
    {
        int result = Integer.hashCode(x);
        result = 31 * result + Integer.hashCode(y);
        return result;
    }
}
