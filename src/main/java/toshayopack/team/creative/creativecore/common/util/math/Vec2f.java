package toshayopack.team.creative.creativecore.common.util.math;

public class Vec2f extends VecNf<Vec2f> {
    public float x;
    public float y;

    public Vec2f() {
    }

    public Vec2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vec2f(Vec2f vec) {
        super(vec);
    }

    public void set(Vec2f vec) {
        this.x = vec.x;
        this.y = vec.y;
    }

    public float get(int dim) {
        if (dim == 0) {
            return this.x;
        } else {
            return dim == 1 ? this.y : 0.0F;
        }
    }

    public void set(int dim, float value) {
        if (dim == 0) {
            this.x = value;
        } else if (dim == 1) {
            this.y = value;
        }

    }

    public int dimensions() {
        return 2;
    }

    public Vec2f copy() {
        return new Vec2f(this.x, this.y);
    }

    public void add(float x, float y) {
        this.x += x;
        this.y += y;
    }

    public void add(Vec2f vec) {
        this.x += this.x;
        this.y += this.y;
    }

    public void sub(float x, float y) {
        this.x -= x;
        this.y -= y;
    }

    public void sub(Vec2f vec) {
        this.x -= vec.x;
        this.y -= vec.y;
    }

    public void scale(double scale) {
        this.x *= (float)scale;
        this.y *= (float)scale;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Vec2f)) {
            return false;
        } else {
            return ((Vec2f)obj).x == this.x && ((Vec2f)obj).y == this.y;
        }
    }

    public boolean epsilonEquals(Vec2f var1, float var2) {
        float var3 = this.x - var1.x;
        if (Float.isNaN(var3)) {
            return false;
        } else if ((var3 < 0.0F ? -var3 : var3) > var2) {
            return false;
        } else {
            var3 = this.y - var1.y;
            if (Float.isNaN(var3)) {
                return false;
            } else {
                return (var3 < 0.0F ? -var3 : var3) <= var2;
            }
        }
    }

    public double distance(Vec2f vec) {
        double x = this.x - vec.x;
        double y = this.y - vec.y;
        return Math.sqrt(x * x + y * y);
    }

    public double distanceSqr(Vec2f vec) {
        double x = this.x - vec.x;
        double y = this.y - vec.y;
        return x * x + y * y;
    }

    public double length() {
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }

    public double lengthSquared() {
        return this.x * this.x + this.y * this.y;
    }

    public double angle(Vec2f vec) {
        double vDot = (double)this.dot(vec) / (this.length() * vec.length());
        if (vDot < -1.0) {
            vDot = -1.0;
        }

        if (vDot > 1.0) {
            vDot = 1.0;
        }

        return Math.acos(vDot);
    }

    public float dot(Vec2f vec) {
        return this.x * vec.x + this.y * vec.y;
    }
}

