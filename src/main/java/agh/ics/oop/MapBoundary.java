package agh.ics.oop;

import java.util.*;

@SuppressWarnings("unchecked")
public class MapBoundary implements IPositionChangeObserver{
    private class MyCompX implements Comparator<AbstractMapElement> {
        @Override
        public int compare(AbstractMapElement o1, AbstractMapElement o2) {
            if (o1.getPosition().x < o2.getPosition().x) {
                return -1;
            }
            if (o1.getPosition().x > o2.getPosition().x) {
                return 1;
            }
            if (o1.getPosition().y - o2.getPosition().y != 0) {
                return o1.getPosition().y - o2.getPosition().y;
            }
            if (o1 instanceof Animal && o2 instanceof Grass){
                return 1;
            }
            if (o2 instanceof Animal && o1 instanceof Grass){
                return -1;
            }
            return 0;
        }
    }

    private class MyCompY implements Comparator<AbstractMapElement> {
        @Override
        public int compare(AbstractMapElement o1, AbstractMapElement o2) {
            if (o1.getPosition().y < o2.getPosition().y) {
                return -1;
            }
            if (o1.getPosition().y > o2.getPosition().y) {
                return 1;
            }
            if (o1.getPosition().x - o2.getPosition().x != 0) {
                return o1.getPosition().x - o2.getPosition().x;
            }
            if (o1 instanceof Animal && o2 instanceof Grass){
                return 1;
            }
            if (o2 instanceof Animal && o1 instanceof Grass){
                return -1;
            }
            return 0;
        }
    }
    private Map<Vector2d,IMapElement> mapElements;
    public SortedSet<IMapElement> setX;
    public SortedSet<IMapElement> setY;

    public void addToMap(Vector2d key, IMapElement value){
        mapElements.put(key,value);
        this.setX.add(value);
        this.setY.add(value);
    }

    public void removeKey(Vector2d key){
        IMapElement element = mapElements.get(key);
        mapElements.remove(key);
        this.setX.remove(element);
        this.setY.remove(element);
    }

    public MapBoundary() {
        this.setX =new TreeSet(new MyCompX());
        this.setY =new TreeSet(new MyCompY());
        mapElements= new HashMap<Vector2d,IMapElement>();
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        System.out.println("Position changed w boundary!"+" from: "+oldPosition+" to: "+newPosition );
        IMapElement element=mapElements.get(oldPosition);
        mapElements.remove(oldPosition);
        mapElements.put(newPosition,element);
        this.setX.remove(element);
        this.setY.remove(element);
        this.setX.add(element);
        this.setY.add(element);
//        System.out.println("First X item: " +this.setX.first().getPosition());
//        System.out.println("last X item: " +this.setX.last().getPosition());
//        System.out.println("First Y item: " +this.setY.first().getPosition());
//        System.out.println("Last Y item: " +this.setY.last().getPosition());
    }

    public int getFirstX(){
//        System.out.println(this.setX.first().getPosition().x + " firstPositionX");
//        for (IMapElement el: setX){
//            System.out.println(el.getPosition());
//        }
        return this.setX.first().getPosition().x;
    }
    public int getLastX(){
//        System.out.println(this.setX.last().getPosition().x + " lastPositionX");
        return this.setX.last().getPosition().x;
    }
    public int getFirstY(){
//        System.out.println(this.setY.first().getPosition().y + " firstPositionY");
        return this.setY.first().getPosition().y;
    }
    public int getLastY(){
//        System.out.println(this.setY.last().getPosition().y+ " lastPositionY");
        return this.setY.last().getPosition().y;
    }
}
