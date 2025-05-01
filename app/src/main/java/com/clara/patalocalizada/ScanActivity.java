package com.clara.patalocalizada;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.Size;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.mlkit.vision.barcode.BarcodeScanner;
import com.google.mlkit.vision.barcode.BarcodeScanning;
import com.google.mlkit.vision.common.InputImage;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ScanActivity extends AppCompatActivity {

    // Constante para identificar a solicitação de permissão da câmera
    private static final int REQUEST_CAMERA_PERMISSION = 10;

    // Componente que exibe a pré-visualização da câmera
    private PreviewView previewView;

    // Executor para processar imagens em segundo plano
    private ExecutorService cameraExecutor;

    // Evitar múltiplas leituras simultâneas
    private boolean isScanning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        // Referência ao componente visual da câmera
        previewView = findViewById(R.id.previewView);

        // Análise de imagens
        cameraExecutor = Executors.newSingleThreadExecutor();

        // Verifica se a permissão da câmera foi concedida, senão solicita
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {
            startCamera(); // Inicia a câmera
        } else {
            // Solicita permissão da câmera ao utilizador
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
        }
    }

    // Método para configurar e iniciar a câmera
    private void startCamera() {
        // Gerir o acesso físico à câmera do dispositivo
        ListenableFuture<ProcessCameraProvider> cameraProviderFuture =
                ProcessCameraProvider.getInstance(this);

        // Aguarda a câmera estar pronta
        cameraProviderFuture.addListener(() -> {
            try {
                ProcessCameraProvider cameraProvider = cameraProviderFuture.get();

                // Configura a visualização da câmera
                Preview preview = new Preview.Builder().build();
                preview.setSurfaceProvider(previewView.getSurfaceProvider());

                // Configura a análise de imagens da câmera
                ImageAnalysis imageAnalysis = new ImageAnalysis.Builder()
                        .setTargetResolution(new Size(1280, 720)) // resolução do frame
                        .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST) // estratégia de descarte
                        .build();

                // Define o analisador de imagem que será chamado para cada frame
                imageAnalysis.setAnalyzer(cameraExecutor, this::analyzeImage);

                // Usa a câmera traseira
                CameraSelector cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA;

                // Garante que nenhuma câmera seja usada antes de iniciar
                cameraProvider.unbindAll();

                // Liga a câmera ao ciclo de vida da atividade
                cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageAnalysis);

            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace(); // Trata possíveis erros
            }
        }, ContextCompat.getMainExecutor(this));
    }

    // Método que processa cada frame da câmera e tenta detectar códigos de barras
    @androidx.camera.core.ExperimentalGetImage
    private void analyzeImage(ImageProxy imageProxy) {
        // Verifica se a imagem é válida e se já ocorreu uma leitura
        if (imageProxy.getImage() == null || isScanning) {
            imageProxy.close();
            return;
        }

        // Converte o frame da câmera para o formato aceito pelo ML Kit
        InputImage image = InputImage.fromMediaImage(
                imageProxy.getImage(), imageProxy.getImageInfo().getRotationDegrees());

        // Cria um scanner de código de barras usando ML Kit (conjunto de APIs)
        BarcodeScanner scanner = BarcodeScanning.getClient();
        isScanning = true; // Marca que a leitura está em andamento

        // Inicia o processo de detecção do código
        scanner.process(image)
                .addOnSuccessListener(barcodes -> {
                    if (!barcodes.isEmpty()) {
                        // Pega o primeiro código detectado
                        String rawValue = barcodes.get(0).getRawValue();

                        // Executa ações na thread principal (UI)
                        runOnUiThread(() -> {
                            if (rawValue != null && rawValue.startsWith("http")) {
                                // Se for uma URL, abre no navegador
                                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(rawValue));
                                startActivity(browserIntent);
                            } else {
                                // Senão, mostra o conteúdo num Toast
                                Toast.makeText(this, "Conteúdo: " + rawValue, Toast.LENGTH_LONG).show();
                            }
                            finish(); // Encerra a atividade após ser lido
                        });
                    }
                })
                .addOnFailureListener(e -> Log.e("ScanActivity", "Erro na leitura", e)) // Log de erro
                .addOnCompleteListener(task -> {
                    imageProxy.close(); // Liberta o frame
                    isScanning = false; // Permite novas leituras
                });
    }

    // Encerra o executor da câmera ao destruir a atividade
    @Override
    protected void onDestroy() {
        super.onDestroy();
        cameraExecutor.shutdown();
    }

    // Método chamado quando o utilizador responde à solicitação de permissão
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CAMERA_PERMISSION &&
                grantResults.length > 0 &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startCamera(); // Permissão concedida, inicia a câmera
        } else {
            Toast.makeText(this, "Permissão negada", Toast.LENGTH_SHORT).show();
            finish(); // Encerra a atividade se a permissão for negada
        }
    }
}
