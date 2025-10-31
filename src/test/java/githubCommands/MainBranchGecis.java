package githubCommands;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainBranchGecis {
    private String projectPath;

    public MainBranchGecis(String projectPath) {
        this.projectPath = projectPath;
    }

    /**
     * Main branch'ine geçiş işlemini başlatır
     */
    public void switchToMainBranch() {
        System.out.println("🔄 MAIN BRANCH'INE GEÇİŞ SİSTEMİ");
        System.out.println("================================");
        System.out.println("Proje Yolu: " + projectPath);
        System.out.println("Hedef Branch: main");

        try {
            // 1. Main branch'ine geç
            System.out.println("\n1️⃣ MAIN BRANCH'INE GEÇİLİYOR...");
            executeCommand("git", "checkout", "main");

            // 2. Main branch'ini güncelle
            System.out.println("\n2️⃣ MAIN BRANCH GÜNCELLENİYOR...");
            executeCommand("git", "pull", "origin", "main");

            System.out.println("\n🎉 BAŞARILI! MAIN BRANCH'INE GEÇİLDİ!");
            System.out.println("📌 Şu anki branch: main");
            System.out.println("💡 Artık main branch'inde çalışıyorsunuz!");

        } catch (IOException | InterruptedException e) {
            System.out.println("\n❌ HATA: Main branch'ine geçiş başarısız!");
            System.out.println("📝 Hata Mesajı: " + e.getMessage());
        }
    }

    /**
     * Mevcut branch'i gösterir
     */
    public void showCurrentBranch() {
        System.out.println("\n📌 MEVCUT BRANCH:");
        try {
            executeCommand("git", "branch", "--show-current");
        } catch (IOException | InterruptedException e) {
            System.out.println("❌ Branch kontrol hatası: " + e.getMessage());
        }
    }

    private void executeCommand(String... command) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        processBuilder.directory(new File(projectPath));
        processBuilder.redirectErrorStream(true);

        Process process = processBuilder.start();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("   → " + line);
            }
        }

        process.waitFor();
    }

    public static void main(String[] args) {
        // 🔧 AYARI BURADAN DEĞİŞTİRİN:
        String projectPath = "C:\\Users\\user\\IdeaProjects\\GithubWorkflow"; // KENDİ PROJE YOLUNUZU YAZIN

        MainBranchGecis branchSwitcher = new MainBranchGecis(projectPath);

        // Mevcut branch'i göster
        branchSwitcher.showCurrentBranch();

        // Main branch'ine geç
        branchSwitcher.switchToMainBranch();

        // Son durumu göster
        branchSwitcher.showCurrentBranch();
    }
}