package com.wxine.android.model;

import com.wxine.android.utils.HtmlUtil;
import com.wxine.android.utils.SubString;

import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

public class Task implements java.io.Serializable {
	private static final long serialVersionUID = 5255664133410825880L;
	private String id;
	private User user;
	private Community community;
	private Course course;
	private Syscate syscate;
	private Usercate usercate;
	private Task task;
	private Goods goods;
	private String scope;
	private String friend;
	private String topic;
	private String type;
	private String name;
	private String title;
	private String image;
	private String banner;
	private String brief;
	private String tag;
	private String content;
	private Integer csort;
	private String status;
	private String userstatus;
	private Integer ilike;
	private Integer cmcount;
	private Integer sharecount;
	private Integer readcount;
	private String security;
	private String district;
	private String address;
	private Double latitude;
	private Double longitude;
	private String url;
	private Timestamp ctime;
	private Integer usercount;
	private Integer infocount;
	private Integer photocount;
	private Integer videocount;
	private Timestamp plantime;
	private String emerlevel;
	private Integer point;
	private Timestamp starttime;
	private Timestamp endtime;
	
	private Set<String> scopes = new HashSet<String>(0);
	private Set<String> friends = new HashSet<String>(0);
	private Set<String> topics = new HashSet<String>(0);
	private Set<String> tags = new HashSet<String>(0);
	private String cleancontent;// 清除了所有标签
	private String basiccontent;
	private String country;//国家
	private String province;//省
	private String city;//市
	private String region;//城区

