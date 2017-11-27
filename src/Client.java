import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;


public class Client {
	public static void main(String[] args) throws IOException {
		String data = null ;
        switch(args[3]) {
        case "put":data = (args[3]+" "+args[4]+" "+args[5]);break;
        case "del":data = (args[3]+" "+args[4]);break;
        default:data = (args[3]);break;
        }
        		Socket socket = new Socket(args[1], Integer.parseInt(args[2]));
            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os);
            pw.write(data);
            pw.flush();//刷新缓存，向服务器端输出信息
            socket.shutdownOutput();//关闭输出流
            
            //获取输入流，接收服务器端响应信息
            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "GBK"));
            String datain = null;
            while((datain=br.readLine())!= null){
                System.out.println("我是客户端，服务器端提交信息为："+datain);
            }

            socket.close();
        

	}
}
