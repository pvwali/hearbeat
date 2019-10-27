package com.ds.heartbeat;

public class HeartBeatEvent {	
	HeartBeatState curState;
	
	public HeartBeatEvent(HeartBeatState state) {
		curState = state;
	}
}
