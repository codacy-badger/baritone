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

package baritone.api.event.events;

import baritone.api.event.events.type.EventState;
import baritone.api.event.events.type.ManagedPlayerEvent;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

/**
 * @author Brady
 * @since 8/21/2018
 */
public final class RotationMoveEvent extends ManagedPlayerEvent {

    /**
     * The type of event
     */
    private final Type type;

    /**
     * The state of the event
     */
    private final EventState state;

    public RotationMoveEvent(EntityPlayerSP player, EventState state, Type type) {
        super(player);
        this.state = state;
        this.type = type;
    }

    /**
     * @return The state of the event
     */
    public final EventState getState() {
        return this.state;
    }

    /**
     * @return The type of the event
     */
    public final Type getType() {
        return this.type;
    }

    public enum Type {

        /**
         * Called when the player's motion is updated.
         *
         * @see Entity#moveRelative(float, float, float, float)
         */
        MOTION_UPDATE,

        /**
         * Called when the player jumps.
         *
         * @see EntityLivingBase#jump
         */
        JUMP
    }
}
