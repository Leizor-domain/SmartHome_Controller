/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package distsys.smarthome3;

/**
 *
 * @author macbookm2chip
 */
// Import core gRPC classes needed to start and manage the server
import io.grpc.Server;
import io.grpc.ServerBuilder;

// Import the generated gRPC service base classes (used to bind implementations)
import smarthome.climate.ClimateServiceGrpc;
import smarthome.lighting.LightingServiceGrpc;
import smarthome.security.SecurityServiceGrpc;

public class SmartHomeServer {
    public static void main(String[] args) {
        try {
            // Step 1: Define the port number where the server will listen for gRPC requests
            int port = 50051;

            // Step 2: Build and configure the gRPC server
            Server server = ServerBuilder.forPort(port) // Bind server to defined port
                    // Register the service implementations
                    .addService(new ClimateServiceImpl())     // Bind climate service implementation
                    .addService(new LightingServiceImpl())    // Bind lighting service implementation
                    .addService(new SecurityServiceImpl())    // Bind security service implementation
                    .build()                                  // Build the server
                    .start();                                 // Start listening for client connections

            // Step 3: Print to console that the server is now running
            System.out.println("SmartHome gRPC Server started on port " + port);

            // Step 4: Keep the server process running and block the main thread
            server.awaitTermination(); // This keeps the server alive indefinitely

        } catch (Exception e) {
            // Handle any server startup errors
            System.err.println("Server error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
