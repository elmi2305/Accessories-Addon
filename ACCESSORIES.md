# Accessories mod reference

## What it provides

The addon adds six dedicated, persistent accessory slots. Players open them from the normal inventory using the accessory button. Items in these slots are `AccessoryItem`s and grant passive or triggered effects; ordinary materials and the Soul Sword are registered alongside them in `ACItems` but are not accessories.

## Inventory and multiplayer flow

- `EntityPlayerMixin` implements `IPlayerAccessories`, keeps a six-slot `InventoryBasic`, and saves/loads the slots in the player's `Accessories` NBT list.
- `ContainerAccessories` exposes six `SlotAccessory` slots followed by the normal player inventory and hotbar. Shift-click moves valid accessories into the accessory slots or returns them to player inventory.
- Opening the screen is server-authoritative: client `GuiInventoryMixin` sends the `accessories|OA` custom packet; `AccessoriesAddon` creates the container and sends a type-`42` open-window packet; `NetClientHandlerMixin` then creates the matching client container and `GuiAccessories`.
- Do not change the six-slot count, slot ordering, packet channel, or inventory type on only one side.

## Registration and content

- `ACItems.initItems()` owns item IDs (accessories currently begin at 2600, materials at 2700, and the Soul Sword is 2675), names, tooltips, and cooldown setup.
- `ACRecipes.initRecipes()` registers accessory recipes after `initIngredientRecipes()` and uses BTW's `RecipeManager` plus mill-stone recipes.
- `AccessoriesEntityMapper` registers `EntityFriendlySilverfish`; the entity and renderer are connected through the entity/render mapper mixins.
- Text is in `src/main/resources/assets/accessories/lang/en_US.lang`. Existing item and GUI textures are in `src/main/resources/assets/minecraft/textures/`.

## Effect implementation

Most mechanics live in mixins and should query equipped items via `ACUtils` rather than directly scanning player state:

- `EntityPlayerMixin`: NBT persistence plus movement, fall damage, combat, mining, breathing, water/lava walking, passive effects, and triggered accessory abilities.
- `EntityLivingBaseMixin`: combat/status effects that apply to living entities.
- Other targeted mixins cover mobs, item pickup, blocks, crafting/fire-starting, interaction range, and client HUD/GUI behavior.
- `ACUtils` centralizes equipped-item queries and cooldown helpers. `AccessoryItem` holds tooltip and cooldown metadata.

## Effect families

- Mobility and traversal: balloons, horseshoes, frog leg, spring/spectre/hermes boots, ice skates, water-walking boots, and lava waders.
- Defense and survival: shields, pendants, sun/moon/celestial stones, the eye of the sun, and bezoar.
- Utility and interaction: animal/mob scent items, cat ears and skirt, hand stick/extendo grip, mechanical glove, sewing kit, matches, loot magnet, and wood/digging accessories.
- Combat: gambling trinkets, whetstone, momentum blade, sweeping edge, and enchantment trinkets for vampirism, lightning, silverfish allies, explosions, katana counters, crumbling, and velocity dashes.

## Third-person appearance

- `AccessoryAppearanceSync` sends `accessories|AP` snapshots from the server: entity ID (int), active slot count (byte), and one item ID (int, `-1` for empty) per maximum slot. The current maximum is nine, including the three progression unlocks; the fixed payload is 41 bytes.
- `AccessoryAppearancePlayerMixin` broadcasts changes each server tick. `EntityTrackerEntryMixin` sends an initial snapshot immediately after the tracked player's spawn packet.
- The client-only `AccessoryAppearanceCache` validates packets and discards stale entries on destruction, world changes, and session reset. Same-world respawns retain other players' appearances.
- `AccessoryRenderer` uses only these snapshots and renders clothing: Cat Ears, Counter Scarf, Skirt, Mechanical Glove, Spring/Hermes/Spectre/Water Walking Boots, Lava Waders, Ice Skates, Monster Necklace, both ocean pendants, and Sun/Moon/Celestial Stones. Carried trinkets, tools, shields, and other accessories have no physical appearance. Ice Skates supply blue boot uppers and blades and take visual priority over other boots; otherwise the existing boot priority applies.
- `WearableModels` builds shaped clothing meshes with armor clearance, including a hollow pleated skirt and a seated variant, each fitted separately to bare waists, leggings, or chestplates. Cat Ears fit bare heads or helmets; necklace chains follow the skin or chestplate surface. `WearableMesh` maps dedicated 16x16 cloth/metal textures onto the geometry; item icons are never used. See `ASTRA_ACCESSORY_RENDERING_PLAN.md` and `docs/WEARABLE_TEXTURES.md`.
- In-game validation still requires two clients: equip/unequip, enter tracking range, respawn in the same world, change dimensions, reconnect, and check armor, poses, invisibility, and mixed attachments.

## Cooldowns

Some `AccessoryItem`s declare a final cooldown and a display color with `setFinalCooldown()` and `setCooldownColor()`. `ACUtils` decrements and resets them. These values are stored on the item instance, so current cooldown state is shared by every stack of that registered item type rather than saved per stack.

## Guardrail

Never generate textures unless the user specifically asks for textures.
