import java.net.*;
import java.io.*;
import java.util.*;
public class Client{

  private Socket clientSocekt;

  private OutputStream os;
  private OutputStreamWriter osw;
  private BufferedWriter bw;

  private InputStream is;
  private InputStreamReader ir;
  private BufferedReader br;

  private Scanner scan;

  public Client(){
    this.connectServer();
    scan = new Scanner(System.in);
    this.send_recv();

  }
  public void connectServer(){
    try{
      clientSocekt = new Socket("localhost",1111);

      os = clientSocekt.getOutputStream();
      osw = new OutputStreamWriter(os);
      bw = new BufferedWriter(osw);

      is = clientSocekt.getInputStream();
      ir = new InputStreamReader(is);
      br = new BufferedReader(ir);
    }
    catch(Exception e){
      e.printStackTrace();
    }
  }
  public String inputData(){
    return scan.nextLine();
  }

  public void send_recv(){
    try{
      while(true){
        String data = inputData();
        System.out.println("Send To SEVER : "+data);
        bw.write(data);
        bw.newLine();
        bw.flush();

        data = br.readLine();
        System.out.println("data from SERVER : "+data);
    }
    }
    catch(IOException e){
      e.printStackTrace();
      try{
        System.out.println("allClose");
        this.closeData();


      }catch(IOException es){
        es.printStackTrace();
      }
    }
  }

  public void closeData() throws IOException{
    clientSocekt.close();
    bw.close();
    br.close();
    os.close();
    osw.close();
    is.close();
    ir.close();
  }
}
