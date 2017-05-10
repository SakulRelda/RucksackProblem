/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basic.algorithms;

import basic.classes.Knapsack;

/**
 * This Interface must be included by all Algorithm Solutions
 * @author Lukas Adler
 * @version v1.0.0
 */
public interface IAlgorithm {
    
    /**
     * Starts a Algorithm
     * @return Knapsack
     */
    public Knapsack startAlgorithm();
    
}
