package gameFight.vm;

import gameFight.vm.gameUnits.wm.*;
import gameFight.vm.knowledge.vm.Influence;

public class Render {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static void selectEnemy(){
        System.out.println(ANSI_CYAN + "ВЫБОР СОПЕРНИКА: \n1 - КОМПЬЮТЕР\n2 - ЧЕЛОВЕК" + ANSI_RESET);
    }
    public static void enterNameMsg(int serialNumber){
        if(serialNumber == 1)
            System.out.println(ANSI_CYAN + "ИМЯ ПЕРВОГО ИГРОКА: " + ANSI_RESET);
        else
            System.out.println(ANSI_CYAN + "ИМЯ ВТОРОГО ИГРОКА: " + ANSI_RESET);
    }
    public static void selectUnitMsg(Player player){
        System.out.println("_____________________________________________________________________" +
                ANSI_CYAN + "\n" + stepInfo(player.getStep()) + " " +
                player.getName() + " - ВЫБОР ПЕРСОНАЖА: " + ANSI_RESET);
    }

    public static void selectInfluenceMsg(Player player){
        System.out.println(ANSI_CYAN + "\t" + stepInfo(player.getStep()) +
                " " + player.getName() + " - ВЫБОР АТАКИ: " + ANSI_RESET);
    }

    public static void selectTargetMsg(Player player){
        System.out.println(ANSI_CYAN + "\t\t" + stepInfo(player.getStep()) + " " +
                player.getName() + " - ВЫБОР ЦЕЛИ: " + ANSI_RESET);
    }

    public static void unitInfoMsg(int serialNumber, Unit unit){
        if(serialNumber == 0)
            System.out.println("    " + unitInfo(unit));
        else
            System.out.println(serialNumber + " - " + unitInfo(unit));
    }
    public static void targetInfoMsg(int serialNumber, Unit unit){
            System.out.println("\t\t" + serialNumber + " - " + unitInfo(unit));
    }

    public static void influenceInfoMsg(int serialNumber, Influence influence){
        System.out.println("\t" + serialNumber + " - " + skillInfo(influence));
    }

    public static void choiceMsg(){
        System.out.print("ВЫБОР: ");
    }

    public static void stringMsg(){
        System.out.print("ВВЕДИТЕ: ");
    }

    public static void inputErrorMsg(){
        System.out.println(ANSI_RED + "Ошибка ввода, попробуйте еще раз" + ANSI_RESET);
    }

    public static void unitFailMsg(Player player, Unit unit){
        System.out.println(ANSI_RED + "\t\t\t\t" + player.getName() + " - " +
                unit.getName() + " убит" + ANSI_RESET);
    }

    public static void winMsg(Player player){
        System.out.println(ANSI_RED + "\n\n" + player.getName() + " ПОБЕДИЛ!!!" +
                ANSI_CYAN + "\n" + player.getName() + " ПОБЕДИЛ!!!" +
                ANSI_GREEN + "\n" + player.getName() + " ПОБЕДИЛ!!!" + ANSI_RESET);
    }

    public static void botSelectMsg(Player player, Unit unit){
        System.out.println("\n_____________________________________________________________________" +
                ANSI_CYAN + "\n" + stepInfo(player.getStep()) + " " +
                player.getName() + ANSI_RESET + " - " + unit.getName() + ":");
    }

    private static String unitInfo(Unit unit){
        return unit.getName() + ANSI_GREEN + " (Здоровье: " + unit.getHp() +
                " | Физ Сила: " + unit.getPhysicPower() + " | Маг Сила: " +
                 unit.getMagicPower() + " | Защита: " + unit.getDefence() + ")" + ANSI_RESET;
    }

    private static String skillInfo(Influence skill){
        return skill.getName() + ANSI_GREEN + " " + skill.getInfo() + ANSI_RESET;
    }

    public static void attackResultInfo(Unit unit,  Influence influence, int damage){
        System.out.println(ANSI_RED + "\t\t\t\t" + unit.getType() + "-" + unit.getName() +
                " получает " + damage + " урона от " + influence.getOwner().getType() +
                 "-" + influence.getOwner().getName() + " (" + influence.getName() +
                 ") осталось " + influence.getTime() + " хода" + ANSI_RESET);
    }

    public static void influenceStartInfo(Unit unit, Unit unitOwner,  Influence influence){
        System.out.println(ANSI_RED + "\t\t\t\t" + unit.getType() + "-" + unit.getName() +
                " подвергается влиянию " + unitOwner.getType() + "-" + unitOwner.getName() +
                " (" + influence.getName() + ") на " + influence.getTime() + " хода" + ANSI_RESET);
    }
    private static String stepInfo(int step){
        return "ХОД " + step;
    }
}
