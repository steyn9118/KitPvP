package steyn91.kitPvP.mechanicsHandling;

import java.util.function.Consumer;

public class ParticleAdapter {
    enum ConditionOfStay { // условия существования партиклов, по другому можно назвать не ебу как
        TIMER, // существуют пока не истекло время в тиках
        WHILE_EXISTS(), // пока существует энтити
        INTERVAL_TIMER() // парктиклы появляются с каким то интервалом
    }




    public void singularParticle(ConditionOfStay condition)  {
            switch (condition) {
                case WHILE_EXISTS:
                    break;
                case INTERVAL_TIMER:
                    break;
                case TIMER:
                    break;

            }

    }


    
}
