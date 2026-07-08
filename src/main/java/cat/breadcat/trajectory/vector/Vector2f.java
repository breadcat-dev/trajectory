package cat.breadcat.trajectory.vector;

public final class Vector2f
{
    public static final Vector2f ZERO = new Vector2f(0, 0);
    public static final Vector2f ONE  = new Vector2f(1, 1);
    public static final Vector2f UNIT_X  = new Vector2f(1, 0);
    public static final Vector2f UNIT_Y  = new Vector2f(0, 1);
    
    public final float x;
    public final float y;

    public Vector2f(float x, float y)
    {
        this.x = x;
        this.y = y;
    }


    private float sumProducts(float a, float b)
    {
        return x * a + y * b;
    }


    public Vector2f add(Vector2f other)
    {
        return new Vector2f(x + other.x, y + other.y);
    }

    public Vector2f subtract(Vector2f other)
    {
        return new Vector2f(x - other.x, y - other.y);
    }

    public Vector2f multiply(Vector2f other)
    {
        return new Vector2f(x * other.x, y * other.y);
    }

    public Vector2f multiply(float other)
    {
        return new Vector2f(x * other, y * other);
    }

    public Vector2f divide(Vector2f other)
    {
        return new Vector2f(x / other.x, y / other.y);
    }

    public Vector2f divide(float other)
    {
        return new Vector2f(x / other, y / other);
    }


    public float dot(Vector2f other)
    {
        return sumProducts(other.x, other.y);
    }


    public float length()
    {
        return (float)Math.sqrt(lengthSquared()); // distance from 0,0 to vector
    }

    public float lengthSquared()
    {
        return sumProducts(x, y);
    }

    public float distance(Vector2f other)
    {
        return (float)Math.sqrt(distanceSquared(other));
    }

    public float distanceSquared(Vector2f other)
    {
        float dx = x - other.x;
        float dy = y - other.y;

        return dx * dx + dy * dy;
    }


    public Vector2f normalize()
    {
        float length = length();

        if(length == 0)
            throw new ArithmeticException("Cannot normalize zero vector.");

        return divide(length);
    }


    public Vector2f negate()
    {
        return new Vector2f(-x, -y);
    }


    public Vector2f lerp(Vector2f other, float interpolation)
    {
        return new Vector2f(x + (other.x - x) * interpolation, y + (other.y - y) * interpolation);
    }


    public boolean isZero()
    {
        return x == 0 && y == 0;
    }


    @Override
    public String toString()
    {
        return "Vector2f(" + x + ", " + y + ")";
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (!(obj instanceof Vector2f other)) return false;

        return Float.compare(x, other.x) == 0 && Float.compare(y, other.y) == 0;
    }

    @Override
    public int hashCode()
    {
        int result = Float.hashCode(x);
        result = 31 * result + Float.hashCode(y);
        return result;
    }
}
