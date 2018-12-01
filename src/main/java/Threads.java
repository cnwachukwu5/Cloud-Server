import java.io.DataInputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Threads implements Runnable{

    private final Socket cacheNode;

    public Threads(Socket cacheNode){

        this.cacheNode = cacheNode;
    }

    @Override
    public void run() {
        try {
            while(true){
                FeedBackMechanism feedBackMechanism = new FeedBackMechanism();
                PrintWriter output = new PrintWriter(cacheNode.getOutputStream(), true);
                DataInputStream fromCacheNodes = new DataInputStream(cacheNode.getInputStream());//Read from input

                String server_status = feedBackMechanism.currentNodeStatus();
                int status_duration = feedBackMechanism.duration_of_Status();

                System.out.println("Server status: " + server_status);
                System.out.println("Duration: " + status_duration);

                long start_Duration_Monitor = System.currentTimeMillis();
                long end_Duration_Monitor = 0;

                output.println(server_status+"\n");
                Thread.sleep(status_duration);
//                while ((end_Duration_Monitor - start_Duration_Monitor) <= status_duration) {
//                    output.println(server_status);
//
//                    end_Duration_Monitor = System.currentTimeMillis();
//                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
