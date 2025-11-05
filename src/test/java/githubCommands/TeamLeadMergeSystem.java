package githubCommands;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TeamLeadMergeSystem {
    private final String projectPath;
    private final String mainBranch;
    private List<String> teamBranches;

    public TeamLeadMergeSystem(String projectPath, String mainBranch) {
        this.projectPath = projectPath;
        this.mainBranch = mainBranch;
        this.teamBranches = new ArrayList<>();
    }

    /**
     * TAKIM LİDERİ - TÜM BRANCH'LERİ MERGE ETME SİSTEMİ
     */
    public void startTeamLeadMerge() {
        System.out.println("🎖️  TEAM LEAD MERGE SİSTEMİ BAŞLATILIYOR");
        System.out.println("==========================================");
        System.out.println("TeamLead Notu: Tüm ekip çalışmalarını ana branch'le birleştiriyorum.");
        System.out.println("Proje Yolu: " + projectPath);
        System.out.println("Ana Branch: " + mainBranch);

        Scanner scanner = new Scanner(System.in);

        try {
            // 1. Ana branch'e geç ve güncelle
            System.out.println("\n--- ADIM 1: Ana Branch Hazırlanıyor ---");
            executeCommand("git", "checkout", mainBranch);
            executeCommand("git", "pull", "origin", mainBranch);
            System.out.println("✅ Ana branch güncellendi: " + mainBranch);

            // 2. Tüm branch'leri listele
            System.out.println("\n--- ADIM 2: Branch'ler Listeleniyor ---");
            List<String> allBranches = getAllBranches();
            System.out.println("📋 Mevcut Branch'ler:");
            for (int i = 0; i < allBranches.size(); i++) {
                System.out.println("   " + (i + 1) + ". " + allBranches.get(i));
            }

            // 3. Merge edilecek branch'leri seç
            System.out.println("\n--- ADIM 3: Merge Edilecek Branch'ler Seçiliyor ---");
            System.out.println("Hangi branch'leri merge etmek istiyorsunuz?");
            System.out.println("(Örnek: 1,3,5 veya 'hepsi' yazın)");
            System.out.print("Seçiminiz: ");
            String selection = scanner.nextLine();

            List<String> branchesToMerge = parseBranchSelection(selection, allBranches);

            if (branchesToMerge.isEmpty()) {
                System.out.println("❌ Hiç branch seçilmedi. İşlem iptal ediliyor.");
                return;
            }

            // 4. Seçilen branch'leri merge et
            System.out.println("\n--- ADIM 4: Merge İşlemleri Başlatılıyor ---");
            for (String branch : branchesToMerge) {
                if (branch.equals(mainBranch)) {
                    System.out.println("⏭️  " + branch + " atlandı (ana branch)");
                    continue;
                }
                mergeBranch(branch);
            }

            // 5. Değişiklikleri push et
            System.out.println("\n--- ADIM 5: Tüm Değişiklikler Push Ediliyor ---");
            executeCommand("git", "push", "origin", mainBranch);

            System.out.println("\n🎉 TÜM MERGE İŞLEMLERİ TAMAMLANDI!");
            System.out.println("✅ " + branchesToMerge.size() + " branch başarıyla merge edildi.");
            System.out.println("📊 Merge Edilen Branch'ler: " + branchesToMerge);

        } catch (Exception e) {
            System.out.println("\n❌ Merge işlemi başarısız: " + e.getMessage());
            System.out.println("⚠️  Conflict durumunda lütfen manuel müdahale yapın.");
        } finally {
            scanner.close();
        }
    }

    /**
     * Belirtilen branch'i ana branch'le merge et
     */
    private void mergeBranch(String branchName) throws IOException, InterruptedException {
        System.out.println("\n🔄 '" + branchName + "' branch'i merge ediliyor...");

        try {
            // Branch'i merge et
            executeCommand("git", "merge", branchName, "--no-ff", "-m",
                    "TeamLead Merge: '" + branchName + "' branch'ini birleştir");
            System.out.println("✅ '" + branchName + "' başarıyla merge edildi.");

        } catch (IOException e) {
            System.out.println("❌ '" + branchName + "' merge edilemedi: " + e.getMessage());
            System.out.println("⚠️  Conflict oluştu, manuel çözüm gerekli.");
            // Conflict durumunda merge işlemini iptal et
            executeCommand("git", "merge", "--abort");
            System.out.println("🔄 Merge işlemi iptal edildi.");
        }
    }

    /**
     * Tüm branch'leri listele
     */
    private List<String> getAllBranches() throws IOException, InterruptedException {
        List<String> branches = new ArrayList<>();
        Process process = executeCommandWithReturn("git", "branch", "-a");

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));
        String line;
        while ((line = reader.readLine()) != null) {
            line = line.trim().replace("*", "").trim();
            if (!line.isEmpty() && !line.contains("HEAD") && !line.contains("->")) {
                // Sadece local branch'leri al
                if (!line.startsWith("remotes/")) {
                    branches.add(line);
                }
            }
        }
        return branches;
    }

    /**
     * Kullanıcı seçimini parse et
     */
    private List<String> parseBranchSelection(String selection, List<String> allBranches) {
        List<String> selectedBranches = new ArrayList<>();

        if (selection.equalsIgnoreCase("hepsi")) {
            selectedBranches.addAll(allBranches);
            return selectedBranches;
        }

        String[] selections = selection.split(",");
        for (String sel : selections) {
            try {
                int index = Integer.parseInt(sel.trim()) - 1;
                if (index >= 0 && index < allBranches.size()) {
                    selectedBranches.add(allBranches.get(index));
                }
            } catch (NumberFormatException e) {
                // Sayısal olmayan girişleri ignore et
            }
        }
        return selectedBranches;
    }

    /**
     * Komut çalıştırma utility'si - Process RETURN EDEN
     */
    private Process executeCommandWithReturn(String... command) throws IOException, InterruptedException {
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
        return process;
    }

    /**
     * Komut çalıştırma utility'si - VOID (orijinal sistemle uyumlu)
     */
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
        // TeamLead için ayarlar
        String projectPath = "C:\\Users\\user\\IdeaProjects\\GithubWorkflow"; // Proje yolu
        String mainBranch = "main"; // Ana branch adı

        TeamLeadMergeSystem mergeSystem = new TeamLeadMergeSystem(projectPath, mainBranch);
        mergeSystem.startTeamLeadMerge();
    }
}