/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basic.algorithms;

import basic.classes.Knapsack;
import basic.classes.KnapsackElement;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Solution for the Knapsack Problem by using the Profit Index Solution
 * PI = Profit Index
 * W = Worth
 * R = Weight
 * 
 * The algorithm starts to add KnapsackElementProfit Object to the Knapsack 
 * started by the highest PI Value.
 * 
 * @author Lukas Adler
 * @version v1.0.0
 */
public class ProfiIndexAlgorithm implements IAlgorithm{
    
    private final Knapsack sack;
    private final ArrayList<KnapsackElement> elements;

    public ProfiIndexAlgorithm(Knapsack sack, ArrayList<KnapsackElement> elements) {
        this.sack = sack;
        this.elements = elements;
    }
    
    /**
     * Starts the Algorithm and delivers the Knapsack with all elements and
     * informations
     *
     * Sorty the Elementlist by the Profit Index
     * 
     * @return Knapsack
     */
    @Override
    public Knapsack startAlgorithm(){
        sortListByProfitIndex(this.elements);
        for (int i = 0; i < this.elements.size(); i++) {
            KnapsackElement element = this.elements.get(i);
            if(sack.getFreeMass()>element.getMassValue()){
                sack.addElementToKnapsack(element);
            }
        }
        return this.sack;
    }
    
    /**
     * Sorts the Elements by the Value of the ProfiIndex
     * A higher Profit Index has a higher profit in this algorithm
     * @param elements 
     */
    public void sortListByProfitIndex(ArrayList<KnapsackElement> elements){
        Collections.sort(elements, (KnapsackElement e1, KnapsackElement e2) -> {
            return (e1.getProfitIndex() > e2.getProfitIndex()?-1:1);
        });
    }
}
