package id.co.sigma.common.client.util;

import id.co.sigma.common.util.SimpleDebugerWriter;
import id.co.sigma.common.util.SimpleDebugerWriterManager;

public class ClientSideSimpleDebugerWriterManager implements SimpleDebugerWriterManager{

	@Override
	public SimpleDebugerWriter getDebugWriter(String debuggerName) {
		return   new ClientSideSimpleDebugerWriter();
	}

}
