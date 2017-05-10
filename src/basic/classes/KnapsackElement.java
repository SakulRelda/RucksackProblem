/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basic.classes;

/**
 * Standard Knapsack Element which contains a Name, a mass value and a worth
 * value. This class can be added to a Knapsack
 *
 * @author adlerlu
 */
public class KnapsackElement implements Comparable<KnapsackElement> {

    private int id;
    private String elementName;
    private int massValue;
    private int worthValue;
    private double profitIndex;

    /**
     * Constructor which creates a new Knapsack Element with Name, Weight and
     * Worth
     *
     * @param elementName
     * @param massValue
     * @param worthValue
     */
    public KnapsackElement(String elementName, int massValue, int worthValue) {
        this.elementName = elementName;
        this.massValue = massValue;
        this.worthValue = worthValue;
        this.profitIndex = ((worthValue * 1.0) / massValue);
    }

    /**
     * Get the Name of the Element
     * @return 
     */
    public String getElementName() {
        return elementName;
    }

    /**
     * Set the Name of the Element
     * @param elementName 
     */
    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    /**
     * Get the Weight of the Element
     * @return 
     */
    public int getMassValue() {
        return massValue;
    }

    /**
     * Get the ID of the Element
     * @return 
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the Element
     * @param id 
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets the Weight for an Element
     * @param massValue 
     */
    public void setMassValue(int massValue) {
        this.massValue = massValue;
    }

    /**
     * Gets the Worth of an Element
     * @return 
     */
    public int getWorthValue() {
        return worthValue;
    }

    /**
     * Sets the Worth of an Element
     * @param worthValue 
     */
    public void setWorthValue(int worthValue) {
        this.worthValue = worthValue;
    }

    
    /**
     * Gets the Profit Index of an Element
     * @return 
     */
    public double getProfitIndex() {
        return profitIndex;
    }

    /**
     * Sets the Profit Index of an Element
     * @param profitIndex 
     */
    public void setProfitIndex(double profitIndex) {
        this.profitIndex = profitIndex;
    }

    /**
     * Compares two Knapsack Elements by using the Profit Index
     * Its needed for the Profit Index Algorithm
     * @param o
     * @return 
     */
    @Override
    public int compareTo(KnapsackElement o) {
        return (this.profitIndex > o.profitIndex) ? 1 : -1;
    }
}
