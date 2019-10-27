package com.ds.heartbeat.monitor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.ds.heartbeat.HeartBeatEvent;
import com.ds.heartbeat.HeartBeatManager;
import com.ds.heartbeat.HostInfo;

public class HeartBeatMonitor implements Runnable {
	HostInfo host;
	ExecutorService exeSvc;
	HeartBeatManager hbm;
	IMonitor<HeartBeatEvent> monitor;
	
	public HeartBeatMonitor(HostInfo host, HeartBeatManager hbm) {
		this.host = host;
		this.hbm = hbm;
		monitor = new MockMonitor();
		this.exeSvc = Executors.newSingleThreadExecutor();
	}
	
	/**
	 * Demon thread that runs periodically to trigger the heartBeat monitor
	 */
	public void run() {
		System.out.println("Starting monitor"+host);
		try {
			Future hbMonitor = exeSvc.submit(heartBeat());
			while (!exeSvc.isShutdown()) {
				while(!hbMonitor.isDone()) {
					Thread.sleep(1000);
				}
				hbMonitor = exeSvc.submit(heartBeat());				
			}
			hbMonitor.cancel(true);
			
		} catch (Exception e) {
		} finally {
			terminate();
		}
	}

	/**
	 * Invokes the monitor to check the heartBeat
	 * @return
	 */
	private Runnable heartBeat() {
		return () -> {  
			HeartBeatEvent heartBeat = monitor.monitor(this.host);
			this.hbm.update(host, heartBeat);					
		};
	}

	/**
	 * Terminate the executor service when no longer required
	 */
	public void terminate() {
		System.out.println("Terminated"+host);
		exeSvc.shutdownNow();
	}
}
