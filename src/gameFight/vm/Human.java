package gameFight.vm;

import arrListLib.vm.VMArrayList;
import gameFight.vm.gameUnits.wm.Unit;
import gameFight.vm.gameUnits.wm.UnitContainer;
import gameFight.vm.knowledge.vm.Influence;

import java.util.Scanner;

public class Human extends Player {
    Scanner scan;

    public Human() {
        scan = new Scanner(System.in);
    }

    public int selectEnemy(){
        Render.selectEnemy();
        return makeChoice(2);
    }

    public String selectName(){
        return enterString();
    }

    @Override
    public Unit selectUnit(){
        int serialNumber = 0;
        VMArrayList<Unit> activeUnits = new VMArrayList<>();
        Render.selectUnitMsg(this);
        for(int i = 0; i < getArmy().getUnits().size(); ++i){
            Unit unit = getArmy().getUnits().get(i);
            if(getArmy().getUnits().get(i).getStep() == getStep()){
                Render.unitInfoMsg(++serialNumber, unit);
                activeUnits.addLast(unit);
            }
            else
                Render.unitInfoMsg(0, unit);
        }
        if(serialNumber == 0) return null;
        int choice = makeChoice(serialNumber) - 1;
        return activeUnits.get(choice);
    }
    @Override
    public Influence selectInfluence(Unit unit){
        int serialNumber = 0;
        Render.selectInfluenceMsg(this);
        for(int i = 0; i < unit.getSkills().size(); ++i){
            Influence influence = unit.getSkills().get(i);
            Render.influenceInfoMsg(++serialNumber, influence);
        }
        int choice = makeChoice(serialNumber) - 1;
        return unit.getSkills().get(choice).copy();
    }
    @Override
    public Unit selectTarget(UnitContainer units){
        int serialNumber = 0;
        Render.selectTargetMsg(this);
        for(int i = 0; i < units.getUnits().size(); ++i){
            Unit unit = units.getUnits().get(i);
            Render.targetInfoMsg(++serialNumber, unit);
        }
        int choice = makeChoice(serialNumber) - 1;
        return units.getUnits().get(choice);
    }
    @Override
    public int makeChoice(int pointsQuantity){
        while(true){
            try {
                Render.choiceMsg();
                int choice = scan.nextInt();
                if(choice > 0 && choice <= pointsQuantity)
                    return choice;
                else
                    throw new Exception();
            }catch (Exception ex){
                Render.inputErrorMsg();
                scan = new Scanner(System.in);
            }
        }
    }

    private String enterString(){
        while(true){
            try {
                Render.stringMsg();
                String string = scan.next();
                if(string.length() > 0)
                    return string;
                else
                    throw new Exception();
            }catch (Exception ex){
                Render.inputErrorMsg();
                scan = new Scanner(System.in);
            }
        }
    }
}
