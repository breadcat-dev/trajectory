package cat.breadcat.trajectory.vector;

import cat.breadcat.toolbox.constant.MathConstants;
import cat.breadcat.toolbox.exception.DivisionByZeroException;
import cat.breadcat.toolbox.util.MathUtils;

public final class Vector3f
{
    public static final Vector3f ZERO = new Vector3f(0, 0, 0);
    public static final Vector3f UNIT = new Vector3f(1, 1, 1);
    public static final Vector3f UNIT_X = new Vector3f(1, 0, 0);
    public static final Vector3f UNIT_Y = new Vector3f(0, 1, 0);
    public static final Vector3f UNIT_Z = new Vector3f(0, 0, 1);

    public final float x;
    public final float y;
    public final float z;

    public Vector3f(float x, float y, float z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }


    private static float sumProducts(float a1, float a2, float a3,
                               float b1, float b2, float b3)
    {
        return a1 * b1 +
                a2 * b2 +
                a3 * b3;
    }

    private static boolean approximatelyEqual(
            float a1,
            float b1
    )
    {
        return MathUtils.approximatelyEqual(a1, b1, MathConstants.EPSILON_F);
    }


    public Vector3f add(Vector3f other)
    {
        return new Vector3f(
                x + other.x,
                y + other.y,
                z + other.z
        );
    }
    public Vector3f subtract(Vector3f other)
    {
        return new Vector3f(
                x - other.x,
                y - other.y,
                z - other.z
        );
    }
    public Vector3f multiply(float scalar)
    {
        return new Vector3f(
                x * scalar,
                y * scalar,
                z * scalar
        );
    }
    public Vector3f multiply(Vector3f other)
    {
        return new Vector3f(
                x * other.x,
                y * other.y,
                z * other.z
        );
    }
    public Vector3f divide(float scalar)
    {
        if(Float.compare(scalar, 0.0f) == 0)
            throw new DivisionByZeroException("scalar");

        return new Vector3f(
                x / scalar,
                y / scalar,
                z / scalar
        );
    }
    public Vector3f divide(Vector3f other)
    {
        if(
                Float.compare(other.x, 0.0f) == 0 &&
                        Float.compare(other.y, 0.0f) == 0 &&
                        Float.compare(other.z, 0.0f) == 0
        )
            throw new DivisionByZeroException("other");

        return new Vector3f(
                x / other.x,
                y / other.y,
                z / other.z
        );
    }

    public float length()
    {
        return (float)Math.sqrt(lengthSquared());
    }
    public float lengthSquared()
    {
        return sumProducts(x, y, z, x, y, z);
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

        return sumProducts(dx, dy, dz, dx, dy, dz);
    }

    public float dot(Vector3f other)
    {
        return sumProducts(x, y, z, other.x, other.y, other.z);
    }
    public Vector3f cross(Vector3f other)
    {
        return new Vector3f(y * other.z - z * other.y, z * other.x - x * other.z, x * other.y - y * other.x);
    }
    public Vector3f normalize()
    {
        float length = length();

        if(MathUtils.approximatelyZero(length))
            throw new DivisionByZeroException("length");

        return divide(length);
    }
    public Vector3f negate()
    {
        return new Vector3f(-x, -y, -z);
    }
    public Vector3f lerp(Vector3f other, float interpolation)
    {
        return new Vector3f(
                MathUtils.lerp(x, other.x, interpolation),
                MathUtils.lerp(y, other.y, interpolation),
                MathUtils.lerp(z, other.z, interpolation)
        );
    }

    public boolean approximatelyEqual(Vector3f other)
    {
        return approximatelyEqual(x, other.x) && approximatelyEqual(y, other.y) && approximatelyEqual(z, other.z);
    }

    @Override
    public String toString()
    {
        return String.format(
                """
                Vector3f(%+.6f, %+.6f, %+.6f)
                """,
                x, y, z
        );
    }
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (!(obj instanceof Vector3f other)) return false;

        return Float.compare(x, other.x) == 0 &&
                Float.compare(y, other.y) == 0 &&
                Float.compare(z, other.z) == 0;
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
