package se.luppii.greenmod.updater;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.logging.Level;

import se.luppii.greenmod.lib.GMLogger;
import se.luppii.greenmod.lib.GMReferences;

public class UpdateChecker extends Thread {
	
	private String _versionUrl;
	private ModVersion _newVersion;
	private boolean _checkComplete;
	private boolean _newVersionAvailable;
	
	public UpdateChecker() {
		
		_versionUrl = "https://raw.github.com/Luppii/" + GMReferences.MOD_ID + "/master/VERSION";
	}
	
	@Override
	public void run() {
		
		try {
			
			URL versionFile = new URL(_versionUrl);
			BufferedReader reader = new BufferedReader(new InputStreamReader(versionFile.openStream()));
			ModVersion newVersion = ModVersion.parse(reader.readLine());
			ModVersion thisVersion = ModVersion.parse(GMReferences.MOD_VERSION);
			reader.close();
			
			_newVersion = newVersion;
			_newVersionAvailable = thisVersion.compareTo(newVersion) < 0;
			if (_newVersionAvailable) {
				
				GMLogger.log(Level.INFO, "A new version of " + GMReferences.MOD_NAME + "is available: " + newVersion.toString() + ".");
			}
			
			_checkComplete = true;
		}
		catch (Exception err) {
			
			GMLogger.log(Level.WARNING, "Update check for " + GMReferences.MOD_ID + " failed: " + err.getMessage());
			err.printStackTrace();
		}
	}

	public boolean checkComplete() {

		return _checkComplete;
	}
	
	public boolean isNewVersionAvailable() {
		
		return _newVersionAvailable;
	}
	
	public ModVersion newVersion() {
		
		return _newVersion;
	}
}
