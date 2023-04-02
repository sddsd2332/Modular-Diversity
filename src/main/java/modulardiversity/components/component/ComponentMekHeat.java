package modulardiversity.components.component;

import com.google.gson.JsonObject;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.machine.IOType;
import modulardiversity.components.requirements.RequirementMekHeat;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ComponentMekHeat extends ComponentType<RequirementMekHeat> {
    @Nonnull
    @Override
    public String getRegistryName() {
        return "mekheat";
    }

    @Nullable
    @Override
    public String requiresModid() {
        return null;
    }

    @Nonnull
    @Override
    public RequirementMekHeat provideComponent(IOType ioType, JsonObject requirement) {
        return provideComponentInternal(ioType, requirement);
    }

    private RequirementMekHeat provideComponentInternal(IOType ioType, JsonObject requirement) {
        double temperatureRequiredMin = requirement.has("temperatureRequiredMin") ? requirement.getAsJsonPrimitive("temperatureRequiredMin").getAsDouble() : 0;
        double temperatureRequiredMax = requirement.has("temperatureRequiredMax") ? requirement.getAsJsonPrimitive("temperatureRequiredMax").getAsDouble() : Double.POSITIVE_INFINITY;
        double temperature = requirement.has("temperature") ? requirement.getAsJsonPrimitive("temperature").getAsDouble() : 0;
        return new RequirementMekHeat(ioType, temperature, temperatureRequiredMin, temperatureRequiredMax);
    }
}
