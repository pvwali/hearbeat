package com.ds.heartbeat.listener;

import com.ds.heartbeat.HostInfo;

public class HearBeatListener implements IHeartBeatListener{
	public void onRegistered(HostInfo host) {
		System.out.println("Registered"+host);
	}
	public void onDead(HostInfo host) {
		System.out.println("Dead"+host);				
	}
	public void onUnRegistered(HostInfo host) {
		System.out.println("UnRegistered"+host);
	}
	@Override
	public void onAlive(HostInfo host) {
		System.out.println("Alive"+host);				
	}
}
