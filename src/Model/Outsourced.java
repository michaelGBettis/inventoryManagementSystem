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
public class Outsourced extends Part {
    
    private String companyName;

    public Outsourced(int ID, String Name, double Price, int Stock, int Min, int Max, boolean isInHouse, String companyName) {
        super(ID, Name, Price, Stock, Min, Max, isInHouse);
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    
    
}
