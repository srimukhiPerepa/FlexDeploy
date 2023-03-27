package flexagon.fd.plugin.ec2.commands;

import flexagon.fd.plugin.cloud.FlexDeployPluginCoreCloudProperties;
import flexagon.fd.plugin.cloud.model.AWSAccount;
import flexagon.fd.plugin.cloud.model.CloudCliExecutionReponseObject;
import flexagon.fd.plugin.core.utils.FlexFileName;
import flexagon.fd.plugin.ec2.EC2Properties;
import flexagon.fd.plugin.ec2.model.EC2InstanceDetailsResponseObject;
import flexagon.fd.plugin.ec2.utils.EC2PluginExecutionContext;

import flexagon.ff.common.core.exceptions.FlexCheckedException;
import flexagon.ff.common.core.logging.FlexLogger;
import flexagon.ff.common.core.utils.FlexCommonUtils;
import flexagon.ff.common.core.utils.FlexFileUtils;

import java.io.File;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public abstract class EC2Commands
  extends AbstractAwsCommands
{
  private static final String CLZ_NAM = EC2Commands.class.getName();
  private static final FlexLogger LOG = FlexLogger.getLogger(CLZ_NAM);

  public EC2Commands()
  {
    super();
  }

  protected abstract void addEC2InstanceOperationCommand(List<String> pCommands);

  protected void addAdditionalCommands(List<String> pCommands)
  {

  }

  /**Form the CLI command to Start/Stop EC2 Instance and execute them
   * @param pEC2InstanceIds
   * @param pAwsAccount
   * @return
   * @throws FlexCheckedException
   */
  protected CloudCliExecutionReponseObject executeEC2InstanceCommand(String pEC2InstanceIds, AWSAccount pAwsAccount)
    throws FlexCheckedException
  {
    String methodName = "executeEC2InstanceCommand";
    LOG.logFinerEntering(methodName, pEC2InstanceIds, pAwsAccount);

    List<String> commands = new ArrayList<String>();
    commands.add(EC2Properties.FDAWS_EC2_CONSTANT);
    addEC2InstanceOperationCommand(commands);
    commands.add(EC2Properties.FDAWS_INSTANCE_IDS_CONSTANT);
    commands = retrieveAWSInstanceIds(commands, pEC2InstanceIds);

    //Override command's default URL with the given URL.
    String endpointURL = getEC2PluginExecutionContext().getEC2Endpoint();
    if (FlexCommonUtils.isNotEmpty(endpointURL))
    {
      commands.add(EC2Properties.FDAWS_ENDPOINT_CONSTANT);
      commands.add(getEC2PluginExecutionContext().getEC2Endpoint());
    }
    //Overwrite Region configured in AWS account
    if (FlexCommonUtils.isNotEmpty(getEC2PluginExecutionContext().getAWSRegionProperty()))
    {
      commands.add(EC2Properties.FDAWS_REGION_CONSTANT);
      commands.add(getEC2PluginExecutionContext().getAWSRegionProperty());
    }
    else if (FlexCommonUtils.isNotEmpty(pAwsAccount.getRegion()))
    {
      commands.add(EC2Properties.FDAWS_REGION_CONSTANT);
      commands.add(pAwsAccount.getRegion());
    }
    //additional command arguments
    addAdditionalCommands(commands);

    CloudCliExecutionReponseObject respOBj = run(commands, true, FlexDeployPluginCoreCloudProperties.FDCLOUD_PROVIDER_AWS);

    LOG.logFinerExiting(methodName, respOBj);
    return respOBj;
  }

  /** to parse the AWS Instance Id's
   * @param pCommands
   * @param pEC2InstanceIds
   * @return
   */
  private List<String> retrieveAWSInstanceIds(List<String> pCommands, String pEC2InstanceIds)
  {
    String methodName = "retrieveAWSInstanceIds";
    LOG.logFinerEntering(methodName, pCommands, pEC2InstanceIds);

    // Can handle both comma and space seperated Instaince Ids
    List<String> updatedCommands = pCommands;
    List<String> instanceIdsList = new ArrayList<String>();
    // Checking if separated by ','
    if (pEC2InstanceIds.contains(","))
    {
      instanceIdsList = FlexCommonUtils.splitByComma(pEC2InstanceIds);
      for (String instanceId: instanceIdsList)
      {
        updatedCommands.add(instanceId);
      }
    }
    // Checking if separated by ' '(space)
    else if (pEC2InstanceIds.contains(" "))
    {
      instanceIdsList = FlexCommonUtils.splitBySpace(pEC2InstanceIds);
      for (String instanceId: instanceIdsList)
      {
        updatedCommands.add(instanceId);
      }
    }
    else
    {
      updatedCommands.add(pEC2InstanceIds);
    }

    LOG.logFinerExiting(methodName, updatedCommands);
    return updatedCommands;
  }

  public void validateInstanceIdsRequired()
    throws FlexCheckedException
  {
    if (FlexCommonUtils.isEmpty(getEC2PluginExecutionContext().getInstanceIDs()))
    {
      throw new FlexCheckedException(EC2Properties.FDAWS_EC2_00012_INSTANCE_ID_IS_REQUIRED, "Instance Id is required, but not provided as input");
    }
  }

  public void validateAWSAccountCode()
    throws FlexCheckedException
  {
    if (FlexCommonUtils.isEmpty(getEC2PluginExecutionContext().getAWSAccountCode()))
    {
      throw new FlexCheckedException(EC2Properties.FDAWS_EC2_00014_AWS_ACCOUNT_CODE_IS_REQUIRED, "AWS Cloud Account is not set. Ensure the value is set in topology.");
    }
  }

  /**
   * Form the CLI command to Describe Statuses of EC2 Instances and execute them
   * @param pOperation
   * @param pEC2InstanceIds
   * @param pAwsAccount
   * @return
   * @throws FlexCheckedException
   * @throws ParseException
   */
  protected CloudCliExecutionReponseObject executeDescribeInstanceStatusCommand(String pOperation, String pEC2InstanceIds, AWSAccount pAwsAccount)
    throws FlexCheckedException, ParseException
  {
    String methodName = "executeDescribeInstanceStatusCommand";
    LOG.logFinerEntering(methodName, pOperation, pEC2InstanceIds, pAwsAccount);

    // Execute EC2 describe Instance Status CLI command
    List<String> commands = new ArrayList<String>();
    commands.add(EC2Properties.FDAWS_EC2_CONSTANT);
    commands.add(EC2Properties.FDAWS_DESCRIBE_INSTANCES_STATUS_CONSTANT);
    commands.add(EC2Properties.FDAWS_INSTANCE_IDS_CONSTANT);
    commands = retrieveAWSInstanceIds(commands, pEC2InstanceIds);
    commands.add(EC2Properties.FDAWS_INLCUDE_ALL_INSTANCES_CONSTANT);

    if (FlexCommonUtils.isNotEmpty(getEC2PluginExecutionContext().getAWSRegionProperty()))
    {
      commands.add(EC2Properties.FDAWS_REGION_CONSTANT);
      commands.add(getEC2PluginExecutionContext().getAWSRegionProperty());
    }
    else if (FlexCommonUtils.isNotEmpty(pAwsAccount.getRegion()))
    {
      commands.add(EC2Properties.FDAWS_REGION_CONSTANT);
      commands.add(pAwsAccount.getRegion());
    }
    //run describe-instance-status
    CloudCliExecutionReponseObject respOBj = run(commands, true, FlexDeployPluginCoreCloudProperties.FDCLOUD_PROVIDER_AWS);

    LOG.logFinerExiting(methodName, respOBj);
    return respOBj;
  }

  /**
   * Parse the EC2 CLI Response
   * @param pResponseObject
   * @param pOperation
   * @return
   * @throws ParseException
   * @throws FlexCheckedException
   */
  protected List<EC2InstanceDetailsResponseObject> parseEC2CliResponse(CloudCliExecutionReponseObject pResponseObject, String pOperation)
    throws ParseException, FlexCheckedException
  {
    String methodName = "parseEC2CliResponse";
    LOG.logFinerEntering(methodName, pResponseObject, pOperation);

    String instStatus = null;
    String instState = null;
    String sysStatus = null;
    List<EC2InstanceDetailsResponseObject> statusList = new ArrayList<EC2InstanceDetailsResponseObject>();

    String responseMessage = pResponseObject.getRespMsg();
    JSONParser parser = new JSONParser();
    JSONObject jsonObj = (JSONObject) parser.parse(responseMessage);
    JSONArray instanceDetails = (JSONArray) jsonObj.get(EC2Properties.JSON_EC2_INSTANCE_STATUSES_CONSTANT);
    EC2InstanceDetailsResponseObject instDetailsRespObj = null;
    for (int i = 0; i < instanceDetails.size(); i++)
    {
      instDetailsRespObj = new EC2InstanceDetailsResponseObject();
      JSONObject stateObject = (JSONObject) instanceDetails.get(i);
      JSONObject instanceState = (JSONObject) stateObject.get(EC2Properties.JSON_EC2_INSTANCE_STATE_CONSTANT);
      instState = (String) instanceState.get(EC2Properties.JSON_EC2_NAME_CONSTANT);

      JSONObject instanceStatus = (JSONObject) stateObject.get(EC2Properties.JSON_EC2_INSTANCE_STATUS_CONSTANT);
      instStatus = (String) instanceStatus.get(EC2Properties.JSON_EC2_STATUS_CONSTANT);

      JSONObject systemStatus = (JSONObject) stateObject.get(EC2Properties.JSON_EC2_SYSTEM_STATUS_CONSTANT);
      sysStatus = (String) systemStatus.get(EC2Properties.JSON_EC2_STATUS_CONSTANT);

      instDetailsRespObj.setInstanceState(instState);
      instDetailsRespObj.setInstanceStatus(instStatus);
      instDetailsRespObj.setSystemStatus(sysStatus);
      instDetailsRespObj.setInstanceId((String) stateObject.get(EC2Properties.JSON_EC2_INSTANCE_ID_CONSTANT));

      LOG.logInfo(methodName, instDetailsRespObj.toString());
      statusList.add(instDetailsRespObj);
    }

    LOG.logFinerExiting(methodName, statusList);
    return statusList;
  }


  /**
   * Check each EC2 Instance Status/State.
   * @param pOperation
   * @param pInstRespObjects
   * @return
   */
  protected boolean checkInstanceStatus(String pOperation, List<EC2InstanceDetailsResponseObject> pInstRespObjects)
  {
    String methodName = "checkInstanceStatus";
    LOG.logFinerEntering(methodName, pOperation, pInstRespObjects);

    boolean status = true;

    if (EC2Properties.FDAWS_START_CONSTANT.equalsIgnoreCase(pOperation))
    {
      for (EC2InstanceDetailsResponseObject instDetailsObj: pInstRespObjects)
      {
        if (!(EC2Properties.STATE_RUNNING.equals(instDetailsObj.getInstanceState()) && EC2Properties.STATUS_OK.equals(instDetailsObj.getInstanceStatus()) &&
              EC2Properties.STATUS_OK.equals(instDetailsObj.getSystemStatus())))
        {
          status = false;
          break;
        }
      }
    }
    else if (EC2Properties.FDAWS_STOP_CONSTANT.equalsIgnoreCase(pOperation))
    {
      for (EC2InstanceDetailsResponseObject instDetailsObj: pInstRespObjects)
      {
        if (!(EC2Properties.STATE_STOPPED.equals(instDetailsObj.getInstanceState()) && EC2Properties.STATUS_NOT_APPLICABLE.equals(instDetailsObj.getInstanceStatus()) &&
              EC2Properties.STATUS_NOT_APPLICABLE.equals(instDetailsObj.getSystemStatus())))
        {
          status = false;
          break;
        }
      }
    }

    LOG.logFinerExiting(methodName, status);
    return status;
  }

  /**
   * Execute the CLI Command to Describe the Instance statuses for given number of Iterations.
   * @param pOperation
   * @param pInstanceIds
   * @param pAwsAccount
   * @return
   * @throws FlexCheckedException
   */
  protected boolean isEC2InstanceRunning(String pOperation, String pInstanceIds, AWSAccount pAwsAccount)
    throws FlexCheckedException
  {
    String methodName = "isEC2InstanceRunning";
    LOG.logFinerEntering(methodName, pOperation, pInstanceIds, pAwsAccount);

    EC2PluginExecutionContext ec2PluginContext = getEC2PluginExecutionContext();
    int noOfIterations = ec2PluginContext.getNumberOfIterations();
    int seconds = ec2PluginContext.getEC2IntervalDuration();
    boolean isInstanceRunning = false;
    CloudCliExecutionReponseObject descInstRespObj = null;
    int iteration = 0;
    List<EC2InstanceDetailsResponseObject> listOfInstDetails = null;
    //executes the describe-instance-status command for the given number of iterations
    //and waits for interval duration of seconds for each iteration.
    //Once the status Running/Ok or Stopped/not-applicable the loop breaks.
    while (!isInstanceRunning && iteration < noOfIterations)
    {
      try
      {
        //run the describe-instance-status command
        descInstRespObj = executeDescribeInstanceStatusCommand(pOperation, pInstanceIds, pAwsAccount);
        //parsing the response to JSON
        listOfInstDetails = parseEC2CliResponse(descInstRespObj, pOperation);
        //checking the status of the instances
        isInstanceRunning = checkInstanceStatus(pOperation, listOfInstDetails);
        //wait for given number of seconds
        Thread.sleep(seconds * 1000L);
        iteration++;
      }
      catch (ParseException e)
      {
        LOG.logInfo(methodName, "Error parsing JSON ", e);
        throw new FlexCheckedException(EC2Properties.FDAWS_EC2_00015_AWS_EC2_INST_STATUS_PARSING_ERROR, "Failure occurred parsing JSON response", e);
      }
      catch (InterruptedException ex)
      {
        LOG.logInfo(methodName, "Thread interrupted while waiting for EC2 Instance Status check ", ex);
        throw new FlexCheckedException(EC2Properties.FDAWS_EC2_00016_INST_STATUS_THREAD_SLEEP_ERROR, "Exception while waiting for EC2 status check.", ex);
      }
    }

    //generating the report to capture the instance statuses.
    generateReport(listOfInstDetails);

    LOG.logFinerExiting(methodName, isInstanceRunning);
    return isInstanceRunning;
  }


  /**
   * Generates the report with Instance status details
   * @param pEC2InstanceObjects
   * @throws FlexCheckedException
   */
  private void generateReport(List<EC2InstanceDetailsResponseObject> pEC2InstanceObjects)
    throws FlexCheckedException
  {
    String methodName = "generateReport";
    LOG.logFinerEntering(methodName, pEC2InstanceObjects);

    String instResponse = "";
    File reportFolder = new FlexFileName(getWorkflowExecutionContext().getReportsDirectory()).asFile();
    if (!reportFolder.exists())
    {
      reportFolder.mkdirs();
    }

    String reportFileName = EC2Properties.FDAWS_DESCRIBE_EC2_INSTANCE_STATUS_JSON_RESPONSE + "_" + getEC2PluginExecutionContext().getWorkflowExecutionId() + ".txt";
    for (EC2InstanceDetailsResponseObject instObject: pEC2InstanceObjects)
    {
      instResponse = instResponse.concat(instObject.toString());
    }
    //write the instance response to the file.
    FlexFileUtils.write(new File(reportFolder, reportFileName), Arrays.asList(instResponse));

    LOG.logFinerExiting(methodName);
  }
}
