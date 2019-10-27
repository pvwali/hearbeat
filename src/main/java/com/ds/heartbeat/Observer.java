package com.ds.heartbeat;

public interface Observer<E, D> {
	public void update(E e, D d);
}
