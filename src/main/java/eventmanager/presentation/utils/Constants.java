/**
 * 
 */
package eventmanager.presentation.utils;

/**
 * @author Hadrien
 *
 */
public class Constants {


	public static final int SESSION_MAXTIME = 30*60; // 30 minutes
	
	public static final String SERVLET_LOGIN = "/Login";
	public static final String SERVLET_SUBSCRIBE = "/Subscribe";
	public static final String SERVLET_PUBLIC_EVENT = "/Event";
	public static final String SERVLET_CREATE_EVENT = "/CreateEvent";
	public static final String SERVLET_EDIT_EVENT = "/EditEvent";
	public static final String SERVLET_EVENT_LIST = "/MyEvents";
	public static final String SERVLET_LOGOUT = "/Logout";
	
	public static final String SERVLET_MAIN = SERVLET_EVENT_LIST;
	
	
	public static String FOLDER_JS = "/js/";
	public static String FOLDER_CSS = "/css/";
	public static String FOLDER_FONTS = "/fonts/";
	public static String FOLDER_JSP = "/WEB-INF/jsp/";
	
	public static final String JSP_HEAD = FOLDER_JSP + "head.jsp";
	public static final String JSP_NAV = FOLDER_JSP + "nav.jsp";
	
	public static final String JSP_LOGIN		 = 	FOLDER_JSP + "login.jsp";
	public static final String JSP_SUBSCRIBE 	 = 	FOLDER_JSP + "subscribe.jsp";
	public static final String JSP_EVENT_LIST 	 =	FOLDER_JSP + "myEvents.jsp";
	public static final String JSP_EVENT		 = 	FOLDER_JSP + "event.jsp";
	public static final String JSP_EDIT_EVENT = 	FOLDER_JSP + "editEvent.jsp";

	
	public static final String ACTION = "action";
	public static final String ACTION_DELETE = "delete";
	public static final String ACTION_SHOW = "publish";
	public static final String ACTION_SUSBCRIBE= "show";
	
}
