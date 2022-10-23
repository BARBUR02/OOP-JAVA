package agh.ics.oop;

import java.util.Objects;

public class Vector2d {
    public final int x;
    public final int y;

    public Vector2d(int x,int y) {
        this.x = x;
        this.y= y;
    }

    public String toString(){
        return "("+x+","+y+")";
    }

    public static void main(String[] args) {
        Vector2d position1 = new Vector2d(1,2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2,1);
        System.out.println(position2);
        System.out.println(position1.add(position2));
        Vector2d position3 = new Vector2d(1,-1);
        System.out.println("\n\n\n=====================\n\n\n");

        MapDirection direction = MapDirection.NORTH;
        System.out.println(direction);
        System.out.println(direction.next().previous().equals(direction));
        System.out.println(direction.next());
        System.out.println(direction.previous());
        System.out.println(direction.toUnitVector());
    }

    public boolean precedes(Vector2d other){
        return this.x<=other.x && this.y<=other.y;
    }

    public boolean follows(Vector2d other){
        return this.x>=other.x && this.y>=other.y;
    }

    public Vector2d add(Vector2d other){
        return new Vector2d(this.x+other.x,this.y+other.y);
    }

    public Vector2d subtract(Vector2d other){
        return new Vector2d(this.x-other.x,this.y-other.y);
    }

    public Vector2d upperRight(Vector2d other){
        return new Vector2d(this.x >=other.x?this.x:other.x,this.y >=other.y?this.y:other.y);
    }
    public Vector2d lowerLeft(Vector2d other){
        return new Vector2d(this.x <=other.x?this.x:other.x,this.y <=other.y?this.y:other.y);
    }

    public Vector2d opposite(){
        return new Vector2d(-this.x,-this.y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector2d vector2d = (Vector2d) o;
        return x == vector2d.x && y == vector2d.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
