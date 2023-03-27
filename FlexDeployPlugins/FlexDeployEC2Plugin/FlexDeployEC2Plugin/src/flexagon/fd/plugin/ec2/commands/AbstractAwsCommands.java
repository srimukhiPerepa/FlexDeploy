package flexagon.fd.plugin.ec2.commands;

import flexagon.fd.plugin.cloud.providers.CloudExecute;
import flexagon.fd.plugin.ec2.utils.EC2PluginExecutionContext;

import flexagon.ff.common.core.externalprocess.ExternalProcess;
import flexagon.ff.common.core.logging.FlexLogger;

public abstract class AbstractAwsCommands
  extends CloudExecute
{
  private static final String CLZ_NAM = AbstractAwsCommands.class.getName();
  private static final FlexLogger LOG = FlexLogger.getLogger(CLZ_NAM);

  protected ExternalProcess mExternalProcess;
  protected EC2PluginExecutionContext mEC2PluginExecutionContext = null;

  public AbstractAwsCommands()
  {
    super();
    mExternalProcess = new ExternalProcess();
  }

  public EC2PluginExecutionContext getEC2PluginExecutionContext()
  {
    if (mEC2PluginExecutionContext == null)
    {
      mEC2PluginExecutionContext = new EC2PluginExecutionContext(getWorkflowExecutionContext());
    }
    return mEC2PluginExecutionContext;
  }
}
