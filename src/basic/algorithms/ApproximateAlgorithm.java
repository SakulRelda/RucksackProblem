/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basic.algorithms;

import basic.classes.Knapsack;
import basic.classes.KnapsackElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Approximatly Algorithm to calculate the highes possibility for the Knapsack Problem
 * @author Lukas Adler
 * @version v1.0.1
 */
public class ApproximateAlgorithm implements IAlgorithm{

    private int highesWorthSolutionValue;
    private boolean[] usedElements;

    private final List<KnapsackElement> elements;
    private final Knapsack sack;

    /**
     * Constructor to set the Knapsack which should hold the solution and 
     * the possible elements for the Knapsack
     * @param sack
     * @param elements 
     */
    public ApproximateAlgorithm(Knapsack sack, List<KnapsackElement> elements) {
        this.sack = sack;
        this.elements = elements;
    }

    /**
     * Starts the Algorithm and delivers the Knapsack with all elements and
     * informations
     *
     * @return Knapsack
     */
    @Override
    public Knapsack startAlgorithm() {
        highesWorthSolutionValue = 0;
        usedElements = new boolean[elements.size()];
        recursiveApproximateSolution(0, 0, 0);
        return this.sack;
    }

    /**
     * Handler for the complete recursive approximate solution
     * @param elementCounter
     * @param cumulatedMassValue
     * @param cumulatedWorthValue 
     */
    private void recursiveApproximateSolution(int elementCounter, int cumulatedMassValue, int cumulatedWorthValue) {
        if (cumulatedMassValue > this.sack.getMaximumMass()) {
            return;
        }
        //If the Counter is equal to the length of the elements which could be packed to the sack...
        if (elementCounter == usedElements.length) {
            //If the combination worth value is higher than the current optimum solution...
            if (cumulatedWorthValue > highesWorthSolutionValue) {
                optimalPath(cumulatedWorthValue);
            }
        } else {
            //Call the Method which handels the recursive method
            recursivePath(elementCounter, cumulatedMassValue, cumulatedWorthValue);
        }
    }

    /**
     * Path which is executed when there is a new optimum
     * @param cumulatedWorthValue 
     */
    private void optimalPath(int cumulatedWorthValue) {
        highesWorthSolutionValue = cumulatedWorthValue;
        safeOptimumToKnapsack();
    }

    /**
     * Recursive itertion of the approximation solutin
     * @param elementCounter
     * @param cumulatedMassValue
     * @param cumulatedWorthValue 
     */
    private void recursivePath(int elementCounter, int cumulatedMassValue, int cumulatedWorthValue) {
        usedElements[elementCounter] = false;
        recursiveApproximateSolution(elementCounter + 1, cumulatedMassValue, cumulatedWorthValue);
        usedElements[elementCounter] = true;
        recursiveApproximateSolution(elementCounter + 1, cumulatedMassValue + massVal(elementCounter), cumulatedWorthValue + worthVal(elementCounter));
    }

    /**
     * Returns the Mass Value for an Knapsack Element
     *
     * @param counter
     * @return Mass Value
     */
    private int massVal(int counter) {
        return this.elements.get(counter).getMassValue();
    }

    /**
     * Returns the Worth Value for an Knapsack Element
     *
     * @param counter
     * @return Worth Value
     */
    private int worthVal(int counter) {
        return this.elements.get(counter).getWorthValue();
    }

    /**
     * Safes the optimal Approximatly State to the Knapsack It's called every
     * time the worth value of the combinations is higher than before
     */
    private void safeOptimumToKnapsack() {
        sack.setContainedElements(new ArrayList<>());
        sack.setFreeMass(sack.getMaximumMass());
        sack.setKnapsackWorthValue(0);
        for (int i = 0; i < usedElements.length; i++) {
            if (usedElements[i]) {
                sack.addElementToKnapsack(elements.get(i));
            }
        }
    }
}
