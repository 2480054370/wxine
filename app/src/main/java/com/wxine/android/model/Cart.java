package com.wxine.android.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.wxine.android.utils.Arith;

public class Cart implements Serializable {
	private static final long serialVersionUID = 1527083377180183849L;
	private List<Goods> goodses = new ArrayList<Goods>();
	private Double total = Double.valueOf(0);
	
	public void add(Goods goods) {
		boolean exist = false;
		for (int i=0; i<goodses.size(); i++) {
			Goods item = goodses.get(i);
			if (StringUtils.equals(goods.getId(), item.getId())) {
				exist = true;
				item.setNumber(item.getNumber() + goods.getNumber());
				break;
			}
		}
		if (!exist) {
			goodses.add(goods);
		}
		refresh();
	}
	
	public void update(String code, Integer number, Set<String> domains) {
		for (int i=0; i<goodses.size(); i++) {
			Goods item = goodses.get(i);
			if (StringUtils.equals(item.getPcode(), code)) {
				item.setNumber(number);
				domains.removeAll(Collections.singleton(null));
				item.setDomains(domains);
				break;
			}
		}
		refresh();
	}
	
	public void delete(String code) {
		for (int i=0; i<goodses.size(); i++) {
			Goods item = goodses.get(i);
			if (StringUtils.equals(item.getPcode(), code)/* && !StringUtils.equals(item.getPcode(), "standard")*/) {
				goodses.remove(item);
				break;
			}
		}
		refresh();
	}
	
	public void refresh() {
		total = Double.valueOf(0);
		for (int i=0; i<goodses.size(); i++) {
			Goods service = goodses.get(i);
			if (null != service) {
				total = Arith.add(service.getSubtotal(), total);//累加
				Set<String> domains = service.getDomains();
				domains.removeAll(Collections.singleton(null));
				domains.removeAll(Collections.singleton(""));
				service.setDomains(domains);
			} else {
				goodses.remove(i);//删除空对象
			}
		}
	}

	public List<Goods> getGoodses() {
		return goodses;
	}

	public void setGoodses(List<Goods> goodses) {
		this.goodses = goodses;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}
}
