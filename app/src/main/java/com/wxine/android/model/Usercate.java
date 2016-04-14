package com.wxine.android.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

public class Usercate implements Serializable {
	private static final long serialVersionUID = -3606774690255430826L;
	private String id;
	private User user;
	private Usercate usercate;
	private String type;
	private String locale;
	private String scope;
	private String friend;
	private String image;//相册封面
	private String name;
	private String details;
	private String status;
	private Integer csort;
	private Timestamp ctime;
	private Set<Usercate> usercates = new HashSet<Usercate>(0);
	private Set<Photo> photos = new HashSet<Photo>(0);
	
	private String nodestr;
	private Set<Photo> subphotos = new HashSet<Photo>(0);
	private Set<String> scopes = new HashSet<String>(0);
	private Set<String> friends = new HashSet<String>(0);
	
	public String getNodestr() {
		return nodestr;
	}

	public void setNodestr(String nodestr) {
		this.nodestr = nodestr;
	}

	public Set<Photo> getSubphotos() {
		return subphotos;
	}
	
	public Set<Photo> getSubphotos(int size) {
		try {
			if (photos.size() < size) {
				size = photos.size();
			}
			int i=0;
			for(Photo image : photos) {
				subphotos.add(image);
				i++;
				if (i >= size)
					break;
			}
		} catch(Exception e) {
			
		}
		return subphotos;
	}

	public void setSubphotos(Set<Photo> subphotos) {
		this.subphotos = subphotos;
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

	public Usercate() {
	}

	public Usercate(String id, User user, String type, String locale, String name, Integer csort) {
		this.id = id;
		this.user = user;
		this.type = type;
		this.locale = locale;
		this.name = name;
		this.csort = csort;
	}

	public Usercate(String id, User user, Usercate usercate, String type, String locale, String scope, String friend, String image, String name,
			String details, String status, Integer csort, Timestamp ctime, Set<Usercate> usercates) {
		this.id = id;
		this.user = user;
		this.usercate = usercate;
		this.type = type;
		this.locale = locale;
		this.scope = scope;
		this.friend = friend;
		this.image = image;
		this.name = name;
		this.details = details;
		this.status = status;
		this.csort = csort;
		this.ctime = ctime;
		this.usercates = usercates;
	}
	
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Usercate getUsercate() {
		return usercate;
	}

	public void setUsercate(Usercate usercate) {
		this.usercate = usercate;
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
		if (!StringUtils.isNotBlank(image)) {
			try {
				this.image = photos.iterator().next().getUrl();
			} catch(Exception e) {
			}
		}
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
	
	public Timestamp getCtime() {
		return this.ctime;
	}

	public void setCtime(Timestamp ctime) {
		this.ctime = ctime;
	}

	public Set<Usercate> getUsercates() {
		return usercates;
	}

	public void setUsercates(Set<Usercate> usercates) {
		this.usercates = usercates;
	}

	public Set<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(Set<Photo> photos) {
		this.photos = photos;
	}
}
