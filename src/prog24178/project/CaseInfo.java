/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prog24178.project;

/**
 * Name: Mikkel Harris [programmer #2's name, if applicable] [programmer #3's
 * name, if applicable] File: Employee.java Other Files in this Project:
 * FullTimeEmployee.java PartTimeEmployee.java CommissionEmployee.java
 * EmpGui.java Main class: Main.java
 */
public class CaseInfo
{
    private String caseNumber;
    private int caseDateDay;
    private String caseDateMonth;
    private int caseDateYear;
    private String caseLocation;
    
    
    /**
     * 
     */
    public CaseInfo()
    {
    }
    
    /**
     * 
     * @param caseNumber
     * @param caseDateDay
     * @param caseDateMonth
     * @param caseDateYear
     * @param caseLocation
     */
    public CaseInfo(String caseNumber, int caseDateDay, String caseDateMonth, int caseDateYear, String caseLocation)
    {
	setCaseNum(caseNumber);
	setCaseDateDay(caseDateDay);
	setCaseDateMonth(caseDateMonth);
	setCaseDateMonth(caseDateMonth);
	setCaseDateYear(caseDateYear);
	setCaseLocation(caseLocation);
    }

    /**
     * 
     * @return
     */
    public int getCaseDateDay()
    {
	return caseDateDay;
    }

    /**
     * 
     * @param caseDateDay
     */
    public void setCaseDateDay(int caseDateDay)
    {
	this.caseDateDay = caseDateDay;
    }

    /**
     * 
     * @return
     */
    public String getCaseDateMonth()
    {
	return caseDateMonth;
    }

    /**
     * 
     * @param caseDateMonth
     */
    public void setCaseDateMonth(String caseDateMonth)
    {
	this.caseDateMonth = caseDateMonth;
    }

    /**
     * 
     * @return
     */
    public int getCaseDateYear()
    {
	return caseDateYear;
    }

    /**
     * 
     * @param caseDateYear
     */
    public void setCaseDateYear(int caseDateYear)
    {
	this.caseDateYear = caseDateYear;
    }

    /**
     * 
     * @return
     */
    public String getCaseLocation()
    {
	return caseLocation;
    }

    /**
     * 
     * @param caseLocation
     */
    public void setCaseLocation(String caseLocation)
    {
	this.caseLocation = caseLocation;
    }

    /**
     * 
     * @return
     */
    public String getCaseNum()
    {
	return caseNumber;
    }

    /**
     * 
     * @param caseNum
     */
    public void setCaseNum(String caseNum)
    {
	this.caseNumber = caseNum;
    }
    
    /**
     * 
     * @return
     */
    public String toFileString()
    {
	return getCaseNum() + " | " + getCaseDateDay() + " | " + getCaseDateMonth() + " | " + getCaseDateYear() + " | " + getCaseLocation() + "\n";
    }
    
}
