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

    public BoneInfo()
    {
    }

    public BoneInfo(String caseNumber, String bodyRegion, String boneName, String condition, boolean foundStatus)
    {
	setCaseNumber(caseNumber);
	setBodyRegion(bodyRegion);
	setBoneName(boneName);
	setCondition(condition);
	setFoundStatus(foundStatus);
    }

    public String getBodyRegion()
    {
	return bodyRegion;
    }

    public void setBodyRegion(String bodyRegion)
    {
	this.bodyRegion = bodyRegion;
    }

    public String getBoneName()
    {
	return boneName;
    }

    public void setBoneName(String boneName)
    {
	this.boneName = boneName;
    }

    public String getCaseNumber()
    {
	return caseNumber;
    }

    public void setCaseNumber(String caseNumber)
    {
	this.caseNumber = caseNumber;
    }

    public String getCondition()
    {
	return condition;
    }

    public void setCondition(String condition)
    {
	this.condition = condition;
    }

    public boolean isFoundStatus()
    {
	return foundStatus;
    }

    public void setFoundStatus(boolean foundStatus)
    {
	this.foundStatus = foundStatus;
    }
    
    public String toFileString()
    {
	return getCaseNumber() + " | " + getBodyRegion() + " | " + getBoneName() + " | " + getCondition() + " | " + isFoundStatus() + " | endOfLine\n";
    }
}
