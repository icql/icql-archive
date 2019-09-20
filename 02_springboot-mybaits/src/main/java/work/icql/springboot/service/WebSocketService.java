package work.icql.springboot.service;

/**
 * @author icql
 * @version 1.0
 * @date 2019/3/31 21:07
 * @Title WebSocketService
 * @Description WebSocketService
 */
public interface WebSocketService {
    /**
     * 广播：发给所有在线用户
     *
     * @param msg
     */
    void sendByBroadcast4Type1(String msg);

    /**
     * 点对点通知
     *
     * @param id
     * @param msg
     */
    void sendByPoint4Type1(String id, String msg);
}
