package com.oracle.clearing.sim.entities;


import com.oracle.clearing.sim.entities.enums.Direction;

import java.util.Objects;

public class Location {

    private int x;
    private int y;
    private Direction direction;

    public Location(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return x == location.x &&
                y == location.y &&
                direction == location.direction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, direction);
    }

    @Override
    public String toString() {
        return "Current Location{" +
                "x=" + x +
                ", y=" + y +
                ", direction=" + direction +
                '}';
    }
}
