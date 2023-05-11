package pers.lxl.mylearnproject.integratedapplication.systemabout;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: lxl
 * @Date: 2023/05/05/11:23
 * @Description:
 */
public class GetSystem {
    public static void main(String[] args) throws UnknownHostException {
        Properties props = System.getProperties();

        System.out.println(InetAddress.getLocalHost().getHostName());
        System.out.println(props.getProperty("os.name"));
        System.out.println(props.getProperty("os.arch"));
        System.out.println(props.getProperty("user.dir"));
    }
}
