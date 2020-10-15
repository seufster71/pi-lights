package org.pidragon.stats;

import org.pidragon.utils.Request;
import org.pidragon.utils.Response;

public interface GeneralStatsSvc {

	public void systemStats(Request request, Response response);
	public void prefStats(Request request, Response response);
	public void piStats(Request request, Response response);
}
