package co.fastestpath.api.alerts;

import org.glassfish.jersey.media.multipart.FormDataParam;

class AlertEmail {

  private String headers;

  private String dkim;

  @FormDataParam("content-ids")
  private String contentIds;

  private String to;

  private String from;

  private String html;

  private String text;

  @FormDataParam("sender_ip")
  private String senderIp;

  @FormDataParam("spam_report")
  private String spamReport;

  private String envelope;

  private String attachments;

  private String subject;

  @FormDataParam("spam_score")
  private String spamScore;

  @FormDataParam("attachment-info")
  private String attachmentInfo;

  private String charsets;

  @FormDataParam("SPF")
  private String spf;

  public String getHeaders() {
    return headers;
  }

  public String getDkim() {
    return dkim;
  }

  public String getContentIds() {
    return contentIds;
  }

  public String getTo() {
    return to;
  }

  public String getFrom() {
    return from;
  }

  public String getHtml() {
    return html;
  }

  public String getText() {
    return text;
  }

  public String getSenderIp() {
    return senderIp;
  }

  public String getSpamReport() {
    return spamReport;
  }

  public String getEnvelope() {
    return envelope;
  }

  public String getAttachments() {
    return attachments;
  }

  public String getSubject() {
    return subject;
  }

  public String getSpamScore() {
    return spamScore;
  }

  public String getAttachmentInfo() {
    return attachmentInfo;
  }

  public String getCharsets() {
    return charsets;
  }

  public String getSpf() {
    return spf;
  }
}
