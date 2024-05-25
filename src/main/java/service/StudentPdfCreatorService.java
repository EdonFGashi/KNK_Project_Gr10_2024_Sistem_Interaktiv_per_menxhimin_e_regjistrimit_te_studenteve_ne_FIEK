package service;

//import com.itextpdf.layout.element.Cell;
import com.itextpdf.text.*;
//import com.itextpdf.layout.element.Table;

import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import repository.AdminRepository;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class StudentPdfCreatorService {
    public static void main(String[] args){
        Date data = new Date(2004 - 1880, 01 - 1, 04);
        StudentPdfCreatorService.generatePdf(200213,
                20031232,
                LocalDate.now(),
                "220756100086",
                "Edison",
                "Ramadani",
                "04/04/2004",
                "Baks",
                "I rregullt",
                2022,
                "BSC",
                "Inxhinieri Kompjuterike",
                "Inxhinieri Kompjuterike dhe Softuerike",
                "Për nevoja personale",
                "Edon Gashi",
                "path");
    }
    public static void generatePdf(int nrRendor,
                                   int nrSerik,
                                   LocalDate dataAktuale,
                                   String idStudentit,
                                   String emri,
                                   String mbiemri,
                                   String dataLindjes,
                                   String vendlindja,
                                   String statusiStudentit,
                                   int vitiRegjistrimit,
                                   String niveli,
                                   String departamenti,
                                   String programiStudimit,
                                   String arsyejaLeshimit,
                                   String emriAdminit,
                                   String path
    ) {
        try {
//            String file_name = "C:\\Users\\Dell\\Downloads\\"+ emri + "_" + mbiemri + "_" + dataAktuale +".pdf";
            String file_name = path + emri + "_" + mbiemri + "_" + dataAktuale +".pdf";
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, new FileOutputStream(file_name));
            document.open();

            Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 13, Font.BOLD);
            Paragraph para = new Paragraph("             Universiteti i Prishtinës \"Hasan Prishtina\" \n              University of Prishtina \"Hasan Prishtina\"" ,boldFont);
            Font fontSmall = new Font(Font.FontFamily.TIMES_ROMAN, 10);
            Paragraph para1 = new Paragraph("\n                       Fakulteti i Inxhinierisë Elektrike dhe Kompjuterike" ,fontSmall);
//            document.add(para);

            float kolonaLogo = 87f;
            float kolonaEmriUp = 348f;
            float kolonatHeaderit[] = {kolonaLogo, kolonaEmriUp};

            // Create a headerName with 2 columns
            PdfPTable headerName = new PdfPTable(2);
            headerName.setWidthPercentage(100); // Set the headerName width to 100% of the page width
            headerName.setWidths(kolonatHeaderit);

            PdfPCell cell1 = new PdfPCell(new Paragraph("Cell 1"));
            PdfPCell cell2 = new PdfPCell(para);
            PdfPCell cell3 = new PdfPCell(para1);
            cell1.setRowspan(2);
            cell3.setBorderWidth(0);

            Image image = Image.getInstance("Images/UPLOGOO.png");
            image.scaleAbsolute(70f, 70f);
            // Scale the image to fit inside the cell
//            image.scaleToFit(cell1.getWidth(), cell1.getHeight());

            cell1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            cell1.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
            // Add the image to the cell
            cell1.addElement(image);

            headerName.addCell(cell1);
            headerName.addCell(cell2);
            headerName.addCell(cell3);

//            Header information email, adresa...
            float headerInfoGjersiaKolonave[] = {kolonaLogo, kolonaLogo, kolonaLogo, kolonaLogo, kolonaLogo};
            PdfPTable headerInfo = new PdfPTable(5);
            headerInfo.setWidthPercentage(100);
            headerInfo.setWidths(headerInfoGjersiaKolonave);

            headerInfo.addCell(nestedTwoTable("Adresa: ", "Bregu i Diellit, pn", 7, 27f, 61f, Font.BOLD, Font.NORMAL, PdfPCell.ALIGN_RIGHT));
            headerInfo.addCell(nestedTwoTable("Tel: ", "+383(0) 38 554 896", 7, 27f, 61f, Font.BOLD, Font.NORMAL, PdfPCell.ALIGN_RIGHT));
            headerInfo.addCell(nestedTwoTable("Fax: ", "+383(0)38 542 525", 7, 27f, 61f, Font.BOLD, Font.NORMAL, PdfPCell.ALIGN_RIGHT));
            headerInfo.addCell(nestedTwoTable("Email: ", "fiek@uni-pr.edu", 7, 27f, 61f, Font.BOLD, Font.NORMAL, PdfPCell.ALIGN_RIGHT));
            headerInfo.addCell(nestedTwoTable("Web: ", "http://fiek.uni-pr.edu/", 7, 27f, 61f, Font.BOLD, Font.NORMAL, PdfPCell.ALIGN_RIGHT));

//            headerInfo.setSpacingBefore(10f);


            //Borderi i lart per numer serik
            PdfPTable tablePerBorderLart1 = new PdfPTable(1);
            tablePerBorderLart1.setWidthPercentage(100);
            tablePerBorderLart1.setSpacingBefore(10f);
            tablePerBorderLart1.addCell(border(1f, BaseColor.GRAY));

            PdfPTable tablePerBorderLart2 = new PdfPTable(1);
            tablePerBorderLart2.setWidthPercentage(100);
            tablePerBorderLart2.setSpacingBefore(4f);
            tablePerBorderLart2.addCell(border(1f, BaseColor.GRAY));

            //Tabela per numrin serik
            PdfPTable rreshtiPerNumrinSerik = new PdfPTable(3);
            rreshtiPerNumrinSerik.setWidthPercentage(100); // Set the headerName width to 100% of the page width

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            rreshtiPerNumrinSerik.addCell(nestedTwoTable("Numri rendor: ", String.valueOf(nrRendor), 8, 60f, 57f, Font.BOLD, Font.NORMAL, PdfPCell.ALIGN_RIGHT));
            rreshtiPerNumrinSerik.addCell(nestedTwoTable("Numri serik: ", String.valueOf(nrSerik), 8, 30f, 57f, Font.BOLD, Font.NORMAL, PdfPCell.ALIGN_RIGHT));
            rreshtiPerNumrinSerik.addCell(nestedTwoTable("Data: ", dataAktuale.format(dateFormatter), 8, 60f, 25f, Font.BOLD, Font.NORMAL, PdfPCell.ALIGN_RIGHT));


            //Borderi i poshtem per numer serik
            PdfPTable tablePerBorderPosht1 = new PdfPTable(1);
            tablePerBorderPosht1.setWidthPercentage(100);
            tablePerBorderPosht1.setSpacingBefore(2f);
            tablePerBorderPosht1.addCell(border(1f, BaseColor.GRAY));

            PdfPTable tablePerBorderPosht2 = new PdfPTable(1);
            tablePerBorderPosht2.setWidthPercentage(100);
            tablePerBorderPosht2.setSpacingBefore(4f);
            tablePerBorderPosht2.addCell(border(1f, BaseColor.GRAY));


            //Pershkrimi i dokumentit
            Font boldFontBig = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD);
            Paragraph pershkrimiDokumentit = new Paragraph("VËRTETIM | CERTIFICATE | POTVRDA" ,boldFontBig);
            pershkrimiDokumentit.setAlignment(Paragraph.ALIGN_CENTER);

            //tabela kryesore
            PdfPTable tabelaKryesore = new PdfPTable(1);
            tabelaKryesore.setWidthPercentage(100); // Set the headerName width to 100% of the page width
            tabelaKryesore.setSpacingBefore(0f);

            //rreshti i pare
            PdfPTable rreshtiPare = new PdfPTable(2);
            rreshtiPare.setWidthPercentage(100); // Set the headerName width to 100% of the page width
            rreshtiPare.addCell(nestedTwoTable("Shërbimi i studentëve të fakultetit\nStudent's service of the faculty\nStudentska služba fakulteta", " ", 8, 108f, 108f,Font.NORMAL, Font.NORMAL, PdfPCell.ALIGN_LEFT));
            rreshtiPare.addCell(nestedTwoTable("Nr. i indeksit/Id\nNr. of index/Id\nBr. na indeksa/Id", idStudentit, 8, 108f, 108f, Font.NORMAL, Font.NORMAL, PdfPCell.ALIGN_CENTER));
            for (PdfPCell cell : rreshtiPare.getRows().get(0).getCells()) {
                cell.setBorderWidth(0);
            }
            tabelaKryesore.addCell(rreshtiPare);

            //rreshti i dyte
            PdfPTable rreshtiDyte = new PdfPTable(2);
            rreshtiDyte.setWidthPercentage(100);
            float gjersiaKolonaveRreshtiDyte[] = {120f, 314f};
            rreshtiDyte.setWidths(gjersiaKolonaveRreshtiDyte);
            rreshtiDyte.setSpacingBefore(10f);

            Font fontNormalSmall = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.NORMAL);
            Paragraph teDhenatEmriMbiemri = new Paragraph("Emri dhe Mbiemri\nName and Surname\nIme i Prezime", fontNormalSmall);
            PdfPCell kolonaPerEmerMbiemer = new PdfPCell(teDhenatEmriMbiemri);
            kolonaPerEmerMbiemer.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
            rreshtiDyte.addCell(kolonaPerEmerMbiemer);

            Font fontBoldBig = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD | Font.UNDERLINE);
            Paragraph emriMbiemriStudentit = new Paragraph(emri + " " + mbiemri, fontBoldBig);
            PdfPCell kolonaEmriMbiemriStudentit = new PdfPCell(emriMbiemriStudentit);
            kolonaEmriMbiemriStudentit.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            kolonaEmriMbiemriStudentit.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
            rreshtiDyte.addCell(kolonaEmriMbiemriStudentit);

            for (PdfPCell cell : rreshtiDyte.getRows().get(0).getCells()) {
                cell.setBorderWidth(0);
            }

            tabelaKryesore.addCell(rreshtiDyte);

            //rreshti i trete
            PdfPTable rreshtiTrete = new PdfPTable(3);
            rreshtiTrete.setWidthPercentage(100); // Set the headerName width to 100% of the page width
            rreshtiDyte.setSpacingBefore(5f);

            float gjersiaKolonaveRreshtiTrete[] = {120f, 80f, 294f};
            rreshtiTrete.setWidths(gjersiaKolonaveRreshtiTrete);
            SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");
            rreshtiTrete.addCell(nestedTwoTable("I/e lindur më:\nBorn on:\nRodjen/a:", dataLindjes, 8, 25f, 24f,Font.NORMAL, Font.BOLD, PdfPCell.ALIGN_LEFT));
            rreshtiTrete.addCell(nestedTwoTable("Në: \nIn: \nU: ", vendlindja, 8, 15f, 35f, Font.NORMAL, Font.BOLD, PdfPCell.ALIGN_CENTER));
            rreshtiTrete.addCell(nestedTwoTable("Statusi i studentit: \nStatus of student: \nStatus studenta: ", "  " + statusiStudentit, 8, 190f, 143, Font.NORMAL, Font.BOLD, PdfPCell.ALIGN_RIGHT));

            for (PdfPCell cell : rreshtiTrete.getRows().get(0).getCells()) {
                cell.setBorderWidth(0);
            }

            tabelaKryesore.addCell(rreshtiTrete);

            //rreshti i katërt
            PdfPTable rreshtiKatert = new PdfPTable(1);
            rreshtiKatert.setWidthPercentage(100); // Set the headerName width to 100% of the page width
            rreshtiKatert.setSpacingBefore(5f);
            rreshtiKatert.addCell(nestedTwoTable("I regjistruar për herë parë në vitin akademik:\nEnrolled for the first time in academic year:\nUpisani po prvi put na akademsku godinu:", String.valueOf(vitiRegjistrimit), 8, 120f, 270f,Font.NORMAL, Font.BOLD | Font.UNDERLINE, PdfPCell.ALIGN_LEFT));

            for (PdfPCell cell : rreshtiKatert.getRows().get(0).getCells()) {
                cell.setBorderWidth(0);
            }

            tabelaKryesore.addCell(rreshtiKatert);

            //vije ndarese
            PdfPTable tablePerBorder1 = new PdfPTable(1);
            tablePerBorder1.setWidthPercentage(100);
            tablePerBorder1.setSpacingBefore(3f);
            tablePerBorder1.addCell(border(1f, BaseColor.BLACK));

            tabelaKryesore.addCell(tablePerBorder1);

            //rreshti i pest
            PdfPTable rreshtiPest = new PdfPTable(1);
            rreshtiPest.setWidthPercentage(100); // Set the headerName width to 100% of the page width
            rreshtiPest.setSpacingBefore(5f);
            String niveliEnglish = "";
            String niveliSrb = "";
            if(niveli.equals("BSC") || niveli.equals("Bachelor") || niveli.equals("Baçelor")){
                niveli = "Baçelor";
                niveliEnglish = "Bachelor";
                niveliSrb = "Bačelorat";
            } else if(niveli.equals("MSC") || niveli.equals("Master")){
                niveli = "Master";
                niveliEnglish = "Master";
                niveliSrb = "Master";
            } else if(niveli.equals("Ph.D.") || niveli.equals("DOC") || niveli.equals("Doktorratur") || niveli.equals("Doktorraturë") || niveli.equals("Doktoratur") || niveli.equals("Doktoraturë")){
                niveli = "Ph.D.";
                niveliEnglish = "Ph.D.";
                niveliSrb = "Dr.";
            }
            rreshtiPest.addCell(nestedTwoTable("Është student në studimet " + niveli+" në:\nIs a student of " + niveliEnglish +" studies in:\nJe student u " + niveliSrb +" studije u:", "Fakulteti i Inxhinierisë Elektrike dhe Kompjuterike", 8, 120f, 270f,Font.NORMAL, Font.BOLD | Font.UNDERLINE, PdfPCell.ALIGN_LEFT));

            for (PdfPCell cell : rreshtiPest.getRows().get(0).getCells()) {
                cell.setBorderWidth(0);
            }

            tabelaKryesore.addCell(rreshtiPest);


            //rreshti i gjashte
            PdfPTable rreshtiGjashte = new PdfPTable(2);
            rreshtiGjashte.setWidthPercentage(100); // Set the headerName width to 100% of the page width
            rreshtiGjashte.setSpacingBefore(5f);
            rreshtiGjashte.addCell(nestedTwoTable("Departamenti:\nDepartment:\nOdeljenje:", "", 8, 215f, 2f,Font.NORMAL, Font.NORMAL, PdfPCell.ALIGN_LEFT));
            rreshtiGjashte.addCell(nestedTwoTable("Programi i studimit:\nStudy program:\nStudijski program:", "", 8, 215f, 2f,Font.NORMAL, Font.NORMAL, PdfPCell.ALIGN_LEFT));
            rreshtiGjashte.addCell(nestedTwoTable("", departamenti, 8, 1f, 215f,Font.NORMAL, Font.BOLD | Font.UNDERLINE, PdfPCell.ALIGN_LEFT));
            rreshtiGjashte.addCell(nestedTwoTable("", programiStudimit, 8, 1f, 215f,Font.NORMAL, Font.BOLD | Font.UNDERLINE, PdfPCell.ALIGN_LEFT));

            for (PdfPCell cell : rreshtiGjashte.getRows().get(0).getCells()) {
                cell.setBorderWidth(0);
            }
            rreshtiGjashte.getRow(1).getCells()[0].setBorderWidth(0);
            rreshtiGjashte.getRow(1).getCells()[1].setBorderWidth(0);


            tabelaKryesore.addCell(rreshtiGjashte);

            //rreshti i tete
            PdfPTable rreshtiTete = new PdfPTable(1);
            rreshtiTete.setWidthPercentage(100); // Set the headerName width to 100% of the page width
            rreshtiTete.setSpacingBefore(5f);
            rreshtiTete.addCell(nestedTwoTable("Vërtetimi i lëshohet kandidatit/es si dëshmi për ta rregulluar të drejtën e\nThe certificate is issued with the request of the student for\nPotvrda se izdaje kandidatu da posluži kao dokaz za", "", 8, 300f, 100f,Font.NORMAL, Font.NORMAL, PdfPCell.ALIGN_LEFT));
            rreshtiTete.addCell(nestedTwoTable("", arsyejaLeshimit, 8, 1f, 400f,Font.NORMAL, Font.BOLD | Font.UNDERLINE, PdfPCell.ALIGN_LEFT));

            rreshtiTete.getRow(1).getCells()[0].setBorderWidth(0);
            for (PdfPCell cell : rreshtiTete.getRows().get(0).getCells()) {
                cell.setBorderWidth(0);
            }

            tabelaKryesore.addCell(rreshtiTete);

            //hapesire
            PdfPTable tablePerBorder2 = new PdfPTable(1);
            tablePerBorder2.setWidthPercentage(100);
            tablePerBorder2.setSpacingBefore(100f);
            tablePerBorder2.addCell(border(1f, BaseColor.BLACK));

            tabelaKryesore.addCell(tablePerBorder2);


            //rreshti i fundit
            PdfPTable rreshtiNente = new PdfPTable(2);
            rreshtiNente.setWidthPercentage(100); // Set the headerName width to 100% of the page width


            Font fontBoldBig2 = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.NORMAL | Font.UNDERLINE);
            Paragraph paragraph = new Paragraph("                   "+emriAdminit+"                   .\n ", fontBoldBig2);
            PdfPCell nenshkrimiCell = new PdfPCell(paragraph);
            nenshkrimiCell.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
            nenshkrimiCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            nenshkrimiCell.setRowspan(2);

            rreshtiNente.addCell(nestedTwoTable("Shërbimi i studentëve\nStudent's service\nStudentska služba\n", "", 8, 215f, 1f,Font.NORMAL, Font.NORMAL, PdfPCell.ALIGN_LEFT));
            rreshtiNente.addCell(nenshkrimiCell);
            rreshtiNente.addCell(nestedTwoTable("Nënshkrimi i personit përgjegjës dhe vula\nThe responsible persons's and seal signature\nPotpis odgovornog lica i pečat\n ", "", 8, 215f, 1f, Font.NORMAL, Font.NORMAL, PdfPCell.ALIGN_LEFT));

            rreshtiNente.getRow(1).getCells()[0].setBorderWidth(0);
            for (PdfPCell cell : rreshtiNente.getRows().get(0).getCells()) {
                cell.setBorderWidth(0);
            }

            tabelaKryesore.addCell(rreshtiNente);




            for (PdfPCell cell : tabelaKryesore.getRows().get(0).getCells()) {
                cell.setBorderWidth(0);
            }
            tabelaKryesore.getRow(1).getCells()[0].setBorderWidth(0);
