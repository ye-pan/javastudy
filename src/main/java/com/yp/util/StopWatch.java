package com.yp.util;

import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.List;

/**
 * springframework StopWatch.class
 */
public class StopWatch {
	private final String id;
	private boolean keepTaskList = true;
	private final List<TaskInfo> taskList = new LinkedList<>();
	private long startTimeMillis;
	private String currentTaskName;
	private TaskInfo lastTaskInfo;
	private int taskCount;
	private long totalTimeMillis;
	
	public StopWatch() {
		this("");
	}
	
	public StopWatch(String id) {
		this.id = id;
	}
	
	public String getId() {
		return this.id;
	}
	
	public void setKeepTaskList(boolean keepTaskList) {
		this.keepTaskList = keepTaskList;
	}
	
	public void start() {
		start("");
	}
	
	public void start(String taskName) {
		if(this.currentTaskName != null) {
			throw new IllegalStateException("Can't start StopWatch: it's already running");
		}
		this.currentTaskName = taskName;
		this.startTimeMillis = System.currentTimeMillis();
	}
	
	public void stop() {
		if(this.currentTaskName == null) {
			throw new IllegalStateException("Can't stop StopWatch: it's not running");
		}
		long lastTime = System.currentTimeMillis() - this.startTimeMillis;
		this.totalTimeMillis += lastTime;
		this.lastTaskInfo = new TaskInfo(this.currentTaskName, lastTime);
		if(this.keepTaskList) {
			this.taskList.add(this.lastTaskInfo);
		}
		++this.taskCount;
		this.currentTaskName = null;
	}
	
	public boolean isRunning() {
		return (this.currentTaskName != null);
	}
	
	public String currentTaskName() {
		return this.currentTaskName;
	}
	
	public long getLastTaskTimeMillis() {
		if(lastTaskInfo == null) {
			throw new IllegalStateException("No tasks run: can't get last task interval");
		}
		return this.lastTaskInfo.getTimeMillis();
	}
	
	public String getLastTaskName() {
		if(lastTaskInfo == null) {
			throw new IllegalStateException("No tasks run: can't get last task name");
		}
		return lastTaskInfo.getTaskName();
	}
	
	public TaskInfo getLastTaskInfo() {
		if(this.lastTaskInfo == null) {
			throw new IllegalStateException("No tasks run: can't get last task info");
		}
		return this.lastTaskInfo;
	}
	
	public long getTotalTimeMillis() {
		return this.totalTimeMillis;
	}
	
	public double getTotalTimeSeconds() {
		return this.totalTimeMillis / 1000.0;
	}
	
	public int getTaskCount() {
		return this.taskCount;
	}
	
	public TaskInfo[] getTaskInfo() {
		if(!this.keepTaskList) {
			throw new UnsupportedOperationException("Task info is not being kept!");
		}
		return this.taskList.toArray(new TaskInfo[0]);
	}
	
	public String shortSummary() {
		return "StopWatch '" + getId() + "': running time (millis) = " + getTotalTimeMillis();
	}
	
	public String prettyPrint() {
		StringBuilder str = new StringBuilder(shortSummary());
		str.append("\n");
		if(!this.keepTaskList) {
			str.append("No task info kept");
		} else {
			str.append("-----------------------------------------\n");
			str.append("ms     %     Task name\n");
			str.append("-----------------------------------------\n");
			NumberFormat nf = NumberFormat.getInstance();
			nf.setMinimumIntegerDigits(5);
			nf.setGroupingUsed(false);
			NumberFormat pf = NumberFormat.getInstance();
			pf.setMinimumIntegerDigits(3);
			pf.setGroupingUsed(false);
			for(TaskInfo task : getTaskInfo()) {
				str.append(nf.format(task.getTimeMillis())).append("  ");
				str.append(pf.format(task.getTimeSeconds() / getTotalTimeSeconds())).append("  ");
				str.append(task.getTaskName()).append("\n");
			}
		}
		
		return str.toString();
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		if(this.keepTaskList) {
			for(TaskInfo task : getTaskInfo()) {
				str.append("; [").append(task.getTaskName()).append("] took").append(task.getTimeMillis());
				long percent = Math.round((100.0 * task.getTimeSeconds()) / getTotalTimeSeconds());
				str.append(" = ").append(percent).append("%");
			}
		} else {
			str.append("; no task info kept");
		}
		return str.toString();
	}
	
	public static final class TaskInfo {
		private final String taskName;
		private final long timeMillis;
		TaskInfo(String taskName, long timeMillis) {
			this.taskName = taskName;
			this.timeMillis = timeMillis;
		}
		
		public String getTaskName() {
			return this.taskName;
		}
		
		public long getTimeMillis() {
			return this.timeMillis;
		}
		
		public double getTimeSeconds() {
			return (this.timeMillis / 1000.0);
		}
	}
}
