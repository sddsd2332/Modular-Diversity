package modulardiversity.tile;

import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import modulardiversity.components.MachineComponents;
import modulardiversity.components.requirements.RequirementMekLaser;
import modulardiversity.tile.base.TileEntityMekLaser;
import modulardiversity.util.ICraftingResourceHolder;

import javax.annotation.Nullable;

public class TileEntityMekLaserAcceptor extends TileEntityMekLaser {

    public TileEntityMekLaserAcceptor() {
        super();
    }

    @Nullable
    @Override
    public MachineComponent provideComponent() {
        return new MachineComponents.MekLaserAcceptor(IOType.INPUT) {
            @Override
            public ICraftingResourceHolder<RequirementMekLaser.ResourceToken> getContainerProvider() {
                return TileEntityMekLaserAcceptor.this;
            }
        };

    }

    @Override
    public String getInputProblem(RequirementMekLaser.ResourceToken token) {
        return "craftcheck.meklaser.input";
    }

    @Override
    public String getOutputProblem(RequirementMekLaser.ResourceToken token) {
        return "craftcheck.meklaser.output";
    }
}
