package com.game.evolution.websocket;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.game.evolution.domain.Userinfo;

@ServerEndpoint(value = "/websocketddz", configurator = GetHttpSessionConfigurator.class)
public class WebSocketDDZ {
	/**
	 * 连接对象集合
	 */
	private static final Set<WebSocketDDZ> connections = new CopyOnWriteArraySet<WebSocketDDZ>();
	// 角色名称
	private String roleName;
	// 用户未知
	private String location;
	// 用户操作
	private String operation;

	/**
	 * WebSocket Session
	 */
	private Session session;

	public WebSocketDDZ() {
	}

	/**
	 * 打开连接
	 * 
	 * @param session
	 * @param roleName
	 */
	@OnOpen
	public void onOpen(Session session, EndpointConfig config) {
		HttpSession httpSession = (HttpSession) config.getUserProperties().get(
				HttpSession.class.getName());
		Userinfo userinfo = (Userinfo) httpSession.getAttribute("userinfo");
		System.out.println(userinfo.getTruename());
		this.session = session;
		this.location = "ddz";
		this.roleName = userinfo.getTruename();

		connections.add(this);
		String message = String.format("System> %s %s", this.roleName, " 上线了.");
		WebSocketDDZ.broadCast(message, this.location);
	}

	/**
	 * 关闭连接
	 */
	@OnClose
	public void onClose() {
		connections.remove(this);
		String message = String
				.format("System> %s, %s", this.roleName, " 下线了.");
		WebSocketDDZ.broadCast(message, this.location);
	}

	/**
	 * 接收信息
	 * 
	 * @param message
	 * @param roleName
	 */
	@OnMessage
	public void onMessage(String message) {
		WebSocketDDZ.broadCast("" + this.roleName + ">" + message,
				this.location);
	}

	/**
	 * 错误信息响应
	 * 
	 * @param throwable
	 */
	@OnError
	public void onError(Throwable throwable) {
		System.out.println(throwable.getMessage());
	}

	/**
	 * 发送或广播信息
	 * 
	 * @param message
	 */
	private static void broadCast(String message, String location) {
		for (WebSocketDDZ websocketddz : connections) {
			try {
				synchronized (websocketddz) {
					if (websocketddz.location.equals(location)) {
						websocketddz.session.getBasicRemote().sendText(message);
					}

				}
			} catch (IOException e) {
				connections.remove(websocketddz);
				try {
					websocketddz.session.close();
				} catch (IOException e1) {
				}
				if (websocketddz.location.equals(location)) {
					WebSocketDDZ.broadCast(String.format("System> %s %s",
							websocketddz.roleName, " has bean disconnection."),
							location);// 问题点
				}
			}
		}
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public static Set<WebSocketDDZ> getConnections() {
		return connections;
	}

}