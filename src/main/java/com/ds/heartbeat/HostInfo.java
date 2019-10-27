package com.ds.heartbeat;

public class HostInfo {
	String id;
	
	public HostInfo(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return id;
	}
}
