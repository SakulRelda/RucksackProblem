/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basic.algorithms;

import basic.classes.Knapsack;
import basic.classes.KnapsackElement;
import java.util.List;

/**
 * Implementation of the Greedy Knapsack Algorithm
 * @author Lukas Adler
 * @version v1.0.0
 */
public class GreedyAlgorithm implements IAlgorithm{
    
    private final Knapsack sack;
    private final List<KnapsackElement> elements;

    /**
     * Constructor to set the Knapsack which should hold the solution and 
     * the possible elements for the Knapsack
     * @param sack
     * @param elements 
     */
    public GreedyAlgorithm(Knapsack sack, List<KnapsackElement> elements) {
        this.sack = sack;
        this.elements = elements;
    }

    /**
     * Starts the Algorithm and delivers the Knapsack with all elements and
     * informations
     *
     * Greedy takes what fits in fifo order
     * 
     * @return Knapsack
     */
    @Override
    public Knapsack startAlgorithm() {
        for (int i = 0; i < elements.size(); i++) {
            KnapsackElement element = elements.get(i);
            if(sack.getFreeMass()>element.getMassValue()){
                sack.addElementToKnapsack(element);
            }
        }
        return this.sack;
    }
    
}
