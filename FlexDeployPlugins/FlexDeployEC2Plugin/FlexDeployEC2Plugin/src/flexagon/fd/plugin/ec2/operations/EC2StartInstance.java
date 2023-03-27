package flexagon.fd.plugin.ec2.operations;

import flexagon.fd.core.plugin.PluginResult;
import flexagon.fd.plugin.cloud.model.AWSAccount;
import flexagon.fd.plugin.cloud.model.CloudCliExecutionReponseObject;
import flexagon.fd.plugin.ec2.EC2Properties;
import flexagon.fd.plugin.ec2.commands.EC2Commands;
import flexagon.fd.plugin.ec2.utils.EC2PluginExecutionContext;

import flexagon.ff.common.core.exceptions.FlexCheckedException;
import flexagon.ff.common.core.logging.FlexLogger;
import flexagon.ff.common.core.utils.FlexCommonUtils;

import java.util.List;


public class EC2StartInstance
  extends EC2Commands
{
  private static final String CLZ_NAM = EC2StartInstance.class.getName();
  private static final FlexLogger LOG = FlexLogger.getLogger(CLZ_NAM);

  public EC2StartInstance()
  {
    super();
  }

  @Override
  public PluginResult execute()
    throws FlexCheckedException
  {
    String methodName = "startInstance";
    LOG.logFinerEntering(methodName);

    CloudCliExecutionReponseObject respObj = null;
    try
    {
      EC2PluginExecutionContext ec2PluginExecutionContext = getEC2PluginExecutionContext();
      // Retreive AWS Account Code
      String awsAccountCode = ec2PluginExecutionContext.getAWSAccountCode();
      // Create AWS Account object based on Account Code
      AWSAccount awsAccount = ec2PluginExecutionContext.getAWSAccount(awsAccountCode);
      // Retreive AWS CLI Path
      String awsCliPath = ec2PluginExecutionContext.getAwsCliAbsolutePath();
      // Connect to AWS
      setEnvironmentVariablesForAWSLogin(awsAccount, awsCliPath);

      String instanceIds = ec2PluginExecutionContext.getInstanceIDs();
      if (FlexCommonUtils.isNotEmpty(instanceIds))
      {
        instanceIds = instanceIds.trim();
      }
      // Execute EC2 start Instance CLI command
      respObj = executeEC2InstanceCommand(instanceIds, awsAccount);
      if (respObj.getRespCode() != 0)
      {
        throw new FlexCheckedException(EC2Properties.FDAWS_EC2_00010_START_INSTANCE_FAILURE, "Start EC2 Instance failed with Error Message:" + respObj.getRespError());
      }
      // Set o/p messages
      EC2PluginExecutionContext ec2PluginContext = getEC2PluginExecutionContext();
      ec2PluginContext.setEC2InstanceStatus(respObj.getRespCode());
      ec2PluginContext.setEC2InstanceResp(respObj.getRespMsg());
      ec2PluginContext.setEC2InstanceError(respObj.getRespError());

      int noOfIterations = ec2PluginContext.getNumberOfIterations();
      boolean status = false;
      status = isEC2InstanceRunning(EC2Properties.FDAWS_START_CONSTANT, instanceIds, awsAccount);
      if (status == false)
      {
        throw new FlexCheckedException(EC2Properties.FDAWS_EC2_00003_TIMEOUT,
                                       "After " + noOfIterations + " attempts, some/all instances are not changed to state [" + EC2Properties.STATE_RUNNING + "]" + " and status [" +
                                       EC2Properties.STATUS_OK + "]. Please verify details from Reports result/logs.");
      }
      else
      {
        LOG.logInfo(methodName, "All the instances are running successfully");
      }
    }
    finally
    {
      // Logout from AWS
      resetEnvironmentVariablesForAWSLogout();
    }

    PluginResult result = PluginResult.createPluginResult(getEC2PluginExecutionContext().getWorkflowExecutionContext());

    LOG.logFinerExiting(methodName, result);
    return result;
  }

  @Override
  public void validate()
    throws FlexCheckedException
  {
    String methodName = "validate";
    LOG.logFinerEntering(methodName);

    validateInstanceIdsRequired();
    validateAWSAccountCode();

    LOG.logFinerExiting(methodName);
  }

  @Override
  protected void addEC2InstanceOperationCommand(List<String> pCommands)
  {
    String methodName = "addEC2InstanceOperationCommand";
    LOG.logFinerEntering(methodName, pCommands);

    pCommands.add(EC2Properties.FDAWS_START_INSTANCES_CONSTANT);

    LOG.logFinerExiting(methodName);
  }
}
