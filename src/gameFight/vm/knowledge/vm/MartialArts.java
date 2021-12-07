package gameFight.vm.knowledge.vm;

import gameFight.vm.gameUnits.wm.Unit;

public interface MartialArts extends Knowledge{
    Influence[] MARTIAL_KNOWLEDGE = {
            new Influence("МОЩНЫЙ УДАР", actionTypes.PERSONAL_NEGATIVE, 0, Kick.strongBeatInfo()) {
                @Override
                public void influence(Unit unit, Unit enemy) {
                    Kick.kick(unit, enemy, STRONG_COEFFICIENT);
                }
            },
            new Influence("МАССОВЫЙ УДАР", actionTypes.MASSIVE_NEGATIVE, 0, Kick.massBeatInfo()) {
                @Override
                public void influence(Unit unit, Unit enemy) {
                    Kick.kick(unit, enemy, MASS_COEFFICIENT);
                }
            }
    };
    class Kick{
        private static void kick(Unit unit, Unit enemy, double coefficient){
            if(unit.getHp() > 0 && enemy.getHp() > 0){
                double unitPowMax = unit.getPhysicPower();
                double unitPowMin = unitPowMax / 2;
                double randomPow = unitPowMin + Math.random()*(unitPowMax - unitPowMin);
                double enemyDef = enemy.getDefence();
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
        }
        private static String strongBeatInfo(){
            return "(Немедленно наносит сильный урон одной цели,\n" +
                    "\t\tзависит от физ силы атакующего и защиты жертвы)";
        }
        private static String massBeatInfo(){
            return "(Немедленно наносит средний урон по всем врагам,\n" +
                    "\t\tзависит от физ силы атакующего и защиты жертвы)";
        }
    }
}
