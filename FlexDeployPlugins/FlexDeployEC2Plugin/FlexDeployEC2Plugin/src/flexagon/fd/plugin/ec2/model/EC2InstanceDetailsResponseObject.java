package flexagon.fd.plugin.ec2.model;

import flexagon.ff.common.core.pojos.BasePOJO;

public class EC2InstanceDetailsResponseObject
  extends BasePOJO
{

  private String instanceId;
  private String instanceStatus;
  private String instanceState;
  private String systemStatus;

  public EC2InstanceDetailsResponseObject()
  {
    super();
  }

  public void setInstanceId(String pInstanceId)
  {
    this.instanceId = pInstanceId;
  }

  public String getInstanceId()
  {
    return instanceId;
  }

  public void setInstanceStatus(String pInstanceStatus)
  {
    this.instanceStatus = pInstanceStatus;
  }

  public String getInstanceStatus()
  {
    return instanceStatus;
  }

  public void setInstanceState(String pInstanceState)
  {
    this.instanceState = pInstanceState;
  }

  public String getInstanceState()
  {
    return instanceState;
  }

  public void setSystemStatus(String pSystemStatus)
  {
    this.systemStatus = pSystemStatus;
  }

  public String getSystemStatus()
  {
    return systemStatus;
  }

  @Override
  public String toString()
  {
    StringBuilder sb = new StringBuilder("");
    sb.append(" Instance Id: " + getInstanceId() + "," + " Instance State: " + getInstanceState() + "," + " Instance Status: " + getInstanceStatus() + "," + " System Status: " + getSystemStatus());
    sb.append(System.lineSeparator());
    return sb.toString();
  }
}
