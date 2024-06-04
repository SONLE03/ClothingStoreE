package com.sa.clothingstore.util.printInvoice.component;

import com.itextpdf.kernel.color.Color;
import com.sa.clothingstore.util.printInvoice.ConstantUtil;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Builder
public class HeaderDetails {
    String invoiceTitle= ConstantUtil.INVOICE_TITLE;
    String invoiceNoText=ConstantUtil.INVOICE_NO_TEXT;
    String invoiceDateText=ConstantUtil.INVOICE_DATE_TEXT;
    String invoiceNo = ConstantUtil.EMPTY;
    String invoiceDate = ConstantUtil.EMPTY;
    Color borderColor = Color.GRAY;
}
