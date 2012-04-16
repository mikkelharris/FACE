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
     * @throws IllegalArgumentException  if caseDateDay is less than 0 or greater than 31
     */
    public void setCaseDateDay(int caseDateDay) throws IllegalArgumentException
    {
	if (caseDateDay > 0 || caseDateDay <= 31)
	{
	    this.caseDateDay = caseDateDay;
	}
	else if (caseDateDay < 0)
	{
	    throw new IllegalArgumentException("The case day is less than 0");
	}
	else
	    throw new IllegalArgumentException("The case day is greater than 31");
	    
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
     * @throws IllegalArgumentException  if caseDateMonth is null
     */
    public void setCaseDateMonth(String caseDateMonth) throws IllegalArgumentException
    {
	if (caseDateMonth != null)
	{
	    this.caseDateMonth = caseDateMonth;
	}
	else 
	{
	    throw new IllegalArgumentException("The case Date Month is empty.");
	}
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
     * @throws IllegalArgumentException  is less than 1950
     */
    public void setCaseDateYear(int caseDateYear) throws IllegalArgumentException
    {
	if (caseDateYear >= 1950)
	{
	    this.caseDateYear = caseDateYear;
	}
	else
	    throw new IllegalArgumentException("The case year is less than 1950");
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
     * @throws IllegalArgumentException  if caseLocation is null
     */
    public void setCaseLocation(String caseLocation) throws IllegalArgumentException
    {
	if (caseLocation != null)
	{
	    this.caseLocation = caseLocation;	    
	}
	else
	{
	    throw new IllegalArgumentException("The case Location is empty");
	}
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
     * @throws IllegalArgumentException  if caseNum is null
     */
    public void setCaseNum(String caseNum) throws IllegalArgumentException
    {
	if (caseNum != null)
	{
	    this.caseNumber = caseNum;	    
	}
	else
	{
	    throw new IllegalArgumentException("Case Number is empty");
	}
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