	public String getCountry() {
		try {
			String[] arr = StringUtils.split(district, "-");
			country = arr[0];
		} catch (Exception e) {
			country = "";
		}
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		try {
			String[] arr = StringUtils.split(district, "-");
			province = arr[1];
		} catch (Exception e) {
			province = "";
		}
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		try {
			String[] arr = StringUtils.split(district, "-");
			city = arr[2];
		} catch (Exception e) {
			city = "";
		}
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRegion() {
		try {
			String[] arr = StringUtils.split(district, "-");
			region = arr[3];
		} catch (Exception e) {
			region = "";
		}
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getBasiccontent() {
		try {
			basiccontent = HtmlUtil.cleanHtml(content);
		} catch (Exception e) {
		}
		return basiccontent;
	}

	public void setBasiccontent(String basiccontent) {
		this.basiccontent = basiccontent;
	}

	public String getBasiccontent(int length) {
		return getBasiccontent(length, "");
	}

	public String getBasiccontent(int length, String tag) {
		try {
			if (!StringUtils.isNotBlank(tag)) {
				tag = "...";
			}
			return SubString.substring(HtmlUtil.cleanHtml(content), length, tag);
		} catch (Exception e) {
		}
		return basiccontent;
	}

	private String nodestr;
	public String getNodestr() {
		return nodestr;
	}

	public void setNodestr(String nodestr) {
		this.nodestr = nodestr;
	}

	public void setScopes(Set<String> scopes) {
		this.scopes = scopes;
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

	public Set<String> getTopics() {
		try {
			String[] array = topic.split(",");
			for (String a : array) {
				if (StringUtils.isNotBlank(a)) {
					topics.add(StringUtils.trim(StringUtils.strip(a)));
				}
			}
		} catch (Exception e) {
		}
		return topics;
	}

	public void setTopics(Set<String> topics) {
		this.topics = topics;
	}

	public String existTopic(String topic) {
		try {
			Set<String> set = getTopics();
			if (set.contains(topic)) {
				return "yes";
			} else {
				return "no";
			}
		} catch (Exception e) {
			return "no";
		}
	}

	public Set<String> getTags() {
		try {
			String[] array = tag.split(",");
			for (String a : array) {
				if (StringUtils.isNotBlank(a)) {
					tags.add(StringUtils.trim(StringUtils.strip(a)));
				}
			}
		} catch (Exception e) {
		}
		return tags;
	}

	public void setTags(Set<String> tags) {
		this.tags = tags;
	}

	public String existTag(String tag) {
		try {
			Set<String> set = getTags();
			if (set.contains(tag)) {
				return "yes";
			} else {
				return "no";
			}
		} catch (Exception e) {
			return "no";
		}
	}

	public String getCleancontent() {
		try {
			cleancontent = HtmlUtil.cleanText(content);
		} catch (Exception e) {
		}
		return cleancontent;
	}

	public void setCleancontent(String cleantext) {
		this.cleancontent = cleantext;
	}

	public String getCleancontent(int length) {
		return getCleancontent(length, "");
	}

	public String getCleancontent(int length, String tag) {
		try {
			if (!StringUtils.isNotBlank(tag)) {
				tag = "...";
			}
			return SubString.substring(HtmlUtil.cleanText(content), length, tag);
		} catch (Exception e) {
		}
		return cleancontent;
	}

	public String clearhtml(String html) {
		return HtmlUtil.cleanHtml(html);
	}

	public Task() {
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

	public Syscate getSyscate() {
		return syscate;
	}

	public void setSyscate(Syscate syscate) {
		this.syscate = syscate;
	}

	public Usercate getUsercate() {
		return usercate;
	}

	public void setUsercate(Usercate usercate) {
		this.usercate = usercate;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
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

	public String getTopic() {
		return this.topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getBanner() {
		return banner;
	}

	public void setBanner(String banner) {
		this.banner = banner;
	}

	public String getBrief() {
		return getBrief(100);
	}

	public String getBrief(int length) {
		return getBrief(length, "");
	}

	public String getBrief(int length, String tag) {
		try {
			if (StringUtils.isNotBlank(title)) {
				return SubString.substring(HtmlUtil.cleanText(title + "," + content), length, tag);
			} else {
				return SubString.substring(HtmlUtil.cleanText(content), length, tag);
			}
		} catch (Exception e) {
		}
		return this.brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getTag() {
		return this.tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getCsort() {
		return this.csort;
	}

	public void setCsort(Integer csort) {
		this.csort = csort;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserstatus() {
		return userstatus;
	}

	public void setUserstatus(String userstatus) {
		this.userstatus = userstatus;
	}

	public Integer getIlike() {
		return this.ilike;
	}

	public void setIlike(Integer ilike) {
		this.ilike = ilike;
	}

	public Integer getCmcount() {
		return this.cmcount;
	}

	public void setCmcount(Integer cmcount) {
		this.cmcount = cmcount;
	}

	public Integer getSharecount() {
		return sharecount;
	}

	public void setSharecount(Integer sharecount) {
		this.sharecount = sharecount;
	}

	public Integer getReadcount() {
		return readcount;
	}

	public void setReadcount(Integer readcount) {
		this.readcount = readcount;
	}

	public String getSecurity() {
		return security;
	}

	public void setSecurity(String security) {
		this.security = security;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Timestamp getCtime() {
		return this.ctime;
	}

	public void setCtime(Timestamp ctime) {
		this.ctime = ctime;
	}

	public Integer getUsercount() {
		return usercount;
	}

	public void setUsercount(Integer usercount) {
		this.usercount = usercount;
	}

	public Integer getInfocount() {
		return infocount;
	}

	public void setInfocount(Integer infocount) {
		this.infocount = infocount;
	}

	public Integer getPhotocount() {
		return photocount;
	}

	public void setPhotocount(Integer photocount) {
		this.photocount = photocount;
	}

	public Integer getVideocount() {
		return videocount;
	}

	public void setVideocount(Integer videocount) {
		this.videocount = videocount;
	}

	public Timestamp getPlantime() {
		return plantime;
	}

	public void setPlantime(Timestamp plantime) {
		this.plantime = plantime;
	}

	public String getEmerlevel() {
		return emerlevel;
	}

	public void setEmerlevel(String emerlevel) {
		this.emerlevel = emerlevel;
	}

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	public Timestamp getStarttime() {
		return starttime;
	}

	public void setStarttime(Timestamp starttime) {
		this.starttime = starttime;
	}

	public Timestamp getEndtime() {
		return endtime;
	}

	public void setEndtime(Timestamp endtime) {
		this.endtime = endtime;
	}
}