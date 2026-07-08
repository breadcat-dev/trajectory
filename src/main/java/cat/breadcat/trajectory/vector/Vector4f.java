package cat.breadcat.trajectory.vector;

public final class Vector4f
{
    public static final Vector4f ZERO = new Vector4f(0, 0, 0, 0);
    public static final Vector4f ONE  = new Vector4f(1, 1, 1, 1);

    public final float x;
    public final float y;
    public final float z;
    public final float w;

    public Vector4f(float x, float y, float z, float w)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }


    private float sumProducts(float a, float b, float c, float d)
    {
        return x * a + y * b + z * c + w * d;
    }


    public Vector4f add(Vector4f other)
    {
        return new Vector4f(x + other.x, y + other.y, z + other.z, w + other.w);
    }

    public Vector4f subtract(Vector4f other)
    {
        return new Vector4f(x - other.x, y - other.y, z - other.z, w - other.w);
    }

    public Vector4f multiply(Vector4f other)
    {
        return new Vector4f(x * other.x, y * other.y, z * other.z, w * other.w);
    }

    public Vector4f multiply(float other)
    {
        return new Vector4f(x * other, y * other, z * other, w * other);
    }

    public Vector4f divide(Vector4f other)
    {
        return new Vector4f(x / other.x, y / other.y, z / other.z, w / other.w);
    }

    public Vector4f divide(float other)
    {
        return new Vector4f(x / other, y / other, z / other, w / other);
    }


    public float dot(Vector4f other)
    {
        return sumProducts(other.x, other.y, other.z, other.w);
    }


    public float length()
    {
        return (float)Math.sqrt(lengthSquared());
    }

    public float lengthSquared()
    {
        return sumProducts(x, y, z, w);
    }

    public float distance(Vector4f other)
    {
        return (float)Math.sqrt(distanceSquared(other));
    }

    public float distanceSquared(Vector4f other)
    {
        float dx = x - other.x;
        float dy = y - other.y;
        float dz = z - other.z;
        float dt = w - other.w;

        return dx * dx + dy * dy + dz * dz + dt * dt;
    }


    public Vector4f normalize()
    {
        float length = length();

        if(length == 0)
            throw new ArithmeticException("Cannot normalize zero vector.");

        return divide(length);
    }


    public Vector4f negate()
    {
        return new Vector4f(-x, -y, -z, -w);
    }


    public Vector4f lerp(Vector4f other, float interpolation)
    {
        return new Vector4f(x + (other.x - x) * interpolation, y + (other.y - y) * interpolation, z + (other.z - z) * interpolation, w + (other.w - w) * interpolation);
    }


    public boolean isZero()
    {
        return x == 0 && y == 0 && z == 0 && w == 0;
    }


    @Override
    public String toString()
    {
        return "Vector4f(" + x + ", " + y + ", " + z + ", " + w + ")";
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (!(obj instanceof Vector4f other)) return false;

        return Float.compare(x, other.x) == 0 && Float.compare(y, other.y) == 0 && Float.compare(z, other.z) == 0 && Float.compare(w, other.w) == 0;
    }

    @Override
    public int hashCode()
    {
        int result = Float.hashCode(x);
        result = 31 * result + Float.hashCode(y);
        result = 31 * result + Float.hashCode(z);
        result = 31 * result + Float.hashCode(w);
        return result;
    }
}
