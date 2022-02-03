package Server.proxies;

import Common.Message;
import Server.Communications.ServerCom;

/**
 * 
 * @author renato
 * @author ruben
 *
 */
public interface SharedRegionProxy {
	
	/**
	 * 
	 * @param msg
	 * @param scon
	 * @return
	 */
	public Message processAndReply(Message msg, ServerCom scon);
	
	/**
	 * 
	 * @return
	 */
	public boolean open();
}
