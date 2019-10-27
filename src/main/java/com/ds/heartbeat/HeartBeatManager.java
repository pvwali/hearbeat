package com.ds.heartbeat;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.ds.heartbeat.listener.HearBeatListener;
import com.ds.heartbeat.listener.IHeartBeatListener;
import com.ds.heartbeat.monitor.HeartBeatMonitor;

public class HeartBeatManager implements Observer<HostInfo, HeartBeatEvent> {
	Map<String, HeartBeatMonitor> heartBeatMonitors;
	ExecutorService executorSvc;
	IHeartBeatListener listener;
	
	private static HeartBeatManager instance;
	public static HeartBeatManager getInstance() {
		if (instance == null) {
			instance = new HeartBeatManager();
		}
		return instance;
	}
	
	private HeartBeatManager() {
		heartBeatMonitors = new ConcurrentHashMap<String, HeartBeatMonitor>();
		executorSvc = Executors.newCachedThreadPool();
		listener = new HearBeatListener();
	}
	
	/**
	 * Registers the host to be monitored
	 * @param host
	 */
	public void register(HostInfo host) {
		if (!heartBeatMonitors.containsKey(host.id)) {
			HeartBeatMonitor hbm = new HeartBeatMonitor(host, this);
			heartBeatMonitors.put(host.id, hbm);
			
			listener.onRegistered(host);
			executorSvc.submit(hbm);
		}
	}

	/**
	 * UnRegisters the host to be monitored
	 * @param host
	 */
	public void unRegister(HostInfo host) {
		if (heartBeatMonitors.containsKey(host.id)) {
			HeartBeatMonitor hbm = heartBeatMonitors.remove(host.id);
			hbm.terminate();
			
			listener.onUnRegistered(host);
		}
	}
	
	/**
	 * Gets an updated info on one of the host being monitored
	 */
	public void update(HostInfo host, HeartBeatEvent event) {
		switch (event.curState) {
			case ALIVE: 
				listener.onAlive(host);
				break;
			case DEAD:
				listener.onDead(host);
				break;				
		}
	}
}
