package com.puzzle.algorithm;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author : beline
 * @description : first try for the implementation of A* algorithm
 */
public class AStarSolver {
    public static void main(String[] args){
        Random r = new Random();
        ArrayList<String> str = new ArrayList<String>();
        str.add("empty");
        str.add("sad");
        str.add("guilty");
        str.add("disrespected");
        str.add("i don't care");
        str.add("hopeless");
        str.add("waiting for a change");
        str.add("trying my best");
        str.add("not being able to give up");
        System.out.println("How do i feel today?");
        int randomize = r.nextInt(str.size());
        String randomElement = str.get(randomize);
        System.out.println(randomElement);
    }
}
