package prog24178.project;

/**
 * Name: Mikkel Harris 
 * File: CaseInfo.java
 * Other Files in this Project:
 *	FACECaseDetails.java 
 *	FACECaseSummary.java 
 *	FACEBoneDetails.java
 *	BoneInfo.java
 * Main class: FACEStart.java 
 */
public class CaseInfo
{
    private String caseNumber;
    private int caseDateDay;
    private String caseDateMonth;
    private int caseDateYear;
    private String caseLocation;
    
    
    /**
     * Default constructor of caseInfo objects
     */
    public CaseInfo()
    {
    }
    
    /**
     * Constructor of caseInfo objects that takes parameters
     * @param caseNumber the caseNumber of the case
     * @param caseDateDay the day of the case
     * @param caseDateMonth the month of the case
     * @param caseDateYear the year of the case
     * @param caseLocation the location of the case
     */
    public CaseInfo(String caseNumber, int caseDateDay, String caseDateMonth, 
	    int caseDateYear, String caseLocation)
    {
	setCaseNum(caseNumber);
	setCaseDateDay(caseDateDay);
	setCaseDateMonth(caseDateMonth);
	setCaseDateMonth(caseDateMonth);
	setCaseDateYear(caseDateYear);
	setCaseLocation(caseLocation);
    }

    /**
     * Getter for the day of the case
     * @return the objects day
     */
    public int getCaseDateDay()
    {
	return caseDateDay;
    }

    /**
     * Setter for the day of the case
     * @param caseDateDay the objects day
     */
    public void setCaseDateDay(int caseDateDay)
    {
	this.caseDateDay = caseDateDay;
    }

    /**
     * Getter for the month of the case
     * @return the objects month
     */
    public String getCaseDateMonth()
    {
	return caseDateMonth;
    }

    /**
     * Setter for the month of the case
     * @param caseDateMonth the objects month
     */
    public void setCaseDateMonth(String caseDateMonth)
    {
	this.caseDateMonth = caseDateMonth;
    }

    /**
     * Getter for the year of the case
     * @return the objects year
     */
    public int getCaseDateYear()
    {
	return caseDateYear;
    }

    /**
     * Setter for the year of the case
     * @param caseDateYear the objects year
     */
    public void setCaseDateYear(int caseDateYear)
    {
	this.caseDateYear = caseDateYear;
    }

    /**
     * Getter for the location of the case
     * @return the objects location
     */
    public String getCaseLocation()
    {
	return caseLocation;
    }

    /**
     * Setter for the location of the case
     * @param caseLocation the objects location
     */
    public void setCaseLocation(String caseLocation)
    {
	this.caseLocation = caseLocation;
    }

    /**
     * Getter for the caseNumber of the case
     * @return the objects caseNumber
     */
    public String getCaseNum()
    {
	return caseNumber;
    }

    /**
     * Setter for the caseNumber of the case
     * @param caseNum the objects caseNumber
     */
    public void setCaseNum(String caseNum)
    {
	this.caseNumber = caseNum;
    }
    
    /**
     * Custom delimited toString of the case
     * @return delimited toString for the object
     */
    public String toFileString()
    {
	return getCaseNum() + " | " + getCaseDateDay() + " | " + getCaseDateMonth() 
		+ " | " + getCaseDateYear() + " | " + getCaseLocation() + "\n";
    }
    
}
