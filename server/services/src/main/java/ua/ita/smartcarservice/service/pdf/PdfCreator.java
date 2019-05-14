package ua.ita.smartcarservice.service.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.Data;
import org.apache.log4j.Logger;
import ua.ita.smartcarservice.dto.booking.ReportExtendedDto;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Data
public class PdfCreator {

    private static final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

    private String fileName = "report.pdf";
    private String header = "Vehicle Inspection Report";
    private String footer = "By Smart Car Service system";
    private String serviceUrl = "http://ec2-18-221-198-253.us-east-2.compute.amazonaws.com:9501";
    private String imageUrl = "https://cdn4.iconfinder.com/data/icons/technical-support/256/96-512.png";

    private final int FONT_SIZE_HUGE = 32;
    private final int FONT_SIZE_MIDDLE = 25;
    private final int FONT_SIZE_BIG = 16;
    private final int FONT_SIZE_SMALL = 14;

    private final Font fontHeader = new Font(Font.FontFamily.HELVETICA, FONT_SIZE_HUGE, Font.BOLD);
    private final Font fontItalicUnderline = new Font(Font.FontFamily.HELVETICA, FONT_SIZE_BIG, Font.ITALIC | Font.UNDERLINE);
    private final Font fontSimple = new Font(Font.FontFamily.HELVETICA, FONT_SIZE_BIG);
    private final Font fontSimpleSmall = new Font(Font.FontFamily.HELVETICA, FONT_SIZE_SMALL);

    private PdfPTable table;
    private Document document;

    public byte[] getByteArrayWithPdfReport(ReportExtendedDto report){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        document = new Document();

        try {
            PdfWriter.getInstance(document, byteArrayOutputStream);
            document.open();
            formPdfReport(report);
            document.close();
        } catch (DocumentException e ) {
            logger.error("Error! Can not form PDF: " + e.getMessage());
        }

        return byteArrayOutputStream.toByteArray();
    }

    private void formPdfReport(ReportExtendedDto report) {
        try {
            setHeader(header);
            setImageWithParagraphs(report);
            document.add(getVehicleInspectionParagraph(report));
            setTableWithWorkTasks(report);
            setFooter(footer, serviceUrl);
        } catch (DocumentException | IOException e) {
            logger.error("Error! Can not form PDF: " + e.getMessage());
        }
    }


    private void setHeader(String header) throws DocumentException {
        Paragraph title = new Paragraph(header, fontHeader);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(FONT_SIZE_HUGE);
        document.add(title);
    }

    private void setImageWithParagraphs(ReportExtendedDto report) throws IOException, DocumentException {
        table = new PdfPTable(2);

        PdfPCell cell = new PdfPCell();
        cell.addElement(getCarInfoParagraph(report));
        cell.addElement(getTechnicalServiceParagraph(report));

        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);

        cell = new PdfPCell(getImageByUrl(imageUrl), true);
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);

        document.add(table);
    }

    private Image getImageByUrl(String imageUrl) throws IOException, BadElementException {
        Image stamp = Image.getInstance(new URL(imageUrl));
        stamp.scalePercent(35);
        stamp.setAlignment(Element.ALIGN_RIGHT);
        return stamp;
    }

    private Paragraph getCarInfoParagraph(ReportExtendedDto report) {
        Paragraph carInfo = new Paragraph("Car info", fontItalicUnderline);
        carInfo.setSpacingAfter(FONT_SIZE_HUGE);
        carInfo.add(new Paragraph("Model: " + report.getCarModel()));
        carInfo.add(new Paragraph("Brand: " + report.getCarBrand()));
        carInfo.add(new Paragraph("Number: " + report.getCarNumber()));
        return carInfo;
    }

    private Paragraph getTechnicalServiceParagraph(ReportExtendedDto report) {
        Paragraph technicalService = new Paragraph("Technical service details", fontItalicUnderline);
        technicalService.setSpacingAfter(FONT_SIZE_HUGE);
        technicalService.add(new Paragraph("Name: " + report.getTechnicalServiceName()));
        technicalService.add(new Paragraph("Address: " + report.getTechnicalServiceAddress()));
        return technicalService;
    }

    private Paragraph getVehicleInspectionParagraph(ReportExtendedDto report) throws DocumentException {
        Paragraph vehicleInspection = new Paragraph("Inspection details", fontItalicUnderline);
        vehicleInspection.setSpacingAfter(FONT_SIZE_HUGE);
        if (isTheSameDay(report.getStartTime(), report.getEndTime())) {
            vehicleInspection.add(new Paragraph("Date: " + parseDate(report.getStartTime())));
            vehicleInspection.add(new Paragraph(
                    "Time: " + parseTime(report.getStartTime()) + " - " + parseTime(report.getEndTime())));
        } else {
            vehicleInspection.add(new Paragraph(
                    "Date: " + parseDateTime(report.getStartTime()) + " - " + parseDateTime(report.getEndTime())));
        }

        String requiredTime = formatDouble(report.getRequiredTime() / 60.0);
        vehicleInspection.add(new Paragraph("Total time of work: " + requiredTime
                + (requiredTime.equals("1") ? " hours" : " hour")));
        vehicleInspection.add(new Paragraph("Price: " + report.getPrice() + " uah"));
        return vehicleInspection;
    }

    private void setTableWithWorkTasks(ReportExtendedDto report) throws DocumentException {
        Paragraph workDetails = new Paragraph("Work details", fontItalicUnderline);
        workDetails.setAlignment(Element.ALIGN_CENTER);
        workDetails.setSpacingAfter(FONT_SIZE_BIG);
        document.add(workDetails);

        table = new PdfPTable(4);
        table.setTotalWidth(new float[]{150, 100, 100, 100});
        table.setLockedWidth(true);

        addCellToTable("Worker Name");
        addCellToTable("Work done");
        addCellToTable("Time, hours");
        addCellToTable("Price, uah");

        report.getWorkerTasks().forEach((name, tasks) -> {
            addCellWithRowspan(name, tasks.size());
            tasks.forEach(task -> {
                addCellToTable(task.getName());
                addCellToTable(formatDouble(task.getRequiredTime() / 60.0));
                addCellToTable(String.valueOf(task.getCost()));
            });

        });
        document.add(table);
    }

    private void addCellToTable(String text) {
        saveCell(createCellWithText(text));
    }

    private void addCellWithRowspan(String text, int rowspan) {
        PdfPCell cell = createCellWithText(text);
        cell.setRowspan(rowspan);
        saveCell(cell);
    }

    private PdfPCell createCellWithText(String text) {
        PdfPCell cell = new PdfPCell(new Phrase(text, fontSimpleSmall));
        cell.setMinimumHeight(30);
        cell.setPadding(5);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        return cell;
    }

    private void saveCell(PdfPCell cell) {
        table.addCell(cell);
    }

    private void setFooter(String text, String link) throws DocumentException {
        Paragraph footer = new Paragraph();

        Anchor anchor = new Anchor(text);
        anchor.setReference(link);
        footer.add(anchor);

        footer.setAlignment(Element.ALIGN_CENTER);
        footer.setSpacingBefore(85);
        document.add(footer);
    }

    private String parseDate(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.UK));
    }

    private String parseTime(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    private String parseDateTime(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm", Locale.UK));
    }

    private boolean isTheSameDay(LocalDateTime start, LocalDateTime end) {
        return start.getDayOfMonth() == end.getDayOfMonth();
    }

    private String formatDouble(double num){
        DecimalFormat df = new DecimalFormat("###.#");
        return df.format(num);
    }

}
