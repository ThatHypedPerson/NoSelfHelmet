package moe.thathypedperson.noselfhelmet;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;

public class ToggleCommand extends CommandBase
{
	
	@Override
	public String getCommandName()
	{
		return "helmettoggle";
	}
	
	@Override
	public int getRequiredPermissionLevel()
	{
		return 0;
	}
	
	@Override
	public String getCommandUsage(ICommandSender iCommandSender)
	{
		return null;
	}
	
	@Override
	public void processCommand(ICommandSender sender, String[] strings)
	{
		NoSelfHelmet.helmetDisabled = !NoSelfHelmet.helmetDisabled;
		alertChangedState(NoSelfHelmet.helmetDisabled);
		NoSelfHelmet.syncConfig(false);
	}
	
	private void alertChangedState(boolean state)
	{
		IChatComponent chatMessage;
		String message = EnumChatFormatting.AQUA + "Your Helmet will now be ";
		if(state)
		{
			chatMessage = new ChatComponentText(message + "hidden.");
		}
		else
		{
			chatMessage = new ChatComponentText(message + "shown.");
		}
		Minecraft.getMinecraft().thePlayer.addChatMessage(chatMessage);
	}
}
