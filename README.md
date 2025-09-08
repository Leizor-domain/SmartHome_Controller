SmartHome Controller

A modular IoT Controller System built with Java gRPC, designed to manage climate, lighting, and security services in a smart home environment. The system leverages advanced communication patterns, authentication, and GUI interaction to provide a seamless automation experience.

✨ Features

Climate Control

Adjust temperature & monitor climate status

Bi-directional streaming for live updates

Lighting Management

Toggle lights, adjust brightness, and monitor energy usage

Client-streaming for batch commands

Security Service

Lock/unlock doors

Motion detection & event logs

Server-streaming for live alerts

gRPC Communication

Implements all four RPC styles (Unary, Server Streaming, Client Streaming, Bi-Directional Streaming)

Authentication & Security

API Key / JWT authentication

Secure communication between services

GUI Integration

Java Swing (JFrame + JInternalFrame) user interface

Real-time log console with auto-scrolling

Tabbed control panels for each service

🛠️ Tech Stack

Backend: Java, gRPC, Protocol Buffers

Frontend: Java Swing (NetBeans GUI)

Security: JWT / API Keys

Data Handling: Logging, analytics, error tracking

Build Tool: Maven

📂 Project Structure
SmartHome-Controller/
 ├── proto/                # Unified .proto definitions
 ├── src/main/java/
 │    ├── smartclimate/    # Climate service implementation
 │    ├── smartlighting/   # Lighting service implementation
 │    ├── smartsecurity/   # Security service implementation
 │    ├── gui/             # GUI controller (Swing-based)
 │    └── common/          # Shared utilities, auth, logging
 ├── resources/            # Configs, keys, GUI assets
 └── README.md

🚀 Getting Started
Prerequisites

Java 17+

Maven 3.8+

NetBeans or IntelliJ IDEA (recommended)

Installation
# Clone the repository
git clone https://github.com/Leizor-domain/SmartHome-Controller.git
cd SmartHome-Controller

# Compile and package
mvn clean install

Running the Server
# Start the gRPC server
mvn exec:java -Dexec.mainClass="com.smarthome.Server"

Running the GUI Client
# Launch Swing-based GUI client
mvn exec:java -Dexec.mainClass="com.smarthome.gui.ClientApp"

📊 Roadmap

 Core services (Climate, Lighting, Security)

 gRPC invocation patterns

 GUI integration

 Add voice assistant integration

 Expand to support additional IoT devices (e.g., smart appliances)

 Cloud deployment (Docker + Kubernetes)

 Mobile companion app (Flutter)

📸 Screenshots



📜 License

This project is licensed under the MIT License. See LICENSE
 for details.

👤 Author

Olalekan Ade-Ojo (Leizor)
