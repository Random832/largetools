package io.github.elizayami.largetools.common.data;

import java.util.function.Consumer;

import io.github.elizayami.largetools.LargeTools;
import io.github.elizayami.largetools.common.ItemInit;
import io.github.elizayami.largetools.common.VanillaMaterial;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.SmithingRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;

public class ModRecipes extends RecipeProvider
{
	public ModRecipes(DataGenerator generatorIn)
	{
		super(generatorIn);
	}

	private ResourceLocation rl(String s)
	{
		return new ResourceLocation(LargeTools.MOD_ID, s);
	}

	@Override
	protected void registerRecipes(Consumer<IFinishedRecipe> consumer)
	{
		makeVanillaMaterialRecipes(ItemInit.WOOD, ItemTags.LOGS, consumer);
		makeVanillaMaterialRecipes(ItemInit.STONE, Blocks.STONE_BRICKS.asItem(), consumer);
		makeVanillaMaterialRecipes(ItemInit.IRON, Blocks.IRON_BLOCK.asItem(), consumer);
		makeVanillaMaterialRecipes(ItemInit.GOLD, Blocks.GOLD_BLOCK.asItem(), consumer);
		makeVanillaMaterialRecipes(ItemInit.DIAMOND, Blocks.DIAMOND_BLOCK.asItem(), consumer);
		makeNetheriteMaterialRecipes(consumer);
	}

	private void makeVanillaMaterialRecipes(VanillaMaterial material, Item item, Consumer<IFinishedRecipe> consumer)
	{
		makePaxelRecipe(material.paxel.get(), item, material.axe, material.pickaxe, material.shovel, consumer,
				material.name);
		makePickaxeRecipe(material.hammer.get(), item, consumer, material.name);
		makeAxeRecipe(material.saw.get(), item, consumer, material.name);
		makeShovelRecipe(material.backhoe.get(), item, consumer, material.name);
		makeHoeRecipe(material.tiller.get(), item, consumer, material.name);
		makePaxelRecipe(material.backhaw.get(), item, material.saw.get(), material.hammer.get(), material.backhoe.get(),
				consumer, material.name);
	}

	private void makeVanillaMaterialRecipes(VanillaMaterial material, ITag<Item> item,
			Consumer<IFinishedRecipe> consumer)
	{
		makePaxelRecipe(material.paxel.get(), item, material.axe, material.pickaxe, material.shovel, consumer,
				material.name);
		makePickaxeRecipe(material.hammer.get(), item, consumer, material.name);
		makeAxeRecipe(material.saw.get(), item, consumer, material.name);
		makeShovelRecipe(material.backhoe.get(), item, consumer, material.name);
		makeHoeRecipe(material.tiller.get(), item, consumer, material.name);
		makePaxelRecipe(material.backhaw.get(), item, material.saw.get(), material.hammer.get(), material.backhoe.get(),
				consumer, material.name);
	}

	private void makeNetheriteMaterialRecipes(Consumer<IFinishedRecipe> consumer)
	{
		VanillaMaterial diamond = ItemInit.DIAMOND;
		VanillaMaterial netherite = ItemInit.NETHERITE;
		
		makeSmithingRecipe(diamond.paxel.get(), Blocks.NETHERITE_BLOCK.asItem(), netherite.paxel.get(), consumer);
		
		makeSmithingRecipe(diamond.hammer.get(), Blocks.NETHERITE_BLOCK.asItem(), netherite.hammer.get(), consumer);
		makeSmithingRecipe(diamond.saw.get(), Blocks.NETHERITE_BLOCK.asItem(), netherite.saw.get(), consumer);
		makeSmithingRecipe(diamond.backhoe.get(), Blocks.NETHERITE_BLOCK.asItem(), netherite.backhoe.get(), consumer);
		makeSmithingRecipe(diamond.tiller.get(), Blocks.NETHERITE_BLOCK.asItem(), netherite.tiller.get(), consumer);
		makeSmithingRecipe(diamond.backhaw.get(), Blocks.NETHERITE_BLOCK.asItem(), netherite.backhaw.get(), consumer);
	}

	private void makeSmithingRecipe(Item base, Item addition, Item output, Consumer<IFinishedRecipe> consumer)
	{
		SmithingRecipeBuilder.smithingRecipe(Ingredient.fromItems(base), Ingredient.fromItems(addition), output)
				.addCriterion("has_" + addition.getRegistryName().getPath(), hasItem(addition))
				.build(consumer, rl(output.getRegistryName().getPath() + "_smithing"));
	}

	private void makeSwordRecipe(Item sword, Item ingredient, Consumer<IFinishedRecipe> consumer, String material)
	{
		ShapedRecipeBuilder.shapedRecipe(sword).key('#', Items.STICK).key('X', ingredient).patternLine("X")
				.patternLine("X").patternLine("#").addCriterion("has_" + material + "_ingot", hasItem(ingredient))
				.build(consumer);
	}

