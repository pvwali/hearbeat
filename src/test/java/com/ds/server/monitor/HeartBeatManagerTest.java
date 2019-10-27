package com.ds.server.monitor;

import org.junit.Test;

import com.ds.heartbeat.HeartBeatManager;
import com.ds.heartbeat.HostInfo;

public class HeartBeatManagerTest {
	
	@Test
	public void testMockClient() throws InterruptedException {
		HostInfo one = new HostInfo("One");
		HeartBeatManager.getInstance().register(one);

		HostInfo two = new HostInfo("Two");
		HeartBeatManager.getInstance().register(two);
		
		HostInfo three = new HostInfo("Three");
		HeartBeatManager.getInstance().register(three);
		
		HeartBeatManager.getInstance().unRegister(one);
		HeartBeatManager.getInstance().unRegister(two);
		HeartBeatManager.getInstance().unRegister(three);	
		System.out.print("Alls well");
	}
}
