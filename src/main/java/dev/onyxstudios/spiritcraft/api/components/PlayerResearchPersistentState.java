package dev.onyxstudios.spiritcraft.api.components;

import net.fabricmc.fabric.api.util.NbtType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.PersistentState;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerResearchPersistentState extends PersistentState {

    private static final String ID = "PlayerResearchPersistentState";
    private Map<UUID, PlayerResearch> data = new HashMap<>();

    public PlayerResearchPersistentState() {
        super(ID);
    }

    public PlayerResearch getPlayerResearch(PlayerEntity player) {
        return data.compute(player.getUuid(), (uuid, playerResearch) -> new PlayerResearch());
    }

    public static PlayerResearchPersistentState get(ServerWorld world) {
        return world.getPersistentStateManager().getOrCreate(PlayerResearchPersistentState::new, ID);
    }

    @Override
    public void fromTag(CompoundTag compoundTag) {
        ListTag entries = compoundTag.getList("entries", NbtType.COMPOUND);
        for (Tag tag : entries) {
            CompoundTag entry = (CompoundTag) tag;
            UUID uuid = entry.getUuid("uuid");
            PlayerResearch research = new PlayerResearch();
            research.fromTag(entry.getCompound("research"));
            data.put(uuid, research);
        }
    }

    @Override
    public CompoundTag toTag(CompoundTag compoundTag) {
        ListTag listTag = new ListTag();
        for (Map.Entry<UUID, PlayerResearch> entry : data.entrySet()) {
            CompoundTag entryTag = new CompoundTag();
            entryTag.putUuid("uuid", entry.getKey());
            entryTag.put("research", entry.getValue().toTag());
            listTag.add(entryTag);
        }

        compoundTag.put("entries", listTag);
        return compoundTag;
    }
}
