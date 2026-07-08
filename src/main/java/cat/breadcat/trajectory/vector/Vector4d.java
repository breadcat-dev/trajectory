package cat.breadcat.trajectory.vector;

public final class Vector4d
{
    public static final Vector4d ZERO = new Vector4d(0, 0, 0, 0);
    public static final Vector4d ONE  = new Vector4d(1, 1, 1, 1);

    public final double x;
    public final double y;
    public final double z;
    public final double w;

    public Vector4d(double x, double y, double z, double w)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }


    private double sumProducts(double a, double b, double c, double d)
    {
        return x * a + y * b + z * c + w * d;
    }


    public Vector4d add(Vector4d other)
    {
        return new Vector4d(x + other.x, y + other.y, z + other.z, w + other.w);
    }

    public Vector4d subtract(Vector4d other)
    {
        return new Vector4d(x - other.x, y - other.y, z - other.z, w - other.w);
    }

    public Vector4d multiply(Vector4d other)
    {
        return new Vector4d(x * other.x, y * other.y, z * other.z, w * other.w);
    }

    public Vector4d multiply(double other)
    {
        return new Vector4d(x * other, y * other, z * other, w * other);
    }

    public Vector4d divide(Vector4d other)
    {
        return new Vector4d(x / other.x, y / other.y, z / other.z, w / other.w);
    }

    public Vector4d divide(double other)
    {
        return new Vector4d(x / other, y / other, z / other, w / other);
    }


    public double dot(Vector4d other)
    {
        return sumProducts(other.x, other.y, other.z, other.w);
    }


    public double length()
    {
        return Math.sqrt(lengthSquared());
    }

    public double lengthSquared()
    {
        return sumProducts(x, y, z, w);
    }

    public double distance(Vector4d other)
    {
        return Math.sqrt(distanceSquared(other));
    }

    public double distanceSquared(Vector4d other)
    {
        double dx = x - other.x;
        double dy = y - other.y;
        double dz = z - other.z;
        double dt = w - other.w;

        return dx * dx + dy * dy + dz * dz + dt * dt;
    }


    public Vector4d normalize()
    {
        double length = length();

        if(length == 0)
            throw new ArithmeticException("Cannot normalize zero vector.");

        return divide(length);
    }


    public Vector4d negate()
    {
        return new Vector4d(-x, -y, -z, -w);
    }


    public Vector4d lerp(Vector4d other, double interpolation)
    {
        return new Vector4d(x + (other.x - x) * interpolation, y + (other.y - y) * interpolation, z + (other.z - z) * interpolation, w + (other.w - w) * interpolation);
    }


    public boolean isZero()
    {
        return x == 0 && y == 0 && z == 0 && w == 0;
    }


    @Override
    public String toString()
    {
        return "Vector4d(" + x + ", " + y + ", " + z + ", " + w + ")";
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (!(obj instanceof Vector4d other)) return false;

        return Double.compare(x, other.x) == 0 && Double.compare(y, other.y) == 0 && Double.compare(z, other.z) == 0 && Double.compare(w, other.w) == 0;
    }

    @Override
    public int hashCode()
    {
        int result = Double.hashCode(x);
        result = 31 * result + Double.hashCode(y);
        result = 31 * result + Double.hashCode(z);
        result = 31 * result + Double.hashCode(w);
        return result;
    }
}
