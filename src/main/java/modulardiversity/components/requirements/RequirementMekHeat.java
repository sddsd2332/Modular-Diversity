package modulardiversity.components.requirements;

import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.crafting.helper.RecipeCraftingContext;
import hellfirepvp.modularmachinery.common.crafting.requirement.type.RequirementType;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.modifier.RecipeModifier;
import modulardiversity.components.MachineComponents;
import modulardiversity.jei.JEIComponentMekHeat;
import modulardiversity.jei.ingredients.MekHeat;
import modulardiversity.util.IResourceToken;
import modulardiversity.util.Misc;

import java.util.List;

public class RequirementMekHeat extends RequirementConsumePerTick<MekHeat, RequirementMekHeat.ResourceToken> {
    double temperature;
    double temperatureRequiredMin;
    double temperatureRequiredMax;

    public RequirementMekHeat(IOType actionType, double temperature, double temperatureRequiredMin, double temperatureRequiredMax) {
        super(ComponentType.Registry.getComponent("mekheat"), actionType);
        this.temperature = temperature;
        this.temperatureRequiredMin = temperatureRequiredMin;
        this.temperatureRequiredMax = temperatureRequiredMax;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getTemperatureRequiredMax() {
        return temperatureRequiredMax;
    }

    public double getTemperatureRequiredMin() {
        return temperatureRequiredMin;
    }

    @Override
    protected RequirementMekHeat.ResourceToken emitConsumptionToken(RecipeCraftingContext context) {
        return new ResourceToken(temperature, temperatureRequiredMin, temperatureRequiredMax);
    }

    @Override
    protected boolean isCorrectHatch(MachineComponent component) {
        return component.getComponentType().getRegistryName().equals("mekheat") &&
                component instanceof MachineComponents.MekHeatHatch &&
                component.getIOType() == getActionType();
    }

    @Override
    public ComponentRequirement<MekHeat> deepCopy() {
        return new RequirementMekHeat(getActionType(),temperature, temperatureRequiredMin, temperatureRequiredMax);
    }

    @Override
    public ComponentRequirement<MekHeat> deepCopyModified(List<RecipeModifier> modifiers) {
        return new RequirementMekHeat(getActionType(),
                Misc.applyModifiers(modifiers,"mekheat",getActionType(),temperature,false),
                Misc.applyModifiers(modifiers,"mekheat_min",getActionType(),temperatureRequiredMin,false),
                Misc.applyModifiers(modifiers,"mekheat_max",getActionType(), temperatureRequiredMax,false)
        );
    }

    @Override
    public JEIComponent<MekHeat> provideJEIComponent() {
        return new JEIComponentMekHeat(this);
    }

    public static class ResourceToken implements IResourceToken {
        public double temperature;
        public double requiredTemperatureMin;
        public double requiredTemperatureMax;
        private boolean requiredTemperatureMet;

        public ResourceToken(double temperature, double requiredTemperatureMin, double requiredTemperatureMax) {
            this.temperature = temperature;
            this.requiredTemperatureMin = requiredTemperatureMin;
            this.requiredTemperatureMax = requiredTemperatureMax;
        }

        public double getTemperature() {
            return temperature;
        }

        public double getRequiredTemperatureMin() {
            return requiredTemperatureMin;
        }

        public double getRequiredTemperatureMax() {
            return requiredTemperatureMax;
        }

        public void setTemperatureMet() {
            requiredTemperatureMet = true;
        }

        @Override
        public void applyModifiers(RecipeCraftingContext modifiers, IOType ioType, float durationMultiplier) {
            requiredTemperatureMin = Misc.applyModifiers(modifiers,"mekheat_min",ioType, requiredTemperatureMin,false);
            requiredTemperatureMax = Misc.applyModifiers(modifiers,"mekheat_max",ioType, requiredTemperatureMax,false);
            temperature = Misc.applyModifiers(modifiers,"mekheat",ioType, temperature,false);
        }

        @Override
        public void applyModifiers(RecipeCraftingContext modifiers, RequirementType target, IOType ioType, float durationMultiplier) {

        }

        @Override
        public boolean isEmpty() {
            return requiredTemperatureMet;
        }
    }
}
