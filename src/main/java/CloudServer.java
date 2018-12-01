
import java.io.DataInputStream;
import java.io.File;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class CloudServer {

    private static ServerSocket serverSocket;

    private static void init(){
        int port = 56941;
        try{
            serverSocket = new ServerSocket(port);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {
        init();
        Socket cacheNode = null;

        while(true) {
            try {
                //Listen for connection
                System.out.println("Waiting for connection from CacheNode");
                cacheNode = serverSocket.accept();
                System.out.println("CacheNode connected");

                new Thread(new Threads(cacheNode)).start();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
