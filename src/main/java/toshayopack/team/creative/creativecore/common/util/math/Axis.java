package toshayopack.team.creative.creativecore.common.util.math;

import net.minecraft.util.Vec3;

public enum Axis {
    X {
        public double get(double x, double y, double z) {
            return x;
        }

        public float get(float x, float y, float z) {
            return x;
        }

        public int get(int x, int y, int z) {
            return x;
        }

        public <T> T get(T x, T y, T z) {
            return x;
        }

        /*public int get(Vec3i vec) {
            return vec.getX();
        }

        public Vec3i set(Vec3i vec, int value) {
            return new Vec3i(value, vec.getY(), vec.getZ());
        }

        public BlockPos set(BlockPos pos, int value) {
            return new BlockPos(value, pos.getY(), pos.getZ());
        }

        public void set(BlockPos.MutableBlockPos pos, int value) {
            pos.setX(value);
        }

        public SectionPos set(SectionPos pos, int value) {
            return SectionPos.of(value, pos.getY(), pos.getZ());
        }

        public int get(ChunkPos pos) {
            return pos.x;
        }

        public ChunkPos set(ChunkPos pos, int value) {
            return new ChunkPos(value, pos.z);
        }*/

        public double get(Vec3 vec) {
            return vec.xCoord;
        }

        public Vec3 set(Vec3 vec, double value) {
            return Vec3.createVectorHelper(value, vec.yCoord, vec.zCoord);
        }

        /*public double get(Vector3d vec) {
            return vec.x;
        }

        public void set(Vector3d vec, double value) {
            vec.x = value;
        }

        public float get(Vector3f vec) {
            return vec.x();
        }

        public void set(Vector3f vec, float value) {
            vec.setX(value);
        }*/

        public Axis one() {
            return Axis.Y;
        }

        public Axis two() {
            return Axis.Z;
        }

        public Facing facing(boolean positive) {
            return positive ? Facing.EAST : Facing.WEST;
        }

        /*public Direction.Axis toVanilla() {
            return net.minecraft.core.Direction.Axis.X;
        }

        public Vec3i mirror(Vec3i vec) {
            return new Vec3i(-vec.getX(), vec.getY(), vec.getZ());
        }

        public BlockPos mirror(BlockPos vec) {
            return new BlockPos(-vec.getX(), vec.getY(), vec.getZ());
        }*/
    },
    Y {
        public double get(double x, double y, double z) {
            return y;
        }

        public float get(float x, float y, float z) {
            return y;
        }

        public int get(int x, int y, int z) {
            return y;
        }

        public <T> T get(T x, T y, T z) {
            return y;
        }

        /*public int get(Vec3i vec) {
            return vec.getY();
        }

        public Vec3i set(Vec3i vec, int value) {
            return new Vec3i(vec.getX(), value, vec.getZ());
        }

        public BlockPos set(BlockPos pos, int value) {
            return new BlockPos(pos.getX(), value, pos.getZ());
        }

        public void set(BlockPos.MutableBlockPos pos, int value) {
            pos.setY(value);
        }

        public SectionPos set(SectionPos pos, int value) {
            return SectionPos.of(pos.getX(), value, pos.getZ());
        }

        public int get(ChunkPos pos) {
            throw new UnsupportedOperationException();
        }

        public ChunkPos set(ChunkPos pos, int value) {
            throw new UnsupportedOperationException();
        }*/

        public double get(Vec3 vec) {
            return vec.yCoord;
        }

        public Vec3 set(Vec3 vec, double value) {
            return Vec3.createVectorHelper(vec.xCoord, value, vec.zCoord);
        }

        /*public double get(Vector3d vec) {
            return vec.y;
        }

        public void set(Vector3d vec, double value) {
            vec.y = value;
        }

        public float get(Vector3f vec) {
            return vec.y();
        }

        public void set(Vector3f vec, float value) {
            vec.setY(value);
        }*/

        public Axis one() {
            return Axis.Z;
        }

        public Axis two() {
            return Axis.X;
        }

        public Facing facing(boolean positive) {
            return positive ? Facing.UP : Facing.DOWN;
        }

        /*public Direction.Axis toVanilla() {
            return net.minecraft.core.Direction.Axis.Y;
        }

        public Vec3i mirror(Vec3i vec) {
            return new Vec3i(vec.getX(), -vec.getY(), vec.getZ());
        }

        public BlockPos mirror(BlockPos vec) {
            return new BlockPos(vec.getX(), -vec.getY(), vec.getZ());
        }*/
    },
    Z {
        public double get(double x, double y, double z) {
            return z;
        }

        public float get(float x, float y, float z) {
            return z;
        }

        public int get(int x, int y, int z) {
            return z;
        }

        public <T> T get(T x, T y, T z) {
            return z;
        }

        /*public int get(Vec3i vec) {
            return vec.getZ();
        }

        public Vec3i set(Vec3i vec, int value) {
            return new Vec3i(vec.getX(), vec.getY(), value);
        }

        public BlockPos set(BlockPos pos, int value) {
            return new BlockPos(pos.getX(), pos.getY(), value);
        }

        public void set(BlockPos.MutableBlockPos pos, int value) {
            pos.setZ(value);
        }

        public SectionPos set(SectionPos pos, int value) {
            return SectionPos.of(pos.getX(), pos.getY(), value);
        }

        public int get(ChunkPos pos) {
            return pos.z;
        }

        public ChunkPos set(ChunkPos pos, int value) {
            return new ChunkPos(pos.x, value);
        }*/

        public double get(Vec3 vec) {
            return vec.zCoord;
        }

        public Vec3 set(Vec3 vec, double value) {
            return Vec3.createVectorHelper(vec.xCoord, vec.yCoord, value);
        }

        /*public double get(Vector3d vec) {
            return vec.z;
        }

        public void set(Vector3d vec, double value) {
            vec.z = value;
        }

        public float get(Vector3f vec) {
            return vec.z();
        }

        public void set(Vector3f vec, float value) {
            vec.setZ(value);
        }*/

        public Axis one() {
            return Axis.X;
        }

        public Axis two() {
            return Axis.Y;
        }

        public Facing facing(boolean positive) {
            return positive ? Facing.SOUTH : Facing.NORTH;
        }

        /*public Direction.Axis toVanilla() {
            return net.minecraft.core.Direction.Axis.Z;
        }

        public Vec3i mirror(Vec3i vec) {
            return new Vec3i(vec.getX(), vec.getY(), -vec.getZ());
        }

        public BlockPos mirror(BlockPos vec) {
            return new BlockPos(vec.getX(), vec.getY(), -vec.getZ());
        }*/
    };

