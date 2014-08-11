package id.co.sigma.common.client.util;

import id.co.sigma.common.util.NativeJsUtilities;
import id.co.sigma.common.util.SimpleDebugerWriter;

public class ClientSideSimpleDebugerWriter implements SimpleDebugerWriter{

	@Override
	public void debug(String message) {
		NativeJsUtilities.getInstance().writeToBrowserConsole(message);
		
	}

	@Override
	public void error(String message) {
		NativeJsUtilities.getInstance().writeToBrowserConsole(message);
		
	}

}
