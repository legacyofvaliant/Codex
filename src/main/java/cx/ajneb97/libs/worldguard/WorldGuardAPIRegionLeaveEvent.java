package cx.ajneb97.libs.worldguard;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class WorldGuardAPIRegionLeaveEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private Player player;
    private String region;

    public WorldGuardAPIRegionLeaveEvent(Player player, String region) {
        this.player = player;
        this.region = region;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public Player getPlayer() {
        return player;
    }

    public String getRegion() {
        return region;
    }

    @Override
    public HandlerList getHandlers() {
        // TODO Auto-generated method stub
        return handlers;
    }
}
