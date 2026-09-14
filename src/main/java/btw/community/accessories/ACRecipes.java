package btw.community.accessories;

import api.util.color.Color;
import btw.block.BTWBlocks;
import btw.crafting.recipe.RecipeManager;
import btw.item.BTWItems;
import btw.item.BTWTags;
import net.fabricmc.accessories.items.ACItems;
import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

@SuppressWarnings("deprecation")
public class ACRecipes {

    public static void initRecipes(){
        initIngredientRecipes();


        RecipeManager.addRecipe(new ItemStack(ACItems.cloudInABottle), new Object[]{
                " Y ",
                "X#X",
                '#', Item.glassBottle,
                'X', Item.feather,
                'Y', Item.ghastTear
                // 1x glass bottle, 1x feather, 1x ghast tear
        });

        RecipeManager.addRecipe(new ItemStack(ACItems.shinyBalloon), new Object[]{
                " R ",
                "S#S",
                " G ",
                '#', Item.slimeBall,
                'R', new ItemStack(Item.dyePowder, 1, 1), // red dye
                'S', BTWTags.strings,
                'G', Item.goldNugget
                // 1x slimeball, 2x string, 1x gold nugget, 1x red dye
        });

        RecipeManager.addRecipe(new ItemStack(ACItems.cloudBalloon), new Object[]{
                " # ",
                "FXF",
                " H ",
                '#', ACItems.cloudInABottle,
                'F', Item.feather,
                'H', BTWItems.hempFibers,
                'X', ACItems.shinyBalloon
                // 2x feather, 1x hemp fiber
                // 1x slimeball, 2x string, 1x gold nugget, 1x red dye
                // 1x glass bottle, 1x feather, 1x ghast tear

        });

        RecipeManager.addRecipe(new ItemStack(ACItems.luckyHorseshoe), new Object[]{
                "G G",
                "GSG",
                "I#I",
                '#', Item.ingotGold,
                'G', Item.goldNugget,
                'S', ACItems.stoneEffigy,
                'I', Item.ingotIron
                // 1x gold ingot, 5x gold nugget, 2x iron ingot
        });

        RecipeManager.addRecipe(new ItemStack(ACItems.bundleBalloons), new Object[]{
                "ADC",
                " B ",
                'A', ACItems.shinyBalloon,
                'B', ACItems.cloudBalloon,
                'C', ACItems.shinyBalloon,
                'D', ACItems.binder
                // 2x feather, 1x hemp fiber
                // 2x slimeball, 4x string, 2x gold nugget, 2x red dye
                // 1x glass bottle, 2x feather, 1x ghast tear
        });

        RecipeManager.addShapelessRecipe(new ItemStack(ACItems.bundleHorseshoeBalloons), new Object[]{
                new ItemStack(ACItems.luckyHorseshoe),
                new ItemStack(ACItems.bundleBalloons),
                new ItemStack(ACItems.luckyHorseshoe),
                new ItemStack(Item.slimeBall)
                // 2x gold ingot, 10x gold nugget, 4x iron ingot
                // 2x feather, 1x hemp fiber
                // 2x slimeball, 4x string, 2x gold nugget, 2x red dye
                // 1x glass bottle, 2x feather, 1x ghast tear
                // 1x slimeball
        });
        RecipeManager.addShapelessRecipe(new ItemStack(ACItems.bundleHorseshoeBalloons), new Object[]{
                new ItemStack(ACItems.luckyHorseshoe),
                new ItemStack(ACItems.cloudBalloon),
                new ItemStack(ACItems.shinyBalloon),
                new ItemStack(ACItems.shinyBalloon)
                // 1x gold ingot, 5x gold nugget, 2x iron ingot
                // 2x feather, 1x hemp fiber
                // 1x slimeball, 2x string, 1x gold nugget, 1x red dye
                // 1x glass bottle, 2x feather, 1x ghast tear
                // 2x slimeball, 4x string, 2x gold nugget, 2x red dye
        });

        RecipeManager.addRecipe(new ItemStack(ACItems.frogLeg), new Object[]{
                "#S#",
                "F#F",
                "#S#",
                '#', Item.slimeBall,
                'S', BTWTags.strings,
                'F', Item.fishCooked
                // 5x slimeball, 2x string, 2x cooked fish
        });

        RecipeManager.addRecipe(new ItemStack(ACItems.springBoots), new Object[]{
                " S ",
                "I#I",
                "RIR",
                '#', Item.bootsIron,
                'I', Item.ingotIron,
                'R', BTWItems.screw,
                'S', ACItems.frogLeg
                // 1x iron boots, 3x iron ingot, 2x screw
                // 5x slimeball, 2x string, 2x cooked fish
        });

        RecipeManager.addRecipe(new ItemStack(ACItems.putridScent), new Object[]{
                "FRF",
                "S#S",
                "YFY",
                '#', Item.slimeBall,
                'R', ACItems.rottenChunk,
                'S', BTWTags.strings,
                'Y', BTWItems.foulFood,
                'F', BTWItems.dung,
                // 1x slimeball, 2x string, 1x rotten flesh, 2x foul food, 4x dung, 1x oyster, 1x bone, 1x bone meal
        });

        RecipeManager.addRecipe(new ItemStack(ACItems.rottenPerfume), new Object[]{
                "RDR",
                "P#P",
                " B ",
                '#', ACItems.putridScent,
                'P', Item.potion,
                'R', ACItems.rottenChunk,
                'B', BTWItems.urn,
                'D', ACItems.basicDyeBlend

                // 1x slimeball, 2x string, 3x rotten flesh, 2x foul food, 5x dung, 2x oyster, 2x bone, 2x bone meal
                // 1x glowstone dust, 2x potion, 1x urn
        });


        RecipeManager.addRecipe(new ItemStack(ACItems.sunStone), new Object[]{
                "#R#",
                "SGS",
                "#R#",
                '#', Item.magmaCream,
                'G', Block.glowStone,
                'S', Item.ingotGold,
                'R', ACItems.emberFabric
                // 2x redstone, 1x glowstone block, 2x gold ingot, 4x magma cream
                // 4x hemp fibers, 2x blaze powder, 2x fabric, 1x glowstone

        });

        RecipeManager.addRecipe(new ItemStack(ACItems.moonStone), new Object[]{
                "LBL",
                "S#S",
                "LBL",
                '#', new ItemStack(Item.dyePowder, 1, 4),
                'B', Item.bucketWater,
                'S', Item.ingotIron,
                'L', BTWTags.looseCobblestones
                // 1x lapis, 2x water bucket, 2x iron ingot, 4x cobblestone block
        });

        RecipeManager.addRecipe(new ItemStack(ACItems.celestialStone), new Object[]{
                "#AS",
                "YLY",
                "SM#",
                '#', ACItems.sunStone,
                'S', ACItems.moonStone,
                'M', Item.diamond,
                'A', ACItems.astralThread,
                'Y', BTWItems.gear,
                'L', ACItems.stoneEffigy
        });
        RecipeManager.addRecipe(new ItemStack(ACItems.eyeOfTheSun), new Object[]{
                "GPD",
                "ESE",
                "DPG",
                'S', ACItems.sunStone,
                'E', Item.spiderEye,
                'P', ACItems.stickyAdhesive,
                'D', Item.diamond,
                'G', Item.glowstone,
                // 2x redstone, 1x glowstone block, 2x gold ingot, 4x magma cream
                // 4x spider eye, 2x glowstone dust, 2x diamond
        });
        RecipeManager.addRecipe(new ItemStack(ACItems.eyeOfTheSun), new Object[]{
                "DPG",
                "ESE",
                "GPD",
                'S', ACItems.sunStone,
                'E', Item.spiderEye,
                'P', ACItems.stickyAdhesive,
                'D', Item.diamond,
                'G', Item.glowstone,
                // 2x redstone, 1x glowstone block, 2x gold ingot, 4x magma cream
                // 4x spider eye, 2x glowstone dust, 2x diamond
        });
        RecipeManager.addRecipe(new ItemStack(ACItems.counterScarf), new Object[]{
                "BDB",
                "FEF",
                "BDB",
                'F', BTWItems.fabric,
                'E', ACItems.enderFabric,
                'D', ACItems.linenCloth,
                'B', ACItems.basicDyeBlend,
        });

        RecipeManager.addRecipe(new ItemStack(ACItems.tastyTreat), new Object[]{
                "  P",
                "WCW",
                "B  ",
                'P', ACItems.plantyMush,
                'C', BTWItems.carrot,
                'B', Item.bone,
                'W', BTWItems.wheat,
        });



        RecipeManager.addRecipe(new ItemStack(ACItems.hermesBoots), new Object[]{
                "L L",
                "RPR",
                " B ",
                'B', Item.bootsLeather,
                'R', ACItems.basicDyeBlend,
                'P', ACItems.plantyMush,
                'L', ACItems.linenCloth
        });

        RecipeManager.addRecipe(new ItemStack(ACItems.spectreBoots), new Object[]{
                "L L",
                "RPR",
                " B ",
                'B', ACItems.hermesBoots,
                'R', ACItems.enhancedDyeBlend,
                'P', ACItems.enderFabric,
                'L', ACItems.astralThread
        });



        RecipeManager.addRecipe(new ItemStack(ACItems.catEars), new Object[]{
                "F F",
                "IBI",
                " L ",
                'F', BTWItems.fabric,
                'B', ACItems.binder,
                'L', ACItems.basicDyeBlend,
                'I', ACItems.linenCloth,
        });

        RecipeManager.addRecipe(new ItemStack(ACItems.skirt), new Object[]{
                "A A",
                "ILI",
                "FFF",
                'F', BTWItems.fabric,
                'L', ACItems.enhancedDyeBlend,
                'A', ACItems.astralThread,
                'I', ACItems.linenCloth,
        });

        RecipeManager.addRecipe(new ItemStack(ACItems.obsidianShield), new Object[]{
                "ROR",
                "ODO",
                "OOO",
                'R', ACItems.reinforcedFabric,
                'O', Block.obsidian,
                'D', Item.diamond,
        });

        RecipeManager.addRecipe(new ItemStack(ACItems.steelShield), new Object[]{
                "ROR",
                "OSO",
                "OOO",
                'R', ACItems.reinforcedFabric,
                'O', BTWItems.soulforgedSteelIngot,
                'S', ACItems.obsidianShield,
        });

        RecipeManager.addRecipe(new ItemStack(ACItems.monsterNecklace), new Object[]{
                "TDT",
                "GAG",
                "DRD",
                'A', ACItems.tastyTreat,
                'G', Item.goldNugget,
                'D', ACItems.witherDust,
                'T', ACItems.astralThread,
                'R', ACItems.rottenChunk,
        });

        RecipeManager.addRecipe(new ItemStack(ACItems.pendantTides), new Object[]{
                " I ",
                "RCR",
                "GMG",
                'M', BTWItems.mysteriousGland,
                'C', BTWItems.creeperOysters,
                'G', ACItems.glassShard,
                'I', Item.dyePowder,
                'R', ACItems.plantyMush,
                'S', BTWTags.strings,
        });

        RecipeManager.addRecipe(new ItemStack(ACItems.pendantSea), new Object[]{
                "ADA",
                "VPV",
                "FMF",
                'P', ACItems.pendantTides,
                'M', BTWItems.mysteriousGland,
                'V', ACItems.voidFragment,
                'F', ACItems.enderFabric,
                'A', ACItems.astralThread,
                'D', Item.diamond,
        });

        RecipeManager.addRecipe(new ItemStack(ACItems.handStick), new Object[]{
                " G ",
                " SB",
                " S ",
                'S', Item.stick,
                'G', ACItems.mechanicalGlove,
                'B', ACItems.binder,
        });

        RecipeManager.addRecipe(new ItemStack(ACItems.extendoGrip), new Object[]{
                "FHF",
                "SBS",
                "FRF",
                'R', ACItems.enderRod,
                'S', ACItems.stickyAdhesive,
                'H', ACItems.handStick,
                'B', ACItems.binder,
                'F', ACItems.reinforcedFabric,
        });

        RecipeManager.addRecipe(new ItemStack(ACItems.gamblersDelight), new Object[]{
                "POL",
                "OBO",
                "LOP",
                'O', Item.bone,
                'L', ACItems.linenCloth,
                'P', ACItems.plantyMush,
                'B', ACItems.binder,
        });
        RecipeManager.addRecipe(new ItemStack(ACItems.gamblersDelight), new Object[]{
                "LOP",
                "OBO",
                "POL",
                'O', Item.bone,
                'L', ACItems.linenCloth,
                'P', ACItems.plantyMush,
                'B', ACItems.binder,
        });

        RecipeManager.addRecipe(new ItemStack(ACItems.gamblersDream), new Object[]{
                "NBN",
                "GEG",
                "NBN",
                'B', ACItems.basicDyeBlend,
                'E', ACItems.emberFabric,
                'N', Item.goldNugget,
                'G', ACItems.gamblersDelight,
        });

        RecipeManager.addRecipe(new ItemStack(ACItems.gamblersParadise), new Object[]{
                "BGB",
                "TST",
                "GDG",
                'D', Item.diamond,
                'B', ACItems.enhancedDyeBlend,
                'T', ACItems.astralThread,
                'G', ACItems.gamblersDream,
                'S', Item.netherStar,
        });
        RecipeManager.addRecipe(new ItemStack(ACItems.theDice), new Object[]{
                "FSF",
                "SGS",
                "FSF",
                'F', ACItems.reinforcedFabric,
                'G', ACItems.gamblersParadise,
                'S', BTWItems.soulforgedSteelIngot,
        });


        RecipeManager.addRecipe(new ItemStack(ACItems.sewingKit), new Object[]{
                " I ",
                "NFN",
                " S ",
                'I', Item.ingotIron,
                'N', BTWTags.strings,
                'F', BTWItems.fabric,
                'S', Item.stick,

        });

        RecipeManager.addRecipe(new ItemStack(ACItems.boxOfMatches), new Object[]{
                "SSS",
                "SFS",
                "SCS",
                'S', Item.stick,
                'F', Item.flint,
                'C', Item.coal,
        });

        RecipeManager.addShapelessRecipe(new ItemStack(ACItems.bezoar), new Object[]{
                Item.slimeBall,
                ACItems.rottenChunk,
                BTWItems.creeperOysters,
                BTWItems.mysteriousGland,
                Item.emerald,
        });

        RecipeManager.addRecipe(new ItemStack(ACItems.mechanicalGlove), new Object[]{
                "IBI",
                "LGL",
                "ISI",
                'I', Item.ingotIron,
                'B', ACItems.binder,
                'L', ACItems.industrialTannedLeather,
                'G', BTWItems.gear,
                'S', BTWItems.screw,
        });


        RecipeManager.addRecipe(new ItemStack(ACItems.whetstone), new Object[]{
                " I ",
                "ISI",
                " L ",
                'I', Item.ingotIron,
                'S', BTWTags.looseRocks,
                'L', ACItems.industrialLeather
                // 3x iron ingot, 1x stone, 1x leather
        });

        RecipeManager.addRecipe(new ItemStack(ACItems.lootMagnet), new Object[]{
                "GGG",
                "G#G",
                " R ",
                '#', Item.ingotIron,
                'G', Item.goldNugget,
                'R', Item.redstone
                // 6x gold nugget, 1x iron ingot, 1x redstone
        });

        RecipeManager.addRecipe(new ItemStack(ACItems.momentumBlade), new Object[]{
                " A ",
                "DBD",
                " A ",
                'A', ACItems.astralThread,
                'D', Item.diamond,
                'B', Item.blazePowder
                // 2x astralThread, 2x diamond, 1x blaze powder
                // astralThread (per craft reference) = 4x silk, 2x glowstone, 1x ghast tear
                // => expanded: 8x silk, 4x glowstone, 2x ghast tear (for 2x astralThread)
        });

        // Vampire Enchantment - post-Nether (life steal)
        RecipeManager.addRecipe(new ItemStack(ACItems.vampireEnchantment), new Object[]{
                "DRD",
                "V#V",
                "DAD",
                'R', Item.ghastTear,
                'D', Item.redstone,
                'V', ACItems.rottenChunk,
                '#', BTWItems.batWing,
                'A', ACItems.astralThread
                // 1x ghast tear, 2x rotten chunk, 1x soul dust, 1x astralThread
                // rottenChunk = 1x bone meal, 1x rotten flesh, 1x bone, 1x creeper oyster, 1x dung
                // astralThread = 4x silk, 2x glowstone, 1x ghast tear
                // => expanded (vanilla-level where possible):
                // 1x ghast tear, 2x rotten flesh, 1x bone meal, 1x bone, 1x creeper oyster, 1x dung ,
                // 4x silk, 2x glowstone, 1x ghast tear
                // 4x redstone
                // 1x bat wing
        });

        // Lightning Enchantment - post-Nether (attacks can call lightning)
        RecipeManager.addRecipe(new ItemStack(ACItems.lightningEnchantment), new Object[]{
                "GEG",
                "L#L",
                "GBG",
                'E', ACItems.enderRod,
                'L', new ItemStack(Item.dyePowder, 1, Color.BLUE.colorID),
                '#', ACItems.enhancedDyeBlend,
                'B', Item.blazePowder,
                'G', Item.goldNugget
                // 1x enderRod, 2x lapis lazuli, 1x blaze powder
                // enderRod = ender pearl + blaze rod OR ender pearl + 2x blaze powder (your existing recipes)
                // => expanded (vanilla-level where possible):
                // 1x ender pearl, 1x blaze rod (or 2x blaze powder), 2x lapis, 1x gold nugget, 1x redstone, 1x lapis, 1x blaze powder
        });

        // Silverfish Enchantment - ore stage (spawns friendly silverfish on kills)
        RecipeManager.addRecipe(new ItemStack(ACItems.silverfishEnchantment), new Object[]{
                "RSR",
                "S#S",
                "RBR",
                'S', ACItems.stoneEffigy,
                '#', ACItems.binder,
                'B', Item.bone,
                'R', BTWTags.stoneBrickItems
                // 2x stone effigy, 1x binder, 1x bone
                // stoneEffigy = 3x loose cobblestone, 1x gold nugget, 1x BTW stone
                // binder = 4x bone OR 4x iron nugget (your dual recipes)
                // => expanded (vanilla-level where possible):
                // 6x loose cobblestone, 2x gold nugget, 1x BTW stone, 4x bone (or 4x iron nugget), 1x bone
                // 4x stone brick
        });

        RecipeManager.addRecipe(new ItemStack(ACItems.explosionEnchantment), new Object[]{
                "HRH",
                "G#G",
                "KBK",
                'G', Item.gunpowder,
                'R', BTWItems.brimstone,
                '#', ACItems.voidFragment,
                'H', BTWItems.concentratedHellfire,
                'K', Block.tnt,
                'B', Item.blazePowder
                // 3x gunpowder, 1x void fragment, 1x blaze powder
                // voidFragment = 1x ghast tear, 1x wither dust, 1x loose cobblestone
                // => expanded (vanilla-level where possible):
                // 3x gunpowder, 1x ghast tear, 1x loose cobblestone, 1x blaze powder, 1x wither dust (mod)
                // 2x powder keg
                // 1x brimstone
                // 2x concentrated hellfire
        });




        RecipeManager.addRecipe(new ItemStack(ACItems.katanaEnchantment), new Object[]{
                "FAF",
                "BCB",
                "FDF",
                'A', ACItems.astralThread,
                'B', ACItems.emberFabric,
                'F', ACItems.enderFabric,
                'C', Item.swordDiamond,
                'D', Item.ghastTear
        });

        // Crumbling Enchantment - applies crumbling on hit, very expensive, post-Nether
        RecipeManager.addRecipe(new ItemStack(ACItems.crumblingEnchantment), new Object[]{
                "V#V",
                "EGE",
                "FDF",
                'V', ACItems.voidFragment,
                '#', Block.blockDiamond,
                'G', Item.blazePowder,
                'E', ACItems.enhancedDyeBlend,
                'F', ACItems.reinforcedFabric,
                'D', Item.gunpowder
        });

        RecipeManager.addRecipe(new ItemStack(ACItems.velocityEnchantment), new Object[]{
                "VRV",
                "ACA",
                "VSV",
                'R', ACItems.enderRod,
                'C', ACItems.crumblingEnchantment,
                'A', ACItems.astralThread,
                'V', ACItems.voidFragment,
                'S', ACItems.stickyAdhesive
        });

        RecipeManager.addRecipe(new ItemStack(ACItems.sweepingEdge), new Object[]{
                "ASA",
                "DBD",
                "PFP",
                'A', ACItems.astralThread,
                'B', ACItems.enderRod,
                'S', Item.swordDiamond,
                'D', ACItems.witherDust,
                'F', ACItems.enderFabric,
                'P', new ItemStack(Item.potion, 1, 8265), // strength for 8 minutes
        });

        // Dig Faster - +25% digging speed (early-ore / diamond stage)
        RecipeManager.addRecipe(new ItemStack(ACItems.digFaster), new Object[]{
                " D ",
                "IRI",
                " L ",
                'D', Item.diamond,
                'I', Item.ingotIron,
                'R', Item.redstone,
                'L', Item.leather
        });

        RecipeManager.addRecipe(new ItemStack(ACItems.woodEnchantment), new Object[]{
                "WFW",
                "WBW",
                "SSS",
                'W', Block.wood,
                'F', Item.rottenFlesh,
                'B', ACItems.binder,
                'S', Item.stick
        });

        RecipeManager.addRecipe(new ItemStack(ACItems.iceSkates), new Object[]{
                "WSW",
                "GHG",
                "SSS",
                'S', new ItemStack(BTWBlocks.looseSnow),
                'G', ACItems.linenCloth,
                'W', Item.bucketWater,
                'H', ACItems.hermesBoots
        });

        RecipeManager.addRecipe(new ItemStack(ACItems.waterWalkingBoots), new Object[]{
                " P ",
                "HBH",
                "LLL",
                'P', ACItems.pendantTides,
                'H', ACItems.hermesBoots,
                'B', ACItems.binder,
                'L', ACItems.linenCloth
        });

        RecipeManager.addRecipe(new ItemStack(ACItems.lavaWaders), new Object[]{
                "FEF",
                "MBM",
                "ERE",
                'E', ACItems.emberFabric,
                'M', Item.magmaCream,
                'B', ACItems.hermesBoots,
                'R', Item.blazeRod,
                'F', ACItems.reinforcedFabric
        });







        RecipeManager.addRecipe(new ItemStack(ACItems.itemSoulSword, 1, ACItems.itemSoulSword.getInitialItemDamage()), new Object[]{
                "AGA",
                "LSL",
                "ADA",
                'S', Item.swordIron,
                'L', BTWItems.soulSandPile,
                'D', Item.ghastTear,
                'G', Item.glowstone,
                'A', ACItems.astralThread,
        });

    }


























