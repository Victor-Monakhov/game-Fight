package gameFight.vm;

import arrListLib.vm.VMArrayList;
import gameFight.vm.gameUnits.wm.Unit;
import gameFight.vm.gameUnits.wm.UnitContainer;
import gameFight.vm.knowledge.vm.Influence;

public class Bot extends Player{

    @Override
    public Unit selectUnit() {
        int serialNumber = 0;
        VMArrayList<Unit> activeUnits = new VMArrayList<>();
        for(int i = 0; i < getArmy().getUnits().size(); ++i){
            Unit unit = getArmy().getUnits().get(i);
            if(getArmy().getUnits().get(i).getStep() == getStep()){
                activeUnits.addLast(unit);
                ++serialNumber;
            }
        }
        if(serialNumber == 0) return null;
        int choice = makeChoice(serialNumber) - 1;
        Render.botSelectMsg(this, activeUnits.get(choice));
        return activeUnits.get(choice);
    }

    @Override
    public Influence selectInfluence(Unit unit) {
        int serialNumber = unit.getSkills().size();
        int choice = makeChoice(serialNumber) - 1;
        return unit.getSkills().get(choice).copy();
    }

    @Override
    public Unit selectTarget(UnitContainer units) {
        int serialNumber = units.getUnits().size();
        int choice = makeChoice(serialNumber) - 1;
        return units.getUnits().get(choice);
    }
    @Override
    public int makeChoice(int pointsQuantity){
        return (int)(1 + Math.random()*(pointsQuantity));
    }
}
