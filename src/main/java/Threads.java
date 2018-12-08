import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Threads implements Runnable{

    private final Socket cacheNode;

    public Threads(Socket cacheNode){

        this.cacheNode = cacheNode;
    }

    @Override
    public void run() {
        try {
            int counter = 0;
            List<String> dataFromCacheNode = new ArrayList<>();
            while(true){
                FeedBackMechanism feedBackMechanism = new FeedBackMechanism();
                PrintWriter output = new PrintWriter(cacheNode.getOutputStream(), true);
                BufferedReader fromCacheNodes = new BufferedReader(new InputStreamReader(cacheNode.getInputStream()));//Read from input

                String server_status = feedBackMechanism.currentNodeStatus();

                int status_duration = feedBackMechanism.duration_of_Status();

                System.out.println("Server status: " + server_status);
                System.out.println("Duration: " + status_duration);

                long start_Duration_Monitor = System.currentTimeMillis();
                long end_Duration_Monitor = 0;

                output.println(server_status+"\n");

                while ((end_Duration_Monitor - start_Duration_Monitor) <= status_duration) {
                    output.println(server_status+"\n");
                    String value_Read = fromCacheNodes.readLine();

                    if(!value_Read.equals("")){
                        if(!value_Read.equals("Not actual data")){
                            counter++;
                        }
                        System.out.println("Value received: " + value_Read);
                        dataFromCacheNode.add(value_Read);
                    }



                    end_Duration_Monitor = System.currentTimeMillis();
                }
                System.out.println("Total data received: " + dataFromCacheNode.size());
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
