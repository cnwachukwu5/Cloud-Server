
import java.io.DataInputStream;
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
        try{
            //Listen for connection
            cacheNode = serverSocket.accept();

            PrintWriter output = new PrintWriter(cacheNode.getOutputStream(), true);
            DataInputStream fromCacheNodes = new DataInputStream(cacheNode.getInputStream());//Read from input

        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
