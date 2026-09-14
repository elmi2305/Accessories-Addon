package net.fabricmc.accessories.items;

import btw.community.accessories.AccessoriesProgressData;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EnumToolMaterial;
import net.minecraft.src.Item;
import net.minecraft.src.ItemSword;

public class ACItems {
    public static AccessoryItem cloudInABottle;
    public static AccessoryItem shinyBalloon;
    public static AccessoryItem cloudBalloon;
    public static AccessoryItem luckyHorseshoe;
    public static AccessoryItem bundleBalloons;
    public static AccessoryItem bundleHorseshoeBalloons;
    public static AccessoryItem frogLeg;
    public static AccessoryItem springBoots;
    public static AccessoryItem putridScent;
    public static AccessoryItem rottenPerfume;
    public static AccessoryItem sunStone;
    public static AccessoryItem moonStone;
    public static AccessoryItem celestialStone;
    public static AccessoryItem eyeOfTheSun;
    public static AccessoryItem counterScarf;
    public static AccessoryItem tastyTreat;
    public static AccessoryItem hermesBoots;
    public static AccessoryItem spectreBoots;
    public static AccessoryItem catEars;
    public static AccessoryItem skirt;
    public static AccessoryItem obsidianShield;
    public static AccessoryItem steelShield;
    public static AccessoryItem monsterNecklace;
    public static AccessoryItem pendantTides;
    public static AccessoryItem pendantSea;
    public static AccessoryItem handStick;
    public static AccessoryItem extendoGrip;
    public static AccessoryItem gamblersDelight;
    public static AccessoryItem gamblersDream;
    public static AccessoryItem gamblersParadise;
    public static AccessoryItem theDice;
    public static AccessoryItem sewingKit;
    public static AccessoryItem boxOfMatches;
    public static AccessoryItem bezoar;
    public static AccessoryItem mechanicalGlove;
    public static AccessoryItem whetstone;
    public static AccessoryItem lootMagnet;
    public static AccessoryItem momentumBlade;
    public static AccessoryItem vampireEnchantment;
    public static AccessoryItem lightningEnchantment;
    public static AccessoryItem silverfishEnchantment;
    public static AccessoryItem explosionEnchantment;
    public static AccessoryItem katanaEnchantment;
    public static AccessoryItem crumblingEnchantment;
    public static AccessoryItem velocityEnchantment;
    public static AccessoryItem sweepingEdge;
    public static AccessoryItem digFaster;
    public static AccessoryItem woodEnchantment;
    public static AccessoryItem goldEnchantment;
    public static AccessoryItem iceSkates;
    public static AccessoryItem waterWalkingBoots;
    public static AccessoryItem lavaWaders;
    public static AccessoryItem totemOfUndying;







    public static Item enderFabric;
    public static Item emberFabric;
    public static Item astralThread;
    public static Item glassShard;
    public static Item voidFragment;
    public static Item linenCloth;
    public static Item stoneEffigy;
    public static Item binder;
    public static Item stickyAdhesive;
    public static Item rottenChunk;
    public static Item basicDyeBlend;
    public static Item enhancedDyeBlend;
    public static Item industrialLeather;
    public static Item industrialTannedLeather;
    public static Item reinforcedFabric;
    public static Item plantyMush;
    public static Item enderRod;
    public static Item witherDust;
    public static Item netherFruit;
    public static Item witherFruit;
    public static Item dragonFruit;




    public static ItemSoulSword itemSoulSword;