	private void makePickaxeRecipe(Item pickaxe, Item ingredient, Consumer<IFinishedRecipe> consumer, String material)
	{
		ShapedRecipeBuilder.shapedRecipe(pickaxe).key('#', Items.STICK).key('X', ingredient).patternLine("XXX")
				.patternLine(" # ").patternLine(" # ").addCriterion("has_" + material + "_ingot", hasItem(ingredient))
				.build(consumer);
	}

	private void makeAxeRecipe(Item axe, Item ingredient, Consumer<IFinishedRecipe> consumer, String material)
	{
		ShapedRecipeBuilder.shapedRecipe(axe).key('#', Items.STICK).key('X', ingredient).patternLine("XX")
				.patternLine("X#").patternLine(" #").addCriterion("has_" + material + "_ingot", hasItem(ingredient))
				.build(consumer);
	}

	private void makeShovelRecipe(Item shovel, Item ingredient, Consumer<IFinishedRecipe> consumer, String material)
	{
		ShapedRecipeBuilder.shapedRecipe(shovel).key('#', Items.STICK).key('X', ingredient).patternLine("X")
				.patternLine("#").patternLine("#").addCriterion("has_" + material + "_ingot", hasItem(ingredient))
				.build(consumer);
	}

	private void makeHoeRecipe(Item hoe, Item ingredient, Consumer<IFinishedRecipe> consumer, String material)
	{
		ShapedRecipeBuilder.shapedRecipe(hoe).key('#', Items.STICK).key('X', ingredient).patternLine("XX")
				.patternLine(" #").patternLine(" #").addCriterion("has_" + material + "_ingot", hasItem(ingredient))
				.build(consumer);
	}

	private void makePaxelRecipe(Item paxel, Item ingredient, Item ingredient1, Item ingredient2, Item ingredient3,
			Consumer<IFinishedRecipe> consumer, String material)
	{
		ShapedRecipeBuilder.shapedRecipe(paxel).key('#', Items.STICK).key('X', ingredient1).key('Y', ingredient2)
				.key('Z', ingredient3).patternLine("XYZ").patternLine(" # ").patternLine(" # ")
				.addCriterion("has_" + material + "_ingot", hasItem(ingredient)).build(consumer);
	}

	private void makePickaxeRecipe(Item pickaxe, ITag<Item> ingredient, Consumer<IFinishedRecipe> consumer,
			String material)
	{
		ShapedRecipeBuilder.shapedRecipe(pickaxe).key('#', Items.STICK).key('X', ingredient).patternLine("XXX")
				.patternLine(" # ").patternLine(" # ").addCriterion("has_" + material + "_ingot", hasItem(ingredient))
				.build(consumer);
	}

	private void makeAxeRecipe(Item axe, ITag<Item> ingredient, Consumer<IFinishedRecipe> consumer, String material)
	{
		ShapedRecipeBuilder.shapedRecipe(axe).key('#', Items.STICK).key('X', ingredient).patternLine("XX")
				.patternLine("X#").patternLine(" #").addCriterion("has_" + material + "_ingot", hasItem(ingredient))
				.build(consumer);
	}

	private void makeShovelRecipe(Item shovel, ITag<Item> ingredient, Consumer<IFinishedRecipe> consumer,
			String material)
	{
		ShapedRecipeBuilder.shapedRecipe(shovel).key('#', Items.STICK).key('X', ingredient).patternLine("X")
				.patternLine("#").patternLine("#").addCriterion("has_" + material + "_ingot", hasItem(ingredient))
				.build(consumer);
	}

	private void makeHoeRecipe(Item hoe, ITag<Item> ingredient, Consumer<IFinishedRecipe> consumer, String material)
	{
		ShapedRecipeBuilder.shapedRecipe(hoe).key('#', Items.STICK).key('X', ingredient).patternLine("XX")
				.patternLine(" #").patternLine(" #").addCriterion("has_" + material + "_ingot", hasItem(ingredient))
				.build(consumer);
	}

	private void makePaxelRecipe(Item paxel, ITag<Item> ingredient, Item ingredient1, Item ingredient2,
			Item ingredient3, Consumer<IFinishedRecipe> consumer, String material)
	{
		ShapedRecipeBuilder.shapedRecipe(paxel).key('#', Items.STICK).key('X', ingredient1).key('Y', ingredient2)
				.key('Z', ingredient3).patternLine("XYZ").patternLine(" # ").patternLine(" # ")
				.addCriterion("has_" + material + "_ingot", hasItem(ingredient)).build(consumer);
	}
}
