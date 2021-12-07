package gameFight.vm.knowledge.vm;

public interface Knowledge {
    double PERSONAL_COEFFICIENT = 1;
    double MASS_MAGIC_COEFFICIENT = 0.4;
    double STRONG_COEFFICIENT = 1.2;
    double MASS_COEFFICIENT = 0.7;
    int SCRATCH = 1;
    enum actionTypes{
        PERSONAL_POSITIVE,
        PERSONAL_NEGATIVE,
        MASSIVE_POSITIVE,
        MASSIVE_NEGATIVE
    }
}
