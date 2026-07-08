package cat.breadcat.trajectory.vector;

public final class Vector3f
{
    public static final Vector3f ZERO = new Vector3f(0, 0, 0);
    public static final Vector3f ONE  = new Vector3f(1, 1, 1);
    public static final Vector3f UNIT_X  = new Vector3f(1, 0, 0);
    public static final Vector3f UNIT_Y  = new Vector3f(0, 1, 0);
    public static final Vector3f UNIT_Z  = new Vector3f(0, 0, 1);

    public final float x;
    public final float y;
    public final float z;

    public Vector3f(float x, float y, float z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }


    private float sumProducts(float a, float b, float c)
    {
        return x * a + y * b + z * c;
    }


    public Vector3f add(Vector3f other)
    {
        return new Vector3f(x + other.x, y + other.y, z + other.z);
    }

    public Vector3f subtract(Vector3f other)
    {
        return new Vector3f(x - other.x, y - other.y, z - other.z);
    }

    public Vector3f multiply(Vector3f other)
    {
        return new Vector3f(x * other.x, y * other.y, z * other.z);
    }

    public Vector3f multiply(float other)
    {
        return new Vector3f(x * other, y * other, z * other);
    }

    public Vector3f divide(Vector3f other)
    {
        return new Vector3f(x / other.x, y / other.y, z / other.z);
    }

    public Vector3f divide(float other)
    {
        return new Vector3f(x / other, y / other, z / other);
    }


    public float dot(Vector3f other)
    {
        return sumProducts(other.x, other.y, other.z);
    }

    public Vector3f cross(Vector3f other)
    {
        return new Vector3f(y * other.z - z * other.y, z * other.x - x * other.z, x * other.y - y * other.x);
    }


    public float length()
    {
        return (float)Math.sqrt(lengthSquared());
    }

    public float lengthSquared()
    {
        return sumProducts(x, y, z);
    }

    public float distance(Vector3f other)
    {
        return (float)Math.sqrt(distanceSquared(other));
    }

    public float distanceSquared(Vector3f other)
    {
        float dx = x - other.x;
        float dy = y - other.y;
        float dz = z - other.z;

        return dx * dx + dy * dy + dz * dz;
    }


    public Vector3f normalize()
    {
        float length = length();

        if(length == 0)
            throw new ArithmeticException("Cannot normalize zero vector.");

        return divide(length);
    }


    public Vector3f negate()
    {
        return new Vector3f(-x, -y, -z);
    }


    public Vector3f lerp(Vector3f other, float interpolation)
    {
        return new Vector3f(x + (other.x - x) * interpolation, y + (other.y - y) * interpolation, z + (other.z - z) * interpolation);
    }


    public boolean isZero()
    {
        return x == 0 && y == 0 && z == 0;
    }


    @Override
    public String toString()
    {
        return "Vector3f(" + x + ", " + y + ", " + z + ")";
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (!(obj instanceof Vector3f other)) return false;

        return Float.compare(x, other.x) == 0 && Float.compare(y, other.y) == 0 && Float.compare(z, other.z) == 0;
    }

    @Override
    public int hashCode()
    {
        int result = Float.hashCode(x);
        result = 31 * result + Float.hashCode(y);
        result = 31 * result + Float.hashCode(z);
        return result;
    }
}