    private static void initIngredients(){
        enderFabric = new Item(2700).setUnlocalizedName("acEnderFabric").setCreativeTab(CreativeTabs.tabMaterials).setTextureName("acEnderFabric");
        emberFabric = new Item(2701).setUnlocalizedName("acEmberFabric").setCreativeTab(CreativeTabs.tabMaterials).setTextureName("acEmberFabric");
        astralThread = new Item(2702).setUnlocalizedName("acAstralThread").setCreativeTab(CreativeTabs.tabMaterials).setTextureName("acAstralThread");
        glassShard = new Item(2703).setUnlocalizedName("acGlassShard").setCreativeTab(CreativeTabs.tabMaterials).setTextureName("acGlassShard");
        voidFragment = new Item(2704).setUnlocalizedName("acVoidFragment").setCreativeTab(CreativeTabs.tabMaterials).setTextureName("acVoidFragment");
        linenCloth = new Item(2705).setUnlocalizedName("acLinenCloth").setCreativeTab(CreativeTabs.tabMaterials).setTextureName("acLinenCloth");
        stoneEffigy = new Item(2706).setUnlocalizedName("acStoneEffigy").setCreativeTab(CreativeTabs.tabMaterials).setTextureName("acStoneEffigy");
        binder = new Item(2707).setUnlocalizedName("acBinder").setCreativeTab(CreativeTabs.tabMaterials).setTextureName("acBinder");
        stickyAdhesive = new Item(2708).setUnlocalizedName("acStickyAdhesive").setCreativeTab(CreativeTabs.tabMaterials).setTextureName("acStickyAdhesive");
        rottenChunk = new Item(2709).setUnlocalizedName("acRottenChunk").setCreativeTab(CreativeTabs.tabMaterials).setTextureName("acRottenChunk");
        basicDyeBlend = new Item(2710).setUnlocalizedName("acBasicDyeBlend").setCreativeTab(CreativeTabs.tabMaterials).setTextureName("acBasicDyeBlend");
        enhancedDyeBlend = new Item(2711).setUnlocalizedName("acEnhancedDyeBlend").setCreativeTab(CreativeTabs.tabMaterials).setTextureName("acEnhancedDyeBlend");
        industrialLeather = new Item(2712).setUnlocalizedName("acIndustrialLeather").setCreativeTab(CreativeTabs.tabMaterials).setTextureName("acIndustrialLeather");
        industrialTannedLeather = new Item(2713).setUnlocalizedName("acIndustrialTannedLeather").setCreativeTab(CreativeTabs.tabMaterials).setTextureName("acIndustrialTannedLeather");
        reinforcedFabric = new Item(2714).setUnlocalizedName("acReinforcedFabric").setCreativeTab(CreativeTabs.tabMaterials).setTextureName("acReinforcedFabric");
        plantyMush = new Item(2715).setUnlocalizedName("acPlantyMush").setCreativeTab(CreativeTabs.tabMaterials).setTextureName("acPlantyMush");
        enderRod = new Item(2716).setUnlocalizedName("acEnderRod").setCreativeTab(CreativeTabs.tabMaterials).setTextureName("acEnderRod");
        witherDust = new Item(2717).setUnlocalizedName("acWitherDust").setCreativeTab(CreativeTabs.tabMaterials).setTextureName("acWitherDust");
        netherFruit = new ItemAccessorySlotFruit(2718, AccessoriesProgressData.PORTAL_SLOT_UNLOCKED).setUnlocalizedName("acNetherFruit").setTextureName("acFruit1");
        witherFruit = new ItemAccessorySlotFruit(2719, AccessoriesProgressData.WITHER_SLOT_UNLOCKED).setUnlocalizedName("acWitherFruit").setTextureName("acFruit2");
        dragonFruit = new ItemAccessorySlotFruit(2720, AccessoriesProgressData.DRAGON_SLOT_UNLOCKED).setUnlocalizedName("acDragonFruit").setTextureName("acFruit3");
    }





