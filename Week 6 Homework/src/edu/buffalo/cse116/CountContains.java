package edu.buffalo.cse116;

import java.util.Iterator;
import java.util.List;

public class CountContains<E> {
	public int countContains(Iterator<E> it, Object obj) {
		int count = 0;
		if (obj == null) {
			while (it.hasNext()) {
				if (it.next() == null) {
					count++;
				}
			}
		} else {
			while (it.hasNext()) {
				Object o = it.next();
				if (o!=null && o.equals(obj)) {
					count++;
				}
			}

		}
		return count;
	}

	public int countContains(List<E> al, Object obj) {
		int count = 0;
		if (obj == null) {
			for (int i = 0; i < al.size(); i++) {
				if (al.get(i) == null) {
					count++;
				}
			}
		} else {
			for (int i = 0; i < al.size(); i++) {
				Object o = al.get(i);
				if (o!=null && o.equals(obj)) {
					count++;
				}
			}
		}
		return count;
	}
}