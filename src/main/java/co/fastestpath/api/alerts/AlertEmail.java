package co.fastestpath.api.alerts;

import org.glassfish.jersey.media.multipart.FormDataParam;

// TODO: inject this once @BeanParam issue is resolved
// https://github.com/HubSpot/dropwizard-guice/issues/93
public class AlertEmail {

  @FormDataParam("headers")
  private String headers;

  @FormDataParam("dkim")
  private String dkim;

  @FormDataParam("content-ids")
  private String contentIds;

  @FormDataParam("to")
  private String to;

  @FormDataParam("from")
  private String from;

  @FormDataParam("html")
  private String html;

  @FormDataParam("text")
  private String text;

  @FormDataParam("sender_ip")
  private String senderIp;

  @FormDataParam("spam_report")
  private String spamReport;

  @FormDataParam("envelope")
  private String envelope;

  @FormDataParam("attachments")
  private String attachments;

  @FormDataParam("subject")
  private String subject;

  @FormDataParam("spam_score")
  private String spamScore;

  @FormDataParam("attachment-info")
  private String attachmentInfo;

  @FormDataParam("charsets")
  private String charsets;

  @FormDataParam("SPF")
  private String spf;

  public AlertEmail() { }

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

  public void setHeaders(String headers) {
    this.headers = headers;
  }

  public void setDkim(String dkim) {
    this.dkim = dkim;
  }

  public void setContentIds(String contentIds) {
    this.contentIds = contentIds;
  }

  public void setTo(String to) {
    this.to = to;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public void setHtml(String html) {
    this.html = html;
  }

  public void setText(String text) {
    this.text = text;
  }

  public void setSenderIp(String senderIp) {
    this.senderIp = senderIp;
  }

  public void setSpamReport(String spamReport) {
    this.spamReport = spamReport;
  }

  public void setEnvelope(String envelope) {
    this.envelope = envelope;
  }

  public void setAttachments(String attachments) {
    this.attachments = attachments;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public void setSpamScore(String spamScore) {
    this.spamScore = spamScore;
  }

  public void setAttachmentInfo(String attachmentInfo) {
    this.attachmentInfo = attachmentInfo;
  }

  public void setCharsets(String charsets) {
    this.charsets = charsets;
  }

  public void setSpf(String spf) {
    this.spf = spf;
  }
}
