package modulardiversity.jei;

import mezz.jei.api.*;
import mezz.jei.api.ingredients.IModIngredientRegistration;
import modulardiversity.ModularDiversity;
import modulardiversity.jei.ingredients.*;
import modulardiversity.jei.renderer.*;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;

@JEIPlugin
public class JEIHelpers implements IModPlugin {
    public static final ResourceLocation TEXTURE = new ResourceLocation(ModularDiversity.MODID,"textures/gui/widgets.png");
    public static IGuiHelper GUI_HELPER;

    @Override
    public void register(IModRegistry registry) {
        GUI_HELPER = registry.getJeiHelpers().getGuiHelper();
    }

    @Override
    public void registerIngredients(IModIngredientRegistration registry) {
        registry.register(Mechanical.class, new ArrayList<>(), new FakeIngredientHelper<>(), new RendererMechanical());
        registry.register(Laser.class, new ArrayList<>(), new FakeIngredientHelper<>(), new RendererLaser());
        registry.register(MekLaser.class, new ArrayList<>(), new FakeIngredientHelper<>(), new RendererMekLaser());
        registry.register(MekHeat.class, new ArrayList<>(), new FakeIngredientHelper<>(), new RendererMekHeat());
        registry.register(MysticalMechanics.class, new ArrayList<>(), new FakeIngredientHelper<>(), new RendererMysticalMechanics());
        registry.register(Reservoir.class, new ArrayList<>(), new FakeIngredientHelper<>(), new RendererReservoir());
        registry.register(Mineral.class, new ArrayList<>(), new FakeIngredientHelper<>(), new RendererMineral());
        registry.register(Modifier.class, new ArrayList<>(), new FakeIngredientHelper<>(), new RendererModifier());
    }
}
