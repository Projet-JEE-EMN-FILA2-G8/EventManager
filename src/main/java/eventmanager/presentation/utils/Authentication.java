/**
 * 
 */
package eventmanager.presentation.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import eventmanager.presentation.utils.Constants;

/**
 * @author Hadrien
 * 
 */
public class Authentication {

	public static boolean userIsAuthenticated(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		return (session != null) && (session.getAttribute("user") != null);
	}

	
	private static final String[] notRestrictedServlets = {
										Constants.SERVLET_LOGIN,
										Constants.SERVLET_SUBSCRIBE,
										Constants.SERVLET_PUBLIC_EVENT 
									};

	public static String[] getNotRestrictedServlets() {
		return notRestrictedServlets;
	}

	private static final String[] notRestrictedPaths = {
										Constants.FOLDER_CSS,
										Constants.FOLDER_JS,
										Constants.FOLDER_FONTS
									};

	public static String[] getNotRestrictedPaths() {
		return notRestrictedPaths;
	}

	private static final String[] notRestrictedFiles = {};

	public static String[] getNotRestrictedFiles() {
		return notRestrictedFiles;
	}

}
