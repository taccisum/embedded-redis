package redis.embedded.ports;

import redis.embedded.PortProvider;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * @author tac
 * @since 08/10/2018
 */
public class FreePortProvider implements PortProvider {
    private Set<Integer> usingPorts = new HashSet<Integer>();

    private int min = 40000;
    private int max = 60000;
    private Random r = new Random();

    @Override
    public int next() {
        boolean flag = false;
        int port = 0;
        while (!flag) {
            port = r.nextInt(max - min) + min;
            if (usingPorts.contains(port)) {
                continue;
            }
            if (isFreePort(port)) {
                flag = true;
            } else {
                usingPorts.add(port);
            }
        }
        return port;
    }

    public static boolean isFreePort(int port) {
        boolean flag = true;
        try {
            InetAddress theAddress = InetAddress.getByName("127.0.0.1");
            Socket socket = new Socket(theAddress, port);
            flag = false;
        } catch (IOException ignored) {
        }
        return flag;
    }
}
