<%@ page import="
			 com.lowagie.text.*,
	         java.awt.Color,
			 com.lowagie.text.pdf.*, 
			 com.lowagie.text.pdf.PdfWriter"
%>			
<%!
    public void loadDocument(Document document) {
        String[] bogusData = { "M0065920-12333-AEF-566",
        "SL",
        "FR86000P",
        "PCGOLD",
        "119000",
        "96 06",
        "2001-08-13",
        "4350",
        "6011648299",
        "FLFLMTGP",
        "153",
        "119000.00"
        };
        int NumColumns = 12;

        try {
        
            // we add some meta information to the document
            
            PdfPTable datatable = new PdfPTable(NumColumns);
            
            datatable.getDefaultCell().setPadding(3);
            int headerwidths[] = {9, 4, 8, 10, 8, 11, 9, 7, 9, 10, 4, 10}; // percentage
            datatable.setWidths(headerwidths);
            datatable.setWidthPercentage(100); // percentage
            
            datatable.getDefaultCell().setBorderWidth(2);
            datatable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            datatable.addCell("Clock #");
            datatable.addCell("Trans Type");
            datatable.addCell("Cusip");
            datatable.addCell("Long Name");
            datatable.addCell("Quantity");
            datatable.addCell("Fraction Price");
            datatable.addCell("Settle Date");
            datatable.addCell("Portfolio");
            datatable.addCell("ADP Number");
            datatable.addCell("Account ID");
            datatable.addCell("Reg Rep ID");
            datatable.addCell("Amt To Go ");
            
            datatable.setHeaderRows(1);  // this is the end of the table header
            
            datatable.getDefaultCell().setBorderWidth(1);
            
            int max = 666;
            for (int i = 1; i < max; i++) {
                if (i % 2 == 1) {
                    datatable.getDefaultCell().setGrayFill(0.9f);
                }
                for (int x = 0; x < NumColumns; x++) {
                    datatable.addCell(bogusData[x]);
                }
                if (i % 2 == 1) {
                    datatable.getDefaultCell().setGrayFill(0.9f);
                }
            }
            document.add(datatable);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

%>			
<%		

    response.setContentType( "application/pdf" );
		
	Document document = new Document(PageSize.LETTER, 10, 10, 10, 10);
    // step 2: we create a writer that listens to the document
    PdfWriter.getInstance(document, response.getOutputStream());
       
    Font titleFont = new Font(Font.HELVETICA, 18, Font.BOLDITALIC, new Color(128, 128, 128));
    
    Paragraph para = new Paragraph("Header",titleFont);
    Phrase x = new Phrase();

    x.add(new Paragraph("Paragrafo Um"));
    x.add(Chunk.NEWLINE);
    x.add(new Paragraph("Paragrafo Dois"));
    x.add(Chunk.NEWLINE);
    x.add(new Paragraph("Paragrafo Tres"));
    
    HeaderFooter header = new HeaderFooter(x,false);
    document.setHeader(header);

    HeaderFooter footer = new HeaderFooter(para,false);
    footer.setAlignment(Element.ALIGN_CENTER);
    document.setFooter(footer);

    document.open();
    this.loadDocument(document);
	document.close();

%>