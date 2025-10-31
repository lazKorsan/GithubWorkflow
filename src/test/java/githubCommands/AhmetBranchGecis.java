package githubCommands;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class AhmetBranchGecis {
    private String projectPath;

    // 🔧 BRANCH AYARI - BU SATIRI DEĞİŞTİRİN! (SATIR 13)
    // "ahmet" yazan yere KENDİ BRANCH ADINIZI yazın
    public static final String BRANCH_NAME = "ahmet"; // ← BU SATIRI DEĞİŞTİRİN!

    public AhmetBranchGecis(String projectPath) {
        this.projectPath = projectPath;
    }

    /**
     * Branch geçiş işlemini başlatır
     */
    public void switchToMyBranch() {
        System.out.println("🔄 BRANCH GEÇİŞ SİSTEMİ");
        System.out.println("========================");
        System.out.println("Proje Yolu: " + projectPath);
        System.out.println("Hedef Branch: " + BRANCH_NAME);

        // Kontrol mesajı
        if (BRANCH_NAME.equals("ahmet")) {
            System.out.println("⚠️  UYARI: Branch adı hala 'ahmet' olarak ayarlı!");
            System.out.println("   Lütfen 13. satırdaki BRANCH_NAME değişkenini kendi branch adınızla güncelleyin.");
        }

        try {
            // 1. Hedef branch'e geç
            System.out.println("\n1️⃣ " + BRANCH_NAME.toUpperCase() + " BRANCH'INE GEÇİLİYOR...");
            executeCommand("git", "checkout", BRANCH_NAME);

            // 2. Branch'i güncelle
            System.out.println("\n2️⃣ " + BRANCH_NAME.toUpperCase() + " BRANCH GÜNCELLENİYOR...");
            executeCommand("git", "pull", "origin", BRANCH_NAME);

            System.out.println("\n🎉 BAŞARILI! " + BRANCH_NAME.toUpperCase() + " BRANCH'INE GEÇİLDİ!");
            System.out.println("📌 Şu anki branch: " + BRANCH_NAME);
            System.out.println("💡 Artık " + BRANCH_NAME + " branch'inde çalışıyorsunuz!");

        } catch (IOException | InterruptedException e) {
            System.out.println("\n❌ HATA: " + BRANCH_NAME + " branch'ine geçiş başarısız!");
            System.out.println("📝 Hata Mesajı: " + e.getMessage());
            System.out.println("🔧 Çözüm: " + BRANCH_NAME + " branch'i var mı kontrol edin: git branch -a");
            System.out.println("   Eğer branch yoksa önce oluşturun: git checkout -b " + BRANCH_NAME);
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

    /**
     * Tüm branch'leri listeler
     */
    public void listAllBranches() {
        System.out.println("\n📋 TÜM BRANCH'LER:");
        try {
            executeCommand("git", "branch", "-a");
        } catch (IOException | InterruptedException e) {
            System.out.println("❌ Branch listeleme hatası: " + e.getMessage());
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
        // 🔧 PROJE YOLU AYARI - BU SATIRI DEĞİŞTİRİN! (SATIR 77)
        String projectPath = "C:\\Users\\user\\IdeaProjects\\GithubWorkflow"; // ← KENDİ PROJE YOLUNUZU YAZIN

        // Kontrol mesajı
        if (projectPath.contains("user")) {
            System.out.println("⚠️  UYARI: Proje yolu hala örnek yol olarak ayarlı!");
            System.out.println("   Lütfen 77. satırdaki projectPath değişkenini kendi proje yolunuzla güncelleyin.");
        }

        AhmetBranchGecis branchSwitcher = new AhmetBranchGecis(projectPath);

        System.out.println("🚀 BRANCH GEÇİŞ SİSTEMİ BAŞLATILIYOR...");
        System.out.println("======================================");

        // Tüm branch'leri göster
        branchSwitcher.listAllBranches();

        // Mevcut branch'i göster
        branchSwitcher.showCurrentBranch();

        // Branch'e geç
        branchSwitcher.switchToMyBranch();

        System.out.println("\n✅ İŞLEM TAMAMLANDI!");
    }
}