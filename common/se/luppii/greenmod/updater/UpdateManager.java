package se.luppii.greenmod.updater;

import java.util.EnumSet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatMessageComponent;
import se.luppii.greenmod.GreenMod;
import se.luppii.greenmod.lib.GMReferences;
import cpw.mods.fml.common.IScheduledTickHandler;
import cpw.mods.fml.common.TickType;

public class UpdateManager implements IScheduledTickHandler {
	
	private boolean _notificationDisplayed;
	private UpdateChecker _updateChecker;
	
	public UpdateManager() {
		
		_updateChecker = new UpdateChecker();
		if (GreenMod.checkForUpdates) {
			
			_updateChecker.start();
		}
	}

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		
		if (!_notificationDisplayed && _updateChecker.checkComplete()) {
			
			_notificationDisplayed = true;
			if (_updateChecker.isNewVersionAvailable()) {
				
				EntityPlayer player = (EntityPlayer) tickData[0];
				player.sendChatToPlayer(new ChatMessageComponent().addText("[" + GMReferences.MOD_NAME + "] New version is available: " + _updateChecker.newVersion().toString()));
				player.sendChatToPlayer(new ChatMessageComponent().addText("[" + GMReferences.MOD_NAME + "] " + _updateChecker.newVersion().toString() + ": " +_updateChecker.newVersion().description()));
			}
		}
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		
	}

	@Override
	public EnumSet<TickType> ticks() {
		
		if (_notificationDisplayed) {
			
			return EnumSet.noneOf(TickType.class);
		}
		return EnumSet.of(TickType.PLAYER);
	}

	@Override
	public String getLabel() {
		
		return GMReferences.MOD_ID + ".version";
	}

	@Override
	public int nextTickSpacing() {
		
		if (!_notificationDisplayed) {
			return 400;
		}
		return 72000;
	}
}
