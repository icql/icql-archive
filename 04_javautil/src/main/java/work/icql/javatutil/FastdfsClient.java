package work.icql.javatutil;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

/**
 * @author icql
 * @version 1.0
 * @date 2018/10/31 11:47
 * @Title FastdfsClient
 * @Description FastdfsClient
 */
public class FastdfsClient {
    private TrackerClient trackerClient;

    /**
     * 初始化FastdfsClient
     *
     * @param trackerServers 例如："10.0.11.245:22122,10.0.11.246:22122"
     *                       *                       server的IP和端口用冒号':'分隔
     *                       *                       server之间用逗号','分隔
     * @throws Exception
     */
    public FastdfsClient(String trackerServers) {
        try {
            ClientGlobal.initByTrackers(trackerServers);
            if (ClientGlobal.g_tracker_group.tracker_servers.length == 0) {
                System.err.println("Fastdfs出错:未配置 tracker_servers 地址");
                return;
            }
            trackerClient = new TrackerClient(ClientGlobal.g_tracker_group);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param fileBuff    文件字节数组
     * @param fileExtName 文件后缀名，不带.
     * @return
     * @throws Exception
     */
    public String upload(byte[] fileBuff, String fileExtName) {
        return upload(fileBuff, null, fileExtName);
    }

    /**
     * @param fileName    文件路径
     * @param fileExtName 文件后缀名，不带.
     * @return
     * @throws Exception
     */
    public String upload(String fileName, String fileExtName) {
        return upload(null, fileName, fileExtName);
    }

    private String upload(byte[] fileBuff, String fileName, String fileExtName) {
        String url = null;
        try {
            TrackerServer trackerServer = trackerClient.getConnection();
            StorageClient storageClient = new StorageClient(trackerServer, null);

            String[] strings = fileBuff == null ? storageClient.upload_file(fileName, fileExtName, null) :
                    storageClient.upload_file(fileBuff, 0, fileBuff.length, fileExtName, null);
            if (strings.length != 2) {
                System.err.println("Fastdfs出错:文件上传失败");
                throw new Exception("文件上传失败");
            }
            url = strings[0] + "/" + strings[1];
        } catch (Exception e) {
            e.printStackTrace();
        }
        return url;
    }
}