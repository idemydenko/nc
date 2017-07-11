#!/usr/bin/env groovy

if (args.length != 2 && args.length != 3) {
    println "Usage: nc [destination] [port]";
    System.exit(1);
}

def String host = args[0];
def int port = Integer.parseInt(args[1]);
def String command = null;

BufferedReader br = new BufferedReader(new InputStreamReader(System.in))

try {
	if (br.ready()) {
		command = br.readLine();
	} else if (args.length == 3) {
		command = args[2];
	}
} finally {
	br?.close();
}

if (command == null) {
	println "no command found";
	System.exit(1);
}

def socket = new Socket(host, port);
socket.withStreams { inStream, outStream ->
    def reader = inStream.newReader();
    String res = reader.readLine();
	println res;
    if (res != "") {
        outStream << command + "\n";
    }
	res = reader.readLine();
	println res;
}

socket.close();