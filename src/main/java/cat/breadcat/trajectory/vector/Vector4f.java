package cat.breadcat.trajectory.vector;

import cat.breadcat.toolbox.constant.MathConstants;
import cat.breadcat.toolbox.exception.DivisionByZeroException;
import cat.breadcat.toolbox.util.MathUtils;

public final class Vector4f
{
    public static final Vector4f ZERO = new Vector4f(0, 0, 0, 0);
    public static final Vector4f UNIT = new Vector4f(1, 1, 1, 1);
    public static final Vector4f UNIT_X = new Vector4f(1, 0, 0, 0);
    public static final Vector4f UNIT_Y = new Vector4f(0, 1, 0, 0);
    public static final Vector4f UNIT_Z = new Vector4f(0, 0, 1, 0);
    public static final Vector4f UNIT_W = new Vector4f(0, 0, 0, 1);

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


    private static float sumProducts(float a1, float a2, float a3, float a4,
                               float b1, float b2, float b3, float b4)
    {
        return a1 * b1 +
                a2 * b2 +
                a3 * b3 +
                a4 * b4;
    }

    private static boolean approximatelyEqual(
            float a1,
            float b1
    )
    {
        return MathUtils.approximatelyEqual(a1, b1, MathConstants.EPSILON_F);
    }

    public Vector4f add(Vector4f other)
    {
        return new Vector4f(
                x + other.x,
                y + other.y,
                z + other.z,
                w + other.w
        );
    }
    public Vector4f subtract(Vector4f other)
    {
        return new Vector4f(
                x - other.x,
                y - other.y,
                z - other.z,
                w - other.w
        );
    }
    public Vector4f multiply(float scalar)
    {
        return new Vector4f(
                x * scalar,
                y * scalar,
                z * scalar,
                w * scalar
        );
    }
    public Vector4f multiply(Vector4f other)
    {
        return new Vector4f(
                x * other.x,
                y * other.y,
                z * other.z,
                w * other.w
        );
    }
    public Vector4f divide(float scalar)
    {
        if(Float.compare(scalar, 0.0f) == 0)
            throw new DivisionByZeroException("scalar");

        return new Vector4f(
                x / scalar,
                y / scalar,
                z / scalar,
                w / scalar
        );
    }
    public Vector4f divide(Vector4f other)
    {
        if(
                Float.compare(other.x, 0.0f) == 0 &&
                        Float.compare(other.y, 0.0f) == 0 &&
                        Float.compare(other.z, 0.0f) == 0 &&
                        Float.compare(other.w, 0.0f) == 0
        )
            throw new DivisionByZeroException("other");

        return new Vector4f(
                x / other.x,
                y / other.y,
                z / other.z,
                w / other.w
        );
    }

    public float length()
    {
        return (float)Math.sqrt(lengthSquared());
    }
    public float lengthSquared()
    {
        return sumProducts(x, y, z, w, x, y, z, w);
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

        return sumProducts(dx, dy, dz, dt, dx, dy, dz, dt);
    }

    public float dot(Vector4f other)
    {
        return sumProducts(x, y, z, w, other.x, other.y, other.z, other.w);
    }
    public Vector4f normalize()
    {
        float length = length();

        if(MathUtils.approximatelyZero(length))
            throw new DivisionByZeroException("length");

        return divide(length);
    }
    public Vector4f negate()
    {
        return new Vector4f(-x, -y, -z, -w);
    }
    public Vector4f lerp(Vector4f other, float interpolation)
    {
        return new Vector4f(
                MathUtils.lerp(x, other.x, interpolation),
                MathUtils.lerp(y, other.y, interpolation),
                MathUtils.lerp(z, other.z, interpolation),
                MathUtils.lerp(w, other.w, interpolation)
        );
    }

    public boolean approximatelyEqual(Vector4f other)
    {
        return approximatelyEqual(x, other.x) && approximatelyEqual(y, other.y) && approximatelyEqual(z, other.z) && approximatelyEqual(w, other.w);
    }

    @Override
    public String toString()
    {
        return String.format(
                """
                Vector4f(%+.6f, %+.6f, %+.6f, %+.6f)
                """,
                x, y, z, w
        );
    }
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (!(obj instanceof Vector4f other)) return false;

        return Float.compare(x, other.x) == 0 &&
                Float.compare(y, other.y) == 0 &&
                Float.compare(z, other.z) == 0 &&
                Float.compare(w, other.w) == 0;
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
