package flexagon.fd.plugin.ec2.operations;

import flexagon.fd.core.plugin.PluginProvider;
import flexagon.fd.core.plugin.PluginResult;
import flexagon.fd.core.workflow.WorkflowExecutionContext;

import flexagon.ff.common.core.exceptions.FlexCheckedException;
import flexagon.ff.common.core.logging.FlexLogger;
//import com.amazonaws.AmazonServiceException;
//import com.amazonaws.services.ec2.model.AuthorizeSecurityGroupEgressRequest;
//import com.amazonaws.services.ec2.model.AuthorizeSecurityGroupIngressRequest;
//import com.amazonaws.services.ec2.model.CreateSecurityGroupRequest;
//import com.amazonaws.services.ec2.model.CreateSecurityGroupResult;
//import com.amazonaws.services.ec2.model.DescribeSecurityGroupsRequest;
//import com.amazonaws.services.ec2.model.DescribeSecurityGroupsResult;
//import com.amazonaws.services.ec2.model.IpPermission;
//import com.amazonaws.services.ec2.model.SecurityGroup;
//import com.amazonaws.util.json.JSONArray;
//import com.amazonaws.util.json.JSONException;
//import com.amazonaws.util.json.JSONObject;

public class EC2CreateSecurityGroup
  implements PluginProvider
{
  private static final String CLZ_NAM = EC2CreateSecurityGroup.class.getName();
  private static final FlexLogger LOG = FlexLogger.getLogger(CLZ_NAM);

  public EC2CreateSecurityGroup()
  {
    super();
  }

  //  @Override
  //  public PluginResult execute()
  //    throws FlexCheckedException
  //  {
  //    createOrUpdateSecGroup();
  //    return PluginResult.createPluginResult(getEC2PluginExecutionContext().getWorkflowExecutionContext());
  //  }
  //
  //  public void createOrUpdateSecGroup()
  //    throws FlexCheckedException
  //  {
  //    String methodName = "createOrUpdateSecGroup";
  //    LOG.logInfoEntering(methodName);
  //    initializeClient();
  //
  //    String groupId = "";
  //    JSONObject jsonResponse;
  //    List<IpPermission> newIpPermissions = null;
  //    List<IpPermission> existingIpPermissions = null;
  //
  //    try
  //    {
  //      DescribeSecurityGroupsRequest describeSecurityGroupsRequest = new DescribeSecurityGroupsRequest();
  //      describeSecurityGroupsRequest.withGroupNames(mEC2PluginExecutionContext.getSecurityGroupName());
  //      DescribeSecurityGroupsResult describeSecurityGroupsResult = mClient.describeSecurityGroups(describeSecurityGroupsRequest);
  //
  //      List<SecurityGroup> securityGroupList = describeSecurityGroupsResult.getSecurityGroups();
  //      existingIpPermissions = securityGroupList.get(0).getIpPermissions();
  //
  //      jsonResponse = new JSONObject(describeSecurityGroupsResult);
  //
  //      try
  //      {
  //        JSONArray securityGroups = jsonResponse.getJSONArray("securityGroups");
  //        groupId = (String) securityGroups.getJSONObject(0).get("groupId");
  //      }
  //      catch (JSONException f)
  //      {
  //        throw new FlexCheckedException(EC2Properties.FDAWS_EC2_00000_JSON_PARSE_FAIL, "Failed to get group id for the requested security group.");
  //      }
  //
  //      LOG.logInfo(methodName, "Security group [" + mEC2PluginExecutionContext.getSecurityGroupName() + "] exists, updating...");
  //    }
  //    catch (AmazonServiceException ase)
  //    {
  //      LOG.logInfo(methodName, "Security group [" + mEC2PluginExecutionContext.getSecurityGroupName() + "] does not exist, creating...");
  //
  //      CreateSecurityGroupRequest createSecurityGroupsRequest = new CreateSecurityGroupRequest();
  //      createSecurityGroupsRequest.withGroupName(mEC2PluginExecutionContext.getSecurityGroupName()).withDescription(mEC2PluginExecutionContext.getSecurityGroupDesc());
  //      CreateSecurityGroupResult createSecurityGroupResult = mClient.createSecurityGroup(createSecurityGroupsRequest);
  //      jsonResponse = new JSONObject(createSecurityGroupResult);
  //
  //      try
  //      {
  //        groupId = (String) jsonResponse.get("groupId");
  //      }
  //      catch (JSONException f)
  //      {
  //        throw new FlexCheckedException(EC2Properties.FDAWS_EC2_00000_JSON_PARSE_FAIL, "Failed to get group id for the requested security group.");
  //      }
  //
  //      LOG.logInfo(methodName, "Security group [" + mEC2PluginExecutionContext.getSecurityGroupName() + "] created with id [" + groupId + "]");
  //    }
  //
  //    if (!FlexCommonUtils.isEmpty(mEC2PluginExecutionContext.getInboundRules()))
  //    {
  //      newIpPermissions = splitSecurityRules(mEC2PluginExecutionContext.getInboundRules(), existingIpPermissions);
  //      if (!FlexCommonUtils.isEmpty(newIpPermissions))
  //      {
  //        AuthorizeSecurityGroupIngressRequest authorizeSecurityGroupIngressRequest = new AuthorizeSecurityGroupIngressRequest();
  //        authorizeSecurityGroupIngressRequest.withGroupName(mEC2PluginExecutionContext.getSecurityGroupName()).withIpPermissions(newIpPermissions);
  //        mClient.authorizeSecurityGroupIngress(authorizeSecurityGroupIngressRequest);
  //      }
  //      else
  //      {
  //        LOG.logWarning(methodName, "No new security rules to add.");
  //      }
  //    }
  //
  //    if (!FlexCommonUtils.isEmpty(mEC2PluginExecutionContext.getOutboundRules()))
  //    {
  //      newIpPermissions = splitSecurityRules(mEC2PluginExecutionContext.getOutboundRules(), existingIpPermissions);
  //      if (!FlexCommonUtils.isEmpty(newIpPermissions))
  //      {
  //        AuthorizeSecurityGroupEgressRequest authorizeSecurityGroupEgressRequest = new AuthorizeSecurityGroupEgressRequest();
  //        authorizeSecurityGroupEgressRequest.withGroupId(groupId).withIpPermissions(newIpPermissions);
  //        mClient.authorizeSecurityGroupEgress(authorizeSecurityGroupEgressRequest);
  //      }
  //      else
  //      {
  //        LOG.logWarning(methodName, "No new security rules to add.");
  //      }
  //    }
  //
  //    LOG.logInfoExiting(methodName);
  //  }
  //
  //    public List<IpPermission> splitSecurityRules(String pSecRules, List<IpPermission> pPermissions)
  //      throws FlexCheckedException
  //    {
  //      String methodName = "splitSecurityRules";
  //      LOG.logInfoEntering(methodName);
  //
  //      List<IpPermission> newIpPermissions = new ArrayList<IpPermission>();
  //      String rulesFromInput = pSecRules;
  //      String[] rules = rulesFromInput.split(";");
  //      String[] rule;
  //      String protocol;
  //      String ipRange;
  //      int fromPort;
  //      int toPort;
  //
  //      for (String currentRule: rules)
  //      {
  //        if (!FlexCommonUtils.isEmpty(currentRule))
  //        {
  //          rule = currentRule.split(",");
  //          LOG.logFinest(methodName, "Splitting rule [" + currentRule + "]");
  //
  //          if (rule.length != 3)
  //          {
  //            throw new FlexCheckedException(EC2Properties.FDAWS_EC2_00001_BAD_SECURITY_RULE, "Security rule format does not contain exactly 3 elements.");
  //          }
  //
  //          try
  //          {
  //            if (rule[2].contains("-"))
  //            {
  //              LOG.logFinest(methodName, "Found port range [" + rule[2] + "]");
  //              fromPort = Integer.parseInt(rule[2].split("-")[0]);
  //              toPort = Integer.parseInt(rule[2].split("-")[1]);
  //
  //              if (fromPort > toPort)
  //              {
  //                LOG.logInfo(methodName, "From port greater than to port, switching...");
  //                toPort = Integer.parseInt(rule[2].split("-")[0]);
  //                fromPort = Integer.parseInt(rule[2].split("-")[1]);
  //              }
  //            }
  //            else
  //            {
  //              LOG.logFinest(methodName, "Found single port [" + rule[2] + "]");
  //              fromPort = Integer.parseInt(rule[2]);
  //              toPort = Integer.parseInt(rule[2]);
  //            }
  //          }
  //          catch (NumberFormatException nfe)
  //          {
  //            throw new FlexCheckedException(EC2Properties.FDAWS_EC2_00002_BAD_PORT_RANGE, "[" + rule[2] + "] is not a valid port range.");
  //          }
  //
  //          protocol = rule[0].trim();
  //          ipRange = rule[1].trim();
  //
  //          if (!ipRange.contains("."))
  //          {
  //            throw new FlexCheckedException(EC2Properties.FDAWS_EC2_00004_MALFORMED_IP, "[" + ipRange + "] is an invalid IP range.");
  //          }
  //
  //          //Check for CIDR notation
  //          if (!ipRange.contains("/"))
  //          {
  //            if (ipRange.equals("0.0.0.0"))
  //            {
  //              ipRange.concat("/0");
  //            }
  //            else
  //            {
  //              ipRange.concat("/32");
  //            }
  //          }
  //
  //          IpPermission ipPermission = new IpPermission().withIpProtocol(protocol).withIpRanges(ipRange).withFromPort(fromPort).withToPort(toPort);
  //
  //          LOG.logFinest(methodName, "Protocol: [" + protocol + "] IP range: [" + ipRange + "] To port: [" + toPort + "] From port: [" + fromPort + "]");
  //          if (pPermissions != null)
  //          {
  //            if (!pPermissions.contains(ipPermission) && newIpPermissions.contains(ipPermission))
  //            {
  //              newIpPermissions.add(ipPermission);
  //            }
  //          }
  //          else
  //          {
  //            newIpPermissions.add(ipPermission);
  //          }
  //        }
  //      }
  //
  //      if (FlexCommonUtils.isEmpty(newIpPermissions))
  //      {
  //        LOG.logWarning(methodName, "Security rule already exists, skipping.");
  //      }
  //
  //      LOG.logInfoExiting(methodName);
  //      return newIpPermissions;
  //    }
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
  //    requiredInputs.add(EC2Properties.FDAWS_EC2_SEC_GROUP_NAME);
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
    LOG.logInfo("EC2CreateSecurityGroup", "This operation functionality is currently non-functional. Will be available in upcoming releases.");
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
