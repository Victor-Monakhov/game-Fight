package gameFight.vm.knowledge.vm;

import gameFight.vm.gameUnits.wm.Unit;

public abstract class Influence {
    private String name;
    private final Knowledge.actionTypes type;
    private String info;
    private int time = 0;
    private Unit owner;

    public Influence(String name, Knowledge.actionTypes type, int time, String info){
        this.type = type;
        this.name = name;
        this.time = time;
        this.info = info;
    }

    public abstract void influence(Unit unit, Unit enemy);

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getName() { return name; }

    public Knowledge.actionTypes getType() {
        return type;
    }

    public String getInfo() {
        return info;
    }

    public Unit getOwner() {
        return owner;
    }

    public void setOwner(Unit owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object influence) {
        if(!(influence instanceof Influence)){
            return false;
        }
        Influence temp = (Influence) influence;
        return temp.getName().equals(name);
    }

    public Influence copy(){
        return new Influence(this.name, this.type, this.time, this.info) {
            @Override
            public void influence(Unit unit, Unit enemy) {
                Influence.this.influence(unit, enemy);
            }
        };
    }
}
