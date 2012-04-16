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
     */
    public void setBodyRegion(String bodyRegion)
    {
	this.bodyRegion = bodyRegion;
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
     */
    public void setBoneName(String boneName)
    {
	this.boneName = boneName;
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
     */
    public void setCaseNumber(String caseNumber)
    {
	this.caseNumber = caseNumber;
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
     */
    public void setCondition(String condition)
    {
	this.condition = condition;
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
     */
    public void setFoundStatus(boolean foundStatus)
    {
	this.foundStatus = foundStatus;
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
