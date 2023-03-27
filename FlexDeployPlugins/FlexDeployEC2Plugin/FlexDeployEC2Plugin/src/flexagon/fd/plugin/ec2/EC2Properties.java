package flexagon.fd.plugin.ec2;

import flexagon.fd.plugin.cloud.FlexDeployPluginCoreCloudProperties;


public abstract class EC2Properties
  extends FlexDeployPluginCoreCloudProperties
{
  // constants
  public static final String STATE_RUNNING = "running";
  public static final String STATE_STOPPED = "stopped";
  public static final String STATE_TERMINATED = "terminated";
  public static final String STATUS_OK = "ok";
  public static final String STATUS_NOT_APPLICABLE = "not-applicable";
  public static final String FDAWS_DESCRIBE_EC2_INSTANCE_STATUS_JSON_RESPONSE = "Describe_Instance_Status";

  // properties
  public static final String FDAWS_ACCESS_KEY = "FDAWS_ACCESS_KEY"; //The access key generated on the "Security Credentials" page.
  public static final String FDAWS_SECRET_KEY = "FDAWS_SECRET_KEY"; //The secret key generated on the "Security Credentials" page.
  public static final String FDAWS_EC2_STATUS_INTERVAL_DURATION = "FDAWS_EC2_STATUS_INTERVAL_DURATION";
  public static final String FDAWS_EC2_STATUS_CHECK_COUNT = "FDAWS_EC2_STATUS_CHECK_COUNT";

  // inputs
  public static final String FDAWS_EC2_INP_INSTANCE_IDS = "FDAWS_EC2_INP_INSTANCE_IDS"; //The ID of the EXISTING instance we want to use (e.g. i-04ad0x0x0xxx00xx)
  public static final String FDAWS_EC2_INSTANCE_TYPE = "FDAWS_EC2_INSTANCE_TYPE"; //The type of instance (e.g. c4.xlarge)
  public static final String FDAWS_EC2_IMAGE_ID = "FDAWS_EC2_IMAGE_ID"; //The image ID used for creation (e.g. ami-d0f506b0). Not to be confused with Instance ID, which points at an existing instance.
  public static final String FDAWS_EC2_MIN_INSTANCES = "FDAWS_EC2_MIN_INSTANCES"; //The minimum number of instances to create/start/stop
  public static final String FDAWS_EC2_MAX_INSTANCES = "FDAWS_EC2_MAX_INSTANCES"; //The maximum number of instances to create/start/stop
  public static final String FDAWS_EC2_SEC_GROUP_NAME = "FDAWS_EC2_SEC_GROUP_NAME"; //The name of the security group to use when creating
  public static final String FDAWS_EC2_SEC_GROUP_DESCRIPTION = "FDAWS_EC2_SEC_GROUP_DESCRIPTION"; //The description of the security group being created
  public static final String FDAWS_EC2_KEY_NAME = "FDAWS_EC2_KEY_NAME"; //The name of the key to use when creating
  public static final String FDAWS_EC2_WITH_FORCE = "FDAWS_EC2_WITH_FORCE"; //Boolean to force stop instances.
  public static final String FDAWS_EC2_RELEASE_EIP = "FDAWS_EC2_RELEASE_EIP"; //Boolean to release elastic IP on termination of instance
  public static final String FDAWS_EC2_INBOUND_SECURITY_RULES = "FDAWS_EC2_INBOUND_SECURITY_RULES"; //Security rules to be added to the security group. (e.g. tcp,192.169.15.124,5900-5910;)
  public static final String FDAWS_EC2_OUTBOUND_SECURITY_RULES = "FDAWS_EC2_OUTBOUND_SECURITY_RULES"; //Outbound security rules
  public static final String FDAWS_EC2_ELASTIC_IP_ID = "FDAWS_EC2_ELASTIC_IP_ID"; //The allocation ID for the elastic IP to use on creation. use "new" to create a new eip
  public static final String FDAWS_CF_TEMPLATE_URL = "FDAWS_CF_TEMPLATE_URL"; //The URL path to the CloudFormation template to use when creating a stack
  public static final String FDAWS_CF_TEMPLATE_FILENAME = "FDAWS_CF_TEMPLATE_FILENAME"; //The name of the template file to use when creating a stack
  public static final String FDAWS_CF_STACK_NAME = "FDAWS_CF_STACK_NAME"; //The name to give to the created stack
  public static final String FDAWS_CF_TEMPLATE_PARAMETERS = "FDAWS_CF_TEMPLATE_PARAMETERS"; //Semicolon delimited list of parameters to pass in on creation (e.g. DBUser=sys;DBPassword=welcome1)
  public static final String FDAWS_CF_ON_FAILURE = "FDAWS_CF_ON_FAILURE"; //Drop down menu of what to do if stack creation fails. Options are: DELETE,DO NOTHING,ROLLBACK

  // outputs
  public static final String FDAWS_EC2_OUT_INSTANCE_ID = "FDAWS_EC2_OUT_INSTANCE_ID";
  public static final String FDAWS_CF_OUT_STACK_ID = "FDAWS_EC2_OUT_STACK_ID";

  //Project FlexDeployEC2Plugin
  // Code: [ (F)lex (D)eploy  (A)mazon (W)eb (S)ervices ]

  // Class flexagon.fd.plugin.ec2.operations.******  range 00000-00049
  public static final String FDAWS_EC2_00000_JSON_PARSE_FAIL = "FDAWS_EC2-00000";
  public static final String FDAWS_EC2_00001_BAD_SECURITY_RULE = "FDAWS_EC2-00001";
  public static final String FDAWS_EC2_00002_BAD_PORT_RANGE = "FDAWS_EC2-00002";
  public static final String FDAWS_EC2_00003_TIMEOUT = "FDAWS_EC2-00003";
  public static final String FDAWS_EC2_00004_MALFORMED_IP = "FDAWS_EC2-00004";
  public static final String FDAWS_EC2_00005_BAD_ALLOCATION_ID = "FDAWS_EC2-00005";
  public static final String FDAWS_CF_00006_NO_TEMPLATE = "FDAWS_EC2-00006";
  public static final String FDAWS_CF_00007_TOO_MANY_FILES = "FDAWS_EC2-00007";
  public static final String FDAWS_CF_00008_BAD_PARAMETER = "FDAWS_EC2-00008";
  public static final String FDAWS_CF_00009_COULDNT_GET_STATUS = "FDAWS_EC2-00009";
  public static final String FDAWS_EC2_00010_START_INSTANCE_FAILURE = "FDAWS_EC2-00010";
  public static final String FDAWS_EC2_00011_STOP_INSTANCE_FAILURE = "FDAWS_EC2-00011";
  public static final String FDAWS_EC2_00012_INSTANCE_ID_IS_REQUIRED = "FDAWS_EC2-00012";
  public static final String FDAWS_EC2_00013_GENERIC_INSTANCE_ID_REQUIRED = "FDAWS_EC2-00013";
  public static final String FDAWS_EC2_00014_AWS_ACCOUNT_CODE_IS_REQUIRED = "FDAWS_EC2-00014";
  public static final String FDAWS_EC2_00015_AWS_EC2_INST_STATUS_PARSING_ERROR = "FDAWS_EC2-00015";
  public static final String FDAWS_EC2_00016_INST_STATUS_THREAD_SLEEP_ERROR = "FDAWS_EC2-00016";

  //AWS CLI commands
  public static final String FDAWS_EC2_CONSTANT = "ec2";
  public static final String FDAWS_START_INSTANCES_CONSTANT = "start-instances";
  public static final String FDAWS_STOP_INSTANCES_CONSTANT = "stop-instances";
  public static final String FDAWS_INSTANCE_IDS_CONSTANT = "--instance-ids";
  public static final String FDAWS_ENDPOINT_CONSTANT = "--endpoint-url";
  public static final String FDAWS_FORCE_CONSTANT = "--force";
  public static final String FDAWS_DESCRIBE_INSTANCES_STATUS_CONSTANT = "describe-instance-status";
  public static final String FDAWS_INLCUDE_ALL_INSTANCES_CONSTANT = "--include-all-instances";
  public static final String FDAWS_START_CONSTANT = "start";
  public static final String FDAWS_STOP_CONSTANT = "stop";
  public static final String JSON_EC2_INSTANCE_STATUSES_CONSTANT = "InstanceStatuses";
  public static final String JSON_EC2_INSTANCE_STATUS_CONSTANT = "InstanceStatus";
  public static final String JSON_EC2_INSTANCE_STATE_CONSTANT = "InstanceState";
  public static final String JSON_EC2_STATUS_CONSTANT = "Status";
  public static final String JSON_EC2_NAME_CONSTANT = "Name";
  public static final String JSON_EC2_INSTANCE_ID_CONSTANT = "InstanceId";
  public static final String JSON_EC2_SYSTEM_STATUS_CONSTANT = "SystemStatus";

  //o/p variables
  public static final String FDAWS_OUT_EC2_INSTANCE_STATUS = "FDAWS_OUT_EC2_INSTANCE_STATUS";
  public static final String FDAWS_OUT_EC2_INSTANCE_RESP = "FDAWS_OUT_EC2_INSTANCE_RESP";
  public static final String FDAWS_OUT_EC2_INSTANCE_ERR = "FDAWS_OUT_EC2_INSTANCE_ERR";
}


