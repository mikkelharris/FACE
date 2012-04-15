package prog24178.project;

/**
 * Name: Mikkel Harris [programmer #2's name, if applicable] [programmer #3's
 * name, if applicable] File: Employee.java Other Files in this Project:
 * FullTimeEmployee.java PartTimeEmployee.java CommissionEmployee.java
 * EmpGui.java Main class: Main.java
 */
public class BoneInfo
{

    private String caseNumber;
    private String bodyRegion;
    private String boneName;
    private String condition;
    private boolean foundStatus;

    /**
     * 
     */
    public BoneInfo()
    {
    }

    /**
     * 
     * @param caseNumber
     * @param bodyRegion
     * @param boneName
     * @param condition
     * @param foundStatus
     */
    public BoneInfo(String caseNumber, String bodyRegion, String boneName, String condition, boolean foundStatus)
    {
	setCaseNumber(caseNumber);
	setBodyRegion(bodyRegion);
	setBoneName(boneName);
	setCondition(condition);
	setFoundStatus(foundStatus);
    }

    /**
     * 
     * @return
     */
    public String getBodyRegion()
    {
	return bodyRegion;
    }

    /**
     * 
     * @param bodyRegion
     */
    public void setBodyRegion(String bodyRegion)
    {
	this.bodyRegion = bodyRegion;
    }

    /**
     * 
     * @return
     */
    public String getBoneName()
    {
	return boneName;
    }

    /**
     * 
     * @param boneName
     */
    public void setBoneName(String boneName)
    {
	this.boneName = boneName;
    }

    /**
     * 
     * @return
     */
    public String getCaseNumber()
    {
	return caseNumber;
    }

    /**
     * 
     * @param caseNumber
     */
    public void setCaseNumber(String caseNumber)
    {
	this.caseNumber = caseNumber;
    }

    /**
     * 
     * @return
     */
    public String getCondition()
    {
	return condition;
    }

    /**
     * 
     * @param condition
     */
    public void setCondition(String condition)
    {
	this.condition = condition;
    }

    /**
     * 
     * @return
     */
    public boolean isFoundStatus()
    {
	return foundStatus;
    }

    /**
     * 
     * @param foundStatus
     */
    public void setFoundStatus(boolean foundStatus)
    {
	this.foundStatus = foundStatus;
    }
    
    /**
     * 
     * @return
     */
    public String toFileString()
    {
	return getCaseNumber() + " | " + getBodyRegion() + " | " + getBoneName() + " | " + getCondition() + " | " + isFoundStatus() + " | endOfLine\n";
    }
}
