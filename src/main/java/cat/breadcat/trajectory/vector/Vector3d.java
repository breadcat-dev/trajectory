package cat.breadcat.trajectory.vector;

public final class Vector3d
{
    public static final Vector3d ZERO = new Vector3d(0, 0, 0);
    public static final Vector3d ONE  = new Vector3d(1, 1, 1);
    public static final Vector3d UNIT_X  = new Vector3d(1, 0, 0);
    public static final Vector3d UNIT_Y  = new Vector3d(0, 1, 0);
    public static final Vector3d UNIT_Z  = new Vector3d(0, 0, 1);

    public final double x;
    public final double y;
    public final double z;

    public Vector3d(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }


    private double sumProducts(double a, double b, double c)
    {
        return x * a + y * b + z * c;
    }


    public Vector3d add(Vector3d other)
    {
        return new Vector3d(x + other.x, y + other.y, z + other.z);
    }

    public Vector3d subtract(Vector3d other)
    {
        return new Vector3d(x - other.x, y - other.y, z - other.z);
    }

    public Vector3d multiply(Vector3d other)
    {
        return new Vector3d(x * other.x, y * other.y, z * other.z);
    }

    public Vector3d multiply(double other)
    {
        return new Vector3d(x * other, y * other, z * other);
    }

    public Vector3d divide(Vector3d other)
    {
        return new Vector3d(x / other.x, y / other.y, z / other.z);
    }

    public Vector3d divide(double other)
    {
        return new Vector3d(x / other, y / other, z / other);
    }


    public double dot(Vector3d other)
    {
        return sumProducts(other.x, other.y, other.z);
    }

    public Vector3d cross(Vector3d other)
    {
        return new Vector3d(y * other.z - z * other.y, z * other.x - x * other.z, x * other.y - y * other.x);
    }


    public double length()
    {
        return Math.sqrt(lengthSquared());
    }

    public double lengthSquared()
    {
        return sumProducts(x, y, z);
    }

    public double distance(Vector3d other)
    {
        return Math.sqrt(distanceSquared(other));
    }

    public double distanceSquared(Vector3d other)
    {
        double dx = x - other.x;
        double dy = y - other.y;
        double dz = z - other.z;

        return dx * dx + dy * dy + dz * dz;
    }


    public Vector3d normalize()
    {
        double length = length();

        if(length == 0)
            throw new ArithmeticException("Cannot normalize zero vector.");

        return divide(length);
    }


    public Vector3d negate()
    {
        return new Vector3d(-x, -y, -z);
    }


    public Vector3d lerp(Vector3d other, double interpolation)
    {
        return new Vector3d(x + (other.x - x) * interpolation, y + (other.y - y) * interpolation, z + (other.z - z) * interpolation);
    }


    public boolean isZero()
    {
        return x == 0 && y == 0 && z == 0;
    }


    @Override
    public String toString()
    {
        return "Vector3d(" + x + ", " + y + ", " + z + ")";
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (!(obj instanceof Vector3d other)) return false;

        return Double.compare(x, other.x) == 0 && Double.compare(y, other.y) == 0 && Double.compare(z, other.z) == 0;
    }

    @Override
    public int hashCode()
    {
        int result = Double.hashCode(x);
        result = 31 * result + Double.hashCode(y);
        result = 31 * result + Double.hashCode(z);
        return result;
    }
}
