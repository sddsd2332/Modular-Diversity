package modulardiversity.components;

import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import modulardiversity.components.requirements.*;
import modulardiversity.util.ICraftingResourceHolder;

public class MachineComponents {
    public static abstract class MechanicalHatch extends MachineComponent<ICraftingResourceHolder<RequirementMechanical.ResourceToken>> {
        public MechanicalHatch(IOType ioType) {
            super(ioType);
        }

        @Override
        public ComponentType getComponentType() {
            return ComponentType.Registry.getComponent("mechanical");
        }
    }


    public static abstract class LaserHatch extends MachineComponent<ICraftingResourceHolder<RequirementLaser.ResourceToken>> {
        public LaserHatch(IOType ioType) {
            super(ioType);
        }

        @Override
        public ComponentType getComponentType() {
            return ComponentType.Registry.getComponent("laser");
        }

        public abstract void reset();
    }


    public static abstract class MekLaserAcceptor extends MachineComponent<ICraftingResourceHolder<RequirementMekLaser.ResourceToken>> {
        public MekLaserAcceptor(IOType ioType) {
            super(ioType);
        }

        @Override
        public ComponentType getComponentType() {
            return ComponentType.Registry.getComponent("meklaser");
        }
    }

    public static abstract class MekHeatHatch extends MachineComponent<ICraftingResourceHolder<RequirementMekHeat.ResourceToken>> {
        public MekHeatHatch(IOType ioType) {
            super(ioType);
        }

        @Override
        public ComponentType getComponentType() {
            return ComponentType.Registry.getComponent("mekheat");
        }
    }

    public static abstract class MysticalMechanicsHatch extends MachineComponent<ICraftingResourceHolder<RequirementMysticalMechanics.ResourceToken>> {
        public MysticalMechanicsHatch(IOType ioType) {
            super(ioType);
        }

        @Override
        public ComponentType getComponentType() {
            return ComponentType.Registry.getComponent("mysticalmechanics");
        }
    }
}