    Axis() {
    }

    /*public static Axis get(Direction.Axis axis) {
        Axis var10000;
        switch (axis) {
            case X:
                var10000 = X;
                break;
            case Y:
                var10000 = Y;
                break;
            case Z:
                var10000 = Z;
                break;
            default:
                throw new IllegalArgumentException();
        }

        return var10000;
    }*/

    public static Axis third(Axis one, Axis two) {
        Axis var10000;
        switch (one) {
            case X:
                var10000 = two == Y ? Z : Y;
                break;
            case Y:
                var10000 = two == X ? Z : X;
                break;
            case Z:
                var10000 = two == Y ? X : Y;
                break;
            default:
                throw new IncompatibleClassChangeError();
        }

        return var10000;
    }

    /*public static Axis getMirrorAxis(Mirror mirrorIn) {
        Axis var10000;
        switch (mirrorIn) {
            case FRONT_BACK:
                var10000 = X;
                break;
            case LEFT_RIGHT:
                var10000 = Z;
                break;
            default:
                var10000 = null;
        }

        return var10000;
    }*/

    public abstract Axis one();

    public abstract Axis two();

    public abstract Facing facing(boolean var1);

    public abstract double get(double var1, double var3, double var5);

    public abstract float get(float var1, float var2, float var3);

    public abstract int get(int var1, int var2, int var3);

    /*public abstract int get(Vec3i var1);

    public abstract Vec3i set(Vec3i var1, int var2);

    public abstract BlockPos set(BlockPos var1, int var2);

    public abstract void set(BlockPos.MutableBlockPos var1, int var2);

    public abstract SectionPos set(SectionPos var1, int var2);

    public abstract int get(ChunkPos var1);

    public abstract ChunkPos set(ChunkPos var1, int var2);*/

    public abstract double get(Vec3 var1);

    public abstract Vec3 set(Vec3 var1, double var2);

    /*public abstract double get(Vector3d var1);

    public abstract void set(Vector3d var1, double var2);

    public abstract float get(Vector3f var1);

    public abstract void set(Vector3f var1, float var2);*/

    public abstract <T> T get(T var1, T var2, T var3);

    //public abstract Direction.Axis toVanilla();

    public Facing mirror(Facing facing) {
        return facing.axis == this ? facing.opposite() : facing;
    }

    /*public Direction mirror(Direction facing) {
        return facing.getAxis() == this.toVanilla() ? facing.getOpposite() : facing;
    }

    public abstract Vec3i mirror(Vec3i var1);

    public abstract BlockPos mirror(BlockPos var1);*/

    public void mirror(Vec3d vec) {
        vec.set(this, -vec.get(this));
    }

    public void mirror(Vec3f vec) {
        vec.set(this, -vec.get(this));
    }
}

