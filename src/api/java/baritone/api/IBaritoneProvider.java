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

package baritone.api;

import baritone.api.behavior.*;
import baritone.api.cache.IWorldProvider;
import baritone.api.cache.IWorldScanner;
import baritone.api.event.listener.IGameEventListener;

/**
 * @author Brady
 * @since 9/29/2018
 */
public interface IBaritoneProvider {

    /**
     * @see IFollowBehavior
     *
     * @return The {@link IFollowBehavior} instance
     */
    IFollowBehavior getFollowBehavior();

    /**
     * @see ILookBehavior
     *
     * @return The {@link ILookBehavior} instance
     */
    ILookBehavior getLookBehavior();

    /**
     * @see IMemoryBehavior
     *
     * @return The {@link IMemoryBehavior} instance
     */
    IMemoryBehavior getMemoryBehavior();

    /**
     * @see IMineBehavior
     *
     * @return The {@link IMineBehavior} instance
     */
    IMineBehavior getMineBehavior();

    /**
     * @see IPathingBehavior
     *
     * @return The {@link IPathingBehavior} instance
     */
    IPathingBehavior getPathingBehavior();

    /**
     * @see IWorldProvider
     *
     * @return The {@link IWorldProvider} instance
     */
    IWorldProvider getWorldProvider();

    /**
     * @see IWorldScanner
     *
     * @return The {@link IWorldScanner} instance
     */
    IWorldScanner getWorldScanner();

    /**
     * Registers a {@link IGameEventListener} with Baritone's "event bus".
     *
     * @param listener The listener
     */
    void registerEventListener(IGameEventListener listener);
}
