<p align="center">
  <a>
    <img src="logo1.png" alt="Logo" height="40">
  </a>

<h3 align="center">Sistemi për menaxhimin e regjistrimit të studentëve - FIEK</h3>
</p><br>

## Përmbledhje e Projektit

Ky projekt, është një sistem gjithëpërfshirës i projektuar për të menaxhuar dhe thjeshtuar procesin e regjistrimit të studentëve në universitet. Ai përfshin role të ndryshme, përfshirë Adminin, Mbikëqyrësin dhe Studentin, secili me funksionalitete specifike. Sistemi është ndërtuar duke përdorur Java dhe JavaFX, dhe përfshin disa kontrollues dhe FXML files për të trajtuar ndërfaqen e përdoruesit.

## Struktura e Projektit

Struktura e projektit është e organizuar si më poshtë:

- **src/main/java/app**: Përmban files kryesorë të aplikacionit.
  - `Main.java`: Inicializon dhe nis window kryesorë të aplikacionit.
  - `Navigator.java`: Trajton navigimin midis faqeve të ndryshme.
    - `AdminPages.java`
    - `OverallPages.java`
    - `StudentPages.java`
    - `SupervisorPages.java`
    - `PopUp.java`

- **src/main/java/controller**: Përmban kontrolluesit për funksionalitete dhe role të ndryshme.
  - **Admin**
    - `InboxController.java`
    - `MenuController.java`
    - `NjoftimPaneController.java`
    - `ProfileController.java`
    - `RegistrationMenuAddRegistrationController.java`
    - `RegistrationMenuShowAndEditController.java`
    - `ResetPasswordController.java`
    - `RibbonController.java`
    - `StatisticsController.java`
  - **Animations**
    - `Tick.java`
    - `UpLogoAnimate.java`
  - **Help**
    - `AdminHelpController.java`
    - `DashboardHelpController.java`
    - `StudentHelpController.java`
    - `SupervisorHelpController.java`
  - **Overall**
    - `ChangePassword.java`
    - `DashboardController.java`
    - `Error404Controller.java`
    - `LoginController.java`
    - `SignUpController.java`
  - **Student**
    - `GradePointsController.java`
    - `MenuController.java`
    - `ProfileController.java`
    - `RibbonController.java`
    - `SupervisorMenuController.java`
  - **Supervisor**
    - `StudentMenuShowAndEditController.java`
    - `StudentMenuStatisticsController.java`
    - `SupervisorMenuAddController.java`
    - `SupervisorMenuApproveController.java`
    - `SupervisorMenuEditController.java`
  - **BackAndForth**
    - `CommunicativeController.java`
    - `SESSION.java`
  - **Model**

- **src/main/resources/app**: Përmban FXML files që përcaktojnë ndërfaqen/GUI e përdoruesit.
  - `academic-interest.fxml`
  - `admin-inbox.fxml`
  - `admin-Menu.fxml`
  - `admin-profile.fxml`
  - `admin-registrationMenu-showAndEdit.fxml`
  - `admin-registrationPeriodMenu-addRegistration.fxml`
  - `admin-resetPassword.fxml`
  - `admin-ribbon.fxml`
  - `admin-studentMenu-konkurimet.fxml`
  - `admin-studentMenu-showAndEdit.fxml`
  - `admin-studentsMenu-statistics.fxml`
  - `admin-supervisorMenu-addSupervisor.fxml`
  - `admin-supervisorMenu-approveSupervisor.fxml`
  - `admin-supervisorMenu-editSupervisor.fxml`
  - `admin-supervisorMenu-showAndEditSupervisor.fxml`
  - `changePassword.fxml`
  - `educational-experience.fxml`
  - `EducationalMaster.fxml`
  - `EducationalPHD.fxml`
  - `EducationalPhd2.fxml`
  - `help-admin.fxml`
  - `help-dashboard.fxml`
  - `help-student.fxml`
  - `help-supervisor.fxml`
  - `overall-dashboard.fxml`
  - `overall-dashboard2.fxml`
  - `overall-error404.fxml`
  - `overall-login.fxml`

## Karakteristikat dhe Funksionaliteti

### Faqja e Adminit

Faqja e Adminit është qendra e kontrollit për menaxhimin e gjithë aplikacionit. Admini ka akses në funksionalitete të ndryshme përfshirë:

- **Menaxhimi i Inbox-it**: Trajton mesazhet dhe njoftimet e ardhshme.
- **Menaxhimi i Menusë**: Navigon në seksionet e ndryshme administrative.
- **Menaxhimi i Regjistrimit**:Shtoni periudha të reja regjistrimi.
- **Menaxhimi i Studentëve**:
  - Shikon detajet e konkurrimeve të studentëve.
  - Shfaq informacionin e studentëve.
  - Akses në statistikat e studentëve.
- **Menaxhimi i Mbikëqyrësve**:
  - Shton mbikëqyrës të rinj.
  - Miraton ose refuzon aplikimet e mbikëqyrësve.
  - Kontrollon detajet e mbikëqyrësve ekzistues.
  - Shikon dhe kontrollon mbikëqyrësit ekzistues.
- **Rivendosja e Fjalëkalimit**: Rivendosn fjalëkalimet për përdoruesit.

### Faqja e Mbikëqyrësit

Faqja e Mbikëqyrësit lejon mbikëqyrësit të menaxhojnë pikët për provimet e pranimit dhe të mbikëqyrin aplikimet e studentëve.
- **Menaxhimin e Pikëve të Provimit Pranues**: Vendos dhe përditëson pikët për provimet pranuese të studentëve.

### Faqja e Studentit

Faqja e Studentit është vendi ku studentët mund të aplikojnë për programe universitare dhe të menaxhojnë profilet e tyre. Karakteristikat kryesore përfshijnë:

- **Aplikimit**: Mundësohet aplikimi në drejtimin e dëshiruar.
- **Menaxhimi i Profilit**: Mundësohet menaxhimi i të dhënave personale.

### Pjesa e Kontrollit/Controllers:

[Projekti përfshin disa pamje të controllerve për role të ndryshme:

- **Controller i Përgjithshëm**
- **Controller i Administratorit**
- **Controller i Studentit**
- **Controller i Mbikëqyrësit**

## Përfundimi

### Tools të nevojshëm:

- Java Development Kit (JDK) 8 ose më i lartë
- JavaFX SDK
- Një IDE si IntelliJ
- Scene Builder

### Instalimi

1. Klononi:
   ```bash
   git clone https://github.com/EdonFGashi/KNK_Project_Gr10_2024_Sistem_Interaktiv_per_menxhimin_e_regjistrimit_te_studenteve_ne_FIEK)](https://github.com/EdonFGashi/KNK_Project_Gr10_2024_Sistem_Interaktiv_per_menxhimin_e_regjistrimit_te_studenteve_ne_FIEK

### Punuan:
- Jon Kuqi
- Kaltrina Krasniqi
- Edon Gashi
- Erza Gashi
- Dren Morina
- Endrit Kastrati
