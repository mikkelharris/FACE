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
    
    
    public CaseInfo()
    {
    }
    
    public CaseInfo(String caseNumber, int caseDateDay, String caseDateMonth, int caseDateYear, String caseLocation)
    {
	setCaseNum(caseNumber);
	setCaseDateDay(caseDateDay);
	setCaseDateMonth(caseDateMonth);
	setCaseDateMonth(caseDateMonth);
	setCaseDateYear(caseDateYear);
	setCaseLocation(caseLocation);
    }

    public int getCaseDateDay()
    {
	return caseDateDay;
    }

    public void setCaseDateDay(int caseDateDay)
    {
	this.caseDateDay = caseDateDay;
    }

    public String getCaseDateMonth()
    {
	return caseDateMonth;
    }

    public void setCaseDateMonth(String caseDateMonth)
    {
	this.caseDateMonth = caseDateMonth;
    }

    public int getCaseDateYear()
    {
	return caseDateYear;
    }

    public void setCaseDateYear(int caseDateYear)
    {
	this.caseDateYear = caseDateYear;
    }

    public String getCaseLocation()
    {
	return caseLocation;
    }

    public void setCaseLocation(String caseLocation)
    {
	this.caseLocation = caseLocation;
    }

    public String getCaseNum()
    {
	return caseNumber;
    }

    public void setCaseNum(String caseNum)
    {
	this.caseNumber = caseNum;
    }
    
    public String toFileString()
    {
	return getCaseNum() + " | " + getCaseDateDay() + " | " + getCaseDateMonth() + " | " + getCaseDateYear() + " | " + getCaseLocation() + "\n";
    }
    
}
