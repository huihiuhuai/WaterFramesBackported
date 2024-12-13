package toshayopack.team.creative.creativecore.common.util.math;

public enum BoxCorner {
    EUN(Facing.EAST, Facing.UP, Facing.NORTH),
    EUS(Facing.EAST, Facing.UP, Facing.SOUTH),
    EDN(Facing.EAST, Facing.DOWN, Facing.NORTH),
    EDS(Facing.EAST, Facing.DOWN, Facing.SOUTH),
    WUN(Facing.WEST, Facing.UP, Facing.NORTH),
    WUS(Facing.WEST, Facing.UP, Facing.SOUTH),
    WDN(Facing.WEST, Facing.DOWN, Facing.NORTH),
    WDS(Facing.WEST, Facing.DOWN, Facing.SOUTH);

    public final Facing x;
    public final Facing y;
    public final Facing z;
    public BoxCorner neighborOne;
    public BoxCorner neighborTwo;
    public BoxCorner neighborThree;
    public static final BoxCorner[][] FACING_CORNERS = new BoxCorner[][]{{EDN, EDS, WDN, WDS}, {EUN, EUS, WUN, WUS}, {EUN, EDN, WUN, WDN}, {EUS, EDS, WUS, WDS}, {WUN, WUS, WDN, WDS}, {EUN, EUS, EDN, EDS}};

    BoxCorner(Facing x, Facing y, Facing z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    private void init() {
        this.neighborOne = getCorner(this.x.opposite(), this.y, this.z);
        this.neighborTwo = getCorner(this.x, this.y.opposite(), this.z);
        this.neighborThree = getCorner(this.x, this.y, this.z.opposite());
    }

    public boolean isFacing(Facing facing) {
        return this.getFacing(facing.axis) == facing;
    }

    public boolean isFacingPositive(Axis axis) {
        return this.getFacing(axis).positive;
    }

    public Facing getFacing(Axis axis) {
        Facing var10000;
        switch (axis) {
            case X:
                var10000 = this.x;
                break;
            case Y:
                var10000 = this.y;
                break;
            case Z:
                var10000 = this.z;
                break;
            default:
                throw new IncompatibleClassChangeError();
        }

        return var10000;
    }

    public BoxCorner mirror(Axis axis) {
        BoxCorner var10000;
        switch (axis) {
            case X:
                var10000 = getCorner(this.x.opposite(), this.y, this.z);
                break;
            case Y:
                var10000 = getCorner(this.x, this.y.opposite(), this.z);
                break;
            case Z:
                var10000 = getCorner(this.x, this.y, this.z.opposite());
                break;
            default:
                throw new IncompatibleClassChangeError();
        }

        return var10000;
    }

    /*public BoxCorner rotate(Rotation rotation) {
        int normalX = this.x.offset();
        int normalY = this.y.offset();
        int normalZ = this.z.offset();
        return getCorner(Facing.get(Axis.X, rotation.getMatrix().getX(normalX, normalY, normalZ) > 0), Facing.get(Axis.Y, rotation.getMatrix().getY(normalX, normalY, normalZ) > 0), Facing.get(Axis.Z, rotation.getMatrix().getZ(normalX, normalY, normalZ) > 0));
    }

    public Vec3d get(ABB bb) {
        return new Vec3d(bb.get(this.x), bb.get(this.y), bb.get(this.z));
    }

    public void set(ABB bb, Vec3d vec) {
        vec.x = bb.get(this.x);
        vec.y = bb.get(this.y);
        vec.z = bb.get(this.z);
    }

    public Vec3d get(AxisAlignedBB bb) {
        return new Vec3d(BoxUtils.get(bb, this.x), BoxUtils.get(bb, this.y), BoxUtils.get(bb, this.z));
    }

    public void set(AxisAlignedBB bb, Vec3d vec) {
        vec.x = BoxUtils.get(bb, this.x);
        vec.y = BoxUtils.get(bb, this.y);
        vec.z = BoxUtils.get(bb, this.z);
    }*/

    public static BoxCorner getCornerUnsorted(Facing facing, Facing facing2, Facing facing3) {
        return getCorner(facing.axis != Axis.X ? (facing2.axis != Axis.X ? facing3 : facing2) : facing, facing.axis != Axis.Y ? (facing2.axis != Axis.Y ? facing3 : facing2) : facing, facing.axis != Axis.Z ? (facing2.axis != Axis.Z ? facing3 : facing2) : facing);
    }

    public static BoxCorner getCorner(Facing x, Facing y, Facing z) {
        BoxCorner[] var3 = values();
        int var4 = var3.length;

        for (BoxCorner corner : var3) {
            if (corner.x == x && corner.y == y && corner.z == z) {
                return corner;
            }
        }

        return null;
    }

    public static BoxCorner[] faceCorners(Facing facing) {
        return FACING_CORNERS[facing.ordinal()];
    }

    static {
        BoxCorner[] var0 = values();
        int var1 = var0.length;

        for (BoxCorner corner : var0) {
            corner.init();
        }

    }
}
