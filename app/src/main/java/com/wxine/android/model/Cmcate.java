package com.wxine.android.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;

import org.apache.commons.lang3.StringUtils;

public class Cmcate implements Serializable {
	private static final long serialVersionUID = 7533861909883247380L;
	private String id;
	private Community community;
	private Course course;
	private Task task;
	private Event event;
	private Cmcate cmcate;
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
	private Set<Cmcate> cmcates = new HashSet<Cmcate>(0);
	
	private String nodestr;
	private String pid;//用于批量更新分类
	private Set<String> scopes = new HashSet<String>(0);
	private Set<String> friends = new HashSet<String>(0);
	
	private String text;
	private String icon;
	private Set<Cmcate> children = new HashSet<Cmcate>(0);

	public Set<Cmcate> getChildren() {
		return cmcates;
	}

	public void setChildren(Set<Cmcate> children) {
		this.children = children;
	}

	public String getText() {
		this.text = this.name;
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIcon() {
		this.icon = this.image;
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	private boolean load_on_demand;

	public boolean isLoad_on_demand() {
		this.load_on_demand = false;
		if (null != cmcates && cmcates.size() > 0) {
			this.load_on_demand = true;
		}
		return load_on_demand;
	}

	public void setLoad_on_demand(boolean load_on_demand) {
		this.load_on_demand = load_on_demand;
	}
	
	private List<Cmcate> menu = new ArrayList<Cmcate>(0);//导航路径

	public List<Cmcate> getMenu() {
		findCmcate(menu, this);
		Collections.reverse(menu);
		return menu;
	}

	public void setMenu(List<Cmcate> menu) {
		this.menu = menu;
	}
	
	public List<Cmcate> findCmcate(List<Cmcate> menu, Cmcate cmcate) {
		if (null != cmcate && StringUtils.isNotBlank(cmcate.getId())) {
			menu.add(cmcate);
			return findCmcate(menu, cmcate.getCmcate());
		}
		return menu;
	}

	public String getNodestr() {
		return nodestr;
	}

	public void setNodestr(String nodestr) {
		this.nodestr = nodestr;
	}

	public String getPid() {
		try {
			this.pid = this.cmcate.getId();//tree需要
		}catch(Exception e) {
		}
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
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

	public Cmcate() {
	}

	public Cmcate(String id, Community community, String type, String locale, String name, Integer csort) {
		this.id = id;
		this.community = community;
		this.type = type;
		this.locale = locale;
		this.name = name;
		this.csort = csort;
	}

	public Cmcate(String id, Community community, Cmcate cmcate, String type, String locale, String scope, String friend, String image, String name,
			String details, String status, Integer csort, Timestamp ctime, Set<Cmcate> cmcates) {
		this.id = id;
		this.community = community;
		this.cmcate = cmcate;
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
		this.cmcates = cmcates;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Community getCommunity() {
		return community;
	}

	public void setCommunity(Community community) {
		this.community = community;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Cmcate getCmcate() {
		return cmcate;
	}

	public void setCmcate(Cmcate cmcate) {
		this.cmcate = cmcate;
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

	public Set<Cmcate> getCmcates() {
		return cmcates;
	}

	public void setCmcates(Set<Cmcate> cmcates) {
		this.cmcates = cmcates;
	}
}
