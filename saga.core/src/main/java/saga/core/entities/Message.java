package saga.core.entities;

import java.util.UUID;

public final class Message {
	private String id;
	private String content;
	private int command;
	private String route;
	private boolean isAsync;
	private boolean isDone;
	
	public static final int MARGIN = 1000;

	public Message() {
		
	}

	public Message(String content, int command, String route) {
		this.id = genId();

		this.content = content;
		this.command = command;
		this.route = route;
		
		this.isAsync = false;
		this.isDone = false;
	}
	
	public Message(String content, int command, String route, boolean isAsync) {
		this.id = genId();

		this.content = content;
		this.command = command;
		this.route = route;
		this.isAsync = isAsync;

		this.isDone = false;

	}

	public String genId() {
		return UUID.randomUUID().toString();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public boolean isDone() {
		return isDone;
	}

	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}

	public boolean isAsync() {
		return isAsync;
	}

	public void setAsync(boolean isAsync) {
		this.isAsync = isAsync;
	}

	public int getCommand() {
		return command;
	}

	public void setCommand(int command) {
		this.command = command;
	}
	
	public void setRollbackCommand(int command) {
		this.command = command + MARGIN;
	}

}