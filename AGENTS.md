# Accessories Addon contributor notes

Accessories is a Better Than Wolves Community Edition (BTW CE) addon for Minecraft 1.6.4. It adds a persistent six-slot accessory inventory and a large collection of equippable trinkets that alter movement, combat, survival, interaction, and mob behaviour. Both client and server run the addon; accessory containers are deliberately server-authoritative for multiplayer correctness.

## Repository scope

- The addon source of truth is `src/`, primarily `src/main/java/` and `src/main/resources/`.
- `BTWSRC/` is a **read-only reference** copy of BTW CE and vanilla Minecraft source. Consult it to understand target methods, mappings, and existing behavior, but do not edit it as part of addon work.
- `build_BTW/` and `custom_mappings/` are generated development inputs. Do not treat them as addon source.
- Read [ACCESSORIES.md](ACCESSORIES.md) for accessory mechanics, inventory handling, registrations, or other systems the user mentioned.

## Initialization and registration

- `btw.community.accessories.AccessoriesAddon` is the BTW addon entry point. `initialize()` calls `ACItems.initItems()` followed by `ACRecipes.initRecipes()` and registers the accessory-open packet handler.
- `net.fabricmc.accessories.Accessories` is the Fabric entry point required by `fabric.mod.json`; it currently has no initialization logic.
- `net.fabricmc.accessories.items.ACItems` owns static item references and all numeric item IDs, ingredient setup, unlocalized names, texture names, tooltips, and cooldown metadata.
- `btw.community.accessories.ACRecipes` owns shaped, shapeless, and mill-stone recipes.
- `btw.community.accessories.AccessoriesEntityMapper`, wired by `BTWEntityMapperMixin`, registers addon entities; `BTWRenderMapperMixin` adds their client renderers.
- `src/main/resources/fabric.mod.json`, `accessories.mixins.json`, and `Accessories.accessWidener` define runtime integration. Add a mixin to the correct common or `client` list when introducing one.

## Established patterns

- Define wearable items as `AccessoryItem` entries in `ACItems.initItems()`. It provides one-item stacks, tooltips, and optional cooldown/display-color metadata. Add matching language and texture resources only when supplied or specifically requested.
- Store and query equipped items through `IPlayerAccessories` and `ACUtils` (`hasAccessory`, `hasAnyAccessory`, `findAccessory`); do not duplicate slot-array scans in new mechanics.
- `EntityPlayerMixin` supplies the persistent `InventoryBasic` backing inventory, synchronizes it with its six-element accessory array, and serializes it under the `Accessories` NBT tag. Preserve that backing/sync arrangement when changing persistence.
- Effects belong in focused mixin injections/redirects against the appropriate vanilla/BTW class. Keep client-only rendering/UI code in mixins listed under `client` in `accessories.mixins.json`; gameplay and inventory authority remain server-compatible.
- The GUI flow is packet-based: `GuiInventoryMixin` sends `accessories|OA`, `AccessoriesAddon` opens `ContainerAccessories` on the server, and `NetClientHandlerMixin` creates the client container only after receiving the server's `Packet100OpenWindow` (inventory type `42`). Do not open this container locally first.
- `ContainerAccessories` and `SlotAccessory` enforce the six accessory slots and shift-click behavior. Keep client/server slot order and window IDs in sync.
- Existing cooldown fields on `AccessoryItem` are item-level, not per-`ItemStack`; account for that behavior before extending cooldown mechanics.

## Assets and validation

- **Never generate textures unless the user specifically asks for textures.** Preserve and reuse existing assets under `src/main/resources/assets/` where possible.
- Compile-check on Windows with `gradlew.bat compileJava` from the repository root. Run it after Java/resource-integration changes when the local BTW development setup is available.
