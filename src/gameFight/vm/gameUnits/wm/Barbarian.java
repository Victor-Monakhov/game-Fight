package gameFight.vm.gameUnits.wm;

import gameFight.vm.knowledge.vm.MartialArts;

public class Barbarian extends Unit implements MartialArts {

    public Barbarian(String name, int hp, int physicPower, int magicPower, int defence) {
        super(name, hp, physicPower, magicPower, defence);
    }

    @Override
    public void skillsInit() {
        this.getSkills().concatArray(MARTIAL_KNOWLEDGE);
    }
}
