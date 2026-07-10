package cat.breadcat.trajectory.vector;

import cat.breadcat.toolbox.constant.MathConstants;
import cat.breadcat.toolbox.exception.DivisionByZeroException;
import cat.breadcat.toolbox.util.MathUtils;

public final class Vector2f
{
    public static final Vector2f ZERO = new Vector2f(0, 0);
    public static final Vector2f UNIT = new Vector2f(1, 1);
    public static final Vector2f UNIT_X = new Vector2f(1, 0);
    public static final Vector2f UNIT_Y = new Vector2f(0, 1);

    public final float x;
    public final float y;

    public Vector2f(float x, float y)
    {
        this.x = x;
        this.y = y;
    }


    private static float sumProducts(float a1, float a2,
                                      float b1, float b2)
    {
        return a1 * b1 +
                a2 * b2;
    }

    private static boolean approximatelyEqual(
            float a1,
            float b1
    )
    {
        return MathUtils.approximatelyEqual(a1, b1, MathConstants.EPSILON_F);
    }

    public Vector2f add(Vector2f other)
    {
        return new Vector2f(
                x + other.x,
                y + other.y
        );
    }
    public Vector2f subtract(Vector2f other)
    {
        return new Vector2f(
                x - other.x,
                y - other.y
        );
    }
    public Vector2f multiply(float scalar)
    {
        return new Vector2f(
                x * scalar,
                y * scalar
        );
    }
    public Vector2f multiply(Vector2f other)
    {
        return new Vector2f(
                x * other.x,
                y * other.y
        );
    }
    public Vector2f divide(float scalar)
    {
        if(Float.compare(scalar, 0.0f) == 0)
            throw new DivisionByZeroException("scalar");

        return new Vector2f(
                x / scalar,
                y / scalar
        );
    }
    public Vector2f divide(Vector3f other)
    {
        if(
                Float.compare(other.x, 0.0f) == 0 &&
                Float.compare(other.y, 0.0f) == 0
        )
            throw new DivisionByZeroException("other");

        return new Vector2f(
                x / other.x,
                y / other.y
        );
    }

    public float length()
    {
        return (float)Math.sqrt(lengthSquared());
    }
    public float lengthSquared()
    {
        return sumProducts(x, y, x, y);
    }
    public float distance(Vector2f other)
    {
        return (float)Math.sqrt(distanceSquared(other));
    }
    public float distanceSquared(Vector2f other)
    {
        float dx = x - other.x;
        float dy = y - other.y;

        return sumProducts(dx, dy, dx, dy);
    }

    public float dot(Vector2f other)
    {
        return sumProducts(x, y, other.x, other.y);
    }
    public Vector2f normalize()
    {
        float length = length();

        if(MathUtils.approximatelyZero(length))
            throw new DivisionByZeroException("length");

        return divide(length);
    }
    public Vector2f negate()
    {
        return new Vector2f(-x, -y);
    }
    public Vector2f lerp(Vector2f other, float interpolation)
    {
        return new Vector2f(
                MathUtils.lerp(x, other.x, interpolation),
                MathUtils.lerp(y, other.y, interpolation)
        );
    }

    public boolean approximatelyEqual(Vector2f other)
    {
        return approximatelyEqual(x, other.x) && approximatelyEqual(y, other.y);
    }

    @Override
    public String toString()
    {
        return String.format(
                """
                Vector2f(%+.6f, %+.6f)
                """,
                x, y
        );
    }
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (!(obj instanceof Vector2f other)) return false;

        return Float.compare(x, other.x) == 0 &&
                Float.compare(y, other.y) == 0;
    }
    @Override
    public int hashCode()
    {
        int result = Float.hashCode(x);
        result = 31 * result + Float.hashCode(y);
        return result;
    }
}
