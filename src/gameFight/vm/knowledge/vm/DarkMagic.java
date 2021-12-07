package gameFight.vm.knowledge.vm;

import gameFight.vm.gameUnits.wm.Unit;

public interface DarkMagic extends Knowledge{
    Influence[] DARK_KNOWLEDGE = {
            new Influence("ЧУМА", actionTypes.PERSONAL_NEGATIVE, 2, Toxin.personalIllnessInfo()) {
                @Override
                public void influence(Unit unit, Unit enemy) {
                    Toxin.toxin(unit, enemy, PERSONAL_COEFFICIENT);
                }
            },
            new Influence("МАССОВАЯ ЧУМА", actionTypes.MASSIVE_NEGATIVE, 3, Toxin.massIllnessInfo()) {
                @Override
                public void influence(Unit unit, Unit enemy) {
                    Toxin.toxin(unit, enemy, MASS_MAGIC_COEFFICIENT);
                }
            }
    };
    class Toxin{
        private static void toxin(Unit unit, Unit enemy, double coefficient){
            double unitPowMax = unit.getMagicPower();
            double unitPowMin = unitPowMax / 2;
            double randomPow = unitPowMin + Math.random()*(unitPowMax - unitPowMin);
            double enemyDef = enemy.getHp() / 10.0;
            double resultDef = Math.random() * enemyDef;
            double resultPow = randomPow * coefficient;
            int damage = (int)Math.round(resultPow - resultDef);
            if(damage > SCRATCH)
                enemy.setHp(enemy.getHp() - damage);
            else
                enemy.setHp(enemy.getHp() - SCRATCH);
            if(enemy.getHp() <= 0)
                enemy.setHp(0);
        }
        private static String personalIllnessInfo(){
            return "(Наносит средний урон одной цели в течении 2 ходов,\n" +
                    "\t\tзависит от маг силы атакующего и здоровья жертвы)";
        }
        private static String massIllnessInfo(){
            return "(Наносит слабый урон по всем врагам в течении 3 ходов,\n" +
                    "\t\tзависит от маг силы атакующего и здоровья жертвы)";
        }
    }
}
