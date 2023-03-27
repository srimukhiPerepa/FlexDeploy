package flexagon.fd.plugin.ec2.operations;

import flexagon.fd.core.plugin.PluginProvider;
import flexagon.fd.core.plugin.PluginResult;
import flexagon.fd.core.workflow.WorkflowExecutionContext;

import flexagon.ff.common.core.exceptions.FlexCheckedException;
import flexagon.ff.common.core.logging.FlexLogger;

//import flexagon.fd.core.plugin.PluginResult;
//
//import flexagon.ff.common.core.exceptions.FlexCheckedException;
//import flexagon.ff.common.core.logging.FlexLogger;
//
//
//import com.amazonaws.services.ec2.model.TerminateInstancesRequest;
//import com.amazonaws.services.ec2.model.TerminateInstancesResult;
//import com.amazonaws.util.json.JSONObject;

//import flexagon.fd.plugin.ec2.EC2Properties;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//
//import java.util.List;

public class EC2TerminateInstance
  implements PluginProvider
{
  private static final String CLZ_NAM = EC2TerminateInstance.class.getName();
  private static final FlexLogger LOG = FlexLogger.getLogger(CLZ_NAM);

  public EC2TerminateInstance()
  {
    super();
  }

  //
  //  @Override
  //  public PluginResult execute()
  //    throws FlexCheckedException
  //  {
  //    String methodName = "terminateInstance";
  //    LOG.logInfoEntering(methodName);
  //    initializeClient();
  //
  //    TerminateInstancesRequest terminateInstancesRequest = new TerminateInstancesRequest().withInstanceIds(mEC2PluginExecutionContext.getInstanceID().split(","));
  //
  //    LOG.logInfo(methodName, "Terminating instance(s) {0}", Arrays.asList(mEC2PluginExecutionContext.getInstanceID().split(",")).toString());
  //    TerminateInstancesResult terminateInstancesResult = mClient.terminateInstances(terminateInstancesRequest);
  //
  //    JSONObject jsonResponse = new JSONObject(terminateInstancesResult);
  //
  //    String instanceID = mResponseHandler.getInstanceID(jsonResponse);
  //
  //    waitForState(EC2Properties.STATE_TERMINATED, instanceID);
  //
  //    LOG.logInfoExiting(methodName);
  //
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
  //    requiredInputs.add(EC2Properties.FDAWS_EC2_INP_INSTANCE_IDS);
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
    LOG.logInfo("EC2TerminateInstance", "This operation functionality is currently non-functional. Will be available in upcoming releases.");
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
