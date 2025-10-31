package githubCommands;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class githubBranchOlusturma {
    private String projectPath;
    private String branchName;

    // BU KOD BLOĞU ÇOK GEREKLİ DEĞİL  SADECE BİR KERE ÇALIŞTIRIN
    // ***************  SADECE BİR KERE ÇALIŞTIRIN *************************

    public githubBranchOlusturma(String projectPath, String branchName) {
        this.projectPath = projectPath;
        this.branchName = branchName;
    }

    /**
     * Yeni branch oluşturma işlemini başlatır
     */
    public void createNewBranch() {
        System.out.println("🌿 YENİ BRANCH OLUŞTURMA SİSTEMİ");
        System.out.println("================================");
        System.out.println("Proje Yolu: " + projectPath);
        System.out.println("Branch Adı: " + branchName);

        try {
            // 1. Ana branch'e geç
            System.out.println("\n1️⃣ ANA BRANCH'E GEÇİLİYOR...");
            executeCommand("git", "checkout", "main");

            // 2. Ana branch'i güncelle
            System.out.println("\n2️⃣ ANA BRANCH GÜNCELLENİYOR...");
            executeCommand("git", "pull", "origin", "main");

            // 3. Yeni branch oluştur ve geç
            System.out.println("\n3️⃣ YENİ BRANCH OLUŞTURULUYOR: " + branchName);
            executeCommand("git", "checkout", "-b", branchName);

            // 4. Yeni branch'i GitHub'a gönder
            System.out.println("\n4️⃣ BRANCH GITHUB'A GÖNDERİLİYOR...");
            executeCommand("git", "push", "-u", "origin", branchName);

            System.out.println("\n🎉 BRANCH BAŞARIYLA OLUŞTURULDU!");
            System.out.println("🔗 Yeni Branch: " + branchName);
            System.out.println("📌 Proje: " + projectPath);
            System.out.println("💡 Not: Artık '" + branchName + "' branch'inde çalışıyorsunuz!");

        } catch (IOException | InterruptedException e) {
            System.out.println("\n❌ HATA: Branch oluşturma işlemi başarısız!");
            System.out.println("📝 Hata Mesajı: " + e.getMessage());
        }
    }

    /**
     * Mevcut branch'leri listeler
     */
    public void listBranches() {
        System.out.println("\n📋 MEVCUT BRANCH'LER:");
        try {
            executeCommand("git", "branch", "-a");
        } catch (IOException | InterruptedException e) {
            System.out.println("❌ Branch listeleme hatası: " + e.getMessage());
        }
    }

    /**
     * Git komutunu çalıştırır
     */
    private void executeCommand(String... command) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        processBuilder.directory(new File(projectPath));
        processBuilder.redirectErrorStream(true);

        Process process = processBuilder.start();

        // Çıktıyı oku ve göster
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("   → " + line);
            }
        }

        int exitCode = process.waitFor();
        if (exitCode != 0) {
            System.out.println("   ⚠️ Komut çıkış kodu: " + exitCode);
        }
    }

    public static void main(String[] args) {
        // 🔧 AYARLARI BURADAN DEĞİŞTİRİN:
        String projectPath = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"; // KENDİ PROJE YOLUNUZU YAZIN
        String yourBranch = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"; // KENDİ BRANCH ADINIZI YAZIN

        githubBranchOlusturma branchCreator = new githubBranchOlusturma(projectPath, yourBranch);

        // Mevcut branch'leri göster
        branchCreator.listBranches();

        // Yeni branch oluştur
        branchCreator.createNewBranch();

        // Son durumu göster
        branchCreator.listBranches();
    }
}