package modulardiversity.components.component;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.machine.IOType;
import modulardiversity.components.requirements.RequirementMysticalMechanics;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ComponentMysticalMechanics extends ComponentType<RequirementMysticalMechanics> {
    @Nonnull
    @Override
    public String getRegistryName() {
        return "mysticalmechanics";
    }

    @Nullable
    @Override
    public String requiresModid() {
        return null;
    }

    @Nonnull
    @Override
    public RequirementMysticalMechanics provideComponent(IOType ioType, JsonObject requirement) {
        if(ioType == IOType.INPUT) {
            double levelRequiredMin = requirement.has("levelRequiredMin") ? requirement.getAsJsonPrimitive("levelRequiredMin").getAsDouble() : 0;
            double levelRequiredMax = requirement.has("levelRequiredMax") ? requirement.getAsJsonPrimitive("levelRequiredMax").getAsDouble() : Double.POSITIVE_INFINITY;
            return new RequirementMysticalMechanics(ioType, levelRequiredMin, levelRequiredMax);
        }
        else if(ioType == IOType.OUTPUT) {
            double levelOutput = requirement.has("level") ? requirement.getAsJsonPrimitive("level").getAsDouble() : 0;
            int time = requirement.has("time") ? requirement.getAsJsonPrimitive("time").getAsInt() : 5;
            return new RequirementMysticalMechanics(ioType, levelOutput, time);
        }
        throw new JsonParseException("The ComponentType \'"+getRegistryName()+"\' was marked neither input nor output.");
    }
}
