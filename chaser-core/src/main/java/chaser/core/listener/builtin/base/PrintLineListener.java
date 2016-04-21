package chaser.core.listener.builtin.base;

import chaser.core.listener.Listener;

public class PrintLineListener implements Listener<Object, Object> {

	@Override
	public Object process(Object obj) {
		if (obj != null) {
			System.out.println(obj);
		}
		return obj;
	}

}
