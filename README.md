# FootballTeamProject
Halı Saha Müsabakası
Takım Organ3ze
RestAp3 Projes3
Amaç: B$r futbol maçı $ç$n A ve B takımlarına katılacak futbolcuları seçerek takımları
organ$ze etmek.
Kurallar:
• Tüm ver$ alanları val$date ed$lmel$d$r. Örn: E-ma$l hem format olarak hem de
boşluk denet$m$ olarak kontrol ed$lmel$d$r.
• Json ver$ dönüş standartlarına uyulmalıdır. Object kullanarak ger$ dönüş
key’ler$ standartı sağlanmalıdır.
• Ger$ dönşlerde httpstatus kodları kullanılmalıdır.
• Ş$freler Google T$nk $le Encrypt$on yapılmalıdır.
• Object hatalarının yakalanmasında Global Except$on kullanılmalıdır.
• Ver$ tabanı olarak $sted$ğ$n$z b$r yapıyı kullanab$l$rs$n$z, Mysql, H2, ..
S*steme üye olma: Futbolcu ad, soy ad, ma$l, ş$fre ve yaş g$rerek s$steme kayıt olur.
Üye olurken en az 18 yaş ve üstü kuralı olmalıdır. Bu $şlem sonunda futbolcuya
üyel$ğ$ başarılı olmuş $se “f$d” $d’s$ ver$l$r. Daha önce futbolcu üye oldu $se buna
yönel$k b$r uyarı almalıdır.
URL: footballerReg$ster -> POST
Takıma katılma: Futbolcu kend$s$ $ç$n atanmış “f$d” $le hang$ takıma üye olacağını
bel$rt$r ve o takıma g$rer. Takımlar “A” ve “B” takımı olarak mevcuttur. Futbolcu takıma
başvuru yapmadan önce s$steme g$r$ş yapmalıdır. B$r futbolcu aynı anda b$r takıma
üye olab$l$r eğer farklı b$r takıma daha katılmak $sterse uygulama zaten katıldığı b$r
takım olduğuna da$r uyarı vermel$d$r.
URL: teamInsert/A -> POST
Takımları oluşturma: S$stem A ve B takımları $ç$n yapılan başvurular sonunda
takımları oluşturmalıdır. Her takım 6 k$ş$den oluşmalıdır. Takımlar oluşturulurken
yaşları en küçük olandan en büyük olana sıralayacak şek$lde yapılmalıdır.
URL: teamCreate -> GET
{
A: [{footballerObj1}, {footballerObj2}, .. ],
B: [{footballerObj1}, {footballerObj1}, .. ],
}
Yedekler*n oluşturulması: Her takım $ç$n eğer varsa fazla başvuru yedek l$stes$ de
olmalıdır. Yedek l$stes$ 3 k$ş$den oluşmalı ana takıma atanmamış y$ne yaşları
küçükten büyüğe doğru sıralayacak şek$lde olmalıdır.
URL: backUpCreate -> GET
{
A: [{footballerObj1}, {footballerObj1}, .. ],
B: [{footballerObj1}, {footballerObj1}, .. ],
}