package distsys.smarthome3;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import java.awt.event.ActionEvent;
import javax.swing.*;
import smarthome.climate.Climate.*;
import smarthome.climate.ClimateServiceGrpc;
import smarthome.common.Common.*;
import smarthome.lighting.Lighting.*;
import smarthome.lighting.LightingServiceGrpc;
import smarthome.security.Security.*;
import smarthome.security.SecurityServiceGrpc;

public class SmartHomeGui extends javax.swing.JFrame {

    private ManagedChannel channel;
    private ClimateServiceGrpc.ClimateServiceBlockingStub climateStub;
    private ClimateServiceGrpc.ClimateServiceStub climateAsync;
    private LightingServiceGrpc.LightingServiceBlockingStub lightingStub;
    private LightingServiceGrpc.LightingServiceStub lightingAsync;
    private SecurityServiceGrpc.SecurityServiceBlockingStub securityStub;
    private SecurityServiceGrpc.SecurityServiceStub securityAsync;

    private Auth auth = Auth.newBuilder()
            .setApiKey("valid-api-key")
            .setDeviceId("device-thermo-1")
            .build();

    private JTextField txtTargetTemp;
    private JTextArea txtClimateLog;
    private JButton btnSetTemp, btnAdjustUp, btnAdjustDown;

    private JCheckBox chkLight;
    private JSlider sliderBrightness;
    private JButton btnSetBrightness;
    private JTextArea txtLightingLog;

    private JButton btnLock, btnUnlock, btnStartCamera;
    private JTextArea txtSecurityLog;

    public SmartHomeGui() {
        initComponents();
        initGrpcClient();
    }

    private void initGrpcClient() {
        channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        climateStub = ClimateServiceGrpc.newBlockingStub(channel);
        climateAsync = ClimateServiceGrpc.newStub(channel);
        lightingStub = LightingServiceGrpc.newBlockingStub(channel);
        lightingAsync = LightingServiceGrpc.newStub(channel);
        securityStub = SecurityServiceGrpc.newBlockingStub(channel);
        securityAsync = SecurityServiceGrpc.newStub(channel);
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SmartHome GUI");

        JTabbedPane tabs = new JTabbedPane();

        // === Climate Tab ===
        JPanel panelClimate = new JPanel();
        txtTargetTemp = new JTextField(10);
        btnSetTemp = new JButton("Set Temperature");
        btnAdjustUp = new JButton("Adjust +");
        btnAdjustDown = new JButton("Adjust -");
        txtClimateLog = new JTextArea(10, 30);
        txtClimateLog.setEditable(false);
        JScrollPane scrollClimate = new JScrollPane(txtClimateLog);

        panelClimate.add(new JLabel("Target Temp:"));
        panelClimate.add(txtTargetTemp);
        panelClimate.add(btnSetTemp);
        panelClimate.add(btnAdjustUp);
        panelClimate.add(btnAdjustDown);
        panelClimate.add(scrollClimate);
        tabs.addTab("Climate", panelClimate);

        btnSetTemp.addActionListener((ActionEvent e) -> handleSetTemp());
        btnAdjustUp.addActionListener((ActionEvent e) -> adjustTemperature(1.0f));
        btnAdjustDown.addActionListener((ActionEvent e) -> adjustTemperature(-1.0f));

        // === Lighting Tab ===
        JPanel panelLighting = new JPanel();
        chkLight = new JCheckBox("Light ON");
        sliderBrightness = new JSlider(0, 100, 50);
        btnSetBrightness = new JButton("Set Brightness");
        txtLightingLog = new JTextArea(10, 30);
        txtLightingLog.setEditable(false);
        JScrollPane scrollLighting = new JScrollPane(txtLightingLog);

        panelLighting.add(chkLight);
        panelLighting.add(new JLabel("Brightness:"));
        panelLighting.add(sliderBrightness);
        panelLighting.add(btnSetBrightness);
        panelLighting.add(scrollLighting);
        tabs.addTab("Lighting", panelLighting);

        btnSetBrightness.addActionListener((ActionEvent e) -> handleSetBrightness());

        // === Security Tab ===
        JPanel panelSecurity = new JPanel();
        btnLock = new JButton("Lock Door");
        btnUnlock = new JButton("Unlock Door");
        btnStartCamera = new JButton("Start Camera Feed");
        txtSecurityLog = new JTextArea(10, 30);
        txtSecurityLog.setEditable(false);
        JScrollPane scrollSecurity = new JScrollPane(txtSecurityLog);

        panelSecurity.add(btnLock);
        panelSecurity.add(btnUnlock);
        panelSecurity.add(btnStartCamera);
        panelSecurity.add(scrollSecurity);
        tabs.addTab("Security", panelSecurity);

        btnLock.addActionListener((ActionEvent e) -> handleLock(true));
        btnUnlock.addActionListener((ActionEvent e) -> handleLock(false));
        btnStartCamera.addActionListener((ActionEvent e) -> handleStartCamera());

        getContentPane().add(tabs);
        pack();
        setLocationRelativeTo(null);
    }

