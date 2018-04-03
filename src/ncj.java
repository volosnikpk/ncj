import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.net.*;

public class ncj {

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: ncj <ip> <port> <filename>");
            return;
        }

        try {
            Socket l_client_socket = new Socket(args[0], Integer.parseInt(args[1]));
            FileReader l_file_reader = new FileReader(args[2]);
            BufferedReader l_buffered_reader = new BufferedReader(l_file_reader);
            InputStream l_buffer_in = l_client_socket.getInputStream();
            OutputStream l_buffer_out = l_client_socket.getOutputStream();
            String l_text_buffer = l_buffered_reader.readLine();
            byte[] l_bytes_received = new byte[10000];

            System.out.println("Sent RAW [" + l_text_buffer + "]");
            System.out.println("Sent HEX [" + DatatypeConverter.printHexBinary(l_text_buffer.getBytes()).substring(0, l_text_buffer.getBytes().length*2) + "]");
            l_buffer_out.write(l_text_buffer.getBytes());

            int l_bytes_read = l_buffer_in.read(l_bytes_received);
            System.out.println("Recv RAW [" + new String(l_bytes_received).trim() + "]");
            System.out.println("Recv HEX [" + DatatypeConverter.printHexBinary(l_bytes_received).substring(0, l_bytes_read*2) + "]");
            l_client_socket.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
