package service;

import message.TopAndNextResponse;
import net.RecvThread;
import net.sf.json.JSONObject;
import view.MusicPlayerJFrame;


//K¸è·þÎñ
public class TopAndNextService implements IService {
	@Override
	public void doRun(RecvThread handle) {
		JSONObject object = handle.getJsonObject();
		MusicPlayerJFrame.getMusicPlayerJFrame().getMusicPlayerJPanel().FirstPlayMusRequest();
		String msg = JSONObject.fromObject(new TopAndNextResponse()).toString();
		handle.getWriter().println(msg);
		handle.getWriter().flush();
	}
}
