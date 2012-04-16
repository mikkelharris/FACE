package prog24178.project;

/**
 * Name: Mikkel Harris 
 * File: BoneInfo.java 
 * Other Files in this Project:
 *	FACECaseDetails.java 
 *	FACECaseSummary.java 
 *	FACEBoneDetails.java
 *	CaseInfo.java
 * Main class: FACEStart.java 
 */
public class BoneInfo
{

    private String caseNumber;
    private String bodyRegion;
    private String boneName;
    private String condition;
    private boolean foundStatus;

    /**
     * Default constructor of BoneInfo objects
     */
    public BoneInfo()
    {
    }

    /**
     * Constructor of boneInfo object that takes parameters
     * @param caseNumber the case number for the bone
     * @param bodyRegion the body region  for the bone 
     * @param boneName the name of the bone
     * @param condition the condition of the bone
     * @param foundStatus the found status of the bone
     */
    public BoneInfo(String caseNumber, String bodyRegion, String boneName, 
	    String condition, boolean foundStatus)
    {
	setCaseNumber(caseNumber);
	setBodyRegion(bodyRegion);
	setBoneName(boneName);
	setCondition(condition);
	setFoundStatus(foundStatus);
    }

    /**
     * Getter for bodyRegion of the bone 
     * @return the objects bodyRegion
     */
    public String getBodyRegion()
    {
	return bodyRegion;
    }

    /**
     * Setter for bodyRegion of the bone
     * @param bodyRegion the objects bodyRegion
     * @throws IllegalArgumentException  if bodyRegion is null
     */
    public void setBodyRegion(String bodyRegion) throws IllegalArgumentException
    {
	if (bodyRegion != null)
	{
	    this.bodyRegion = bodyRegion;
	}
	else 
	{
	    throw new IllegalArgumentException("Body region is empty.");
	}
    }

    /**
     * Getter for boneName of the bone 
     * @return the objects boneName
     */
    public String getBoneName()
    {
	return boneName;
    }

    /**
     * Setter for bodyRegion of the bone
     * @param boneName the objects boneName
     * @throws IllegalArgumentException  if boneName is null
     */
    public void setBoneName(String boneName) throws IllegalArgumentException
    {
	if (boneName != null)
	{
	    this.boneName = boneName;
	}
	else 
	{
	    throw new IllegalArgumentException("Bone Name is empty.");
	}
    }

    /**
     * Getter for the caseNumber of the bone
     * @return the objects caseNumber
     */
    public String getCaseNumber()
    {
	return caseNumber;
    }

    /**
     * Setter for the caseNumber of the bone
     * @param caseNumber the objects caseNumber
     * @throws IllegalArgumentException  if caseNumber is null
     */
    public void setCaseNumber(String caseNumber) throws IllegalArgumentException
    {
	if (caseNumber != null)
	{
	    this.caseNumber = caseNumber;
	}
	else 
	{
	    throw new IllegalArgumentException("Case Number is empty.");
	}
    }

    /**
     * Getter for the condition of the bone
     * @return the objects condition
     */
    public String getCondition()
    {
	return condition;
    }

    /**
     * Setter for the condition of the bone
     * @param condition the objects condition
     * @throws IllegalArgumentException  if condition is null
     */
    public void setCondition(String condition) throws IllegalArgumentException
    {
	if (condition != null)
	{
	    this.condition = condition;
	}
	else 
	{
	    throw new IllegalArgumentException("Bone Condition is empty.");
	}
    }

    /**
     * Getter for the foundStatus of the bone
     * @return the objects foundStatus
     */
    public boolean isFoundStatus()
    {
	return foundStatus;
    }

    /**
     * Setter for the foundStatus of the bone
     * @param foundStatus the objects foundStatus
     * @throws IllegalArgumentException  if foundStatus is not a boolean
     */
    public void setFoundStatus(boolean foundStatus) throws IllegalArgumentException
    {
	if (foundStatus == true || foundStatus == false)
	{
	    this.foundStatus = foundStatus;
	}
	else 
	{
	    throw new IllegalArgumentException("Found status is not a boolean.");
	}
    }
    
    /**
     * Custom delimited toString of the bone
     * @return delimited toString for the object
     */
    public String toFileString()
    {
	return getCaseNumber() + " | " + getBodyRegion() + " | " + getBoneName() 
		+ " | " + getCondition() + " | " + isFoundStatus() 
		+ " | endOfLine\n";
    }
}
