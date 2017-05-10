/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basic.helpers;

import basic.classes.Knapsack;
import basic.classes.KnapsackElement;
import java.util.ArrayList;

/**
 * Holds some global Informations
 * @author adlerlu
 */
public class StandardHelper {
    //Holds all Knapsack Elements
    public static ArrayList<KnapsackElement> knapsackElements = new ArrayList<>();

    //Holds all Knapsacks
    public static ArrayList<Knapsack> knapsacks = new ArrayList<>();
    
    //Holds the Knapsack with the Solution
    public static Knapsack solutionSack;
}
