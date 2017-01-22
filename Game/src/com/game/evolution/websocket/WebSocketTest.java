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

@ServerEndpoint(value = "/websocket", configurator = GetHttpSessionConfigurator.class)
public class WebSocketTest {
	/**
	 * 连接对象集合
	 */
	private static final Set<WebSocketTest> connections = new CopyOnWriteArraySet<WebSocketTest>();
	// 角色名称
	private String roleName;
	// 所属桌号
	private String tableNum;
	// 用户操作
	private String operation;

	/**
	 * WebSocket Session
	 */
	private Session session;

	public WebSocketTest() {
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
		this.roleName = userinfo.getTruename();

		connections.add(this);
		String message = String.format("System> %s %s", this.roleName,
				" 上线了.");
		WebSocketTest.broadCast(message);
	}

	/**
	 * 关闭连接
	 */
	@OnClose
	public void onClose() {
		connections.remove(this);
		String message = String.format("System> %s, %s", this.roleName,
				" 下线了.");
		WebSocketTest.broadCast(message);
	}

	/**
	 * 接收信息
	 * 
	 * @param message
	 * @param roleName
	 */
	@OnMessage
	public void onMessage(String message) {
		WebSocketTest.broadCast("" + this.roleName + ">" + message);
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
	private static void broadCast(String message) {
		for (WebSocketTest websocketTest : connections) {
			try {
				synchronized (websocketTest) {
					websocketTest.session.getBasicRemote().sendText(message);
				}
			} catch (IOException e) {
				connections.remove(websocketTest);
				try {
					websocketTest.session.close();
				} catch (IOException e1) {
				}
				WebSocketTest.broadCast(String.format("System> %s %s",
						websocketTest.roleName, " has bean disconnection."));
			}
		}
	}

}