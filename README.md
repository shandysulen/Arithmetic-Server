# Arithmetic Server
Arithmetic Server is a Java client/server program that allows up to one client at a time to connect to the server, in which the client sends arithmetic inputs for the server to compute and relay the results back to the client. Extensive error handling is implemented server-side to filter out bad inputs and return error codes to the client. The client has the ability to end its connection with the server as well terminate the server.

### Getting Started
___
These instructions will get you a copy of the project up and running on local or remote machines.

##### Prerequisites
Arithmetic Server has been tested to successfully work on [Java JRE 8u161](http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html) for the appropriate operating system. In order to develop and extend Arithmetic Server, you must download [Java JDK 8u161](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) for the appropriate operating system.

##### Deployment
Upon downloading the JRE (either directly or through a JDK) in the envinronment that you want to test, develop, or go live in, make sure that the absolute paths for `javac.exe` and `java.exe` are added to the `$PATH` environment variable. This will allow you to compile `.java` files into `.class` files with `javac` in any directory, just as you will be able to execute the `.class` files using the `java` interpreter.

Download `server.java` and `client.java` to your desired directories and run the following commands to compile the follows:

`$ javac server.java`

`$ javac client.java`

Follow these commands to execute the files and launch the program (be sure to always launch `server` first):

`$ java server <portNumber>`

The port number cannot exceed 65536.

`$ java client <serverURL> <portNumber>`

The port number should match the one used when launching `server`, and the server URL should be the domain name or IP address of the machine that was used to launch `server`.

### Usage
___

Upon connecting to the server, the client will receive `Hello!`. The client can now send the server the following commands:

1. add operand1 operand2 operand3 operand4
2. subtract operand1 operand2 operand3 operand4
3. multiply operand1 operand2 operand3 operand4

The third and fourth operands are optional, however, the first and second operands are required.

If you wish to close the client connection, simply send `bye` to the server. The client will then receive `exit` and the connection will be closed.

If you wish to shut down the server, simply send `terminate` to the server. The client will then receive `exit` and the connection will be closed and the server will be terminated.


### Authors
___
[Shandy Sulen](https://github.com/shandysulen)

### Bugs & Feature Requests
___
Anyone is welcome to contribute to this repository and suggest future potential features.

For any bugs that you may find within the program, please open an issue within GitHub.
