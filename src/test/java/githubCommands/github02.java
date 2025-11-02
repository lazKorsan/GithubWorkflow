package githubCommands;

public class github02 {

    public static void main(String[] args) {
        System.out.println("Bu dosya, günlük Git komutları ve zorlu durumlar için bir rehberdir.");
        /*
        ===================================================================================
        BÖLÜM 1: GÜNLÜK STANDART GIT İŞ AKIŞI
        ===================================================================================
        Bu bölüm, her gün kullanacağın temel Git komutlarını içerir.

        --- ADIM 1: Güne Başlarken Projeyi Güncelle ---
        // Kendi branch'indeyken, merkezi sunucudaki en son değişiklikleri al.
        // Bu, olası çakışmaları en aza indirir.
        git pull origin <branch_adı>
        // Örnek: git pull origin ahmet

        --- ADIM 2: Kodunu Yaz ve Değişikliklerini Yap ---
        // Bu aşamada IntelliJ IDEA'da kodlarını yazar, testlerini yaparsın.

        --- ADIM 3: Değişikliklerini Kontrol Et ve Ekle ---
        // Hangi dosyalarda değişiklik yaptığını gör.
        git status

        // Yaptığın tüm değişiklikleri Git'in takip etmesi için hazırla (staging).
        git add .

        --- ADIM 4: Değişikliklerini Kaydet ---
        // Hazırladığın değişiklikleri açıklayıcı bir mesajla yerel repoya kaydet.
        git commit -m "Buraya yaptığın işi özetleyen bir mesaj yaz"
        // Örnek: git commit -m "Login sayfası için test senaryoları eklendi."

        --- ADIM 5: Değişikliklerini Sunucuya Gönder ---
        // Yerel repodaki kayıtlı değişikliklerini takım arkadaşlarının da görebilmesi için merkezi sunucuya gönder.
        git push origin <branch_adı>


        ===================================================================================
        BÖLÜM 2: ZORLU DURUMLAR VE ÇÖZÜMLERİ
        ===================================================================================
        İşler yolunda gitmediğinde veya acil bir durum olduğunda bu komutlar hayat kurtarır.

        --- SENARYO 1: Değişikliklerini Kaybetmeden Acil Branch Değiştirme ---
        // PROBLEM: Bir branch'te çalışırken (örneğin 'ahmet'), henüz commit'lemediğin değişikliklerin var
        // ama acil olarak 'main' branch'ine geçip bir şeye bakman gerekiyor.
        // Git, değişikliklerini kaybetme riski olduğu için branch değiştirmene izin vermez.

        // ÇÖZÜM: Değişiklikleri Geçici Olarak Saklama (Stash)

        // 1. Mevcut değişikliklerini geçici hafızaya al:
        git stash
        // Bu komut, tüm değişikliklerini bir kenara ayırır ve çalışma alanını temizler.

        // 2. İstediğin branch'e geçiş yap:
        git switch main
        // Artık 'main' branch'indesin ve işini halledebilirsin.

        // 3. İşin bitince kendi branch'ine geri dön:
        git switch ahmet

        // 4. Kenara ayırdığın değişiklikleri geri yükle:
        git stash pop
        // Bu komut, en son sakladığın değişiklikleri geri getirir ve kaldığın yerden devam etmeni sağlar.


        --- SENARYO 2: Yaptığın Tüm Değişikliklerden Vazgeçip Zorla Branch Değiştirme ---
        // PROBLEM: Bir branch'te yaptığın değişiklikler işe yaramaz hale geldi ve hepsinden
        // kurtulup branch'in en temiz haline dönmek istiyorsun.

        // DİKKAT: BU İŞLEMLER GERİ ALINAMAZ! KAYDEDİLMEMİŞ TÜM DEĞİŞİKLİKLERİN SİLİNİR!

        // ÇÖZÜM 1: Mevcut Branch'teki Tüm Değişiklikleri İptal Et
        git reset --hard
        // Bu komut, son commit'ten sonra yapılan tüm değişiklikleri siler.

        // ÇÖZÜM 2: Değişiklikleri Yoksayarak Başka Bir Branch'e Geçmeye Zorla
        git checkout -f <hedef_branch>
        // Örnek: git checkout -f main
        // Bu komut, mevcut değişikliklerini siler ve zorla 'main' branch'ine geçer.
         */


    }
}
