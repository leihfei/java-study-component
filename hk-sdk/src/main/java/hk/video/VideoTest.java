package hk.video;

import com.lnlr.jna.HCNetSDK;
import com.sun.jna.NativeLong;
import com.sun.jna.ptr.IntByReference;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author:leihfei
 * @description:
 * @date:Create in 16:02 2018/10/25
 * @email:leihfein@gmail.com
 */
@Component
public class VideoTest {

    @Scheduled(cron = "0/5 * * * * *")
    public Object init() {
        boolean initSuc = HCNetSDK.INSTANCE.NET_DVR_Init();
        if (initSuc != true) {
            System.out.println("系统初始化失败!");
            int i = HCNetSDK.INSTANCE.NET_DVR_GetLastError();
            System.out.println("错误失败码：" + i);
        }
        boolean b = HCNetSDK.INSTANCE.NET_DVR_SetConnectTime(5000, 4);
        if (b == false) {
            System.out.println("设置超时时间失败");
            int i = HCNetSDK.INSTANCE.NET_DVR_GetLastError();
            System.out.println("错误码:" + i);
        }
        // 用户注册
        HCNetSDK.NET_DVR_DEVICEINFO_V30 ipDeviceInfo = new HCNetSDK.NET_DVR_DEVICEINFO_V30();
        // 用户句柄
        NativeLong lUserID = HCNetSDK.INSTANCE.NET_DVR_Login_V30("192.168.1.222", (short) 8000, "admin", "xzl_123456", ipDeviceInfo);
        long userID = lUserID.longValue();
        if (userID == -1) {
            System.out.println("注册失败!");
        } else {
            System.out.println("登录成功!");
        }
        // 获取设备信息
        byte byChanNum = ipDeviceInfo.byChanNum;
        System.out.println("设备模拟通道个数:" + byChanNum);
        byte byIPChanNum = ipDeviceInfo.byIPChanNum;
        System.out.println("设备最大数字通道个数: " + byIPChanNum);
        // 获取ip通道参数信息
        createDeviceTree(lUserID, ipDeviceInfo);
        return null;
    }

    private void createDeviceTree(NativeLong userId, HCNetSDK.NET_DVR_DEVICEINFO_V30 ipDeviceInfo) {
        //获取IP接入配置参数
        IntByReference ibrBytesReturned = new IntByReference(0);
        HCNetSDK.NET_DVR_IPPARACFG ipparacfg = new HCNetSDK.NET_DVR_IPPARACFG();
        boolean bRet = HCNetSDK.INSTANCE.NET_DVR_GetDVRConfig(userId, HCNetSDK.NET_DVR_GET_IPPARACFG, new NativeLong(0), ipparacfg.getPointer(), 1, ibrBytesReturned);
        if (bRet == false) {
            System.out.println("设备不支持Ip通道");
        } else {
            // 设备支持ip通道
            for (int i = 0; i < HCNetSDK.MAX_IP_CHANNEL; i++) {
                if (ipparacfg.struIPChanInfo[i].byEnable == 1) {
                    System.out.println("通道名称: " + ipDeviceInfo.byStartChan);
                }
            }
        }
    }
}
