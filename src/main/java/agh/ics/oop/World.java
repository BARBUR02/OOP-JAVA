package agh.ics.oop;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class World {
    public static void main(String[] args) {
        System.out.println("System wystartowal");
//        Za pomoca stream:
        run(transform(args));
//        runStream(transformStream(args));
        System.out.println("System zakonczyl dzialanie");
    }
    static void run(Direction[] steps){
        /*
        System.out.println("Zwierzak idzie do przodu");
        for (int i=0;i<ruchy.length;i++) {
            if (i == ruchy.length - 1) System.out.println(ruchy[i]);
            else System.out.print(ruchy[i]+", ");
        }
        */
        System.out.println("Start");
        for (Direction step : steps ){
            action(step);
        }
        System.out.println("Stop");

    }

    static List <Direction> transformStream(String[] steps){
        List <String> list= Arrays.asList(steps);
        return list.stream().map(arg->switch(arg){
            case "f"-> Direction.FORWARD;
            case "b"-> Direction.BACKWARD;
            case "r"-> Direction.RIGHT;
            case "l"-> Direction.LEFT;
            default -> Direction.UNKNOWN;
        }).collect(Collectors.toList());
    }

    static void runStream(List<Direction> answer){
        answer.stream().filter(step->step!=Direction.UNKNOWN).forEach(step->action(step));
    }
    static Direction[] transform(String[] steps){
        Direction[] resultTab= new Direction[steps.length];
        for (int i=0;i<steps.length;i++){
            resultTab[i] = switch(steps[i]){
                case "f"-> Direction.FORWARD;
                case "b"-> Direction.BACKWARD;
                case "r"-> Direction.RIGHT;
                case "l"-> Direction.LEFT;
                default -> Direction.UNKNOWN;
            };
        }
        return resultTab;
    }

    static void action(Direction step){
        switch(step){
            case FORWARD-> System.out.println("Zwierzak idzie do przodu");
            case BACKWARD-> System.out.println("Zwierzak idzie do tylu");
            case RIGHT -> System.out.println("Zwierzak skreca w prawo");
            case LEFT-> System.out.println("Zwierzak skreca w lewo");
        }
    }
}
