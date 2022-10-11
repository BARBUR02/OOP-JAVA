package agh.ics.oop;


public class World {
    public static void main(String[] args) {
        System.out.println("System wystartowal");
        run(transform(args));
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
            switch(step){
                case FORWARD-> System.out.println("Zwierzak idzie do przodu");
                case BACKWARD-> System.out.println("Zwierzak idzie do tylu");
                case RIGHT -> System.out.println("Zwierzak skreca w prawo");
                case LEFT-> System.out.println("Zwierzak skreca w lewo");
            }
        }
        System.out.println("Stop");

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
}
