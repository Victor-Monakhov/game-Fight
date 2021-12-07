package gameFight.vm.gameUnits.wm;

import arrListLib.vm.VMArrayList;

public class UnitContainer {
    private final VMArrayList<Unit> units = new VMArrayList<>();

    public UnitContainer() {
        this.units.addLast(new Barbarian("Варвар", 150, 50, 10, 20));
        this.units.addLast(new Witch("Ведьма", 100, 30, 50, 15));
        this.units.addLast(new Wizard("Маг", 120, 30, 60,10 ));
        for(int i = 0; i < units.size(); i++){
            units.get(i).skillsInit();
        }
    }

    public VMArrayList<Unit> getUnits() {
        return units;
    }
}
