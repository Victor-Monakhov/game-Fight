package gameFight.vm;

import gameFight.vm.gameUnits.wm.Unit;
import gameFight.vm.gameUnits.wm.UnitContainer;
import gameFight.vm.knowledge.vm.Influence;


public abstract class Player {
    private String name;
    private final UnitContainer army;
    private int step = 1;
    public Player() {
        this.army = new UnitContainer();
    }
    public void preparation(UnitContainer enemy){
        for(int i = 0; i < army.getUnits().size(); ++i){
            army.getUnits().get(i).setType(name);
            army.getUnits().get(i).setTargets(enemy.getUnits());
            army.getUnits().get(i).setAllies(army.getUnits());
        }
    }
    public abstract Unit selectUnit();
    public abstract Influence selectInfluence(Unit unit);
    public abstract Unit selectTarget(UnitContainer units);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UnitContainer getArmy() {
        return army;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public abstract int makeChoice(int pointsQuantity);

}
