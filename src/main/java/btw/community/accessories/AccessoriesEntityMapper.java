package btw.community.accessories;

import net.fabricmc.accessories.EntityFriendlySilverfish;
import net.minecraft.src.EntityList;

public class AccessoriesEntityMapper {
    public static void createModEntityMappings() {
        EntityList.addMapping(EntityFriendlySilverfish.class, "acFriendlySilverfish", 2600, 0x777777, 0);
    }
}
