package dice.sinanya.tools;

import dice.sinanya.dice.manager.Roles;
import dice.sinanya.entity.EntityRoleTag;
import dice.sinanya.entity.EntityTypeMessages;

import static dice.sinanya.system.RoleInfoCache.ROLE_INFO_CACHE;

public class SearchRole {
    public static boolean searchRole(String role, EntityTypeMessages entityTypeMessages) {
        long qq = Long.parseLong(entityTypeMessages.getFromQQ());
        if (ROLE_INFO_CACHE.containsKey(new EntityRoleTag(qq, role))) {
            return true;
        } else {
            new Roles(entityTypeMessages).get();
            return ROLE_INFO_CACHE.containsKey(new EntityRoleTag(qq, role));
        }
    }

    public static boolean searchRole(EntityTypeMessages entityTypeMessages) {
        long qq = Long.parseLong(entityTypeMessages.getFromQQ());
        if (ROLE_INFO_CACHE.containsKey(new EntityRoleTag(entityTypeMessages))) {
            return true;
        } else {
            new Roles(entityTypeMessages).get();
            return ROLE_INFO_CACHE.containsKey(new EntityRoleTag(entityTypeMessages));
        }
    }
}