//            tabelaKryesore.getRow(2).getCells()[0].setBorderWidth(0);
            tabelaKryesore.getRow(3).getCells()[0].setBorderWidth(0);
            tabelaKryesore.getRow(4).getCells()[0].setBorderWidth(0);
            tabelaKryesore.getRow(5).getCells()[0].setBorderWidth(0);
//            tabelaKryesore.getRow(6).getCells()[0].setBorderWidth(0);
            tabelaKryesore.getRow(7).getCells()[0].setBorderWidth(0);
            tabelaKryesore.getRow(8).getCells()[0].setBorderWidth(0);



            PdfPTable tabelaJashtme = new PdfPTable(1);
            tabelaJashtme.addCell(new PdfPCell(tabelaKryesore));
            tabelaJashtme.getDefaultCell().setBorderWidth(5f);



            // Add the headerName to the document
            headerName.getDefaultCell().setBorderWidth(0);
            for (PdfPCell cell : headerName.getRows().get(0).getCells()) {
                cell.setBorderWidth(0);
            }
            for (PdfPCell cell : headerInfo.getRows().get(0).getCells()) {
                cell.setBorderWidth(0);
            }
            rreshtiPerNumrinSerik.getDefaultCell().setBorderWidth(0);
            for (PdfPCell cell : rreshtiPerNumrinSerik.getRows().get(0).getCells()) {
                cell.setBorderWidth(0);
            }




