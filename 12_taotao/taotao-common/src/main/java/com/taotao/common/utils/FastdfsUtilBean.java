package com.taotao.common.utils;


import org.csource.fastdfs.*;

public class FastdfsUtilBean {
    private String fastdfsConfig;
    private TrackerClient trackerClient;

    public void setFastdfsConfig(String fastdfsConfig) {
        this.fastdfsConfig = this.getClass().getResource(fastdfsConfig).getFile();

        try {
            ClientGlobal.init(this.fastdfsConfig);
            if (ClientGlobal.g_tracker_group.tracker_servers.length == 0) {
                System.err.println("Fastdfs出错:未配置 tracker_servers 地址");
                return;
            }
            trackerClient = new TrackerClient(ClientGlobal.g_tracker_group);
        } catch (Exception e) {
            System.err.println("Fastdfs出错" + e.getMessage());
        }
    }

    private StorageClient getStorageClient() throws Exception {
        TrackerServer trackerServer = trackerClient.getConnection();
        StorageServer storageServer = null;
        return new StorageClient(trackerServer, storageServer);
    }

    public String upload_file(byte[] file_buff, String file_ext_name) throws Exception {
        String[] strings = getStorageClient().upload_file(file_buff, 0, file_buff.length, file_ext_name, null);
        if (strings.length != 2) {
            System.err.println("Fastdfs出错:文件上传失败");
            throw new Exception("文件上传失败");
        }

        return strings[0] + "/" + strings[1];
    }

    public String upload_file(String local_filename, String file_ext_name) throws Exception {
        String[] strings = getStorageClient().upload_file(local_filename, file_ext_name, null);
        if (strings.length != 2) {
            System.err.println("Fastdfs出错:文件上传失败");
            throw new Exception("文件上传失败");
        }

        return strings[0] + "/" + strings[1];
    }
}
