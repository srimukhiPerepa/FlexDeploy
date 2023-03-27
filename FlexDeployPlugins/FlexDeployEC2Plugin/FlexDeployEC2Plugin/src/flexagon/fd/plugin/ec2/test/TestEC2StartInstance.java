package flexagon.fd.plugin.ec2.test;

import flexagon.fd.core.Environment;
import flexagon.fd.core.Instance;
import flexagon.fd.core.Project;
import flexagon.fd.core.PropertyValue;
import flexagon.fd.core.plugin.PluginResult;
import flexagon.fd.core.workflow.WorkflowExecutionContext;
import flexagon.fd.plugin.cloud.FlexDeployPluginCoreCloudProperties;
import flexagon.fd.plugin.ec2.EC2Properties;
import flexagon.fd.plugin.ec2.commands.EC2Commands;
import flexagon.fd.plugin.ec2.operations.EC2StartInstance;

import flexagon.ff.common.core.exceptions.FlexCheckedException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Assert;
import org.junit.Test;

public class TestEC2StartInstance
{
  public TestEC2StartInstance()
  {
    super();
  }

  @SuppressWarnings("oracle.jdeveloper.java.insufficient-catch-block")
  @Test
  public void testStartEC2Instance()
    throws FlexCheckedException
  {
    final String instanceCode = "AWS";
    final String cloudAccountCode = "AWS";
    final String endpointURL = "<CHANGE_ME>";
    final String awsCLIPath = "<CHANGE_ME>";
    final String awsAccessKey = "<CHANGE_ME>";
    final String awsSecretKey = "<CHANGE_ME>";
    final String region = "<CHANGE_ME>";
    final String awsInstanceIds = "<CHANGE_ME>";
    final int iterations = 0; //CHANGE_ME
    final int seconds = 0; //CHANGE_ME

    Environment env = new Environment(new Long(5501), "DEV", "Development");
    Instance inst = new Instance(new Long(6601), instanceCode, cloudAccountCode);
    Project proj = new Project(new Long(1000), "ProjectAWS");
    Map<String, PropertyValue> inputs = new ConcurrentHashMap<String, PropertyValue>();
    WorkflowExecutionContext context = new WorkflowExecutionContext(proj, env, inst, (long) 2000, inputs);

    context.setWorkflowExecutionId(1000L);
    context.setOrginatingWorkflowExecutionId(10002L);
    //Above 2 lines makes the artifact folder to be C:\Test\fd\work\1000\10002\artifacts(windows)

    context.addProperty(EC2Properties.FDAWS_ACCOUNT_CODE, cloudAccountCode, PropertyValue.PropertyTypeEnum.String, false);
    context.addProperty(EC2Properties.FDAWS_ENDPOINT, endpointURL, PropertyValue.PropertyTypeEnum.String, false);
    context.addProperty(EC2Properties.FDAWS_CLI_PATH, awsCLIPath, PropertyValue.PropertyTypeEnum.String, false);
    context.addProperty(EC2Properties.FDAWS_EC2_STATUS_CHECK_COUNT, iterations, PropertyValue.PropertyTypeEnum.Integer, false);
    context.addProperty(EC2Properties.FDAWS_EC2_STATUS_INTERVAL_DURATION, seconds, PropertyValue.PropertyTypeEnum.Integer, false);
    context.addProperty(instanceCode, FlexDeployPluginCoreCloudProperties.FDAWSACCT_ACCESS_KEY, awsAccessKey, PropertyValue.PropertyTypeEnum.String, false);
    context.addProperty(instanceCode, FlexDeployPluginCoreCloudProperties.FDAWSACCT_SECRET_KEY, awsSecretKey, PropertyValue.PropertyTypeEnum.String, true);
    context.addProperty(instanceCode, FlexDeployPluginCoreCloudProperties.FDAWSACCT_DEFAULT_REGION, region, PropertyValue.PropertyTypeEnum.String, false);

    inputs.put(EC2Properties.FDAWS_EC2_INP_INSTANCE_IDS, new PropertyValue(awsInstanceIds, PropertyValue.PropertyTypeEnum.String, false));

    EC2Commands plugin;
    plugin = new EC2StartInstance();
    plugin.setWorkflowExecutionContext(context);
    plugin.validate();
    String expectedStatus = "Success";
    PluginResult result = null;

    String status = "Success";
    try
    {
      result = plugin.execute();
    }
    catch (Exception e)
    {
      status = "Failed";
    }

    Assert.assertEquals(expectedStatus, status);
    if (status.equals("Success"))
    {
      Map<String, PropertyValue> output = context.getOutputMap();
      Assert.assertTrue(Integer.parseInt(output.get(EC2Properties.FDAWS_OUT_EC2_INSTANCE_STATUS).getValue().toString()) == 0);
    }
  }
}
