package com.wxine.android.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.TypeReference;

public class Syscate implements Serializable {
	private static final long serialVersionUID = 8774949472479447242L;
	private String id;
	private Integer code;
	private Syscate syscate;
	private String type;
	private String locale;
	private String scope;
	private String friend;
	private String image;
	private String name;
	private String attr;
	private String details;
	private String status;
	private Integer csort;
	private Set<Syscate> syscates = new HashSet<Syscate>(0);
	
	private String nodestr;
	private List<Attr> attrs = new ArrayList<Attr>();
	private Set<String> scopes = new HashSet<String>(0);
	private Set<String> friends = new HashSet<String>(0);
	
	public String getNodestr() {
		return nodestr;
	}

	public void setNodestr(String nodestr) {
		this.nodestr = nodestr;
	}

	public List<Attr> getAttrs() {
		try {
			return com.alibaba.fastjson.JSON.parseObject(attr, new TypeReference<List<Attr>>(){});
		} catch(Exception e) {
		}
		return attrs;
	}

	public void setAttrs(List<Attr> attrs) {
		this.attrs = attrs;
	}
	
	public Set<String> getScopes() {
		try {
			String[] array = scope.split(",");
			for (String a : array) {
				if (StringUtils.isNotBlank(a)) {
					scopes.add(StringUtils.trim(StringUtils.strip(a)));
				}
			}
		} catch (Exception e) {
		}
		return scopes;
	}

	public void setScopes(Set<String> scopes) {
		this.scopes = scopes;
	}
	
	public String existScope(String scope) {
		try {
			Set<String> set = getScopes();
			if (set.contains(scope)) {
				return "yes";
			} else {
				return "no";
			}
		} catch (Exception e) {
			return "no";
		}
	}
	
	public Set<String> getFriends() {
		try {
			String[] array = friend.split(",");
			for (String a : array) {
				if (StringUtils.isNotBlank(a)) {
					friends.add(StringUtils.trim(StringUtils.strip(a)));
				}
			}
		} catch (Exception e) {
		}
		return friends;
	}

	public void setFriends(Set<String> friends) {
		this.friends = friends;
	}

	public String existFriend(String friend) {
		try {
			Set<String> set = getFriends();
			if (set.contains(friend)) {
				return "yes";
			} else {
				return "no";
			}
		} catch (Exception e) {
			return "no";
		}
	}

	public Syscate() {
	}

	public Syscate(String id, Integer code, String type, String locale, String name, Integer csort) {
		this.id = id;
		this.code = code;
		this.type = type;
		this.locale = locale;
		this.name = name;
		this.csort = csort;
	}

	public Syscate(String id, Integer code, Syscate syscate, String type, String locale, String scope, String friend,String image, String name,
			String attr, String details, String status, Integer csort, Set<Syscate> syscates) {
		this.id = id;
		this.code = code;
		this.syscate = syscate;
		this.type = type;
		this.locale = locale;
		this.scope = scope;
		this.friend = friend;
		this.image = image;
		this.name = name;
		this.attr = attr;
		this.details = details;
		this.status = status;
		this.csort = csort;
		this.syscates = syscates;
	}
	
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
	
	public Syscate getSyscate() {
		return syscate;
	}

	public void setSyscate(Syscate syscate) {
		this.syscate = syscate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}
	
	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}
	
	public String getFriend() {
		return friend;
	}

	public void setFriend(String friend) {
		this.friend = friend;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAttr() {
		return attr;
	}

	public void setAttr(String attr) {
		this.attr = attr;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getCsort() {
		return csort;
	}

	public void setCsort(Integer csort) {
		this.csort = csort;
	}

	public Set<Syscate> getSyscates() {
		return syscates;
	}

	public void setSyscates(Set<Syscate> syscates) {
		this.syscates = syscates;
	}
}
