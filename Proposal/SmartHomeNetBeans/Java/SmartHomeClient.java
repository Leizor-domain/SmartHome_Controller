/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package distsys.smarthome3;

/**
 *
 * @author macbookm2chip
 */
import io.grpc.Server;
import io.grpc.ServerBuilder;
import smarthome.climate.ClimateServiceGrpc;
import smarthome.lighting.LightingServiceGrpc;
import smarthome.security.SecurityServiceGrpc;

public class SmartHomeServer {
    public static void main(String[] args) {
        try {
            // Define server port
            int port = 50051;

            // Build and start gRPC server
            Server server = ServerBuilder.forPort(port)
                    .addService(new ClimateServiceImpl())
                    .addService(new LightingServiceImpl())
                    .addService(new SecurityServiceImpl())
                    .build()
                    .start();

            System.out.println("SmartHome gRPC Server started on port " + port);

            // Keep server running
            server.awaitTermination();

        } catch (Exception e) {
            System.err.println("Server error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
}
