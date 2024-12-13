package toshayopack.team.creative.creativecore.common.util.math;

import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;

public enum Facing {
    DOWN(Axis.Y, false, Vec3.createVectorHelper(0, -1, 0), -1) {
        public Facing opposite() {
            return Facing.UP;
        }

        public EnumFacing toVanilla() {
            return EnumFacing.DOWN;
        }

        public double get(AxisAlignedBB bb) {
            return bb.minY;
        }

        /*public Vector3f rotation() {
            return Vector3f.YN;
        }*/

        public AxisAlignedBB set(AxisAlignedBB bb, double value) {
            bb.minY = value;
            return bb;
        }

        public float get(float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
            return minY;
        }

        public double get(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
            return minY;
        }

        public int get(int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
            return minY;
        }
    },
    UP(Axis.Y, true, Vec3.createVectorHelper(0, 1, 0), -1) {
        public Facing opposite() {
            return Facing.DOWN;
        }

        public EnumFacing toVanilla() {
            return EnumFacing.UP;
        }

        public double get(AxisAlignedBB bb) {
            return bb.maxY;
        }

        /*public Vector3f rotation() {
            return Vector3f.YP;
        }*/

        public AxisAlignedBB set(AxisAlignedBB bb, double value) {
            bb.maxY = value;
            return bb;
        }

        public float get(float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
            return maxY;
        }

        public double get(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
            return maxY;
        }

        public int get(int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
            return maxY;
        }
    },
    NORTH(Axis.Z, false, Vec3.createVectorHelper(0, 0, -1), 2) {
        public Facing opposite() {
            return SOUTH;
        }

        public EnumFacing toVanilla() {
            return EnumFacing.NORTH;
        }

        public double get(AxisAlignedBB bb) {
            return bb.minZ;
        }

        /*public Vector3f rotation() {
            return Vector3f.ZN;
        }*/

        public AxisAlignedBB set(AxisAlignedBB bb, double value) {
            bb.minZ = value;
            return bb;
        }

        public float get(float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
            return minZ;
        }

        public double get(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
            return minZ;
        }

        public int get(int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
            return minZ;
        }
    },
    SOUTH(Axis.Z, true, Vec3.createVectorHelper(0, 0, 1), 0) {
        public Facing opposite() {
            return Facing.NORTH;
        }

        public EnumFacing toVanilla() {
            return EnumFacing.SOUTH;
        }

        public double get(AxisAlignedBB bb) {
            return bb.maxZ;
        }

        /*public Vector3f rotation() {
            return Vector3f.ZP;
        }*/

        public AxisAlignedBB set(AxisAlignedBB bb, double value) {
            bb.maxZ = value;
            return bb;
        }

        public float get(float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
            return maxZ;
        }

        public double get(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
            return maxZ;
        }

        public int get(int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
            return maxZ;
        }
    },
    WEST(Axis.X, false, Vec3.createVectorHelper(-1, 0, 0), 1) {
        public Facing opposite() {
            return Facing.EAST;
        }

        public EnumFacing toVanilla() {
            return EnumFacing.WEST;
        }

        public double get(AxisAlignedBB bb) {
            return bb.minX;
        }

        /*public Vector3f rotation() {
            return Vector3f.XN;
        }*/

        public AxisAlignedBB set(AxisAlignedBB bb, double value) {
            bb.minX = value;
            return bb;
        }

        public float get(float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
            return minX;
        }

        public double get(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
            return minX;
        }

        public int get(int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
            return minX;
        }
    },
    EAST(Axis.X, true, Vec3.createVectorHelper(1, 0, 0), 3) {
        public Facing opposite() {
            return Facing.WEST;
        }

        public EnumFacing toVanilla() {
            return EnumFacing.EAST;
        }

        public double get(AxisAlignedBB bb) {
            return bb.maxX;
        }

        /*public Vector3f rotation() {
            return Vector3f.XP;
        }*/

        public AxisAlignedBB set(AxisAlignedBB bb, double value) {
            bb.maxX = value;
            return bb;
        }

        public float get(float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
            return maxX;
        }

        public double get(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
            return maxX;
        }

        public int get(int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
            return maxX;
        }
    };

    public static final Facing[] VALUES = new Facing[]{DOWN, UP, NORTH, SOUTH, WEST, EAST};
    public static final Facing[] HORIZONTA_VALUES = new Facing[]{SOUTH, WEST, NORTH, EAST};
    public static final String[] FACING_NAMES = new String[]{"down", "up", "north", "south", "west", "east"};
    public static final String[] HORIZONTAL_FACING_NAMES = new String[]{"north", "south", "west", "east"};
    public final String name = this.name().toLowerCase();
    public final Axis axis;
    public final boolean positive;
    public final Vec3 normal;
    //public final NormalPlaneF plane;
    public final int horizontalIndex;

