/*
 * This file is part of Baritone.
 *
 * Baritone is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Baritone is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Baritone.  If not, see <https://www.gnu.org/licenses/>.
 */

package baritone.utils;

import baritone.api.utils.Rotation;
import net.minecraft.block.BlockFire;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import static baritone.utils.Helper.HELPER;

/**
 * @author Brady
 * @since 8/1/2018 12:56 AM
 */
public final class Utils {

    /**
     * Constant that a degree value is multiplied by to get the equivalent radian value
     */
    public static final double DEG_TO_RAD = Math.PI / 180.0;

    /**
     * Constant that a radian value is multiplied by to get the equivalent degree value
     */
    public static final double RAD_TO_DEG = 180.0 / Math.PI;

    public static Rotation calcRotationFromCoords(BlockPos orig, BlockPos dest) {
        return calcRotationFromVec3d(vec3dFromBlockPos(orig), vec3dFromBlockPos(dest));
    }

    /**
     * Calculates rotation to given Vec<sub>dest</sub> from Vec<sub>orig</sub>
     *
     * @param orig
     * @param dest
     * @return Rotation {@link Rotation}
     */
    public static Rotation calcRotationFromVec3d(Vec3d orig, Vec3d dest) {
        double[] delta = {orig.x - dest.x, orig.y - dest.y, orig.z - dest.z};
        double yaw = MathHelper.atan2(delta[0], -delta[2]);
        double dist = Math.sqrt(delta[0] * delta[0] + delta[2] * delta[2]);
        double pitch = MathHelper.atan2(delta[1], dist);
        return new Rotation(
                (float) radToDeg(yaw),
                (float) radToDeg(pitch)
        );
    }

    /**
     * Calculates rotation to given Vec<sub>dest</sub> from Vec<sub>orig</sub>
     *
     * @param orig
     * @param dest
     * @return Rotation {@link Rotation}
     */
    public static Rotation calcRotationFromVec3d(Vec3d orig, Vec3d dest, Rotation current) {
        return wrapAnglesToRelative(current, calcRotationFromVec3d(orig, dest));
    }

    public static Vec3d calcCenterFromCoords(BlockPos orig, World world) {
        IBlockState b = BlockStateInterface.get(orig);
        AxisAlignedBB bbox = b.getBoundingBox(world, orig);
        double xDiff = (bbox.minX + bbox.maxX) / 2;
        double yDiff = (bbox.minY + bbox.maxY) / 2;
        double zDiff = (bbox.minZ + bbox.maxZ) / 2;
        if (b.getBlock() instanceof BlockFire) {//look at bottom of fire when putting it out
            yDiff = 0;
        }
        return new Vec3d(
                orig.getX() + xDiff,
                orig.getY() + yDiff,
                orig.getZ() + zDiff
        );
    }

    public static Vec3d getBlockPosCenter(BlockPos pos) {
        return new Vec3d(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
    }

    public static Rotation wrapAnglesToRelative(Rotation current, Rotation target) {
        return target.subtract(current).normalize().add(current);
    }

    public static Vec3d vec3dFromBlockPos(BlockPos orig) {
        return new Vec3d(orig.getX() + 0.0D, orig.getY() + 0.0D, orig.getZ() + 0.0D);
    }

    public static double distanceToCenter(BlockPos pos, double x, double y, double z) {
        double xdiff = x - (pos.getX() + 0.5D);
        double ydiff = y - (pos.getY() + 0.5D);
        double zdiff = z - (pos.getZ() + 0.5D);
        return Math.sqrt(xdiff * xdiff + ydiff * ydiff + zdiff * zdiff);
    }

    public static double playerDistanceToCenter(BlockPos pos) {
        EntityPlayerSP player = HELPER.player();
        return distanceToCenter(pos, player.posX, player.posY, player.posZ);
    }

    public static double playerFlatDistanceToCenter(BlockPos pos) {
        EntityPlayerSP player = HELPER.player();
        return distanceToCenter(pos, player.posX, pos.getY() + 0.5, player.posZ);
    }

    public static double degToRad(double deg) {
        return deg * DEG_TO_RAD;
    }

    public static double radToDeg(double rad) {
        return rad * RAD_TO_DEG;
    }
}
