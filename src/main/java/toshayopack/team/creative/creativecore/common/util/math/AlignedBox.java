package toshayopack.team.creative.creativecore.common.util.math;

import net.minecraft.util.AxisAlignedBB;

public class AlignedBox {
    public float minX;
    public float minY;
    public float minZ;
    public float maxX;
    public float maxY;
    public float maxZ;

    public AlignedBox(float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        this.minX = minX;
        this.minY = minY;
        this.minZ = minZ;
        this.maxX = maxX;
        this.maxY = maxY;
        this.maxZ = maxZ;
    }

    public AlignedBox(AxisAlignedBB box) {
        this((float)box.minX, (float)box.minY, (float)box.minZ, (float)box.maxX, (float)box.maxY, (float)box.maxZ);
    }

    public AlignedBox() {
        this(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    public AlignedBox(AlignedBox cube) {
        this(cube.minX, cube.minY, cube.minZ, cube.maxX, cube.maxY, cube.maxZ);
    }

    public void add(float x, float y, float z) {
        this.minX += x;
        this.minY += y;
        this.minZ += z;
        this.maxX += x;
        this.maxY += y;
        this.maxZ += z;
    }

    public void sub(float x, float y, float z) {
        this.minX -= x;
        this.minY -= y;
        this.minZ -= z;
        this.maxX -= x;
        this.maxY -= y;
        this.maxZ -= z;
    }

    /*public void add(Vector3d vec) {
        this.add((float)vec.x, (float)vec.y, (float)vec.z);
    }

    public void sub(Vector3d vec) {
        this.sub((float)vec.x, (float)vec.y, (float)vec.z);
    }

    public void add(Vec3i vec) {
        this.add((float)vec.getX(), (float)vec.getY(), (float)vec.getZ());
    }

    public void sub(Vec3i vec) {
        this.sub((float)vec.getX(), (float)vec.getY(), (float)vec.getZ());
    }*/

    public void scale(float scale) {
        this.minX *= scale;
        this.minY *= scale;
        this.minZ *= scale;
        this.maxX *= scale;
        this.maxY *= scale;
        this.maxZ *= scale;
    }

    public float getSize(Axis axis) {
        float var10000;
        switch (axis) {
            case X:
                var10000 = this.maxX - this.minX;
                break;
            case Y:
                var10000 = this.maxY - this.minY;
                break;
            case Z:
                var10000 = this.maxZ - this.minZ;
                break;
            default:
                throw new IncompatibleClassChangeError();
        }

        return var10000;
    }

    /*public Vec3d getSize() {
        return new Vec3d((double)(this.maxX - this.minX), (double)(this.maxY - this.minY), (double)(this.maxZ - this.minZ));
    }

    public Vec3d getCenter() {
        return new Vec3d((double)(this.maxX + this.minX) * 0.5, (double)(this.maxY + this.minY) * 0.5, (double)(this.maxZ + this.minZ) * 0.5);
    }*/

    public String toString() {
        return "cube[" + this.minX + ", " + this.minY + ", " + this.minZ + " -> " + this.maxX + ", " + this.maxY + ", " + this.maxZ + "]";
    }

    public Vec3f getCorner(BoxCorner corner) {
        return new Vec3f(this.get(corner.x), this.get(corner.y), this.get(corner.z));
    }

    public AxisAlignedBB getBB() {
        return AxisAlignedBB.getBoundingBox(this.minX, this.minY, this.minZ, this.maxX, this.maxY, this.maxZ);
    }

    public AxisAlignedBB getBB(int x, int y, int z) {
        return AxisAlignedBB.getBoundingBox(
                this.minX + (float)x, this.minY + (float)y, this.minZ + (float)z,
                this.maxX + (float)x, this.maxY + (float)x, this.maxZ + (float)z
        );
    }

    /*public VoxelShape voxelShape() {
        return Shapes.box((double)this.minX, (double)this.minY, (double)this.minZ, (double)this.maxX, (double)this.maxY, (double)this.maxZ);
    }

    public VoxelShape voxelShape(BlockPos pos) {
        return Shapes.box((double)(this.minX + (float)pos.getX()), (double)(this.minY + (float)pos.getY()), (double)(this.minZ + (float)pos.getZ()), (double)(this.maxX + (float)pos.getX()), (double)(this.maxY + (float)pos.getY()), (double)(this.maxZ + (float)pos.getZ()));
    }

    public void rotate(Rotation rotation, Vec3f center) {
        Vec3f low = new Vec3f(this.minX, this.minY, this.minZ);
        Vec3f high = new Vec3f(this.maxX, this.maxY, this.maxZ);
        low.sub(center);
        high.sub(center);
        rotation.getMatrix().transform(low);
        rotation.getMatrix().transform(high);
        low.add(center);
        high.add(center);
        this.set(low.x, low.y, low.z, high.x, high.y, high.z);
    }

    public void rotate(Matrix3 matrix, Vec3f center) {
        Vec3f low = new Vec3f(this.minX, this.minY, this.minZ);
        Vec3f high = new Vec3f(this.maxX, this.maxY, this.maxZ);
        low.sub(center);
        high.sub(center);
        matrix.transform(low);
        matrix.transform(high);
        low.add(center);
        high.add(center);
        this.set(low.x, low.y, low.z, high.x, high.y, high.z);
    }

    public void set(float x, float y, float z, float x2, float y2, float z2) {
        this.minX = Math.min(x, x2);
        this.minY = Math.min(y, y2);
        this.minZ = Math.min(z, z2);
        this.maxX = Math.max(x, x2);
        this.maxY = Math.max(y, y2);
        this.maxZ = Math.max(z, z2);
    }

    public BlockPos getOffset() {
        return new BlockPos(Mth.floor(this.minX), Mth.floor(this.minY), Mth.floor(this.minZ));
    }*/

    public float get(Facing facing) {
        float var10000;
        switch (facing) {
            case EAST:
                var10000 = this.maxX;
                break;
            case WEST:
                var10000 = this.minX;
                break;
            case UP:
                var10000 = this.maxY;
                break;
            case DOWN:
                var10000 = this.minY;
                break;
            case SOUTH:
                var10000 = this.maxZ;
                break;
            case NORTH:
                var10000 = this.minZ;
                break;
            default:
                throw new IncompatibleClassChangeError();
        }

        return var10000;
    }

    public void set(Facing facing, float value) {
        switch (facing) {
            case EAST:
                this.maxX = value;
                break;
            case WEST:
                this.minX = value;
                break;
            case UP:
                this.maxY = value;
                break;
            case DOWN:
                this.minY = value;
                break;
            case SOUTH:
                this.maxZ = value;
                break;
            case NORTH:
                this.minZ = value;
        }

    }

    public void setMin(Axis axis, float value) {
        switch (axis) {
            case X:
                this.minX = value;
                break;
            case Y:
                this.minY = value;
                break;
            case Z:
                this.minZ = value;
        }

    }

    public float getMin(Axis axis) {
        float var10000;
        switch (axis) {
            case X:
                var10000 = this.minX;
                break;
            case Y:
                var10000 = this.minY;
                break;
            case Z:
                var10000 = this.minZ;
                break;
            default:
                throw new IncompatibleClassChangeError();
        }

        return var10000;
    }

    public void setMax(Axis axis, float value) {
        switch (axis) {
            case X:
                this.maxX = value;
                break;
            case Y:
                this.maxY = value;
                break;
            case Z:
                this.maxZ = value;
        }

    }

    public float getMax(Axis axis) {
        float var10000;
        switch (axis) {
            case X:
                var10000 = this.maxX;
                break;
            case Y:
                var10000 = this.maxY;
                break;
            case Z:
                var10000 = this.maxZ;
                break;
            default:
                throw new IncompatibleClassChangeError();
        }

        return var10000;
    }

    public void grow(Axis axis, float value) {
        value /= 2.0F;
        this.setMin(axis, this.getMin(axis) - value);
        this.setMax(axis, this.getMax(axis) + value);
    }

    public void shrink(Axis axis, float value) {
        value /= 2.0F;
        this.setMin(axis, this.getMin(axis) + value);
        this.setMax(axis, this.getMax(axis) - value);
    }
}
