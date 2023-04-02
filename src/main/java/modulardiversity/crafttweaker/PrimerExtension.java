package modulardiversity.crafttweaker;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.annotations.ZenRegister;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.integration.crafttweaker.RecipePrimer;
import hellfirepvp.modularmachinery.common.machine.IOType;
import modulardiversity.components.requirements.*;
import stanhebben.zenscript.annotations.ZenExpansion;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

@ZenRegister
@ZenExpansion("mods.modularmachinery.RecipePrimer")
public class PrimerExtension {
    //----------------------------------------------------------------------------------------------
    // all environmental
    //----------------------------------------------------------------------------------------------
    @ZenMethod
    public static RecipePrimer setPerTick(RecipePrimer primer, boolean perTick) {
        runOnLastRequirement(primer, RequirementEnvironmental.class,"setPerTick", (requirement) -> {
            requirement.setPerTick(perTick);
        });
        return primer;
    }



    //----------------------------------------------------------------------------------------------
    // mineral
    //----------------------------------------------------------------------------------------------
    @ZenMethod
    public static RecipePrimer addMineralInput(RecipePrimer primer, String name, int added) {
        requireMineral(primer, IOType.INPUT, name, added);
        return primer;
    }

    @ZenMethod
    public static RecipePrimer addMineralOutput(RecipePrimer primer, String name, int added) {
        requireMineral(primer, IOType.OUTPUT, name, added);
        return primer;
    }

    @ZenMethod
    public static RecipePrimer addOreRequirement(RecipePrimer primer, int min, int max) {
        runOnLastRequirement(primer,RequirementMineral.class,"addOreRequirement", (requirement) -> {
            requirement.oreMin = min;
            requirement.oreMax = max;
        });
        return primer;
    }

    private static void requireMineral(RecipePrimer primer, IOType io, String name, int added) {
        primer.appendComponent(new RequirementMineral(io,name, 0, Integer.MAX_VALUE, added));
    }

    //----------------------------------------------------------------------------------------------
    // reservoir
    //----------------------------------------------------------------------------------------------
    @ZenMethod
    public static RecipePrimer addReservoirInput(RecipePrimer primer, String name, int added) {
        requireReservoir(primer, IOType.INPUT, name, added);
        return primer;
    }

    @ZenMethod
    public static RecipePrimer addReservoirOutput(RecipePrimer primer, String name, int added) {
        requireReservoir(primer, IOType.OUTPUT, name, added);
        return primer;
    }

    @ZenMethod
    public static RecipePrimer addReservoirFluidRequirement(RecipePrimer primer, int min, int max) {
        runOnLastRequirement(primer,RequirementReservoir.class,"addReservoirFluidRequirement", (requirement) -> {
            requirement.fluidMin = min;
            requirement.fluidMax = max;
        });
        return primer;
    }

    @ZenMethod
    public static RecipePrimer addReservoirResidualRequirement(RecipePrimer primer, int min, int max) {
        runOnLastRequirement(primer,RequirementReservoir.class,"addReservoirResidualRequirement", (requirement) -> {
            requirement.residualMin = min;
            requirement.residualMax = max;
        });
        return primer;
    }

    private static void requireReservoir(RecipePrimer primer, IOType io, String name, int added) {
        primer.appendComponent(new RequirementReservoir(io,name, 0, Integer.MAX_VALUE,0, Integer.MAX_VALUE, added));
    }

    //----------------------------------------------------------------------------------------------
    // mechanical
    //----------------------------------------------------------------------------------------------
    @ZenMethod
    public static RecipePrimer addMechanicalInput(RecipePrimer primer, int power) {
        requireMechanical(primer, IOType.INPUT, power, false);
        return primer;
    }

    @ZenMethod
    public static RecipePrimer addMechanicalCrankInput(RecipePrimer primer, int power) {
        requireMechanical(primer, IOType.INPUT, power, true);
        return primer;
    }

    @ZenMethod
    public static RecipePrimer addMechanicalOutput(RecipePrimer primer, int power) {
        requireMechanical(primer, IOType.OUTPUT, power, false);
        return primer;
    }

    private static void requireMechanical(RecipePrimer primer, IOType io, int power, boolean crankAllowed) {
        primer.appendComponent(new RequirementMechanical(io,power,crankAllowed));
    }

    //----------------------------------------------------------------------------------------------
    // laser
    //----------------------------------------------------------------------------------------------
    @ZenMethod
    public static RecipePrimer addLaserInput(RecipePrimer primer, long mMJ) {
        requireLaser(primer, IOType.INPUT, mMJ);
        return primer;
    }

    private static void requireLaser(RecipePrimer primer, IOType io, long mMJ) {
        primer.appendComponent(new RequirementLaser(io,mMJ));
    }

    //----------------------------------------------------------------------------------------------
    // mekanism laser
    //----------------------------------------------------------------------------------------------
    @ZenMethod
    public static RecipePrimer addMekanismLaserInput(RecipePrimer primer, double power) {
        requireMekanismLaser(primer, IOType.INPUT, power);
        return primer;
    }

    private static void requireMekanismLaser(RecipePrimer primer, IOType io, double power) {
        primer.appendComponent(new RequirementMekLaser(io,power));
    }


    //----------------------------------------------------------------------------------------------
    // mekanism heat
    //----------------------------------------------------------------------------------------------
    @ZenMethod
    public static RecipePrimer addMekanismHeatInput(RecipePrimer primer, float temperature, float temperatureMin, double temperatureMax) {
        requireMekanismHeat(primer, IOType.INPUT, temperature, temperatureMin, temperatureMax);
        return primer;
    }

    @ZenMethod
    public static RecipePrimer addMekanismHeatOutput(RecipePrimer primer, float temperature, float temperatureMin, double temperatureMax) {
        requireMekanismHeat(primer, IOType.OUTPUT, temperature, temperatureMin, temperatureMax);
        return primer;
    }

    private static void requireMekanismHeat(RecipePrimer primer, IOType io, double temperature, double temperatureMin, double temperatureMax) {
        primer.appendComponent(new RequirementMekHeat(io,temperature,temperatureMin,temperatureMax));
    }

    //----------------------------------------------------------------------------------------------
    // mystical mechanics
    //----------------------------------------------------------------------------------------------
    @ZenMethod
    public static RecipePrimer addMysticalMechanicsInput(RecipePrimer primer, double min, double max) {
        primer.appendComponent(new RequirementMysticalMechanics(IOType.INPUT,min,max));
        return primer;
    }

    @ZenMethod
    public static RecipePrimer addMysticalMechanicsOutput(RecipePrimer primer, double level, int time) {
        primer.appendComponent(new RequirementMysticalMechanics(IOType.OUTPUT,level,time));
        return primer;
    }


    //----------------------------------------------------------------------------------------------
    // toolbox
    //----------------------------------------------------------------------------------------------
    private static <T extends ComponentRequirement> void runOnLastRequirement(RecipePrimer primer, Class<T> requiredClass, String method, Consumer<T> consumer) {
        ComponentRequirement last = getLastRequirement(primer);
        if(requiredClass.isInstance(last))
            consumer.accept((T) last);
        else
            CraftTweakerAPI.logError("Wrong component to call "+method+". (Expected: "+requiredClass+", Got: "+last.getClass()+")");
    }

    private static ComponentRequirement getLastRequirement(RecipePrimer primer) {
        List<ComponentRequirement<?, ?>> requirementList = primer.getComponents();
        if(requirementList instanceof LinkedList)
            return ((LinkedList<ComponentRequirement<?, ?>>) requirementList).getLast();
        return requirementList.get(requirementList.size()-1);
    }
}
