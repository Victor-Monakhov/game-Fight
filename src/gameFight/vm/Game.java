package gameFight.vm;

import gameFight.vm.gameUnits.wm.*;
import gameFight.vm.knowledge.vm.Influence;


public class Game {
    private final Human player1;
    private  Player player2;

    public Game(){
        player1 = new Human();
        preparation();
    }

    private void preparation(){
        player2 = (player1.selectEnemy() == 1)? new Bot() : new Human();
        Render.enterNameMsg(1);
        player1.setName(player1.selectName());
        Render.enterNameMsg(2);
        player2.setName(player1.selectName());
        player1.preparation(player2.getArmy());
        player2.preparation(player1.getArmy());
    }

    public void run(){
        while(!checkGameOver()){
            if(checkStep(player1) && checkStep(player2)) {
                player1.setStep(player1.getStep() + 1);
                player2.setStep(player2.getStep() + 1);
            }
            unitStep(player1, player2);
            if(checkGameOver())break;
            unitStep(player2, player1);
        }
    }
    private void unitStep(Player player, Player enemy){
        Unit currentUnit = player.selectUnit();
        if(currentUnit == null){ return; }
        currentUnit.setStep(currentUnit.getStep() + 1);
        influencesRun(currentUnit);
        if(!checkAlive(player)){ return; }
        Influence playerInfluence = player.selectInfluence(currentUnit);
        switch (playerInfluence.getType()){
            case PERSONAL_NEGATIVE:{
                Unit target = player.selectTarget(enemy.getArmy());
                attack(enemy, currentUnit, target, playerInfluence);
                break;
            }
            case PERSONAL_POSITIVE:{
                Unit target = player.selectTarget(player.getArmy());
                attack(player, currentUnit, target, playerInfluence);
                break;
            }
            case MASSIVE_NEGATIVE:{
                massiveAttack(enemy, currentUnit, playerInfluence);
                break;
            }
            case MASSIVE_POSITIVE:{
                massiveAttack(player, currentUnit, playerInfluence);
                break;
            }
        }
    }
    private void influencesRun(Unit unit){
        for(int i = 0; i < unit.getInfluences().size(); i++){
            Influence influence = unit.getInfluences().get(i);
            int pastHp = unit.getHp();
            influence.influence(influence.getOwner(), unit);
            influence.setTime(influence.getTime() - 1);
            Render.attackResultInfo(unit, influence, getAttackResult(unit, pastHp));
            if(influence.getTime() == 0)
                unit.getInfluences().delete(i);
        }
    }
    private void attack(Player playerTarget, Unit unit, Unit target, Influence influence){
        if(influence.getTime() == 0) {
            influence.setOwner(unit);
            int pastHp = target.getHp();
            influence.influence(unit, target);
            Render.attackResultInfo(target, influence, getAttackResult(target, pastHp));
            checkAlive(playerTarget);
        }
        else {
            target.setInfluences(influence);
            influence.setOwner(unit);
            Render.influenceStartInfo(target, unit, influence);
        }
    }
    private void massiveAttack(Player playerTarget, Unit unit, Influence influence){
        for(int i = 0; i < playerTarget.getArmy().getUnits().size(); ++i){
            Unit target = playerTarget.getArmy().getUnits().get(i);
            attack(playerTarget, unit, target, influence.copy());
        }
    }
    private boolean checkStep(Player player){
        int counter = 0;
        for(int i= 0; i < player.getArmy().getUnits().size(); ++i){
            Unit unit = player.getArmy().getUnits().get(i);
            if(unit.getStep() > player.getStep())
                ++counter;
        }
        return player.getArmy().getUnits().size() == counter;
    }
    private boolean checkAlive(Player player){
        for(int i= 0; i < player.getArmy().getUnits().size(); ++i){
            Unit unit = player.getArmy().getUnits().get(i);
            if(unit.getHp() <= 0) {
                Render.unitFailMsg(player, unit);
                player.getArmy().getUnits().delete(i);
                return false;
            }
        }
        return true;
    }
    private int getAttackResult(Unit unit, int pastHp){
        return pastHp - unit.getHp();
    }
    private boolean checkGameOver(){
        if(player1.getArmy().getUnits().size() == 0){
            Render.winMsg(player2);
            return true;
        }
        if(player2.getArmy().getUnits().size() == 0){
            Render.winMsg(player1);
            return true;
        }
        return false;
    }

}
