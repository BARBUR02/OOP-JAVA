package agh.ics.oop;

import agh.ics.oop.gui.App;
import javafx.application.Application;

import java.util.Arrays;
import java.util.List;
import java.util.SortedSet;
import java.util.stream.Collectors;

public class World {
    public static void main(String[] args) {
        Application.launch(App.class,args);

//
//        MapBoundary test=new MapBoundary();
//        SortedSet zbior = test.setX;
//        zbior.add(new Animal(new GrassField(10),new Vector2d(4,5)));
//        zbior.add(new Animal(new GrassField(10),new Vector2d(2,2)));
//        zbior.add(new Animal(new GrassField(10),new Vector2d(4,4)));
//        zbior.add(new Animal(new GrassField(10),new Vector2d(2,3)));
//        zbior.add(new Animal(new GrassField(10),new Vector2d(4,4)));
//        zbior.add(new Animal(new GrassField(10),new Vector2d(3,2)));
//        AbstractMapElement first= (AbstractMapElement) zbior.first();
//        AbstractMapElement last= (AbstractMapElement) zbior.last();
//        System.out.println(first.getPosition());
//        System.out.println(last.getPosition());
//        try {
//            MoveDirection[] directions = new OptionsParser().parse(args);
////        IWorldMap map = new RectangularMap(10, 5);
//            IWorldMap map = new GrassField(10);
//            Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 3)};
////        Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4), new Vector2d(5, 4)};
//            IEngine engine = new SimulationEngine(directions, map, positions);
//            engine.run();
//            System.out.println(map);
//        }
//        catch (IllegalArgumentException e){
//            System.out.println(e);
//            System.exit(0);
//        }
    }

    static void run(Direction[] steps) {
        /*
        System.out.println("Zwierzak idzie do przodu");
        for (int i=0;i<ruchy.length;i++) {
            if (i == ruchy.length - 1) System.out.println(ruchy[i]);
            else System.out.print(ruchy[i]+", ");
        }
        */
        System.out.println("Start");
        for (Direction step : steps) {
            action(step);
        }
        System.out.println("Stop");

    }

    static List<Direction> transformStream(String[] steps) {
        List<String> list = Arrays.asList(steps);
        return list.stream().map(arg -> switch (arg) {
            case "f" -> Direction.FORWARD;
            case "b" -> Direction.BACKWARD;
            case "r" -> Direction.RIGHT;
            case "l" -> Direction.LEFT;
            default -> Direction.UNKNOWN;
        }).collect(Collectors.toList());
    }

    static void runStream(List<Direction> answer) {
        answer.stream().filter(step -> step != Direction.UNKNOWN).forEach(step -> action(step));
    }

    static Direction[] transform(String[] steps) {
        Direction[] resultTab = new Direction[steps.length];
        for (int i = 0; i < steps.length; i++) {
            resultTab[i] = switch (steps[i]) {
                case "f" -> Direction.FORWARD;
                case "b" -> Direction.BACKWARD;
                case "r" -> Direction.RIGHT;
                case "l" -> Direction.LEFT;
                default -> Direction.UNKNOWN;
            };
        }
        return resultTab;
    }

    static void action(Direction step) {
        switch (step) {
            case FORWARD -> System.out.println("Zwierzak idzie do przodu");
            case BACKWARD -> System.out.println("Zwierzak idzie do tylu");
            case RIGHT -> System.out.println("Zwierzak skreca w prawo");
            case LEFT -> System.out.println("Zwierzak skreca w lewo");
        }
    }
}
