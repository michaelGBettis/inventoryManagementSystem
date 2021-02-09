/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Myque
 */
public class InHouse extends Part{
    
    private int machineId;
    
    public InHouse(int ID, String Name, double Price, int Stock, int Min, int Max, boolean isInHouse, int machineId) {
        super(ID, Name, Price, Stock, Min, Max, isInHouse);
        this.machineId = machineId;
    }

    public int getMachineid() {
        return machineId;
    }

    public void setMachineid(int machineid) {
        this.machineId = machineId;
    }    
    
}
