package cat.breadcat;

import cat.breadcat.trajectory.vector.Vector3d;

public class Main
{
    public static void main(String[] args)
    {
        Vector3d v1 = new Vector3d(2,3,4);
        Vector3d v2 = new Vector3d(5,6,7);
        Vector3d v3 = v1.cross(v2);

        System.out.println(v3);
    }
}