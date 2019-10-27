package com.ds.heartbeat.listener;

import com.ds.heartbeat.HostInfo;

public interface IHeartBeatListener {
	public void onRegistered(HostInfo host);
	public void onDead(HostInfo host);
	public void onUnRegistered(HostInfo host);
	public void onAlive(HostInfo host);
}