    private static void initIngredientRecipes(){
        RecipeManager.addShapelessRecipe(new ItemStack(ACItems.enderRod), new Object[]{
                new ItemStack(Item.enderPearl),
                new ItemStack(Item.blazeRod)
        });
        RecipeManager.addShapelessRecipe(new ItemStack(ACItems.enderRod), new Object[]{
                new ItemStack(Item.enderPearl),
                new ItemStack(Item.blazePowder),
                new ItemStack(Item.blazePowder)
        });
        RecipeManager.addRecipe(new ItemStack(ACItems.enderFabric, 2), new Object[]{
                "HBH",
                "FEF",
                "HRH",
                'F', BTWItems.fabric,
                'E', Item.enderPearl,
                'B', Item.blazePowder,
                'H', BTWItems.hempFibers,
                'R', ACItems.enderRod,
        });
        RecipeManager.addRecipe(new ItemStack(ACItems.emberFabric, 2), new Object[]{
                "HBH",
                "FEF",
                "HBH",
                'F', BTWItems.fabric,
                'E', Item.glowstone,
                'B', Item.blazePowder,
                'H', BTWItems.hempFibers,
                // 2x hemp fibers, 1x blaze powder, 1x fabric, 0.5x glowstone PER CRAFT
        });
        RecipeManager.addRecipe(new ItemStack(ACItems.astralThread, 8), new Object[]{
                "STS",
                "GSG",
                "STS",
                'S', BTWTags.strings,
                'G', Item.glowstone,
                'T', Item.ghastTear,
        });

        RecipeManager.addShapelessRecipe(new ItemStack(ACItems.voidFragment), new Object[]{
                Item.ghastTear,
                ACItems.witherDust,
                BTWTags.looseCobblestones
        });

        RecipeManager.addRecipe(new ItemStack(ACItems.linenCloth), new Object[]{
                " S ",
                "WWW",
                " S ",
                'S', Item.stick,
                'W', BTWTags.wools,
        });

        RecipeManager.addRecipe(new ItemStack(ACItems.stoneEffigy), new Object[]{
                " R ",
                "SGS",
                " S ",
                'S', BTWTags.looseCobblestones,
                'G', Item.goldNugget,
                'R', BTWTags.looseRocks,
        });

        RecipeManager.addRecipe(new ItemStack(ACItems.binder), new Object[]{
                "B B",
                " B ",
                "B B",
                'B', Item.bone,
        });
        RecipeManager.addRecipe(new ItemStack(ACItems.binder), new Object[]{
                "B B",
                " B ",
                "B B",
                'B', BTWItems.ironNugget,
        });

        RecipeManager.addShapelessRecipe(new ItemStack(ACItems.stickyAdhesive), new Object[]{
                new ItemStack(Item.dyePowder, 1, 15),
                BTWItems.netherSludge,
                BTWItems.glue
        });
        RecipeManager.addShapelessRecipe(new ItemStack(ACItems.rottenChunk), new Object[]{
                new ItemStack(Item.dyePowder, 1, 15),
                Item.rottenFlesh,
                Item.bone,
                BTWItems.creeperOysters,
                BTWItems.dung
        });

        RecipeManager.addRecipe(new ItemStack(ACItems.basicDyeBlend, 4), new Object[]{
                "LBL",
                "ICI",
                "LBL",
                'B', new ItemStack(Item.dyePowder, 1, 3), // cocoa powder
                'I', new ItemStack(Item.dyePowder, 1, 0), // ink sac
                'L', new ItemStack(Item.dyePowder, 1, 4), // lapis
                'C', new ItemStack(Item.dyePowder, 1, 15), // bone meal
                // just natural plant resources
        });

        RecipeManager.addRecipe(new ItemStack(ACItems.enhancedDyeBlend, 2), new Object[]{
                "LBL",
                "ICI",
                "LBL",
                'B', ACItems.witherDust,
                'I', Item.blazePowder,
                'L', BTWItems.soulSandPile,
                'C', ACItems.basicDyeBlend
                // more advanced dye resources
        });

        RecipeManager.addShapelessRecipe(new ItemStack(ACItems.industrialLeather, 8), new Object[]{
                new ItemStack(BTWItems.scouredLeather),
                new ItemStack(BTWItems.scouredLeather),
                new ItemStack(BTWItems.scouredLeather),
                new ItemStack(BTWItems.scouredLeather),
                new ItemStack(BTWItems.scouredLeather),
                new ItemStack(BTWItems.scouredLeather),
                new ItemStack(BTWItems.scouredLeather),
                new ItemStack(BTWItems.scouredLeather),
                Item.bucketWater
        });
        RecipeManager.addShapelessRecipe(new ItemStack(ACItems.industrialLeather, 2), new Object[]{
                new ItemStack(BTWItems.scouredLeather),
                new ItemStack(BTWItems.scouredLeather),
                new ItemStack(BTWItems.scouredLeather),
                new ItemStack(BTWItems.scouredLeather),
                new ItemStack(Item.potion, 1, 0),
                new ItemStack(Item.potion, 1, 0),
                new ItemStack(Item.potion, 1, 0),
                new ItemStack(Item.potion, 1, 0),
        });

        RecipeManager.addShapelessRecipe(new ItemStack(ACItems.industrialTannedLeather), new Object[]{
                ACItems.industrialLeather,
                BTWItems.dung,
                new ItemStack(ACItems.basicDyeBlend, 1, 0),
                new ItemStack(ACItems.basicDyeBlend, 1, 0),
        });

        RecipeManager.addShapelessRecipe(new ItemStack(ACItems.industrialTannedLeather), new Object[]{
                BTWItems.tannedLeather,
                new ItemStack(Item.potion, 1, 0),
                new ItemStack(Item.potion, 1, 0),
                new ItemStack(ACItems.basicDyeBlend, 1, 0),
                new ItemStack(ACItems.basicDyeBlend, 1, 0),
        });

        RecipeManager.addRecipe(new ItemStack(ACItems.reinforcedFabric), new Object[]{
                "IDI",
                "CFC",
                "IDI",
                'F', BTWItems.fabric,
                'D', ACItems.enhancedDyeBlend,
                'I', BTWItems.ironNugget,
                'C', ACItems.glassShard
        });
        RecipeManager.addRecipe(new ItemStack(ACItems.reinforcedFabric), new Object[]{
                " S ",
                "SFS",
                " S ",
                'F', BTWItems.fabric,
                'S', BTWItems.steelNugget,
        });

        RecipeManager.addMillStoneRecipe(new ItemStack(ACItems.plantyMush, 4), new ItemStack(Block.leaves));
        RecipeManager.addMillStoneRecipe(new ItemStack(ACItems.plantyMush, 2), new ItemStack(Block.tallGrass));
        RecipeManager.addMillStoneRecipe(new ItemStack(ACItems.plantyMush), new ItemStack(Block.vine));






    }

}
