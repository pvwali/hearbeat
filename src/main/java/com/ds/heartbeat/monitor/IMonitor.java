package com.ds.heartbeat.monitor;

import com.ds.heartbeat.HostInfo;

public interface IMonitor<R> {
	public R monitor(HostInfo host);
}
