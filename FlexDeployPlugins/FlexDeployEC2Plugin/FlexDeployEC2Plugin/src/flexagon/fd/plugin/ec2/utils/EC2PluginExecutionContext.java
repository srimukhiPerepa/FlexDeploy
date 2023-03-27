package flexagon.fd.plugin.ec2.utils;

import flexagon.fd.core.PropertyValue;
import flexagon.fd.core.workflow.WorkflowExecutionContext;
import flexagon.fd.plugin.cloud.utils.CloudCommonExecutionContext;
import flexagon.fd.plugin.ec2.EC2Properties;

import flexagon.ff.common.core.logging.FlexLogger;
import flexagon.ff.common.core.utils.FlexCommonUtils;

public class EC2PluginExecutionContext
  extends CloudCommonExecutionContext
{
  private static final String CLZ_NAM = EC2PluginExecutionContext.class.getName();
  private static final FlexLogger LOG = FlexLogger.getLogger(CLZ_NAM);

  public EC2PluginExecutionContext(WorkflowExecutionContext pWorkflowExecutionContext)
  {
    super(pWorkflowExecutionContext);
  }

  public EC2PluginExecutionContext()
  {
    super();
  }

  //Properties
  public String getAccessKey()
  {
    return getStringCurrentInstancePropertyValue(EC2Properties.FDAWS_ACCESS_KEY);
  }

  public String getSecretKey()
  {
    return getStringCurrentInstancePropertyValue(EC2Properties.FDAWS_SECRET_KEY);
  }

  public String getAWSRegionProperty()
  {
    return getStringCurrentInstancePropertyValue(EC2Properties.FDAWS_REGION);
  }

  /** Get Ec2 Endpoint details
   * @return
   */
  public String getEC2Endpoint()
  {
    String endpoint = getStringCurrentInstancePropertyValue(EC2Properties.FDAWS_ENDPOINT);
    if (FlexCommonUtils.isNotEmpty(endpoint))
    {
      if (!endpoint.contains("."))
      {
        endpoint = "ec2." + endpoint + ".amazonaws.com";
      }
    }
    return endpoint;
  }

  public String getCFEndpoint()
  {
    String endpoint = getEC2Endpoint().replace("ec2.", "cloudformation.");
    return endpoint;
  }

  //Inputs
  public String getOnFailure()
  {
    return getStringInput(EC2Properties.FDAWS_CF_ON_FAILURE);
  }

  public String getTemplateParameters()
  {
    return getStringInput(EC2Properties.FDAWS_CF_TEMPLATE_PARAMETERS);
  }

  public String getStackName()
  {
    return getStringInput(EC2Properties.FDAWS_CF_STACK_NAME);
  }

  public String getTemplateFileName()
  {
    return getStringInput(EC2Properties.FDAWS_CF_TEMPLATE_FILENAME);
  }

  public String getTemplateURL()
  {
    return getStringInput(EC2Properties.FDAWS_CF_TEMPLATE_URL);
  }

  public String getInstanceIDs()
  {
    return getStringInput(EC2Properties.FDAWS_EC2_INP_INSTANCE_IDS);
  }

  public String getInstanceType()
  {
    return getStringInput(EC2Properties.FDAWS_EC2_INSTANCE_TYPE);
  }

  public String getImageID()
  {
    return getStringInput(EC2Properties.FDAWS_EC2_IMAGE_ID);
  }

  public int getMinCount()
  {
    return getIntegerInputOrDefault(EC2Properties.FDAWS_EC2_MIN_INSTANCES, 1);
  }

  public int getMaxCount()
  {
    return getIntegerInputOrDefault(EC2Properties.FDAWS_EC2_MAX_INSTANCES, 1);
  }

  public String getSecurityGroupName()
  {
    return getStringInput(EC2Properties.FDAWS_EC2_SEC_GROUP_NAME);
  }

  public String getSecurityGroupDesc()
  {
    return getStringInputOrDefault(EC2Properties.FDAWS_EC2_SEC_GROUP_DESCRIPTION, "Security group created by FlexDeploy.");
  }

  public String getKeyName()
  {
    return getStringInput(EC2Properties.FDAWS_EC2_KEY_NAME);
  }

  public Boolean getWithForce()
  {
    return getBooleanInputOrDefault(EC2Properties.FDAWS_EC2_WITH_FORCE, false);
  }

  public String getInboundRules()
  {
    return getStringInput(EC2Properties.FDAWS_EC2_INBOUND_SECURITY_RULES);
  }

  public String getOutboundRules()
  {
    return getStringInput(EC2Properties.FDAWS_EC2_OUTBOUND_SECURITY_RULES);
  }

  public String getEIPAllocationID()
  {
    return getStringInput(EC2Properties.FDAWS_EC2_ELASTIC_IP_ID);
  }

  public Boolean getReleaseEIP()
  {
    return getBooleanInput(EC2Properties.FDAWS_EC2_RELEASE_EIP);
  }

  /**
   * To retreive Aws cli path from Environment mampping
   * Ideally it should be present in m/c classpath
   * If not this property can be used
   * @return
   */
  public String getAwsCliAbsolutePath()
  {
    return getStringCurrentInstancePropertyValue(EC2Properties.FDAWS_CLI_PATH);
  }

  /**
   * Retrieve the AWS Account code from Environment property
   * @return
   */
  public String getAWSAccountCode()
  {
    String methodName = "getAWSAccountCode";
    LOG.logFinerEntering(methodName);

    String awsCode = getStringCurrentInstancePropertyValue(EC2Properties.FDAWS_ACCOUNT_CODE);
    if (FlexCommonUtils.isNotEmpty(awsCode))
    {
      LOG.logFine(methodName, "Using workflow input azure {0}", awsCode);
      return awsCode;
    }

    LOG.logFinerExiting(methodName);
    return awsCode;
  }

  public void setEC2InstanceStatus(int status)
  {
    getWorkflowExecutionContext().setOutput(EC2Properties.FDAWS_OUT_EC2_INSTANCE_STATUS, status, PropertyValue.PropertyTypeEnum.Integer);
  }

  public void setEC2InstanceResp(String respMsg)
  {
    getWorkflowExecutionContext().setOutput(EC2Properties.FDAWS_OUT_EC2_INSTANCE_RESP, respMsg, PropertyValue.PropertyTypeEnum.String);
  }

  public void setEC2InstanceError(String respErr)
  {
    getWorkflowExecutionContext().setOutput(EC2Properties.FDAWS_OUT_EC2_INSTANCE_ERR, respErr, PropertyValue.PropertyTypeEnum.String);
  }

  public int getEC2IntervalDuration()
  {
    return getIntegerCurrentInstancePropertyValue(EC2Properties.FDAWS_EC2_STATUS_INTERVAL_DURATION);
  }

  public int getNumberOfIterations()
  {
    return getIntegerCurrentInstancePropertyValue(EC2Properties.FDAWS_EC2_STATUS_CHECK_COUNT);
  }
}