    public static void initItems(){
        initIngredients();




        cloudInABottle = ((AccessoryItem) new AccessoryItem(2600).setUnlocalizedName("acCloudBottle").setTextureName("acCloudBottle"))
                .addTooltip("\2472Allows the wearer to Double Jump")
                .addTooltip("\247eWhat's next? A fart in a bottle?");
        shinyBalloon = ((AccessoryItem) new AccessoryItem(2601).setUnlocalizedName("acShinyBalloon").setTextureName("acShinyBalloon"))
                .addTooltip("\2472Reduces fall damage by 25%")
                .addTooltip("\247eOoh, shiny!");
            cloudBalloon = ((AccessoryItem) new AccessoryItem(2602).setUnlocalizedName("acCloudBalloon").setTextureName("acCloudBalloon"))
                    .addTooltip("\2472Reduces fall damage by 25%")
                    .addTooltip("\2472Allows the wearer to Double Jump")
                    .addTooltip("\247eLike riding the wind!");
        luckyHorseshoe = ((AccessoryItem) new AccessoryItem(2603).setUnlocalizedName("acLuckyHorseshoe").setTextureName("acLuckyHorseshoe"))
                .addTooltip("\2472Makes the wearer immune to fall damage")
                .addTooltip("\247eTerminal velocity!");
            bundleBalloons = ((AccessoryItem) new AccessoryItem(2604).setUnlocalizedName("acBundleBalloons").setTextureName("acBundleBalloons"))
                    .addTooltip("\2472Reduces fall damage by 25%")
                    .addTooltip("\2472Allows the wearer to Quadruple Jump")
                    .addTooltip("\2472Increases air acceleration")
                    .addTooltip("\247eWhen one balloon just isn’t enough");
            bundleHorseshoeBalloons = ((AccessoryItem) new AccessoryItem(2605).setUnlocalizedName("acBundleHorseshoeBalloons").setTextureName("acBundleHorseshoeBalloons"))
                    .addTooltip("\2472Makes the wearer immune to fall damage")
                    .addTooltip("\2472Allows the wearer to Quadruple Jump")
                    .addTooltip("\2472Increases air acceleration")
                    .addTooltip("\247eBasically flight!");
        frogLeg = ((AccessoryItem) new AccessoryItem(2606).setUnlocalizedName("acFrogLeg").setTextureName("acFrogLeg"))
                .addTooltip("\2472Increases jump height by half a block")
                .addTooltip("\247eNot for consumption");
            springBoots = ((AccessoryItem) new AccessoryItem(2607).setUnlocalizedName("acSpringBoots").setTextureName("acSpringBoots"))
                    .addTooltip("\2472Increases jump height by a full block")
                    .addTooltip("\247eBoing!");
        putridScent = ((AccessoryItem) new AccessoryItem(2608).setUnlocalizedName("acPutridScent").setTextureName("acPutridScent"))
                .addTooltip("\2472Reduces Mob detection by 15%")
                .addTooltip("\247eIt's called AURA");
            rottenPerfume = ((AccessoryItem) new AccessoryItem(2609).setUnlocalizedName("acMonsterPerfume").setTextureName("acMonsterPerfume"))
                    .addTooltip("\2472Reduces Mob detection by 25%")
                    .addTooltip("\2472Reduces detection radius")
                    .addTooltip("\2472of undead Mobs by 40%")
                    .addTooltip("\247eFor those who want their presence felt");
        sunStone = ((AccessoryItem) new AccessoryItem(2610).setUnlocalizedName("acSunStone").setTextureName("acSunStone"))
                .addTooltip("\247aDAY: Greatly increases health regeneration")
                .addTooltip("\247cNIGHT: Reduces health regeneration")
                .addTooltip("\247eGood vibes only");
        moonStone = ((AccessoryItem) new AccessoryItem(2611).setUnlocalizedName("acMoonStone").setTextureName("acMoonStone"))
                .addTooltip("\247aDAY: Reduces health regeneration")
                .addTooltip("\247cNIGHT: Greatly increases health regeneration")
                .addTooltip("\247eJust don't turn into a Werewolf");
            celestialStone = ((AccessoryItem) new AccessoryItem(2612).setUnlocalizedName("acCelestialStone").setTextureName("acCelestialStone"))
                    .addTooltip("\2472Increases health regeneration")
                    .addTooltip("\247aDAY: Increases damage done by 10%")
                    .addTooltip("\247cNIGHT: Reduces incoming damage by 6%")
                    .addTooltip("\247eBalanced, as all things should be");
        eyeOfTheSun = ((AccessoryItem) new AccessoryItem(2613).setUnlocalizedName("acEyeOfTheSun").setTextureName("acEyeOfTheSun"))
                .addTooltip("\2472Greatly increases regeneration")
                .addTooltip("\2472while standing still")
                .addTooltip("\247eFor when sunglasses just aren’t enough");
        counterScarf = ((AccessoryItem) new AccessoryItem(2614).setUnlocalizedName("acCounterScarf").setTextureName("acCounterScarf"))
                .addTooltip("\2472Grants a 10% chance to dodge attacks")
                .addTooltip("\247eMissed me?");
        tastyTreat = ((AccessoryItem) new AccessoryItem(2615).setUnlocalizedName("acTastyTreat").setTextureName("acTastyTreat"))
                .addTooltip("\2472Attracts friendly animals")
                .addTooltip("\247eBest friends forever!");
        hermesBoots = ((AccessoryItem) new AccessoryItem(2616).setUnlocalizedName("acHermesBoots").setTextureName("acHermesBoots"))
                .addTooltip("\2472Makes the wearer run 10% faster")
                .addTooltip("\247eNo time to explain");
            spectreBoots = ((AccessoryItem) new AccessoryItem(2617).setUnlocalizedName("acSpectreBoots").setTextureName("acSpectreBoots"))
                    .addTooltip("\2472Makes the wearer run 15% faster")
                    .addTooltip("\2472Allows the wearer to double jump")
                    .addTooltip("\2472Reduces fall damage by 30%")
                    .addTooltip("\247eI am Speed");
        catEars = ((AccessoryItem) new AccessoryItem(2618).setUnlocalizedName("acCatEars").setTextureName("acCatEars"))
                .addTooltip("\2472Repels Creepers")
                .addTooltip("\247eMeow :3");
        skirt = ((AccessoryItem) new AccessoryItem(2619).setUnlocalizedName("acSkirt").setTextureName("acSkirt"))
                .addTooltip("\2472Increases effectiveness of Cat Ears")
                .addTooltip("\247dSet bonus with Cat Ears:")
                .addTooltip("\247dMakes Creepers friendly")
                .addTooltip("\247eI don't care if you're a guy")
                .addTooltip("\247ePUT ON THE CAT EARS");
        obsidianShield = ((AccessoryItem) new AccessoryItem(2620).setUnlocalizedName("acShield").setTextureName("acShield"))
                .addTooltip("\2472Reduces knockback taken by 15%")
                .addTooltip("\247eBracing for impact");
            steelShield = ((AccessoryItem) new AccessoryItem(2621).setUnlocalizedName("acSteelShield").setTextureName("acSteelShield"))
                    .addTooltip("\2472Reduces knockback taken by 30%")
                    .addTooltip("\2472Reduces damage taken from")
                    .addTooltip("\2472projectiles and explosions")
                    .addTooltip("\247eAverage Tank build");
        monsterNecklace = ((AccessoryItem) new AccessoryItem(2622).setUnlocalizedName("acMonsterNecklace").setTextureName("acMonsterNecklace"))
                .addTooltip("\2472Increases mob vision by 25%")
                .addTooltip("\247eSelf-sabotage");
        pendantTides = ((AccessoryItem) new AccessoryItem(2623).setUnlocalizedName("acPendantTides").setTextureName("acPendantTides"))
                .addTooltip("\2472Reduces aggressiveness of Squids")
                .addTooltip("\2472Squids will not shoot tentacles")
                .addTooltip("\2472at the player")
                .addTooltip("\247eSquid whisperer");
            pendantSea = ((AccessoryItem) new AccessoryItem(2624).setUnlocalizedName("acPendantSeas").setTextureName("acPendantSeas"))
                    .addTooltip("\2472Makes Squids friendly")
                    .addTooltip("\2472Increases movement speed underwater")
                    .addTooltip("\2472Increases mining speed underwater")
                    .addTooltip("\2472Reduces breath loss underwater")
                    .addTooltip("\247eWon’t protect you from orcas, though");
        handStick = ((AccessoryItem) new AccessoryItem(2625).setUnlocalizedName("acHandStick").setTextureName("acHandStick"))
                .addTooltip("\2472Increases attack & placement")
                .addTooltip("\2472range by 0.5 blocks")
                .addTooltip("\247eVery handy");
            extendoGrip = ((AccessoryItem) new AccessoryItem(2626).setUnlocalizedName("acExtendoGrip").setTextureName("acExtendoGrip"))
                    .addTooltip("\2472Increases attack & placement")
                    .addTooltip("\2472range by 1 block")
                    .addTooltip("\247eProsthetic limbs have gone too far");
        gamblersDelight = ((AccessoryItem) new AccessoryItem(2627).setUnlocalizedName("acGamblersDelight").setTextureName("acGamblersDelight"))
                .addTooltip("\2472Damage you deal and receive is ")
                .addTooltip("\2472randomized between 0.5× and 1.5×")
                .addTooltip("\247eLuck is on your side... or not.");
            gamblersDream = ((AccessoryItem) new AccessoryItem(2628).setUnlocalizedName("acGamblersDream").setTextureName("acGamblersDream"))
                    .addTooltip("\2472All damage is either nullified or doubled")
                    .addTooltip("\247eDouble or nothing!");
                gamblersParadise = ((AccessoryItem) new AccessoryItem(2629).setUnlocalizedName("acGamblersParadise").setTextureName("acGamblersParadise"))
                        .addTooltip("\2472Deal triple damage, or your own")
                        .addTooltip("\2472damage is reflected onto you")
                        .addTooltip("\247eWe've been spending most our lives")
                        .addTooltip("\247eliving in a Gambler's Paradise");
                    theDice = ((AccessoryItem) new AccessoryItem(2630).setUnlocalizedName("acTheDice").setTextureName("acTheDice"))
                            .addTooltip("\2472You deal 0 damage")
                            .addTooltip("\2472Hitting an enemy has a 1% chance")
                            .addTooltip("\2472to instantly kill it")
                            .addTooltip("\2474You take 150% of the enemy's health")
                            .addTooltip("\2474as damage")
                            .addTooltip("\247eWe're up all night to get lucky");
        sewingKit = ((AccessoryItem) new AccessoryItem(2631).setUnlocalizedName("acSewingKit").setTextureName("acSewingKit"))
                .addTooltip("\2472Doubles knitting speed")
                .addTooltip("\247eGrandma arc");
        boxOfMatches = ((AccessoryItem) new AccessoryItem(2632).setUnlocalizedName("acBoxOfMatches").setTextureName("acBoxOfMatches"))
                .addTooltip("\2472Doubles efficiency of fire starters")
                .addTooltip("\247eBurn it all down!");
        bezoar = ((AccessoryItem) new AccessoryItem(2633).setUnlocalizedName("acBezoar").setTextureName("acBezoar"))
                .addTooltip("\2472Grants immunity to Poison")
                .addTooltip("\247eDoctors HATE him");
        mechanicalGlove = ((AccessoryItem) new AccessoryItem(2634).setUnlocalizedName("acMechanicalGlove").setTextureName("acMechanicalGlove"))
                .addTooltip("\2472Allows placing blocks while airborne")
                .addTooltip("\247eThe way of the Nerd Pole");
        whetstone = ((AccessoryItem) new AccessoryItem(2635).setUnlocalizedName("acWhetstone").setTextureName("acWhetstone"))
                .addTooltip("\2472Increases damage reduction while blocking")
                .addTooltip("\247eParried!");
        lootMagnet = ((AccessoryItem) new AccessoryItem(2636).setUnlocalizedName("acLootMagnet").setTextureName("acLootMagnet"))
                .addTooltip("\2472Increases item pick-up range")
                .addTooltip("\247eSadly doesn't have full map range");
        momentumBlade = ((AccessoryItem) new AccessoryItem(2637).setUnlocalizedName("acMomentumBlade").setTextureName("acMomentumBlade"))
                .addTooltip("\2472Increases damage depending on")
                .addTooltip("\2472your camera movement speed")
                .addTooltip("\247eIt makes sense, right?");
        vampireEnchantment = ((AccessoryItem) new AccessoryItem(2638).setUnlocalizedName("acVampireEnchantment").setTextureName("acVampireEnchantment"))
                .addTooltip("\2472Attacks have a chance to life-steal")
                .addTooltip("\2474Life-steal attacks deal less damage")
                .addTooltip("\247eStay out of the Sun!");
        lightningEnchantment = ((AccessoryItem) new AccessoryItem(2639).setUnlocalizedName("acLightningEnchantment").setTextureName("acLightningEnchantment"))
                .addTooltip("\2472Attacking an enemy spawns Lightning")
                .addTooltip("\247220 second cooldown")
                .addTooltip("\247eYou should love yourself NOW")
                .setFinalCooldown(200)
                .setCooldownColor(0xFFFF00);
        silverfishEnchantment = ((AccessoryItem) new AccessoryItem(2640).setUnlocalizedName("acSilverfishEnchantment").setTextureName("acSilverfishEnchantment"))
                .addTooltip("\2472Killing an enemy spawns friendly Silverfish")
                .addTooltip("\2472They will attack nearby mobs & apply slowness")
                .addTooltip("\247210 second cooldown")
                .addTooltip("\247eRise, my minions!")
                .setFinalCooldown(160)
                .setCooldownColor(0x444444);
        explosionEnchantment = ((AccessoryItem) new AccessoryItem(2641).setUnlocalizedName("acExplosionEnchantment").setTextureName("acExplosionEnchantment"))
                .addTooltip("\2472Attacking an enemy causes it to explode")
                .addTooltip("\247215 second cooldown")
                .addTooltip("\247eBoom boom boom!")
                .setFinalCooldown(160)
                .setCooldownColor(0xFF1111);
        katanaEnchantment = ((AccessoryItem) new AccessoryItem(2642).setUnlocalizedName("acKatanaEnchantment").setTextureName("acKatanaEnchantment"))
                .addTooltip("\2472Reflects 150% of incoming damage")
                .addTooltip("\2472onto the attacker while blocking")
                .addTooltip("\2472Every reflection uses durability!")
                .addTooltip("\247210 second cooldown")
                .addTooltip("\247eReturn to sender")
                .setFinalCooldown(100)
                .setCooldownColor(0xAAAAFF);
        crumblingEnchantment = ((AccessoryItem) new AccessoryItem(2643).setUnlocalizedName("acCrumblingEnchantment").setTextureName("acCrumblingEnchantment"))
                .addTooltip("\2472Crumbles enemies, reducing their defense")
                .addTooltip("\2472Prevents enemies from moving & attacking")
                .addTooltip("\2472Reduces enemy invincibility frames")
                .addTooltip("\247210 second cooldown")
                .addTooltip("\247eGroundbreaking technology")
                .setFinalCooldown(230)
                .setCooldownColor(0x7B3F00);
        velocityEnchantment = ((AccessoryItem) new AccessoryItem(2644).setUnlocalizedName("acVelocityEnchantment").setTextureName("acVelocityEnchantment"))
                .addTooltip("\2472Hit enemies to dash through them")
                .addTooltip("\2472Dash deals normal damage and applies Crumbling")
                .addTooltip("\2472Grants brief invincibility after dashing")
                .addTooltip("\2472Block while hitting to do a Movement Dash")
                .addTooltip("\24723 second cooldown")
                .addTooltip("\247eZoomies")
                .setFinalCooldown(60)
                .setCooldownColor(0x43FADB);
        sweepingEdge = ((AccessoryItem) new AccessoryItem(2645).setUnlocalizedName("acSweepingEdge").setTextureName("acSweepingEdge"))
                .addTooltip("\2472Attacks unleash a sweeping strike")
                .addTooltip("\2472Hits all enemies in front of you")
                .addTooltip("\247eCombat update? No, I don't think I will");
        digFaster = ((AccessoryItem) new AccessoryItem(2646).setUnlocalizedName("acDigFaster").setTextureName("acDigFaster"))
                .addTooltip("\2472Increases mining speed by 25%")
                .addTooltip("\247eMiners HATE him...");
        woodEnchantment = ((AccessoryItem) new AccessoryItem(2647).setUnlocalizedName("acWoodEnchantment").setTextureName("acWoodEnchantment"))
                .addTooltip("\2472Increases wood harvesting yield")
                .addTooltip("\2472Effectiveness increases with axe tier")
                .addTooltip("\247eTimber!");
        iceSkates = ((AccessoryItem) new AccessoryItem(2648).setUnlocalizedName("acIceSkates").setTextureName("acIceSkates"))
                .addTooltip("\2472Makes all surfaces slippery")
                .addTooltip("\247eBecause everyone loves ice levels");
        waterWalkingBoots = ((AccessoryItem) new AccessoryItem(2649).setUnlocalizedName("acWaterWalkingBoots").setTextureName("acWaterWalkingBoots"))
                .addTooltip("\2472Allows walking on water")
                .addTooltip("\247eBiblically accurate bootsies");
        lavaWaders = ((AccessoryItem) new AccessoryItem(2650).setUnlocalizedName("acLavaWaders").setTextureName("acLavaWaders"))
                .addTooltip("\2472Allows walking on lava")
                .addTooltip("\2472Grants fire immunity")
                .addTooltip("\247eToo hot to handle");








        itemSoulSword = ((ItemSoulSword) new ItemSoulSword(2675, EnumToolMaterial.WOOD)
                .setUnlocalizedName("acSoulSword")
                .setTextureName("acSoulSword"))
                .addTooltip("\2472Striking mobs restores 1 durability")
                .addTooltip("\2472Damage scales from 1 to 9 with durability")
                .addTooltip("\2472At full durability, attacks have a 33%")
                .addTooltip("\2472chance to make mobs drop loot")
                .addTooltip("\247eThe grind never ends.");
    }
}
