package gameFight.vm.gameUnits.wm;

import gameFight.vm.knowledge.vm.LightMagic;
import gameFight.vm.knowledge.vm.MartialArts;

public class Wizard extends Unit implements LightMagic, MartialArts {

    public Wizard(String name, int hp, int physicPower, int magicPower, int defence) {
        super(name, hp, physicPower, magicPower, defence);
    }

    @Override
    public void skillsInit() {
        this.getSkills().concatArray(LIGHT_KNOWLEDGE);
        this.getSkills().concatArray(MARTIAL_KNOWLEDGE);
    }
}