    private void handleSetTemp() {
        try {
            float target = Float.parseFloat(txtTargetTemp.getText());
            SetTemperatureRequest request = SetTemperatureRequest.newBuilder()
                    .setAuth(auth)
                    .setTemperature(target)
                    .build();
            StatusResponse response = climateStub.setTemperature(request);
            txtClimateLog.append("✔ Temp Set: " + response.getMessage() + "\n");
        } catch (NumberFormatException ex) {
            txtClimateLog.append("❌ Invalid temperature input\n");
        }
    }

    private void adjustTemperature(float delta) {
        StreamObserver<TemperatureAdjustmentAck> responseObserver = new StreamObserver<>() {
            public void onNext(TemperatureAdjustmentAck ack) {
                SwingUtilities.invokeLater(() -> txtClimateLog.append("↔ Target Temp: " + ack.getTargetTemperature() + "\n"));
            }
            public void onError(Throwable t) {
                SwingUtilities.invokeLater(() -> txtClimateLog.append("⚠ Error: " + t.getMessage() + "\n"));
            }
            public void onCompleted() {}
        };
        StreamObserver<TemperatureAdjustment> requestObserver = climateAsync.adjustTemperature(responseObserver);
        requestObserver.onNext(TemperatureAdjustment.newBuilder()
                .setAuth(auth)
                .setDevice(Device.newBuilder().setId(auth.getDeviceId()).build())
                .setTemperatureDelta(delta)
                .build());
        requestObserver.onCompleted();
    }

    private void handleSetBrightness() {
        try {
            boolean isOn = chkLight.isSelected();
            int brightness = sliderBrightness.getValue();
            Device device = Device.newBuilder().setId("device-light-1").build();

            ToggleRequest toggleRequest = ToggleRequest.newBuilder()
                    .setAuth(auth).setDevice(device).setOn(isOn).build();
            lightingStub.toggleLight(toggleRequest);

            BrightnessRequest brightnessRequest = BrightnessRequest.newBuilder()
                    .setAuth(auth).setDevice(device).setPercent(brightness).build();
            StatusResponse response = lightingStub.setBrightness(brightnessRequest);

            txtLightingLog.append("✔ Light updated: " + response.getMessage() + "\n");
        } catch (Exception e) {
            txtLightingLog.append("⚠ Error: " + e.getMessage() + "\n");
        }
    }

    private void handleLock(boolean lock) {
        try {
            LockRequest request = LockRequest.newBuilder()
                    .setAuth(auth)
                    .setDevice(Device.newBuilder().setId("device-door-1").build())
                    .build();
            StatusResponse response = lock ? securityStub.lockDoor(request) : securityStub.unlockDoor(request);
            txtSecurityLog.append("🔐 Door: " + response.getMessage() + "\n");
        } catch (Exception e) {
            txtSecurityLog.append("⚠ Error: " + e.getMessage() + "\n");
        }
    }

    private void handleStartCamera() {
        CameraRequest request = CameraRequest.newBuilder()
                .setAuth(auth)
                .setCameraId("camera-1")
                .setFrameRate(1)
                .build();
        securityAsync.streamCameraFeed(request, new StreamObserver<CameraFrame>() {
            int count = 0;
            public void onNext(CameraFrame frame) {
                SwingUtilities.invokeLater(() -> txtSecurityLog.append("📷 Frame received at " + frame.getTimestamp() + "\n"));
                if (++count >= 5) onCompleted();
            }
            public void onError(Throwable t) {
                SwingUtilities.invokeLater(() -> txtSecurityLog.append("⚠ Camera error: " + t.getMessage() + "\n"));
            }
            public void onCompleted() {
                SwingUtilities.invokeLater(() -> txtSecurityLog.append("✅ Camera stream ended.\n"));
            }
        });
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new SmartHomeGui().setVisible(true));
    }
} 
