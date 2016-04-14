package com.wxine.android.utils;

import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

@SuppressLint("NewApi")
public class HttpCookieStore implements CookieStore {
	private static final String LOGTAG = "HttpCookieStore";

	/*
	 * The memory storage of the cookies
	 */
	private Map<String, Map<String, String>> mapCookies = new HashMap<String, Map<String, String>>();
	/*
	 * The instance of the shared preferences
	 */
	private final SharedPreferences sharedPrefs;

	/*
	 * Constructor
	 * 
	 * @param ctxContext the context of the Activity
	 */
	public HttpCookieStore(Context ctxContext) {
		//Log.i(LOGTAG, "constructor()");
		sharedPrefs = ctxContext.getSharedPreferences("Config.SHARED_PREF_NAME", Context.MODE_PRIVATE);
	}

	@Override
	public void add(URI uri, HttpCookie cookie) {
		String domain = cookie.getDomain();

		//Log.i(LOGTAG, "adding ( " + domain +", " + cookie.toString() );

		Map<String, String> cookies = mapCookies.get(domain);
		if (cookies == null) {
			cookies = new HashMap<String, String>();
			mapCookies.put(domain, cookies);
		}
		cookies.put(cookie.getName(), cookie.getValue());

		if (cookie.getName().startsWith("JSESSIONID") && !cookie.getValue().equals("")) {
			//Log.i(LOGTAG, "Saving JSESSIONID = " + cookie.getValue() );
			// Update in Shared Preferences
			Editor e = sharedPrefs.edit();
			e.putString("JSESSIONID", cookie.toString());
			e.commit(); // save changes
		}
	}

	@Override
	public List<HttpCookie> get(URI uri) {
		List<HttpCookie> cookieList = new ArrayList<HttpCookie>();

		String domain = uri.getHost();

		//Log.i(LOGTAG, "getting ( " + domain +" )" );

		Map<String, String> cookies = mapCookies.get(domain);
		if (cookies == null) {
			cookies = new HashMap<String, String>();
			mapCookies.put(domain, cookies);
		}

		for (Map.Entry<String, String> entry : cookies.entrySet()) {
			cookieList.add(new HttpCookie(entry.getKey(), entry.getValue()));
			//Log.i(LOGTAG, "returning cookie: " + entry.getKey() + "="+entry.getValue());
		}
		return cookieList;
	}

	@Override
	public List<HttpCookie> getCookies() {
		//Log.i(LOGTAG, "getCookies()");

		Set<String> mapKeys = mapCookies.keySet();

		List<HttpCookie> result = new ArrayList<HttpCookie>();
		for (String key : mapKeys) {
			Map<String, String> cookies = mapCookies.get(key);
			for (Map.Entry<String, String> entry : cookies.entrySet()) {
				result.add(new HttpCookie(entry.getKey(), entry.getValue()));
				//Log.i(LOGTAG, "returning cookie: " + entry.getKey() + "=" + entry.getValue());
			}
		}

		return result;
	}

	@Override
	public List<URI> getURIs() {
		//Log.i(LOGTAG, "getURIs()");

		Set<String> keys = mapCookies.keySet();
		List<URI> uris = new ArrayList<URI>(keys.size());
		for (String key : keys) {
			URI uri = null;
			try {
				uri = new URI(key);
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			uris.add(uri);
		}
		return uris;
	}

	@Override
	public boolean remove(URI uri, HttpCookie cookie) {
		String domain = cookie.getDomain();

		//Log.i(LOGTAG, "remove( " + domain + ", " + cookie.toString());

		Map<String, String> lstCookies = mapCookies.get(domain);

		if (lstCookies == null)
			return false;

		return lstCookies.remove(cookie.getName()) != null;
	}

	@Override
	public boolean removeAll() {
		//Log.i(LOGTAG, "removeAll()" );

		mapCookies.clear();
		return true;
	}

}
