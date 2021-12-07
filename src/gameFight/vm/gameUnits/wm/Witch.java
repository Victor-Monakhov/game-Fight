package gameFight.vm.gameUnits.wm;

import gameFight.vm.knowledge.vm.DarkMagic;
import gameFight.vm.knowledge.vm.MartialArts;

public class Witch extends Unit implements DarkMagic, MartialArts {

    public Witch(String name, int hp, int physicPower, int magicPower, int defence) {
        super(name, hp, physicPower, magicPower, defence);
    }

    @Override
    public void skillsInit() {
        this.getSkills().concatArray(DARK_KNOWLEDGE);
        this.getSkills().concatArray(MARTIAL_KNOWLEDGE);
    }
}
