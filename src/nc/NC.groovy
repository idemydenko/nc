import javax.swing.ToolTipManager.outsideTimerAction;

if (args.length != 2) {
    println "Usage nc [destination] [port]";
    System.exit(1);
}

def String host = args[0];
def int port = Integer.parseInt(args[1]);
println host + ":" + port;

BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
if (!br.ready()) {
    println "no command found";
    System.exit(1);
}

def String command = br.readLine();
br.close();
println command


def socket = new Socket(host, port);
socket.setSoTimeout(10000);
socket.withCloseable { inStream, outStream ->
    def reader = inStream.newReader();
    String hello = reader.readLine();
    if (hello != "") {
        outStream << command;
    }
}
