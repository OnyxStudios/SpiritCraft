package dev.onyxstudios.spiritcraft.mixins;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(CookingRecipeSerializer.class)
public class CookingRecipeSerializerMixin<T extends AbstractCookingRecipe> {
    
    @Shadow
    @Final
    public int cookingTime;

    @Inject(method = "read(Lnet/minecraft/util/Identifier;Lcom/google/gson/JsonObject;)Lnet/minecraft/recipe/AbstractCookingRecipe;",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/recipe/Ingredient;fromJson(Lcom/google/gson/JsonElement;)Lnet/minecraft/recipe/Ingredient;", shift = At.Shift.AFTER),
            cancellable = true,
            locals = LocalCapture.CAPTURE_FAILHARD)
    public void read(Identifier identifier, JsonObject jsonObject, CallbackInfoReturnable cir, String string, JsonElement jsonElement) {
        if(jsonObject.get("result").isJsonObject()) {
            //Little Hack to check for type as RecipeFactory is private
            String type = jsonObject.get("type").getAsString();
            if(type.equals("smelting") || type.equals("blasting")) {
                Ingredient ingredient = Ingredient.fromJson(jsonElement);
                ItemStack result = ShapedRecipe.getItemStack(JsonHelper.getObject(jsonObject, "result"));
                float f = JsonHelper.getFloat(jsonObject, "experience", 0.0F);
                int i = JsonHelper.getInt(jsonObject, "cookingtime", this.cookingTime);

                cir.setReturnValue(type.equals("smelting") ? new SmeltingRecipe(identifier, string, ingredient, result, f, i) :new BlastingRecipe(identifier, string, ingredient, result, f, i));
            }
        }
    }
}