//            for (PdfPCell cell : tabelaJashtme.getRows().get(0).getCells()) {
//                cell.setBorderWidth(0);
//            }
            tabelaJashtme.setSpacingBefore(20f);
            tabelaJashtme.setWidthPercentage(100);

            document.add(headerName);
            document.add(headerInfo);
            document.add(tablePerBorderLart1);
            document.add(tablePerBorderLart2);
            document.add(rreshtiPerNumrinSerik);
            document.add(tablePerBorderPosht1);
            document.add(tablePerBorderPosht2);
            document.add(pershkrimiDokumentit);
//            document.add(tabelaKryesore);
            document.add(tabelaJashtme);

            document.close();
        } catch (Exception a) {
            // Handle exceptions here...
            a.printStackTrace();
        }
    }

    private static PdfPCell nestedTwoTable(String teksti1, String teksti2, int madhesiaTekstit, float gjeresiaTitullit, float gjeresiaPershkrimit, int fontiTitullit, int fontiPershkrimit, int align) throws DocumentException {
        PdfPCell outerCell = new PdfPCell();
        PdfPTable nestedTable = new PdfPTable(2);

        float nestedHeaderInfoGjersiaKolonave[] = {gjeresiaTitullit, gjeresiaPershkrimit};
        nestedTable.setWidths(nestedHeaderInfoGjersiaKolonave);
        nestedTable.setWidthPercentage(100);

        Font fontBoldSmall = new Font(Font.FontFamily.TIMES_ROMAN, madhesiaTekstit, fontiTitullit);
        Font fontSmall = new Font(Font.FontFamily.TIMES_ROMAN, madhesiaTekstit, fontiPershkrimit);
        Paragraph titulli = new Paragraph(teksti1 ,fontBoldSmall);
        Paragraph pershkrimi = new Paragraph(teksti2 ,fontSmall);
        PdfPCell titulliCell = new PdfPCell(titulli);
        titulliCell.setHorizontalAlignment(align);
        PdfPCell pershkrimiCell = new PdfPCell(pershkrimi);
        pershkrimiCell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        titulliCell.setBorderWidth(0);
        pershkrimiCell.setBorderWidth(0);
        nestedTable.addCell(titulliCell);
        nestedTable.addCell(pershkrimiCell);
        for (PdfPCell cell : nestedTable.getRows().get(0).getCells()) {
            cell.setBorderWidth(0);
        }

        outerCell.addElement(nestedTable);
        return outerCell;
    }

    private static PdfPCell border(float trashesia, BaseColor color){
        PdfPCell vija = new PdfPCell();
        vija.setBorderWidth(trashesia);
        vija.setBorderColor(color); // Sets border color to red
        return vija;
    }


}
