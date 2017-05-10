/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basic.classes;

import java.util.ArrayList;

/**
 * The knapsack is the class which contains the objects. A Knapsack has a
 * maximum mass value which limits the elements of objects.
 *
 * @author adlerlu
 */
public class Knapsack {

    private int id;
    private int maximumMass;
    private int freeMass;
    private int worthValue = 0;
    private String name;
    private ArrayList<KnapsackElement> containedElements;

    /**
     * Constructor which needs the maximum Weight and a Name for a Knapsack
     * @param maximumMass
     * @param name 
     */
    public Knapsack(int maximumMass,String name) {
        this.name = name;
        this.maximumMass = maximumMass;
        this.freeMass = this.maximumMass;
        this.containedElements = new ArrayList<>();
    }

    /**
     * Get the maximum Weight for the Knapsack
     * @return weight
     */
    public int getMaximumMass() {
        return maximumMass;
    }

    /**
     * Sets the maximum Weight for the Knapsack
     * @param maximumMass 
     */
    public void setMaximumMass(int maximumMass) {
        this.maximumMass = maximumMass;
    }

    /**
     * Get the free Weight for the Knapsack
     * This means the Value which is free for new Elements
     * @return freeMass
     */
    public int getFreeMass() {
        return freeMass;
    }

    /**
     * Sets the free Weight for the Knapsack
     * @param freeMass 
     */
    public void setFreeMass(int freeMass) {
        this.freeMass = freeMass;
    }

    /**
     * Get the worth of all elements which are in the knapsack
     * @return 
     */
    public int getKnapsackWorthValue() {
        return worthValue;
    }

    /**
     * Sets the Worth of the Knapsack
     * @param knapsackWorthValue 
     */
    public void setKnapsackWorthValue(int knapsackWorthValue) {
        this.worthValue = knapsackWorthValue;
    }

    /**
     * Gets the Elements which are in the Knapsack
     * @return Knapsack Elements
     */
    public ArrayList<KnapsackElement> getContainedElements() {
        return containedElements;
    }

    /**
     * Get the worth of all elements which are in the knapsack
     * @return worthValue
     */
    public int getWorthValue() {
        return worthValue;
    }

    /**
     * Sets the Worth of the Knapsack
     * @param worthValue  
     */
    public void setWorthValue(int worthValue) {
        this.worthValue = worthValue;
    }

    /**
     * Get the Name of the Knapsack
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the Name of the Knapsack
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the ID of the Knapsack
     * @return 
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the Knapsack
     * @param id 
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * Setts the Elements
     * @param containedElements 
     */
    public void setContainedElements(ArrayList<KnapsackElement> containedElements) {
        this.containedElements = containedElements;
    }

    /**
     * Add Element to the Knapsack
     * @param element 
     */
    public void addElementToKnapsack(KnapsackElement element) {
        this.freeMass = this.freeMass - element.getMassValue();
        this.worthValue = this.worthValue +  element.getWorthValue();
        this.containedElements.add(element);
    }

    /**
     * Delete Element from the Knapsack
     * @param element 
     */
    public void deleteElementFromKnapsack(KnapsackElement element) {
        this.freeMass += element.getMassValue();
        this.worthValue -= element.getWorthValue();
        this.containedElements.remove(element);
    }
    
    /**
     * Calculates the Sum Mass Value
     * @return 
     */
    public int calculateSumMassValue(){
        return this.maximumMass-this.freeMass;
    }

}
