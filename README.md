# Accessories Addon

Accessories is a Better Than Wolves Community Edition addon for Minecraft 1.6.4. It adds a collection of equippable trinkets—accessories with movement, combat, utility, and survival effects—and an accessory inventory that is stored with the player.

## Features

- Six dedicated accessory slots, opened from the player inventory.
- Server-authoritative inventory handling: multiplayer clicks, slot updates, and saves are validated by the server.
- A broad set of accessory items, including balloons, boots, shields, pendants, gloves, magnets, and enchantment-style trinkets.
- Gameplay effects such as altered fall damage, movement, reach, damage, loot collection, water/lava walking, and more.
- Recipes and item assets integrated with Better Than Wolves Community Edition.

## Requirements

- Java 17
- Minecraft 1.6.4
- Better Than Wolves Community Edition 3.1.1
- Fabric Loader 0.18.4 or newer

Both the client and server need the addon installed for the accessory inventory and effects to work in multiplayer.

## Development setup

The project uses Gradle and Fabric Loom. The BTW development archive and mappings are generated locally; they are intentionally not committed.

On Windows, run:

```bat
install.bat
```

On Linux or macOS, run:

```sh
./install.sh
```

These scripts download and prepare the required BTW development files under `build_BTW/` and `custom_mappings/`.

## Build

```sh
./gradlew build
```

On Windows, use `gradlew.bat build`. The built addon JAR is written to `build/libs/`.

## Project layout

- `src/main/java/btw/community/accessories/` — addon initialization, recipes, and gameplay helpers.
- `src/main/java/net/fabricmc/accessories/` — accessory inventory, container, GUI, items, and mixins.
- `src/main/resources/` — Fabric/BTW metadata, mixin configuration, translations, textures, and other assets.

## License

This project is licensed under the [MIT License](LICENSE).

The development workflow includes a precompiled copy of [Tiny Remapper](https://github.com/FabricMC/tiny-remapper), which is licensed under LGPL-3.0. Its license remains applicable to that dependency.
