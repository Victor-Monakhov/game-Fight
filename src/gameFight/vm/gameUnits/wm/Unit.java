package gameFight.vm.gameUnits.wm;

import arrListLib.vm.VMArrayList;
import gameFight.vm.knowledge.vm.Influence;

public abstract class Unit {
    private String type;
    private String name;
    private final int maxHP;
    private int hp;
    private int physicPower;
    private int magicPower;
    private int defence;
    private int step = 1;
    private VMArrayList<Unit> targets;
    private VMArrayList<Unit> allies;
    private VMArrayList<Influence> influences;
    private VMArrayList<Influence> skills;
    public Unit(String name, int hp, int physicPower, int magicPower, int defence){
        this.targets = new VMArrayList<>();
        this.allies = new VMArrayList<>();
        this.influences = new VMArrayList<>();
        this.skills = new VMArrayList<>();
        this.name = name;
        this.hp = hp;
        this.maxHP = hp;
        this.physicPower = physicPower;
        this.magicPower = magicPower;
        this.defence = defence;
    }

    public abstract void skillsInit();

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public String getName() { return name; }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMaxHP() { return maxHP; }

    public int getPhysicPower() {
        return physicPower;
    }

    public void setPhysicPower(int physicPower) {
        this.physicPower = physicPower;
    }

    public int getMagicPower() {
        return magicPower;
    }

    public void setMagicPower(int magicPower) {
        this.magicPower = magicPower;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public VMArrayList<Influence> getInfluences() {
        return influences;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public void setInfluences(Influence influence) {
        for(int i = 0; i < influences.size(); ++i){
            if(influences.get(i).equals(influence)){
                influences.set(i, influence);
                return;
            }
        }
        this.influences.addLast(influence);
    }

    public VMArrayList<Unit> getTargets() {
        return targets;
    }

    public VMArrayList<Unit> getAllies() {
        return allies;
    }

    public void setTargets(VMArrayList<Unit> targets) {
        this.targets = targets;
    }

    public void setAllies(VMArrayList<Unit> allies) {
        this.allies = allies;
    }

    public VMArrayList<Influence> getSkills() {
        return skills;
    }

    public void setSkills(VMArrayList<Influence> skills) {
        this.skills = skills;
    }
}
