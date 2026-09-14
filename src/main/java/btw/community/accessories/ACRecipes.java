package btw.community.accessories;

import api.util.color.Color;
import btw.block.BTWBlocks;
import btw.crafting.manager.MillStoneCraftingManager;
import btw.crafting.recipe.RecipeManager;
import btw.item.BTWItems;
import net.fabricmc.accessories.items.ACItems;
import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

public class ACRecipes {

    public static void initRecipes(){
        initIngredientRecipes();


        RecipeManager.addRecipe(new ItemStack(ACItems.cloudInABottle), new Object[]{
                " Y ",
                "X#X",
                Character.valueOf('#'), Item.glassBottle,
                Character.valueOf('X'), Item.feather,
                Character.valueOf('Y'), Item.ghastTear
                // 1x glass bottle, 1x feather, 1x ghast tear
        });

        RecipeManager.addRecipe(new ItemStack(ACItems.shinyBalloon), new Object[]{
                " R ",
                "S#S",
                " G ",
                Character.valueOf('#'), Item.slimeBall,
                Character.valueOf('R'), new ItemStack(Item.dyePowder, 1, 1), // red dye
                Character.valueOf('S'), Item.silk,
                Character.valueOf('G'), Item.goldNugget
                // 1x slimeball, 2x string, 1x gold nugget, 1x red dye
        });

        RecipeManager.addRecipe(new ItemStack(ACItems.cloudBalloon), new Object[]{
                " # ",
                "FXF",
                " H ",
                Character.valueOf('#'), ACItems.cloudInABottle,
                Character.valueOf('F'), Item.feather,
                Character.valueOf('H'), BTWItems.hempFibers,
                Character.valueOf('X'), ACItems.shinyBalloon
                // 2x feather, 1x hemp fiber
                // 1x slimeball, 2x string, 1x gold nugget, 1x red dye
                // 1x glass bottle, 1x feather, 1x ghast tear

        });

        RecipeManager.addRecipe(new ItemStack(ACItems.luckyHorseshoe), new Object[]{
                "G G",
                "GSG",
                "I#I",
                Character.valueOf('#'), Item.ingotGold,
                Character.valueOf('G'), Item.goldNugget,
                Character.valueOf('S'), ACItems.stoneEffigy,
                Character.valueOf('I'), Item.ingotIron
                // 1x gold ingot, 5x gold nugget, 2x iron ingot
        });

        RecipeManager.addRecipe(new ItemStack(ACItems.bundleBalloons), new Object[]{
                "ADC",
                " B ",
                Character.valueOf('A'), ACItems.shinyBalloon,
                Character.valueOf('B'), ACItems.cloudBalloon,
                Character.valueOf('C'), ACItems.shinyBalloon,
                Character.valueOf('D'), ACItems.binder
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
                Character.valueOf('#'), Item.slimeBall,
                Character.valueOf('S'), Item.silk,
                Character.valueOf('F'), Item.fishCooked
                // 5x slimeball, 2x string, 2x cooked fish
        });

        RecipeManager.addRecipe(new ItemStack(ACItems.springBoots), new Object[]{
                " S ",
                "I#I",
                "RIR",
                Character.valueOf('#'), Item.bootsIron,
                Character.valueOf('I'), Item.ingotIron,
                Character.valueOf('R'), BTWItems.screw,
                Character.valueOf('S'), ACItems.frogLeg
                // 1x iron boots, 3x iron ingot, 2x screw
                // 5x slimeball, 2x string, 2x cooked fish
        });

        RecipeManager.addRecipe(new ItemStack(ACItems.putridScent), new Object[]{
                "FRF",
                "S#S",
                "YFY",
                Character.valueOf('#'), Item.slimeBall,
                Character.valueOf('R'), ACItems.rottenChunk,
                Character.valueOf('S'), Item.silk,
                Character.valueOf('Y'), BTWItems.foulFood,
                Character.valueOf('F'), BTWItems.dung,
                // 1x slimeball, 2x string, 1x rotten flesh, 2x foul food, 4x dung, 1x oyster, 1x bone, 1x bone meal
        });

        RecipeManager.addRecipe(new ItemStack(ACItems.rottenPerfume), new Object[]{
                "RDR",
                "P#P",
                " B ",
                Character.valueOf('#'), ACItems.putridScent,
                Character.valueOf('P'), Item.potion,
                Character.valueOf('R'), ACItems.rottenChunk,
                Character.valueOf('B'), BTWItems.urn,
                Character.valueOf('D'), ACItems.basicDyeBlend

                // 1x slimeball, 2x string, 3x rotten flesh, 2x foul food, 5x dung, 2x oyster, 2x bone, 2x bone meal
                // 1x glowstone dust, 2x potion, 1x urn
        });


        RecipeManager.addRecipe(new ItemStack(ACItems.sunStone), new Object[]{
                "#R#",
                "SGS",
                "#R#",
                Character.valueOf('#'), Item.magmaCream,
                Character.valueOf('G'), Block.glowStone,
                Character.valueOf('S'), Item.ingotGold,
                Character.valueOf('R'), ACItems.emberFabric
                // 2x redstone, 1x glowstone block, 2x gold ingot, 4x magma cream
                // 4x hemp fibers, 2x blaze powder, 2x fabric, 1x glowstone

        });

        RecipeManager.addRecipe(new ItemStack(ACItems.moonStone), new Object[]{
                "LBL",
                "S#S",
                "LBL",
                Character.valueOf('#'), new ItemStack(Item.dyePowder, 1, 4),
                Character.valueOf('B'), Item.bucketWater,
                Character.valueOf('S'), Item.ingotIron,
                Character.valueOf('L'), new ItemStack(BTWBlocks.looseCobblestone)
                // 1x lapis, 2x water bucket, 2x iron ingot, 4x cobblestone block
        });

        RecipeManager.addRecipe(new ItemStack(ACItems.celestialStone), new Object[]{
                "#AS",
                "YLY",
                "SM#",
                Character.valueOf('#'), ACItems.sunStone,
                Character.valueOf('S'), ACItems.moonStone,
                Character.valueOf('M'), Item.diamond,
                Character.valueOf('A'), ACItems.astralThread,
                Character.valueOf('Y'), BTWItems.gear,
                Character.valueOf('L'), ACItems.stoneEffigy
        });
        RecipeManager.addRecipe(new ItemStack(ACItems.eyeOfTheSun), new Object[]{
                "GPD",
                "ESE",
                "DPG",
                Character.valueOf('S'), ACItems.sunStone,
                Character.valueOf('E'), Item.spiderEye,
                Character.valueOf('P'), ACItems.stickyAdhesive,
                Character.valueOf('D'), Item.diamond,
                Character.valueOf('G'), Item.glowstone,
                // 2x redstone, 1x glowstone block, 2x gold ingot, 4x magma cream
                // 4x spider eye, 2x glowstone dust, 2x diamond
        });
        RecipeManager.addRecipe(new ItemStack(ACItems.eyeOfTheSun), new Object[]{
                "DPG",
                "ESE",
                "GPD",
                Character.valueOf('S'), ACItems.sunStone,
                Character.valueOf('E'), Item.spiderEye,
                Character.valueOf('P'), ACItems.stickyAdhesive,
                Character.valueOf('D'), Item.diamond,
                Character.valueOf('G'), Item.glowstone,
                // 2x redstone, 1x glowstone block, 2x gold ingot, 4x magma cream
                // 4x spider eye, 2x glowstone dust, 2x diamond
        });
        RecipeManager.addRecipe(new ItemStack(ACItems.counterScarf), new Object[]{
                "BDB",
                "FEF",
                "BDB",
                Character.valueOf('F'), BTWItems.fabric,
                Character.valueOf('E'), ACItems.enderFabric,
                Character.valueOf('D'), ACItems.linenCloth,
                Character.valueOf('B'), ACItems.basicDyeBlend,
        });

        RecipeManager.addRecipe(new ItemStack(ACItems.tastyTreat), new Object[]{
                "  P",
                "WCW",
                "B  ",
                Character.valueOf('P'), ACItems.plantyMush,
                Character.valueOf('C'), Item.carrot,
                Character.valueOf('B'), Item.bone,
                Character.valueOf('W'), Item.wheat,
        });



        RecipeManager.addRecipe(new ItemStack(ACItems.hermesBoots), new Object[]{
                "L L",
                "RPR",
                " B ",
                Character.valueOf('B'), Item.bootsLeather,
                Character.valueOf('R'), ACItems.basicDyeBlend,
                Character.valueOf('P'), ACItems.plantyMush,
                Character.valueOf('L'), ACItems.linenCloth
        });

        RecipeManager.addRecipe(new ItemStack(ACItems.spectreBoots), new Object[]{
                "L L",
                "RPR",
                " B ",
                Character.valueOf('B'), ACItems.hermesBoots,
                Character.valueOf('R'), ACItems.enhancedDyeBlend,
                Character.valueOf('P'), ACItems.enderFabric,
                Character.valueOf('L'), ACItems.astralThread
        });



        RecipeManager.addRecipe(new ItemStack(ACItems.catEars), new Object[]{
                "F F",
                "IBI",
                " L ",
                Character.valueOf('F'), BTWItems.fabric,
                Character.valueOf('B'), ACItems.binder,
                Character.valueOf('L'), ACItems.basicDyeBlend,
                Character.valueOf('I'), ACItems.linenCloth,
        });

        RecipeManager.addRecipe(new ItemStack(ACItems.skirt), new Object[]{
                "A A",
                "ILI",
                "FFF",
                Character.valueOf('F'), BTWItems.fabric,
                Character.valueOf('L'), ACItems.enhancedDyeBlend,
                Character.valueOf('A'), ACItems.astralThread,
                Character.valueOf('I'), ACItems.linenCloth,
        });

        RecipeManager.addRecipe(new ItemStack(ACItems.obsidianShield), new Object[]{
                "ROR",
                "ODO",
                "OOO",
                Character.valueOf('R'), ACItems.reinforcedFabric,
                Character.valueOf('O'), Block.obsidian,
                Character.valueOf('D'), Item.diamond,
        });

        RecipeManager.addRecipe(new ItemStack(ACItems.steelShield), new Object[]{
                "ROR",
                "OSO",
                "OOO",
                Character.valueOf('R'), ACItems.reinforcedFabric,
                Character.valueOf('O'), BTWItems.soulforgedSteelIngot,
                Character.valueOf('S'), ACItems.obsidianShield,
        });

        RecipeManager.addRecipe(new ItemStack(ACItems.monsterNecklace), new Object[]{
                "TDT",
                "GAG",
                "DRD",
                Character.valueOf('A'), ACItems.tastyTreat,
                Character.valueOf('G'), Item.goldNugget,
                Character.valueOf('D'), ACItems.witherDust,
                Character.valueOf('T'), ACItems.astralThread,
                Character.valueOf('R'), ACItems.rottenChunk,
        });

        RecipeManager.addRecipe(new ItemStack(ACItems.pendantTides), new Object[]{
                " I ",
                "RCR",
                "GMG",
                Character.valueOf('M'), BTWItems.mysteriousGland,
                Character.valueOf('C'), BTWItems.creeperOysters,
                Character.valueOf('G'), ACItems.glassShard,
                Character.valueOf('I'), Item.dyePowder,
                Character.valueOf('R'), ACItems.plantyMush,
                Character.valueOf('S'), Item.silk,
        });

        RecipeManager.addRecipe(new ItemStack(ACItems.pendantSea), new Object[]{
                "ADA",
                "VPV",
                "FMF",
                Character.valueOf('P'), ACItems.pendantTides,
                Character.valueOf('M'), BTWItems.mysteriousGland,
                Character.valueOf('V'), ACItems.voidFragment,
                Character.valueOf('F'), ACItems.enderFabric,
                Character.valueOf('A'), ACItems.astralThread,
                Character.valueOf('D'), Item.diamond,
        });

        RecipeManager.addRecipe(new ItemStack(ACItems.handStick), new Object[]{
                " G ",
                " SB",
                " S ",
                Character.valueOf('S'), Item.stick,
                Character.valueOf('G'), ACItems.mechanicalGlove,
                Character.valueOf('B'), ACItems.binder,
        });

        RecipeManager.addRecipe(new ItemStack(ACItems.extendoGrip), new Object[]{
                "FHF",
                "SBS",
                "FRF",
                Character.valueOf('R'), ACItems.enderRod,
                Character.valueOf('S'), ACItems.stickyAdhesive,
                Character.valueOf('H'), ACItems.handStick,
                Character.valueOf('B'), ACItems.binder,
                Character.valueOf('F'), ACItems.reinforcedFabric,
        });

        RecipeManager.addRecipe(new ItemStack(ACItems.gamblersDelight), new Object[]{
                "POL",
                "OBO",
                "LOP",
                Character.valueOf('O'), Item.bone,
                Character.valueOf('L'), ACItems.linenCloth,
                Character.valueOf('P'), ACItems.plantyMush,
                Character.valueOf('B'), ACItems.binder,
        });
        RecipeManager.addRecipe(new ItemStack(ACItems.gamblersDelight), new Object[]{
                "LOP",
                "OBO",
                "POL",
                Character.valueOf('O'), Item.bone,
                Character.valueOf('L'), ACItems.linenCloth,
                Character.valueOf('P'), ACItems.plantyMush,
                Character.valueOf('B'), ACItems.binder,
        });

        RecipeManager.addRecipe(new ItemStack(ACItems.gamblersDream), new Object[]{
                "NBN",
                "GEG",
                "NBN",
                Character.valueOf('B'), ACItems.basicDyeBlend,
                Character.valueOf('E'), ACItems.emberFabric,
                Character.valueOf('N'), Item.goldNugget,
                Character.valueOf('G'), ACItems.gamblersDelight,
        });

        RecipeManager.addRecipe(new ItemStack(ACItems.gamblersParadise), new Object[]{
                "BGB",
                "TST",
                "GDG",
                Character.valueOf('D'), Item.diamond,
                Character.valueOf('B'), ACItems.enhancedDyeBlend,
                Character.valueOf('T'), ACItems.astralThread,
                Character.valueOf('G'), ACItems.gamblersDream,
                Character.valueOf('S'), Item.netherStar,
        });
        RecipeManager.addRecipe(new ItemStack(ACItems.theDice), new Object[]{
                "FSF",
                "SGS",
                "FSF",
                Character.valueOf('F'), ACItems.reinforcedFabric,
                Character.valueOf('G'), ACItems.gamblersParadise,
                Character.valueOf('S'), BTWItems.soulforgedSteelIngot,
        });


        RecipeManager.addRecipe(new ItemStack(ACItems.sewingKit), new Object[]{
                " I ",
                "NFN",
                " S ",
                Character.valueOf('I'), Item.ingotIron,
                Character.valueOf('N'), Item.silk,
                Character.valueOf('F'), BTWItems.fabric,
                Character.valueOf('S'), Item.stick,

        });

        RecipeManager.addRecipe(new ItemStack(ACItems.boxOfMatches), new Object[]{
                "SSS",
                "SFS",
                "SCS",
                Character.valueOf('S'), Item.stick,
                Character.valueOf('F'), Item.flint,
                Character.valueOf('C'), Item.coal,
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
                Character.valueOf('I'), Item.ingotIron,
                Character.valueOf('B'), ACItems.binder,
                Character.valueOf('L'), ACItems.industrialTannedLeather,
                Character.valueOf('G'), BTWItems.gear,
                Character.valueOf('S'), BTWItems.screw,
        });


        RecipeManager.addRecipe(new ItemStack(ACItems.whetstone), new Object[]{
                " I ",
                "ISI",
                " L ",
                Character.valueOf('I'), Item.ingotIron,
                Character.valueOf('S'), BTWItems.stone,
                Character.valueOf('L'), ACItems.industrialLeather
                // 3x iron ingot, 1x stone, 1x leather
        });

        RecipeManager.addRecipe(new ItemStack(ACItems.lootMagnet), new Object[]{
                "GGG",
                "G#G",
                " R ",
                Character.valueOf('#'), Item.ingotIron,
                Character.valueOf('G'), Item.goldNugget,
                Character.valueOf('R'), Item.redstone
                // 6x gold nugget, 1x iron ingot, 1x redstone
        });

        RecipeManager.addRecipe(new ItemStack(ACItems.momentumBlade), new Object[]{
                " A ",
                "DBD",
                " A ",
                Character.valueOf('A'), ACItems.astralThread,
                Character.valueOf('D'), Item.diamond,
                Character.valueOf('B'), Item.blazePowder
                // 2x astralThread, 2x diamond, 1x blaze powder
                // astralThread (per craft reference) = 4x silk, 2x glowstone, 1x ghast tear
                // => expanded: 8x silk, 4x glowstone, 2x ghast tear (for 2x astralThread)
        });

        // Vampire Enchantment - post-Nether (life steal)
        RecipeManager.addRecipe(new ItemStack(ACItems.vampireEnchantment), new Object[]{
                "DRD",
                "V#V",
                "DAD",
                Character.valueOf('R'), Item.ghastTear,
                Character.valueOf('D'), Item.redstone,
                Character.valueOf('V'), ACItems.rottenChunk,
                Character.valueOf('#'), BTWItems.batWing,
                Character.valueOf('A'), ACItems.astralThread
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
                Character.valueOf('E'), ACItems.enderRod,
                Character.valueOf('L'), new ItemStack(Item.dyePowder, 1, Color.BLUE.colorID),
                Character.valueOf('#'), ACItems.enhancedDyeBlend,
                Character.valueOf('B'), Item.blazePowder,
                Character.valueOf('G'), Item.goldNugget
                // 1x enderRod, 2x lapis lazuli, , 1x blaze powder
                // enderRod = ender pearl + blaze rod OR ender pearl + 2x blaze powder (your existing recipes)
                // => expanded (vanilla-level where possible):
                // 1x ender pearl, 1x blaze rod (or 2x blaze powder), 2x lapis, 1x gold nugget, 1x redstone, 1x lapis, 1x blaze powder
        });

        // Silverfish Enchantment - ore stage (spawns friendly silverfish on kills)
        RecipeManager.addRecipe(new ItemStack(ACItems.silverfishEnchantment), new Object[]{
                "RSR",
                "S#S",
                "RBR",
                Character.valueOf('S'), ACItems.stoneEffigy,
                Character.valueOf('#'), ACItems.binder,
                Character.valueOf('B'), Item.bone,
                Character.valueOf('R'), BTWItems.stoneBrick
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
                Character.valueOf('G'), Item.gunpowder,
                Character.valueOf('R'), BTWItems.brimstone,
                Character.valueOf('#'), ACItems.voidFragment,
                Character.valueOf('H'), BTWItems.concentratedHellfire,
                Character.valueOf('K'), Block.tnt,
                Character.valueOf('B'), Item.blazePowder
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
                Character.valueOf('A'), ACItems.astralThread,
                Character.valueOf('B'), ACItems.emberFabric,
                Character.valueOf('F'), ACItems.enderFabric,
                Character.valueOf('C'), Item.swordDiamond,
                Character.valueOf('D'), Item.ghastTear
        });

        // Crumbling Enchantment - applies crumbling on hit, very expensive, post-Nether
        RecipeManager.addRecipe(new ItemStack(ACItems.crumblingEnchantment), new Object[]{
                "V#V",
                "EGE",
                "FDF",
                Character.valueOf('V'), ACItems.voidFragment,
                Character.valueOf('#'), Block.blockDiamond,
                Character.valueOf('G'), Item.blazePowder,
                Character.valueOf('E'), ACItems.enhancedDyeBlend,
                Character.valueOf('F'), ACItems.reinforcedFabric,
                Character.valueOf('D'), Item.gunpowder
        });

        RecipeManager.addRecipe(new ItemStack(ACItems.velocityEnchantment), new Object[]{
                "VRV",
                "ACA",
                "VSV",
                Character.valueOf('R'), ACItems.enderRod,
                Character.valueOf('C'), ACItems.crumblingEnchantment,
                Character.valueOf('A'), ACItems.astralThread,
                Character.valueOf('V'), ACItems.voidFragment,
                Character.valueOf('S'), ACItems.stickyAdhesive
        });

        RecipeManager.addRecipe(new ItemStack(ACItems.sweepingEdge), new Object[]{
                "ASA",
                "DBD",
                "PFP",
                Character.valueOf('A'), ACItems.astralThread,
                Character.valueOf('B'), ACItems.enderRod,
                Character.valueOf('S'), Item.swordDiamond,
                Character.valueOf('D'), ACItems.witherDust,
                Character.valueOf('F'), ACItems.enderFabric,
                Character.valueOf('P'), new ItemStack(Item.potion, 1, 8265), // strength for 8 minutes
        });

        // Dig Faster - +25% digging speed (early-ore / diamond stage)
        RecipeManager.addRecipe(new ItemStack(ACItems.digFaster), new Object[]{
                " D ",
                "IRI",
                " L ",
                Character.valueOf('D'), Item.diamond,
                Character.valueOf('I'), Item.ingotIron,
                Character.valueOf('R'), Item.redstone,
                Character.valueOf('L'), Item.leather
        });

        RecipeManager.addRecipe(new ItemStack(ACItems.woodEnchantment), new Object[]{
                "WFW",
                "WBW",
                "SSS",
                Character.valueOf('W'), Block.wood,
                Character.valueOf('F'), Item.rottenFlesh,
                Character.valueOf('B'), ACItems.binder,
                Character.valueOf('S'), Item.stick
        });

        RecipeManager.addRecipe(new ItemStack(ACItems.iceSkates), new Object[]{
                "WSW",
                "GHG",
                "SSS",
                Character.valueOf('S'), new ItemStack(Block.blockSnow),
                Character.valueOf('G'), ACItems.linenCloth,
                Character.valueOf('W'), Item.bucketWater,
                Character.valueOf('H'), ACItems.hermesBoots
        });

        RecipeManager.addRecipe(new ItemStack(ACItems.waterWalkingBoots), new Object[]{
                " P ",
                "HBH",
                "LLL",
                Character.valueOf('P'), ACItems.pendantTides,
                Character.valueOf('H'), ACItems.hermesBoots,
                Character.valueOf('B'), ACItems.binder,
                Character.valueOf('L'), ACItems.linenCloth
        });

        RecipeManager.addRecipe(new ItemStack(ACItems.lavaWaders), new Object[]{
                "FEF",
                "MBM",
                "ERE",
                Character.valueOf('E'), ACItems.emberFabric,
                Character.valueOf('M'), Item.magmaCream,
                Character.valueOf('B'), ACItems.hermesBoots,
                Character.valueOf('R'), Item.blazeRod,
                Character.valueOf('F'), ACItems.reinforcedFabric
        });







        RecipeManager.addRecipe(new ItemStack(ACItems.itemSoulSword), new Object[]{
                "AGA",
                "LSL",
                "ADA",
                Character.valueOf('S'), Item.swordIron,
                Character.valueOf('L'), BTWItems.soulSandPile,
                Character.valueOf('D'), Item.ghastTear,
                Character.valueOf('G'), Item.glowstone,
                Character.valueOf('A'), ACItems.astralThread,
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
                Character.valueOf('F'), BTWItems.fabric,
                Character.valueOf('E'), Item.enderPearl,
                Character.valueOf('B'), Item.blazePowder,
                Character.valueOf('H'), BTWItems.hempFibers,
                Character.valueOf('R'), ACItems.enderRod,
        });
        RecipeManager.addRecipe(new ItemStack(ACItems.emberFabric, 2), new Object[]{
                "HBH",
                "FEF",
                "HBH",
                Character.valueOf('F'), BTWItems.fabric,
                Character.valueOf('E'), Item.glowstone,
                Character.valueOf('B'), Item.blazePowder,
                Character.valueOf('H'), BTWItems.hempFibers,
                // 2x hemp fibers, 1x blaze powder, 1x fabric, 0.5x glowstone PER CRAFT
        });
        RecipeManager.addRecipe(new ItemStack(ACItems.astralThread, 8), new Object[]{
                "STS",
                "GSG",
                "STS",
                Character.valueOf('S'), Item.silk,
                Character.valueOf('G'), Item.glowstone,
                Character.valueOf('T'), Item.ghastTear,
        });

        RecipeManager.addShapelessRecipe(new ItemStack(ACItems.voidFragment), new Object[]{
                Item.ghastTear,
                ACItems.witherDust,
                new ItemStack(BTWBlocks.looseCobblestone)
        });

        RecipeManager.addRecipe(new ItemStack(ACItems.linenCloth), new Object[]{
                " S ",
                "WWW",
                " S ",
                Character.valueOf('S'), Item.stick,
                Character.valueOf('W'), BTWItems.wool,
        });

        RecipeManager.addRecipe(new ItemStack(ACItems.stoneEffigy), new Object[]{
                " R ",
                "SGS",
                " S ",
                Character.valueOf('S'), new ItemStack(BTWBlocks.looseCobblestone),
                Character.valueOf('G'), Item.goldNugget,
                Character.valueOf('R'), BTWItems.stone,
        });

        RecipeManager.addRecipe(new ItemStack(ACItems.binder), new Object[]{
                "B B",
                " B ",
                "B B",
                Character.valueOf('B'), Item.bone,
        });
        RecipeManager.addRecipe(new ItemStack(ACItems.binder), new Object[]{
                "B B",
                " B ",
                "B B",
                Character.valueOf('B'), BTWItems.ironNugget,
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
                Character.valueOf('B'), new ItemStack(Item.dyePowder, 1, 3), // cocoa powder
                Character.valueOf('I'), new ItemStack(Item.dyePowder, 1, 0), // ink sac
                Character.valueOf('L'), new ItemStack(Item.dyePowder, 1, 4), // lapis
                Character.valueOf('C'), new ItemStack(Item.dyePowder, 1, 15), // bone meal
                // just natural plant resources
        });

        RecipeManager.addRecipe(new ItemStack(ACItems.enhancedDyeBlend, 2), new Object[]{
                "LBL",
                "ICI",
                "LBL",
                Character.valueOf('B'), ACItems.witherDust,
                Character.valueOf('I'), Item.blazePowder,
                Character.valueOf('L'), BTWItems.soulSandPile,
                Character.valueOf('C'), ACItems.basicDyeBlend
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
                Character.valueOf('F'), BTWItems.fabric,
                Character.valueOf('D'), ACItems.enhancedDyeBlend,
                Character.valueOf('I'), BTWItems.ironNugget,
                Character.valueOf('C'), ACItems.glassShard
        });
        RecipeManager.addRecipe(new ItemStack(ACItems.reinforcedFabric), new Object[]{
                " S ",
                "SFS",
                " S ",
                Character.valueOf('F'), BTWItems.fabric,
                Character.valueOf('S'), BTWItems.steelNugget,
        });

        RecipeManager.addMillStoneRecipe(new ItemStack(ACItems.plantyMush, 4), new ItemStack(Block.leaves));
        RecipeManager.addMillStoneRecipe(new ItemStack(ACItems.plantyMush, 2), new ItemStack(Block.tallGrass));
        RecipeManager.addMillStoneRecipe(new ItemStack(ACItems.plantyMush), new ItemStack(Block.vine));






    }

}
