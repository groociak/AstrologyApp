package org.app.functions;

import kong.unirest.core.HttpResponse;
import kong.unirest.core.Unirest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class birthChartService {
    public birthChartService() {
        startBackend();
        waitForBackend();
    }

    private boolean isBackendRunning() {

        try {

            HttpResponse<String> response =
                    Unirest.get("http://127.0.0.1:8000/api/v1/health")
                            .asString();

            return response.getStatus() == 200;

        } catch (Exception e) {
            return false;
        }
    }

    private void startBackend() {
        if (isBackendRunning()) {
            System.out.println("Backend already running");
            return;
        }


        try {

            ProcessBuilder pb =
                    new ProcessBuilder("runtime/start_api.exe");

            pb.redirectErrorStream(true);

            Process process = pb.start();

            new Thread(() -> {

                try (BufferedReader reader =
                             new BufferedReader(
                                     new InputStreamReader(
                                             process.getInputStream()
                                     )
                             )) {

                    String line;

                    while ((line = reader.readLine()) != null) {
                        System.out.println("[PYTHON] " + line);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }).start();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void waitForBackend(){
        for (int i = 0; i < 20; i++) {
            try {
                HttpResponse<String> response = Unirest.get("http://127.0.0.1:8000/api/v1/health").asString();

                if (response.getStatus() == 200) {
                    return;
                }

            } catch (Exception ignored) {}

            try {
                Thread.sleep(500);
            } catch (InterruptedException ignored) {}
        }

        throw new RuntimeException("Backend did not start");
    }

}
