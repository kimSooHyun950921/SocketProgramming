import java.net.*;
import java.io.*;
import java.util.Scanner;
public class Server{
  private ServerSocket server;
  private Socket socket;

  private InputStream input_data;
  private InputStreamReader reader;
  private BufferedReader bufferedReader;

  private OutputStream os;
  private OutputStreamWriter osw;
  private BufferedWriter bw;

  private Scanner scan ;

  public Server(){
    System.out.println("server Stert");
    this.connectServer();
    scan = new Scanner(System.in);
    this.read_write();



  }
  public void connectServer(){      System.out.println("서버 운영중");

    try{
      server = new ServerSocket(1111);
      System.out.println("서버 운영중");
      socket = server.accept();
      System.out.println("접속자 정보 : "+ socket.toString());

      input_data = socket.getInputStream();
      reader = new InputStreamReader(input_data);
      bufferedReader = new BufferedReader(reader);

      os = socket.getOutputStream();
      osw = new OutputStreamWriter(os);
      bw = new BufferedWriter(osw);


    }
    catch(IOException e){
      e.printStackTrace();
    }

  }
  public String inputData(){
    String s = scan.nextLine();
    return s;
  }
  public void read_write(){
    try{
      while(true){
        String data = null;
        data = bufferedReader.readLine();
        System.out.println("클라이언트로부터 받은 데이터 - "+data);
        data = inputData();
        System.out.println("클라이언트로부터 보낼 데이터 - "+data);

        bw.write(data);
        bw.newLine();
        bw.flush();
      }
    }
    catch(java.lang.NullPointerException e){
      e.printStackTrace();
    }
    catch(IOException e){
      e.printStackTrace();
      try{
        System.out.println("allClose");
        socket.close();
        bw.close();
        osw.close();
        os.close();
        input_data.close();
        reader.close();
        bufferedReader.close();

      }
      catch(IOException es){
        es.printStackTrace();;
      }

    }


  }

  }
