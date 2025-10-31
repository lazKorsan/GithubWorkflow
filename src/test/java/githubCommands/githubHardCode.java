package githubCommands;

public class githubHardCode {
    /*
        Bazen işler beklendiği gibi gitmez ve daha zorlayıcı Git komutlarını kullanmak gerekebilir.
        Bu komutlar genellikle geçmişi değiştirir veya yerel değişiklikleri kalıcı olarak siler,
        bu yüzden DİKKATLİ kullanılmalıdırlar. Özellikle ortak çalışılan branch'lerde takım ile iletişim kurmadan kullanılmamalıdır.

        Örneklerde branch adı olarak "ahmet" kullanılmıştır.

        1. Yerel Değişiklikleri Tamamen Geri Alma (git reset --hard):
           Projenizdeki tüm yerel değişiklikleri (commit'lenmemiş) iptal edip, son commit'lenmiş hale geri dönmek için kullanılır.
           DİKKAT: Bu komut, kaydetmediğiniz tüm değişiklikleri kalıcı olarak siler.
           git reset --hard HEAD

        2. Uzak Depodaki Branch'i Yerel Branch ile Eşitleme (Zorla):
           Yerel branch'inizi uzak depodaki (origin) branch'in birebir aynısı yapmak istediğinizde kullanılır.
           Aradaki tüm yerel commit'leriniz silinir. Bu, genellikle başkasının yaptığı değişiklikleri alırken çakışma (conflict)
           yaşandığında ve yerel değişikliklerinizin önemsiz olduğu durumlarda kullanılır.
           git reset --hard origin/ahmet

        3. Uzak Depoyu Yerel Değişikliklerle Ezme (git push --force):
           Uzak depodaki (GitHub) branch geçmişini, kendi yerel makinenizdeki geçmişle zorla değiştirir.
           DİKKAT: Bu komut, başka ekip üyelerinin yaptığı ve sizin haberiniz olmayan değişiklikleri silebilir.
           Sadece kendi kişisel branch'inizde veya tüm ekibin onayıyla kullanılmalıdır.
           git push origin ahmet --force

           Daha güvenli bir alternatif ise '--force-with-lease' kullanmaktır. Bu komut, uzaktaki branch'e sizden sonra
           kimsenin bir değişiklik göndermediğinden emin olur, aksi takdirde işlemi reddeder.
           git push origin ahmet --force-with-lease
     */
}
