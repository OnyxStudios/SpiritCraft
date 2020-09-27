package dev.onyxstudios.spiritcraft.client.models;

import com.mojang.datafixers.util.Pair;
import dev.onyxstudios.spiritcraft.SpiritCraft;
import dev.onyxstudios.spiritcraft.api.items.IWand;
import dev.onyxstudios.spiritcraft.registry.ModRenders;
import net.fabricmc.fabric.api.renderer.v1.mesh.MutableQuadView;
import net.fabricmc.fabric.api.renderer.v1.mesh.QuadEmitter;
import net.fabricmc.fabric.api.renderer.v1.model.FabricBakedModel;
import net.fabricmc.fabric.api.renderer.v1.render.RenderContext;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.model.*;
import net.minecraft.client.render.model.json.*;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockRenderView;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

public class ModelWand implements UnbakedModel, BakedModel, FabricBakedModel {

	public static Identifier ROD_MODEL = new Identifier(SpiritCraft.MODID, "item/wand_rod");
	public static Identifier CAPS_MODEL = new Identifier(SpiritCraft.MODID, "item/wand_caps");
	private ModelTransformation transformation;
	private JsonUnbakedModel rodModel;
	private JsonUnbakedModel capsModel;
	private Sprite defaultSprite;

	@Override
	public void emitItemQuads(ItemStack stack, Supplier<Random> supplier, RenderContext renderContext) {
		Sprite rodSprite = MinecraftClient.getInstance().getSpriteAtlas(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE).apply(ModRenders.getWandRod(stack.getItem()));
		Sprite capsSprite = null;

		Item wandCap = ((IWand) stack.getItem()).getWandCap(stack);
		if (wandCap != null) {
			capsSprite = MinecraftClient.getInstance().getSpriteAtlas(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE).apply(ModRenders.getWandCaps(wandCap));
		}

		QuadEmitter emitter = renderContext.getEmitter();
		emitModel(emitter, rodModel, rodSprite);
		if(capsSprite != null) {
			emitModel(emitter, capsModel, capsSprite);
		}
	}

	@Override
	public void emitBlockQuads(BlockRenderView blockRenderView, BlockState blockState, BlockPos blockPos, Supplier<Random> supplier, RenderContext renderContext) {
	}

	private void emitModel(QuadEmitter emitter, JsonUnbakedModel model, Sprite sprite) {
		for (ModelElement element : model.getElements()) {
			for (Direction direction : Direction.values()) {
				ModelElementTexture texture = element.faces.get(direction).textureData;
				if (direction == Direction.DOWN || direction == Direction.UP) {
					emitter.square(direction, element.from.getX() / 16.0f, element.from.getZ() / 16.0f, element.to.getZ() / 16.0f, element.to.getZ() / 16.0f, element.from.getY() / 16.0f);
				}else {
					emitter.square(direction, element.from.getX() / 16.0f, element.from.getY() / 16.0f, element.to.getX() / 16.0f, element.to.getY() / 16.0f, element.from.getZ() / 16.0f);
				}

				emitter.sprite(0, 0, texture.uvs[0], texture.uvs[1]);
				emitter.sprite(1, 0, texture.uvs[0], texture.uvs[3]);
				emitter.sprite(2, 0, texture.uvs[2], texture.uvs[3]);
				emitter.sprite(3, 0, texture.uvs[2], texture.uvs[1]);
				emitter.spriteBake(0, sprite, MutableQuadView.BAKE_ROTATE_NONE);
				emitter.spriteColor(0, -1, -1, -1, -1);
				emitter.emit();
			}
		}
	}

	@Override
	public BakedModel bake(ModelLoader loader, Function<SpriteIdentifier, Sprite> textureGetter, ModelBakeSettings rotationContainer, Identifier modelId) {
		rodModel = (JsonUnbakedModel) loader.getOrLoadModel(ROD_MODEL);
		capsModel = (JsonUnbakedModel) loader.getOrLoadModel(CAPS_MODEL);
		defaultSprite = textureGetter.apply(new SpriteIdentifier(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE, ModRenders.DEFAULT_ROD_TEXTURE));
		transformation = capsModel.getTransformations();
		return this;
	}


	@Override
	public List<BakedQuad> getQuads(BlockState state, Direction face, Random random) {
		return null;
	}

	@Override
	public boolean useAmbientOcclusion() {
		return false;
	}

	@Override
	public boolean hasDepth() {
		return false;
	}

	@Override
	public boolean isSideLit() {
		return true;
	}

	@Override
	public boolean isBuiltin() {
		return false;
	}

	@Override
	public Sprite getSprite() {
		return defaultSprite;
	}

	@Override
	public ModelTransformation getTransformation() {
		return transformation;
	}

	@Override
	public ModelOverrideList getOverrides() {
		return ModelOverrideList.EMPTY;
	}

	@Override
	public Collection<Identifier> getModelDependencies() {
		return Collections.EMPTY_LIST;
	}

	@Override
	public boolean isVanillaAdapter() {
		return false;
	}

	@Override
	public Collection<SpriteIdentifier> getTextureDependencies(Function<Identifier, UnbakedModel> unbakedModelGetter, Set<Pair<String, String>> unresolvedTextureReferences) {
		return Collections.EMPTY_LIST;
	}
}