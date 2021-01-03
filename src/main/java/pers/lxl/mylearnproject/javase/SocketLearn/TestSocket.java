package pers.lxl.mylearnproject.javase.SocketLearn;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class TestSocket {

//    其他内容查看一下文件学习 pers.lxl.mylearnproject.programbase.net

    public static void main(String[] args) throws IOException {
        //获取IP地址
        InetAddress host = InetAddress.getLocalHost();
        String ip =host.getHostAddress();
        System.out.println("本机ip地址：" + ip);
//        Ping判断一个地址是否能够到达
//        借助 Runtime.getRuntime().exec() 可以运行一个windows的exe程序
        Process p = Runtime.getRuntime().exec("ping " + "192.168.2.106");
        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            if (line.length() != 0)
                sb.append(line + "\r\n");
        }
        System.out.println("本次指令返回的消息是：");
        System.out.println(sb.toString());
    }
}
