package com.ds.heartbeat.monitor;

import java.util.Random;

import com.ds.heartbeat.HeartBeatEvent;
import com.ds.heartbeat.HeartBeatState;
import com.ds.heartbeat.HostInfo;

public class MockMonitor implements IMonitor<HeartBeatEvent> {
	Random r = new Random();
	
	public HeartBeatEvent monitor(HostInfo host) {
		HeartBeatEvent hb = new HeartBeatEvent(
			HeartBeatState.values()[r.nextInt(2)]);
		return hb;
	}
}
