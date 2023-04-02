package modulardiversity.tile;

import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.tiles.base.TileColorableMachineComponent;
import modulardiversity.util.ReservoirTank;
import net.minecraft.nbt.NBTTagCompound;

public class TileJackHatch extends TileColorableMachineComponent {
    private ReservoirTank tank;
    private IOType ioType = IOType.INPUT;

    public TileJackHatch() {
        this.tank = new ReservoirTank();
        this.tank.setTileEntity(this);
    }

    @Override
    public void writeCustomNBT(NBTTagCompound compound) {
        super.writeCustomNBT(compound);
        NBTTagCompound tankTag = new NBTTagCompound();
        tank.writeToNBT(tankTag);
        compound.setTag("tank",tankTag);
    }

    @Override
    public void readCustomNBT(NBTTagCompound compound) {
        super.readCustomNBT(compound);
        NBTTagCompound tankTag = compound.getCompoundTag("tank");
        tank.readFromNBT(tankTag);
    }
}
