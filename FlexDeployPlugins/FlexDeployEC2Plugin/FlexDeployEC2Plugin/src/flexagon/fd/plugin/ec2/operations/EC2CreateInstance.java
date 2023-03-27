package flexagon.fd.plugin.ec2.operations;

import flexagon.fd.core.plugin.PluginProvider;
import flexagon.fd.core.plugin.PluginResult;
import flexagon.fd.core.workflow.WorkflowExecutionContext;

import flexagon.ff.common.core.exceptions.FlexCheckedException;
import flexagon.ff.common.core.logging.FlexLogger;


//import com.amazonaws.services.ec2.model.AllocateAddressResult;
//import com.amazonaws.services.ec2.model.AssociateAddressRequest;
//import com.amazonaws.services.ec2.model.AssociateAddressResult;
//import com.amazonaws.services.ec2.model.DescribeAddressesRequest;
//import com.amazonaws.services.ec2.model.DescribeAddressesResult;
//import com.amazonaws.services.ec2.model.RunInstancesRequest;
//import com.amazonaws.services.ec2.model.RunInstancesResult;
//import com.amazonaws.util.json.JSONObject;

//import flexagon.fd.core.plugin.PluginResult;
//import flexagon.fd.plugin.ec2.EC2Properties;

//import flexagon.ff.common.core.exceptions.FlexCheckedException;
//import flexagon.ff.common.core.logging.FlexLogger;

//import java.util.ArrayList;
//import java.util.List;

public class EC2CreateInstance
  implements PluginProvider
{
  private static final String CLZ_NAM = EC2CreateInstance.class.getName();
  private static final FlexLogger LOG = FlexLogger.getLogger(CLZ_NAM);

  public EC2CreateInstance()
  {
    super();
  }
  //
  //  @Override
  //  public PluginResult execute()
  //    throws FlexCheckedException
  //  {
  //    String methodName = "createInstance";
  //    LOG.logInfoEntering(methodName);
  //    initializeClient();
  //
  //    String elasticIPAllocationID = "";
  //
  //    String[] secGroups = mEC2PluginExecutionContext.getSecurityGroupName().split(",");
  //
  //    RunInstancesRequest runInstancesRequest = new RunInstancesRequest();
  //    runInstancesRequest.withImageId(mEC2PluginExecutionContext.getImageID()).withInstanceType(mEC2PluginExecutionContext.getInstanceType()).withMaxCount(mEC2PluginExecutionContext.getMaxCount()).withMinCount(mEC2PluginExecutionContext.getMinCount()).withKeyName(mEC2PluginExecutionContext.getKeyName()).withSecurityGroupIds(secGroups);
  //
  //    LOG.logInfo(methodName, "Attempting creation of {0} instance(s) of type {1} using security group(s) {2} and key {3}", mEC2PluginExecutionContext.getMaxCount(),
  //                mEC2PluginExecutionContext.getInstanceType(), Arrays.asList(secGroups).toString(), mEC2PluginExecutionContext.getKeyName());
  //
  //    RunInstancesResult runInstancesResult = mClient.runInstances(runInstancesRequest);
  //
  //    JSONObject jsonResponse = new JSONObject(runInstancesResult);
  //    String instanceID = mResponseHandler.getInstanceID(jsonResponse);
  //
  //    waitForState(EC2Properties.STATE_RUNNING, instanceID);
  //
  //    if (!FlexCommonUtils.isEmpty(mEC2PluginExecutionContext.getEIPAllocationID()))
  //    {
  //      if (mEC2PluginExecutionContext.getEIPAllocationID().equalsIgnoreCase("new"))
  //      {
  //        LOG.logInfo(methodName, "Creating new Elastic IP address.");
  //        AllocateAddressResult allocateAddressResult = mClient.allocateAddress();
  //        elasticIPAllocationID = allocateAddressResult.getAllocationId();
  //        LOG.logInfo(methodName, "Elastic IP Address: [" + allocateAddressResult.getPublicIp() + "] Allocation ID: [" + allocateAddressResult.getAllocationId() + "]");
  //      }
  //      else
  //      {
  //        try
  //        {
  //          DescribeAddressesRequest describeAddressesRequest = new DescribeAddressesRequest().withAllocationIds(mEC2PluginExecutionContext.getEIPAllocationID());
  //          DescribeAddressesResult describeAddressesResult = mClient.describeAddresses(describeAddressesRequest);
  //          elasticIPAllocationID = mEC2PluginExecutionContext.getEIPAllocationID();
  //          LOG.logInfo(methodName, "Using elastic IP [" + describeAddressesResult.getAddresses() + "]");
  //        }
  //        catch (Exception e)
  //        {
  //          throw new FlexCheckedException(EC2Properties.FDAWS_EC2_00005_BAD_ALLOCATION_ID, "The allocation ID [" + mEC2PluginExecutionContext.getEIPAllocationID() + "] does not exist.");
  //        }
  //      }
  //
  //      AssociateAddressRequest associateAddressRequest = new AssociateAddressRequest().withAllocationId(elasticIPAllocationID).withInstanceId(instanceID);
  //      AssociateAddressResult associateAddressResult = mClient.associateAddress(associateAddressRequest);
  //    }
  //
  //    waitForBoot(instanceID);
  //
  //    LOG.logInfoExiting(methodName);
  //
  //    getEC2PluginExecutionContext().getWorkflowExecutionContext().setOutput(EC2Properties.FDAWS_EC2_OUT_INSTANCE_ID, instanceID, PropertyValue.PropertyTypeEnum.String);
  //    return PluginResult.createPluginResult(getEC2PluginExecutionContext().getWorkflowExecutionContext());
  //  }
  //
  //  @Override
  //  public void validate()
  //    throws FlexCheckedException
  //  {
  //    List<String> requiredProperties = new ArrayList<String>();
  //    List<String> requiredInputs = new ArrayList<String>();
  //
  //    requiredProperties.add(EC2Properties.FDAWS_ENDPOINT);
  //    requiredProperties.add(EC2Properties.FDAWS_ACCESS_KEY);
  //    requiredProperties.add(EC2Properties.FDAWS_SECRET_KEY);
  //
  //    requiredInputs.add(EC2Properties.FDAWS_EC2_IMAGE_ID);
  //    requiredInputs.add(EC2Properties.FDAWS_EC2_INSTANCE_TYPE);
  //    requiredInputs.add(EC2Properties.FDAWS_EC2_SEC_GROUP_NAME);
  //    requiredInputs.add(EC2Properties.FDAWS_EC2_KEY_NAME);
  //  }
  @Override
  public void setWorkflowExecutionContext(WorkflowExecutionContext pExecutionContext)
  {
    // TODO Implement this method
  }

  @Override
  public PluginResult execute()
    throws FlexCheckedException
  {
    LOG.logInfo("EC2CreateInstance", "This operation functionality is currently non-functional. Will be available in upcoming releases.");
    return null;
  }

  @Override
  public void validate()
    throws FlexCheckedException
  {
    // TODO Implement this method
  }

  @Override
  public void cleanup()
  {
    // TODO Implement this method
  }
}
