package lu.uni.rpg.model.Entities;

import lu.uni.rpg.RPG;
import lu.uni.rpg.event.events.EntityDeathEvent;
import lu.uni.rpg.model.Blocks.Block;
import lu.uni.rpg.model.Rooms.Room;

import java.util.Objects;
import java.util.UUID;

// Class Entity
public abstract class Entity {
    protected final UUID uuid = UUID.randomUUID();
    protected int x;
    protected int y;
    protected int damage;
    protected int hp;
    protected Block onBlock = null;
    protected boolean alive = true;

    private Room room;

    @Deprecated
    public Entity(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Entity(int x, int y, Room room) {
        this.x = x;
        this.y = y;
        this.room = room;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void takeDamage(int damage) {
        if (damage < hp) {
            hp -= damage;
        } else {
            alive = false;
            hp = 0;
        }
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public abstract String getTexture();

    public void setBlock(Block block) {
        onBlock = block;
    }

    public Block getBlock() {
        return onBlock;
    }
    public UUID getUuid() {
        return uuid;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Entity other = (Entity) obj;
        return Objects.equals(uuid, other.getUuid());
    }

    // testing
    private boolean isSummoned = false;

    public boolean isSummoned() {
        return isSummoned;
    }

    public void setSummoned(boolean isSummoned) {
        this.isSummoned = isSummoned;
    }
    public void kill() {
        alive = false;
        hp = 0;

        if(this.room != null) this.room.removeEntity(this);

        RPG.getInstance().getEngine().getEventManager().callEvent(new EntityDeathEvent(this, null));
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}