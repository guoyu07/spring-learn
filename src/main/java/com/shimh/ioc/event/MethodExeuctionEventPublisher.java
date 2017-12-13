package com.shimh.ioc.event;

import java.util.ArrayList;
import java.util.List;

public class MethodExeuctionEventPublisher {
	private List<MethodExecutionEventListener> listeners = new ArrayList<MethodExecutionEventListener>();

	public void methodToMonitor(){
		MethodExecutionEvent event2Publish = new MethodExecutionEvent(this,"methodToMonitor");
		publishEvent("start",event2Publish);
		
		// 执行实际的方法逻辑
		// ...
		publishEvent("end",event2Publish);
}

	private void publishEvent(String status, MethodExecutionEvent methodExecutionEvent) {

		List<MethodExecutionEventListener> copyListeners = new ArrayList<MethodExecutionEventListener>(listeners);
		
		for(MethodExecutionEventListener listener:copyListeners){
			if("start".equals(status))
				listener.onMethodBegin(methodExecutionEvent);
			else
				listener.onMethodEnd(methodExecutionEvent);
		}
	}
	
	public void addMethodExecutionEventListener(MethodExecutionEventListener listener){
		this.listeners.add(listener);
	}
	public void removeListener(MethodExecutionEventListener listener){
		if(this.listeners.contains(listener))
			this.listeners.remove(listener);
	}
	public void removeAllListeners(){
		this.listeners.clear();
	}
	public static void main(String[] args) {
		MethodExeuctionEventPublisher eventPublisher = new MethodExeuctionEventPublisher();
		eventPublisher.addMethodExecutionEventListener(new SimpleMethodExecutionEventListener());
		eventPublisher.methodToMonitor();
	}
	
}
