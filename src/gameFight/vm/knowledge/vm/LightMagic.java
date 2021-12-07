package gameFight.vm.knowledge.vm;

import gameFight.vm.gameUnits.wm.Unit;

public interface LightMagic extends Knowledge {
    Influence[] LIGHT_KNOWLEDGE = {
            new Influence("ИСЦЕЛЕНИЕ", actionTypes.PERSONAL_POSITIVE, 1, Heal.personalHealInfo()) {
                @Override
                public void influence(Unit unit, Unit ally) {
                    Heal.heal(unit, ally, PERSONAL_COEFFICIENT);
                }
            },
            new Influence("МАССОВОЕ ИСЦЕЛЕНИЕ", actionTypes.MASSIVE_POSITIVE, 2, Heal.massHealInfo()) {
                @Override
                public void influence(Unit unit, Unit ally) {
                    Heal.heal(unit, ally, MASS_MAGIC_COEFFICIENT);
                }
            }
    };
    class Heal{
        private static void heal(Unit unit, Unit ally, double coefficient){
            if(unit.getHp() > 0 && ally.getHp() > 0 && ally.getHp() < ally.getMaxHP()){
                double unitPowMax = unit.getMagicPower();
                double unitPowMin = unitPowMax / 2;
                double randomPow = unitPowMin + Math.random()*(unitPowMax - unitPowMin);
                double resultPow = randomPow * coefficient;
                int influenceResult = (int)Math.round(resultPow);
                if(ally.getMaxHP() < ally.getHp() + influenceResult){
                    influenceResult = ally.getMaxHP() - ally.getHp();
                }
                if(influenceResult > SCRATCH)
                    ally.setHp(ally.getHp() + influenceResult);
                else
                    ally.setHp(ally.getHp() + SCRATCH);
            }
        }
        private static String personalHealInfo(){
            return "(Средне исцеляет здоровье одной цели в течении 1 хода,\n" +
                    "\t\tзависит от маг силы целителя)";
        }
        private static String massHealInfo(){
            return "(Слабо исцеляет здоровье всех союзников в течении 2 ходов,\n" +
                    "\t\tзависит от маг силы целителя)";
        }
    }
}
