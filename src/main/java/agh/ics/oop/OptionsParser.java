package agh.ics.oop;

import java.util.Arrays;
import java.util.List;

public class OptionsParser {
    public static MoveDirection[] parse (String[] args) throws IllegalArgumentException{
        List<String> original = Arrays.asList(args);
        return original.stream()
//                .filter(e->e.equals("b")|| e.equals("backward")
//        || e.equals("f") || e.equals("forward") ||
//                e.equals("r") || e.equals("right") ||
//                e.equals("l") || e.equals("left") ).
                .map(e->switch(e){
            case "f", "forward" ->MoveDirection.FORWARD;
            case "b", "backward"->MoveDirection.BACKWARD;
            case "r", "right" ->MoveDirection.RIGHT;
            case "l","left"->MoveDirection.LEFT;
            default -> throw new IllegalArgumentException(e + " is not legal move specification ");
        }).toArray(MoveDirection[] :: new);
    }
}
