package modulardiversity.tile;

import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import modulardiversity.components.MachineComponents;
import modulardiversity.components.requirements.RequirementMekHeat;
import modulardiversity.tile.base.TileEntityMekHeat;
import modulardiversity.util.ICraftingResourceHolder;

import javax.annotation.Nullable;

public class TileEntityMekHeatOutput extends TileEntityMekHeat {
    @Nullable
    @Override
    public MachineComponent provideComponent() {
        return new MachineComponents.MekHeatHatch(IOType.OUTPUT) {
            @Override
            public ICraftingResourceHolder<RequirementMekHeat.ResourceToken> getContainerProvider() {
                return TileEntityMekHeatOutput.this;
            }
        };
    }

    @Override
    public boolean consume(RequirementMekHeat.ResourceToken token, boolean doConsume) {
        return false;
    }

    @Override
    public boolean generate(RequirementMekHeat.ResourceToken token, boolean doGenerate) {
        if(temperature < token.getRequiredTemperatureMin() || temperature > token.getRequiredTemperatureMax())
            return false;
        token.setTemperatureMet();
        if(doGenerate)
            transferHeatTo(token.getTemperature());
        return true;
    }
}
