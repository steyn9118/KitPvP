package steyn91.kitPvP.mapRelated;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class GameController {

    private static final List<ArenaClassic> arenas = new LinkedList<>();

    public static ArenaClassic getClassicArena(String arenaId){
        Optional<ArenaClassic> arena = arenas.stream().filter(arenaClassic -> arenaClassic.getArenaId().equals(arenaId)).findFirst();
        if (arena.isPresent()) return arena.get();
        else throw new RuntimeException("Не найдена арена " + arenaId);
    }

    public static void addClassicArena(ArenaClassic arena) {
        arenas.add(arena);
    }

}
