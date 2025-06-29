package in.novopay.platform_ui.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class RedisRemoteFlush {
	

	

	public static boolean flushRedisUsingJSch() {
        String user = "soumiyak"; // SSH username
        String host = "172.60.4.41"; // Remote server
        String password = "9oVYUX8%T7Z!"; // 🔐 Replace with actual password
        String redisCommand = "sudo -u tomcat /apps/redis-3.2.8/src/redis-cli FLUSHALL";

        boolean success = false;

        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(user, host, 22);
            session.setPassword(password);

            // Disable host key checking for now (optional, not for production)
            session.setConfig("StrictHostKeyChecking", "no");

            session.connect(10000); // 10 sec timeout
            System.out.println("✅ SSH connected to " + host);

            ChannelExec channel = (ChannelExec) session.openChannel("exec");
            channel.setCommand(redisCommand);

            channel.setInputStream(null);
            InputStream stdout = channel.getInputStream();
            InputStream stderr = channel.getErrStream();

            channel.connect();

            // Read output
            StringBuilder outputBuilder = new StringBuilder();
            byte[] tmp = new byte[1024];
            while (true) {
                while (stdout.available() > 0) {
                    int i = stdout.read(tmp, 0, 1024);
                    if (i < 0) break;
                    outputBuilder.append(new String(tmp, 0, i));
                }
                if (channel.isClosed()) {
                    if (stdout.available() > 0) continue;
                    break;
                }
                Thread.sleep(100);
            }

            String output = outputBuilder.toString().trim();
            System.out.println("[Redis Output] " + output);

            if (channel.getExitStatus() == 0 && output.contains("OK")) {
                System.out.println("✅ Redis FLUSHALL successful");
                success = true;
            } else {
                System.err.println("❌ Redis FLUSHALL failed");
            }

            channel.disconnect();
            session.disconnect();

        } catch (Exception e) {
            System.err.println("❌ Error during SSH/Redis flush:");
            e.printStackTrace();
        }

        return success;
    }

    public static void main(String[] args) {
        boolean result = flushRedisUsingJSch();
        System.out.println("Final status: " + (result ? "SUCCESS" : "FAILED"));
    }
}
            
            
            /*ProcessBuilder processBuilder = new ProcessBuilder(command);
            Process process = processBuilder.start();
            System.out.println("I am in the class Flush 2");

            // Output from the SSH command
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()));
            StringBuilder outputBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Redis Output: " + line);
                outputBuilder.append(line).append("\n");
                
                System.out.println("I am in the class Flush While");
            }

            // Wait for process to complete
            int exitCode = process.waitFor();
           // String output = outputBuilder.toString().trim();
            System.out.println("I am in the class Flush Wait for");
            if (exitCode == 0) {
            	System.out.println("I am in the class Flush ");
                System.out.println("✅ Redis flush successful on QA server.");
            } else {
                System.err.println("❌ Redis flush failed. Exit code: " + exitCode + , "Output: " + output);
            }

        } catch (Exception e) {
            System.err.println("❌ Exception while flushing Redis:");
            e.printStackTrace();
        }
    }
}*/