    public static Facing get(int index) {
        return VALUES[index];
    }

    public static Facing get(EnumFacing direction) {
        if (direction == null) {
            return null;
        } else {
            Facing var10000;
            switch (direction) {
                case DOWN:
                    var10000 = DOWN;
                    break;
                case UP:
                    var10000 = UP;
                    break;
                case NORTH:
                    var10000 = NORTH;
                    break;
                case SOUTH:
                    var10000 = SOUTH;
                    break;
                case WEST:
                    var10000 = WEST;
                    break;
                case EAST:
                    var10000 = EAST;
                    break;
                default:
                    throw new IncompatibleClassChangeError();
            }

            return var10000;
        }
    }

    public static Facing get(Axis axis, boolean positive) {
        Facing var10000;
        switch (axis) {
            case X:
                var10000 = positive ? EAST : WEST;
                break;
            case Y:
                var10000 = positive ? UP : DOWN;
                break;
            case Z:
                var10000 = positive ? SOUTH : NORTH;
                break;
            default:
                throw new IllegalArgumentException();
        }

        return var10000;
    }

    public static Facing getHorizontal(int index) {
        return HORIZONTA_VALUES[index];
    }

    /*public static Facing direction(Vec3i pos, Vec3i second) {
        if (pos.getX() == second.getX()) {
            if (pos.getY() == second.getY()) {
                return pos.getZ() == second.getZ() + 1 ? SOUTH : NORTH;
            } else {
                return pos.getY() == second.getY() + 1 ? UP : DOWN;
            }
        } else {
            return pos.getX() == second.getX() + 1 ? EAST : WEST;
        }
    }*/

    public static Facing nearest(Vec3f vec) {
        return nearest(vec.x, vec.y, vec.z);
    }

    public static Facing nearest(float x, float y, float z) {
        if (x == 0.0F && y == 0.0F && z == 0.0F) {
            return null;
        } else {
            Facing facing = null;
            float distance = Float.MIN_VALUE;

            for (Facing f : VALUES) {
                float newDistance = x * (float) f.normal.xCoord + y * (float) f.normal.yCoord + z * (float) f.normal.zCoord;
                if (newDistance > distance) {
                    distance = newDistance;
                    facing = f;
                }
            }

            return facing;
        }
    }

    Facing(Axis axis, boolean positive, Vec3 normal, int horizontalIndex) {
        this.axis = axis;
        this.positive = positive;
        this.normal = normal;
        //this.plane = new NormalPlaneF(this);
        this.horizontalIndex = horizontalIndex;
    }

    public int offset() {
        return this.positive ? 1 : -1;
    }

    public int offset(Axis axis) {
        return this.axis == axis ? this.offset() : 0;
    }

    /*public Component translate() {
        return Component.translatable("facing." + this.name);
    }*/

    public abstract Facing opposite();

    public abstract EnumFacing toVanilla();

    public Axis one() {
        return this.axis.one();
    }

    public Axis two() {
        return this.axis.two();
    }

    public Axis getUAxis() {
        Axis var10000;
        switch (this.axis) {
            case X:
                var10000 = Axis.Z;
                break;
            case Y:
            case Z:
                var10000 = Axis.X;
                break;
            default:
                var10000 = null;
        }

        return var10000;
    }

    public Axis getVAxis() {
        Axis var10000;
        switch (this.axis) {
            case X:
            case Z:
                var10000 = Axis.Y;
                break;
            case Y:
                var10000 = Axis.Z;
                break;
            default:
                var10000 = null;
        }

        return var10000;
    }

    public float getU(float x, float y, float z) {
        float var10000;
        switch (this.axis) {
            case X:
                var10000 = z;
                break;
            case Y:
            case Z:
                var10000 = x;
                break;
            default:
                var10000 = 0.0F;
        }

        return var10000;
    }

    public float getV(float x, float y, float z) {
        float var10000;
        switch (this.axis) {
            case X:
            case Z:
                var10000 = y;
                break;
            case Y:
                var10000 = z;
                break;
            default:
                var10000 = 0.0F;
        }

        return var10000;
    }

    public abstract double get(AxisAlignedBB var1);

    public abstract AxisAlignedBB set(AxisAlignedBB var1, double var2);

    //public abstract Vector3f rotation();

    public abstract float get(float var1, float var2, float var3, float var4, float var5, float var6);

    public abstract double get(double var1, double var3, double var5, double var7, double var9, double var11);

    public abstract int get(int var1, int var2, int var3, int var4, int var5, int var6);
}
