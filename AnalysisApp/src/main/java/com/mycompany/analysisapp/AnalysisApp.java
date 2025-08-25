package com.mycompany.analysisapp;

import panels.*;

import javax.swing.*;
import java.awt.*;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;

public class AnalysisApp {

    public static void main(String[] args) {
        showSameThings();
    }

    private static void showSameThings() {

        JFrame frame = new JFrame("KULLANICILAR ARASINDA ORTAK OLAN DEĞİŞKENLER LİSTESİ");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton showButton = new JButton("ORTAK DİLLERİ KULLANAN KULLANICILARI GÖSTER.");
        showButton.addActionListener(e -> searchingSameLanguage());

        JButton showButton2 = new JButton("ORTAK BÖLGELERDE BULUNAN KULLANICILARI GÖSTER.");
        showButton2.addActionListener(e -> searchingSameRegion());

        JButton showButton3 = new JButton("AYNI TAKİPÇİ SAYISINA SAHİP KULLANICILARI GÖSTER.");
        showButton3.addActionListener(e -> searchingSameFollowersCount());

        JButton showButton4 = new JButton("AYNI TAKİP SAYISINA SAHİP KULLANICILARI GÖSTER.");
        showButton4.addActionListener(e -> searchingSameFollowingCount());

        JButton showButton5 = new JButton("KULLANICI-TAKİPÇİ İLİŞKİLERİNİ GÖSTEREN LİSTE.");
        showButton5.addActionListener(e -> userFollowersRelationshipList());

        JButton showButton6 = new JButton("KULLANICI-TAKİP İLİŞKİLERİNİ GÖSTEREN LİSTE.");
        showButton6.addActionListener(e -> userFollowingRelationshipList());

        JButton showButton7 = new JButton("İLGİ ALANLARI-KULLANICI İLİŞKİSİNİ GÖSTEREN LİSTE.");
        showButton7.addActionListener(e -> searchingInterestList());

        JButton showButton8 = new JButton("KULLANICININ TAKİPÇİ-TAKİP GRAFINI GÖSTER.");
        showButton8.addActionListener(e -> showUsersGraph());

        JButton showButton9 = new JButton("KULLANICININ İLGİ ALANI GRAFINI GÖSTER.");
        showButton9.addActionListener(e -> showInterestGraph());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(showButton);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(showButton2);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(showButton3);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(showButton4);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(showButton5);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(showButton6);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(showButton7);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(showButton8);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(showButton9);

        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(buttonPanel, BorderLayout.LINE_START);

        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void searchingSameLanguage() {
        String filepath = "src/main/java/jsonfiles/twitter_data.json";

        ObjectMapper objectMapper = new ObjectMapper();

        try {

            CustomLinkedList<User> userList = new CustomLinkedList<>();
            User[] users = objectMapper.readValue(new File(filepath), User[].class);
            for (User user : users) {
                userList.insertNode(user);
            }

            CustomLinkedList<String> languageList = new CustomLinkedList<>();

            CustomNode<User> temp1 = userList.getHead();
            while (temp1 != null) {

                boolean languageFound = false;
                CustomNode<String> temp2 = languageList.getHead();
                while (temp2 != null) {
                    if (temp2.data.equals(temp1.data.getLanguage())) {
                        languageFound = true;
                        break;
                    }
                    temp2 = temp2.next;
                }

                if (!languageFound) {
                    languageList.insertNode(temp1.data.getLanguage());
                }

                temp1 = temp1.next;
            }

            StringBuilder result = new StringBuilder();
            CustomNode<String> temp4 = languageList.getHead();
            while (temp4 != null) {
                result.append(temp4.data).append(" : ");

                CustomNode<User> temp3 = userList.getHead();
                while (temp3 != null) {
                    if (temp3.data.getLanguage().equals(temp4.data)) {
                        result.append(temp3.data.getUsername()).append(", ");
                    }
                    temp3 = temp3.next;
                }

                result.append("\n\n");
                temp4 = temp4.next;
            }

            JTextArea textArea = new JTextArea(result.toString());
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(700, 700));

            JOptionPane.showMessageDialog(null, scrollPane, "ORTAK DİLLERİ KULLANAN KULLANICILARIN LİSTESİ", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void searchingSameRegion() {
        String filepath = "src/main/java/jsonfiles/twitter_data.json";

        ObjectMapper objectMapper = new ObjectMapper();

        try {

            CustomLinkedList<User> userList = new CustomLinkedList<>();
            User[] users = objectMapper.readValue(new File(filepath), User[].class);
            for (User user : users) {
                userList.insertNode(user);
            }

            CustomLinkedList<String> regionList = new CustomLinkedList<>();

            CustomNode<User> temp1 = userList.getHead();
            while (temp1 != null) {

                boolean regionFound = false;
                CustomNode<String> temp2 = regionList.getHead();
                while (temp2 != null) {
                    if (temp2.data.equals(temp1.data.getRegion())) {
                        regionFound = true;
                        break;
                    }
                    temp2 = temp2.next;
                }

                if (!regionFound) {
                    regionList.insertNode(temp1.data.getRegion());
                }

                temp1 = temp1.next;
            }

            StringBuilder result = new StringBuilder();
            CustomNode<String> temp4 = regionList.getHead();
            while (temp4 != null) {
                result.append(temp4.data).append(" : ");

                CustomNode<User> temp3 = userList.getHead();
                while (temp3 != null) {
                    if (temp3.data.getRegion().equals(temp4.data)) {
                        result.append(temp3.data.getUsername()).append(", ");
                    }
                    temp3 = temp3.next;
                }

                result.append("\n\n");
                temp4 = temp4.next;
            }

            JTextArea textArea = new JTextArea(result.toString());
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(700, 700));

            JOptionPane.showMessageDialog(null, scrollPane, "AYNI BÖLGELERDE BULUNAN KULLANICILARIN LİSTESİ", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void searchingSameFollowersCount() {
        String filepath = "src/main/java/jsonfiles/twitter_data.json";

        ObjectMapper objectMapper = new ObjectMapper();

        try {

            CustomLinkedList<User> userList = new CustomLinkedList<>();
            User[] users = objectMapper.readValue(new File(filepath), User[].class);
            for (User user : users) {
                userList.insertNode(user);
            }

            CustomLinkedList<Integer> followersCountList = new CustomLinkedList<>();

            CustomNode<User> temp1 = userList.getHead();
            while (temp1 != null) {

                boolean followersCountFound = false;
                CustomNode<Integer> temp2 = followersCountList.getHead();
                while (temp2 != null) {
                    if (temp2.data.equals(temp1.data.getFollowersCount())) {
                        followersCountFound = true;
                        break;
                    }
                    temp2 = temp2.next;
                }

                if (!followersCountFound) {
                    followersCountList.insertNode(temp1.data.getFollowersCount());
                }

                temp1 = temp1.next;
            }

            StringBuilder result = new StringBuilder();
            CustomNode<Integer> temp4 = followersCountList.getHead();
            while (temp4 != null) {
                result.append(temp4.data).append(" : ");

                CustomNode<User> temp3 = userList.getHead();
                while (temp3 != null) {
                    if (temp3.data.getFollowersCount() == (temp4.data)) {
                        result.append(temp3.data.getUsername()).append(", ");
                    }
                    temp3 = temp3.next;
                }

                result.append("\n\n");
                temp4 = temp4.next;
            }

            JTextArea textArea = new JTextArea(result.toString());
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(700, 700));

            JOptionPane.showMessageDialog(null, scrollPane, "AYNI TAKİPÇİ SAYISINA SAHİP KULLANICILARIN LİSTESİ", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void searchingSameFollowingCount() {
        String filepath = "src/main/java/jsonfiles/twitter_data.json";

        ObjectMapper objectMapper = new ObjectMapper();

        try {

            CustomLinkedList<User> userList = new CustomLinkedList<>();
            User[] users = objectMapper.readValue(new File(filepath), User[].class);
            for (User user : users) {
                userList.insertNode(user);
            }

            CustomLinkedList<Integer> followingCountList = new CustomLinkedList<>();

            CustomNode<User> temp1 = userList.getHead();
            while (temp1 != null) {

                boolean followingCountFound = false;
                CustomNode<Integer> temp2 = followingCountList.getHead();
                while (temp2 != null) {
                    if (temp2.data.equals(temp1.data.getFollowingCount())) {
                        followingCountFound = true;
                        break;
                    }
                    temp2 = temp2.next;
                }

                if (!followingCountFound) {
                    followingCountList.insertNode(temp1.data.getFollowingCount());
                }

                temp1 = temp1.next;
            }

            StringBuilder result = new StringBuilder();
            CustomNode<Integer> temp4 = followingCountList.getHead();
            while (temp4 != null) {
                result.append(temp4.data).append(" : ");

                CustomNode<User> temp3 = userList.getHead();
                while (temp3 != null) {
                    if (temp3.data.getFollowingCount() == (temp4.data)) {
                        result.append(temp3.data.getUsername()).append(", ");
                    }
                    temp3 = temp3.next;
                }

                result.append("\n\n");
                temp4 = temp4.next;
            }

            JTextArea textArea = new JTextArea(result.toString());
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(700, 700));

            JOptionPane.showMessageDialog(null, scrollPane, "AYNI TAKİP SAYISINA SAHİP KULLANICILARIN LİSTESİ", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void userFollowersRelationshipList() {
        String filepath = "src/main/java/jsonfiles/twitter_data.json";

        ObjectMapper objectMapper = new ObjectMapper();

        try {

            CustomLinkedList<User> userList = new CustomLinkedList<>();
            User[] users = objectMapper.readValue(new File(filepath), User[].class);
            for (User user : users) {
                userList.insertNode(user);
            }

            StringBuilder result = new StringBuilder();
            for (User user : users) {
                result.append(user.getUsername()).append(" : ");
                String[] followers = user.getFollowers();

                for (String follower : followers) {

                    result.append(follower).append(", ");
                }

                result.append("\n\n");

            }

            JTextArea textArea = new JTextArea(result.toString());
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(700, 700));

            JOptionPane.showMessageDialog(null, scrollPane, "KULLANICI - TAKİPÇİ İLİŞKİLERİNİN LİSTESİ", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void userFollowingRelationshipList() {
        String filepath = "src/main/java/jsonfiles/twitter_data.json";
        ObjectMapper objectMapper = new ObjectMapper();

        try {

            CustomLinkedList<User> userList = new CustomLinkedList<>();

            User[] users = objectMapper.readValue(new File(filepath), User[].class);
            for (User user : users) {
                userList.insertNode(user);
            }

            StringBuilder result = new StringBuilder();
            for (User user : users) {
                result.append(user.getUsername()).append(" : ");
                String[] following = user.getFollowing();

                for (String following1 : following) {

                    result.append(following1).append(", ");
                }

                result.append("\n\n");

            }

            JTextArea textArea = new JTextArea(result.toString());
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(700, 700));

            JOptionPane.showMessageDialog(null, scrollPane, "KULLANICI - TAKİP İLİŞKİLERİNİN LİSTESİ", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void searchingInterestList() {

        String[] ilgiAlanlari = {"spor", "eğitim", "teknoloji", "bilim",
            "sağlık", "müzik", "eğlence", "ekonomi", "hayvan", "bitki", "araç"};

        String[] spor = {"spor", "futbol", "asist", "aşırtma", "aut", "averaj", "beşlik", "ceza sahası", "degaj", "de-marke",
            "dripling", "endirekt serbest vuruş", "faul", "fikstür", "forvet", "frikik", "hattrick", "kademe",
            "markaj", "ofsayt", "ön libero", "penaltı", "plonjon", "röveşata", "santra", "santrafor", "stoper",
            "taç", "voleybol", "pas", "manşet", "smaş", "blok", "libero", "ralli", "set", "teknik mol", "puan", "sayı",
            "hücum", "defans", "serbest vuruş", "antrenman", "kaçırma", "servis", "top", "rakip", "file", "çapraz hücum",
            "doksan derece dönüş", "dış şut", "iç şut", "orta blok", "plase", "orta çizgi", "çapraz", "hücum hattı",
            "baraj", "orta oyuncu", "kanat oyuncusu", "pasör", "koç", "rövaşata", "hızlı hücum", "liderlik", "smaşör",
            "setör", "libero değişikliği", "bire bir", "hücum organizasyonu", "toplantı", "sayı avantajı", "tehlikeli servis",
            "serbest oyuncu değişikliği", "badminton", "shuttlecock", "servis", "rakip", "net", "ortadan vuruş", "smash", "drop shot",
            "backhand", "forehand", "yari-final", "final", "maç", "ralli", "set", "rakip saha", "puan", "skor", "çiftler",
            "tekler", "rakip değişikliği", "oyun", "file", "çizgi", "çapraz", "çapraz vuruş", "ağ direği",
            "hücum", "defans", "rakip servisi", "toplantı", "koç", "antrenman", "servis atışı", "taktik",
            "tekli oyun", "çiftli oyun", "rakip taraf", "pozisyon", "yarı-finalist", "turnuva", "vuruş",
            "kazanan", "kaybeden", "ilk vuruş avantajı", "altın set", "beraberlik", "maç puanı", "set puanı", "beyzbol", "atış", "fırlatma", "vuruş", "kaçış", "sayı", "homerun", "stoper", "bekçi", "üçlü oyun", "lider",
            "pich", "yakalama", "foul", "yedek oyuncu", "oyun alanı", "kaleci", "sayıcı", "altı", "üç vuruş",
            "dört toplam", "çıkış", "oyun", "altı toplam", "kurallar", "kalkan", "atıcı", "vurucu", "çalma",
            "atış", "kurtarma", "hücum", "savunma", "zemin", "uzun koşu", "kısa koşu", "atlayış", "sırıkla atlama",
            "gülle atma", "disk atma", "yarış", "maraton", "engelli koşu", "yürüyüş", "kros", "stadyum",
            "bokser", "maç", "raund", "antrenman", "antrenör", "direk", "jab", "crochet", "kombinezon", "halka",
            "sarı kart", "put", "green", "par", "hazard", "backspin", "bunker", "tee", "fairway", "swing", "atletizm", "uzun atlama", "yüksek atlama", "gülle atma", "disk atma", "cirit atma", "yarış", "maraton", "engelli koşu",
            "kros", "stadyum", "sporcu", "antrenman", "koşu", "sprint", "orta mesafe", "uzun mesafe", "yarı maraton",
            "hür koşu", "yarı hür koşu", "yarı hür engelli koşu", "engelli koşu", "yarı engelli koşu",
            "yarı hür engelli koşu", "pol vault", "sırıkla atlama", "tripple jump", "üç adım atlama",
            "5000 metre", "10000 metre", "100 metre", "200 metre", "400 metre", "800 metre", "1500 metre",
            "110 metre engelli", "400 metre engelli", "4x100 bayrak yarışı", "4x400 bayrak yarışı",
            "çekiç atma", "dekatlon", "heptatlon", "pentatlon", "boks", "yeni başlayan", "ring", "raund", "köşe", "antrenman", "antrenör", "direk", "crochet", "jab", "kombinezon",
            "halka", "sarı kart", "sporcu", "maç", "yarışma", "raund kızıştırma", "son vuruş", "ağırsiklet", "yarı ağırsiklet",
            "orta siklet", "yarı orta siklet", "hafif siklet", "yarı hafif siklet", "süper hafif siklet", "hafif ağırsiklet",
            "yarı hafif ağırsiklet", "süper ağırsiklet", "duyarlılık", "teknik nakavt", "teknik üstünlük",
            "yıldız hakem", "puanlama hakemi", "hakem", "kırmızı köşe", "mavi köşe", "kayış", "pençe",
            "güvenlik", "kayıt", "boks eldiveni", "yumruk", "savunma", "saldırı", "kanama", "baş döndürücü",
            "boksör", "rakip", "ayak hızı", "ayak teknikleri", "direkt yumruk", "vücut hücumu", "golf", "put", "green", "par", "hazard", "backspin", "bunker", "tee", "fairway", "swing", "putter",
            "golf topu", "golf sopası", "green sahası", "hatchback", "kulüp", "düz vuruş", "fade", "hook", "slice",
            "draw", "fade", "handicap", "hafta sonu golfçüsü", "honor", "hücum vuruşu", "bogey", "toprak", "kart",
            "vuruş sayısı", "birey", "eagle", "topuk vuruşu", "ortak", "insanlar", "in", "insanlar", "insanlar",
            "çiftler", "putt", "sahil", "vuruş kontrollü", "rüzgar", "golfer", "pazar günü golfçüsü", "kendi toprak",
            "par oyunu", "golfer", "par oyunu", "küçük golfçü", "güç vuruşu", "golf arabası", "golf çantası",
            "golf eldiveni", "golf ayakkabısı", "golf kıyafeti", "golf şapkası", "golfçü kartı"};

        String[] eğitim = {"eğitim", "öğrenci", "öğretmen", "sınıf", "okul", "ders", "kitap", "defter", "kalem", "tahta", "yazılı",
            "sınav", "not", "proje", "tez", "laboratuvar", "fen", "matematik", "türkçe", "sosyal bilgiler", "fen bilgisi",
            "coğrafya", "tarih", "müzik", "resim", "beden eğitimi", "rehberlik", "okuma", "yazma", "konuşma", "dil",
            "dilbilgisi", "kelime", "cümle", "paragraf", "ifade", "anlatım", "konu", "kavram", "deneme", "makale",
            "roman", "şiir", "edebiyat", "dil sanatları", "öykü", "drama", "diksiyon", "anlatım becerileri", "problem çözme",
            "eleştirel düşünme", "bilim", "fenomen", "deney", "hipotez", "madde", "enerji", "hücre", "canlı", "bitki",
            "hayvan", "ekosistem", "genetik", "evrim", "iklim", "atmosfer", "matematiksel ifadeler", "geometri", "cebir",
            "fonksiyon", "grafik", "trigonometri", "istatistik", "molekül", "iyon", "kimyasal reaksiyon", "asit", "baz",
            "element", "periyodik cetvel", "madde döngüleri", "sosyal bilimler", "psikoloji", "sosyoloji", "antropoloji",
            "ekonomi", "politika", "tarih araştırmaları", "insan hakları", "hukuk", "filozofi", "mantık", "etik", "estetik",
            "din kültürü ve ahlak bilgisi", "yabancı dil", "bilgisayar bilimleri", "programlama", "veri analizi", "mühendislik",
            "teknoloji", "eğitim planlaması", "öğrenme ortamları", "sınıf yönetimi", "eğitim araştırmaları", "pedagoji",
            "andragoji", "eğitim teknolojisi", "öğrenci başarısı", "eğitim programları", "kültür ve eğitim", "bilim eğitimi",
            "teknoloji tabanlı öğretim", "meslek eğitimi", "beceri geliştirme", "yetenek geliştirme", "bilgisayar destekli eğitim",
            "oyun tabanlı öğrenme", "zeka gelişimi", "sosyal beceri", "motivasyonel stratejiler", "duygusal zeka", "eğitimde inovasyon",
            "eğitimde teknoloji kullanımı", "öğretmen eğitimi programları", "öğrenci merkezli öğrenme", "eğitim psikolojisi",
            "eğitim politikaları", "eğitimde çeşitlilik", "eğitim reformları", "öğretmen performansı", "okul yönetimi",
            "okul-aile işbirliği", "öğrenci değerlendirmesi", "sınav stratejileri", "öğrenci rehberliği", "disiplin yönetimi",
            "öğrenci katılımı", "öğrenci disiplini", "eğitim teknolojisi araçları", "e-öğrenme", "bilgisayar tabanlı eğitim",
            "uzaktan eğitim", "e-kitap", "e-öğretmen", "e-öğrenci", "e-okul", "e-devlet", "e-posta", "e-imza", "e-ticaret", "e-iş",
            "e-devir", "enerji eğitimi", "çevre eğitimi", "sürdürülebilirlik eğitimi", "küresel eğitim", "uluslararası eğitim",
            "yabancı dil eğitimi", "dil öğrenme stratejileri", "dil değişimi", "dil engelleri", "dil gelişimi", "dil bozuklukları",
            "dil terapisi", "dil kursları", "dil sertifikaları", "dil bilgisi kuralları", "dil iletişimi", "dil öğretimi", "dil sanatları",
            "moleküler biyoloji", "ekonomi politikası", "uygulamalı fizik", "astronomi", "astrofizik", "antik yunan tarihi",
            "sanat tarihi", "arkeoloji", "jeoloji", "jeofizik", "dil çeşitliliği", "toplum bilgisi", "okuma stratejileri",
            "eğitim psikolojisi", "öğrenme kuramları", "sosyal adalet", "medya eğitimi", "bilgi teknolojileri", "dijital okuryazarlık",
            "oyun tabanlı öğrenme", "zeka oyunları", "öğrenci merkezli öğretim", "eğitim yönetimi", "okul yönetimi",
            "öğrenci değerlendirmesi", "öğretim teknolojileri", "bilimsel yöntem", "istatistiksel analiz", "biyoetik", "uzaktan eğitim",
            "eğitim politikaları", "öğretmen eğitimi", "öğrenci motivasyonu", "yaratıcı düşünce", "eleştirel okuma", "eleştirel düşünce",
            "kültürel çeşitlilik", "insan gelişimi", "çocuk psikolojisi", "ergen gelişimi", "yetişkin eğitimi", "özel eğitim",
            "öğrenme güçlükleri", "işbirlikçi öğrenme", "çevre eğitimi", "eğitim finansmanı", "çocuk edebiyatı", "fen eğitimi",
            "matematik eğitimi", "sosyal bilgiler eğitimi", "edebiyat eleştirisi", "ingilizce dilbilgisi", "almanca dil kursu",
            "fransızca dil yeterliliği", "İspanyolca dil öğrenimi", "çince dil kursu", "japonca dil bilgisi", "sanat eğitimi",
            "müzik eğitimi", "tiyatro sanatları", "resim teknikleri", "dans eğitimi", "beden eğitimi", "yoga pratiği", "sağlık eğitimi",
            "beslenme bilgisi", "cinsel eğitim", "madde bağımlılığı önleme", "afet eğitimi", "ilk yardım eğitimi", "kariyer geliştirme",
            "girişimcilik eğitimi", "iş sağlığı ve güvenliği", "insan kaynakları yönetimi", "pazarlama stratejileri",
            "finansal okuryazarlık", "bilim ve teknoloji etikleri", "yaratıcı yazma", "dil oyunları", "fenomenoloji"};

        String[] teknoloji = {"teknoloji", "internet", "bilgisayar", "yazılım", "donanım", "mobil", "uygulama", "program", "veri",
            "bulut", "güvenlik", "ağ", "cihaz", "teknoloji", "dijital", "e-posta", "web", "tarayıcı", "site", "web sitesi", "platform",
            "sosyal medya", "mobil uygulama", "mobil cihaz", "akıllı telefon", "tablet", "bilgisayar ağı", "bağlantı",
            "hız", "depolama", "dosya", "dosya paylaşımı", "güncelleme", "yükleme", "indirme", "yükseltme", "ekran",
            "klavye", "fare", "yazıcı", "tarayıcı", "bilgi teknolojisi", "yapay zeka", "sanal gerçeklik",
            "artırılmış gerçeklik", "robot", "drone", "sensör", "otomasyon", "ışıklandırma", "enerji verimliliği",
            "akıllı ev", "akıllı şehir", "nesnelerin interneti", "IoT", "algoritma", "veri analitiği", "yapay zeka",
            "makine öğrenimi", "blok zinciri", "kripto para", "dijital cüzdan", "şifreleme", "parola", "biyometrik",
            "güvenlik duvarı", "virüs", "kötü amaçlı yazılım", "fidye yazılımı", "siber saldırı", "siber güvenlik",
            "hack", "güvenlik açığı", "savunma", "yedekleme", "felaket kurtarma", "veri merkezi", "bulut bilişim",
            "hibrit bulut", "büyük veri", "veri madenciliği", "nesnelerin interneti", "akıllı nesneler", "uzaktan çalışma",
            "video konferans", "sanal toplantı", "e-ticaret", "dijital pazarlama", "online alışveriş", "e-ticaret sitesi",
            "mobil ödeme", "dijital cüzdan", "elektronik ticaret", "çevrimiçi", "çevrimdışı", "mobil ticaret",
            "e-ticaret platformu", "müşteri hizmetleri", "müşteri deneyimi", "müşteri ilişkileri yönetimi", "CRM",
            "analitik", "veri analizi", "iş zekası", "raporlama", "dashboard", "endüstri 4.0", "dijital dönüşüm",
            "teknoloji trendleri", "yenilik", "AR-GE", "patent", "intelektüel mülkiyet", "lisans", "marka", "logo",
            "tasarım", "patent başvurusu", "patent hakkı", "teknoloji transferi", "prototip", "üretim", "robotik üretim",
            "3D yazıcı", "nanoteknoloji", "enerji teknolojisi", "yeşil teknoloji", "çevre teknolojisi", "sürdürülebilirlik",
            "enerji verimliliği", "yenilenebilir enerji", "elektrikli araçlar", "otonom araçlar", "yapay et",
            "biyoteknoloji", "genetik mühendislik", "biyomalzeme", "sağlık teknolojisi", "telemedicine", "dijital sağlık",
            "telehealth", "sağlık bilgi sistemleri", "elektronik hasta kaydı", "teşhis", "tedavi", "ilaç", "aşı",
            "biyolojik", "gen terapisi", "nano tıp", "tıbbi cihaz", "tıbbi görüntüleme", "MRI", "CT tarama", "X-ray",
            "biyoelektronik", "beyin-bilgisayar arayüzü", "giyilebilir teknoloji", "akıllı giyim", "akıllı saat",
            "fitness takipçisi", "spor teknolojisi", "e-spor", "oyun", "oyun konsolu", "bilgisayar oyunu", "sanal oyun",
            "artırılmış gerçeklik oyunu", "kuantum bilgisayar", "süper bilgisayar", "hesaplama gücü", "bellek",
            "depolama", "SSD", "RAM", "mikroişlemci", "grafik kartı", "anakart", "soğutma sistemleri", "bilgisayar montajı",
            "yazılım geliştirme", "programlama dili", "kodlama", "yazılım mühendisliği", "açık kaynak", "kod tabanı",
            "derleme", "yorumlama", "API", "sürüm kontrolü", "günlükleme", "bellek yönetimi", "hata ayıklama", "veritabanı",
            "SQL", "NoSQL", "veri tabanı yönetimi", "ağ güvenliği", "güvenlik duvarı", "saldırı tespiti",
            "güvenlik protokolleri", "ağ yönetimi", "yönlendirme", "anahtarlayıcı", "WAN", "LAN", "VPN", "IP adresi",
            "DNS", "DHCP", "TCP/IP", "HTTP", "HTTPS", "protokol", "fiber optik", "kablosuz iletişim", "Wi-Fi", "Bluetooth",
            "NFC", "RFID", "5G", "mobil ağ", "iletişim teknolojisi", "ses iletişimi", "görüntü iletişimi", "video konferans",
            "VoIP", "telekomünikasyon", "haberleşme", "sosyal ağ", "paylaşım ekonomisi", "çevrim içi topluluk", "blog",
            "vlog", "podcast", "forum", "çevrim içi etkileşim", "içerik oluşturma", "medya", "dijital medya", "multimedya",
            "video", "ses", "resim", "grafik tasarım", "animasyon", "sanal gerçeklik içeriği", "artırılmış gerçeklik içeriği",
            "oyun içeriği", "eğlence teknolojisi", "film", "müzik", "televizyon", "yayın", "akıllı televizyon", "akıllı hoparlör",
            "ses teknolojisi", "müzik akışı", "podcasting", "radyo", "ses kaydı", "ses efekti", "enstrümantasyon", "mikrofon",
            "müzik prodüksiyonu", "ses miksajı", "stüdyo", "konser", "etkinlik teknolojisi", "bilet satışı",
            "etkinlik yönetimi", "sanatif gerçeklik", "kültür ve sanat teknolojisi", "dijital sanat", "dijital müze",
            "sanal tur", "eğitim teknolojisi", "uzaktan eğitim", "e-öğrenme", "dijital öğrenme", "öğrenci yönetim sistemi",
            "öğretim yöntemleri", "öğrenci değerlendirmesi", "sınav", "sertifika", "derece", "diploma", "online kurs",
            "eğitim platformu", "öğrenci kaydı", "eğitim analitiği", "öğrenci bilgi sistemi", "öğrenci başarıları",
            "öğrenci katılımı", "öğretmen", "eğitim programı", "öğrenci destek", "pedagoji", "eğitim teknolojisi araçları",
            "öğrenci motivasyonu", "öğrenci başarısı"};

        String[] bilim = {"bilim", "araştırma", "deney", "teori", "hipotez", "veri", "analiz", "sonuç",
            "laboratuvar", "keşif", "gözlem", "hipotez", "madde", "enerji", "atom", "molekül",
            "kimya", "fizik", "biyoloji", "genetik", "hücre", "organizma", "evrim", "ekosistem",
            "ekoloji", "habitat", "DNA", "RNA", "protein", "enzim", "mutasyon", "evren", "galaksi",
            "yıldız", "gezegen", "astronomi", "matematik", "istatistik", "hesaplama", "model",
            "formül", "denklem", "fiziksel", "kimyasal", "biyolojik", "ekonomi", "psikoloji",
            "sosyoloji", "antropoloji", "tıp", "hastalık", "sağlık", "genetik", "genom",
            "canlılık", "ölüm", "insan", "hayvan", "bitki", "mikroskop", "teleskop", "elektron",
            "proton", "nötron", "elektrik", "manyetizma", "kuvvet", "momentum", "kütle", "enerji",
            "potansiyel", "kinetik", "dalgalar", "optik", "radyasyon", "ışık", "ses", "termal",
            "basınç", "sıvı", "gaz", "katı", "madde", "malzeme", "mühendislik", "teknoloji",
            "inovasyon", "robot", "yapay zeka", "bilgisayar", "programlama", "algoritma",
            "veritabanı", "ağ", "internet", "yazılım", "donanım", "elektronik", "nano",
            "biyoteknoloji", "nanoteknoloji", "ekonomi", "finans", "ticaret", "pazarlama",
            "strateji", "rekabet", "tüketici", "üretim", "endüstri", "işletme", "liderlik",
            "organizasyon", "motivasyon", "iletişim", "kültür", "tarih", "arkeoloji", "antik",
            "medeniyet", "dil", "iletişim", "konuşma", "yazım", "edebiyat", "şiir", "roman",
            "tiyatro", "müzik", "resim", "heykel", "mimari", "tasarım", "sanat", "estetik",
            "felsefe", "mantık", "etik", "ahlak", "metafizik", "epistemoloji", "ontoloji",
            "rasyonalite", "irade", "özgürlük", "adalet", "hak", "devlet", "demokrasi", "politika",
            "sosyal", "kültür", "toplum", "aile", "eğitim", "öğrenme", "öğretim", "öğrenci",
            "öğretmen", "okul", "üniversite", "bilgi", "öğrenme", "öğrenci", "öğretmen", "okul",
            "üniversite", "bilgi", "teknoloji", "bilgi", "bilgisayar", "internet", "yazılım",
            "donanım", "programlama", "algoritma", "veritabanı", "ağ", "web", "tasarım",
            "geliştirme", "inovasyon", "proje", "yönetim", "liderlik", "strateji", "pazarlama",
            "finans", "ekonomi", "ticaret", "endüstri", "işletme", "organizasyon", "motivasyon",
            "iletişim", "kültür", "sanat", "edebiyat", "müzik", "resim", "heykel", "mimari",
            "tasarım", "estetik", "felsefe", "mantık", "etik", "ahlak", "metafizik", "epistemoloji",
            "ontoloji", "rasyonalite", "irade", "özgürlük", "adalet", "hak", "devlet", "demokrasi",
            "politika", "sosyal", "kültür", "toplum", "aile", "eğitim", "öğrenme", "öğretim",
            "öğrenci", "öğretmen", "okul", "üniversite", "bilgi", "teknoloji", "bilgi",
            "bilgisayar", "internet", "yazılım", "donanım", "programlama", "algoritma",
            "veritabanı", "ağ", "web", "tasarım", "geliştirme", "inovasyon", "proje", "yönetim",
            "liderlik", "strateji", "pazarlama", "finans", "ekonomi", "ticaret", "endüstri",
            "işletme", "organizasyon", "motivasyon", "iletişim", "kültür", "sanat", "edebiyat",
            "müzik", "resim", "heykel", "mimari", "tasarım", "estetik", "felsefe", "mantık",
            "etik", "ahlak", "metafizik", "epistemoloji", "ontoloji", "rasyonalite", "irade",
            "özgürlük", "adalet", "hak", "devlet", "demokrasi", "politika", "sosyal", "kültür",
            "toplum", "aile", "eğitim", "öğrenme", "öğretim", "öğrenci", "öğretmen", "okul",
            "üniversite"};

        String[] sağlık = {"sağlık", "hastalık", "ilaç", "doktor", "hasta", "tedavi", "ağrı", "ateş", "kan basıncı",
            "şeker", "tansiyon", "diyet", "egzersiz", "fitness", "vitamin", "mineral", "sağlıklı beslenme",
            "diş sağlığı", "göz sağlığı", "kulak sağlığı", "alerji", "aşı", "antibiyotik", "bağışıklık",
            "stres", "depresyon", "anksiyete", "psikoloji", "ruh sağlığı", "genetik", "kalp", "akciğer",
            "karaciğer", "böbrek", "sindirim", "mide", "bağırsak", "deri", "kan", "kemik", "kas", "eklem",
            "omurga", "omurilik", "nöroloji", "endokrinoloji", "kardiyoloji", "onkoloji", "romatoloji",
            "ortopedi", "fizyoterapi", "rehabilitasyon", "kilo", "obezite", "zayıflama", "saç sağlığı",
            "cilt bakımı", "solunum", "uyku", "iyileşme", "iyilik hali", "iyileştirme", "rahatlama",
            "kan dolaşımı", "organ", "hücre", "enfeksiyon", "immünoloji", "ergenlik", "menopoz",
            "cinsel sağlık", "gebelik", "doğum", "emzirme", "bebek", "çocuk sağlığı", "ergen sağlığı",
            "yaşlılık", "alzheimer", "parkinson", "diyabet", "hipertansiyon", "kolesterol", "artrit",
            "osteoporoz", "astım", "grip", "soğuk algınlığı", "alerji", "solunum yolu", "kan pıhtıları",
            "anemi", "alerji", "diyabet", "osteoartrit", "psoriasis", "skleroz", "hepatit", "alerjik reaksiyon",
            "diş çürüğü", "diş fırçalama", "diş ipi", "diş hekimi", "gözlük", "lens", "kanser", "leukemia",
            "melanom", "katarakt", "iltihap", "egzama", "güneş koruyucu", "sağlık sigortası", "sigara",
            "alkol", "uyuşturucu", "rehabilitasyon", "organ bağışı", "kan bağışı", "ağrı kesici", "antidepresan",
            "anksiyolitik", "psikiyatri", "ilaç etkileşimi", "alerji testi", "ultrason", "MRI", "röntgen",
            "kan testi", "biyopsi", "operasyon", "ameliyat", "immün sistem", "hormon", "kanser taraması",
            "sağlık kontrolü", "sağlık taraması", "reçete", "ilaç dozu", "doğal tedavi", "homeopati",
            "akupunktur", "refleksoloji", "yoga", "meditasyon", "tai chi", "pilates", "aerobik",
            "kalp atış hızı", "nefes alma", "yoga matı", "spor ayakkabısı", "egzersiz programı", "zumba",
            "kardiyo", "ağırlık antrenmanı", "kuvvet antrenmanı", "kardiyo vasküler", "esneme", "egzersiz rutini",
            "besin", "protein", "karbonhidrat", "yağ", "lif", "mineral", "vitamin", "su", "diyetisyen",
            "beslenme uzmanı", "kalori", "sağlıklı tarifler", "fast food", "açlık", "açlık hissi", "tatlandırıcı",
            "GDO", "organik", "gluten", "laktoz", "vegan", "vejetaryen", "glisemik indeks", "diyet planı",
            "su tüketimi", "uyku düzeni", "uyku apnesi", "horlama", "uyku hijyeni", "rüya", "melatonin",
            "insomnia", "kafein", "akıl sağlığı", "terapi", "ruh terapisi", "stres yönetimi",
            "rahatlama teknikleri", "meditasyon", "yoga", "depresyon tedavisi", "anksiyete tedavisi",
            "bipolar bozukluk", "obsesif kompulsif bozukluk", "panik atak", "şizofreni", "terapist", "psikiyatrist",
            "psikolog", "danışmanlık", "grup terapisi", "aile terapisi", "çift terapisi", "çocuk terapisi",
            "ergen terapisi", "ruh sağlığı destek grupları", "stres azaltma", "mükemmeliyetçilik", "ilişki",
            "aile ilişkileri", "arkadaşlık", "evlilik", "boşanma", "aile planlaması", "cinsellik",
            "cinsel sağlık", "gebelik planlaması", "doğum kontrolü", "cinsel yolla bulaşan enfeksiyonlar",
            "HIV/AIDS", "sağlıklı ilişkiler", "empati", "iletişim", "sosyal bağlantı", "arkadaşlık ilişkileri",
            "sosyal destek", "toplum sağlığı", "kamu sağlığı", "epidemiyoloji", "aşı kampanyası", "sağlık eğitimi",
            "hijyen", "temizlik", "enfeksiyon kontrolü", "su arıtma", "hava kalitesi", "çevre sağlığı",
            "biyolojik tehlikeler", "radyasyon", "kirlilik", "çöp yönetimi", "geri dönüşüm", "yeşil yaşam",
            "doğa yürüyüşü", "yüzme", "bisiklet", "koşu", "yoga", "tai chi", "pilates", "aerobik",
            "fitness", "egzersiz programı", "spor yaralanmaları", "fizik tedavi", "rehabilitasyon",
            "spor psikolojisi", "spor beslenmesi", "koçluk", "performans artırma", "su içme",
            "elektronik sigara", "sigara bırakma", "alkol rehabilitasyonu", "uyuşturucu rehabilitasyonu",
            "narkotikler", "alkolizm", "madde bağımlılığı", "sigara içme", "sigara bırakma programı",
            "alkol tedavisi", "uyuşturucu tedavisi", "rehabilitasyon merkezi", "madde kullanım bozukluğu",
            "sağlıklı yaşam", "sağlıklı yaşam tarzı", "sağlıklı beslenme", "fiziksel aktivite",
            "stressiz yaşam", "dinlenme", "sağlıklı alışkanlıklar", "sağlıklı uyku", "sağlıklı ilişkiler",
            "sağlıklı çevre", "sağlıklı yaşlanma", "sağlıklı cilt", "saç sağlığı", "doğal güzellik",
            "sağlıklı kilo kaybı", "sağlıklı kilo kontrolü"};

        String[] müzik = {"müzik", "akor", "albüm", "aranje", "arka plan", "arp", "arpej", "bando", "bas", "bas davul", "bass",
            "bateri", "beste", "bileşim", "birey", "bölüm", "caz", "çalgı", "çello", "davul", "değişim",
            "denge", "dinleti", "dizgi", "dizi", "dörtlük", "düet", "düzenleme", "efekt", "elektro gitar",
            "enstrüman", "eser", "etkileşim", "ezgi", "fagot", "falsetto", "flüt", "fon müziği", "frekans",
            "gam", "gitar", "güfte", "harmoni", "hız", "icra", "ikili", "improvisasyon", "irtifa", "isabet",
            "işitsel", "jingle", "keman", "klarnet", "klavye", "koro", "kromatik", "kuyruklu piyano",
            "küçük beşlik", "lider", "makam", "marş", "melodi", "metalofon", "metronom", "mey", "mikrofon",
            "minör", "mix", "müzikalite", "nefes", "not", "nota", "oktav", "orkestra", "ölçü", "özel efekt",
            "parti", "performans", "perdesiz gitar", "piyano", "plak", "pop", "portamento", "progresyon",
            "ralli", "rap", "rekabet", "remiks", "repertuar", "ritim", "rock", "rondo", "rüzgar çalgıları",
            "sadelik", "sahne", "şan", "şarkı", "ses", "ses efekti", "ses kaydı", "ses mühendisi",
            "ses tasarımı", "solfej", "solist", "sone", "soprano", "soundcheck", "staccato", "stüdyo", "süit",
            "symphony", "temp", "tenor", "tema", "tempo", "timbal", "timbre", "ton", "transpozisyon", "tremolo",
            "trio", "trombon", "trompet", "tuba", "tını", "tiz", "topluluk", "transpozisyon", "trap", "tını",
            "tutarlılık", "tuşe", "tını", "üçlü", "üslup", "vadi", "vals", "vokal", "vuruş", "yaylı", "yedili",
            "yerel müzik", "yetenek", "yorum", "zarafet", "zil", "zurna", "akustik", "altyapı", "amfi", "anahtar",
            "anlatı", "ara geçiş", "aranje", "arpej", "arşiv", "asansör müziği", "atmosfer", "ayar", "ayin", "balad",
            "balkan müziği", "balkan müziği", "bant", "bağlama", "bayan vokal", "beste", "biberon", "blues", "bongo",
            "bordo", "brass", "büyüleyici", "caz", "cazağacı", "celtic müziği", "cimbalom", "çalgı", "çalma", "çekirdek",
            "çift", "çıkış", "çizim", "dalga", "dans", "davet", "davul", "def", "değerlendirme", "demi-semiton", "depo",
            "derece", "detune", "diatonik", "dikte", "dizi", "düet", "düzenleme", "düzenleme", "efekt", "eğitim",
            "elektrik", "elektronik müzik", "entonasyon", "epik", "etki", "fantezi", "fermata", "flamenco", "flugelhorn",
            "folk", "form", "fragment", "fraz", "fren", "fuar", "füzyon", "gam", "geleneksel", "gelgit", "gemi",
            "genişleme", "geriye sayım", "glissando", "göbek", "görsel", "gösteri", "groove", "güfte", "gürültü",
            "güzellik", "gypsy", "haberci", "halay", "halk müziği", "harmoni", "hava", "hız", "hikaye", "hiphop",
            "hissiyat", "hit", "horn", "hüzün", "içsel", "ilham", "ilmek", "improvize", "inovasyon", "intro",
            "introtone", "istek", "itme", "jaz", "jenerik", "jingle", "kabare", "kabuki", "kadans", "kafiye",
            "kafiye", "kahkaha", "kanvas", "kapak", "karma", "kasvet", "kavram", "kayıt", "kaynak", "kayseri",
            "klip", "klip", "koda", "koro", "koreografi", "kostüm", "köprü", "kült", "kulüp", "kurulum",
            "kuyruklu piyano", "lansman", "Latin", "leitmotiv", "live", "lo-fi", "logos", "lütfen", "makam",
            "makro", "manzara", "marş", "maske", "melodik", "melodrama", "metal", "minimalizm", "miksaj",
            "modülasyon", "motif", "mücadele"};

        String[] eğlence = {"oyun", "eğlence", "dans", "film", "tiyatro", "konser", "parti", "kahkaha", "komedi", "heyecan",
            "macera", "seyahat", "gece", "arkadaş", "aile", "piknik", "festival", "sergi", "sanat", "kitap",
            "yemek", "içki", "barbekü", "kamp", "kahve", "çay", "hobi", "gitar", "resim", "fotoğraf", "spor",
            "bisiklet", "yürüyüş", "koşu", "yoga", "meditasyon", "bahçe", "pikap", "plaj", "tatil", "lunapark",
            "roller coaster", "oyun kartı", "bilardo", "dart", "satranç", "sudoku", "bulmaca", "video oyunu",
            "konsol", "bilgisayar oyunu", "online oyun", "sosyal medya", "takım oyunu", "gülme", "eğlenceli",
            "mutlu", "neşeli", "enerjik", "coşkulu", "spontan", "sürükleyici", "sıcak hava balonu", "jet ski",
            "snowboard", "kayak", "dans yarışması", "kıyafet partisi", "kostüm", "makyaj", "şaka", "gizem",
            "kutlama", "yılbaşı", "doğum günü", "yarışma", "ödül", "özgürlük", "rüya", "macera parkı", "dalış",
            "tırmanma", "yelken", "balık tutma", "safari", "macera sporları", "serbest dalış", "rüzgar sörfü",
            "su kayağı", "kitesurf", "paraşüt", "kır gezisi", "bisiklet turu", "keşif", "gözlem", "astronomi",
            "teleskop", "gökyüzü", "gök bilim", "kamp ateşi", "gece yürüyüşü", "izcilik", "kuş gözlem", "fotoğrafçılık",
            "aile oyunları", "mangal partisi", "dans dersi", "maske balosu", "fotoğraf turu", "gösteri", "pazarlama",
            "tatlı", "pizza", "pasta", "dondurma", "kahvaltı", "brunch", "şarap", "bira", "kokteyl", "gece kulübü",
            "bar", "kıyafet", "alışveriş", "moda", "kozmetik", "güzellik", "saç stil", "makyaj sanatı", "parfüm",
            "takı", "aksesuar", "elbise", "ayakkabı", "çanta", "şapka", "gözlük", "kitap okuma", "hikaye anlatma",
            "şiir yazma", "resim yapma", "el sanatları", "seramik", "ahşap oyma", "bahçe tasarımı", "bonsai",
            "ev dekorasyonu", "DIY projeler", "taş boyama", "ahşap boyama", "antikacılık", "koleksiyon", "pul koleksiyonu",
            "eski eşya", "retro", "vintage", "modellik", "dans yarışması", "şarkı söyleme", "enstrüman çalma", "tiyatro oyunu",
            "drama", "pantomim", "kabare", "akrobasi", "jonglörlük", "sihirbazlık", "palyaço", "kukla", "balon hayvan",
            "graffiti", "sokak sanatı", "heykel", "enstalasyon", "performans sanatı", "drama", "balo",
            "sokak gösterisi", "karaoke", "bilim kurgu", "korku", "gerilim", "aksiyon", "dram", "romantik", "bilim",
            "belgesel", "animasyon", "çizgi film", "anime", "klasik", "müzikal", "opera", "konser", "rock", "pop",
            "rap", "hip-hop", "elektronik müzik", "caz", "blues", "country", "folk", "reggae", "metal", "disko",
            "house", "techno", "dubstep", "funk", "soul", "R&B", "dans müziği", "80'ler müziği", "90'lar müziği", "nostalji",
            "radyo", "müzik festivali", "konser", "albüm", "şarkı sözleri", "enstrüman", "nota", "orkestra", "kor", "opera",
            "bale", "tango", "salsa", "breakdance", "hip-hop dansı", "modern dans", "caz dansı", "halk dansı", "Latin dansı",
            "flört", "aşk", "romantizm", "çıkma teklifi", "evlenme teklifi", "düğün", "balayı", "flört etme", "ilişki",
            "arkadaşlık", "aile bağları", "bağlılık", "sevgi", "öpüşme", "sarılma", "gülme", "sohbet", "espri", "mizah",
            "eğlenceli oyunlar", "bilmece", "şaka", "karaoke", "kahkaha", "esprili", "taklit", "makyajlı yüz", "parti oyunları",
            "maske oyunları", "hızlı tempolu", "heyecan verici", "canlı müzik", "şenlik", "serbest zaman", "kreatif", "keşif",
            "özgürlük", "rüya", "macera parkı", "dalış", "tırmanma", "yelken", "balık tutma", "safari", "macera sporları",
            "serbest dalış", "rüzgar sörfü", "su kayağı", "kitesurf", "paraşüt", "kır gezisi", "bisiklet turu", "keşif",
            "gözlem", "astronomi", "teleskop", "gökyüzü", "gök bilim", "kamp ateşi", "gece yürüyüşü", "izcilik", "kuş gözlem",
            "fotoğrafçılık", "aile oyunları", "mangal partisi", "dans dersi", "maske balosu", "fotoğraf turu", "gösteri",
            "pazarlama", "tatlı", "pizza", "pasta", "dondurma", "kahvaltı", "brunch", "şarap", "bira", "kokteyl", "gece klübü",
            "bar", "kıyafet", "alışveriş", "moda", "kozmetik", "güzellik", "saç stil", "makyaj sanatı", "parfüm", "takı",
            "aksesuar", "elbise", "ayakkabı", "çanta", "şapka", "gözlük", "kitap okuma", "hikaye anlatma", "şiir yazma",
            "resim yapma", "el sanatları", "seramik", "ahşap oyma", "bahçe tasarımı", "bonsai", "ev dekorasyonu", "DIY projeler",
            "taş boyama", "ahşap boyama", "antikacılık", "koleksiyon", "pul koleksiyonu", "eski eşya", "retro", "vintage",
            "modellik", "dans yarışması", "şarkı söyleme", "enstrüman çalma", "tiyatro oyunu", "drama", "pantomim", "kabare",
            "akrobasi", "jonglörlük", "sihirbazlık", "palyaço", "kukla", "balon hayvan", "graffiti", "sokak sanatı", "heykel",
            "enstalasyon", "performans sanatı", "dans drama", "maske balo", "sokak gösterisi", "karaoke", "bilim kurgu", "korku",
            "gerilim", "aksiyon", "dram", "romantik", "bilim", "belgesel", "animasyon", "çizgi film", "anime", "klasik",
            "müzikal", "opera", "konser", "rock", "pop", "rap", "hip-hop", "elektronik müzik", "caz", "blues", "country",
            "folk", "reggae", "metal", "disko", "house", "techno", "dubstep", "funk", "soul", "R&B", "dans müziği",
            "80'ler müziği", "90'lar müziği", "nostalji", "radyo", "müzik festivali", "konser", "albüm", "şarkı sözleri",
            "enstrüman", "nota", "orkestra", "kor", "opera", "bale", "tango", "salsa", "breakdance", "hip-hop dansı",
            "modern dans", "caz dansı", "halk dansı", "Latin dansı", "flört", "aşk", "romantizm", "çıkma teklifi",
            "evlenme teklifi", "düğün", "balayı", "flört etme", "ilişki", "arkadaşlık", "aile bağları", "bağlılık",
            "sevgi", "öpüşme", "sarılma", "gülme", "sohbet", "espri", "mizah", "eğlenceli oyunlar", "bilmece", "şaka",
            "karaoke", "kahkaha", "esprili", "taklit", "makyajlı yüz", "parti oyunları", "maske oyunları", "hızlı tempolu",
            "heyecan verici", "canlı müzik", "şenlik", "serbest zaman", "kreatif", "keşif", "özgürlük", "heyecan verici",
            "etkileyici", "yenilikçi", "renkli", "eşsiz", "enerjik", "coşkulu", "gizemli", "büyüleyici", "romantik", "samimi",
            "sıcak", "dostane", "keyifli", "neşeli", "eğlenceli", "unutulmaz", "güzel", "muhteşem", "harika", "olağanüstü",
            "fantastik", "çarpıcı", "büyüleyici", "ilginç", "eşsiz", "sürükleyici", "unutulmaz"};

        String[] ekonomi = {"para", "ekonomi", "iş", "ticaret", "banka", "dolar", "euro", "kriz", "büyüme", "faiz",
            "gelir", "vergi", "bütçe", "şirket", "hisse", "pazar", "rekabet", "yatırım", "sermaye",
            "işsizlik", "enflasyon", "deflasyon", "ihracat", "ithalat", "cari", "açık", "borsa",
            "finans", "ekonomist", "devlet", "özel", "sektör", "para birimi", "tüketim", "üretim",
            "talep", "arz", "tüketici", "mal", "hizmet", "emtia", "ticaret savaşı", "kriz yönetimi",
            "rezerv", "denge", "likidite", "kur", "gelir dağılımı", "işsizlik oranı", "kalkınma",
            "ekonomik büyüme", "ithalat vergisi", "ihracat teşvikleri", "büyüme oranı", "ticaret dengesi",
            "borsa endeksi", "likit varlık", "işsizlik sigortası", "tüketici güven endeksi", "ekonomik göstergeler",
            "reel gelir", "vergi geliri", "maliyet", "fiyat", "talep elastikiyeti", "mali politika", "para politikası",
            "ekonomik döngü", "para arzı", "para talebi", "banka kredisi", "bütçe açığı", "bütçe fazlası", "kamu borcu",
            "özelleştirme", "rezerv para", "fiyat endeksi", "cari fiyatlar", "satın alma gücü", "döviz kuru", "faiz oranı",
            "likidite yönetimi", "nominal değer", "reel değer", "kurumsal yönetim", "ticaret politikası", "ithalat kotası",
            "mali piyasa", "likidite krizi", "reel faiz", "enflasyon hedeflemesi", "reel kesim", "ekonomik reform",
            "işsizlik sigortası", "sanayi üretimi", "iş gücü", "ekonomik entegrasyon", "para birliği", "serbest ticaret",
            "rekabet politikası", "ekonomik sürdürülebilirlik", "üretim faktörleri", "ekonomik adalet", "kâr marjı",
            "küresel ekonomi", "finansal piyasa", "ekonomik sistem", "yatırım bankası", "merkez bankası", "ticaret bloğu",
            "ithalat vergisi", "dış ticaret", "ekonomik büyüme", "para birimi", "reel gelir", "ekonomik veri", "ekonomik analiz",
            "mali analiz", "ticaret hacmi", "çeyrek dönem", "mevduat", "ulusal gelir", "gayrisafi yurtiçi hasıla", "cari hesap",
            "yatırım fonu", "döviz rezervi", "kamu harcamaları", "özel sektör", "hükümet harcamaları", "talep yönetimi",
            "ekonomik dalgalanma", "sermaye piyasası", "bireysel tasarruf", "finansal kriz", "ekonomik etkileşim",
            "likidite krizi", "tüketici fiyat endeksi", "mal ve hizmetler", "ekonomik durgunluk", "işsizlik sigortası",
            "emek piyasası", "sanayi üretimi", "ekonomik büyüme hızı", "işgücü piyasası", "toplam talep", "üretim faktörleri",
            "üretim maliyeti", "talep artışı", "sermaye birikimi", "gelir dağılımı", "para birimi devalüasyonu", "finansal risk",
            "ekonomik entegrasyon", "cari işlemler dengesi", "mal ve hizmet ticareti", "ithalat vergisi", "ticaret anlaşması",
            "dış ticaret politikası", "ithalat kotaları", "finansal istikrar", "ekonomik eşitsizlik", "mali istikrar",
            "finansal piyasa", "ekonomik kriz", "finansal kurumlar", "ekonomik büyüme", "para politikası", "mali politika",
            "mali piyasa", "borsa endeksi", "döviz kuru", "reel gelir", "enflasyon oranı", "işsizlik oranı", "iş gücü",
            "üretim faktörleri", "sermaye", "girişimcilik", "rekabet", "fiyat", "talep", "arz", "tüketici", "mal", "hizmet",
            "emtia", "ticaret savaşı", "kriz yönetimi", "rezerv", "denge", "likidite", "kur", "gelir dağılımı", "işsizlik sigortası",
            "tüketici güven endeksi", "ekonomik göstergeler", "reel gelir", "vergi geliri", "maliyet", "fiyat", "talep elastikiyeti",
            "mali politika", "para politikası", "ekonomik döngü", "para arzı", "para talebi", "banka kredisi", "bütçe açığı", "bütçe fazlası",
            "kamu borcu", "özelleştirme", "rezerv para", "fiyat endeksi", "cari fiyatlar", "satın alma gücü", "döviz kuru", "faiz oranı",
            "likidite yönetimi", "nominal değer", "reel değer", "kurumsal yönetim", "ticaret politikası", "ithalat kotası", "mali piyasa",
            "likidite krizi", "reel faiz", "enflasyon hedeflemesi", "reel kesim", "ekonomik reform", "işsizlik sigortası", "sanayi üretimi",
            "iş gücü", "ekonomik entegrasyon", "para birliği", "serbest ticaret", "rekabet politikası", "ekonomik sürdürülebilirlik",
            "üretim faktörleri", "ekonomik adalet", "kâr marjı", "küresel ekonomi", "finansal piyasa", "ekonomik sistem", "yatırım bankası",
            "merkez bankası", "ticaret bloğu", "ithalat vergisi", "dış ticaret", "ekonomik büyüme", "para birimi", "reel gelir", "ekonomik veri",
            "ekonomik analiz", "mali analiz", "ticaret hacmi", "çeyrek dönem", "mevduat", "ulusal gelir", "gayrisafi yurtiçi hasıla", "cari hesap",
            "yatırım fonu", "döviz rezervi", "kamu harcamaları", "özel sektör", "hükümet harcamaları", "talep yönetimi", "ekonomik dalgalanma",
            "sermaye piyasası", "bireysel tasarruf", "finansal kriz", "ekonomik etkileşim", "likidite krizi", "tüketici fiyat endeksi",
            "mal ve hizmetler", "ekonomik durgunluk", "işsizlik sigortası", "emek piyasası", "sanayi üretimi", "ekonomik büyüme hızı",
            "işgücü piyasası", "toplam talep", "üretim faktörleri", "üretim maliyeti", "talep artışı", "sermaye birikimi", "gelir dağılımı",
            "para birimi devalüasyonu", "finansal risk", "ekonomik entegrasyon", "cari işlemler dengesi", "mal ve hizmet ticareti", "ithalat vergisi",
            "ticaret anlaşması", "dış ticaret politikası", "ithalat kotaları", "finansal istikrar", "ekonomik eşitsizlik", "mali istikrar",
            "finansal piyasa", "ekonomik kriz"};

        String hayvan[] = {"hayvan", "aslan", "kaplan", "zürafa", "fil", "kurt", "tilki", "ayı", "tavuk", "köpek", "kedi",
            "kuş", "balık", "yılan", "kertenkele", "kaplumbağa", "tavşan", "ördek", "örümcek", "akrep",
            "arı", "karınca", "leopar", "ceylan", "kanguru", "koala", "penguen", "kutup ayısı", "yunus",
            "balina", "tilki", "baykuş", "kartal", "papağan", "flamingo", "maymun", "goril", "zebra",
            "panda", "şahin", "kuğu", "gergedan", "ayı balığı", "yıldız balığı", "kangal", "deve", "at",
            "inek", "koyun", "keçi", "tavuk", "ördek", "kaz", "hindi", "tavus kuşu", "ceylan", "karaca",
            "yabani domuz", "gelincik", "sansar", "puma", "tilki", "sincap", "hamster", "köstebek",
            "kuzgun", "kartal", "leylek", "yaban arısı", "ördek", "pelikan", "orman kartalı", "şahin",
            "albatros", "penguen", "flamingo", "serçe", "muhabbet kuşu", "serçe kuşu", "kırlangıç",
            "kumru", "baykuş", "kartal", "akbaba", "deve kuşu", "leopar", "jaguar", "puma", "çita",
            "tilki", "ayı", "kutup ayısı", "gergedan", "zürafa", "fil", "koala", "kanguru", "goril",
            "maymun", "şempanze", "orangutan", "panda", "timsah", "yılan", "kobra", "piton", "boa yılanı",
            "engerek", "kertenkele", "timsah", "kaplumbağa", "iguana", "salyangoz", "karides", "yengeç",
            "denizanası", "mercan", "denizatı", "balina", "köpek balığı", "vatoz", "morina balığı",
            "somon", "alabalık", "orkinos", "balıkçıl", "martı", "pelikan", "martı", "karabatak", "turna",
            "flamingo", "serçe", "karga", "baykuş", "ispinoz", "kartal", "şahin", "doğan", "kerkenez",
            "baykuş", "papağan", "lor", "muhabbet kuşu", "kakadu", "ara", "flamingo", "kuğu", "ördek",
            "pelikan", "martı", "yunus", "fok", "balina", "morina balığı", "yengeç", "denizanası", "mercan",
            "denizatı", "midye", "istiridye", "karides", "denizyıldızı", "yıldız balığı", "vatoz", "turna balığı",
            "kalkan balığı", "kılıç balığı", "köpek balığı", "yılan balığı", "orman kartalı", "şahin", "akbaba",
            "kartal", "kerkenez", "baykuş", "baykuş", "karabatak", "leylek", "turna", "flamingo", "ispinoz",
            "serçe", "karga", "kartal", "şahin", "kırlangıç", "kumru", "martı", "penguen", "pelikan", "ördek",
            "kaz", "kuğu", "tavus kuşu", "baykuş", "kartal", "şahin", "albatros", "flamingo", "serçe",
            "muhabbet kuşu", "serçe kuşu", "kırlangıç", "kumru", "baykuş", "kartal", "akbaba", "deve kuşu",
            "leopar", "jaguar", "puma", "çita", "tilki", "ayı", "kutup ayısı", "gergedan", "zürafa",
            "fil", "koala", "kanguru", "goril", "maymun", "şempanze", "orangutan", "panda", "timsah",
            "yılan", "kobra", "piton", "boa yılanı", "engerek", "kertenkele", "timsah", "kaplumbağa",
            "iguana", "salyangoz", "karides", "yengeç", "denizanası", "mercan", "denizatı", "balina",
            "köpek balığı", "vatoz", "morina balığı", "somon", "alabalık", "orkinos", "balıkçıl", "martı",
            "pelikan", "martı", "karabatak", "turna", "flamingo", "serçe", "karga", "baykuş", "ispinoz",
            "kartal", "şahin", "doğan", "kerkenez", "baykuş", "papağan", "lor", "muhabbet kuşu", "kakadu",
            "ara", "flamingo", "kuğu", "ördek", "pelikan", "martı", "yunus", "fok", "balina", "morina balığı",
            "yengeç", "denizanası", "mercan", "denizatı", "midye", "istiridye", "karides", "denizyıldızı",
            "yıldız balığı", "vatoz", "turna balığı", "kalkan balığı", "kılıç balığı", "köpek balığı",
            "yılan balığı", "orman kartalı", "şahin", "akbaba", "kartal", "kerkenez"};

        String[] bitki = {"bitki", "ağaç", "çiçek", "yaprak", "kök", "gövde", "tohum", "toprak", "büyüme",
            "fotosentez", "güneş ışığı", "su", "oksijen", "karbon dioksit", "klorofil", "botanik",
            "bahçecilik", "gübre", "yaprak dökme", "çiçeklenme", "tozlaşma", "meyve", "sebze",
            "tarım", "ekosistem", "habitat", "biyoçeşitlilik", "orman", "yağmur ormanı", "dökülen yapraklı",
            "daima yeşil", "çok yıllık", "yıllık", "çimlenme", "üreme", "fotosistem", "transpirasyon",
            "solunum", "besin maddeleri", "adaptasyon", "kuraklık", "zararlı", "yabani ot", "kompost",
            "tropizm", "gövde yumrusu", "budama", "simbiyoz", "mikoriza", "rizom", "vasküler",
            "vasküler olmayan", "kozalaklı", "sucul", "yosun", "liken", "yosunlar", "alg", "bahçe işçiliği",
            "ksilem", "floem", "botanik", "fenotip", "genotip", "sardunya", "taç yaprak", "erkek organ",
            "dişi organ", "nektar", "uç", "polen", "mantar", "alkaloid", "aromatik", "hibrit", "örtü",
            "kuraklığa dayanıklı", "yerli", "invaziv", "herbisit", "sulama", "ayçiçeği", "lale", "gül",
            "zambak", "kaktüs", "eğrelti otu", "yosunlu", "otsu", "tohum", "sucul", "soğan", "etçil",
            "epifit", "sarmasık", "palmiye", "topiary", "arborsanat", "solucan gübresi", "kserofit",
            "zigot", "kotiledon", "yosunlu", "simbiyotik", "petrikor", "arboretum", "orkide", "papatya",
            "sarmasık", "adaçayı", "sarmal", "pestisit", "heliotropizm", "troposfer", "rizosfer", "alkalinite",
            "azot bağlayıcı", "dökülen yapraklı", "otsu", "nektar yiyici", "biyom", "gölgelik", "fotosentetik",
            "stomata", "gübreleme", "yaşlanma", "dökülen yapraklı", "daima yeşil", "su taşıyıcı tabaka",
            "mantar ipliği", "fotosistem", "tozlayıcı", "solunum", "kseriskap", "bahçe işçiliği", "fenotip",
            "genotip", "aşılama", "budama", "kloroz", "fide", "tigmotropizm", "allelopati", "mikoriza",
            "rizom", "transpirasyon", "tropizm", "ksilam", "floem", "vasküler", "vasküler olmayan", "kozalaklı",
            "sucul", "yosun", "liken", "yosunlar", "alg", "bahçe işçiliği", "ksilem", "floem", "botanik",
            "fenotip", "genotip", "sardunya", "taç yaprak", "erkek organ", "dişi organ", "nektar", "uç",
            "polen", "mantar", "alkaloid", "aromatik", "hibrit", "örtü", "kuraklığa dayanıklı", "yerli",
            "invaziv", "herbisit", "sulama", "ayçiçeği", "lale", "gül", "zambak", "kaktüs", "eğrelti otu",
            "yosunlu", "otsu", "tohum", "sucul", "soğan", "etçil", "epifit", "sarmasık", "palmiye",
            "topiary", "arborsanat", "solucan gübresi", "kserofit", "zigot", "kotiledon", "yosunlu",
            "simbiyotik", "petrikor", "arboretum", "orkide", "papatya", "sarmasık", "adaçayı", "sarmal",
            "pestisit", "heliotropizm", "troposfer", "rizosfer", "alkalinite", "azot bağlayıcı", "dökülen yapraklı",
            "otsu", "nektar yiyici", "biyom", "gölgelik", "fotosentetik", "stomata", "gübreleme", "yaşlanma",
            "dökülen yapraklı", "daima yeşil", "su taşıyıcı tabaka", "mantar ipliği", "fotosistem", "tozlayıcı",
            "solunum", "kseriskap", "bahçe işçiliği", "fenotip", "genotip", "aşılama", "budama", "kloroz",
            "fide", "tigmotropizm", "allelopati", "mikoriza", "rizom", "transpirasyon", "tropizm",
            "ksilam", "floem", "vasküler", "vasküler olmayan", "kozalaklı", "sucul", "yosun", "liken",
            "yosunlar", "alg", "bahçe işçiliği", "ksilem", "floem", "botanik", "fenotip", "genotip",
            "sardunya", "taç yaprak", "erkek organ", "dişi organ", "nektar", "uç", "polen", "mantar",
            "alkaloid", "aromatik", "hibrit", "örtü", "kuraklığa dayanıklı", "yerli", "invaziv", "herbisit",
            "sulama", "ayçiçeği", "lale", "gül", "zambak", "kaktüs", "eğrelti otu", "yosunlu", "otsu",
            "tohum", "sucul", "soğan", "etçil", "epifit", "sarmasık", "palmiye", "topiary", "arborsanat",
            "solucan gübresi", "kserofit", "zigot", "kotiledon"};

        String[] araç = {"araç", "araba", "motor", "sürüş", "tekerlek", "hız", "yol", "model", "araç", "benzin", "yakıt",
            "yakıt tüketimi", "şanzıman", "fren", "hızlanma", "direksiyon", "otomatik", "motorlu", "turbo",
            "hibrit", "elektrikli", "güç", "seyir", "kontrol", "süspansiyon", "şasi", "tasarım", "stil",
            "iç mekan", "dış mekan", "özellikler", "güvenlik", "hava yastığı", "koltuk", "ayna", "cam",
            "far", "stop lambası", "sinyal", "korna", "silecek", "lastik", "jant", "alaşım", "gps",
            "navigasyon", "bluetooth", "stereo", "bilgi eğlence sistemi", "gösterge paneli", "konsol",
            "vites", "park", "geri", "nötr", "sürüş", "debriyaj", "manuel", "otomatik", "suv", "sedan",
            "coupe", "hatchback", "cabrio", "kamyonet", "van", "motosiklet", "scooter", "bisiklet", "kask",
            "ehliyet", "plaka", "kayıt", "muayene", "bakım", "yağ", "filtre", "sıvı", "soğutucu", "akü",
            "alternatör", "marş", "buji", "ateşleme", "egzoz", "emisyon", "katalitik", "konvertör", "radyatör",
            "fan", "kayış", "fren", "balata", "disk", "kaliper", "süspansiyon", "şok", "amortisör", "dingil",
            "düzeltme", "çekiş", "kararlılık", "kontrol", "abs", "esp", "hava", "koşullandırma", "ısıtma",
            "havalandırma", "soğutma", "kabin", "konfor", "emniyet kemeri", "hava yastığı", "yan", "perde",
            "çocuk", "kilit", "anahtar", "uzaktan", "merkezi", "kilit", "hırsızlık", "alarm", "cam filmi",
            "gizlilik", "güneş çatısı", "ay çatısı", "bagaj", "yük", "kapasite", "bagaj", "uzay", "kapasite",
            "bagaj", "raf", "çekme", "çekme", "bağlantı", "yük", "römork", "çekme", "yük", "kapasite",
            "süspansiyon", "off-road", "arazi", "dört tekerlekten", "sürüş", "4wd", "awd", "ön tekerlekten",
            "arka tekerlekten", "kompakt", "orta boy", "tam boy", "lüks", "ekonomi", "mpg", "verimlilik",
            "çevre dostu", "hibrit", "elektrikli", "menzil", "şarj", "istasyon", "fiş", "ev", "otonom",
            "sürücüsüz", "teknoloji", "bağlantı", "entegrasyon", "arayüz", "ekran", "dokunmatik ekran", "ses",
            "tanıma", "kamera", "sensör", "radar", "lidar", "yardımcı", "adaptif", "seyir", "şerit", "ayrılma",
            "uyarı", "çarpışma", "kaçınma", "park", "yardım", "park", "sensörler", "radar", "kamera", "lidar",
            "kör", "nokta", "monitörleme", "çapraz", "trafik", "uyarı", "acil", "fren", "sistem", "abs", "ebd",
            "fren", "yardım", "tepesi", "iniş", "kontrol", "çekiş", "kararlılık", "kontrol", "hava yastıkları",
            "ön", "yan", "perde", "diz", "çift", "çoklu", "nokta", "emniyet kemeri", "gerilim", "limiter",
            "çocuk", "güvenlik", "kilit", "immobilizer", "hırsızlık", "sistem", "alarm", "lastik", "basınç",
            "monitörleme", "tpms", "navigasyon", "sistem", "gps", "haritalar", "bluetooth", "bağlantı", "usb",
            "aux", "portlar", "kablosuz", "şarj", "bilgi eğlence", "dokunmatik ekran", "arayüz", "kontroller",
            "direksiyon", "tekerlek", "multimedya", "ses", "ses", "sistem", "hoparlörler", "subwoofer", "amfi",
            "bağlantı", "akıllı telefon", "entegrasyon", "apple", "carplay", "android", "auto", "wi-fi",
            "hotspot", "konfor", "iklim", "kontrol", "hava", "koşullandırma", "ısıtma", "havalandırma",
            "koltuk", "ısıtma", "soğutma", "masaj", "bel desteği", "ayarlanabilir", "güç", "pencereler",
            "koltuklar", "aynalar", "direksiyon", "sütun", "eğme", "teleskopik", "iç mekan", "malzemeler",
            "döşeme", "trim", "kaplama", "ortam", "aydınlatma", "depolama", "gözlük", "tutucu", "göz",
            "bardaklık", "göz", "kargo", "uzay", "hatch", "bagaj"};

        String filepath = "src/main/java/jsonfiles/twitter_data.json";
        ObjectMapper objectMapper = new ObjectMapper();

        CustomLinkedList<String> sporList = new CustomLinkedList<>();
        CustomLinkedList<String> eğitimList = new CustomLinkedList<>();
        CustomLinkedList<String> teknolojiList = new CustomLinkedList<>();
        CustomLinkedList<String> bilimList = new CustomLinkedList<>();
        CustomLinkedList<String> sağlıkList = new CustomLinkedList<>();
        CustomLinkedList<String> müzikList = new CustomLinkedList<>();
        CustomLinkedList<String> eğlenceList = new CustomLinkedList<>();
        CustomLinkedList<String> ekonomiList = new CustomLinkedList<>();
        CustomLinkedList<String> hayvanList = new CustomLinkedList<>();
        CustomLinkedList<String> bitkiList = new CustomLinkedList<>();
        CustomLinkedList<String> araçList = new CustomLinkedList<>();

        try {

            CustomLinkedList<User> userList = new CustomLinkedList<>();

            User[] users = objectMapper.readValue(new File(filepath), User[].class);
            for (User user : users) {
                userList.insertNode(user);
            }

            CustomNode<User> tempuser = userList.getHead();
            while (tempuser != null) {

                String[] tweets = tempuser.data.getTweets();

                int count = 0;
                for (String spor1 : spor) {

                    if (Arrays.toString(tweets).contains(spor1)) {
                        count++;
                    }
                    if (count > 5) {
                        sporList.insertNode(tempuser.data.getUsername());
                        break;
                    }
                }

                count = 0;
                for (String eğitim1 : eğitim) {

                    if (Arrays.toString(tweets).contains(eğitim1)) {
                        count++;
                    }
                    if (count > 5) {
                        eğitimList.insertNode(tempuser.data.getUsername());
                        break;
                    }
                }

                count = 0;
                for (String teknoloji1 : teknoloji) {

                    if (Arrays.toString(tweets).contains(teknoloji1)) {
                        count++;
                    }
                    if (count > 5) {
                        teknolojiList.insertNode(tempuser.data.getUsername());
                        break;
                    }
                }

                count = 0;
                for (String bilim1 : bilim) {

                    if (Arrays.toString(tweets).contains(bilim1)) {
                        count++;
                    }
                    if (count > 5) {
                        bilimList.insertNode(tempuser.data.getUsername());
                        break;
                    }
                }

                count = 0;
                for (String sağlık1 : sağlık) {

                    if (Arrays.toString(tweets).contains(sağlık1)) {
                        count++;
                    }
                    if (count > 5) {
                        sağlıkList.insertNode(tempuser.data.getUsername());
                        break;
                    }
                }

                count = 0;
                for (String müzik1 : müzik) {

                    if (Arrays.toString(tweets).contains(müzik1)) {
                        count++;
                    }
                    if (count > 5) {
                        müzikList.insertNode(tempuser.data.getUsername());
                        break;
                    }
                }

                count = 0;
                for (String eğlence1 : eğlence) {

                    if (Arrays.toString(tweets).contains(eğlence1)) {
                        count++;
                    }
                    if (count > 5) {
                        eğlenceList.insertNode(tempuser.data.getUsername());
                        break;
                    }
                }

                count = 0;
                for (String ekonomi1 : ekonomi) {

                    if (Arrays.toString(tweets).contains(ekonomi1)) {
                        count++;
                    }
                    if (count > 5) {
                        ekonomiList.insertNode(tempuser.data.getUsername());
                        break;
                    }
                }

                count = 0;
                for (String hayvan1 : hayvan) {

                    if (Arrays.toString(tweets).contains(hayvan1)) {
                        count++;
                    }
                    if (count > 5) {
                        hayvanList.insertNode(tempuser.data.getUsername());
                        break;
                    }
                }

                count = 0;
                for (String bitki1 : bitki) {

                    if (Arrays.toString(tweets).contains(bitki1)) {
                        count++;
                    }
                    if (count > 5) {
                        bitkiList.insertNode(tempuser.data.getUsername());
                        break;
                    }
                }

                count = 0;
                for (String araç1 : araç) {

                    if (Arrays.toString(tweets).contains(araç1)) {
                        count++;
                    }
                    if (count > 5) {
                        araçList.insertNode(tempuser.data.getUsername());
                        break;
                    }
                }

                tempuser = tempuser.next;
            }

            CustomHashMap ilgiAlanlariMap = new CustomHashMap();

            ilgiAlanlariMap.put("spor", sporList);
            ilgiAlanlariMap.put("eğitim", eğitimList);
            ilgiAlanlariMap.put("teknoloji", teknolojiList);
            ilgiAlanlariMap.put("bilim", bilimList);
            ilgiAlanlariMap.put("sağlık", sağlıkList);
            ilgiAlanlariMap.put("müzik", müzikList);
            ilgiAlanlariMap.put("eğlence", eğlenceList);
            ilgiAlanlariMap.put("ekonomi", ekonomiList);
            ilgiAlanlariMap.put("hayvan", hayvanList);
            ilgiAlanlariMap.put("bitki", bitkiList);
            ilgiAlanlariMap.put("araç", araçList);

            CustomLinkedList<String> genelList = new CustomLinkedList<>();

            StringBuilder result = new StringBuilder();
            for (String ilgiAlani : ilgiAlanlari) {
                result.append(ilgiAlani).append(" : ");

                genelList = (CustomLinkedList<String>) ilgiAlanlariMap.get(ilgiAlani);

                CustomNode<String> temp1 = genelList.getHead();

                while (temp1 != null) {
                    result.append(temp1.data).append(", ");
                    temp1 = temp1.next;
                }

                result.append("\n\n");

            }

            JTextArea textArea = new JTextArea(result.toString());
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(700, 700));

            JOptionPane.showMessageDialog(null, scrollPane, "İLGİ ALANI - KULLANICI İLİŞKİLERİNİN LİSTESİ", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void showUsersGraph() {

        UsernameCheckPanel usernameCheckPanel = new UsernameCheckPanel();
        usernameCheckPanel.setVisible(true);

    }

    private static void showInterestGraph() {

        InterestCheckPanel interestCheckPanel = new InterestCheckPanel();
        interestCheckPanel.setVisible(true);

    }

}
